package com.sealinkin.odata.service.impl;


import com.sealinkin.comm.model.DocumentTypeModel;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.po.Odata_ExpD;
import com.sealinkin.odata.po.Odata_ExpDId;
import com.sealinkin.odata.po.Odata_ExpM;
import com.sealinkin.odata.po.Odata_ExpMId;
import com.sealinkin.odata.service.Odata_ReceiveSeverService;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

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
    public void sendOrderData(String json) {

        System.out.println(json);
        List<Map> list = JSONArray.fromObject(json);
        for (Map map : list) {
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
            if (map.get("real_consignee") instanceof JSONNull) {
                oem.setContactorName((String) map.get("consignee"));
            } else {
                oem.setContactorName((String) map.get("real_consignee"));
            }
            if (!(map.get("logistics_remark1") instanceof JSONNull)) {
                oem.setDeliverAddress((String) map.get("logistics_remark1"));
                oem.setExpRemark((String) map.get("logistics_remark2"));
            }
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
                JSONArray printData = (JSONArray) map.get("printData");
                JSONObject print = (JSONObject) printData.get(0);
                oem.setRsvVarod5((String) print.get("encryptedData"));
                oem.setRsvVarod6((String) print.get("signature"));
                oem.setRsvVarod7((String) print.get("templateUrl"));
                oem.setRsvVarod8((String) print.get("ver"));
            }


            //   System.out.println(oem);
            //商品列表
            JSONArray ods = (JSONArray) map.get("sone");
            List<Odata_ExpD> odataExpDS = new ArrayList<>();
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
            //保存订单到数据库
            this.genDao.saveOrUpdateObj(oem);
            this.genDao.saveList(odataExpDS);

            //订单跟踪
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
