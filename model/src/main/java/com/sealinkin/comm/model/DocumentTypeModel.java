package com.sealinkin.comm.model;

import java.io.Serializable;

public class DocumentTypeModel implements Serializable{
	/**
	 * 取单号，单据类型定义常量
	 */
	private static final long serialVersionUID = 8722548406731615163L;
	
	public static final String IDATAIMPORTID = "ID";       //直通进货
	public static final String IDATAIMPORTIS = "IS";       //存储进货      
	public static final String IDATAIL  = "IL";       //定位指示号     
	public static final String IDATAIP = "IP";       //进货上架      
	public static final String IDATASC = "SC";          //取汇总验收单号；     
	public static final String IDATAIC = "IC";          //取验收单号；


	public static final String ODATAHO = "HO";           //出货下架单号       
	public static final String ODATAOE = "OE";           //出货手建单号        
	public static final String ODATAOC = "OC";           //病单单号 (复核单头档单号)      
	public static final String ODATACT = "CT";           //整理单号        
	public static final String ODATALO = "LO";           //波次号  
	public static final String ODATAWO = "WO";       //定位指示号；      
	public static final String ODATARL = "RL";        //下架单号；     
	public static final String ODATAOL = "OL";          //装车建议单；
	public static final String ODATAOD = "OD";          //出货配送单；          
	public static final String ODATARD = "RD";          //取分播单号；          

	public static final String FCDATACP = "CP";          //盘点计划单号；
	public static final String FCDATACR = "CR";          //盘点需求单号；
	public static final String FCDATACH = "CH";          //盘点单号；
	public static final String FCDATACD = "CD";          //取差异单号；
     
	public static final String RIDATAUM = "UM";       //返配单号
	public static final String RIDATAVM = "VM";       //返配汇总单号
     
	public static final String RODATARE = "RE";       //退货单号

	public static final String MDATAHC = "HC";          //移库计划单；
	public static final String MDATAHS = "HS";       //移库下架单

	public static final String WMSQU = "QU";          //品质转换单号；

	public static final String WMSAD = "AD";        //库存调账单号；
     
	public static final String PRINTPT = "PT";       //打印
       
	public static final String LABELXH = "XH";       //标签销毁单号
	
	public static final String ACDATAAC = "AC";       //分拨中心单号
	
	public static final String SODATASM = "SM";       //报损单号
	
}

