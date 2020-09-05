package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;
import com.sealinkin.rodata.service.IRodata_StraightOutstockDirectService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;


@SuppressWarnings({"unchecked","rawtypes","unused"})
public class Rodata_StraightOutstockDirectImpl implements IRodata_StraightOutstockDirectService{
	private static final Logger logger = Logger.getLogger(Rodata_StraightOutstockDirectImpl.class);
		
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//获取商品明细
	public ExtListDataBo<Rodata_OutstockDirectModel> getRodata_OutstockDirect(
			String enterpriseNo,String warehouseNo,String str, Integer intStart, Integer intlimit) {
		String sql="";
//		String sql1="";
		String sql2="";
				sql="SELECT t1.*," 
					//"nvl(pk.packing_unit, decode(t1.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                    //"nvl(pk.spec, '1*' || t1.packing_qty || bda.unit) spec,"
					+" f_get_packingunit(t1.enterprise_no,t1.owner_no,t1.article_no,t1.packing_qty) packingUnit,"
					+" f_get_packingunit(t1.enterprise_no,t1.owner_no,t1.article_no,bda.qmin_operate_packing) packingUnitQmin,"
					+" f_get_packingunit(t1.enterprise_no,t1.owner_no,t1.article_no,bda.unit_packing) Unit,"
					+" f_get_spec(t1.enterprise_no,t1.owner_no,t1.article_no,t1.packing_qty) packingSpec,"
					+" f_get_spec(t1.enterprise_no,t1.owner_no,t1.article_no,bda.qmin_operate_packing) packingSpecQmin,"
					+" f_get_spec(t1.enterprise_no,t1.owner_no,t1.article_no,bda.unit_packing) spec,"
					+" BDS.REAL_SUPPLIER_NO,"
					+" BDS.SUPPLIER_NAME,"
					+" BDA.OWNER_ARTICLE_NO,"
					+" BDA.ARTICLE_NAME,"
					+" BDA.EXPIRY_DAYS "
					+" FROM (select  OOD.enterprise_no," 
					+" OOD.warehouse_no,"
					+" OOD.DIRECT_SERIAL,"
					+" OOD.OWNER_NO,"
					+" OOD.CLASS_TYPE,"
					+" OOD.STATUS,"
					+" OOD.SOURCE_NO,"
					+" OOD.S_CELL_NO,"
					+" OOD.D_CELL_NO,"
					+" OOD.ARTICLE_NO,"
					+" sum(OOD.LOCATE_QTY)/OOD.Packing_Qty as LOCATE_QTY,"
					+" OOD.Packing_Qty,"
					+" OOD.SUPPLIER_NO,"
					+" OOD.WAVE_NO,"
					+" sai.BARCODE,"
					+" f_get_fieldtext('N','QUALITY',sai.QUALITY)qualityText "
					+" from rodata_OUTSTOCK_DIRECT OOD"
					+" INNER JOIN stock_ARTICLE_INFO "
					+" sai ON sai.ARTICLE_NO = OOD.ARTICLE_NO"
					+" AND sai.ARTICLE_ID = OOD.ARTICLE_ID " 
					+" AND sai.enterprise_no = OOD.ENTERPRISE_NO "
					+" group by OOD.enterprise_no," 
					+" OOD.warehouse_no,"
					+" OOD.OWNER_NO,"
					+" OOD.DIRECT_SERIAL,"
					+" OOD.CLASS_TYPE,"
					+" OOD.STATUS,"
					+" OOD.SOURCE_NO,"
					+" OOD.S_CELL_NO,"
					+" OOD.D_CELL_NO,"
					+" OOD.ARTICLE_NO,"
					+" OOD.SUPPLIER_NO,"
					+" OOD.WAVE_NO,"
					+" sai.BARCODE,"
					+" sai.QUALITY,"
					+" OOD.Packing_Qty ) t1"
					+" INNER JOIN CDEF_DEFCELL CDC "
					+" ON t1.S_CELL_NO = CDC.CELL_NO"
					+" AND t1.warehouse_no = CDC.warehouse_no"
					+" AND t1.ENTERPRISE_NO = CDC.ENTERPRISE_NO"
					+" AND t1.warehouse_no ='"+warehouseNo+"' " 
					+" AND t1.ENTERPRISE_NO='"+enterpriseNo+"' "
					+" AND CDC.CELL_STATUS = '0'"
					+" AND CDC.CHECK_STATUS = '0'"
					+" LEFT JOIN BDEF_DEFSUPPLIER BDS "
					+" ON t1.SUPPLIER_NO = BDS.SUPPLIER_NO"
					+" AND t1.ENTERPRISE_NO = BDS.ENTERPRISE_NO"
					+" AND t1.OWNER_NO = BDS.OWNER_NO"+
					"  left join bdef_article_packing pk" +
					" on t1.article_no=pk.article_no" +
					" and t1.packing_qty=pk.packing_qty" +
					" and t1.enterprise_no=pk.enterprise_no "
					+" INNER JOIN bdef_defarticle BDA "
					+" ON t1.ARTICLE_NO = BDA.ARTICLE_NO"
					+" AND t1.ENTERPRISE_NO = BDA.enterprise_no"
					+" WHERE t1.STATUS = '10' ";
				if(str!=null && !str.equals(""))
					{
						String ws=CommUtil.covtCollectionToWhereSql(str);
						sql=sql+ws;
					} 
			    sql2=" ORDER BY t1.OWNER_NO,"
					+" BDS.SUPPLIER_NO,"
					+" t1.S_CELL_NO ";
			    sql=sql+sql2;

		   if(!sql.equals("")){
			   List<Rodata_OutstockDirectModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDirectModel.class, intStart,intlimit);
			   ExtListDataBo<Rodata_OutstockDirectModel> extListBo = new ExtListDataBo<Rodata_OutstockDirectModel>(list, 0);
//			   logger.info("leval queryRodata_OutStock_DirectModel");
			   return extListBo;
		   }else{
			   return null;
		   }
	}
    //波次下拉
	@Override
	public List<ComboxBo> getWaveNoComboList(String enterpriseNo,String warehouseNo,String str, Integer intStart,
			Integer intPagesize) {
		    String sql="select 'ALL' as value, 'ALL' as text, 'ALL' as dropValue from wms_defbase "+
					"union "+"select distinct t1.wave_no value , t1.wave_no text," +
                "t1.wave_no dropValue " +
				"from rodata_outstock_direct t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no='"+enterpriseNo+"' "+
				"and t1.status = '10'";
		    if(str != null && !str.equals(""))
			   {
				   String ws  =  CommUtil.covtCollectionToWhereSql(str);
				   sql  =  sql + ws;
			   }
		    sql = sql + " order by value desc";
			List list= (List<Object>) genDao.getListByNativeSql(sql, ComboxBo.class, intStart, intPagesize);
			return  (List<ComboxBo>)list;
	}
    //供应商下拉
	@Override
	public List<ComboxBo> getSupplierCombo(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,
			String str,String strWheresql,String strFlag, Integer intStart,
			Integer intPagesize) {
		String sql="";
		if(strFlag!=null && strFlag.equals("1")){
			sql="select distinct a.supplier_no as value," +
					"a.supplier_name as text," +
					"'['|| ltrim(a.supplier_no)||']'||a.supplier_name " +
					"dropValue from bdef_defsupplier a,rodata_outstock_direct b " +
					" where a.owner_no=b.owner_no " +
					" and a.supplier_no=b.supplier_no " +
					" and a.enterprise_no = b.enterprise_no " +
					" and b.status='10' " +
					" and b.warehouse_no='"+strWarehouseNo+"' " +
					" and b.enterprise_no='"+enterpriseNo+"' "+					
					" and b.owner_no in("+ strOwnerNo+") "+
					"and b.status = '10'";
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	//退货类型下拉
	@Override
	public List<ComboxBo> getClassTypeComboList(String enterpriseNo,
			String warehouseNo, String owenrNo, String str, String wheresql,
			String flag, Integer start, Integer pagesize) {
		 String sql="select distinct t1.class_type value , t1.class_type text," +
             "'['|| ltrim(t1.class_type)||']'||f_get_fieldtext('N', 'CLASS_TYPE', t1.class_type) dropValue " +
				"from rodata_outstock_direct t1 where 1=1 "+
				"and t1.warehouse_no ='"+warehouseNo+"' " +
				"and t1.enterprise_no='"+enterpriseNo+"' "+
				"and t1.status = '10'";
		    if(str != null && !str.equals(""))
			   {
				   String ws  =  CommUtil.covtCollectionToWhereSql(str);
				   sql  =  sql + ws;
			   }
		    sql = sql + " order by value ";
			List list= (List<Object>) genDao.getListByNativeSql(sql, ComboxBo.class, start, pagesize);
			return  (List<ComboxBo>)list;
	}
	//退货发单
	@Override
	public MsgRes send(String enterpriseNo,String warehouseNo,String strWorkerNo,String strSendWorker,String strWaveNo, String strSupplierNo,String strWorkSpaceNo,String strClassType) throws Exception 
	{
		String sql="";
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		outList.add("S");
		if(strSupplierNo!=null && !strSupplierNo.equals("")){
		sql="select distinct rod.enterprise_no,rod.warehouse_no,rod.owner_no,rod.supplier_no," +
				"rod.class_type AClass,rod.source_no,rod.wave_no " +
				"from rodata_outstock_direct rod " +
				"where supplier_no='"+strSupplierNo+"' "+
				"and rod.warehouse_no = '"+warehouseNo+"' " +
				"and rod.enterprise_no='"+enterpriseNo+"' ";
		if(strClassType!=null && !strClassType.equals("")){
			sql = sql+"and rod.class_type = '"+strClassType+"' ";
		}
        if(strWaveNo!=null && !strWaveNo.equals("")){
			sql = sql+"and rod.wave_no = '"+strWaveNo+"' ";
		}
		List<Rodata_OutstockDirectModel> rodm=genDao.getListByNativeSql(sql, Rodata_OutstockDirectModel.class, 0, 100);
		for(int i=0;i<rodm.size();i++){
			List  inList=new ArrayList();
			inList.add(rodm.get(i).getEnterpriseNo());
			inList.add(rodm.get(i).getWarehouseNo());
			inList.add(rodm.get(i).getOwnerNo());
			inList.add(rodm.get(i).getWaveNo());
			inList.add(strSupplierNo);
			inList.add(rodm.get(i).getAClass());
			inList.add(rodm.get(i).getSourceNo());
			inList.add(strWorkSpaceNo);
			inList.add(strWorkerNo);
			inList.add(strSendWorker);
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_GetTaskRooutstock");
		}
		}
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25201"),null);//发单成功！
	}
	
}
