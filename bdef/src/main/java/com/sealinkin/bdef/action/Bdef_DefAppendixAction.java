package com.sealinkin.bdef.action;

import java.io.File;

import com.sealinkin.bdef.model.Bdef_DefappendixModel;
import com.sealinkin.bdef.service.Ibdef_DefAppendixService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;

public class Bdef_DefAppendixAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ibdef_DefAppendixService bdef_DefAppendixImpl;
	private String strQuery;
	private String ownerNo;	
	private String name;
	private String type;
	private String relateOrderNo;
	private String relateClass;
	private File file;
	private String fileFileName;
	private String str;
	private String meno;
	
	//获取附件头档
	public void getDefAppendixList(){
		try{
			PageBo poPagebo= new PageBo(getStart(), getLimit());
			ExtListDataBo<Bdef_DefappendixModel> pageListBo =bdef_DefAppendixImpl.getDefAppendixList(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					super.getMdBdef_DefWorker().getWorkerOwner(),
					this.getStrQuery(),
					poPagebo);
			super.writeObj(pageListBo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//保存
	public void save(){
		try{
			MsgRes msg = bdef_DefAppendixImpl.save(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					ownerNo,
					name,
					type,
				    relateOrderNo,
				    relateClass,
					file,
					fileFileName,
					meno,
					super.getMdBdef_DefWorker().getWorkerNo());	
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//删除
	public void delete(){
		try{
			MsgRes msg = bdef_DefAppendixImpl.delete(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					this.getOwnerNo(),
					this.getName());
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//修改
	public void update(){
		try{
			MsgRes msg = bdef_DefAppendixImpl.update(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					ownerNo,
					name,
					type,
				    relateOrderNo,
				    relateClass,
					file,
					fileFileName,
					meno,
					super.getMdBdef_DefWorker().getWorkerNo());	
			super.writeObj(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setBdef_DefAppendixImpl(
			Ibdef_DefAppendixService bdef_DefAppendixImpl) {
		this.bdef_DefAppendixImpl = bdef_DefAppendixImpl;
	}

	public String getStrQuery() {
		return strQuery;
	}
	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRelateOrderNo() {
		return relateOrderNo;
	}
	public void setRelateOrderNo(String relateOrderNo) {
		this.relateOrderNo = relateOrderNo;
	}
	public String getRelateClass() {
		return relateClass;
	}
	public void setRelateClass(String relateClass) {
		this.relateClass = relateClass;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
