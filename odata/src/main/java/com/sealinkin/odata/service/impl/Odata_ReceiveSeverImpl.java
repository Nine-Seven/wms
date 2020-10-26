package com.sealinkin.odata.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.po.Odata_ExpD;
import com.sealinkin.odata.po.Odata_ExpDId;
import com.sealinkin.odata.po.Odata_ExpM;
import com.sealinkin.odata.po.Odata_ExpMId;
import com.sealinkin.odata.service.Odata_ReceiveSeverService;
import com.sealinkin.odata.util.PinduoduoUtil;

import java.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class Odata_ReceiveSeverImpl implements Odata_ReceiveSeverService {

    private IGenericManager genDao;

    public IGenericManager getGenDao() {
        return genDao;
    }

    public void setGenDao(IGenericManager genDao) {
        this.genDao = genDao;
    }

    //接受订单信息接口
    @Override
    public void sendOrderData(String json) throws Exception {

        System.out.println(json);
        List<JSONObject> list = JSON.parseArray(json, JSONObject.class);
        List<Odata_ExpD> odataExpDS = new ArrayList<>();
        List<Odata_ExpM> odataExpMS = new ArrayList<>();
        for (JSONObject map : list) {
            Odata_ExpM oem = new Odata_ExpM();
            //  BeanMap map = new BeanMap(object);
            //获取货主仓别
            String worker_no = ((String) map.get("shop")).substring(0, 4);//货主编号
            String sql = "SELECT b.warehouse_no FROM bset_worker_loc b   WHERE  b.worker_no = '" + worker_no + "'";
            List listByNativeSql = genDao.getListByNativeSql(sql);
            //生成订单id
            List inList2 = new ArrayList();
            List outList2 = new ArrayList();
            inList2.add("8888");
            inList2.add(listByNativeSql.get(0));
            inList2.add(DocumentTypeModel.ODATAOE);
            outList2.add("S");
            outList2.add("S");
            List resultList2 = null;
            try {
                resultList2 = genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_getsheetno");
                if (resultList2.get(0).toString().contains("N|")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String expNo = resultList2.get(0).toString();
            //填充id
            Odata_ExpMId id = new Odata_ExpMId("8888", (String) listByNativeSql.get(0), expNo, "B2C");
            oem.setId(id);
            //填充其他信息
            oem.setSourceexpNo((String) map.get("logistics_no"));
            oem.setCustExpNo((String) map.get("logistics_no"));
            oem.setShipperDeliverNo((String) map.get("logistics_no"));
            oem.setShipperNo((String) map.get("logisticsCode"));
            if (map.get("real_consignee") != null) {
                oem.setContactorName((String) map.get("real_consignee"));
            } else {
                oem.setContactorName((String) map.get("consignee"));
            }
            oem.setDeliverAddress((String) map.get("logistics_remark1"));
            oem.setExpRemark((String) map.get("logistics_remark2"));
            oem.setCustPhone((String) map.get("consignee_telephone"));
            oem.setCustAddress((String) map.get("consignee_address"));
            oem.setReceiveProvince((String) map.get("consignee_province"));
            oem.setReceiveCity((String) map.get("consignee_city"));
            oem.setReceiveZone((String) map.get("consignee_zone"));
            oem.setSendName((String) map.get("shipper"));
            oem.setSendAddress((String) map.get("shipper_address"));
            oem.setSendMobilePhone((String) map.get("shipper_telephone"));
            oem.setOwnerNo(worker_no);
            oem.setOwnerCustNo(worker_no);
            oem.setCustNo(worker_no);
            oem.setExpDate(new Date());
            oem.setSubCustNo(worker_no);
            oem.setSourceexpType("B2C");
            oem.setAddExpNo("N");
            oem.setRgstName("admin");
            oem.setRgstDate(new Date());
            oem.setErpoperateDate(new Date());
            oem.setFastFlag("0");
            oem.setOrderSource("0");
            oem.setOrgNo("A0000L");
            oem.setTakeType("0");
            oem.setStatus("10");
            oem.setExpStatus("00");
            oem.setBatchNo("N");
            oem.setLineNo("N");
            oem.setPrintBillFlag("0");
            oem.setStockType("1");
            oem.setPriority((short) 100);
            oem.setDeliverType("4");
            oem.setTransportType("1");
            oem.setCreateFlag("1");
            oem.setReturnFlag("0");
            oem.setBelongFlag("0");
            oem.setSendFlag("10");
            oem.setSpecialArticleGroup("0");

            //拼多多信息
            if (map.containsKey("printData")) {
                String address = (String) map.get("consignee_address");
                if (address.contains("(")) {
                    String name = address.substring(address.lastIndexOf("(") + 1, address.lastIndexOf(")"));
                    oem.setContactorName(name);
                    oem.setCustAddress(address.replace("(" + name + ")", ""));
                }
                JSONObject printData = (JSONObject) map.get("printData");
                if (worker_no.equals("AYMM")) {
                    oem.setSendName("保税物流中心");
                    oem.setSendMobilePhone("15518008535");

                    PinduoduoUtil pinduoduoUtil = new PinduoduoUtil();
                    String printDataStr = pinduoduoUtil.getPrintData(oem);
                    if (printDataStr == null || printDataStr.length() == 0) throw new Exception("修改快递单失败");
                    printData = JSON.parseObject(printDataStr);

                }
                oem.setRsvVarod5((String) printData.get("encryptedData"));
                oem.setRsvVarod6((String) printData.get("signature"));
                oem.setRsvVarod7((String) printData.get("templateUrl"));
                oem.setRsvVarod8((String) printData.get("ver"));
            }


            //   System.out.println(oem);
            //商品列表
            JSONArray ods = (JSONArray) map.get("sone");
            for (int i = 0; i < ods.size(); i++) {
                JSONObject beanMap = (JSONObject) ods.get(i);
                Odata_ExpD odata_expD = new Odata_ExpD();
                odata_expD.setOwnerNo(worker_no);
                //添加id
                Odata_ExpDId dId = new Odata_ExpDId("8888", (String) listByNativeSql.get(0), expNo, (short) i);
                odata_expD.setId(dId);
                //获取商品编码
                String sql1 = "SELECT  article_no FROM bdef_defarticle WHERE owner_article_no = '" + (String) beanMap.get("store_goods_no") + "'";
                List article_no = genDao.getListByNativeSql(sql1);
                if (article_no.size() == 0) throw new Exception("没有该商品信息");
                odata_expD.setArticleNo((String) article_no.get(0));
                odata_expD.setArticleQty(Double.parseDouble(beanMap.get("quantity").toString()));
                odata_expD.setPackingQty(1);
                odata_expD.setScheduleQty(0);
                odata_expD.setLocateQty(0);
                odata_expD.setRealQty(0);
                odata_expD.setUnitCost(0);
                odata_expD.setErrorStatus("000");
                odata_expD.setOwnerArticleNo((String) beanMap.get("store_goods_no"));
                odata_expD.setStatus("10");
                odata_expD.setRgstDate(new Date());
                odata_expD.setExpDate(new Date());
                odataExpDS.add(odata_expD);
            }

            odataExpMS.add(oem);
            //订单跟踪
            List inList = new ArrayList();
            List outList = new ArrayList();
            outList.add("S");
            inList.add("8888");
            inList.add(listByNativeSql.get(0));
            inList.add(expNo);
            inList.add("00");
            inList.add(worker_no);
            System.out.println(inList);
            List resultList = genDao.execProc(inList, outList, "PKOBJ_ODISPATCH.P_Insert_Odata_Exp_Trace");
                /*if (resultList.get(0).toString().contains("N|")) {
                    throw new Exception(resultList.get(0).toString());
                }*/

        }
        //保存订单到数据库
        this.genDao.saveList(odataExpMS);
        this.genDao.saveList(odataExpDS);


    }


}
