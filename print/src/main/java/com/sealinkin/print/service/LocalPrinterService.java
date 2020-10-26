package com.sealinkin.print.service;


import com.sealinkin.odata.model.Odata_OutstockDModel;

import java.util.List;

public interface LocalPrinterService {

    List getExp_MList(List<String> lableNos)throws Exception;

    List getPickList(String waveNo);

    List getExpNos(String waveNo);
}
