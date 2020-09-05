package com.sealinkin.cset.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.cdef.po.Cdef_Defcell;
import com.sealinkin.comm.model.AnsArticlePackingInfoModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_AreaBackupM;
import com.sealinkin.cset.po.Cset_CellArticle;
import com.sealinkin.cset.service.ICset_CellArticleService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.protocolExchange.bdef.AnsCheckPickCellModel;
import com.sealinkin.protocolExchange.bdef.AnsPickCellInPutBarcodeModel;
import com.sealinkin.protocolExchange.bdef.ReqPickCellByArticleModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataRequestModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class Cset_CellArticleImpl implements ICset_CellArticleService{
	
	private IGenericManager genDao;
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}	

	/*
	 * 保存、修改商品储位对应关系
	 */
	public MsgRes saveCset_Cell_Article(String workerNo, String str)
			throws Exception {
		Cset_CellArticle cca=(Cset_CellArticle)JSON.parseObject(str,Cset_CellArticle.class);
		//判断货主是否绑定储位0不绑定；1绑定货位
		//当货主是绑定储位时，设置拣货位只能设定该货主所绑定的检货位
		//当货主是不绑定储位时,则需要做混货主的判断
		String sq01=" select * from bdef_defowner o " +
				"where o.enterprise_no='"+cca.getId().getEnterpriseNo()+"' " +
				"and o.owner_no='"+cca.getId().getOwnerNo()+"' ";
		List<Bdef_DefOwnerModel> list01 = genDao.getListByNativeSql(sq01,Bdef_DefOwnerModel.class);
		
		//判断该货位的混属性和混货主状态
		//根据储位获取储格
		String sq2=" select * from cdef_defcell cel " +
				"where cel.enterprise_no='"+cca.getId().getEnterpriseNo()+"' " +
				"and cel.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
				"and cel.cell_no='"+cca.getCellNo()+"' ";
		List<Cdef_Defcell> list2 = genDao.getListByNativeSql(sq2,Cdef_Defcell.class);
		
        if(list01.get(0).getFixedcellFlag().equals("1")){//货主是绑定储位,拣货位只能设定该货主所绑定的检货位
			String sq5 = "select l.cell_no from cset_owner_cell l " +
					"where l.enterprise_no='"+cca.getId().getEnterpriseNo()+"' " +
					"and l.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
					"and l.cell_no='"+cca.getCellNo()+"' " +
					"and l.owner_no='"+cca.getId().getOwnerNo()+"' ";
			List<Cdef_Defcell> list5 = genDao.getListByNativeSql(sq5,Cdef_Defcell.class);
			if(list5.size()==0){
	        	return new MsgRes(false, "该货位不是该货主绑定的货位，不能新增", "") ;
			}
        	
		}else if(list01.get(0).getFixedcellFlag().equals("0")){//货主是不绑定储位,需要做混货主的判断
			if(list2.get(0).getMixOwner().equals("0")){//不可混货主
				//查询该货位的拣货位是否有其他货主
		    	String sql4="select * from Cset_Cell_Article a " +
						   "where a.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
						   "and a.enterprise_no='"+cca.getId().getEnterpriseNo()+"' "+
						   "and a.cell_no='"+cca.getCellNo()+"' "+
				           "and a.owner_no<>'"+cca.getId().getOwnerNo()+"' ";
				List<Cset_CellArticleModel> list4 = genDao.getListByNativeSql(sql4,Cset_CellArticleModel.class);
		        if(list4.size()>0){
		        	return new MsgRes(false, "该货位是不可混货主货位，不能新增", "") ;
		        }
			}
			
		}
		
	    if(list2.get(0).getMixFlag()==0 || list2.get(0).getMixFlag()==1){//不可混商品
	    	//查询该货位的拣货位是否有其他商品
	    	String sql3="select * from Cset_Cell_Article a " +
					   "where a.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
					   "and a.enterprise_no='"+cca.getId().getEnterpriseNo()+"' "+
					   "and a.cell_no='"+cca.getCellNo()+"' "+
			           "and a.article_no<>'"+cca.getId().getArticleNo()+"' ";
			List<Cset_CellArticleModel> list3 = genDao.getListByNativeSql(sql3,Cset_CellArticleModel.class);
	        if(list3.size()>0){
	        	return new MsgRes(false, "该货位是不可混商品货位，不能新增", "") ;
	        }
	    }
	    
	    //当保拣线为空时，取默认保拣线，若未找到默认保拣线，系统给与拦截
	    if(cca.getLineId()==null || cca.getLineId().equals("")){
	    	String sqLIne = "select M.LINE_ID from Cset_Area_Backup_M m " +
	    			"where m.default_flag='1' "+
	            	"and m.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
			        "and m.enterprise_no='"+cca.getId().getEnterpriseNo()+"' " +
			        "and m.s_area_no='"+cca.getAreaNo()+"' " +
			        "and m.s_ware_no='"+cca.getWareNo()+"' ";
	    	List<Cset_CellArticleModel> listLINE = this.genDao.getListByNativeSql(sqLIne,Cset_CellArticleModel.class);
	    	if(listLINE.size()==0){
	    		return new MsgRes(false, "没有默认保拣线，不能新增", "") ;
	    	}else{
	    		cca.setLineId(listLINE.get(0).getLineId());
	    	}
	    }
	    
		cca.setStockX(list2.get(0).getStockX());
		//判断修改还是新增
		String sql="select * from Cset_Cell_Article a " +
				   "where a.warehouse_no='"+cca.getId().getWarehouseNo()+"' " +
				   "and a.enterprise_no='"+cca.getId().getEnterpriseNo()+"' "+
		           "and a.owner_no='"+cca.getId().getOwnerNo()+"' " +
		           "and a.article_no='"+cca.getId().getArticleNo()+"' " +
		           "and a.pick_type='"+cca.getId().getPickType()+"'";
		
		List<Cset_CellArticleModel> list = genDao.getListByNativeSql(sql,Cset_CellArticleModel.class);
		if(list.size()==0){
			cca.setUpdtDate(null);
			cca.setUpdtName(null);
		}else{
			cca.setUpdtDate(list.get(0).getRgstDate());
			cca.setUpdtName(list.get(0).getRgstName());
		}
		
		String logSql="insert into Cset_Cell_Article_Log(enterprise_no, Warehouse_No, " +
				"Owner_No, Ware_No, Area_No, Stock_No, a_Stock_No, " +
				"Stock_x, Cell_No, Article_No, Packing_Qty, Line_Id, " +
				"Max_Qty_a, Alert_Qty_a, Supp_Qty_a, Max_Qty_Na, " +
				"Alert_Qty_Na, Supp_Qty_Na, Keep_Cells, Pick_Type," +
				" Keep_Cells_a, Rgst_Name, Rgst_Date, Updt_Name, Updt_Date)" +
				"values('"+cca.getId().getEnterpriseNo()+"','"+
				cca.getId().getWarehouseNo()+"','"+
				cca.getId().getOwnerNo()+"','"+cca.getWareNo()+"','"+cca.getAreaNo()+"'," +
				"'"+cca.getStockNo()+"','"+cca.getAStockNo()+"','"+
				cca.getStockX()+"','"+cca.getCellNo()+"','"+cca.getId().getArticleNo()+"'," +
				"'"+cca.getPackingQty()+"','"+cca.getLineId()+"','"+cca.getMaxQtyA()
				+"','"+cca.getAlertQtyA()+"','"+cca.getSuppQtyA()+"'," +
				"'"+cca.getMaxQtyNa()+"','"+cca.getAlertQtyNa()+"','"+cca.getSuppQtyNa()
				+"','"+cca.getKeepCells()+"','"+cca.getId().getPickType()+"'," +
				"'"+cca.getKeepCellsA()+"','"+cca.getRgstName()
				+"',sysdate,'"+cca.getRgstName()+"',sysdate)";

		genDao.saveOrUpdateObj(cca);
		genDao.updateBySql(logSql);
		return new MsgRes(true, "保存成功！", "");
	}

    /*
     * 获得已有商品储位对应关系信息(货位维护的拣货位信息查询有用到该方法)
     * zhouhuan
     */
	@Override
	public ExtListDataBo<Cset_CellArticleModel> getCset_Cell_ArticleList(
			String enterpriseNo,String warehouseNo, String ownerNo, 
			String strQuery, PageBo pageBo,Integer requestFlag) throws Exception {
		String sql=" select"
			+" C.*,a.barcode,cabm.line_name,"
			+" a.owner_article_no,a.article_name," +
			" f_get_fieldtext('N','O_TYPE',c.pick_type) pickTypeText, "
			+" d.owner_name,CDW.WARE_NAME,CDA.AREA_NAME, " 
			+" ceil(C.MAX_QTY_A/C.PACKING_QTY) as maxQtyBox," 
			+" ceil(C.Alert_Qty_a/C.Packing_Qty) as alertQtyBox," 
			+" ceil(C.Supp_Qty_a/C.Packing_Qty) as suppQtyBox "
			+" FROM"
			+" Cset_Cell_Article C"
			+" LEFT JOIN"
			+" bdef_defarticle A "
			+" ON C.OWNER_NO = A.OWNER_NO"
			+" AND C.ARTICLE_NO = A.ARTICLE_NO"
			+" AND C.ENTERPRISE_NO = A.ENTERPRISE_NO"
			+" INNER JOIN"
	        +" bdef_defowner d "
	        +" ON C.OWNER_NO = D.OWNER_NO "
	        +" AND C.ENTERPRISE_NO = D.ENTERPRISE_NO"
			+" INNER JOIN"
			+" BDEF_DEFOWNER O "
			+" ON C.OWNER_NO = O.OWNER_NO "
			+" AND C.ENTERPRISE_NO = O.ENTERPRISE_NO"
			+" INNER JOIN"
			+" CDEF_DEFWARE CDW "
			+" ON C.WAREHOUSE_NO = CDW.WAREHOUSE_NO "
			+" and C.ENTERPRISE_NO = CDW.ENTERPRISE_NO"
			+" and C.WARE_NO = CDW.WARE_NO "
			+" INNER JOIN"
			+" CDEF_DEFAREA CDA "
			+" ON C.WAREHOUSE_NO = CDA.WAREHOUSE_NO "
			+" and C.WARE_NO = CDA.WARE_NO "
			+" and C.AREA_NO = CDA.AREA_NO "
			+" and C.ENTERPRISE_NO = CDA.ENTERPRISE_NO"
			+" INNER JOIN"
			+" cset_area_backup_m cabm "
			+" ON C.WAREHOUSE_NO = cabm.WAREHOUSE_NO "
			+" and C.enterprise_no = cabm.enterprise_no"
			+" and C.line_id = cabm.line_id "
			+" where"
			+" c.warehouse_no = '"+warehouseNo+"'"
			+" and c.enterprise_no='"+enterpriseNo+"' "
			+" and c.owner_no in ("+ownerNo+" )";
			
			String totallSql="select count(1) "
				+" FROM Cset_Cell_Article C"
				+" LEFT JOIN bdef_defarticle A "
				+" ON C.OWNER_NO = A.OWNER_NO"
				+" AND C.ARTICLE_NO = A.ARTICLE_NO"
				+" AND C.ENTERPRISE_NO = A.ENTERPRISE_NO"
				+" INNER JOIN BDEF_DEFOWNER O "
				+" ON C.OWNER_NO = O.OWNER_NO"
				+" AND C.ENTERPRISE_NO = O.ENTERPRISE_NO"
				+" INNER JOIN"
				+" CDEF_DEFWARE CDW "
				+" ON C.WAREHOUSE_NO = CDW.WAREHOUSE_NO "
				+" and C.ENTERPRISE_NO = CDW.ENTERPRISE_NO"
				+" and C.WARE_NO = CDW.WARE_NO "
				+" INNER JOIN"
				+" CDEF_DEFAREA CDA "
				+" ON C.WAREHOUSE_NO = CDA.WAREHOUSE_NO "
				+" and C.WARE_NO = CDA.WARE_NO "
				+" and C.AREA_NO = CDA.AREA_NO "
				+" and C.ENTERPRISE_NO = CDA.ENTERPRISE_NO"
				+" where c.warehouse_no = '"+warehouseNo+"'"
				+" and c.enterprise_no='"+enterpriseNo+"' "
				+" and c.owner_no in ("+ownerNo+")";
			  if(strQuery!=null && !strQuery.equals(""))
				{
					String ws=CommUtil.covtCollectionToWhereSql(strQuery);
					sql=sql+ws;
					totallSql=totallSql+ws;
				}
			sql+=" order by c.warehouse_no, c.owner_no ";
			List<Cset_CellArticleModel> list =null; 
			Integer count = 0; 
			ExtListDataBo<Cset_CellArticleModel> extListBo=null;
			if(requestFlag==1){
				list = genDao.getListByNativeSql(sql, Cset_CellArticleModel.class,pageBo.getStart(), pageBo.getPagesize());
				count = genDao.getCountByNativeSql(totallSql);
			}else if(requestFlag==2){
				list = genDao.getListByNativeSql(sql,Cset_CellArticleModel.class);
				count = list.size();
			}
			extListBo= new ExtListDataBo<Cset_CellArticleModel>(list, count);
	        return extListBo;
	}
	// 获取导出的商品储位对应关系
	@Override
	public List<Cset_CellArticleModel> getCset_Cell_ArticleList2(
			String enterpriseNo, String warehouseNo, String ownerNo,
			String strQuery, Integer requestFlag) throws Exception {
		String sql=" select c.warehouse_no,"
					+" C.owner_no,a.owner_article_no,c.article_no," 
					+" a.article_name,c.ware_no,c.cell_no,c.stock_no," 
					+" c.area_no,c.line_id,c.PACKING_QTY,"
					+" ceil(C.MAX_QTY_A/C.PACKING_QTY) as maxQtyBox," 
					+" ceil(C.Alert_Qty_a/C.Packing_Qty) as alertQtyBox," 
					+" ceil(C.Supp_Qty_a/C.Packing_Qty) as suppQtyBox, " 
					+" c.keep_cells,"
					+"'[' || c.pick_type || ']' || f_get_fieldtext('N','O_TYPE',c.pick_type) pickTypeText "
					+" FROM"
					+" Cset_Cell_Article C"
					+" LEFT JOIN"
					+" bdef_defarticle A "
					+" ON C.OWNER_NO = A.OWNER_NO"
					+" AND C.ARTICLE_NO = A.ARTICLE_NO"
					+" AND C.ENTERPRISE_NO = A.ENTERPRISE_NO"
					+" INNER JOIN"
			        +" bdef_defowner d "
			        +" ON C.OWNER_NO = D.OWNER_NO "
			        +" AND C.ENTERPRISE_NO = D.ENTERPRISE_NO"
					+" INNER JOIN"
					+" BDEF_DEFOWNER O "
					+" ON C.OWNER_NO = O.OWNER_NO "
					+" AND C.ENTERPRISE_NO = O.ENTERPRISE_NO"
					+" INNER JOIN"
					+" CDEF_DEFWARE CDW "
					+" ON C.WAREHOUSE_NO = CDW.WAREHOUSE_NO "
					+" and C.ENTERPRISE_NO = CDW.ENTERPRISE_NO"
					+" and C.WARE_NO = CDW.WARE_NO "
					+" INNER JOIN"
					+" CDEF_DEFAREA CDA "
					+" ON C.WAREHOUSE_NO = CDA.WAREHOUSE_NO "
					+" and C.WARE_NO = CDA.WARE_NO "
					+" and C.AREA_NO = CDA.AREA_NO "
					+" and C.ENTERPRISE_NO = CDA.ENTERPRISE_NO"
					+" INNER JOIN"
					+" cset_area_backup_m cabm "
					+" ON C.WAREHOUSE_NO = cabm.WAREHOUSE_NO "
					+" and C.enterprise_no = cabm.enterprise_no"
					+" and C.line_id = cabm.line_id "
					+" where"
					+" c.warehouse_no = '"+warehouseNo+"'"
					+" and c.enterprise_no='"+enterpriseNo+"' "
					+" and c.owner_no in ("+ownerNo+" )";
					
		if(strQuery!=null && !strQuery.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(strQuery);
				sql=sql+ws;
			}
		sql+=" order by c.warehouse_no, c.owner_no ";
		List list=genDao.getListByNativeSql(sql);
		return list;
	}
	/*
	 * 删除商品储位对应关系
	 */
	@Override
	public void delete(String enterpriseNo,String strwarehouseNo, String strownerNo, 
			String strarticleNo,String strpickType)throws Exception {
		String logSql="insert into Cset_Cell_Article_Log " +
				"select * from cset_cell_article cca " +
				"where cca.warehouse_no='"+strwarehouseNo+"'" +
				"and cca.enterprise_no='" +enterpriseNo+"'"+
				"and cca.owner_no='"+strownerNo+"' " +
				"and cca.article_no='"+strarticleNo+"' " +
				"and cca.pick_type='"+strpickType+"' ";
		genDao.updateBySql(logSql);
		
		
		 String sql="delete from Cset_Cell_Article where warehouse_no='" 
			+ strwarehouseNo + "' and owner_no='" + strownerNo + "' and article_no= '" 
		 + strarticleNo + "' and pick_type='" + strpickType + "'";
         genDao.updateBySql(sql);
	}


	/*
	 * 根据业主编号获得商品条码和编码
	 * zhouhuan
	 */
	public List<Cset_CellArticleModel> getCset_BarcodeAndArticleNoCombo(
			String enterpriseNo,
			String ownerNo,
			String wheresql) throws Exception{
	    String sql="select c.barcode, c.article_no, c.article_name " +
	    		"from bdef_defarticle c " +
	    		"where c.owner_article_no='"+ wheresql+"' " +
	    		"and c.owner_no in("+ownerNo+") " +
	    		"and c.enterprise_no='"+enterpriseNo+"' "	;
		List<Cset_CellArticleModel> list = genDao.getListByNativeSql(sql, Cset_CellArticleModel.class);
   
	    return list;
	}

	/**
	 * 校验新增商品储位对应关系是否重复
	 */
	public ExtListDataBo<Cset_CellArticleModel> existsCsetCellArticle(
			String enterpriseNo,
			String warehouseNo,String ownerNo,
			String strQuery) throws Exception{
		String sql="select a.* from Cset_Cell_Article a where 1=1 " +
				"and a.warehouse_no='"+warehouseNo+"' " +
				"and a.enterprise_no='"+enterpriseNo+"' "+
				"and a.owner_no in("+ownerNo+")";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}
		List<Cset_CellArticleModel> list = genDao.getListByNativeSql(sql, Cset_CellArticleModel.class,0, 1000);
		ExtListDataBo<Cset_CellArticleModel> extListBo= new ExtListDataBo<Cset_CellArticleModel>(list, 0);
        return extListBo;
	}

	/**
	 * 校验对应的拣货类型下是否有储区
	 */
	public ExtListDataBo<ComboxBo> existsAreaList(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner, String queryStr)throws Exception {
		String sql="select a.ware_no value,b.ware_Name text,'['|| ltrim(a.ware_no)||']'||b.ware_Name dropValue  " +
			" from cdef_defarea a " +
			" inner join cdef_defware b " +
			" on a.warehouse_no=b.warehouse_no " +
			" and a.enterprise_no = b.enterprise_no " +
			" and a.ware_no=b.ware_no" +
			" and a.enterprise_no='"+enterpriseNo+"' "+
			" and a.warehouse_No='"+warehouseNo+"' " +
			" and a.AREA_USETYPE='1' " +
			" and a.AREA_ATTRIBUTE='0' " +
			" and a.AREA_PICK='1'";
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
		}
		List<ComboxBo> list = genDao.getListByNativeSql(sql, ComboxBo.class,0, 1000);
		ExtListDataBo<ComboxBo> extListBo= new ExtListDataBo<ComboxBo>(list, 0);
        return extListBo;
	}
	
	/**
	 * 获取线路(保拣线)下拉
	 */
	@Override
	public List<ComboxBo> queryLineCombo(String enterpriseNo,String strWarehouseNo,String strQuery)
			throws Exception {
		String strSql="select cabm.line_id as value,cabm.line_name as text," +
				"'['|| ltrim(cabm.line_id)||']'||cabm.line_name dropValue from cset_area_backup_m cabm," +
				" cset_area_backup_d cabd where cabm.warehouse_no=cabd.warehouse_no " +
				" and cabm.enterprise_no=cabd.enterprise_no " +
				" and cabd.line_id=cabm.line_id" +
				" and cabd.enterprise_no='"+enterpriseNo+"' "+
				" %s1 order by cabm.line_id desc";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			strSql=strSql.replace("%s1", ws);
		}
		List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class,0, 1000);
        return list;
	}
	
	/**
	 * 获取stock_x
	 */
	@Override
	public List<String> queryStoctX(String enterpriseNo,String strWarehouseNo, String strOwnerNo,
			String strCellNo) throws Exception {
		String strSql="select stock_x from cdef_defcell cd " +
				"where cd.warehouse_no='"+strWarehouseNo+"'" +
				" and cd.enterprise_no='"+enterpriseNo+"'"+
				" and cd.cell_no='"+strCellNo+"'";
		List<String> list=genDao.getListByNativeSql(strSql);
		return list;
	}
	
	/**
	 * 获取货主商品编码下拉
	 */
	@Override
	public List<ComboxBo> queryOwnerArticleNoList(
			String enterpriseNo,
			String strOwnerNo,
			String strWheresql) throws Exception {
		String strSql="select distinct t1.owner_article_no value,t1.article_name text," +
				"'['|| ltrim(t1.article_no)||']'||t1.article_name dropValue " +
				"from bdef_defarticle t1 " +
				"where 1=1 ";
		
		if(strWheresql!=null && !strWheresql.equals(""))
		{
			strSql+=" and (t1.article_no like '%"+strWheresql+"%' " +
					" or t1.article_name like '%"+strWheresql+"%' " +
							"or t1.barcode like '%"+strWheresql+"%'" +
							"or t1.owner_article_no like '%"+strWheresql+"%')";
		}
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no = '"+strOwnerNo+"'";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			strSql=strSql+" and t1.enterprise_no = '"+enterpriseNo+"'";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes PickCellBarcodeInput(String strRecvData)
			throws Exception {
		MsgRes msg=new MsgRes();
		CommSingleDataRequestModel reqMod=JSON.parseObject(strRecvData, CommSingleDataRequestModel.class);
		//根据条码找商品
		msg =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getReqObj(),reqMod.getEnterpriseNo());
		if(!msg.getIsSucc())
		{
			return msg;
		}
		String strArticleNo=msg.getObj().toString();
		String strSql="select a.*,b.article_name," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingUnitQmin," +
				"f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) Unit," +
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec," +
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.qmin_operate_packing) packingSpecQmin," +
				"f_get_spec(a.enterprise_no,a.owner_no,a.article_no,b.unit_packing) spec " +
				"from cset_cell_article a,bdef_defarticle b,bdef_article_packing c  " +
				"where a.article_no=b.article_no " +
				"and a.article_no=c.article_no " +
				"and a.packing_qty=c.packing_qty " +
				"and a.article_no in(" +strArticleNo+") "+
				"and a.warehouse_no='"+reqMod.getWarehouseNo()+"' " +
				"and a.enterprise_no='"+reqMod.getEnterpriseNo()+"' ";
		List<AnsPickCellInPutBarcodeModel> list=genDao.getListByNativeSql(strSql, AnsPickCellInPutBarcodeModel.class);
		if(list.size()>0)
		{		
			msg.setIsSucc(true);
			msg.setObj(JSON.toJSONString(list));
		}else
		{
			msg.setIsSucc(false);
			msg.setMsg("该条码并未设置储位对照关系！");
		}		
		return msg;
	}
	@Override
	public MsgRes UpdatePickCell(String strRecvData) throws Exception {
		AnsPickCellInPutBarcodeModel reqMod=JSON.parseObject(strRecvData, AnsPickCellInPutBarcodeModel.class);		
		String strSql="update " +
				" cset_cell_article a " +
				" set a.MAX_QTY_A="+reqMod.getMaxQtyA()+"," +
				" a.ALERT_QTY_A="+reqMod.getAlertQtyA()+"," +
				" a.SUPP_QTY_A="+reqMod.getSuppQtyA()+","+
				" a.KEEP_CELLS_A="+reqMod.getKeepCellsA()+"," +
				" a.MAX_QTY_NA="+reqMod.getMaxQtyNa()+"," +
				" a.ALERT_QTY_NA="+reqMod.getAlertQtyNa()+"," +
				" a.SUPP_QTY_NA="+reqMod.getSuppQtyNa()+"," +
				" a.KEEP_CELLS="+reqMod.getKeepCells()+"," +
				" a.UPDT_NAME='"+reqMod.getUserId()+"'," +
				" a.UPDT_DATE=sysdate "+				
				" where a.article_no='" +reqMod.getArticleNo()+"' "+
				" and a.PICK_TYPE='"+reqMod.getPickType()+"' " +
				" and a.WAREHOUSE_NO='"+reqMod.getWarehouseNo()+"' " +
				" and a.ENTERPRISE_NO='"+reqMod.getEnterpriseNo()+"' " +
				" and a.OWNER_NO='"+reqMod.getOwnerNo()+"' ";
		
		genDao.updateBySql(strSql);
		return new MsgRes(true,"","保存成功！");
	}
	
	//获取拣货类型
	@Override
	public List<ComboxBo> getOtypeo(String enterpriseNo, String warehouseNo)
			throws Exception {
		String sql = "select distinct f_get_fieldtext('N','O_TYPE',a.o_type)text,a.o_type value," +
				"'['|| ltrim(a.o_type)||']'||f_get_fieldtext('N','O_TYPE',a.o_type)  dropValue  "
				+ "from cdef_defarea a "
				//+ "where a.area_pick='1' "
				+ "where a.warehouse_No='" + warehouseNo + "' "
				+" and a.enterprise_no = '"+enterpriseNo+"'";
		
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	/**
	 * 拣货位采集-获取商品包装，单位，规格，委托业主，商品名称
	 * huangb 20160525
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes getArticlePackingInfo(String strRecvData) throws Exception {
		ReqPickCellByArticleModel reqMod = (ReqPickCellByArticleModel)JSONObject.toBean(
				JSONObject.fromObject(strRecvData),ReqPickCellByArticleModel.class);
		MsgRes msgRes=new MsgRes();
		
		//获取商品编号
		msgRes =getArticleForBarcodeImpl.checkBarcode(reqMod.getWarehouseNo(),reqMod.getScanNo(),reqMod.getEnterpriseNo());
		if(!msgRes.getIsSucc())
		{
			return msgRes;
		}
		String strArticleNo=msgRes.getObj().toString();
		
		//获取商品委托业主包装信息
		List<AnsArticlePackingInfoModel> list = 
				getArticleForBarcodeImpl.getArticlePackingInfoByScanNo(reqMod.getEnterpriseNo(), strArticleNo);
		if(list.size() <= 0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("商品条码"+ reqMod.getScanNo() +"对应的商品信息不存在！");
			return msgRes;
		}
		
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));
		return msgRes;
	}
    
	/**
	 * 拣货位采集-获取商品拣货位信息
	 * huangb 20160525
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes getArticleCellInfo(String strRecvData) throws Exception {
		ReqPickCellByArticleModel reqMod = (ReqPickCellByArticleModel)JSONObject.toBean(
				JSONObject.fromObject(strRecvData),ReqPickCellByArticleModel.class);
		MsgRes msgRes=new MsgRes();
		//获取商品拣货位信息
		String Sql = "";
		
		Sql = " select cca.cell_no as cellNo, " + 
			  "        ceil(cca.max_qty_a/cca.PACKING_QTY) as maxQtyA, " + 
	          "        ceil(cca.alert_qty_a/cca.PACKING_QTY) as alertQtyA, " + 
	          "        ceil(cca.supp_qty_a/cca.PACKING_QTY) as suppQtyA, " +
              "        cca.keep_cells_a as keepCellsA, " + 
              "        cca.line_id as lineId " + 
              "  from cset_cell_article cca " + 
              "  where cca.enterprise_no = '"+ reqMod.getEnterpriseNo() +"' " + 
              "  and cca.warehouse_no = '"+ reqMod.getWarehouseNo() +"' " + 
              "  and cca.owner_no = '"+ reqMod.getOwnerNo() +"' " + 
              "  and cca.article_no = '"+ reqMod.getArticleNo() +"' " + 
              "  and cca.pick_type = '"+ reqMod.getPickType() +"' ";
		List<ReqPickCellByArticleModel> list = genDao.getListByNativeSql(Sql, ReqPickCellByArticleModel.class);
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));
		return msgRes;
	}
	
	/**
	 * 拣货位采集-判断商品储位的合法性
	 * huangb 20160525
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes getCheckArticleCell(String strRecvData) throws Exception {
		ReqPickCellByArticleModel reqMod = (ReqPickCellByArticleModel)JSONObject.toBean(
				JSONObject.fromObject(strRecvData),ReqPickCellByArticleModel.class);
		MsgRes msgRes=new MsgRes();
        
		//判断储位的合法性
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");

		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo --企业号,
		inList.add(reqMod.getWarehouseNo());//strwarehouse_no --仓别
		inList.add(reqMod.getOwnerNo());//strOwnerNo --货主编码
		inList.add(reqMod.getLineId());//nLineId --保拣线
		inList.add(reqMod.getCellNo());//strCellNo --储位编号
		inList.add(reqMod.getPickType());//strPickType --拣货位类型
		inList.add(reqMod.getArticleNo());//strArticleNo --商品编号
		
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_CDEF.p_Check_ArticleCell");
	
		if(resultList.get(1).toString().indexOf("N|")!=-1){
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(1).toString());
			return msgRes; 
		} 
		
		//获取储位对应的拣货线
		String Sql = "";
	    Sql = " select cabm.line_id as lineNo,cabm.LINE_NAME as lineName " + 
              "   from cset_area_backup_m cabm, cdef_defcell cdc " + 
              "  where cabm.enterprise_no = cdc.enterprise_no " + 
	    	  "       and cabm.warehouse_no = cdc.warehouse_no " + 
	    	  "       and cabm.s_ware_no = cdc.ware_no " + 
	    	  "       and cabm.s_area_no = cdc.area_no " + 
	    	  "       and cdc.enterprise_no = '"+ reqMod.getEnterpriseNo() +"' " + 
	    	  "       and cdc.warehouse_no = '"+ reqMod.getWarehouseNo() +"' " +  
	    	  "       and cdc.cell_no = '"+ reqMod.getCellNo() +"' ";
		List<AnsCheckPickCellModel> list = genDao.getListByNativeSql(Sql, AnsCheckPickCellModel.class);
		if(list.size() <= 0){
			msgRes.setIsSucc(false);
			msgRes.setMsg("储位"+ reqMod.getCellNo() +"不存在保拣线！");
			return msgRes;
		}
		
		for(AnsCheckPickCellModel m:list){
			m.setCellArticleNo(resultList.get(0).toString());
			if(resultList.get(1).toString().indexOf("X|")!=-1){
				m.setDeleteFlag("1");
				msgRes.setMsg(resultList.get(1).toString());
			}
			else{
				m.setDeleteFlag("0");
			}
		}
  
		msgRes.setIsSucc(true);
		msgRes.setObj(JSONArray.fromObject(list));
		return msgRes;
	}
	
	/**
	 * 拣货位采集-删除当前储位与原有商品的对应关系
	 * huangb 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscDeleteArticleCell(String strRecvData) throws Exception{
		ReqPickCellByArticleModel reqMod=JSON.parseObject(strRecvData, ReqPickCellByArticleModel.class);
		MsgRes msgRes=new MsgRes();
        
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo --企业号,
		inList.add(reqMod.getWarehouseNo());//strwarehouse_no --仓别
		inList.add(reqMod.getOwnerNo());//strOwnerNo --货主编码
		inList.add(reqMod.getPickType());//strPickType --拣货位类型
		inList.add(reqMod.getArticleNo());//strCellArticleNo --商品编号
		
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_CDEF.p_delete_ArticleCell");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(0).toString());
			return msgRes; 
		} 
		
		msgRes.setIsSucc(true); 
		return msgRes; 
	}
	
	/**
	 * 拣货位采集-保存当前储位与商品的对应关系
	 * huangb 20160526
	 * @param strRecvData
	 * @throws Exception
	 */
	@Override
	public MsgRes tscSaveArticleCell(String strRecvData) throws Exception{
		ReqPickCellByArticleModel reqMod=JSON.parseObject(strRecvData, ReqPickCellByArticleModel.class);
		MsgRes msgRes=new MsgRes();
		
		//先检查商品的合法性
		msgRes = getCheckArticleCell(strRecvData);
		if(!msgRes.getIsSucc()){
			return msgRes;
		}
		//如果储位存在商品，且商品没有库存 则 返回界面，扫描储位判断是否删除
		if(msgRes.getMsg() != null)
		{
			if(msgRes.getMsg().toString().indexOf("X|")!=-1){
				msgRes.setObj(msgRes.getMsg().toString() + "请输入储位时按回车，删除之前的对应关系");
				msgRes.setIsSucc(false);
				return msgRes;
			}
		}
		//保存当前储位与商品的对应关系
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");

		inList.add(reqMod.getEnterpriseNo());//strEnterPriseNo --企业号,
		inList.add(reqMod.getWarehouseNo());//strwarehouse_no --仓别
		inList.add(reqMod.getOwnerNo());//strOwnerNo --货主编码
		inList.add(reqMod.getPickType());//strPickType --拣货位类型
		inList.add(reqMod.getCellNo());//strCellNo --储位
		inList.add(Double.parseDouble(reqMod.getPackingQty()));//nPackingQty --包装数量
		inList.add(Double.parseDouble(reqMod.getLineId()));//nLineId --拣货线
		inList.add(reqMod.getMaxQtyA());//nMaxQtyA --最大存储量（箱）
		inList.add(reqMod.getAlertQtyA());//nAlertQtyA --补货警示量（箱）
		inList.add(reqMod.getSuppQtyA());//nSuppQtyA --循环补货触发量（箱）
		inList.add(reqMod.getKeepCellsA());//nKeepCells --可用货位数               
        inList.add(reqMod.getArticleNo());//strArticleNo --商品编号
        inList.add(reqMod.getUserId());//strUser_Id --操作人员
		
		System.out.println(inList);
		
		resultList = genDao.execProc(inList, outList, "PKOBJ_CDEF.p_save_ArticleCell");
	
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			msgRes.setIsSucc(false); 
			msgRes.setMsg(resultList.get(0).toString());
			return msgRes; 
		} 
		
		msgRes.setIsSucc(true); 
		return msgRes; 
	}


}
