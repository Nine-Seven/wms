package com.sealinkin.odata.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.odata.service.Odata_ReceiveSeverService;
import com.sealinkin.odata.util.PinduoduoUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"serial", "unused"})
public class Odata_ReceiveSeverAction extends CommAction {

    private Odata_ReceiveSeverService odata_ReceiveSeverImpl;
    public void setOdata_ReceiveSeverImpl(Odata_ReceiveSeverService odata_ReceiveSeverImpl) {
        this.odata_ReceiveSeverImpl = odata_ReceiveSeverImpl;
    }

    public void sendOrderData() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String json = null;
        //从输入流中获取json
        int contentLength = request.getContentLength();
        if (contentLength < 0) {

        }
        byte buffer[] = new byte[contentLength];
        try {
            for (int i = 0; i < contentLength; ) {
                int len = 0;
                len = request.getInputStream().read(buffer, i, contentLength - i);
                if (len == -1) {
                    break;
                }
                i += len;
            }
            json = new String(buffer, "utf-8");
        } catch (IOException e) {
         //   e.printStackTrace();
        }
        //调用Service
        List<String> list = new ArrayList();
        try {
            odata_ReceiveSeverImpl.sendOrderData(json);
            list.add("200");
            super.writeArray(list);
        } catch (Exception e) {
         //   e.printStackTrace();

            list.add("500");
            list.add(e.getMessage());
            super.writeArray(list);
            e.printStackTrace();
        }



    }


}
