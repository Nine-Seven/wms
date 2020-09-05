package com.sealinkin.comm.service;

import java.util.List;

import com.sealinkin.wms.model.Wms_DefModuleQueryColumnmModel;


public interface IWms_DefModuleQueryColumnm {
	public List<Wms_DefModuleQueryColumnmModel> getModuleQueryColumn(String enterpriseNo,String moduleId);
}
