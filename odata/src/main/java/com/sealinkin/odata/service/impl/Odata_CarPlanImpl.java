package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.bdef.model.Bdef_DefCustModel;
import com.sealinkin.bdef.model.Bdef_DefShipperModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.constant.CLabelStatus;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_LoadproposeMModel;
import com.sealinkin.odata.service.IOdata_CarPlanService;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.protocolExchange.comm.CommSingleDataAnswerModel;
import com.sealinkin.protocolExchange.odata.AnsOMDeliverScanCustTTH;
import com.sealinkin.protocolExchange.odata.LabalModel;
import com.sealinkin.protocolExchange.odata.ODataLoadInsertMasterModel;
import com.sealinkin.protocolExchange.odata.OdataCarWorkerModel;
import com.sealinkin.protocolExchange.odata.OdataDeliverGetLineInfoModel;
import com.sealinkin.protocolExchange.odata.OdataDeliverParameterModel;
import com.sealinkin.protocolExchange.odata.stuCollectGoods;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.StringUtil;
import com.sealinkin.wms.model.Wms_DefbaseModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Odata_CarPlanImpl implements IOdata_CarPlanService {

	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	/**
	 * 填充线路下拉控件
	 */
	@Override
	public List<ComboxBo> queryLineNoCombo(String enterpriseNo,
			String strWarehouseNo, String strOwnerNo, String strFlag)
			throws Exception {
		String strSql = "";
		if (strFlag.equals("ST")) {
			strSql = "select distinct t1.line_no value,t1.line_no text,t1.line_no dropValue "
					+ "from stock_label_m t1 "
					+ "where t1.use_type='1' "
					+ "and t1.status='"
					+ CLabelStatus.WAIT_LOAD_CAR
					+ "' and t1.warehouse_no='"
					+ strWarehouseNo
					+ "' and t1.enterprise_no='" + enterpriseNo + "' ";
		} else if (strFlag.equals("OL")) {
			strSql = "select distinct t1.line_no value,t1.line_no text,"
					+ "t1.line_no dropValue " + "from odata_loadpropose_m t1 "
					+ "where t1.status='10' " + "  and t1.warehouse_no='"
					+ strWarehouseNo + "' and t1.enterprise_no='"
					+ enterpriseNo + "' ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 填充承运商下拉控件
	 */
	@Override
	public List<ComboxBo> queryShipperNoCombo(String enterpriseNo,
			String strWarehouseNo, String strFlag) throws Exception {
		String strSql = "";
		if (strFlag.equals("ST")) {
			strSql = "select distinct t2.shipper_no as value, t2.shipper_no text, t2.shipper_no dropValue "
					+ "from stock_label_m t1,oset_shipper_line t2 "
					+ "where t1.line_no=t2.line_no "
					+ "and t1.warehouse_no='"
					+ strWarehouseNo
					+ "' "
					+ "and t2.warehouse_no='"
					+ strWarehouseNo
					+ "' "
					+ "and t1.enterprise_no='"
					+ enterpriseNo
					+ "' "
					+ "and t2.enterprise_no='"
					+ enterpriseNo + "' ";
		} else if (strFlag.equals("OL")) {
			strSql = "select distinct olm.shipper_no as value,olm.shipper_no as text,olm.shipper_no dropValue "
					+ "from odata_loadpropose_m olm "
					+ "where olm.warehouse_no='"
					+ strWarehouseNo
					+ "'  and olm.enterprise_no='" + enterpriseNo + "' ";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 获取标签下的承运商下拉
	 */
	@Override
	public List<ComboxBo> queryLabelShipperLineNo(String enterpriseNo,
			String strWarehouseNo, String strShipperNo) throws Exception {
		String strSql = "select distinct t1.line_no as value, t1.line_no text, t1.line_no dropValue "
				+ "from stock_label_m t1,oset_shipper_line t2 "
				+ "where t1.line_no=t2.line_no "
				+ "and t1.warehouse_no='"
				+ strWarehouseNo
				+ "' "
				+ "and t2.warehouse_no='"
				+ strWarehouseNo
				+ "' "
				+ "and t1.enterprise_no='"
				+ enterpriseNo
				+ "' "
				+ "and t2.enterprise_no='"
				+ enterpriseNo
				+ "' " + "and t2.shipper_no='" + strShipperNo + "'";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 获取派车单下拉
	 */
	@Override
	public List<ComboxBo> queryCarPlanCombo(String enterpriseNo,
			String strWarehouseNo) throws Exception {
		String strSql = "select distinct e.car_plan_no as value, e.car_plan_no text, e.car_plan_no dropValue  "
				+ "from odata_carplan_volume e "
				+ "where e.status<'13' "
				+ "and e.warehouse_no='"
				+ strWarehouseNo
				+ "' "
				+ "and e.enterprise_no='" + enterpriseNo + "' ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 根据派车单获取配送对象
	 */
	@Override
	public ExtListDataBo<Odata_LoadproposeMModel> queryDeliverObj(
			String enterpriseNo, String strWarehouseNo, String strDeliverObj)
			throws Exception {
		String strSql = "select e.car_plan_no,e.deliver_obj,e.load_order "
				+ "from odata_carplan_volume e " + "where e.car_plan_no='"
				+ strDeliverObj + "' " + "and e.warehouse_no='"
				+ strWarehouseNo + "' " + "and e.enterprise_no='"
				+ enterpriseNo + "' ";
		;
		List<Odata_LoadproposeMModel> list = genDao.getListByNativeSql(strSql,
				Odata_LoadproposeMModel.class);
		ExtListDataBo<Odata_LoadproposeMModel> extListBo = new ExtListDataBo<Odata_LoadproposeMModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 获取标签头档
	 */
	@Override
	public ExtListDataBo<Stock_LabelMModel> queryStockLabelM(
			String enterpriseNo, String strWarehouseNo, String strLineNo,
			String strShipperNo, String strDeliverObj) throws Exception {
		String strSql = "select slm.*,bdc.cust_name "
				+ "from stock_label_m slm,bdef_defcust bdc "
				+ "where slm.cust_no=bdc.cust_no "
				+ "and slm.enterprise_no=bdc.enterprise_no "
				+ "and slm.enterprise_no='" + enterpriseNo + "' "
				+ "and slm.warehouse_no ='" + strWarehouseNo + "' " 
				+ "and slm.hm_manual_flag='0' "
				+ "and slm.status='" + CLabelStatus.WAIT_LOAD_CAR + "' %1 ";
		if (strLineNo != null && !strLineNo.equals("")) {
			String ft = CommUtil.covtCollectionToWhereSql(strLineNo);
			strSql = strSql + ft;
		}

		if (strShipperNo != null && !strShipperNo.equals("")) {
			strSql = strSql.replace("%1",
					"and slm.line_no in (select line_no from oset_shipper_line where shipper_no='"
							+ strShipperNo + "')");
		} else {
			strSql = strSql.replace("%1", "");
		}

		if (strDeliverObj != null && !strDeliverObj.equals("")) {
			String ft = CommUtil.covtCollectionToWhereSql(strDeliverObj);
			strSql = strSql + ft;
		}
		List<Stock_LabelMModel> list = genDao.getListByNativeSql(strSql,
				Stock_LabelMModel.class);
		ExtListDataBo<Stock_LabelMModel> extListBo = new ExtListDataBo<Stock_LabelMModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 获取临时表的数据
	 */
	@Override
	public ExtListDataBo<Stock_LabelMModel> queryStockLabelTmp(
			String enterpriseNo, String strWarehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();
		String strSql = "select slm.*,bdc.cust_name "
				+ "from stock_label_m slm," + "odata_loadcar_tmp tmp,"
				+ "bdef_defcust bdc "
				+ "where slm.container_no=tmp.container_no  "
				+ "and slm.warehouse_no=tmp.warehouse_no "
				+ "and slm.enterprise_no=tmp.enterprise_no "
				+ "and tmp.warehouse_no='" + strWarehouseNo + "' "
				+ "and tmp.enterprise_no='" + enterpriseNo + "' "
				+ "and tmp.TMP_ID='" + ip + "' "
				+ "and slm.enterprise_no=bdc.enterprise_no "
				+ "and slm.cust_no=bdc.cust_no";
		strSql+=" order by slm.label_no ";
		List<Stock_LabelMModel> list = genDao.getListByNativeSql(strSql,
				Stock_LabelMModel.class);
		ExtListDataBo<Stock_LabelMModel> extListBo = new ExtListDataBo<Stock_LabelMModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 把勾选中的单据保存进临时表
	 */
	@Override
	public MsgRes saveStockTmp(String enterpriseNo, String strWarehouseNo,
			String strWheresql) throws Exception {
		String str[] = strWheresql.split(",");
		String ip = HttpService.getIpAddr();// InetAddress.getLocalHost().getHostAddress();
		String strSql = "insert into odata_loadcar_tmp "
				+ "select '"
				+ ip
				+ "',slm.warehouse_no,slm.label_no,slm.container_no,slm.enterprise_no from stock_label_m slm "
				+ "where slm.warehouse_no='" + strWarehouseNo + "' "
				+ "and slm.enterprise_no='" + enterpriseNo + "' "
				+ "and slm.label_no='" + str[0].trim() + "' "
				+ "and slm.container_no='" + str[1].trim() + "' ";
		genDao.updateBySql(strSql);
		return new MsgRes(true, "", "");
	}

	/**
	 * 把取消勾选的单据删除
	 */
	@Override
	public MsgRes deleteStockTmp(String enterpriseNo, String strWarehouseNo,
			String strWheresql) throws Exception {
		String str[] = strWheresql.split(",");
		String ip = HttpService.getIpAddr();// InetAddress.getLocalHost().getHostAddress();
		String strSql = "delete odata_loadcar_tmp tmp "
				+ "where tmp.warehouse_no='" + strWarehouseNo
				+ "' and tmp.label_no='" + str[0].trim() + "' "
				+ "and tmp.container_no='" + str[1].trim() + "' "
				+ "and tmp.enterprise_no='" + enterpriseNo + "' "
				+ "and tmp.tmp_id='" + ip + "'";
		genDao.updateBySql(strSql);
		return new MsgRes(true, "", "");
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
		String strSql = "select nvl(max(rownum), 0) as stItems,"
				+ "count(distinct sld.article_no) articleItems,"
				+ "nvl(sum(sld.qty / sld.packing_qty), 0) boxQty,"
				+ "nvl(sum((bda.unit_volumn + (sld.qty - 1) * bda.cumulative_volumn)/1000), 0) as volumn,"
				+ "nvl(sum(bda.unit_weight * sld.qty), 0) as weight "
				+ "from stock_label_d sld, bdef_defarticle bda "
				+ "where sld.container_no "
				+ "in (select container_no from odata_loadcar_tmp tmp "
				+ "      where tmp.enterprise_no=sld.enterprise_no "
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
			strSql = "select slm.label_no,sld.article_no,bda.article_name,bda.owner_article_no as ownerArticleNo, bda.barcode,sld.packing_qty,"+
					//"nvl(bap.packing_unit, decode(sld.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                    //"nvl(bap.spec, '1*' || sld.packing_qty || bda.unit) spec," +
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingUnit,"+
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) Unit,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingSpec,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) spec,"
					+ "(sld.qty/sld.packing_qty) as poBox "
					+ "from stock_label_d sld,stock_label_m slm,bdef_defarticle bda,"
					+ "bdef_article_packing bap "
					+ "where sld.container_no=slm.container_no and sld.article_no=bda.article_no "
					+ "and sld.enterprise_no = slm.enterprise_no and sld.warehouse_no= slm.warehouse_no "
					+ "and slm.enterprise_no=bda.enterprise_no "
					+ "and sld.enterprise_no=bap.enterprise_no(+) "
					+ "and sld.article_no=bap.article_no(+) and sld.packing_qty=bap.packing_qty(+) "
					+ "and sld.container_no "
					+ "in (select container_no from odata_loadcar_tmp tmp "
					+ "      where tmp.enterprise_no=slm.enterprise_no "
					+ "       and tmp.warehouse_no=slm.warehouse_no"
					+ "       and tmp.tmp_id='"
					+ ip
					+ "'"
					+ ") "
					+ "and slm.warehouse_no='"
					+ strWarehouseNo
					+ "' "
					+ "and slm.enterprise_no='" + enterpriseNo + "' ";

		} else if (strFlag.equals("2")) {
			strSql = "select slm.label_no,sld.article_no,bda.article_name,bda.barcode,sld.packing_qty,"+
					//"nvl(bap.packing_unit, decode(sld.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
                    //"nvl(bap.spec, '1*' || sld.packing_qty || bda.unit) spec,"
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingUnit,"+
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
                    "f_get_packingunit(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) Unit,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,sld.packing_qty) packingSpec,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
                    "f_get_spec(sld.enterprise_no,sld.owner_no,sld.article_no,bda.unit_packing) spec,"
					+ "(sld.qty/sld.packing_qty) as poBox,"
					+ "from stock_label_d sld,stock_label_m slm,bdef_defarticle bda,"
					+ "bdef_article_packing bap "
					+ "where sld.container_no=slm.container_no and sld.article_no=bda.article_no "
					+ "and sld.enterprise_no=slm.enterprise_no and sld.warehouse_no= slm.warehouse_no "
					+ "and sld.enterprise_no=bda.enterprise_no "
					+ "and sld.enterprise_no=bap.enterprise_no(+) "
					+ "and sld.article_no=bap.article_no(+) and sld.packing_qty=bap.packing_qty(+) "
					+ "and sld.container_no "
					+ "in ("
					+ strWheresql
					+ ") "
					+ "and slm.warehouse_no='"
					+ strWarehouseNo
					+ "' "
					+ "and slm.enterprise_no='" + enterpriseNo + "' ";
		}
		List<Stock_LabelDModel> list = genDao.getListByNativeSql(strSql,
				Stock_LabelDModel.class);
		ExtListDataBo<Stock_LabelDModel> extListBo = new ExtListDataBo<Stock_LabelDModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 装车建议单(封车)
	 */
	@Override
	public MsgRes tscCloseCar(String enterpriseNo, String strWheresql,
			String strWarehouseNo, String strWorkSpaceNo) throws Exception {
		Odata_LoadproposeMModel poMaster = (Odata_LoadproposeMModel) JSON
				.parseObject(strWheresql, Odata_LoadproposeMModel.class);
		String ip = HttpService.getIpAddr();// InetAddress.getLocalHost().getHostAddress();
		List outList = new ArrayList();
		outList.add("S");
		outList.add("S");
		List resultList = new ArrayList();
		List inList = new ArrayList();
		inList.add(poMaster.getEnterpriseNo());
		inList.add(poMaster.getWarehouseNo());// strWareHouseNo
		inList.add(poMaster.getDeliverObj());// strDeliverObj
		inList.add(poMaster.getShipperNo());// strShipperNo
		inList.add(poMaster.getLineNo());// strLineNo
		inList.add(poMaster.getCarNo());// strCarNo
		inList.add(poMaster.getUserId());// strUserId
		inList.add(poMaster.getDockNo());// strDockNo
		inList.add("N");// strDivideTrunk
		inList.add("N");// strcarPlanNo
		inList.add("N");// strSealNo
		inList.add(poMaster.getLoadtype());// strLoadType
		inList.add(poMaster.getPaperUserId());// strPaperUserId
		inList.add(ip);// tmpID
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_odata_CloseCar");
		System.out.println(resultList);
		if (resultList.get(1).toString().indexOf("Y") == -1) {
			throw new Exception(resultList.get(1).toString());
		}
		// 根据工作站获取打印机组
		/*
		 * 于20141029注释 String strSql="select a.printer_group_no " +
		 * "from PNTSET_PRINTER_WORKSTATION a " + "where  " +
		 * "a.warehouse_no= '"+strWarehouseNo+"' " +
		 * "and a.workstation_no='"+strWorkSpaceNo+"'"; List<String>
		 * list=genDao.getListByNativeSql(strSql);
		 * 
		 * //插入打印任务表 strSql="insert into job_printtask_m(" +
		 * "warehouse_no, task_no, source_no, back_flag, task_type, " +
		 * "report_id, printer_group_no, operate_date, reprint_flag, rgst_name, rgst_date) "
		 * + "values('"+strWarehouseNo+"','"+resultList.get(0).toString()+"','"+
		 * resultList.get(0).toString()+"','0','L'," +
		 * "'OL0001','"+list.get(0).toString()+"',sysdate,0,'admin',sysdate)";
		 * genDao.updateBySql(strSql);
		 */

		// 删除临时表数据
		deleteTmp(poMaster.getEnterpriseNo(), poMaster.getWarehouseNo());

		return new MsgRes(true, "装车建议单成功", "");
	}

	/**
	 * 获取建议单号
	 */
	@Override
	public List<ComboxBo> queryLoadproposeNo(String enterpriseNo,
			String strWarehouseNo) throws Exception {
		String strSql = "select distinct " + "olm.loadpropose_no as value,"
				+ "olm.loadpropose_no as text,"
				+ "olm.loadpropose_no as dropValue " + "from "
				+ "odata_loadpropose_m olm " + "where " + "olm.warehouse_no='"
				+ strWarehouseNo + "' " + "and olm.enterprise_no='"
				+ enterpriseNo + "' " + "and olm.status=10 ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 获取建议单头档
	 */
	@Override
	public ExtListDataBo<Odata_LoadproposeMModel> queryLoadProposeM(
			String enterpriseNo, String strWarehouseNo, String strLineNo,
			String strLoadproposeNo) throws Exception {
		String strSql = "select distinct " + "olm.loadpropose_no,"
				+ "slm.label_no," + "slm.line_no," + "slm.curr_area,"
				+ "slm.cust_no," + "bdc.cust_name " + "from "
				+ "odata_loadpropose_m olm," + "odata_loadpropose_d old,"
				+ "stock_label_m slm," + "bdef_defcust bdc " + "where "
				+ "olm.enterprise_no=old.enterprise_no "
				+ "and olm.warehouse_no=old.warehouse_no "
				+ "and olm.loadpropose_no=old.loadpropose_no "
				+ "and olm.enterprise_no=slm.enterprise_no "
				+ "and olm.warehouse_no=slm.warehouse_no "
				+ "and old.container_no=slm.container_no "
				+ "and slm.enterprise_no=bdc.enterprise_no(+) "
				+ "and slm.cust_no=bdc.cust_no(+) " + "and olm.warehouse_no='"
				+ strWarehouseNo + "' " + "and olm.enterprise_no='"
				+ enterpriseNo + "' " + "and olm.status='10' " + "%S1 %S2";
		if (strLineNo != null && !strLineNo.equals("")) {
			String ft = CommUtil.covtCollectionToWhereSql(strLineNo);
			strSql = strSql.replace("%S1", ft);
		} else {
			strSql = strSql.replace("%S1", "");
		}

		if (strLoadproposeNo != null && !strLoadproposeNo.equals("")) {
			String ft = CommUtil.covtCollectionToWhereSql(strLoadproposeNo);
			strSql = strSql.replace("%S2", ft);
		} else {
			strSql = strSql.replace("%S2", "");
		}
		List<Odata_LoadproposeMModel> list = genDao.getListByNativeSql(strSql,
				Odata_LoadproposeMModel.class);
		ExtListDataBo<Odata_LoadproposeMModel> extListBo = new ExtListDataBo<Odata_LoadproposeMModel>(
				list, 0);
		return extListBo;
	}

	/**
	 * 获取承运商下的线路
	 */
	@Override
	public List<ComboxBo> queryLoadproposeLineCombo(String enterpriseNo,
			String strWarehouseNo, String strShipperNo) throws Exception {
		String strSql = "select distinct " + "olm.line_no as value,"
				+ "olm.line_no as text," + "olm.line_no as dropValue "
				+ "from " + "odata_loadpropose_m olm " + "where "
				+ "olm.warehouse_no='" + strWarehouseNo
				+ "' and olm.shipper_no='" + strShipperNo
				+ "' and olm.enterprise_no='" + enterpriseNo + "' ";
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 获取车辆下拉
	 */
	@Override
	public List<ComboxBo> queryCarCombo(String enterpriseNo,
			String strWarehouseNo, String strWheresql) throws Exception {
		String strSql = "";
		strSql = "select " + "a.car_no as value," + "a.car_name as text,"
				+ "'['|| ltrim(a.car_no)||']'||a.car_name dropValue " + "from "
				+ "bdef_defcar a " + "where " + "a.warehouse_no='"
				+ strWarehouseNo + "' and a.enterprise_no='" + enterpriseNo
				+ "' ";
		if (strWheresql != null && !strWheresql.equals("")) {
			strSql += " and a.car_no like '%" + strWheresql + "%'";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 整单封车
	 */
	@Override
	public MsgRes tscLoadCar(String strJsonDetail1) throws Exception {
		List<Odata_LoadproposeMModel> list = JSON.parseArray(strJsonDetail1,
				Odata_LoadproposeMModel.class);
		String strLoadProposeNo = "N";
		for (int i = 0; i < list.size(); i++) {
			if (!strLoadProposeNo.equals(list.get(i).getLoadproposeNo())) {
				strLoadProposeNo = list.get(i).getLoadproposeNo();
				tscPOdataDeliver(list.get(i).getEnterpriseNo(), list.get(i)
						.getWarehouseNo(), list.get(i).getLoadproposeNo(), list
						.get(i).getUserId(), list.get(i).getPaperUserId());
			}
		}
		return new MsgRes(true, "封车成功", "");
	}

	@Override
	public MsgRes tscPInsertOdataLoadproposeM(String strEnterpriseNo,
			String strWareHouseNo, String strDeliverObj, String strShipperNo,
			String strLineNo, String strCarNo, String strUserId,
			String strDockNo, String strDivideTrunk, String strcarPlanNo,
			String strSealNo, String strLoadType, String strPaperUserId)
			throws Exception {
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strDeliverObj);
		inList.add(strShipperNo);
		inList.add(strLineNo);
		inList.add(strCarNo);
		inList.add(strUserId);
		inList.add(strDockNo);
		inList.add(strDivideTrunk);
		inList.add(strcarPlanNo);
		inList.add(strSealNo);
		inList.add(strLoadType);
		resultList = genDao.execProc(inList, outList,
				"PKOBJ_ODATA_DELIVER.P_odata_loadcarHead");
		System.out.println(inList);
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true, "操作成功", resultList.get(0).toString());
	}

	@Override
	public MsgRes GetLoadproposeInfo(String strRecvData) throws Exception {
		// 转换请求对象
		OdataDeliverParameterModel odataDeliverModel = JSON.parseObject(
				strRecvData, OdataDeliverParameterModel.class);

		String strLabelGetCondition = "";
		String strLoadCarLevel = "";
		String strLoadObj = "";
		// 读取系统参数：是否根据容器号取装车条件1：是;0:否
		String strSql = "select * from wms_defbase a where a.colname='LabelGetCondition'";
		List<Wms_DefbaseModel> listA = genDao.getListByNativeSql(strSql,
				Wms_DefbaseModel.class);
		if (listA.size() == 0) {
			strLabelGetCondition = "0";
		} else {
			strLabelGetCondition = listA.get(0).getSdefine();
		}

		// 读取系统参数：建装车建议单的级别：1：按承运商；2：按线路；3：配送对象
		strSql = "select * from wms_defbase a where a.colname='LoadCarLevel'";
		List<Wms_DefbaseModel> listWms = genDao.getListByNativeSql(strSql,
				Wms_DefbaseModel.class);
		if (listWms.size() == 0) {
			strLoadCarLevel = "1";
		} else {
			strLoadCarLevel = listWms.get(0).getSdefine();
		}
		odataDeliverModel.setLoadCarLevel(strLoadCarLevel);

		// 按容器号取装车条件
		if ("1".equals(strLabelGetCondition)) {
			strSql = "select slm.*,osl.shipper_no "
					+ "from stock_label_m slm left join oset_shipper_line osl on slm.line_no=osl.line_no  "
					+ "where slm.label_no='" + odataDeliverModel.getCustNo()
					+ "' " + "and slm.warehouse_no='"
					+ odataDeliverModel.getWarehouseNo() + "'";

			List<Stock_LabelMModel> listLabelM = genDao.getListByNativeSql(
					strSql, Stock_LabelMModel.class);

			if (listLabelM.size() == 0) {
				return new MsgRes(false, "标签号不存在！", null);
			} else {
				if (!CLabelStatus.WAIT_LOAD_CAR.equals(listLabelM.get(0)
						.getStatus())
						&& !CLabelStatus.LOADED_IN_CAR.equals(listLabelM.get(0)
								.getStatus())) {
					return new MsgRes(false, "标签号状态不为装车状态！", null);
				}
			}

			if ("1".equals(strLoadCarLevel)) {
				strLoadObj = listLabelM.get(0).getShipperNo();
				odataDeliverModel.setShipperNo(strLoadObj);
			} else if ("2".equals(strLoadCarLevel)) {
				strLoadObj = listLabelM.get(0).getLineNo();
				odataDeliverModel.setLineNo(strLoadObj);
			} else if ("3".equals(strLoadCarLevel)) {
				strLoadObj = listLabelM.get(0).getDeliverObj();
				odataDeliverModel.setCustNo(listLabelM.get(0).getDeliverObj());
			}
		} else//
		{
			if ("1".equals(strLoadCarLevel))// 承运商
			{
				strSql = "select * from bdef_defshipper a where a.shipper_no='"
						+ odataDeliverModel.getCustNo() + "'";
				List<Bdef_DefShipperModel> list = genDao.getListByNativeSql(
						strSql, Bdef_DefShipperModel.class);
				if (list.size() == 0) {
					return new MsgRes(false, "承运商不存在！", null);
				}
				odataDeliverModel.setShipperNo(odataDeliverModel.getCustNo());
			} else if ("2".equals(strLoadCarLevel))// 线路
			{
				strSql = "select * from oset_defline a where a.line_no='"
						+ odataDeliverModel.getCustNo() + "'";
				List<Oset_DeflineModel> list = genDao.getListByNativeSql(
						strSql, Oset_DeflineModel.class);
				if (list.size() == 0) {
					return new MsgRes(false, "线路不存在！", null);
				}
				odataDeliverModel.setLabelNo(odataDeliverModel.getCustNo());
			} else if ("3".equals(strLoadCarLevel))// 客户
			{
				strSql = "select * from bdef_defcust bdc where bdc.cust_no='"
						+ odataDeliverModel.getCustNo() + "'";
				List<Bdef_DefCustModel> list = genDao.getListByNativeSql(
						strSql, Bdef_DefCustModel.class);
				if (list.size() == 0) {
					return new MsgRes(false, "客户不存在！", null);
				}
			}
			strLoadObj = odataDeliverModel.getCustNo();
		}

		strSql = "select * from odata_loadpropose_m m " + "where (CASE WHEN '"
				+ strLoadCarLevel + "' = '1' THEN m.shipper_no " + "WHEN '"
				+ strLoadCarLevel + "' = '2' THEN m.line_no "
				+ "ELSE m.cust_no END) = '" + strLoadObj + "' "
				+ "and m.status not in(13,16) " + "and rownum<2 ";
		List<OdataDeliverParameterModel> listDeliver = genDao
				.getListByNativeSql(strSql, OdataDeliverParameterModel.class);
		if (listDeliver.size() > 0) {
			return new MsgRes(true, "", JSON.toJSON(listDeliver.get(0)));
		}
		return new MsgRes(true, "", JSON.toJSON(odataDeliverModel));
	}

	@Override
	public MsgRes tscInsertLoadproposeM(
			OdataDeliverParameterModel odataDeliverModel) throws Exception {
		return null;
	}

	@Override
	public MsgRes tscPOdataLoadCarItem(String strEnterpriseNo,
			String strWareHouseNo, String strProposeNo, String strContainerNo,
			String strUserId, String strPaperUserId) throws Exception {
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");

		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strProposeNo);
		inList.add(strContainerNo);
		inList.add(strUserId);
		inList.add(strPaperUserId);
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKOBJ_ODATA_DELIVER.P_odata_loadcarItem");
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}

		String strSql = "select count(distinct CONTAINER_NO) label_count from odata_loadpropose_d d "
				+ " where d.enterprise_no='"
				+ strEnterpriseNo
				+ "'"
				+ "   and d.warehouse_no='"
				+ strWareHouseNo
				+ "'"
				+ "	and d.loadpropose_no='" + strProposeNo + "'";
		List list = genDao.getListByNativeSql(strSql);
		return new MsgRes(true, "操作成功", list.get(0).toString());
	}

	@Override
	public MsgRes tscPOdataDeliver(String strEnterpriseNo,
			String strWareHouseNo, String strProposeNo, String strUserId,
			String strPaperUserId) throws Exception {
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWareHouseNo);
		inList.add(strProposeNo);
		inList.add(strUserId);
		inList.add(strPaperUserId);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_odata_deliver");
		System.out.println(resultList);
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
		}
		return new MsgRes(true, "封车成功", resultList.get(0).toString());
	}

	/**
	 * 配送对象下拉
	 */
	@Override
	public List<ComboxBo> queryDeliverCombo(String enterpriseNo,
			String strWarehouseNo) throws Exception {
		//取系统参数判断是否单完成才装车
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(enterpriseNo);
		inList.add(strWarehouseNo);
		inList.add("N");
		inList.add("CanLoad_Flag");
		inList.add("O");
		inList.add("O_LOADCAR");
       
		resultList = genDao.execProc(inList, outList, "PKLG_WMS_BASE.p_GetBasePara");
		//判断配送对象是否按客户，如果按客户则显示客户简称
		String sql2 = "select distinct d.deliver_obj_level from wms_outorder t,wms_outlocate_strategy_d d "+
				     " where t.enterprise_no=d.enterprise_no and t.industry_flag='1' and t.locate_strategy_id=d.locate_strategy_id "+
				     " and	t.enterprise_no='"+ enterpriseNo +"' ";
		List<String> list2 = genDao.getListByNativeSql(sql2);
		String deliverObj = list2.get(0);
		String strSql = null;
		if(deliverObj.equals("0")){//按客户
			strSql = "select distinct slm.deliver_obj as value,"
					+ "t.cust_name as text,'['|| ltrim(slm.deliver_obj)||']'||t.cust_name as dropValue "
					+ "from stock_label_m slm,bdef_defcust t where slm.enterprise_no='"
					+ enterpriseNo + "' " + "and slm.warehouse_no='"
					+ strWarehouseNo + "' " + "and slm.status = '"
					+ CLabelStatus.WAIT_LOAD_CAR + "' "
					+ "and slm.hm_manual_flag='0' "
					+ "and slm.deliver_obj<>'N' " +
					" and slm.enterprise_no=t.enterprise_no and slm.deliver_obj=t.cust_no ";
		}else{//按订单
			strSql = "select distinct slm.deliver_obj as value,"
					+ "slm.deliver_obj as text," + "slm.deliver_obj as dropValue "
					+ "from stock_label_m slm " + "where slm.enterprise_no='"
					+ enterpriseNo + "' " + "and slm.warehouse_no='"
					+ strWarehouseNo + "' " + "and slm.status = '"
					+ CLabelStatus.WAIT_LOAD_CAR + "' "
					+ "and slm.hm_manual_flag='0' "
					+ "and slm.deliver_obj<>'N' ";
		}
	
		if(resultList.get(0).toString().equals("1")){
			
			strSql += " and slm.deliver_obj not in (select t.sourceexp_no " +
	                                 "from odata_exp_m t " +
	                                "where t.enterprise_no=slm.enterprise_no " +
	                                  "and t.warehouse_no=slm.warehouse_no " +
	                                  "and t.status='10' and t.wave_no=slm.wave_no) " +
					"and slm.deliver_obj not in (select t.deliver_obj " +
					                  "from odata_locate_d t " +
					                  "where t.enterprise_no=slm.enterprise_no " +
					                   "and t.warehouse_no=slm.warehouse_no " +
					                   " and t.wave_no=slm.wave_no) " +
                     "and slm.deliver_obj not in (select t.deliver_obj " +
                                       "from odata_outstock_direct t " +
                                       "where t.enterprise_no=slm.enterprise_no " +
                                       "and t.warehouse_no=slm.warehouse_no " +
                                       " and t.wave_no=slm.wave_no) " +
                     "and slm.deliver_obj not in (select t.deliver_obj " +
                                       "from odata_outstock_d t  " +
                                       "where t.enterprise_no=slm.enterprise_no " +
                                       "and t.warehouse_no=slm.warehouse_no " +
                                       " and t.wave_no=slm.wave_no) " +
                     "and slm.deliver_obj not in (select t.deliver_obj " +
                                      "from odata_divide_d t " +
                                      "where t.enterprise_no=slm.enterprise_no " +
                                      "and t.warehouse_no=slm.warehouse_no " +
                                      " and t.wave_no=slm.wave_no) "+
                     "and slm.deliver_obj not in "+
                                      "(select t.deliver_obj from stock_label_m t "+
                                      "where  t.enterprise_no = slm.enterprise_no "+
                                      "   and t.warehouse_no = slm.warehouse_no "+
                                      "  and t.owner_container_no=t.container_no "+
                                      "  and (t.status<'A0' and t.status<>'60') " +
                                      " and t.wave_no=slm.wave_no) " ;
			
		}else{
			
			strSql += "and 1=1 ";
		}
		List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	
	}

	/**
	 * 检查配送对象是否与客户一至
	 */
	@Override
	public MsgRes checkDeliverObj(String enterpriseNo, String strWarehouseNo,
			String strDeliverObj) throws Exception {
		String strSql = "select slm.deliver_obj from stock_label_m slm "
				+ "where slm.warehouse_no='" + strWarehouseNo + "' "
				+ "and slm.enterprise_no='" + enterpriseNo + "' "
				+ "and slm.status = '" + CLabelStatus.WAIT_LOAD_CAR + "' "
				+ "and slm.deliver_obj='" + strDeliverObj + "' "
				+ "and slm.cust_no='" + strDeliverObj + "'";
		List<String> list = genDao.getListByNativeSql(strSql);
		if (list.size() == 0) {
			return new MsgRes(true, "", "");
		} else {
			strSql = "select distinct sld.exp_no "
					+ "from stock_label_m slm,stock_label_d sld "
					+ "where slm.container_no=sld.container_no "
					+ "and slm.warehouse_no='" + strWarehouseNo + "' "
					+ "and slm.status='" + CLabelStatus.WAIT_LOAD_CAR + "' "
					+ "and slm.deliver_obj='" + strDeliverObj + "' "
					+ "and slm.cust_no='" + strDeliverObj + "'";
			list = genDao.getListByNativeSql(strSql);
			if(list.size()==0){
				return new MsgRes(false, "","");
			}
			return new MsgRes(false, "",list.get(0));
		}
	}

	@Override
	public MsgRes tscRfInsertLoadproposeM(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		msgRes = tscPInsertOdataLoadproposeM(
				reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(),
				reqMod.getCustNo(),
				StringUtil.isEmpty(reqMod.getShipperNo()) ? "N" : reqMod
						.getShipperNo(),//
				StringUtil.isEmpty(reqMod.getLineNo()) ? "N" : reqMod
						.getLineNo(), // strLineNo
				reqMod.getCarNo(), reqMod.getUserID(), reqMod.getDockNo(), "N",
				"N", "N",// reqMod.getSealNo(),
				reqMod.getLoadCarLevel(),// "3",
				reqMod.getUserID());
		if (msgRes.getIsSucc()) {
			reqMod.setLoadproposeNo(msgRes.getObj().toString());
			msgRes.setObj(JSON.toJSON(reqMod));
		}
		return msgRes;
	}

	@Override
	public MsgRes tscRfInsertLoadproposeD(String strRecvData) throws Exception {
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		return tscPOdataLoadCarItem(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(), reqMod.getLoadproposeNo(),
				reqMod.getLabelNo(), reqMod.getUserID(), reqMod.getUserID());
	}

	@Override
	public MsgRes tscRfSaveDeliver(String strRecvData) throws Exception {
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		return tscPOdataDeliver(reqMod.getEnterpriseNo(),
				reqMod.getWarehouseNo(), reqMod.getLoadproposeNo(),
				reqMod.getUserID(), reqMod.getUserID());
	}

	/**
	 * 天天惠获取装车建议单号，获取不到则新增装车建议单
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscGetLoadproposeM(String strRecvData) throws Exception {

		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);

		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		outList.add("S");

		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add("N");
		inList.add("N");
		inList.add("N");
		inList.add("001");
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getDockNo());
		inList.add("N");
		inList.add("N");
		inList.add("N");
		inList.add("5");
		inList.add(ReqMod.getUserID());
		// inList.add(ReqMod.getMatchNo());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CreateLoadPropose");
		// System.out.println(resultList);
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
			// return new MsgRes(false,resultList.get(1).toString(),"");
		} else {
			return new MsgRes(true, "", resultList.get(0).toString());
		}
	}

	/**
	 * 天天惠获取获校验客户/标签以及取装车客户装车信息
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes CheckCustLabelNo(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
 
		// 获取客户编码
		String strSql = "";

		strSql = "select slm.*,osl.shipper_no "
				+ "from stock_label_m slm left join oset_shipper_line osl on slm.line_no=osl.line_no  "
				+ "where (slm.label_no='" + ReqMod.getCustNo() + "' "
				+ " or slm.DELIVER_OBJ ='" + ReqMod.getCustNo() + "') "
				+ "and slm.warehouse_no='" + ReqMod.getWarehouseNo() + "'"
				+ "and slm.enterprise_no='" + ReqMod.getEnterpriseNo() + "'";

		List<Stock_LabelMModel> listLabelM = genDao.getListByNativeSql(strSql,
				Stock_LabelMModel.class);

		if (listLabelM.size() == 0) {
			return new MsgRes(false, "标签号不存在！", null);
		} else {
			if (!CLabelStatus.WAIT_LOAD_CAR.equals(listLabelM.get(0)
					.getStatus())
					&& !CLabelStatus.LOADED_IN_CAR.equals(listLabelM.get(0)
							.getStatus())) {
				//这里校验当前标签对应的父标签是否是待装车状态
				String sql1 = "SELECT L.STATUS FROM STOCK_LABEL_M L,STOCK_LABEL_M M " +
						" WHERE L.WAREHOUSE_NO = M.WAREHOUSE_NO AND L.ENTERPRISE_NO = M.ENTERPRISE_NO AND L.CONTAINER_NO = M.OWNER_CONTAINER_NO" +
						" AND M.LABEL_NO = '"+ReqMod.getCustNo()+"' AND M.WAREHOUSE_NO='" + ReqMod.getWarehouseNo() + "' AND M.ENTERPRISE_NO ='" + ReqMod.getEnterpriseNo() + "' ";
				List  list1 = genDao.getListByNativeSql(sql1);
				if (list1.size() > 0) {
					if (!CLabelStatus.WAIT_LOAD_CAR.equals(list1.get(0))
							&& !CLabelStatus.LOADED_IN_CAR.equals(list1.get(0)))
					{
						return new MsgRes(false, "标签号状态不为装车状态！", null);
					}
				}
				else{
					return new MsgRes(false, "标签号状态不为装车状态！", null);
				}
			}
		} 
		
		//0、 sl新增。。如果当前的配送对象已经由TMS下传了派车单，则不能使用当前的装车流程。
		strSql = "SELECT 1 FROM ODATA_CARPLAN_VOLUME V WHERE V.STATUS NOT IN ('13','16')" +
				" AND V.DELIVER_OBJ = '"+listLabelM.get(0).getDeliverObj()+"'";

		List list0 = genDao.getListByNativeSql(strSql);
		if (list0.size() > 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("当前标签/客户已有派车单，请按派车单装车");
			return msgRes;
		}
		
		
		// 1、判断建议单是否已封车
		strSql = "select 1 from odata_loadpropose_m a "
				+ " where a.enterprise_no='" + ReqMod.getEnterpriseNo() + "' "
				+ " and a.warehouse_no='" + ReqMod.getWarehouseNo() + "' "
				+ " and a.loadpropose_no='" + ReqMod.getLoadproposeNo() + "'"
				+ " and a.status='13'";
		List list = genDao.getListByNativeSql(strSql);
		if (list.size() > 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("数据已封车，请重新扫描");
			return msgRes;
		}
		// 2、判断当前配送对象是否已装车结束
		strSql = "select 1 from odata_loadpropose_d a "
				+ " where a.enterprise_no='" + ReqMod.getEnterpriseNo() + "' "
				+ " and a.warehouse_no='" + ReqMod.getWarehouseNo() + "' "
				+ " and a.loadpropose_no='" + ReqMod.getLoadproposeNo() + "'"
				+ " and a.DELIVER_OBJ='"+listLabelM.get(0).getDeliverObj()+"'" + " and a.status='12'";
		list = genDao.getListByNativeSql(strSql);
		if (list.size() > 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("当前扫描标签所属配送对象已扫描完成，请扫描其他客户标签！");
			return msgRes;
		}
		// 3、判断是否有其它门店装车未结束
		strSql = "select distinct a.DELIVER_OBJ,a.cust_no from odata_loadpropose_d a "
				+ " join bdef_defcust bdc on a.enterprise_no = bdc.enterprise_no and a.cust_no = bdc.cust_no "
				+ " where a.enterprise_no='"
				+ ReqMod.getEnterpriseNo()
				+ "' "
				+ " and a.warehouse_no='"
				+ ReqMod.getWarehouseNo()
				+ "' "
				+ " and a.loadpropose_no='"
				+ ReqMod.getLoadproposeNo()
				+ "'"
				+ " and a.DELIVER_OBJ<>'" + listLabelM.get(0).getDeliverObj() + "'" + " and a.status<'12'";
		list = genDao.getListByNativeSql(strSql);
		if (list.size() > 0) {

			msgRes.setIsSucc(false);
			msgRes.setMsg("AAAAA"+JSON.toJSON(list));//当前装车单下还有其他配送对象未装车完成
			return msgRes;
		}
		return GetCustLoadproposeInfo(listLabelM.get(0).getDeliverObj() , ReqMod.getLoadproposeNo(),
				ReqMod.getEnterpriseNo(), ReqMod.getWarehouseNo());
	}

	/**
	 * 天天惠装车客户扫描确认
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscCustClose(String strRecvData) throws Exception {
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadproposeNo());
		inList.add(ReqMod.getCustNo());
		inList.add(ReqMod.getDeliverObj());
		inList.add(ReqMod.getUserID());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CloseCust");
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
			// return new MsgRes(false,resultList.get(0).toString(),"");
		} else {
			return new MsgRes(true, "", "");
		}
	}

	/**
	 * 获取客户装车信息
	 * 
	 * @param CustNo  ---- DeliverObj
	 * @param LoadproposeNo
	 * @param EnterpriseNo
	 * @param WarehouseNo 
	 * @return
	 * @throws Exception
	 */
	public MsgRes GetCustLoadproposeInfo(String DeliverObj, String LoadproposeNo,
			String EnterpriseNo, String WarehouseNo) throws Exception {
		MsgRes msgRes = new MsgRes();
		String strSql = " SELECT NVL(AA.QTY,0) QTY,NVL(BB.LABELQTY,0) LABELQTY, NVL(AA.DELIVER_OBJ,BB.DELIVER_OBJ) DeliverObj,C.CUST_NO,C.CUST_NAME " +
				" FROM BDEF_DEFCUST C," +
				" (SELECT COUNT(DISTINCT SLM.LABEL_NO) QTY,SLM.CUST_NO,SLM.DELIVER_OBJ FROM  STOCK_LABEL_M SLM " +
				" WHERE  SLM.STATUS = 'A0' AND SLM.DELIVER_OBJ = '" + DeliverObj + "' " +
				" AND SLM.ENTERPRISE_NO = '"+EnterpriseNo+"' AND SLM.WAREHOUSE_NO = '"+WarehouseNo+"' " +
				" GROUP BY SLM.CUST_NO,SLM.DELIVER_OBJ ) AA, " +
				" (SELECT COUNT(DISTINCT D.CONTAINER_NO) LABELQTY,D.CUST_NO,D.DELIVER_OBJ FROM ODATA_LOADPROPOSE_D D " +
				" WHERE D.DELIVER_OBJ =  '" + DeliverObj + "' AND D.WAREHOUSE_NO = '"+WarehouseNo+"' " +
				" AND D.LOADPROPOSE_NO = '"+LoadproposeNo+"' AND D.ENTERPRISE_NO = '"+EnterpriseNo+"' " +
				" GROUP BY D.CUST_NO,D.DELIVER_OBJ) BB " +
				" WHERE C.CUST_NO = BB.CUST_NO(+)  AND C.CUST_NO = AA.CUST_NO(+) AND (AA.QTY> 0 OR BB.LABELQTY > 0) ";

		List<AnsOMDeliverScanCustTTH> list = genDao.getListByNativeSql(strSql, AnsOMDeliverScanCustTTH.class);
		if (list.size() == 0) {
            //这里取不到数据则证明数据可能已存在问题
        	msgRes.setIsSucc(false);
			msgRes.setMsg("获取装车客户标签信息失败！");
			return msgRes;
		} 
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(list));
		return msgRes;
	}

	/***
	 * 天天惠标签装车
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscCreateLoadItem(String strRecvData) throws Exception {

		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadproposeNo());
		inList.add(ReqMod.getCustNo());
		inList.add(ReqMod.getLabelNo());
		inList.add(ReqMod.getUserID());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CreateLoadItem");
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
			// return new MsgRes(false,resultList.get(0).toString(),"");
		} 
		//封车有调用 此处不需要 huangb 20160726
		/*else {
			//小嘴添加，月台试算 清理暂存区
			List inList1 = new ArrayList();
			List outList1 = new ArrayList();
			List resultList1 = new ArrayList();
			outList1.add("S");
			inList1.add(ReqMod.getEnterpriseNo());
			inList1.add(ReqMod.getWarehouseNo());
			inList1.add(ReqMod.getLabelNo());
			System.out.println(inList1);
			resultList1 = genDao.execProc(inList1, outList1, "PKLG_BUFFER_CALCULATE.P_Clear_DeliverArea_Cal");
			if (resultList1.get(0).toString().indexOf("N|") != -1) {
				throw new Exception(resultList1.get(0).toString());
			}

			return GetCustLoadproposeInfo(ReqMod.getDeliverObj(),
					ReqMod.getLoadproposeNo(), ReqMod.getEnterpriseNo(),
					ReqMod.getWarehouseNo());
		}*/
		
		return GetCustLoadproposeInfo(ReqMod.getDeliverObj(),
				ReqMod.getLoadproposeNo(), ReqMod.getEnterpriseNo(),
				ReqMod.getWarehouseNo());

	}

	/***
	 * 天天惠封车
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscCloseCarTTH(String strRecvData) throws Exception {
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadproposeNo());
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getUserID());

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CloseCar");
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
			// return new MsgRes(false,resultList.get(1).toString(),"");
		} else {
			return new MsgRes(true, "封车成功", "");
		}
	}

	/***
	 * 铁越封车
	 */
	@Override
	public MsgRes tscCloseCarP(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);

		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadproposeNo());
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getUserID());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_odata_deliver");
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());

		}
		OdataDeliverParameterModel AnsModel = new OdataDeliverParameterModel();
		AnsModel.setDeliverNo(resultList.get(0).toString());
		AnsModel.setRecust(resultList.get(1).toString());
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(AnsModel));
		return msgRes;

	}

	/***
	 * 装车确认
	 */
	@Override
	public MsgRes tscContainer(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadproposeNo());
		inList.add(ReqMod.getMatchNo());
		inList.add(ReqMod.getCustNo());
		inList.add(ReqMod.getDeliverObj());
		inList.add(ReqMod.getLabelNo());
		inList.add(ReqMod.getUserID());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_SaveLoadItem");
		if (resultList.get(3).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(3).toString());

		} else {

			AnsOMDeliverScanCustTTH AnsMod = new AnsOMDeliverScanCustTTH();
		    
			if(resultList.get(0) != null)
			{
				AnsMod.setCustNo(resultList.get(0).toString());
				AnsMod.setDeliverObj(resultList.get(1).toString());
			}
			AnsMod.setLoadtype(resultList.get(2).toString());
			AnsMod.setResurt(resultList.get(3).toString());

			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSON(AnsMod));
			return msgRes;
		}
	}

	/***
	 * 扫描码头
	 */
	@Override
	public MsgRes GETDock(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		String strSql = "select * " + "from " + "PNTSET_PRINTER_WORKSTATION a "
				+ "where " + "a.warehouse_no='" + reqMod.getWarehouseNo() + "'"
				+ "and a.enterprise_no='" + reqMod.getEnterpriseNo() + "' "
				+ "and a.workstation_no='" + reqMod.getDockNo() + "'";
		List list = genDao.getListByNativeSql(strSql);
		if (list.size() == 0) {
			msgRes.setIsSucc(false);
			msgRes.setMsg("码头不存在！");
		} else {
			msgRes.setIsSucc(true);
		}

		return msgRes;
	}

	/***
	 * 读取派车单号,并校验是否可装车的过程
	 */
	@Override
	public MsgRes tscNotRead(String strRecvData) throws Exception {

		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");

		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getMatchNo());
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getDockNo());
		inList.add("N");
		inList.add("6");
		System.out.println(inList);

		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_GetCarPlanAndCheck");
		if (resultList.get(4).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(4).toString());
		}

		/*
		 * String
		 * strsql="select  LOADPROPOSE_NO from odata_loadpropose_m a  where " +
		 * "a.CAR_PLAN_NO='"+ReqMod.getMatchNo()+"'";
		 * 
		 * List<String> list1=genDao.getListByNativeSql(strsql);
		 */
		OdataDeliverParameterModel AnsMod = new OdataDeliverParameterModel();
		if( resultList.get(1) != null){
		String StrSQL = "select  CUST_NAME from  BDEF_DEFCUST a  where "
				+ "a.CUST_NO='" + resultList.get(1).toString() + "'";

		List<String> list = genDao.getListByNativeSql(StrSQL);
		    AnsMod.setCustNo(resultList.get(1).toString());
		    AnsMod.setDeliverObj(resultList.get(2).toString());
		    AnsMod.setCustName(list.get(0));
		}
		else
		{
			AnsMod.setCustNo("");
	        AnsMod.setDeliverObj("");
	        AnsMod.setCustName("");
		}
		
		AnsMod.setLoadproposeNo(resultList.get(0).toString());
		AnsMod.setLoadtype(resultList.get(3).toString());
		AnsMod.setRecust(resultList.get(4).toString());
		// AnsMod.setLoadproposeNo(list1.get(0));
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(AnsMod));
		return msgRes;
	}

	/***
	 * 已装未装数量
	 */
	@Override
	public MsgRes GETNoweep(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);

		String strSql = "SELECT DISTINCT m.label_no labelNo FROM odata_carplan_volume v, stock_label_m m,stock_label_d d " +
				"WHERE v.warehouse_no = m.warehouse_no AND v.enterprise_no = m.enterprise_no " +
				"AND m.warehouse_no = d.warehouse_no AND m.enterprise_no = d.enterprise_no " +
				"AND m.container_no = d.container_no AND v.exp_no = d.exp_no AND v.cust_no = m.cust_no " +
				"AND v.deliver_obj = m.deliver_obj AND m.status <'A1'  AND v.car_plan_no = '"+reqMod.getMatchNo()+"'" +
				" AND m.WAREHOUSE_NO = '" + reqMod.getWarehouseNo() + "' " + 
				" AND m.ENTERPRISE_NO = '" + reqMod.getEnterpriseNo() + "' " +
			    " AND M.deliver_obj = '"+reqMod.getDeliverObj()+"'";
		List<LabalModel> list = genDao.getListByNativeSql(strSql,
				LabalModel.class);

		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(list));
		return msgRes;

	}

	// 已扫,未扫标签
	@Override
	public MsgRes GETWsweep(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel reqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		String strSql = "SELECT COUNT(0) LabelQty FROM odata_loadpropose_m m ,odata_loadpropose_d d" +
				" WHERE m.loadpropose_no = d.loadpropose_no AND m.warehouse_no = d.warehouse_no " +
				" AND m.enterprise_no = d.enterprise_no AND m.car_plan_no = '"+reqMod.getMatchNo()+"'" +
				" AND m.WAREHOUSE_NO = '" + reqMod.getWarehouseNo() + "' " + 
				" AND m.ENTERPRISE_NO = '" + reqMod.getEnterpriseNo() + "' " +
				" AND D.deliver_obj = '"+reqMod.getDeliverObj()+"'";

		List<LabalModel> list1 = genDao.getListByNativeSql(strSql, LabalModel.class);

		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(list1));
		return msgRes;

	}

	/* 按线路装车---扫描线路
	 * @param strRecvData warehouse_no enterprise_no line_no
	 * @return OdataDeliverGetLineInfoModel
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscLineScanLineNo(String strRecvData) throws Exception {
		//扫描线路  ，校验线路是否存在 OSET_DEFLINE 
		// 取出当前线路对应的客户信息 依据路顺倒序，关联标签头档
		MsgRes msgRes = new MsgRes();
		OdataDeliverGetLineInfoModel reqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		String strSql = "SELECT 1 FROM oset_defline l WHERE l.line_no = '"+reqMod.getLineNo()+"'";
		List list0 = genDao.getListByNativeSql(strSql);
		if (list0.size() > 0) {
			//线路存在
			String strSql1 = "SELECT t.warehouse_no,t.enterprise_no, t.line_no,t.line_seq_no,c.wave_no,f.buffer_name ,dc.cust_name, dc.cust_no " +
					" FROM oset_line_cust t ,stock_label_m m,odata_send_area_calculate c,oset_buffer f,bdef_defcust dc " +
					" WHERE t.warehouse_no = m.warehouse_no AND t.enterprise_no = m.enterprise_no AND t.cust_no = m.cust_no" +
					" AND m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.deliver_obj = c.deliver_obj" +
					" AND c.warehouse_no = f.warehouse_no AND c.enterprise_no = f.enterprise_no AND c.ware_no = f.ware_no" +
					" AND c.area_no = f.area_no AND c.stock_no = f.stock_no AND c.cell_no = f.cell_no" +
					" AND t.cust_no = dc.cust_no" +
					" AND t.line_no = '"+reqMod.getLineNo()+"' AND t.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND t.enterprise_no = '"+reqMod.getEnterpriseNo()+"' AND m.status = 'A0' AND ROWNUM =1" +
					" ORDER BY  t.line_seq_no DESC ";
			List<OdataDeliverGetLineInfoModel> list1 = genDao.getListByNativeSql(strSql1, OdataDeliverGetLineInfoModel.class);
			if (list1.size() == 0)
			{
	        	msgRes.setIsSucc(false);
				msgRes.setMsg("当前线路没有客户需要装车！");
				return msgRes;	
			}
			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSON(list1.get(0)));
		}
		else
		{
			//线路不存在
			msgRes.setIsSucc(false);
			msgRes.setMsg("线路不存在！");
		} 
		
		return msgRes;
	}

	/* 按线路装车---扫描客户编码/客户箱标签
	 * @param strRecvData --- warehouse_no enterprise_no line_no scanno
	 * @return 
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscLineScanCustNo(String strRecvData) throws Exception {

		MsgRes msgRes = new MsgRes();
		OdataDeliverGetLineInfoModel reqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);

		//扫描客户编码/客户标签
		//先判断当前扫描的标签所属的板号是否允许装车
		String strSql0 = "SELECT * FROM stock_label_m m1,stock_label_m m2 " +
				" WHERE m1.warehouse_no = m2.warehouse_no AND m1.enterprise_no = m2.enterprise_no AND m1.container_no = m2.owner_container_no" +
				" AND (m2.label_no = '"+reqMod.getScanNo()+"' OR m2.cust_no = '"+reqMod.getScanNo()+"') AND m2.warehouse_no='"+reqMod.getWarehouseNo()+"' AND m2.enterprise_no='"+reqMod.getEnterpriseNo()+"' AND m1.status= 'A0' ";
		List list0 = genDao.getListByNativeSql(strSql0);
		if(list0.size() == 0)
		{
        	msgRes.setIsSucc(false);
			msgRes.setMsg("当前扫描的客户或标签号不能装车！");
			return msgRes;
		}
		//校验当前扫描的箱标签或者客户编码是否属于当前的线路下
		String strSql1 ="SELECT m.cust_no FROM stock_label_m m ,oset_line_cust c " +
				" WHERE m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.cust_no = c.cust_no " +
				" AND (m.label_no = '"+reqMod.getScanNo()+"' OR m.cust_no = '"+reqMod.getScanNo()+"') AND m.warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
				" AND m.enterprise_no ='"+reqMod.getEnterpriseNo()+"' AND c.line_no ='"+reqMod.getLineNo()+"' ";
		List list1 = genDao.getListByNativeSql(strSql1);
		if(list1.size() == 0)
		{
        	msgRes.setIsSucc(false);
			msgRes.setMsg("当前扫描的客户或标签号无效或不属于当前线路！");
			return msgRes;
		}
		if(reqMod.getNewCustNo().equals(""))
		{
			//如果界面没传当前新客户信息，则表示是第一次进此方法
			if(!list1.get(0).toString().equals(reqMod.getCustNo()))
			{
				//获取当前的客户，检查是否与标签客户一致
	        	msgRes.setIsSucc(false);
	        	//当前的客户或标签号不属于建议的客户，是否确认装当前的客户
				msgRes.setMsg("L|" + list1.get(0).toString());
				return msgRes;
			}
		}
		else
		{
			if(!list1.get(0).toString().equals(reqMod.getNewCustNo()))
			{
	        	msgRes.setIsSucc(false);
	        	//当前的客户或标签号不属于建议的客户，是否确认装当前的客户
				msgRes.setMsg("N|数据传输异常，请重试");
				return msgRes;
			}
		}
		
		
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(GetDeliverScanInfo(reqMod.getEnterpriseNo(),reqMod.getWarehouseNo(),reqMod.getLineNo(),list1.get(0).toString())));
		
		return msgRes;
	}
	
	/* 公用方法，用于获取当前指定线路，客户的配送暂存区，已装未装数量
	 * */
	public OdataDeliverGetLineInfoModel GetDeliverScanInfo(String enterpriseno,String warehouseNo,String lineNo,String custNo)
	{ 
		//这里表示当前要装list1.get(0).toString() 这个客户
		//此时，获取当前客户下的已装标签数，未装标签数，路顺，暂存区编码，客户名称等信息
		//                 主标签A1      主标签A0
		String strSql2 = "SELECT t.line_no,t.line_seq_no,c.wave_no,f.buffer_name ,dc.cust_name" +
				" FROM oset_line_cust t ,stock_label_m m,odata_send_area_calculate c,oset_buffer f,bdef_defcust dc " +
				" WHERE t.warehouse_no = m.warehouse_no AND t.enterprise_no = m.enterprise_no AND t.cust_no = m.cust_no" +
				" AND m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.deliver_obj = c.deliver_obj" +
				" AND c.warehouse_no = f.warehouse_no AND c.enterprise_no = f.enterprise_no AND c.ware_no = f.ware_no" +
				" AND c.area_no = f.area_no AND c.stock_no = f.stock_no AND c.cell_no = f.cell_no" +
				" AND t.cust_no = dc.cust_no" +
				" AND t.line_no = '"+lineNo+"' AND t.warehouse_no = '"+warehouseNo+"'" +
				" AND t.enterprise_no = '"+enterpriseno+"'  AND t.cust_no = '"+custNo +"'" ;
		List<OdataDeliverGetLineInfoModel> list2 = genDao.getListByNativeSql(strSql2, OdataDeliverGetLineInfoModel.class);
		OdataDeliverGetLineInfoModel returnObj = list2.get(0);
		//取已扫板标签数量
		String strSql31 = "SELECT COUNT(m.label_no) FROM stock_label_m m " +
				" WHERE m.cust_no = '"+custNo +"' AND m.warehouse_no = '"+warehouseNo+"' AND m.enterprise_no = '"+enterpriseno+"'" +
				" AND m.status = 'A1' AND m.container_no = m.owner_container_no";
		List list31 = genDao.getListByNativeSql(strSql31);
		returnObj.setScanCount(list31.get(0).toString());
		//取未扫板标签数量
		String strSql32 = "SELECT COUNT(m.label_no) FROM stock_label_m m " +
				" WHERE m.cust_no = '"+custNo +"' AND m.warehouse_no = '"+warehouseNo+"' AND m.enterprise_no = '"+enterpriseno+"'" +
				" AND m.status = 'A0' AND m.container_no = m.owner_container_no";
		List list32 = genDao.getListByNativeSql(strSql32);
		returnObj.setWaitScanCount(list32.get(0).toString());
		return returnObj;
	}

	/* 按线路装车---扫描 暂存区编码（月台号）+输入物流箱数
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscLineScanAreaNo(String strRecvData) throws Exception {
		
		MsgRes msgRes = new MsgRes();
	    OdataDeliverGetLineInfoModel reqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);

		//校验暂存区编码是否存在
		String strSql1 = "SELECT m.label_no FROM stock_label_m m " +
				" WHERE m.label_no = '"+reqMod.getScanNo() +"' AND m.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND m.enterprise_no = '"+reqMod.getEnterpriseNo()+"'" +
				" AND m.status = 'A0' AND m.container_no = m.owner_container_no";
		List list1 = genDao.getListByNativeSql(strSql1); 
		if(list1.size() == 0)
		{
        	msgRes.setIsSucc(false);
			msgRes.setMsg("当前装车扫描的标签号无效！");
			return msgRes;
		}
		
		//校验暂存区编码是否属于当前线路、客户 
		String strSql2 ="SELECT m.cust_no FROM stock_label_m m ,oset_line_cust c " +
				" WHERE m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.cust_no = c.cust_no " +
				" AND (m.label_no = '"+reqMod.getScanNo()+"' and m.cust_no = '"+reqMod.getCustNo()+"') AND m.warehouse_no ='"+reqMod.getWarehouseNo()+"' " +
				" AND m.enterprise_no ='"+reqMod.getEnterpriseNo()+"' AND c.line_no ='"+reqMod.getLineNo()+"' ";
		List list2 = genDao.getListByNativeSql(strSql2);
		if(list2.size() == 0)
		{
        	msgRes.setIsSucc(false);
			msgRes.setMsg("当前扫描的板号无效或不属于当前线路！");
			return msgRes;
		}
		//校验暂存区编码是否属于当前波次
		String strSql3 ="SELECT * FROM stock_label_m m,odata_send_area_calculate c" +
				" WHERE m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.deliver_obj = c.deliver_obj" +
				" AND m.label_no='"+reqMod.getScanNo()+"' AND m.warehouse_no = '"+reqMod.getWarehouseNo()+"' AND  m.enterprise_no = '"+reqMod.getEnterpriseNo()+"'  AND c.wave_no='"+reqMod.getWaveNo()+"' ";
		List list3 = genDao.getListByNativeSql(strSql3);
		if(list3.size() == 0)
		{
	    	msgRes.setIsSucc(false);
			msgRes.setMsg("当前扫描的板号波次无效！");
			return msgRes;
		}

		
		//装车 
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		inList.add(reqMod.getEnterpriseNo());
		inList.add(reqMod.getWarehouseNo());
		inList.add(reqMod.getLoadProposeNo());
		inList.add(reqMod.getCustNo());
		inList.add(reqMod.getScanNo());
		inList.add(reqMod.getUserID());
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CreateLoadItem");
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		} else {
			//记录下当前的物流箱数 
			String strSql4 ="INSERT INTO odata_car_receipt_tmp VALUES ('"+reqMod.getLoadProposeNo()+"','"+reqMod.getCustNo()+"'," +
					"'"+reqMod.getBoxCount()+"','"+reqMod.getScanNo()+"','"+reqMod.getWaveNo()+"','"+reqMod.getWarehouseNo()+"','"+reqMod.getEnterpriseNo()+"')";
			 genDao.exceuteSql(strSql4);
			
			
			//小嘴添加，月台试算 清理暂存区
			List inList1 = new ArrayList();
			List outList1 = new ArrayList();
			List resultList1 = new ArrayList();
			outList1.add("S");
			inList1.add(reqMod.getEnterpriseNo());
			inList1.add(reqMod.getWarehouseNo());
			inList1.add(reqMod.getScanNo());
			System.out.println(inList1);
			resultList1 = genDao.execProc(inList1, outList1, "PKLG_BUFFER_CALCULATE.P_Clear_DeliverArea_Cal");
			if (resultList1.get(0).toString().indexOf("N|") != -1) {
				throw new Exception(resultList1.get(0).toString());
			}

			//目前这里直接返回当前客户的已扫未扫数量
			msgRes.setIsSucc(true);
			msgRes.setObj(JSON.toJSON(GetDeliverScanInfo(reqMod.getEnterpriseNo(),reqMod.getWarehouseNo(),reqMod.getLineNo(),reqMod.getCustNo())));
			
		}

		return msgRes;
	}
	
	/* 按线路装车---客户扫描完成
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscLineCustScanOver(String strRecvData) throws Exception {

		MsgRes msgRes = new MsgRes();
	    OdataDeliverGetLineInfoModel reqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
	  
		
	    //这里取出当前线路的下一个客户，如果没有客户了，则返回N
		String strSql1 = "SELECT t2.warehouse_no,t2.enterprise_no, t2.line_no,t2.line_seq_no,c.wave_no,f.buffer_name ,dc.cust_name, dc.cust_no " +
				" FROM oset_line_cust t,oset_line_cust t2 ,stock_label_m m,odata_send_area_calculate c,oset_buffer f,bdef_defcust dc " +
				" WHERE t.warehouse_no = m.warehouse_no AND t.enterprise_no = m.enterprise_no AND t.cust_no = m.cust_no" +
				" AND t.warehouse_no = t2.warehouse_no AND t.enterprise_no = t2.enterprise_no AND t.line_no = t2.line_no" +
				" AND m.warehouse_no = c.warehouse_no AND m.enterprise_no = c.enterprise_no AND m.deliver_obj = c.deliver_obj" +
				" AND c.warehouse_no = f.warehouse_no AND c.enterprise_no = f.enterprise_no AND c.ware_no = f.ware_no" +
				" AND c.area_no = f.area_no AND c.stock_no = f.stock_no AND c.cell_no = f.cell_no" +
				" AND t.cust_no = dc.cust_no AND t.line_seq_no > t2.line_seq_no" +
				" AND t.line_no = '"+reqMod.getLineNo()+"' AND t.warehouse_no = '"+reqMod.getWarehouseNo()+"' " +
				" AND t.cust_no = '"+reqMod.getCustNo()+"' AND t.enterprise_no = '"+reqMod.getEnterpriseNo()+"' AND m.status = 'A0' AND ROWNUM =1" +
				" ORDER BY  t.line_seq_no DESC ";
		List<OdataDeliverGetLineInfoModel> list1 = genDao.getListByNativeSql(strSql1, OdataDeliverGetLineInfoModel.class);
		if(list1.size() == 0)
		{
	    	msgRes.setIsSucc(false);
			msgRes.setMsg("N|当前线路已装车完毕！");
			return msgRes;
		}
		msgRes.setIsSucc(true);
		msgRes.setObj(JSON.toJSON(list1.get(0)));

		return msgRes;
	} 

	/* 按线路装车---封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscLineCloseCar(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		outList.add("S");
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add(ReqMod.getLoadProposeNo());
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getUserID());

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,
				"PKLG_ODATA_DELIVER.P_CloseCar");
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
			// return new MsgRes(false,resultList.get(1).toString(),"");
		} else {
			//封车时，需要将临时表的数据写到指定表，
			String strSql1 = "INSERT INTO odata_car_receipt(enterprise_no,warehouse_no,owner_no,wave_no,deliver_no,cust_no,deliver_box,car_no,rgst_name,rgst_date) " +
					" SELECT t.enterprise_no,t.wave_no,em.owner_no,t.wave_no,dm.deliver_no,t.cust_no,sum(t.deliver_box),'"+ReqMod.getCarNo()+"','"+ReqMod.getUserID()+"',SYSDATE " +
					" FROM odata_car_receipt_tmp t,odata_loadpropose_m m,odata_deliver_m dm,odata_exp_m em " +
					" WHERE t.warehouse_no = m.warehouse_no AND t.enterprise_no = m.enterprise_no AND t.loadpropose_no = m.loadpropose_no " +
					" AND t.warehouse_no = dm.warehouse_no AND t.enterprise_no = dm.enterprise_no AND m.loadpropose_no = dm.loadpropose_no " +
					" AND t.warehouse_no = em.warehouse_no AND t.enterprise_no = em.enterprise_no AND dm.deliver_obj = em.sourceexp_no " +
					" AND t.loadpropose_no='"+ReqMod.getLoadProposeNo()+"' AND t.warehouse_no ='"+ReqMod.getWarehouseNo()+"' AND t.enterprise_no='"+ReqMod.getEnterpriseNo()+"'" +
					" GROUP BY t.enterprise_no,t.wave_no,em.owner_no,t.wave_no,dm.deliver_no,t.cust_no";
			 genDao.exceuteSql(strSql1);
			
			 String strSql2 ="DELETE odata_car_receipt_tmp t " +
			 		" WHERE t.loadpropose_no='"+ReqMod.getLoadProposeNo()+"' AND t.warehouse_no ='"+ReqMod.getWarehouseNo()+"' AND t.enterprise_no='"+ReqMod.getEnterpriseNo()+"'";
			 genDao.exceuteSql(strSql2);
			 
			return new MsgRes(true, "封车成功", "");
		}
	} 
	
	/* 装车获取暂存区编码
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes OMGetBufferNo(String strRecvData) throws Exception {
		MsgRes msgRes = new MsgRes();
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverParameterModel.class);
		String nowNo = ReqMod.getCustNo();//这里传入的CustNo和LabelNo是一样的
		//1.先判断当前输入的数据是客户还是标签
		String strSql = "SELECT 1 FROM BDEF_DEFCUST C WHERE C.CUST_NO ='"+nowNo+"'";
		List list0 = genDao.getListByNativeSql(strSql);
		if (list0.size() > 0) {
			//这里表示输入的值是 客户编码
			//这里处理方式待定
			msgRes.setIsSucc(false);
			msgRes.setMsg("暂存区信息未获取到！");
		}
		else
		{
			//这里表示输入的值是标签     可能有些项目不算暂存区 所以要左连接 huangb 20160813
			String  strSql1 = "SELECT nvl(B.BUFFER_NAME,'N') FROM STOCK_LABEL_M M ,ODATA_SEND_AREA_CALCULATE T ,OSET_BUFFER B" +
					" WHERE M.WAREHOUSE_NO = T.WAREHOUSE_NO(+) AND M.ENTERPRISE_NO = T.ENTERPRISE_NO(+) AND M.DELIVER_OBJ = T.DELIVER_OBJ(+) " +
					" AND T.WAREHOUSE_NO = B.WAREHOUSE_NO(+) AND T.ENTERPRISE_NO = B.ENTERPRISE_NO(+) AND T.WARE_NO = B.WARE_NO(+) " +
					" AND T.AREA_NO = B.AREA_NO(+) AND T.CELL_NO = B.CELL_NO(+) AND M.LABEL_NO = '"+nowNo+"'  AND M.WAREHOUSE_NO='"+ReqMod.getWarehouseNo()+"' AND M.ENTERPRISE_NO ='"+ReqMod.getEnterpriseNo()+"' ";
			List  list1 = genDao.getListByNativeSql(strSql1);
			if (list1.size() > 0) { 
				msgRes.setIsSucc(true);
				msgRes.setObj(list1.get(0));
			}
			else
			{
				msgRes.setIsSucc(false);
				msgRes.setMsg("暂存区信息获取失败！");
			}
		}
		
		return msgRes;
	}
	
	/* 装车--原天天惠装车--封车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscSCloseCar(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S"); 
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getCarNo()); //strCarNo
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo
		inList.add(ReqMod.getUserID()); //strUserID
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_S_CloseCar");
		System.out.println(inList);
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
		return new MsgRes(true, "操作成功","");
	}

	/* 装车--改原天天惠装车--扫描客户或标签
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscSScanLabel(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S"); 
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getScanNo().toUpperCase()); //strScanNo 
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_S_ScanLabelNo");
		 
		if (resultList.get(7).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(7).toString());
		}
		 
		if (resultList.get(7).toString().indexOf("AAAAA") != -1) {
			//throw new Exception(resultList.get(7).toString());
			return new MsgRes(false, resultList.get(7).toString(),"");
		}

		ReqMod.setDeliverObj(resultList.get(0).toString());
		ReqMod.setWaveNo(resultList.get(1).toString());
		ReqMod.setBufferName(resultList.get(2).toString());
		ReqMod.setScanCount(resultList.get(3).toString());
		ReqMod.setWaitScanCount(resultList.get(4).toString());
		ReqMod.setCustNo(resultList.get(5).toString());
		ReqMod.setCustName(resultList.get(6).toString()); 
        
		return new MsgRes(true, "操作成功",JSON.toJSON(ReqMod));
	}

	/* 装车--改原天天惠装车--装车
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscSLoadCar(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		  
		outList.add("S"); 
		outList.add("S"); 
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getCustNo()); //strCustNo 
		inList.add(ReqMod.getScanNo()); //strScanNo 
		inList.add(ReqMod.getBoxCount()); //strBoxCount 
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo 
		inList.add(ReqMod.getUserID()); //strUserID 
		inList.add(ReqMod.getWaveNo()); //strWaveNo 
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_S_LoadCar");
		System.out.println(inList);
		 
		if (resultList.get(2).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(2).toString());
		}
 
		ReqMod.setScanCount(resultList.get(0).toString());
		ReqMod.setWaitScanCount(resultList.get(1).toString()); 
        
		return new MsgRes(true, "操作成功",JSON.toJSON(ReqMod));
	}
	
	/* 装车--改原天天惠装车--客户扫描确认
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscSCloseCust(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo
		inList.add(ReqMod.getCustNo()); //strCustNo   
		inList.add(ReqMod.getDeliverObj()); //strDeliverObj 
		inList.add(ReqMod.getUserID()); //strUserID 

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_CloseCust");
		 
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
  
		return new MsgRes(true, "操作成功","");
	}
	
	/**
	 * 面单交接-根据承运商编号获取装车单号 huangb 20160606
	 * 
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 */
	@Override
	public MsgRes tscGetLoadNoByShipperNo(String strRecvData) throws Exception {
		OdataDeliverParameterModel ReqMod = JSON.parseObject(strRecvData,
				OdataDeliverParameterModel.class);
		MsgRes MsgRes=new MsgRes();
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();

		outList.add("S");
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(ReqMod.getEnterpriseNo());
		inList.add(ReqMod.getWarehouseNo());
		inList.add("N");
		inList.add(ReqMod.getShipperNo());
		inList.add("N");
		inList.add("001");
		inList.add(ReqMod.getUserID());
		inList.add(ReqMod.getDockNo());
		inList.add("N");
		inList.add("N");
		inList.add("N");
		inList.add("1");

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList,"PKLG_ODATA_DELIVER.P_CreateLoadMByShipper");
		
		if(resultList.get(3).toString().indexOf("N|")!=-1)
		{
			throw new Exception(resultList.get(3).toString());
		}
		
		ODataLoadInsertMasterModel AnsMod = new ODataLoadInsertMasterModel();
		AnsMod.setLoadProposeNo(resultList.get(0).toString());//装车建议单号
		AnsMod.setShipperName(resultList.get(1).toString());//承运商名称
		AnsMod.setScanLabelCount(resultList.get(2).toString());//已扫描标签量
		
		MsgRes.setIsSucc(true); 
		MsgRes.setObj(JSON.toJSON(AnsMod));
		return MsgRes;
	}
	
	/** 面单交接-新增装车建议单明细 huangb 20160606
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscInserLoadDByShipper(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		MsgRes MsgRes=new MsgRes();
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		  
		outList.add("S"); 
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo 
		inList.add(ReqMod.getScanNo()); //strScanNo
		inList.add(ReqMod.getUserID()); //strUserID 
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_CreateLoadDByShipper");
		
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
		}
 
		//ReqMod.setScanCount(resultList.get(0).toString());
        
		MsgRes.setIsSucc(true); 
		MsgRes.setObj(resultList.get(0).toString());
		return MsgRes;
	}
	
	/** 面单交接-根据承运商封车 huangb 20160606
	 * @param strRecvData
	 * @return
	 * @throws Exception
	 * */
	@Override
	public MsgRes tscDeliverCarByShipper(String strRecvData) throws Exception {
        OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
        MsgRes MsgRes=new MsgRes();
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S"); 
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo
		inList.add(ReqMod.getUserID()); //strUserID
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_DeliverCarByShipper");
		
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
        
		MsgRes.setIsSucc(true); 
		return MsgRes;
	}
	
	/* 按波次装车--扫描客户或标签
	 * huangb 20160707
	 */
	@Override
	public MsgRes checkScanDataByWave(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");//strOutCustNo
		outList.add("S");//strOutMsg
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getScanNo().toUpperCase());//strScanNo 

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_Check_ScanDataByWaveNo");
		 
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
		}
		
		//获取界面需要显示数据
		String Sql = "";
	    Sql = " select t.cust_no," + 
	    	  "        t.cust_name," + 
		      "        t.wave_no," + 
		      "        t.deliver_area," + 
		      "        sum(t.labelNum) labelNum," + 
		      "        sum(t.c_labelNum) c_labelNum," + 
		      "        sum(t.b_labelNum) b_labelNum " + 
		      "   from (select a.cust_no, " + 
		      "                bdf.cust_name, " + 
		      "                a.wave_no, " + 
		      "                a.deliver_area, " + 
		      "                (select count(distinct slm.label_no) " + 
		      "                   from stock_label_m slm " + 
		      "                  where slm.enterprise_no = a.enterprise_no " + 
		      "                    and slm.warehouse_no = a.warehouse_no " + 
		      "                    and slm.owner_container_no = a.container_no " + 
		      "                    and slm.owner_container_no <> slm.container_no) as labelNum, " + 
		      "                (select count(distinct slm.label_no) " + 
		      "                   from stock_label_m slm " + 
		      "                  where slm.enterprise_no = a.enterprise_no " + 
		      "                    and slm.warehouse_no = a.warehouse_no " + 
              "                    and slm.owner_container_no = a.container_no " + 
              "                    and slm.container_type = 'C' " + 
              "                    and slm.owner_container_no <> slm.container_no) as c_labelNum, " + 
		      "                (select count(distinct slm.label_no) " + 
		      "                    from stock_label_m slm " + 
		      "                   where slm.enterprise_no = a.enterprise_no " + 
		      "                     and slm.warehouse_no = a.warehouse_no " + 
		      "                     and slm.owner_container_no = a.container_no " + 
		      "                     and slm.container_type <> 'C' " + 
		      "                     and slm.owner_container_no <> slm.container_no) as b_labelNum " + 
		      "            from stock_label_m a, bdef_defcust bdf " + 
		      "           where bdf.enterprise_no = a.enterprise_no " + 
		      "             and bdf.cust_no = a.cust_no " + 
		      "             and a.enterprise_no = '" + ReqMod.getEnterpriseNo() + "' " + 
		      "             and a.warehouse_no = '" + ReqMod.getWarehouseNo() + "' " + 
		      "             and a.cust_no = '" + resultList.get(0).toString() + "' " + 
		      "             and a.status = 'A0' " +
		      "             and a.hm_manual_flag = '0' " +
		      "             and not exists (select 'X' " +
		      "                    from odata_locate_d ol " +
		      "                   where ol.enterprise_no = a.enterprise_no " +
		      "                     and ol.warehouse_no = a.warehouse_no " +
		      "                     and ol.wave_no = a.wave_no" +
		      "                     and ol.cust_no = a.cust_no) " +
		      "              and not exists (select 'X' " +
		      "                     from odata_outstock_direct ood " +
		      "                    where ood.enterprise_no = a.enterprise_no " +
		      "                      and ood.warehouse_no = a.warehouse_no " +
		      "                      and ood.wave_no = a.wave_no " +
		      "                      and ood.cust_no = a.cust_no " +
		      "                      and ood.status < '13') " +
		      "             and not exists (select 'X' " +
		      "                    from odata_outstock_d ood " +
		      "                   where ood.enterprise_no = a.enterprise_no " +
   		      "                      and ood.warehouse_no = a.warehouse_no " +
   		      "                      and ood.wave_no = a.wave_no " +
   		      "                      and ood.cust_no = a.cust_no " +
   		      "                      and ood.status < '13') " +
   		      "             and not exists (select 'X' " +
   		      "                    from odata_divide_d odd " +
		      "                   where odd.enterprise_no = a.enterprise_no " +
   		      "                      and odd.warehouse_no = a.warehouse_no " +
   		      "                      and odd.wave_no = a.wave_no " +
   		      "                      and odd.cust_no = a.cust_no " +
   		      "                      and odd.status < '13') " + 
		      "             and a.wave_no not in (select b.wave_no " + 
     		  "                                     from stock_label_m b, stock_label_m c " + 
    		  "                                    where b.enterprise_no = '" + ReqMod.getEnterpriseNo() + "' " + 
    		  "                                      and b.warehouse_no = '" + ReqMod.getWarehouseNo() + "' " + 
    		  "                                      and b.cust_no = '" + resultList.get(0).toString() + "' " + 
              "                                      and C.enterprise_no = B.enterprise_no " + 
              "                                      and C.warehouse_no = B.warehouse_no " + 
              "                                      and C.container_no = B.owner_container_no " + 
              "                                      and c.status <> 'A0')) t" +
              "     group by t.cust_no, t.cust_name, t.wave_no, t.deliver_area";
		List<OdataDeliverGetLineInfoModel> list = genDao.getListByNativeSql(Sql, OdataDeliverGetLineInfoModel.class);
		if(list.size() <= 0){
			return new MsgRes(false, "当前的客户或标签没有待装车的数据","");
		}
		return new MsgRes(true, "操作成功",JSONArray.fromObject(list));
	}
	
	/* 按波次装车
	 * huangb 20160707
	 */
	@Override
	public MsgRes tscLoadCarByWaveNo(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getCustNo()); //strCustNo 
		inList.add(ReqMod.getBoxCount()); //strBoxCount 
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo 
		inList.add(ReqMod.getUserID()); //strUserID 
		inList.add(ReqMod.getWaveNo()); //strWaveNo 
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_LoadCarByWaveNo");
		System.out.println(inList);
		 
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
        
		return new MsgRes(true, "操作成功","");
	}
	
	/* 按波次装车--获取车辆或司机信息
	 * huangb 20160708
	 */
	@Override
	public MsgRes getCarInfo(String strRecvData) throws Exception {
		OdataCarWorkerModel ReqMod = JSON.parseObject(strRecvData, OdataCarWorkerModel.class);
		
		//获取界面需要显示数据
		String Sql = "";
		//获取车辆信息
		if(ReqMod.getGetDataFlag().equals("0")){
			Sql = " select bdc.car_plate, " + 
				  "        nvl(bdw.worker_no, 'N') worker_no, " + 
				  "        nvl(bdw.worker_name, 'N') worker_name, " + 
				  "        nvl(bdw.tel, 'N') tel " + 
				  "   from bdef_defcar bdc, bdef_defworker bdw " + 
				  "  where bdw.enterprise_no(+) = bdc.enterprise_no " + 
				  "    and bdw.warehouse_no(+) = bdc.warehouse_no " + 
				  "    and bdw.worker_no(+) = bdc.driver_worker and bdc.status = '1'" + 
				  "    and bdc.enterprise_no = '" + ReqMod.getEnterpriseNo() + "' " + 
				  "    and bdc.warehouse_no = '" + ReqMod.getWarehouseNo() + "' " + 
				  "    and upper(bdc.car_no) = upper('" + ReqMod.getCarNo() + "') ";
		}else{
			Sql = " select t.worker_no, " + 
				  "        t.worker_name, " + 
			      "        t.tel " + 
				  "   from bdef_defworker t " + 
				  "  where t.enterprise_no = '" + ReqMod.getEnterpriseNo() + "' " + 
				  "    and t.warehouse_no = '" + ReqMod.getWarehouseNo() + "' " + 
				  "    and upper(t.worker_no) = upper('" + ReqMod.getWorkerNo() + "') ";
		}
		
		List<OdataCarWorkerModel> list = genDao.getListByNativeSql(Sql, OdataCarWorkerModel.class);
		if(list.size() <= 0){
			if(ReqMod.getGetDataFlag() == "0"){
				return new MsgRes(false, "获取车辆类型信息失败","");
			}else{
				return new MsgRes(false, "获取司机信息失败","");
			}
		}
		return new MsgRes(true, "操作成功", JSON.toJSON(list.get(0)));
	}

	/* 按波次封车
	 * huangb 20160708
	 */
	@Override
	public MsgRes tscDeliverCarByWaveNo(String strRecvData) throws Exception {
		OdataDeliverGetLineInfoModel ReqMod = JSON.parseObject(strRecvData, OdataDeliverGetLineInfoModel.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNo());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNo()); //strWareHouseNo
		inList.add(ReqMod.getLoadProposeNo()); //strProposeNo 
		inList.add(ReqMod.getCarNo()); //strCarNo  
		inList.add(ReqMod.getDriverWorker()); //strDriverWorker 
		inList.add(ReqMod.getUserID()); //strUserID 
		
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_DeliverCarByWaveNo");
		System.out.println(inList);
		 
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
			//return new MsgRes(false, resultList.get(0).toString(),"");
		}
        
		return new MsgRes(true, "操作成功","");
	}

	/* 集货作业--扫提单号
	 * sunl 20160714
	 */
	@Override
	public MsgRes tscCollectGoods_ScanPO_NO(String strRecvData)
			throws Exception { 
		stuCollectGoods ReqMod = JSON.parseObject(strRecvData, stuCollectGoods.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");  
		outList.add("S");  
		inList.add(ReqMod.getEnterpriseNO());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNO()); //strWareHouseNo
		inList.add(ReqMod.getPONO()); //strpo_NO 
		inList.add(ReqMod.getCType()); //STRTYPE 类型: 1，入库；2：出库   

		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "pklg_odata.P_COLLECTGOODS_SCANPONO");
		 
		if (resultList.get(1).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(1).toString());
		}
        
		return new MsgRes(true, "操作成功",resultList.get(0).toString());
	}

	/* 集货作业--扫订单号
	 * sunl 20160714
	 */
	@Override
	public MsgRes tscCollectGoods_ScanSourceExpNO(String strRecvData)
			throws Exception {
		stuCollectGoods ReqMod = JSON.parseObject(strRecvData, stuCollectGoods.class);
		
		List inList = new ArrayList();
		List outList = new ArrayList();
		List resultList = new ArrayList();
		
		outList.add("S");   
		inList.add(ReqMod.getEnterpriseNO());//strEnterPriseNo
		inList.add(ReqMod.getWarehouseNO()); //strWareHouseNo
		inList.add(ReqMod.getOwnerNO()); //STROWNERNO 
		inList.add(ReqMod.getPONO()); //strpo_NO 
		inList.add(ReqMod.getSourceExpNO()); //STRSOURCEEXPNO 
		inList.add(ReqMod.getWorkerNO()); //STRUpdt_Name 
		inList.add(ReqMod.getCType()); //STRTYPE 类型: 1，入库；2：出库   
		
		System.out.println(inList);
		resultList = genDao.execProc(inList, outList, "pklg_odata.P_COLLECTGOODS_SCANSOURCEEXPNO");

		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception(resultList.get(0).toString());
		}
        
		return new MsgRes(true, "操作成功","");
	}

	
}
