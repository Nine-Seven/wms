package com.sealinkin.print.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.print.service.LocalPrinterService;
import org.apache.struts2.ServletActionContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LocalPrinterAciton extends CommAction {
    private LocalPrinterService localPrinterImpl;
    private String waveNo;

    public void printWaybill(){
        try {
            String[] expNos= ServletActionContext.getRequest().getParameterValues("expNos[]");
            List list=localPrinterImpl.getExp_MList(Arrays.asList(expNos));
            super.writeStr(covtListToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printPicking(){
        try {

            List list=localPrinterImpl.getPickList(waveNo);
            super.writeStr(covtListToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getExpNos(){
        try {

            List list=localPrinterImpl.getExpNos(waveNo);
            super.writeStr(covtListToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void setLocalPrinterImpl(LocalPrinterService localPrinterImpl) {
        this.localPrinterImpl = localPrinterImpl;
    }

    public String getWaveNo() {
        return waveNo;
    }

    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }
}
