package com.sealinkin.comm.action;

import com.sealinkin.comm.service.IExportI18nService;

@SuppressWarnings("unused")
public class ExportI18nAction extends CommAction{

	private static final long serialVersionUID = 1L;
	
	private IExportI18nService exportI18nImpl;

	public void setExportI18nImpl(IExportI18nService exportI18nImpl) {
		this.exportI18nImpl = exportI18nImpl;
	}
	

}
