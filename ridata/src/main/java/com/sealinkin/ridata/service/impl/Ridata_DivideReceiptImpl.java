package com.sealinkin.ridata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.model.Ridata_InstockDModel;
import com.sealinkin.ridata.service.IRidata_DivideReceiptService;


/**
 * 返配分播回单service
 * @author hcx
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_DivideReceiptImpl implements IRidata_DivideReceiptService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	@Override
	public MsgRes divide(String enterpriseNo, String warehouseNo,
			String workerNo, String strLabelNo) throws Exception {
		String strSql="select rid.instock_no,"+
                             "rid.article_no,"+
                             "vbd.barcode,"+
                             "rid.packing_qty,"+
                             "sum(rid.article_qty)as article_qty "+
                       "from  ridata_instock_d rid,bdef_defarticle vbd "+
                       "where rid.owner_no=vbd.owner_no "+
                         "and rid.article_no=vbd.article_no "+
                         "and rid.enterprise_no=vbd.enterprise_no "+
                         "and rid.enterprise_no='"+enterpriseNo+"' "+
                         "and rid.warehouse_no='"+warehouseNo+"' "+
                         "and rid.label_no='"+strLabelNo+"' "+
                         "and rid.status in('10','11') "+
                       "group by rid.instock_no,"+
                                "rid.article_no,"+
                                "vbd.barcode,"+
                                "rid.packing_qty ";
		List<Ridata_InstockDModel> list = genDao.getListByNativeSql(
				strSql,Ridata_InstockDModel.class,0, 10000);

		
		for(int i=0;i<list.size();i++){
			List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			
			String strInstockNo = list.get(i).getInstockNo();
			String strArticleNo = list.get(i).getArticleNo();
			String strBarcode = list.get(i).getBarcode();
			Double nPackingQty = list.get(i).getPackingQty();
			Double nRealQty = list.get(i).getArticleQty();
						
			inList.add(enterpriseNo);
			inList.add(warehouseNo);
			inList.add(strInstockNo);
			inList.add(strLabelNo);
			inList.add(strArticleNo);
			inList.add(strBarcode);
			inList.add(nPackingQty);
			inList.add(nRealQty);
			inList.add(workerNo);
			inList.add(workerNo);
			inList.add("1");
			
			resultList = genDao.execProc(inList, outList,"pklg_ridata.P_UpdateDPSInstockitem");
			if (resultList.get(0).toString().indexOf("N|") != -1) {
				throw new Exception();
			}	
		}
		
		return new MsgRes(true, "分播完成！", "");
	}
	@Override
	public MsgRes closeBox(String enterpriseNo, String warehouseNo, 
			String workerNo,String strCellNo,String workSpaceNo) throws Exception {
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		outList.add("S");
		inList.add(enterpriseNo);
		inList.add(warehouseNo);
		inList.add(strCellNo);
		inList.add(workerNo);
		inList.add(workerNo);
		inList.add("1");
		inList.add(workSpaceNo);
		resultList = genDao.execProc(inList, outList,"pklg_ridata.P_DpsComfireInstock");
		if (resultList.get(0).toString().indexOf("N|") != -1) {
			throw new Exception();
		}	
		return new MsgRes(true, "封箱成功！", "");
	}
	
}
