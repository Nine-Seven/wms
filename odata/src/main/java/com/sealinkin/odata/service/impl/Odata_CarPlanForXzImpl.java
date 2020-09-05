package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_LoadproposeMModel;
import com.sealinkin.odata.service.IOdata_CarPlanForXzService;
import com.sealinkin.odata.service.IOdata_CarPlanService;
import com.sealinkin.report.conf.ReportDefine;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.SpringUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Odata_CarPlanForXzImpl implements IOdata_CarPlanForXzService {

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	//检查临时表数据是否存在
	@Override
	public List<String> labelTmpCheck(String strEnterpriseNo,
			String strWarehouseNo, String strQuery) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql = "select tmp.* from odata_loadcar_tmp tmp "
				+ "where tmp.warehouse_no='" + strWarehouseNo + "' "
				+ "and tmp.enterprise_no='" + strEnterpriseNo + "' "
				+ "and tmp.TMP_ID='" + ip + "' ";

	    if(strQuery!=null && !strQuery.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(strQuery);
		    strSql=strSql+strWs;
	    }		
		List list = genDao.getListByNativeSql(strSql);
		return (List<String>) list;
	}

	/**
	 * 删除临时表中的数据
	 */
	@Override
	public MsgRes deleteTmp(String enterpriseNo, String strWarehouseNo)
			throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql = "delete odata_loadcar_tmp where " + "tmp_id='" + ip
				+ "' " + "and warehouse_no='" + strWarehouseNo + "' "
				+ "and enterprise_no='" + enterpriseNo + "' ";
		genDao.updateBySql(strSql);
		return new MsgRes(true, "", "");
	}

	/**
	 * 获取勾选单据的品项明细
	 */
	@Override
	public List<Stock_LabelMModel> getItems(String enterpriseNo,
			String strWarehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql = "select  count(distinct sld.deliver_obj) as stItems,"
				+ "count(distinct sld.article_no) articleItems,"
				+ "count(distinct sld.container_no) boxQty,"
				+ "round(nvl(sum((bda.unit_volumn + (sld.qty - 1) * bda.cumulative_volumn)/1000000), 0),6) as volumn,"
				+ "round(nvl(sum((bda.unit_weight * sld.qty)/1000000), 0),6) as weight "
				+ "from stock_label_d sld, bdef_defarticle bda "
				+ "where sld.container_no "
				+ "in (select slm.container_no from stock_label_m slm,odata_loadcar_tmp tmp "
				+ "      where slm.enterprise_no=tmp.enterprise_no "
				+ "       and slm.owner_container_no=tmp.container_no "
                + "       and tmp.enterprise_no=sld.enterprise_no "
				+ "       and tmp.warehouse_no=sld.warehouse_no"
				+ "       and tmp.tmp_id='" + ip + "'" + "    ) "
				+ "and sld.article_no = bda.article_no "
				+ "and sld.enterprise_no = bda.enterprise_no "
				+ "and sld.enterprise_no ='" + enterpriseNo + "' "
				+ "and sld.warehouse_no='" + strWarehouseNo + "' ";
		List<Stock_LabelMModel> list = genDao.getListByNativeSql(strSql,
				Stock_LabelMModel.class);
		return list;
	}

	/**
	 * 查看明细
	 */
	@Override
	public ExtListDataBo<Stock_LabelDModel> searchDetail(String enterpriseNo,
			String strWarehouseNo, String strFlag, String strWheresql)
			throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql = "";
		if (strFlag.equals("1")) {
			strSql = "select case when slm.container_no = slm.owner_container_no then slm.label_no " +
                           "else (select mm.label_no from stock_label_m mm " +
                                 "where mm.container_no = slm.owner_container_no) " +
                           "end label_no," +
                           "slm.label_no as subLabelNo," +
					       "sld.article_no,bda.article_name," +
					       "bda.owner_article_no as ownerArticleNo, " +
					       "bda.barcode,sld.packing_qty,"+ 
					       //"nvl(bap.packing_unit, decode(sld.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                           //"nvl(bap.spec, '1*' || sld.packing_qty || bda.unit) spec," +
                           "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingUnit,"+
                           "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
                           "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) Unit,"+
                           "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingSpec,"+
                           "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
                           "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) spec,"+
					       "(sld.qty/sld.packing_qty) as poBox "+ 
					  "from stock_label_d sld,stock_label_m slm," +
					       "bdef_defarticle bda,bdef_article_packing bap "+ 
					 "where sld.container_no=slm.container_no " +
					   "and sld.article_no=bda.article_no "+ 
					   "and sld.enterprise_no = slm.enterprise_no " +
					   "and sld.warehouse_no= slm.warehouse_no "+ 
					   "and slm.enterprise_no=bda.enterprise_no "+ 
					   "and sld.enterprise_no=bap.enterprise_no(+) "+ 
					   "and sld.article_no=bap.article_no(+) " +
					   "and sld.packing_qty=bap.packing_qty(+) "+ 
				       "and sld.container_no "+ 
					    "in (select slm.container_no from stock_label_m slm,odata_loadcar_tmp tmp "+ 
					        "where slm.enterprise_no=tmp.enterprise_no "+ 
					          "and slm.owner_container_no=tmp.container_no "+ 
                              "and tmp.enterprise_no=slm.enterprise_no "+ 
					          "and tmp.warehouse_no=slm.warehouse_no "+ 
					          "and tmp.tmp_id='"+ ip + "'"+ ") "+ 
					   "and slm.warehouse_no='"+ strWarehouseNo+ "' "+ 
					   "and slm.enterprise_no='" + enterpriseNo + "' ";

		}
		strSql+=" order by slm.label_no,sld.article_no ";
		List<Stock_LabelDModel> list = genDao.getListByNativeSql(strSql,
				Stock_LabelDModel.class);
		ExtListDataBo<Stock_LabelDModel> extListBo = new ExtListDataBo<Stock_LabelDModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 装车建议单
	 */
	@Override
	public MsgRes tscCloseCar(String strWheresql,String strWorkSpaceNo,
			String strQuery) throws Exception {
		Odata_LoadproposeMModel poMaster = (Odata_LoadproposeMModel) JSON
				.parseObject(strWheresql, Odata_LoadproposeMModel.class);
		List<Odata_LoadproposeMModel> str=JSON.parseArray(strQuery, Odata_LoadproposeMModel.class);

		String ip = HttpService.getIpAddr();// InetAddress.getLocalHost().getHostAddress();
		//校验能否装车
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		
		inList.add(poMaster.getEnterpriseNo());
		inList.add(poMaster.getWarehouseNo());
		inList.add(ip);
		System.out.println(inList);
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_CheckCanLoadCar");
		if(resultList.get(0).toString().indexOf("N|")!=-1){
			throw new Exception(resultList.get(0).toString());
		}
		//写建议单头档
		List inList1 = new ArrayList();
		List outList1 = new ArrayList();
		List resultList1 = new ArrayList();

		outList1.add("S");
		outList1.add("S");
		inList1.add(poMaster.getEnterpriseNo());
		inList1.add(poMaster.getWarehouseNo());
		inList1.add(poMaster.getDeliverObj());
		inList1.add(poMaster.getShipperNo());
		inList1.add(poMaster.getLineNo());
		inList1.add(poMaster.getCarNo());
		inList1.add(poMaster.getUserId());
		inList1.add(poMaster.getDockNo());
		inList1.add("N");
		inList1.add("N");
		inList1.add("N");
		inList1.add(poMaster.getLoadtype());
		System.out.println(inList1);
		resultList1 = genDao.execProc(inList1, outList1,
				"PKOBJ_ODATA_DELIVER.P_odata_loadcarHead");
		System.out.println(resultList1.get(0).toString());
		if (resultList1.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList1.get(1).toString());
		}
		
		String loadproposeNo=resultList1.get(0).toString();
		//写建议单明细
		List outList2 = new ArrayList();
		List resultList2 = new ArrayList();
		outList2.add("S");
		for (int i=0; i<str.size();i++) {
			String strSql = "select slm.label_no from stock_label_m slm " +
	                    " where slm.enterprise_no='"+poMaster.getEnterpriseNo()+"' "+
	                    "and slm.warehouse_no='"+poMaster.getWarehouseNo()+"' " +
	                   	"and slm.label_no='"+str.get(i).getLabelNo()+"'" +
	                    "and slm.status<>'A0' ";
			List list = genDao.getListByNativeSql(strSql);
			if (list.size() > 0) {
				return new MsgRes(false,str.get(i).getLabelNo()+"不是待装车状态","");
			}
			List  inList2=new ArrayList();
			
			inList2.add(poMaster.getEnterpriseNo());
			inList2.add(poMaster.getWarehouseNo());
			inList2.add(loadproposeNo);//strProposeNo
			inList2.add(str.get(i).getLabelNo());//strContainerNo 
			inList2.add(poMaster.getUserId());//strUserId     
			inList2.add(poMaster.getPaperUserId());//strPaperUserId 
			System.out.println(inList2);
			resultList2=genDao.execProc(inList2, outList2, "PKOBJ_ODATA_DELIVER.P_odata_loadcarItem");
			if(resultList2.get(0).toString().indexOf("N|")!=-1){
				throw new Exception(resultList2.get(0).toString());
			}
		}
        if(poMaster.getPrintFlag().equals("1")){
        	//打印建议单
        	List outList3=new ArrayList();
			List resultList3=new ArrayList();
			List inList3=new ArrayList();
			outList3.add("S");
			outList3.add("S");
			inList3.add(poMaster.getEnterpriseNo());//strEnterpriseNo
			inList3.add(poMaster.getWarehouseNo());//strWarehouseNo
			inList3.add(loadproposeNo);//strSourceNo
			inList3.add("0");//strBackFlag
			inList3.add(ReportDefine.RPT_OL_ZHUANGCHEJIANYIDAN);//strReportID
			inList3.add(strWorkSpaceNo);//strDockNo
			inList3.add("0");//strReprintFlag
			inList3.add(poMaster.getUserId());//strUserNo
			resultList3=genDao.execProc(inList3, outList3, "PKOBJ_PRINTTASK.p_insert_taskmaster");
			if(resultList3.get(1).toString().indexOf("N|")!=-1){
				throw new Exception(resultList3.get(1).toString());
			}
        }
		// 删除临时表数据
		deleteTmp(poMaster.getEnterpriseNo(), poMaster.getWarehouseNo());

		return new MsgRes(true, "装车建议单成功", "");
	}
	/**
	 * 整单封车
	 */
	@Override
	public MsgRes tscLoadCar(String strJsonDetail1) throws Exception {
		List<Odata_LoadproposeMModel> list = JSON.parseArray(strJsonDetail1,
				Odata_LoadproposeMModel.class);
		String strLoadProposeNo = "N";
		//String carPlate = "N";
	/*	String sql2=" select a.car_plate from bdef_defcar a " +
				   "  where a.enterprise_no='"+list.get(0).getEnterpriseNo()+"' " +
				   "  and a.warehouse_no='"+list.get(0).getWarehouseNo()+"' " +
				   "  and a.car_no ='"+list.get(0).getCarNo()+"' " ;
		
		List list1 = genDao.getListByNativeSql(sql2);
		
		if(list1.get(0) ==null ){
			return new MsgRes(false,"所选车辆代码没有车牌号，请先维护车牌号！","");
		}
		carPlate=list1.get(0).toString();*/
		//更新建议单明细配送物流箱数
		for (int j = 0; j < list.size(); j++) {
			String sql1="update odata_loadpropose_d old set old.box_num='"+list.get(j).getDeliverBox()+"' " +
					   " where old.enterprise_no='"+list.get(j).getEnterpriseNo()+"' "+
	                   "and old.warehouse_no='"+list.get(j).getWarehouseNo()+"' " +
	                   "and old.loadpropose_no='"+list.get(j).getLoadproposeNo()+"' " +
	                   "and old.container_no=(select slm.container_no from stock_label_m slm " +
	                    " where slm.enterprise_no='"+list.get(j).getEnterpriseNo()+"' "+
	                    "and slm.warehouse_no='"+list.get(j).getWarehouseNo()+"' " +
	                   	"and slm.label_no='"+list.get(j).getLabelNo()+"') ";

			this.genDao.updateBySql(sql1);
		}
		//封车
		for (int i = 0; i < list.size(); i++) {
			if (!strLoadProposeNo.equals(list.get(i).getLoadproposeNo())) {
				strLoadProposeNo = list.get(i).getLoadproposeNo();
				String sql3=" select a.cust_no,a.deliver_obj from odata_loadpropose_d a " +
						   "  where a.enterprise_no='"+list.get(i).getEnterpriseNo()+"' " +
						   "  and a.warehouse_no='"+list.get(i).getWarehouseNo()+"' " +
						   "  and a.loadPropose_no ='"+list.get(i).getLoadproposeNo()+"'" +
						   "  group by a.cust_no,a.deliver_obj  " ;
				List<Odata_LoadproposeMModel> list2 = genDao.getListByNativeSql(sql3,Odata_LoadproposeMModel.class);
				List outList = new ArrayList();
				List resultList = new ArrayList();
				outList.add("S");
				for(int k = 0; k < list2.size(); k++){
					List inList = new ArrayList();
					inList.add(list.get(i).getEnterpriseNo());
					inList.add(list.get(i).getWarehouseNo());
					inList.add(list.get(i).getLoadproposeNo());	
					inList.add(list2.get(k).getCustNo());
					inList.add(list2.get(k).getDeliverObj());
					inList.add(list.get(i).getUserId());
					System.out.println(inList);
					resultList = genDao.execProc(inList, outList,
							"PKLG_ODATA_DELIVER.P_CloseCust");
					System.out.println(resultList);
					if (resultList.get(0).toString().indexOf("N|") != -1) {
						throw new Exception(resultList.get(0).toString());
					}
				}
				tscPOdataDeliver(list.get(i).getEnterpriseNo(), list.get(i)
						.getWarehouseNo(), list.get(i).getLoadproposeNo(),list.get(0).getCarNo(), list
						.get(i).getUserId(), list.get(i).getPaperUserId());
			}
		}
		return new MsgRes(true, "封车成功", "");
	}
	@Override
	public MsgRes tscPOdataDeliver(String strEnterpriseNo,
			String strWareHouseNo, String strProposeNo, String strCarPlate,
			String strUserId,String strPaperUserId) throws Exception {
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strCarPlate);
		inList.add(strProposeNo);
		inList.add(strPaperUserId);
		inList.add(strUserId);
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_S_CloseCar");
		System.out.println(resultList);
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "封车成功", resultList.get(0).toString());
	}

	@Override
	public List<ComboxBo> getCarNoQuery(String strEnterpriseNo,
			String strWarehouseNo, String queryStr) throws Exception {
		String strSql="select distinct a.car_no value,car_name text," +	
				"'['|| ltrim(a.car_no)||']'||a.car_plate dropValue " +
				"from bdef_defcar a " +
				"where 1=1  "+
				"and a.enterprise_no ='"+strEnterpriseNo+"' " +
				"and a.warehouse_no ='"+strWarehouseNo+"' " +
				"and a.status = '1' ";
		if(queryStr!=null && !queryStr.equals(""))
	    {
		    String strWs=CommUtil.covtCollectionToWhereSql(queryStr);
		    strSql=strSql+strWs;
	    }	
	List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
	return (List<ComboxBo>) list;
    }
	
	/**
	 * 根据车辆获取司机
	 */
	@Override
	public MsgRes getDriverName(String enterpriseNo, String strWarehouseNo,
			String strQuery) throws Exception {
		String sql = "select t.driver_worker as workerNo,a.worker_name " +
				"from bdef_defcar t,bdef_defworker a  " +
				"where t.enterprise_no=a.enterprise_no and t.warehouse_no=a.warehouse_no and t.driver_worker=a.worker_no " +
				"and t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+strWarehouseNo+"' " +
				"and t.car_no='"+strQuery+"'";
		List<Bdef_DefWorkerModel> name = genDao.getListByNativeSql(sql,Bdef_DefWorkerModel.class);
		
		if(name.size()==0){
			return new MsgRes(false,"","");
		}
		return new MsgRes(true,"",name);
	}
}