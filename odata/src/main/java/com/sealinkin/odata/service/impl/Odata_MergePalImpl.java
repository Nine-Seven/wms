package com.sealinkin.odata.service.impl;


import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.service.IOdata_MergePalService; 
import com.sealinkin.protocolExchange.odata.ODataMergePalModel;


@SuppressWarnings({"unchecked","rawtypes"})
public class Odata_MergePalImpl implements IOdata_MergePalService{
		
	private IGenericManager genDao;
		
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/* 装并板 扫板号
	 * @see com.sealinkin.odata.service.IOdata_HandlingPlateService#tscScanPlateNo(java.lang.String)
	 */
	@Override
	public MsgRes tscScanPlateNo(String strRecvData) throws Exception {
		ODataMergePalModel reqMod=JSON.parseObject(strRecvData, ODataMergePalModel.class);
		MsgRes msgRes=new MsgRes(); 
		ODataMergePalModel ms =new ODataMergePalModel();	
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();	
		
		String warehouseNo = reqMod.getWarehouseNo();
		String enterpriseNo = reqMod.getEnterpriseNo();
		String userID = reqMod.getUserID();
		String plateNo = reqMod.getPlateNo().toUpperCase();
		String boxNo = reqMod.getBoxNo().toUpperCase();
		String useType = reqMod.getUseType();//1：仅检查标签是否可用 2：检查标签是否可用，且进行装板操作 
			
		outList.add("S");
		outList.add("S");
		outList.add("S");
		
		inList.add(enterpriseNo);
		inList.add(warehouseNo);//strWareHouseNo
		inList.add(boxNo);//strSLabelNo
		inList.add(plateNo);//strDLabelNo
		inList.add(userID);//操作人员
		inList.add(useType);//1：仅检查标签是否可用 2：检查标签是否可用，且进行装板操作
		
		System.out.println(inList);
		//2. 数据校验与保存
		resultList = genDao.execProc(inList, outList, "PKLG_ODATA_MOVE_JUN.P_MergePal_Label_CheckAndSave");
		if(resultList.get(2).toString().indexOf("N|")!=-1)
		{
			 throw new Exception(resultList.get(2).toString());
		}
	    if(!boxNo.equals(""))
	    {
	    	if(resultList.get(0).toString().equals("") )
	    	{
	    		//如果箱码不为空！，但返回的标签状态为空
	    		//只有先扫板号时，才可能出现返回的标签状态和客户为空
				return new MsgRes(false,"标签数据异常，请重试！","");
	    	}
	    } 
	    
	    //获取当前板下所对应的箱标签个数
	    String strsql1 = " SELECT COUNT(M2.LABEL_NO) FROM STOCK_LABEL_M M1,STOCK_LABEL_M M2 " +
	    		" WHERE M1.WAREHOUSE_NO = M2.WAREHOUSE_NO AND M1.ENTERPRISE_NO = M2.ENTERPRISE_NO AND M1.CONTAINER_NO = M2.OWNER_CONTAINER_NO" +
	    		" AND M1.LABEL_NO = '"+plateNo+"' AND M1.WAREHOUSE_NO ='"+warehouseNo+"' AND M1.ENTERPRISE_NO = '"+enterpriseNo+"' AND M2.STATUS = '62'";
	    List list0 = genDao.getListByNativeSql(strsql1);
	    
		//3. 数据返回
		ms.setEnterpriseNo(enterpriseNo);
		ms.setWarehouseNo(warehouseNo);
		ms.setUserID(userID);
		ms.setPlateNo(plateNo);
		ms.setBoxNo(boxNo);
		ms.setStatusText(resultList.get(0) == null ? "" : resultList.get(0).toString());//标签状态，先扫板时可以为空
		ms.setCustName(resultList.get(1) == null ?  "" : resultList.get(1).toString());//客户名称
		ms.setLabelCount(Integer.parseInt(list0.get(0).toString()));
		
		msgRes.setIsSucc(true); 
		msgRes.setObj(JSON.toJSON(ms));
		return msgRes;
	} 
}
