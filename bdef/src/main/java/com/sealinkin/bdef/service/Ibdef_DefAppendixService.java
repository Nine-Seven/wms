package com.sealinkin.bdef.service;

import java.io.File;

import com.sealinkin.bdef.model.Bdef_DefappendixModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public interface Ibdef_DefAppendixService {
	//获取数据
	ExtListDataBo<Bdef_DefappendixModel> getDefAppendixList(
			String enterpriseNo, String warehouseNo, String ownerNo,
			String strQuery, PageBo poPagebo)throws Exception;

	//保存
	MsgRes save(String enterpriseNo, String warehouseNo, String ownerNo,
			String name, String type, String relateOrderNo,
			String relateClass, File file,String fileName, String meno, String workerNo)throws Exception;

	//删除
	MsgRes delete(String enterpriseNo, String warehouseNo, String ownerNo,
			String name)throws Exception;

	//修改
	MsgRes update(String enterpriseNo, String warehouseNo, String ownerNo,
			String name, String type, String relateOrderNo, String relateClass,
			File file, String fileFileName, String meno, String workerNo)throws Exception;

	
	

}
