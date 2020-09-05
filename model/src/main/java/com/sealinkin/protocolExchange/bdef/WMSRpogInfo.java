package com.sealinkin.protocolExchange.bdef;

import java.io.Serializable;
import java.sql.Blob;

public class WMSRpogInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String progName;        
    private String includeProg;       
    private String progType;        
    private String paraValue;       
    private String progPath;        
    private String writeDate;
    private Blob progbyte;
    
    private byte[] ByteInfo;
	public String getProgName() {
		return progName;
	}
	public void setProgName(String progName) {
		this.progName = progName;
	}
	public String getIncludeProg() {
		return includeProg;
	}
	public void setIncludeProg(String includeProg) {
		this.includeProg = includeProg;
	}
	public String getProgType() {
		return progType;
	}
	public void setProgType(String progType) {
		this.progType = progType;
	}
	public String getParaValue() {
		return paraValue;
	}
	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	public String getProgPath() {
		return progPath;
	}
	public void setProgPath(String progPath) {
		this.progPath = progPath;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Blob getProgbyte() {
		return progbyte;
	}
	public void setProgbyte(Blob progbyte) {
		this.progbyte = progbyte;
	}
	public byte[] getByteInfo() {
		return ByteInfo;
	}
	public void setByteInfo(byte[] byteInfo) {
		ByteInfo = byteInfo;
	}
}
