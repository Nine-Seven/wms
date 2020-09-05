package com.sealinkin.rodata.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.rodata.model.Rodata_RecedeDModel;
import com.sealinkin.rodata.model.Rodata_RecedeMModel;
import com.sealinkin.rodata.po.Rodata_RecedeD;
import com.sealinkin.rodata.po.Rodata_RecedeM;
import com.sealinkin.rodata.service.IRodata_RecedeMService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Rodata_RecedeMImpl implements IRodata_RecedeMService{
    private static final Logger logger = Logger.getLogger(Rodata_RecedeMImpl.class);
		
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	
    //获得手键退厂明细信息（标准版和服装版公用）
	public ExtListDataBo<Rodata_RecedeDModel> getRodata_RecedeD(
			String str,String recedeNo, Integer start, Integer limit) {
		String sql="";
	    String sql1=" SELECT DISTINCT D.*,"
			+" BDA.BARCODE,"
			+" BDA.ARTICLE_NAME,"
			+" BDA.ARTICLE_IDENTIFIER,"
			+" BDA.ARTICLE_ONAME,"
			+" BDA.ARTICLE_ALIAS,"
			//+" nvl(bap.spec, '1*' || d.packing_qty || bda.unit) spec,"
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
			+
			//+" nvl(bap.packing_unit, decode(d.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) PACKINGUNIT,"
			"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingUnit,"+
			"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,BDA.qmin_operate_packing) packingUnitQmin,"+
			"f_get_packingunit(d.enterprise_no,d.owner_no,d.article_no,BDA.unit_packing) Unit,"+
			"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,d.packing_qty) packingSpec,"+
			"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,BDA.qmin_operate_packing) packingSpecQmin,"+
			"f_get_spec(d.enterprise_no,d.owner_no,d.article_no,BDA.unit_packing) spec,"
			/*+" NVL((d.recede_qty/ d.packing_qty),0) recedeWholeNum,"*/ 
			
			+
			"trunc(d.recede_qty/d.packing_qty) as planBox,"+
			"trunc(mod(d.recede_qty,d.packing_qty)/bda.QMIN_OPERATE_PACKING) as planQmin,"+
			"(d.recede_qty - trunc(d.recede_qty/d.packing_qty) * d.packing_qty - trunc(mod(d.recede_qty,d.packing_qty)/bda.QMIN_OPERATE_PACKING) * bda.QMIN_OPERATE_PACKING) as planDis,"
			
			+" FQ.TEXT AS QUALITY_DESC"
			+" FROM RODATA_RECEDE_D D,"
			+" bdef_defarticle    BDA,"
			+" BDEF_ARTICLE_PACKING BAP,"
			+" WMS_DEFFIELDVAL    FQ" 
			+" WHERE 1=1 ";
	   String sql2=" AND D.OWNER_NO = BDA.OWNER_NO"
			+" AND D.ENTERPRISE_NO=BDA.ENTERPRISE_NO"   
			+" AND D.ARTICLE_NO = BDA.ARTICLE_NO"
			+" AND D.ENTERPRISE_NO = BAP.ENTERPRISE_NO(+)"
			+" AND D.ARTICLE_NO = BAP.ARTICLE_NO(+)"
			+" AND D.PACKING_QTY = BAP.PACKING_QTY(+)"
			+" AND TRIM(NVL(D.QUALITY, '')) = FQ.VALUE(+)"
			+" AND UPPER(FQ.TABLE_NAME(+)) = 'N'"
			+" AND UPPER(FQ.COLNAME(+)) = 'QUALITY'"
			+" ORDER BY D.PO_ID,"
			+" D.ARTICLE_NO, D.PACKING_QTY";
	   if(str!=null && !str.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(str);
			sql=sql1+ws+sql2;
		}else{
			sql=sql1+sql2;
		}
  	    List<Rodata_RecedeDModel> list=genDao.getListByNativeSql(sql, Rodata_RecedeDModel.class, start,limit);
  	    ExtListDataBo<Rodata_RecedeDModel> extListBo = new ExtListDataBo<Rodata_RecedeDModel>(list, 0);
  	    logger.info("leval queryRodata_Recede_DModel");
	  	 return extListBo;
	}

	/*
	 * 通过商品编号自动获得明细信息（标准版和服装版公用）
	 * zhouhuan
	 * 修改人:czh
	 * 修改日期:2016/5/28
	 */
	public List<String> getRodata_RecedeMByArticleNO(
			String enterpriseNo,String strArticleNo, String strRecedeNo, Integer start, Integer limit) {
		String sql="select a.article_no,a.owner_article_no,a.article_name,a.barcode,a.qmin_operate_packing from " +
				"bdef_defarticle a " +
				"where a.article_no='"+strArticleNo+"' and a.enterprise_no='"+enterpriseNo+"'";
		List list = genDao.getListByNativeSql(sql);
	 return (List<String>)list;
	}

    /*
     * 保存退厂手建单（标准版和服装版公用）
     * zhouhuan
     */
	@Override
	public MsgRes saverodata_recede(String jsonMaster, String jsonDetail) throws Exception {
		Rodata_RecedeM recedeM=(Rodata_RecedeM)JSON.parseObject(jsonMaster,Rodata_RecedeM.class);
		List<Rodata_RecedeD> recedeD=JSON.parseArray(jsonDetail,Rodata_RecedeD.class);
		String recedeNo="";
		if(recedeM.getId().getRecedeNo().equals("保存时自动生成")){
			List inList2=new ArrayList();
			List outList2=new ArrayList();
			List resultList2=new ArrayList();
			
			inList2.add(recedeD.get(0).getId().getEnterpriseNo());
			inList2.add(recedeD.get(0).getId().getWarehouseNo());
			inList2.add(DocumentTypeModel.RODATARE);
			outList2.add("S");
			outList2.add("S");
			resultList2=genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception();
			}
			recedeNo=resultList2.get(0).toString();
			recedeM.getId().setRecedeNo(recedeNo);
			for(Rodata_RecedeD d:recedeD){
				d.getId().setRecedeNo(recedeNo);
			}
		}else{
			deleteRodata_RecedeM(
					recedeM.getId().getEnterpriseNo(),
					recedeM.getId().getWarehouseNo(),
					recedeM.getId().getRecedeNo());
			recedeNo=recedeM.getId().getRecedeNo();
		}
		System.out.println(recedeM);
		this.genDao.saveOrUpdateObj(recedeM);
		this.genDao.saveList(recedeD);
		return new MsgRes(true,TipUtil.getTipsByKey("E25001"),recedeNo);//数据保存成功！
	}

	/**
	 * 删除退厂单
	 */
	public void deleteRodata_RecedeM(String enterpriseNo,String warehouseNo,String recedeNo) {
		String delsql="delete rodata_recede_m a where " +
				"a.recede_no='"+recedeNo+
				"' and a.warehouse_no='"+ warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
		delsql="delete rodata_recede_d a where a.recede_no='"+recedeNo+
				"' and a.warehouse_no='"+ warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ";
		genDao.updateBySql(delsql);
	}

	/*
	 * 定位
	 */
	public MsgRes send(String enterpriseNo, String workerNo, String warehouseNo,String workSpaceNo, String str) throws Exception {
		List<Rodata_RecedeMModel> str1=JSON.parseArray(str, Rodata_RecedeMModel.class);
		List  outList=new ArrayList();
		List  resultList=new ArrayList();
		
		outList.add("S");
		outList.add("S");
		
		
		for (int i=0; i<str1.size();i++) {
			List  inList=new ArrayList();
			
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(str1.get(i).getOwnerNo());//strOwnerNo
			inList.add(str1.get(i).getRecedeNo());//strRecedeNo
			inList.add(str1.get(i).getSupplierNo());//strSupplierNo
			inList.add(str1.get(i).getClassType());//strClassType
			inList.add(workerNo);
			inList.add(workSpaceNo);
			resultList=genDao.execProc(inList, outList, "PKLG_ROLOCATE.P_RODATA_LOCATEAndTask");
			System.out.println(inList);
			if(resultList.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(1).toString());
			}
			System.out.println(resultList.get(0).toString());
		}
		return new MsgRes(true,TipUtil.getTipsByKey("E25001"),null);//定位成功！
	}



	/**
	 * 校验原单号的唯一性（标准版和服装版公用）
	 */
	@Override
	public MsgRes checkPoNo(String enterpriseNo,String strWarehouseNo, String strPoNo)
			throws Exception {
		String strSql="select rrm.po_no from rodata_recede_m rrm where rrm.po_no='"+strPoNo+
				     "' and rrm.warehouse_no='"+strWarehouseNo+
				     "' and rrm.enterprise_no='"+enterpriseNo+"' ";
		List<String> list = genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			return new MsgRes(false,"该单号已存在","");
		}else{
			return new MsgRes(true,"","");
		}
	}


}



















