package com.sealinkin.bdef.service;

import java.io.File;
import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.MsgRes;

/**
 * 数据导入
 * @author lich
 */
public interface IDataImportService {
		
	public MsgRes tscDataImport(String strDataType,
			File file,
			String strWarehouseNo,
			String strWorkerNo,
			String strEnterpriseNo)throws Exception;

	//上传文件
	public MsgRes upload(File file, String fileName, String currEnterpriseNo,
			String warehouseNo, String workerNo)throws Exception;

	//获取下载列表
	public List<ComboxBo> getDownloadList(String currEnterpriseNo,
			String warehouseNo)throws Exception;

	//删除模板
	public MsgRes deleteFile(String currEnterpriseNo, String warehouseNo,
			String fileFileName)throws Exception;
}
