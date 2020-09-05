package com.sealinkin.print.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.print.service.LocalPrinterService;

import java.util.List;

public class LocalPrinterAciton extends CommAction {
    private LocalPrinterService localPrinterImpl;
    private String labelNo;


    public void printWaybill(){
        try {
            List list=localPrinterImpl.getExp_MList(labelNo);
            super.writeStr(covtListToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLocalPrinterImpl(LocalPrinterService localPrinterImpl) {
        this.localPrinterImpl = localPrinterImpl;
    }

    public String getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }
}
