package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_OutstockDirectModel;
import com.sealinkin.rodata.service.IRodata_OutstockDirectService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;


@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_OutstockDirectImpl implements IRodata_OutstockDirectService{
    private static final Logger logger = Logger.getLogger(Rodata_OutstockDirectImpl.class);
		
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/*
	 * 
	 * zhouhuan
	 */
	public ExtListDataBo<Rodata_OutstockDirectModel> getRodata_OutstockDirect(
			String str, Integer intStart, Integer intlimit) {
		String sql="";
		String sql1="";
		String sql2="";
				sql1="SELECT OOD.*," +
					//"nvl(pk.packing_unit, decode(ood.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                    //"nvl(pk.spec, '1*' || ood.packing_qty || bda.unit) spec,"
                    " f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"
					+" f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingUnitQmin,"
					+" f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) Unit,"
					+" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"
					+" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingSpecQmin,"
					+" f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) spec,"
					+" BDS.REAL_SUPPLIER_NO,"
					+" BDS.SUPPLIER_NAME,"
					+" BDA.OWNER_ARTICLE_NO,"
					+" BDA.ARTICLE_NAME,"
					+" BDA.ARTICLE_ENAME,"
					+" BDA.ARTICLE_IDENTIFIER,"
					+" BDA.ARTICLE_ONAME,"
					//+" BDA.SPEC,"
					+" BDA.QMIN_OPERATE_PACKING,"
					+" BDA.RULE_FLAG,"
					+" BDA.ABC,"
					+" BDA.QC_FLAG,"
					+" BDA.MEASURE_MODE,"
					+" BDA.TEMPERATURE_FLAG,"
					+" BDA.VIRTUAL_FLAG,"
					+" BDA.SCAN_FLAG,"
					//+" BDA.UNIT,"
					+" BDA.DOUBLE_CHECK,"
					+" BDA.EXPIRY_DAYS "
					+" FROM (select OOD.enterprise_no," 
					+" OOD.warehouse_no,"
					+" OOD.DIRECT_SERIAL,"
					+" OOD.OWNER_NO,"
					//+" OOD.CLASS ACLASS,"
					+" OOD.STATUS,"
					+" OOD.SOURCE_NO,"
					+" OOD.S_CELL_NO,"
					+" OOD.D_CELL_NO,"
					+" OOD.ARTICLE_NO,"
					+" OOD.OPERATE_DATE,"
					+" OOD.OPERATE_TYPE,"
					+" sum(OOD.LOCATE_QTY) as LOCATE_QTY,"
					+" OOD.Packing_Qty,"
					+" OOD.SUPPLIER_NO,"
					+" sai.BARCODE,"
					+" sai.LOT_NO,"
					+" sai.PRODUCE_DATE,"
					+" sai.EXPIRE_DATE,"
					+" f_get_fieldtext('N','QUALITY',sai.QUALITY)qualityText "
					+" from rodata_OUTSTOCK_DIRECT OOD"
					+" INNER JOIN stock_ARTICLE_INFO sai"
					+" ON sai.ARTICLE_NO = OOD.ARTICLE_NO "
					+" AND sai.enterprise_no=ood.enterprise_no"
					+" AND sai.ARTICLE_ID = OOD.ARTICLE_ID"
					+" group by OOD.enterprise_no," 
					+" OOD.warehouse_no,"
					+" OOD.OWNER_NO,"
					+" OOD.DIRECT_SERIAL,"
				//	+" OOD.CLASS,"
					+" OOD.STATUS,"
					+" OOD.SOURCE_NO,"
					+" OOD.S_CELL_NO,"
					+" OOD.D_CELL_NO,"
					+" OOD.ARTICLE_NO,"
					+" OOD.OPERATE_DATE,"
					+" OOD.OPERATE_TYPE,"
					+" OOD.SUPPLIER_NO,"
					+" sai.BARCODE,"
					+" sai.LOT_NO,"
					+" sai.PRODUCE_DATE,"
					+" sai.EXPIRE_DATE,"
					+" sai.QUALITY,"
					+" OOD.Packing_Qty ) OOD"
					+" INNER JOIN CDEF_DEFCELL CDC "
					+" ON OOD.S_CELL_NO = CDC.CELL_NO"
					+" AND OOD.enterprise_no=CDC.enterprise_no"
					+" AND OOD.warehouse_no = CDC.warehouse_no"				
					+" AND CDC.CELL_STATUS = '0'"
					+" AND CDC.CHECK_STATUS = '0'"
					+" LEFT JOIN BDEF_DEFSUPPLIER BDS "
					+" ON OOD.SUPPLIER_NO = BDS.SUPPLIER_NO"
					+" AND OOD.ENTERPRISE_NO=BDS.ENTERPRISE_NO"
					+" AND OOD.OWNER_NO = BDS.OWNER_NO" +
					"  left join bdef_article_packing pk" +
					" on ood.article_no=pk.article_no" +
					" and ood.packing_qty=pk.packing_qty" +
					" and ood.enterprise_no=pk.enterprise_no "
					+" INNER JOIN bdef_defarticle BDA "
					+" ON OOD.ARTICLE_NO = BDA.ARTICLE_NO"
					+" AND OOD.ENTERPRISE_NO = BDA.enterprise_no"
					+" WHERE OOD.STATUS = '10'";
				//	+" AND ACLASS = '0'";
				
			    sql2=" ORDER BY OOD.OWNER_NO,"
					+" BDS.REAL_SUPPLIER_NO,"
					+" BDS.SUPPLIER_NO,"
					+" OOD.S_CELL_NO";
			    
			  if(str!=null && !str.equals(""))
				{
					String ws=CommUtil.covtCollectionToWhereSql(str);
					sql=sql1+ws+sql2;
				}else{
					sql=sql1+sql2;
				}
	  	   List<Rodata_OutstockDirectModel> list=genDao.getListByNativeSql(sql, Rodata_OutstockDirectModel.class, intStart,intlimit);
	  	   ExtListDataBo<Rodata_OutstockDirectModel> extListBo = new ExtListDataBo<Rodata_OutstockDirectModel>(list, 0);
	  	   logger.info("leval queryRodata_OutStock_DirectModel");
	  	   return extListBo;
	}


	/*
	 * 供应商和退货单号下拉
	 */
	@Override
	public List<ComboxBo> getSupplierAndRecedeNoCombo(
			String enterpriseNo,String strWarehouseNo,String strOwnerNo,
			String str,String strWheresql,String strFlag, Integer intStart,
			Integer intPagesize) {
		String sql="";
		if(strFlag!=null && strFlag.equals("1")){
			sql="select distinct a.supplier_no as value," +
					"a.supplier_name as text," +
					"'['|| ltrim(a.supplier_no)||']'||a.supplier_name " +
					"dropValue from bdef_defsupplier a,rodata_outstock_direct b " +
					"where a.owner_no=b.owner_no " +
					" and a.supplier_no=b.supplier_no " +
					" and a.enterprise_no=b.enterprise_no" +
					" and b.status='10' and b.warehouse_no='"+strWarehouseNo+
					"' and b.enterprise_no='"+enterpriseNo+
					"' and b.owner_no in("+ strOwnerNo+")";
			if(strWheresql!=null && strWheresql!=""){
			    sql+=" and a.supplier_no like '%"+strWheresql+"%'";
			}
		}else if(strFlag!=null && strFlag.equals("2")){
			sql="select distinct a.source_no as value " +
					"from rodata_outstock_direct a where" +
					" a.enterprise_no='"+enterpriseNo+"' "+
					" and a.warehouse_no='"+strWarehouseNo+"' " +
					" and a.owner_no in("+ strOwnerNo+") and a.status='10' ";
			if(str!=null && !str.equals(""))
			{
				String ws=CommUtil.covtCollectionToWhereSql(str);
				sql=sql+ws;
			}
			if(strWheresql!=null && !strWheresql.equals(""))
			{
				sql+=" and a.source_no like '%"+strWheresql+"%'";
			}
		}
		List list=genDao.getListByNativeSql(sql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}

	@Override
	public MsgRes send(String enterpriseNo,String warehouseNo,
			String strWorkerNo,String strSendWorker, String str,String strWorkSpaceNo) throws Exception 
	{
		String sql="";
		sql="select rod.enterprise_no,rod.warehouse_no,rod.owner_no,rod.supplier_no," +
				"rod.class_type AClass,rod.source_no,rod.operate_type,rod.wave_no " +
				"from rodata_outstock_direct rod " +
				"where rod.source_no='"+str+
				"' and rod.enterprise_no='"+enterpriseNo+
				"' and rod.warehouse_no='"+warehouseNo+"' ";
		List<Rodata_OutstockDirectModel> rodm=genDao.getListByNativeSql(sql, Rodata_OutstockDirectModel.class, 0, 100);
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
			List  inList=new ArrayList();
			
			inList.add(rodm.get(0).getEnterpriseNo());
			inList.add(rodm.get(0).getWarehouseNo());
			inList.add(rodm.get(0).getOwnerNo());
			inList.add(rodm.get(0).getSupplierNo());
			inList.add(rodm.get(0).getAClass());
			inList.add(rodm.get(0).getSourceNo());
			inList.add("N");
			inList.add(strWorkSpaceNo);
			inList.add(strWorkerNo);
			inList.add(strSendWorker);
			inList.add(rodm.get(0).getOperateType());
			
			resultList=genDao.execProc(inList, outList, "pklg_rodata.P_RO_OUTSTOCK");
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			System.out.println(resultList.get(1).toString());
		return new MsgRes(true,TipUtil.getTipsByKey("E25201"),null);
	}
}
