package com.sealinkin.ridata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.IGetArticleForBarcodeService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.model.Ridata_CheckDModel;
import com.sealinkin.ridata.model.Ridata_CheckPalTmpModel;
import com.sealinkin.ridata.service.IRidata_CheckBadService;

/**
 * 返配次品扫描验收service
 * @author hkl
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_CheckBadImpl implements IRidata_CheckBadService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	private IGetArticleForBarcodeService getArticleForBarcodeImpl;

	public IGetArticleForBarcodeService getGetArticleForBarcodeImpl() {
		return getArticleForBarcodeImpl;
	}
	public void setGetArticleForBarcodeImpl(
			IGetArticleForBarcodeService getArticleForBarcodeImpl) {
		this.getArticleForBarcodeImpl = getArticleForBarcodeImpl;
	}
	
	/**
	 * 返配次品验收列
	 */
	@Override
	public ExtListDataBo<Ridata_CheckDModel> getRidata_CheckBadDList(
			String enterpriseNO,String strWarehouseNo,String strWhereSql) 
	{
		String sqls = "select SM.S_UNTREAD_NO from ridata_untread_sm sm ,ridata_untread_mm mm " +
				"where sm.enterprise_no=mm.enterprise_no and sm.warehouse_no=mm.warehouse_no " +
				"and sm.s_untread_no=mm.s_untread_no " +
				"and sm.untread_no='"+strWhereSql+"' and sm.enterprise_no='"+enterpriseNO+"' " +
				"and sm.warehouse_no='"+strWarehouseNo+"' and mm.quality = 'B'";
		List<String> lists = genDao.getListByNativeSql(sqls);
		String strSUntreadNo = lists.get(0);
		
		String strSql = "select b.article_no,b.packing_qty,c.article_name,c.barcode," +
			"(b.untread_qty/b.packing_qty) as pobox," +
			"(b.untread_qty/b.packing_qty) as checkbox," +
			"b.untread_qty - b.check_qty - case when (select sum(tmp.check_qty) from " +
			"ridata_check_pal_tmp tmp where tmp.s_untread_no='"+strSUntreadNo+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.article_no=b.article_no ) is null then 0 else (select sum(tmp.check_qty/tmp.packing_qty) " +
				"from ridata_check_pal_tmp tmp where tmp.s_untread_no='"+strSUntreadNo+"' " +
				"and tmp.warehouse_no='"+strWarehouseNo+"' " +
				"and tmp.article_no=b.article_no and tmp.packing_qty=b.packing_qty ) end untreadQty," +
			"c.expiry_days,f.cust_no, g.cust_name," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as produceDate," +
			"case when c.expiry_days=-1 then to_date ('1900-01-01','yyyy-mm-dd') else null end as expireDate," +
			"b.packing_qty,a.warehouse_no,a.owner_no," +
			//"nvl(e.packing_unit, decode(b.packing_qty,c.qmin_operate_packing,c.qmin_operate_packing_unit,c.unit)) unit,"+
            //"nvl(e.spec, '1*' || b.packing_qty || c.unit) spec," +
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingUnit,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingUnitQmin,"+
            "f_get_packingunit(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) Unit,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,b.packing_qty) packingSpec,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.qmin_operate_packing) packingSpecQmin,"+
            "f_get_spec(b.enterprise_no,b.owner_no,b.article_no,c.unit_packing) spec,"+
			"b.supplier_no,a.untread_no,a.untread_type,a.s_untread_no,b.check_qty,  F.QUALITY as qualityFlag, f.CLASS_TYPE " +
			"from ridata_untread_sm a,ridata_untread_d b,Bdef_Defarticle c," +
			"bdef_article_packing e, " +
			"ridata_untread_m f , bdef_defcust g " +
			"where a.untread_no=b.untread_no and b.article_no=c.article_no " +
			"and e.article_no(+)=b.article_no and e.packing_qty(+)=b.packing_qty " +
			"and b.warehouse_no=f.warehouse_no and b.owner_no=f.owner_no " +
			"and b.untread_no=f.untread_no and f.cust_no=g.cust_no  " +
			"and b.untread_qty-b.check_qty>0 and f.quality='B' " +
			"and a.warehouse_no='"+strWarehouseNo+"'" +
			"and a.s_untread_no = '"+strSUntreadNo+"' and a.enterprise_no=b.enterprise_no " +
			"and a.enterprise_no=c.enterprise_no " +
			"and b.enterprise_no=e.enterprise_no(+) and a.enterprise_no=f.enterprise_no " +
			"and a.enterprise_no=g.enterprise_no and a.enterprise_no='"+enterpriseNO+"' ";
	
		List<Ridata_CheckDModel> list = genDao.getListByNativeSql(strSql,Ridata_CheckDModel.class,0, 1000);
		ExtListDataBo<Ridata_CheckDModel> extListBo= new ExtListDataBo<Ridata_CheckDModel>(list, 0);
		return extListBo;
	}
	
	/**
	 * 货主等下拉列表
	 * @param str
	 * @param pageBo
	 * @return
	 */
	@Override
	public List<ComboxBo> getComboList(String enterpriseNO,String strWarehouseNo, String strWorkerOwner,
			String strFlag) {
        String strSql = "select distinct rum.owner_no value,t2.owner_name text,"  + 
				" '['|| ltrim(rum.owner_no)||']'||t2.owner_name dropValue "  + 
				"from ridata_untread_m rum, bdef_defowner t2 " +
				"where rum.owner_no = t2.owner_no " +
				"and rum.warehouse_no='"+strWarehouseNo+"' " +
				"and rum.status=10 and rum.enterprise_no=t2.enterprise_no " +
				"and rum.enterprise_no='"+enterpriseNO+"'";		
        List list=genDao.getListByNativeSql(strSql,ComboxBo.class, 0, 1000);
		return  (List<ComboxBo>)list;
	}
	
	
	//保存
	@Override
	public MsgRes save(String enterpriseNO,String strWarehouseNo,String jsonDetail) throws Exception {
		Ridata_CheckDModel pocd = (Ridata_CheckDModel)JSONObject.toBean(
				JSONObject.fromObject(jsonDetail),Ridata_CheckDModel.class);
		
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.parse("1900-01-01");
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");//strsCheckNo
		outList.add("S");//strResult
		
		inList.add(enterpriseNO);//strEnterpriseNO
		inList.add(strWarehouseNo);//strWareHouseNo
		inList.add(pocd.getOwnerNo());//strOwnerNo
		inList.add(pocd.getUntreadType());
		inList.add(pocd.getSUntreadNo());//strsUntreadNo
		inList.add(pocd.getArticleNo());//strArticleNo
		inList.add(pocd.getBarcode());//strBarcode
		inList.add(pocd.getPackingQty());//nPackingQty
		inList.add(pocd.getCheckQty());//nCheckQty
		inList.add("N");//strPrinterGroupNo
		inList.add(pocd.getDockNo());//strDockNo
		inList.add(pocd.getWorkerNo());//strWorkerNo
		inList.add("1");//strCheckTools
		inList.add(1);//nIsAdd
		inList.add("0");//strQuality
		inList.add(date);//dtProduceDate
		inList.add(date);//dtExpireDate   
		inList.add("N");//strLotNo
		inList.add("N");//strRSV_BATCH1
		inList.add("N");//strRSV_BATCH2
		inList.add("N");//strRSV_BATCH3
		inList.add("N");//strRSV_BATCH4
		inList.add("N");//strRSV_BATCH5
		inList.add("N");//strRSV_BATCH6
		inList.add("N");//strRSV_BATCH7
		inList.add("N");//strRSV_BATCH8
		inList.add(pocd.getLabelNo());//strLabelNo  strLabelNo
		inList.add(pocd.getLabelNo());//strSubLabelNo
		inList.add(pocd.getSupplierNo());//strSupplierNo
		inList.add("3");//strFixPalFlag
		inList.add("0");//strBusinessType
		
		inList.add(pocd.getDeviceNo());//strDeviceNo
		inList.add(pocd.getLabelId());//strLabelId
		inList.add(pocd.getRsvAttr2());//strStyle
		inList.add(pocd.getCellNo());//strCellNo		
		inList.add(pocd.getClassType());//strClassType
		inList.add(pocd.getQualityFlag());//strQualityFlag
		
		resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_SaveCheckTTH");
		System.out.println(resultList);
		if(resultList.get(1).toString().indexOf("Y")==-1){
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true,"保存成功！",null);
	}
	
	/**
	 * 校验码头与单号是否一致
	 */
	@Override
	public MsgRes checkDockNoUNo(String enterpriseNO,String strWarehouseNo,
			String strUntreadNo,String strDockNo) throws Exception {
	    	String str="select l.s_untread_no " +
	    			"from ridata_untread_sm l " +
	    			"where l.untread_no='"+strUntreadNo+"' " +
	    			"and l.enterprise_no='"+enterpriseNO+"' " +
	    			"and l.warehouse_no='"+strWarehouseNo+"' ";
	    	List<String> list=genDao.getListByNativeSql(str);
	    	String strSUntreadNo = list.get(0);
	    
		
			String strSql="select a.dock_no " +
					"from ridata_check_pal_tmp a " +
					"where a.s_untread_no='"+strSUntreadNo+"' " +
					"and a.warehouse_no = '"+strWarehouseNo+"' " +
					"and a.enterprise_no='"+enterpriseNO+"'  ";
			List<String> list2=genDao.getListByNativeSql(strSql);
			String dockNo = null;
			if(list2.size()>0){
				dockNo = list2.get(0);
				if(!dockNo.equals(strDockNo)){
					return new MsgRes(false,"您选择的单号已经在其它扫描台验收，请重新选择单号！","");	
					
				}
			}
			return new MsgRes(true,"",dockNo);	
		
	}
	
	/**
	 * 校验条码（次品只允许一单一验）
	 */
	@Override
	public MsgRes queryBarcode(String enterpriseNO,String strWarehouseNo, String strBarcode,
			String strOwnerNo,  String strUntreadNo)
			throws Exception {
		MsgRes msg=null;
		msg=getArticleForBarcodeImpl.checkBarcode(strWarehouseNo, strBarcode, strOwnerNo,enterpriseNO);
		if(msg.getIsSucc()){
			String strSql="select a.article_no,a.supplier_no,a.packing_qty,a.qty-nvl(b.uncheckqty,0), A.RSV_ATTR2,a.barcode from "+
			             "(select rus.enterprise_no,rus.warehouse_no,rus.s_untread_no,rud.untread_no," +
			             "rud.article_no,rud.packing_qty,rud.supplier_no,rud.untread_qty-rud.check_qty qty,DEF.RSV_ATTR2, def.barcode    "+ 
		                 "from ridata_untread_sm rus,ridata_untread_d rud, BDEF_DEFARTICLE DEF "+
		                 "where rus.warehouse_no=rud.warehouse_no " +
		                 "and rus.enterprise_no=rud.enterprise_no " +
		                 "and rus.untread_no=rud.untread_no  " +
		                 "AND RUd.ENTERPRISE_NO = def.enterprise_no " +
		                 "and rud.owner_no=def.owner_no " +
		                 "and rud.article_no=def.article_no "+
		                 "and rus.untread_no='"+strUntreadNo+"' " +
		                 "and rud.warehouse_no='"+strWarehouseNo+"' " +
		                 "and rud.enterprise_no='"+enterpriseNO+"' " +
		                 "and rud.article_no in ("+(msg.getObj().toString())+")) a,"+
		                 "(select c.enterprise_no,c.warehouse_no,c.s_untread_no,c.article_no,sum(c.check_qty) uncheckqty from "+
		                 "ridata_check_pal_tmp c " +
		                 "where c.article_no in("+(msg.getObj().toString())+") " +
		                 "and c.s_untread_no=(select s_untread_no " +
		                 "from ridata_untread_sm where untread_no='"+strUntreadNo+"' " +
		                 "and warehouse_no='"+strWarehouseNo+"' and enterprise_no='"+enterpriseNO+"') " +
		                 "and c.warehouse_no='"+strWarehouseNo+"' " +
		                 "and c.enterprise_no='"+enterpriseNO+"'" +
		                 "group by c.enterprise_no,c.warehouse_no,c.s_untread_no,c.article_no)b "+
		                 "where a.warehouse_no=b.warehouse_no(+) " +
		                 "and a.enterprise_no=b.enterprise_no(+) " +
		                 "and a.s_untread_no=b.s_untread_no(+) "+
		                 "and a.article_no=b.article_no(+) " +
		                 "and a.qty- nvl(b.uncheckqty,0)>0";

			List<String> list=genDao.getListByNativeSql(strSql);
			if(list.size()>0){
				msg.setObj(list);
				msg.setIsSucc(true);
				msg.setMsg("成功");
			}else{
				msg.setIsSucc(false);
				msg.setMsg("该商品不在此张表内或已验完");
			}
		}else{
			msg.setIsSucc(false);
			msg.setMsg("该条码不存在");
		}
		return msg;
	}
	
	/**
	 * 封板
	 */
	@Override
	public MsgRes tscClosePal(String strDetail) throws Exception {
		List<Ridata_CheckPalTmpModel> list=JSON.parseArray(strDetail,Ridata_CheckPalTmpModel.class);
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");//strResult
		
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			inList.add(list.get(i).getEnterpriseNo());
			inList.add(list.get(i).getWarehouseNo());
			inList.add(list.get(i).getOwnerNo());
			inList.add(list.get(i).getSUntreadNo());//strsUntreadNo
			inList.add(list.get(i).getSCheckNo());//strsCheckNo
			inList.add(list.get(i).getCellNo());//strCellNo
			inList.add(list.get(i).getDockNo());//strDockNo
			inList.add(list.get(i).getWorkerNo());//strWorkerNo
			resultList=genDao.execProc(inList,outList,"PKLG_RIDATA.P_BadGoodSaveCheck_Instock");
			if(resultList.get(0).toString().indexOf("Y")==-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"封板成功","");
	}


	/**
	 * 次品储位下拉
	 */
	@Override
	public List<ComboxBo> getCdef_DefCellCombo(
			String enterpriseNo,String strWarehouseNo, int i, int j) {
		String sql = "select cdc.cell_no value,cdc.cell_no text,cdc.cell_no dropValue  "
				+ "from Cdef_Defcell cdc, cdef_defarea cda "
				+ "where cdc.enterprise_no=cda.enterprise_no " +
				"and cdc.warehouse_no = cda.warehouse_no  " +
				"and cdc.ware_no = cda.ware_no and cdc.area_no = cda.area_no  " +
				"and cda.area_usetype='2' " +
				"and cdc.cell_status = '0' " +
				"and cdc.check_status = '0' " +
				"and cda.Area_Attribute = '0' " +
				"and cda.attribute_type='0' "
				+ "and cdc.warehouse_No='" + strWarehouseNo + "' "
				+" and cdc.enterprise_no = '"+enterpriseNo+"' " +
				"and rownum=1 ";
	
		List list = genDao.getListByNativeSql(sql, ComboxBo.class, 0, 10);
		return (List<ComboxBo>) list;
	}
	
	//获取临时表数据
	@Override
	public ExtListDataBo<Ridata_CheckPalTmpModel> queryCheckPalTmp(
			String enterpriseNO, String strWarehouseNo, 
			String strDockNo,String strSUntreadNo2,String strUntreadNo){
		
		    if(!strUntreadNo.equals("") && strUntreadNo!=null){
		    	String str="select l.s_untread_no " +
		    			"from ridata_untread_sm l " +
		    			"where l.untread_no='"+strUntreadNo+"' " +
		    			"and l.enterprise_no='"+enterpriseNO+"' " +
		    			"and l.warehouse_no='"+strWarehouseNo+"' ";
		    	List<String> list=genDao.getListByNativeSql(str);
		    	strSUntreadNo2 = list.get(0);
		    }
		
			String strSql="select a.label_no,a.barcode,a.packing_qty," +
					"sum(a.check_qty)/a.packing_qty as check_qty,a.label_id,b.article_name,a.s_check_no,a.s_untread_no " +
					"from ridata_check_pal_tmp a," +
					"bdef_defarticle b " +
					"where a.article_no=b.article_no " +
					"and a.dock_no='"+strDockNo+"' " +
					"and a.s_untread_no='"+strSUntreadNo2+"' " +
					"and a.warehouse_no = '"+strWarehouseNo+"' " +
					"and a.enterprise_no=b.enterprise_no " +
					"and a.enterprise_no='"+enterpriseNO+"'  " +
				    "group by a.barcode,a.packing_qty,a.label_no,a.label_id,b.article_name,a.s_check_no,a.s_untread_no  ";
			List<Ridata_CheckPalTmpModel> list=null;
			ExtListDataBo<Ridata_CheckPalTmpModel> extListBo=null;
			list = genDao.getListByNativeSql(strSql,Ridata_CheckPalTmpModel.class,0,1000);
			extListBo= new ExtListDataBo<Ridata_CheckPalTmpModel>(list, 0);
	        return extListBo;
	}
}
