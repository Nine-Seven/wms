package com.sealinkin.bdef.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.service.IDataImportService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.util.ExceptionUtil;



public class DataImportAction extends CommAction{
	private File file;
	private IDataImportService dataImportImpl;
	private String ownerNo;
	private String dataType;
	private String fileFileName;
	private String filePath;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public IDataImportService getDataImportImpl() {
		return dataImportImpl;
	}
	public void setDataImportImpl(IDataImportService dataImportImpl) {
		this.dataImportImpl = dataImportImpl;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = -3128434143636255551L;
	/**
	 * 上传Excel导入数据库
	 */
	public void dataImport(){
		try {
			MsgRes msg = dataImportImpl.tscDataImport(getDataType(), 
					getFile(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	
	//上传文件
	public void upload(){
		try {
			MsgRes msg = dataImportImpl.upload( 
					getFile(),
					this.getFileFileName(),
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(), 
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeStr("{success:false,msg:'false'}");
		}
	}
	
	//获取下载列表
	public void getDownloadList(){
		try 
		{
			List<ComboxBo> list=new ArrayList<ComboxBo>();
			list = dataImportImpl.getDownloadList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo());
			
			super.writeArray(list);
		} catch (Exception e) 
		{
			e.printStackTrace();
			super.writeObj(new MsgRes(false,ExceptionUtil.getCacseMessage(e),""));
		}
	}
	
	//删除模板
	public void deleteFile(){
		try {
			MsgRes msg = this.dataImportImpl.deleteFile(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getFilePath());
			super.writeObj(msg);
		} catch (Exception e) {
			super.writeObj(new MsgRes(false, "", ""));
		}
	}
	
}
