package com.sealinkin.idata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.idata.model.Idata_CheckDModel;
import com.sealinkin.idata.model.Idata_CheckMModel;
import com.sealinkin.idata.service.Iidata_StraightCheckWorkService;

@SuppressWarnings({"rawtypes","unchecked"}) 
public class Idata_StraightCheckWorkImpl implements
		Iidata_StraightCheckWorkService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	@Override
	public MsgRes save(String strJsonMaster, String strJsonDetail1)
			throws Exception {
		Idata_CheckMModel poMaster=(Idata_CheckMModel) JSON.parseObject(strJsonMaster, Idata_CheckMModel.class);
		List<Idata_CheckDModel> list=JSON.parseArray(strJsonDetail1,Idata_CheckDModel.class);
		for(int i=0; i<list.size(); i++){
			
			List<Comparable> inList=new ArrayList<Comparable>();
			List<String> outList=new ArrayList<String>();
			List resultList=new ArrayList();
			
			
			outList.add("S");
			outList.add("S");
			inList.add(poMaster.getEnterpriseNo());
			inList.add(poMaster.getWarehouseNo());
			inList.add(poMaster.getOwnerNo());
			inList.add(poMaster.getSImportNo());
			inList.add(list.get(i).getArticleNo());
			inList.add(list.get(i).getBarcode());
			inList.add(list.get(i).getPackingQty());
			inList.add(list.get(i).getCheckQty());
			inList.add("N");
			inList.add(poMaster.getDockNo());
			inList.add(poMaster.getCheckWorker());
			inList.add(poMaster.getCheckTools());
			inList.add(0);
			inList.add(list.get(i).getQuality());
			inList.add(list.get(i).getProduceDate());
			inList.add(list.get(i).getExpireDate());
			inList.add(list.get(i).getLotNo());
			inList.add((list.get(i).getRsvBatch1() == null || list.get(i).getRsvBatch1().trim().equals("")) ? "N":list.get(i).getRsvBatch1());
			inList.add((list.get(i).getRsvBatch2() == null || list.get(i).getRsvBatch2().trim().equals("")) ? "N":list.get(i).getRsvBatch2());
			inList.add((list.get(i).getRsvBatch3() == null || list.get(i).getRsvBatch3().trim().equals("")) ? "N":list.get(i).getRsvBatch3());
			inList.add((list.get(i).getRsvBatch4() == null || list.get(i).getRsvBatch4().trim().equals("")) ? "N":list.get(i).getRsvBatch4());
			inList.add((list.get(i).getRsvBatch5() == null || list.get(i).getRsvBatch5().trim().equals("")) ? "N":list.get(i).getRsvBatch5());
			inList.add((list.get(i).getRsvBatch6() == null || list.get(i).getRsvBatch6().trim().equals("")) ? "N":list.get(i).getRsvBatch6());
			inList.add((list.get(i).getRsvBatch7() == null || list.get(i).getRsvBatch7().trim().equals("")) ? "N":list.get(i).getRsvBatch7());
			inList.add((list.get(i).getRsvBatch8() == null || list.get(i).getRsvBatch8().trim().equals("")) ? "N":list.get(i).getRsvBatch8());
			inList.add("N");
			inList.add("N");
			inList.add("2");
			inList.add("1");
			
			System.out.println(inList);
			resultList = genDao.execProc(inList, outList, "PKLG_IDATA.Proc_save_idcheck");
			
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}	
		}
		return new MsgRes(true, "保存成功","");
	}



}
