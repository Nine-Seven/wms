package com.sealinkin.odata.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.request.PddLogisticsCompaniesGetRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddWaybillUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddWaybillUpdateRequest.*;
import com.pdd.pop.sdk.http.api.pop.response.PddLogisticsCompaniesGetResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddWaybillUpdateResponse;
import com.sealinkin.odata.po.Odata_ExpM;
import org.springframework.web.client.RestTemplate;


public class PinduoduoUtil {
    public  String getPrintData(Odata_ExpM odata_expM) {
        String printData = "";
        String clientId = "";
        String clientSecret = "";
        String accessToken = "";

        //获取token,clientId,clientSecret
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://39.98.178.86:8055/aymorder/GetAccessToken?sysflag="+odata_expM.getOwnerNo();
        String json = restTemplate.getForObject(url, String.class);
        System.out.println(json);
        JSONObject result = JSON.parseObject(json);
        if (!result.get("code").equals(200)) {
            return null;
        }
        JSONArray userInfo = result.getJSONArray("userInfo");

        for (Object o : userInfo) {
            JSONObject user = (JSONObject) o;
            String remark1 = (String) user.get("remark1");
            if (remark1 != null && remark1.length() != 0){
                clientId = (String) user.get("remark5");
                clientSecret = (String) user.get("remark6");
                accessToken = (String) user.get("remark1");
            }
        }

        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddWaybillUpdateRequest request = new PddWaybillUpdateRequest();

        ParamWaybillCloudPrintUpdateRequest paramWaybillCloudPrintUpdateRequest = new ParamWaybillCloudPrintUpdateRequest();
       // paramWaybillCloudPrintUpdateRequest.setObjectId("str");

        //包裹信息
       /* ParamWaybillCloudPrintUpdateRequestPackageInfo packageInfo = new ParamWaybillCloudPrintUpdateRequestPackageInfo();
        List<ParamWaybillCloudPrintUpdateRequestPackageInfoItemsItem> items = new ArrayList<ParamWaybillCloudPrintUpdateRequestPackageInfoItemsItem>();

        ParamWaybillCloudPrintUpdateRequestPackageInfoItemsItem item = new ParamWaybillCloudPrintUpdateRequestPackageInfoItemsItem();
        item.setCount(0);
        item.setName("str");
        items.add(item);
        packageInfo.setItems(items);
        packageInfo.setVolume(0L);
        packageInfo.setWeight(0L);
        paramWaybillCloudPrintUpdateRequest.setPackageInfo(packageInfo);*/
        //收货人信息
        ParamWaybillCloudPrintUpdateRequestRecipient recipient = new ParamWaybillCloudPrintUpdateRequestRecipient();
        //收货地址
        ParamWaybillCloudPrintUpdateRequestRecipientAddress address = new ParamWaybillCloudPrintUpdateRequestRecipientAddress();
       /* address.setCity(odata_expM.getReceiveCity());
        address.setDetail(odata_expM.getCustAddress());
        address.setDistrict(odata_expM.getReceiveZone());
        address.setProvince(odata_expM.getReceiveProvince());
        address.setTown(odata_expM.getReceiveCountry());
        address.setCountry("中国");*/
        recipient.setAddress(address);
        recipient.setMobile(odata_expM.getCustPhone());
        recipient.setName(odata_expM.getContactorName());
     //   recipient.setPhone("str");
        paramWaybillCloudPrintUpdateRequest.setRecipient(recipient);
        //发货人信息
        ParamWaybillCloudPrintUpdateRequestSender sender = new ParamWaybillCloudPrintUpdateRequestSender();
        sender.setMobile(odata_expM.getSendMobilePhone());
        sender.setName(odata_expM.getSendName());
        sender.setPhone(odata_expM.getSendMobilePhone());
        paramWaybillCloudPrintUpdateRequest.setSender(sender);
    //    paramWaybillCloudPrintUpdateRequest.setTemplateUrl("str");
        paramWaybillCloudPrintUpdateRequest.setWaybillCode(odata_expM.getShipperDeliverNo());
        //获取物流code
        /*PddLogisticsCompaniesGetRequest plcgRequest = new PddLogisticsCompaniesGetRequest();
        PddLogisticsCompaniesGetResponse plcgResponse = null;
        try {
            plcgResponse= client.syncInvoke(plcgRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JsonUtil.transferToJson(plcgResponse));*/
        if(odata_expM.getShipperNo().equals("3301963H57")){paramWaybillCloudPrintUpdateRequest.setWpCode("HT");}
        else if(odata_expM.getShipperNo().equals("3120980110")){paramWaybillCloudPrintUpdateRequest.setWpCode("STO");}

        request.setParamWaybillCloudPrintUpdateRequest(paramWaybillCloudPrintUpdateRequest);
        PddWaybillUpdateResponse response = null;
        try {
            response = client.syncInvoke(request, accessToken);
        } catch (Exception e) {
       //     e.printStackTrace();
        }

        System.out.println(JsonUtil.transferToJson(response));
        if (response.getPddWaybillUpdateResponse() != null)
        return response.getPddWaybillUpdateResponse().getPrintData();
        return null;
    }
}
