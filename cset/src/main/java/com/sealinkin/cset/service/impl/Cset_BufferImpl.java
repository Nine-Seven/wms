package com.sealinkin.cset.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.cdef.po.Cdef_Defarea;
import com.sealinkin.cdef.po.Cdef_DefareaLog;
import com.sealinkin.cdef.po.Cdef_Defcell;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.cost.model.Cost_OtherListModel;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;
import com.sealinkin.cset.model.Cset_BufferModel;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_AreaBackupM;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.cset.po.Oset_Buffer;
import com.sealinkin.cset.service.ICset_BufferService;
import com.sealinkin.cset.service.ICset_CellArticleService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.bdef.AnsPickCellInPutBarcodeModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cset_BufferImpl implements ICset_BufferService{
	
	private IGenericManager genDao;
	
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取暂存区信息列表
	@Override
	public ExtListDataBo<Cset_BufferModel> getBufferList(
			String strEnterpriseNo, String strWarehouseNo, String strOwnerNo,
			String strQuery, PageBo pageBo, Integer requestFlag)
			throws Exception {
		//8-10屏蔽
		/*String sql="select distinct a.* , " +
				"'['|| ltrim(a.status)||']'||f_get_fieldtext('OSET_BUFFER','STATUS',a.status)statusText " +
				" from " + 
		           "oset_buffer a " +
				   " where 1=1  " ;  */
		
		String sql="select distinct a.* , " +
				"'['|| ltrim(a.status)||']'||f_get_fieldtext('OSET_BUFFER','STATUS',a.status)statusText " +
				" from " + 
		           "oset_buffer a,cdef_defcell c " +
				   " where 1=1  " +
				   " and a.cell_no=c.cell_no " +
				   " and a.enterprise_no = '" + strEnterpriseNo + "'" +
				   " and a.warehouse_no = '" + strWarehouseNo + "'";
				   
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}	
		sql =sql +  "order by a.enterprise_no desc ";
		String strTotsql="select count(*) from (" + sql + ") " ;	
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		List<Cset_BufferModel> list = genDao.getListByNativeSql(sql,Cset_BufferModel.class/*,pageBo.getStart(), pageBo.getPagesize()*/);
		ExtListDataBo<Cset_BufferModel> extListBo = new ExtListDataBo<Cset_BufferModel>(list,intCount);
		return extListBo;		
		
	}
	//保存新增或者修改信息
	@Override
	public MsgRes saveBuffer_Ware(String enterpriseNo,String strWarehouseNo,
			String str,String strWorkerNo, String strwareNo,String strareaNo,
			String strbufferName,String strstatus) 
					throws Exception {
		Oset_Buffer defArea=(Oset_Buffer)JSON.parseObject(str,Oset_Buffer.class);
		this.genDao.saveOrUpdateObj(defArea);
		return new MsgRes(true,"保存成功","");
		
	}
	//判断暂存区货位是否唯一
		@Override
		public List<String> cellCheck(String enterpriseNo,String strWarehouseNo,
				String strwareNo,String strareaNo,
				String strstockNo, String strQuery1)
				throws Exception {
		String sql=	"select a.* from oset_buffer a " +
					"where 1=1 and a.ware_no= '" + strwareNo + "' " +
					"and a.area_no='"+strareaNo+"' " +
					"and a.stock_no='"+strstockNo+"' " +
					"and a.cell_no='"+strQuery1+"' " +
					"and a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+strWarehouseNo+"' " ;	
			List list = genDao.getListByNativeSql(sql);
			return (List<String>) list;
		}
	//获取库区下拉框（UI）
	@Override
	public List<ComboxBo> getBufferWareCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select distinct a.ware_no value , b.ware_name text,'['|| ltrim(a.ware_no)||']'||b.ware_name dropValue  " +
				"from oset_buffer a  , cdef_defware b ,cdef_defarea c " +
				"where a.warehouse_No=c.warehouse_No and a.enterprise_no=c.enterprise_no and  c.area_attribute='1' and c.attribute_type='5' and a.warehouse_No=b.warehouse_No and a.enterprise_no=b.enterprise_no and a.ware_no=b.ware_no and a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;	
		if(str!=null && !str.equals(""))
			
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//获取储区下拉框（UI）
	@Override
	public List<ComboxBo> getBufferAreaCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select distinct a.area_no value , b.area_name text,'['|| ltrim(a.area_no)||']'||b.area_name dropValue  " +
				"from oset_buffer a  , cdef_defarea b " +
				"where b.area_attribute='1' and b.attribute_type='5' and a.warehouse_No=b.warehouse_No  and a.enterprise_no=b.enterprise_no and a.ware_no=b.ware_no and a.area_no=b.area_no and a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		sql=sql+" order by a.area_no ";
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	///获取通道下拉框（UI）
	@Override
	public List<ComboxBo> getBufferStockCombo(String enterpriseNo,
			String warehouseNo, String str, int i, int j) {
		String sql="select distinct a.stock_no value, a.stock_no text, '['|| ltrim(a.stock_no)||']'||a.stock_no dropValue " +
				"from oset_buffer a ,cdef_defarea b " +
				"where b.area_attribute='1' and b.attribute_type='5' and a.warehouse_No=b.warehouse_No  and a.enterprise_no=b.enterprise_no and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
}
	//获取库区下拉框（Windows）
	@Override
	public List<ComboxBo> getBufferAddWareCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select distinct a.ware_no value , a.ware_name text,'['|| ltrim(a.ware_no)||']'||a.ware_name dropValue  " +
				"from cdef_defware a , cdef_defarea b " +
				"where a.warehouse_No=b.warehouse_No and a.enterprise_no=b.enterprise_no and a.ware_no=b.ware_no and b.area_attribute='1' and b.attribute_type='5' and a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;	
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//获取储区下拉框（Windows）
	@Override
	public List<ComboxBo> getBufferAddAreaCombo(String enterpriseNo,String warehouseNo,
			String str, 
			int start,
			int pagesize) {
		String sql="select distinct a.area_no value , a.area_name text,'['|| ltrim(a.area_no)||']'||a.area_name dropValue  " +
				"from  cdef_defarea a  " +
				"where a.area_attribute='1' and a.attribute_type='5' and  a.warehouse_No='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' " ;
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		
		sql=sql+" order by a.area_no ";
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//获取通道下拉框（Windows）
	@Override 
	public List<ComboxBo> getBufferAddStockCombo(String enterpriseNo,
					String warehouseNo,String str) {
		String sql="select distinct a.stock_no value, a.stock_no text, '['|| ltrim(a.stock_no)||']'||a.stock_no dropValue,a.area_no " +
				"from cdef_defcell a , cdef_defarea b " +
				"where b.area_attribute='1' and b.attribute_type='5' and a.warehouse_No=b.warehouse_No and a.enterprise_no=b.enterprise_no   and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.warehouse_No='"+warehouseNo+"' " ;		
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql+ws;
		}
		sql=sql+" order by a.area_no ";
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list; 
	
}
	/**
	 * 货位下拉
	 */
	public List<ComboxBo> getBufferAddCellCombo(
			String enterpriseNo,String strWarehouseNo ,String str,String strQuery) {
		String sql = "select distinct a.cell_no value,a.cell_no text,'['|| ltrim(a.cell_no)||']'|| a.cell_no dropValue  "
				+ "from Cdef_Defcell a , cdef_defware b ,cdef_defarea c  "
				+ "where c.area_attribute='1' and c.attribute_type='5' and a.warehouse_No=b.warehouse_No and a.enterprise_no=b.enterprise_no and a.warehouse_No=c.warehouse_No and a.enterprise_no=c.enterprise_no and c.warehouse_No=b.warehouse_No and c.enterprise_no=b.enterprise_no "
				+ "and a.warehouse_No='" + strWarehouseNo + "' "
				+ " and a.enterprise_no = '" +enterpriseNo+ "' ";
		
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}

		if(strQuery != null && !strQuery.equals("")) {
			sql = sql + " and a.cell_no like '%" + strQuery + "%' ";
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	/**
	 * 删除暂存区
	 */
	@Override
	public MsgRes deleteBuffer(String enterpriseNo,String strWarehouseNo, String str,String strwareNo,String strareaNo,String strstockNo,String strcellNo)
			throws Exception {
		//检验该库区下的货位是否存在其他表
		String sq="select a.* from oset_buffer a " +
				"where ( '" + strwareNo + "' , '" + strareaNo + "' , '" + strstockNo + "' , '" + strcellNo + "'  )  in  " +
				"(select b.ware_no,b.area_no,b.stock_no,b.cell_no " +
				"from odata_send_area_calculate b ) " +
				" or ( '" + strwareNo + "' , '" + strareaNo + "' , '" + strstockNo + "' , '" + strcellNo + "'  )  in  " +
				"(select c.ware_no,c.area_no,c.stock_no,c.cell_no " +
				"from odata_temp_send_area_calculate c ) " +
				"and a.enterprise_no='"+enterpriseNo+"' " +
				"and a.warehouse_no='"+strWarehouseNo+"' " ;
				
		sq=sq+" order by a.area_no ";
		List list=genDao.getListByNativeSql(sq);
		if(list.size()>0){
			return new MsgRes(false,"该库区下的货位已存在odata表中，不能删除！","");
		}else{
		
			String strSql4="delete oset_buffer a " +
					   "where a.warehouse_no='"+strWarehouseNo+"' " +
					   "and a.enterprise_no='" +enterpriseNo+"' ";
			if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				strSql4=strSql4+ws;
			}	  
	 	    genDao.updateBySql(strSql4);
			return new MsgRes(true,"删除成功","");
			
		}
	}
}
