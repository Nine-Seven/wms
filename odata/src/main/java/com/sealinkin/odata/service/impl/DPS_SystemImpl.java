package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.util.JSONUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.odata.service.IDPS_SystemService;
import com.sealinkin.protocolExchange.dpsdata.AnsDPSAddressModel;
import com.sealinkin.protocolExchange.dpsdata.AnsGetDivideSuppBoxModel;
import com.sealinkin.protocolExchange.dpsdata.DPSAddressModel;
import com.sealinkin.protocolExchange.dpsdata.DivideSuppBoxDetailModel;
import com.sealinkin.protocolExchange.odata.OdataSavleLabelNoModel;
import com.sealinkin.protocolExchange.odata.StuAreaBarcodeInfoModel;
import com.sealinkin.protocolExchange.odata.StuPickSuppBoxDetail;
import com.sealinkin.protocolExchange.odata.StuReqGetPickBox;
import com.sealinkin.util.DateUtil;
import com.sealinkin.util.MyDateMorpher;
import com.sealinkin.util.StringUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class DPS_SystemImpl implements IDPS_SystemService{
		
	private IGenericManager genDao;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}	

	@Override
	public MsgRes getDPSAddress(String strEnterpriseNo, String strWarehouseNo,
			int nUseType, String strDeviceGroupNo)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		String strSql="select cdd.Device_No as DeviceNo,cdd.cell_no as CellNo ,cdd.ctrl_no as CtrNo,cdd.device_type as DeviceType,cdd.address as TagNode,   "
						+"       bc.address as BarcodeTagNode,dc.address as DispLabelTagNode,   "
						+"       vc.address as VoiceTagNode,lc.address as StockLampTagNode,   "
						+"       cdd.dps_area as AreaNo ,cdd.stock_no as StockNo,cdd.disp_flag as DispFlag,cdd.printer_name as PntName,cdd.dps_disp_length as dpsdisplength   "
						+" from cdef_defcell_dps cdd   "
						+" inner join (select c.enterprise_no,c.warehouse_no,c.device_no,c.dps_area,c.stock_no,c.address from cdef_defcell_dps c where c.device_type=1) bc   "
						+"  on bc.warehouse_no=cdd.warehouse_no and bc.device_no = cdd.device_no and bc.dps_area=cdd.dps_area and bc.stock_no=cdd.stock_no and bc.enterprise_no=cdd.enterprise_no  "
						+" inner join (select c.enterprise_no,c.warehouse_no,c.device_no,c.dps_area,c.stock_no,c.address from cdef_defcell_dps c where c.device_type=2 and c.disp_flag=1) dc   "
						+"  on dc.warehouse_no=cdd.warehouse_no and dc.device_no = cdd.device_no and dc.dps_area=cdd.dps_area and dc.stock_no=cdd.stock_no   and dc.enterprise_no=cdd.enterprise_no "
						+" left join (select c.enterprise_no,c.warehouse_no,c.device_no,c.dps_area,c.stock_no,c.address from cdef_defcell_dps c where c.device_type=5) vc   "
						+"  on vc.warehouse_no=cdd.warehouse_no and vc.device_no = cdd.device_no and vc.dps_area=cdd.dps_area and vc.stock_no=cdd.stock_no   and vc.enterprise_no=cdd.enterprise_no "
						+" left join (select c.enterprise_no,c.warehouse_no,c.device_no,c.dps_area,c.stock_no,c.address from cdef_defcell_dps c where c.device_type=3) lc   "
						+"  on lc.warehouse_no=cdd.warehouse_no and lc.device_no = cdd.device_no and lc.stock_no=cdd.stock_no  and lc.enterprise_no=cdd.enterprise_no  " 
						+" inner join device_divide_m ddm " 
						+"  on cdd.enterprise_no = ddm.enterprise_no " 
						+"  and cdd.warehouse_no = ddm.warehouse_no " 
						+"  and cdd.device_no = ddm.device_no " 
						+"  and ddm.device_group_no = '"+ strDeviceGroupNo +"' "
						+" where cdd.warehouse_no='"+strWarehouseNo+"'" +
						" and cdd.ENTERPRISE_NO='"+strEnterpriseNo+"'" +						
						"  and cdd.use_type="+nUseType;
						//+"  and cdd.work_station='"+strCtrlStatusNo+"'";
		List<DPSAddressModel> list=genDao.getListByNativeSql(strSql, DPSAddressModel.class);
		if(list.size()>0){
			AnsDPSAddressModel ansMod=new AnsDPSAddressModel();
			ansMod.setCount(list.size());
			ansMod.setLstDpsAddr(list);
			msgRes.setIsSucc(true);
			msgRes.setObj(JSONObject.toJSON(ansMod));
		}else{
			msgRes.setIsSucc(false);
			msgRes.setMsg("没有获取到电子标签数据");
		}
		return msgRes;
	}
	@Override
	public MsgRes getDivideDetail(String strEnterpriseNo,String strWarehouseNo, Integer nUseType,
			Integer nCtrlNo, Integer nAreaNo, Integer nStockNo, String strBoxNo)
			throws Exception {
		MsgRes msgRes=new MsgRes();
		//校验箱号
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();

		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(nAreaNo);
		inList.add(nStockNo);
		inList.add(nUseType);
		inList.add(nCtrlNo);
		inList.add(strBoxNo);
		
		resultList = genDao.execProc(inList, outList, "PKLG_RIDATA.P_CheckDpsDeviceNo");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}
		else
		{
			String strSql="select cdp.ctrl_no,   "
				+"       cdp.address,   "
				+"       iid.instock_no as source_no,   "
				+"       'N' as cust_no,   "
				+"       iid.batch_no,   "
				+"       'N' as a_sorter_chute_no,   "
				+"       iid.article_no,   "
				+"       sai.barcode,   "
				+"       iid.packing_qty,   "
				+"       cdp.cell_disp_length,   "
				+"       cdp.cell_start_pos,   "
				+"       cdp.dps_disp_length,   "
				+"       'N' as deliver_obj,   "
				+"       bda.qmin_operate_packing,   "
				+"       sum(iid.article_qty - iid.real_qty) as qty   "
				+"  from stock_article_info sai,   "
				+"       bdef_defarticle    bda,   "
				+"       ridata_instock_d   iid,   "
				+"       cdef_defcell_dps   cdp   "
				+" where bda.article_no = iid.article_no  " 
				+"   and bda.owner_no = iid.owner_no "
				+"   and bda.enterprise_no = iid.enterprise_no  " 
				+"   and sai.article_no = iid.article_no   "
				+"   and sai.article_id = iid.article_id   "
				+"   and sai.enterprise_no = iid.enterprise_no  " 
				+"   and cdp.warehouse_no = iid.warehouse_no   "
				+"   and cdp.cell_no = iid.dest_cell_no" 
				+"   and cdp.enterprise_no = iid.enterprise_no " 
				+"   and iid.article_qty>iid.real_qty  "
				+"   and cdp.dps_area = "+nAreaNo
				+"   and cdp.stock_no = "+nStockNo
				+"   and cdp.use_type = "+nUseType
				+"   and iid.warehouse_no = '"+strWarehouseNo+"'  " 
				+"   and iid.enterprise_no = '"+ strEnterpriseNo + "'" 
				+"   and iid.label_no = '"+strBoxNo+"'   "
				+" group by cdp.ctrl_no,   "
				+"          cdp.address,   "
				+"          iid.instock_no,   "
				+"          iid.batch_no,   "
				+"          iid.article_no,   "
				+"          sai.barcode,   "
				+"          iid.packing_qty,   "
				+"          cdp.cell_disp_length,   "
				+"          cdp.cell_start_pos,   "
				+"          cdp.dps_disp_length,   "
				+"          bda.qmin_operate_packing   ";
			List<DivideSuppBoxDetailModel> list=genDao.getListByNativeSql(strSql, DivideSuppBoxDetailModel.class);
			if(list.size()>0){
				AnsGetDivideSuppBoxModel ansMod=new AnsGetDivideSuppBoxModel();
				ansMod.setLstDetail(list);
				msgRes.setIsSucc(true);
				msgRes.setObj(JSONObject.toJSON(ansMod));
			}
			else{
				msgRes.setIsSucc(false);
				msgRes.setMsg("获取分播箱明细");
			}
		}
		return msgRes;
	}
	//分播数据保存
	@Override
	public MsgRes tscSaveDivideData(String strEnterpriseNo,
			String strWarehouseNo, 
			String instockNo,
			String labelNo, 
			String articleNo, 
			String barcode, 
			float packingQty,
			double realQty, 
			String userId, 
			String paperUserId,
			Integer operateTools) throws Exception {
		
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(instockNo);//strinstockNo
		inList.add(labelNo);//strlabelNo
		inList.add(articleNo);//strArticleNo
		inList.add(barcode);//strBarcode
		inList.add(packingQty);//nPackingQty
		inList.add(realQty);//nRealQty
		inList.add(userId);//strUserId
		inList.add(paperUserId);//strPaperUserId
		inList.add(operateTools);//strTools
		resultList = genDao.execProc(inList, outList, "PKLG_RIDATA.P_UpdateDPSInstockitem");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"保存成功","");
	}
	/**
	 * 封箱
	 */
	@Override
	public MsgRes tscCutBOX(String strEnterpriseNo, 
			String strWarehouseNo, 
			String cellNo,
			String userId, 
			String paperUserId, 
			Integer operateTools,
			String pntName)
			throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(cellNo);//strinstockNo
		inList.add(userId);//strUserId
		inList.add(paperUserId);//strPaperUserId
		inList.add(operateTools);//strTools
		inList.add(pntName);//strPrinterGroupNo
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_RIDATA.P_DpsComfireInstock");
		if(resultList.get(0).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(0).toString());
		}		
		return new MsgRes(true,"保存成功","");
	}
	@Override
	/**
	 * 校验员工号是否存在
	 */
	public MsgRes CheckWorkerNo(String enterpriseNo, String reqObj)
			throws Exception {
		
		String strSql = " select 1 from bdef_defworker a " +
				" where a.worker_no='"+reqObj+"' " +
				" and a.enterprise_no='"+enterpriseNo+"'";
		
		List list = genDao.getListByNativeSql(strSql);
		if(list.size()> 0)
		{
			return new MsgRes(true,"","");
		}
		else
		{ return new MsgRes(false,"","员工号不存在！");}
	}
	
	//拣货回单
	@Override
	public MsgRes tscReceipt(String strRecvData) throws Exception {
		OdataSavleLabelNoModel d=JSON.parseObject(strRecvData,OdataSavleLabelNoModel.class);
		
		JSONUtils.getMorpherRegistry().registerMorpher(new MyDateMorpher(new String[]{"yyyy-MM-dd"}));
		
		String sql=" select b.produce_date,b.expire_date,b.quality, b.lot_no, " +
				   "        b.rsv_batch1,b.rsv_batch2,b.rsv_batch3,b.rsv_batch4, b.rsv_batch5, " +
				   "        b.rsv_batch6,b.rsv_batch7, b.rsv_batch8  ,sum(a.article_qty) article_qty " +
				   "   from odata_outstock_d a, stock_article_info b,STOCK_LABEL_M M " +
				   "  where a.outstock_no = '"+d.getSourceNo()+"' " +
				   "    and a.enterprise_no = '"+d.getEnterpriseNo()+"' " +
				   "    and a.warehouse_no = '"+d.getWareHouseNo()+"' " +
				   "    and M.label_no = '"+d.getLabelNo()+"' " +
				   "    and a.article_no = '"+d.getArticleNo()+"' " +
				   "    and a.packing_qty = '"+d.getPackingQty()+"' " +
				   "    and a.enterprise_no = b.enterprise_no " +
				   "    and a.article_no = b.article_no " +
				   "    and a.article_id = b.article_id and a.status ='10' " +
				   " AND A.WAREHOUSE_NO =M.WAREHOUSE_NO AND A.ENTERPRISE_NO = M.ENTERPRISE_NO AND A.S_CONTAINER_NO = M.CONTAINER_NO " +
				   "  group by b.produce_date,b.expire_date,b.quality, b.lot_no, " +
				   "        b.rsv_batch1,b.rsv_batch2,b.rsv_batch3,b.rsv_batch4, b.rsv_batch5, " +
				   "        b.rsv_batch6,b.rsv_batch7, b.rsv_batch8 ";

		List<Odata_OutstockDModel> ood = genDao.getListByNativeSql(sql, Odata_OutstockDModel.class);

		if(ood.size() == 0)
		{
			return  new MsgRes(false,"未查询到下架信息（"+d.getSourceNo()+"  |  "+d.getLabelNo()+"）！",null); 
		}
		
		List  outList=new ArrayList();
		List  resultList=new ArrayList();	
		outList.add("S");

		List  inList=new ArrayList();
		double totalQty = d.getRealQty();
		double dRealQTY=0;

		for(int i=0;i< ood.size();i++){
			inList=new ArrayList();
			inList.add(d.getEnterpriseNo());
			inList.add(d.getWareHouseNo());
			inList.add(d.getSourceNo());
			//inList.add(StringUtil.isStrEmpty(ood.get(0).getCustContainerNo())?"N":ood.get(0).getCustContainerNo());
			inList.add(d.getLabelNo());
			inList.add(d.getArticleNo());
			inList.add(d.getPackingQty());
			inList.add(d.getCellNo());
			if (ood.get(i).getArticleQty()>=totalQty)
			{
				dRealQTY=totalQty;
			}
			else
			{
				dRealQTY=ood.get(i).getArticleQty();
			}
			totalQty-=dRealQTY;
			inList.add(dRealQTY);

			//inList.add(d.getRealQty());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getQuality())?"0":ood.get(i).getQuality());
			inList.add(ood.get(i).getProduceDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):ood.get(i).getProduceDate());
			inList.add(ood.get(i).getExpireDate()==null? DateUtil.GetDate("19000101", "yyyyMMdd"):ood.get(i).getExpireDate());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getLotNo())?"N":ood.get(0).getLotNo());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch1())?"N":ood.get(i).getRsvBatch1());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch2())?"N":ood.get(i).getRsvBatch2());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch3())?"N":ood.get(i).getRsvBatch3());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch4())?"N":ood.get(i).getRsvBatch4());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch5())?"N":ood.get(i).getRsvBatch5());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch6())?"N":ood.get(i).getRsvBatch6());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch7())?"N":ood.get(i).getRsvBatch7());
			inList.add(StringUtil.isStrEmpty(ood.get(i).getRsvBatch8())?"N":ood.get(i).getRsvBatch8());
			inList.add("N");//码头strDock_No
			inList.add(d.getUserId());//回单人--系统登录人
			inList.add(d.getUserId());//拣货人员strUserID
			inList.add(d.getUserId());
			System.out.println(inList);
			resultList=genDao.execProc(inList, outList, "pklg_odata_lich.P_TaskLabelSave_Odata_Outstock");
			if(resultList.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList.get(0).toString());
			}
		}
		return new MsgRes(true,"回单成功！",null);
	}
	
	//DPS获取当前任务号所在的巷道，区域，最小箱码
	@Override
	public MsgRes getMinArea(String strRecvData) throws Exception {
 
		StuReqGetPickBox req=JSON.parseObject(strRecvData,StuReqGetPickBox.class);
		
		String outstock_no = req.getBoxNo();
		//校验当前扫描的单号是否是有效的单号！需要支持下架单和标签号
		String sql1 ="SELECT M.SOURCE_NO FROM STOCK_LABEL_M M " +
				"WHERE (M.LABEL_NO = '"+outstock_no+"' OR M.SOURCE_NO = '"+outstock_no+"') " +
				   " AND M.enterprise_no = '"+req.getEnterpriseNo()+"' " +
				   " AND M.warehouse_no = '"+req.getWarehouseNo()+"'";
		List list1 = genDao.getListByNativeSql(sql1);
		if(list1.size() == 0)
		{
			return new MsgRes(false,"输入的单号无效！",null); 
		}
		outstock_no = list1.get(0).toString();
		
		String sql=" select cdd.stock_no,cdd.dps_area AreaNo,m.label_no,oom.outstock_no OutStockNo  " +
				   "   from odata_outstock_m oom " +
				   "  inner join odata_outstock_d ood " +
				   "     on oom.enterprise_no = ood.enterprise_no " +
				   "    and oom.warehouse_no = ood.warehouse_no " +
				   "    and oom.outstock_no = ood.outstock_no " +
				   "	INNER JOIN STOCK_LABEL_M M ON OOM.WAREHOUSE_NO = M.WAREHOUSE_NO AND OOM.ENTERPRISE_NO = M.ENTERPRISE_NO AND OOD.S_CONTAINER_NO = M.CONTAINER_NO " +
				   "  inner join cdef_defcell_dps cdd " +
				   "     on ood.enterprise_no = cdd.enterprise_no " +
				   "    and ood.warehouse_no = cdd.warehouse_no " +
				   "    and ood.s_cell_no = cdd.cell_no " +
				   "    and cdd.use_type='"+req.getUseType()+"' " +
				   "  where oom.enterprise_no = '"+req.getEnterpriseNo()+"' " +
				   "    and oom.warehouse_no = '"+req.getWarehouseNo()+"' " +
				   "    and oom.outstock_no = '"+outstock_no+"' and ood.status = '10' " +
				   "  order by cdd.stock_no,cdd.dps_area,m.label_no ";
		
		List<StuAreaBarcodeInfoModel> list = genDao.getListByNativeSql(sql, StuAreaBarcodeInfoModel.class);
		if(list.size() == 0)
		{
			return  new MsgRes(false,"未查询到拣货数据("+outstock_no+")！",null); 
		}
		return new MsgRes(true,"",JSONObject.toJSON(list));
	}
	
	//DPS获取当前任务所在巷道的拣货任务。
	@Override
	public MsgRes getAllTast(String strRecvData) throws Exception {
		StuReqGetPickBox req=JSON.parseObject(strRecvData,StuReqGetPickBox.class);
		
		String sql=" select cdd.ctrl_no,cdd.address,oom.outstock_no,ood.cust_no,ood.batch_no," +
				   "        ood.a_sorter_chute_no,ood.article_no,ood.packing_qty,cdd.cell_disp_length," +
				   "        cdd.cell_start_pos,cdd.dps_disp_length,ood.deliver_obj,sum(ood.article_qty) qty," +
				   "        bda.barcode,bda.qmin_operate_packing,cdd.cell_no " +
				   "   from odata_outstock_m oom " +
				   "  inner join odata_outstock_d ood " +
				   "     on oom.enterprise_no = ood.enterprise_no " +
				   "    and oom.warehouse_no = ood.warehouse_no " +
				   "    and oom.outstock_no = ood.outstock_no " +
				   "  inner join cdef_defcell_dps cdd " +
				   "     on ood.enterprise_no = cdd.enterprise_no " +
				   "    and ood.warehouse_no = cdd.warehouse_no " +
				   "    and ood.s_cell_no = cdd.cell_no " +
				   "    and cdd.use_type='"+req.getUseType()+"'  and cdd.dps_area = '"+req.getAreaNo()+"' " +
				   "    and cdd.stock_no='" +req.getStockNo()+"' " +
				   "  inner join bdef_defarticle bda " +
				   "     on bda.enterprise_no=ood.enterprise_no " +
				   "    and bda.article_no=ood.article_no  " +
				   "  inner join stock_label_m lm on ood.s_container_no = lm.container_no and ood.warehouse_no = lm.warehouse_no and ood.enterprise_no = lm.enterprise_no "+
				   "  where oom.enterprise_no = '"+req.getEnterpriseNo()+"' " +
				   "    and oom.warehouse_no = '"+req.getWarehouseNo()+"' " +
				   "    and oom.outstock_no = '"+req.getSourceNo()+"' and ood.status = '10'" +
				   		" and lm.label_no = '"+req.getBoxNo()+"' " +
				   " group by cdd.stock_no,cdd.dps_area,ood.label_no, cdd.ctrl_no,cdd.address,oom.outstock_no,ood.cust_no,ood.batch_no," +
				   "  ood.a_sorter_chute_no,ood.article_no,ood.packing_qty,cdd.cell_disp_length," +
				   "  cdd.cell_start_pos,cdd.dps_disp_length,ood.deliver_obj," +
				   "  bda.barcode,bda.qmin_operate_packing,cdd.cell_no " +
				   "  order by cdd.stock_no,cdd.dps_area,ood.label_no ";
		List<StuPickSuppBoxDetail> list = genDao.getListByNativeSql(sql, StuPickSuppBoxDetail.class);
		return new MsgRes(true,"",JSONObject.toJSON(list));
	}
	
}
