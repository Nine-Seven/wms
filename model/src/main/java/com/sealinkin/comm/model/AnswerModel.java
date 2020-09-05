package com.sealinkin.comm.model;

import java.io.Serializable;

public class AnswerModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  strData;
	private String  strResult;	//Y|N
	
	public AnswerModel(String strData, String strResult) {
		this.strData = strData;
		this.strResult = strResult;
	}

	public AnswerModel() {
		// TODO Auto-generated constructor stub
	}

	public String getStrData() {
		return strData;
	}

	public void setStrData(String strData) {
		this.strData = strData;
	}

	public String getStrResult() {
		return strResult;
	}
	
	public void setStrResult(String strResult) {
		this.strResult = strResult;
	}
}

