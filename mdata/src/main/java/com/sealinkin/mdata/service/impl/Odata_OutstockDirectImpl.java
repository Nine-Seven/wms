/*
 * 移库发单
 */
package com.sealinkin.mdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.mdata.service.IOdata_OutstockDirectService;
import com.sealinkin.odata.model.Odata_OutstockDirectModel;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_OutstockDirectImpl implements IOdata_OutstockDirectService{
    //private static final Logger logger = Logger.getLogger(Odata_OutStock_DirectServiceImpl.class);
		
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/*
	 * 或取储区信息
	 * zhouhuan
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDirectModel> getAreaList(String enterpriseNo,String str,
			String warehouseNo,Integer start, Integer pageSize) {
		String sql="";
		String sql1="SELECT DISTINCT (CDC.WARE_NO || CDC.AREA_NO) AS AREA_NO," +
				" CDA.AREA_NAME," +
				//" CDA.AREA_TYPE, OOD.OUTSTOCK_TYPE, OOD.S_CELL_NO," +
				"OOD.OPERATE_TYPE " +
				" FROM Odata_OUTSTOCK_DIRECT OOD" +
				" INNER JOIN Cdef_DEFCELL CDC ON OOD.S_CELL_NO = CDC.CELL_NO" +
				" AND OOD.warehouse_no = CDC.warehouse_no " +
				" and ood.enterprise_no=cdc.enterprise_no" +
				" INNER JOIN Cdef_DEFAREA CDA ON CDA.warehouse_no = CDC.warehouse_no" +
				" and cda.enterprise_no=cdc.enterprise_no AND CDA.WARE_NO = CDC.WARE_NO" +
				" AND CDA.AREA_NO = CDC.AREA_NO" +
				" LEFT JOIN (select c.enterprise_no,c.outstock_type," +
				" c.operate_type, c.source_type," +
				" nvl(d.pick_order, c.pick_order) pick_order" +
				" from WMS_CRTPAPER_ORDER c" +
				" left join oset_CRTPAPER_ORDER d " +
				" on c.outstock_type = d.outstock_type" +
				" and c.enterprise_no=d.enterprise_no " +
				" and c.source_type = d.source_type" +
				" and c.operate_type = d.operate_type) od " +
				" ON OOD.OUTSTOCK_TYPE = od.OUTSTOCK_TYPE " +
				" and ood.enterprise_no=od.enterprise_no " +
				" AND OOD.OPERATE_TYPE = od.OPERATE_TYPE" +
				" AND OOD.SOURCE_TYPE = OD.SOURCE_TYPE" +
				" WHERE OOD.STATUS = '10' " +
				" and OOD.enterprise_no='"+enterpriseNo+"' " +
				" and OOD.warehouse_no = '"+warehouseNo+"' " ;
		String sql2=" ORDER BY AREA_NO";
		if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql1+ws+sql2;
			//totsql=totsql+ws;
		}
	    List<Odata_OutstockDirectModel> list = genDao.getListByNativeSql(sql,Odata_OutstockDirectModel.class,start,pageSize);
		//Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Odata_OutstockDirectModel> extListBo= new ExtListDataBo<Odata_OutstockDirectModel>(list, 0);
        return extListBo;
	}

	/*
	 * 获取移库发单信息
	 */
	@Override
	public ExtListDataBo<Odata_OutstockDirectModel> getOdata_OutstockDirectList(
			String enterpriseNo,String areaNo, String warehouseNo, String ownerNo,
			String outstockType, Integer start, Integer pageSize) {
		String sql=" SELECT ood.enterprise_no,OOD.warehouse_no, OOD.OWNER_NO, OOD.OUTSTOCK_TYPE," +
				" OOD.OPERATE_TYPE, OOD.OPERATE_DATE, OOD.PICK_TYPE, OOD.BATCH_NO," +
				" OOD.WAVE_NO, OOD.EXP_NO, OOD.ARTICLE_NO, OOD.PACKING_QTY," +
				" OOD.S_CELL_NO, OOD.D_CELL_NO, OOD.S_CONTAINER_NO," +
				//"nvl(pk.packing_unit, decode(ood.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit," +
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingUnit,"+
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,ood.packing_qty) packingSpec,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(ood.enterprise_no,ood.owner_no,ood.article_no,bda.unit_packing) spec,"+
				//" sum(OOD.ARTICLE_QTY)as article_qty," +
				"  sum(OOD.LOCATE_QTY) as locate_qty, BDA.ARTICLE_NAME," +
				" BDA.BARCODE,BDA.OWNER_ARTICLE_NO as ownerArticleNo FROM Odata_OUTSTOCK_DIRECT OOD" +
				" inner join bdef_defarticle bda" +
				" on ood.article_no = bda.article_no and ood.enterprise_no=bda.enterprise_no " +
				" INNER JOIN Cdef_DEFCELL CDC" +
				" ON OOD.S_CELL_NO = CDC.CELL_NO and ood.enterprise_no=cdc.enterprise_no " +
				" AND OOD.warehouse_no = CDC.warehouse_no" +
				" INNER JOIN Cdef_DEFAREA CDA" +
				" ON CDC.warehouse_no = CDA.warehouse_no" +
				" AND CDC.WARE_NO = CDA.WARE_NO and ood.enterprise_no=cda.enterprise_no" +
				" AND CDC.AREA_NO = CDA.AREA_NO " +
				" left join bdef_article_packing pk " +
				"on ood.enterprise_no=pk.enterprise_no " +
				"and ood.article_no=pk.article_no " +
				"and ood.packing_qty=pk.packing_qty " +
				" WHERE OOD.STATUS='10' " +
				" AND OOD.warehouse_no= '"+warehouseNo+"' " +
				" and ood.enterprise_no='"+enterpriseNo+"' " +
				" AND NOT EXISTS(SELECT 'X' FROM Odata_OUTSTOCK_DIRECT ODDI " +
				" WHERE ODDI.warehouse_no=OOD.warehouse_no and oddi.enterprise_no=ood.enterprise_no" +
				" AND ODDI.WAVE_NO=OOD.WAVE_NO" +
				" AND ODDI.BATCH_NO= OOD.BATCH_NO  AND ODDI.S_CONTAINER_NO=OOD.S_CONTAINER_NO" +
				" and ODDI.STATUS<>OOD.STATUS AND ODDI.S_CONTAINER_NO<>'N' ) " +
				" AND OOD.OWNER_NO= '"+ownerNo+"'"+ 
				" AND  (CDC.Ware_No||cdc.area_no LIKE '"+areaNo+"%' "+
				" OR EXISTS(SELECT 'X' FROM Odata_OUTSTOCK_DIRECT ODDI" +
				" WHERE ODDI.warehouse_no=OOD.warehouse_no and oddi.enterprise_no=ood.enterprise_no" +
				" AND ODDI.WAVE_NO=OOD.WAVE_NO " +
				" AND ODDI.BATCH_NO= OOD.BATCH_NO  AND ODDI.S_CONTAINER_NO=OOD.S_CONTAINER_NO " +
				" AND ODDI.S_CONTAINER_NO<>'N' AND ODDI.S_CELL_NO LIKE '"+areaNo+"%' "+
				" )) AND OOD.OUTSTOCK_TYPE= '"+outstockType+"' "+
				" AND OOD.OUTSTOCK_TYPE= '"+outstockType+"' " +
				" group by ood.enterprise_no,bda.spec,OOD.warehouse_no, OOD.OWNER_NO, OOD.OUTSTOCK_TYPE," +
				" OOD.OPERATE_TYPE, OOD.OPERATE_DATE, OOD.PICK_TYPE," +
				" OOD.BATCH_NO, OOD.WAVE_NO, OOD.EXP_NO," +
				" OOD.ARTICLE_NO, OOD.PACKING_QTY, OOD.S_CELL_NO," +
				" OOD.D_CELL_NO, OOD.S_CONTAINER_NO, " +
				" OOD.LOCATE_QTY, BDA.ARTICLE_NAME,BDA.BARCODE,BDA.OWNER_ARTICLE_NO,pk.packing_unit,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit,bda.unit_packing" +
				" ORDER BY OOD.warehouse_no," +
				" OOD.OWNER_NO, OOD.OUTSTOCK_TYPE, OOD.OPERATE_TYPE," +
				" OOD.OPERATE_DATE, OOD.PICK_TYPE, OOD.BATCH_NO, OOD.WAVE_NO," +
				" OOD.EXP_NO, OOD.ARTICLE_NO, OOD.PACKING_QTY, OOD.S_CELL_NO," +
				" OOD.D_CELL_NO, OOD.S_CONTAINER_NO, " +
				" OOD.LOCATE_QTY, BDA.ARTICLE_NAME,BDA.BARCODE";
     
		 List<Odata_OutstockDirectModel> list = genDao.getListByNativeSql(sql,Odata_OutstockDirectModel.class,start,10000);
		//Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Odata_OutstockDirectModel> extListBo= new ExtListDataBo<Odata_OutstockDirectModel>(list, 0);
        return extListBo;
	}

	/*
	 * 移库发单-->人工移库和安全量补货发单
	 */
	@Override
	public MsgRes send(String enterpriseNo,String warehouseNo,String workerNo,
			String celltype, String outstockworker, String str,String workSpaceNo,String operateType) throws Exception {
		List<Odata_OutstockDirectModel> odd=JSON.parseArray(str,Odata_OutstockDirectModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		for (Odata_OutstockDirectModel i : odd) {
			List  inList=new ArrayList();
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(i.getOwnerNo());
			inList.add(operateType);
			inList.add(celltype);
			inList.add(outstockworker);
			inList.add(workSpaceNo);
			inList.add(workerNo);
			inList.add("1");
			
			resultList=genDao.execProc(inList, outList, "pklg_mdata.p_DoManuHM");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
			System.out.println(resultList.get(0).toString());
		}
		return new MsgRes(true,"发单成功！",null);
	}

	
/*	@Override
	public List<ComboxBo> getOdata_GetWaveCombo(String enterpriseNo,String warehouseNo,
			String workerOwner) {
		String sql="select distinct a.WAVE_No value,a.WAVE_No text,a.WAVE_No dropValue " +
		"from odata_outstock_direct a " +
		"where a.status='10' and a.warehouse_no='"+warehouseNo+"' " +
		"and a.owner_no in (" +workerOwner+") and a.outstock_type='1' " +
		"and a.enterprise_no='"+enterpriseNo+"' "+
		"order by a.wave_no" ;
        List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
*/
}
