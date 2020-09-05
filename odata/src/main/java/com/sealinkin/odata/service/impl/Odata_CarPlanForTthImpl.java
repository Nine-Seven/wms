package com.sealinkin.odata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_LoadproposeMModel;
import com.sealinkin.odata.service.IOdata_CarPlanForTthService;
import com.sealinkin.stock.model.Stock_LabelDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;

@SuppressWarnings({"rawtypes","unchecked"})
public class Odata_CarPlanForTthImpl implements IOdata_CarPlanForTthService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public List<ComboxBo> getCust(String enterpriseNo, String warehouseNo) throws Exception {
		
		String strSql="select distinct b.cust_no value ,b.cust_name text, b.cust_name dropValue "+
		              "from stock_label_m a ,bdef_defcust b  "+"" +
		              "where a.cust_no= b.cust_no " +
		              "and a.enterprise_no = b.enterprise_no "+
		              "and a.status='A0' " +
		              "and a.enterprise_no='"+enterpriseNo+"' "+
		              "and a.warehouse_no='"+warehouseNo+"' ";

		List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return list;
	}

	@Override
	public List<ComboxBo> getLineNoCombo(String enterpriseNo, String warehouseNo,String strQuery) throws Exception {
		String strSql="select distinct a.line_no value, a.line_no text, a.line_no dropValue "+
	                  "from stock_label_m a " +
	                  "where a.cust_no='"+strQuery+
	                  "' and a.enterprise_no='"+enterpriseNo+
	                  "' and a.warehouse_no='"+warehouseNo+"' ";

	List<ComboxBo> list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
	return list;
	}

	@Override
	public ExtListDataBo<Stock_LabelMModel> queryStockLabel(String enterpriseNo,String warehouseNo,
			String lineNo, String custNo) throws Exception {
		String strSql="select slm.label_no ,slm.cust_no, bdc.cust_name, slm.container_no, slm.deliver_obj from stock_label_m slm ,bdef_defcust bdc "+
				      "where slm.cust_no=bdc.cust_no" +
				      "  and slm.enterprise_no = bdc.enterprise_no "+
				      "  and slm.cust_no='"+custNo+
				      "' and slm.line_no='"+lineNo+
				      "' and slm.enterprise_no='"+enterpriseNo+
				      "' and slm.warehouse_no='"+warehouseNo+
				      "' and slm.status='A0'";
	
		
		List<Stock_LabelMModel> list = genDao.getListByNativeSql(strSql,Stock_LabelMModel.class,0, 1000);
		ExtListDataBo<Stock_LabelMModel> extListBo= new ExtListDataBo<Stock_LabelMModel>(list, 0);
        return extListBo;
	}

	@Override
	public ExtListDataBo<Stock_LabelDModel> searchDetail(String enterpriseNo,String warehouseNo,String wheresql,
			PageBo pageBo) {
		String str[]=wheresql.split(",");
		String sql="select sl.label_no,sl.article_no,bda.owner_article_no,bda.article_name,bda.barcode," +
				"sl.packing_qty," +
				//"nvl(bap.packing_unit, decode(sl.packing_qty,bda.qmin_operate_packing,bda.qmin_operate_packing_unit,bda.unit)) packing_unit,"+
				//"nvl(bap.spec, '1*' || sl.packing_qty || bda.unit) spec," +
				"f_get_packingunit(sl.enterprise_no,sl.owner_no,sl.article_no,sl.packing_qty) packingUnit,"+
				"f_get_packingunit(sl.enterprise_no,sl.owner_no,sl.article_no,bda.qmin_operate_packing) packingUnitQmin,"+
				"f_get_packingunit(sl.enterprise_no,sl.owner_no,sl.article_no,bda.unit_packing) Unit,"+
				"f_get_spec(sl.enterprise_no,sl.owner_no,sl.article_no,sl.packing_qty) packingSpec,"+
				"f_get_spec(sl.enterprise_no,sl.owner_no,sl.article_no,bda.qmin_operate_packing) packingSpecQmin,"+
				"f_get_spec(sl.enterprise_no,sl.owner_no,sl.article_no,bda.unit_packing) spec,"+
				"(qty/sl.packing_qty) as pobox " +
				"from " +
				"(select slm.enterprise_no,sld.owner_no,slm.label_no,sld.article_no,sld.packing_qty," +
				"sum(sld.qty) as qty,sld.container_no " +
				"from stock_label_d sld,stock_label_m slm " +
				"where sld.container_no=slm.container_no " +
				"and sld.enterprise_no=slm.enterprise_no "+
				"and sld.warehouse_no=slm.warehouse_no "+
				"and sld.enterprise_no='"+enterpriseNo+"' "+
				"and sld.warehouse_no='"+warehouseNo+"' "+
				"group by slm.enterprise_no,slm.label_no,sld.article_no,sld.packing_qty,sld.owner_no,sld.container_no) sl," +
				"bdef_defarticle bda ,bdef_article_packing bap " +
				"where bda.article_no=sl.article_no  " +
				"and bda.enterprise_no =sl.enterprise_no and sl.enterprise_no = bap.enterprise_no(+) "+
				" and sl.article_no = bap.article_no(+) and " +
				"sl.packing_qty=bap.packing_qty(+) and sl.container_no in (";
		for(int i=0;i<str.length;i++){
			if(i==str.length-1){
				sql+="'"+str[i].trim()+"'";
			}else{
				sql+="'"+str[i].trim()+"',";
			}
		}
		sql+=")";
		List<Stock_LabelDModel> list = genDao.getListByNativeSql(sql,Stock_LabelDModel.class,pageBo.getStart(), 1000);
		ExtListDataBo<Stock_LabelDModel> extListBo= new ExtListDataBo<Stock_LabelDModel>(list, 0);
        return extListBo;
	}

	@Override
	public MsgRes tscCloseCar(String str, String workSpaceNo)
			throws Exception {
		Odata_LoadproposeMModel poMaster=(Odata_LoadproposeMModel) JSON.parseObject(str, Odata_LoadproposeMModel.class);
		String ip = HttpService.getIpAddr();
		List outList=new ArrayList();
	
		outList.add("S");
		outList.add("S");
		List resultList=new ArrayList();
		List inList=new ArrayList();
			
		inList.add(poMaster.getEnterpriseNo());
		inList.add(poMaster.getWarehouseNo());//strWareHouseNo
		inList.add(poMaster.getDeliverObj());//strDeliverObj
		inList.add(poMaster.getShipperNo());//strShipperNo
		inList.add(poMaster.getLineNo());//strLineNo
		inList.add(poMaster.getCarNo());//strCarNo
		inList.add(poMaster.getUserId());//strUserId
		inList.add(poMaster.getDockNo());//strDockNo
		inList.add("N");//strDivideTrunk
		inList.add("N");//strcarPlanNo
		inList.add("N");//strSealNo
		inList.add(poMaster.getLoadtype());//strLoadType
		inList.add(poMaster.getPaperUserId());//strPaperUserId
		inList.add(ip);//tmpID
		
		resultList=genDao.execProc(inList, outList, "PKLG_ODATA_DELIVER.P_odata_CloseCar");
		System.out.println(inList);	
		if(resultList.get(1).toString().indexOf("Y")==-1)
		{
			throw new Exception(resultList.get(1).toString());
		}		
		
		String strLoadProposeNo="N";
		List outList1=new ArrayList();
		outList1.add("S");
		outList1.add("S");
		List resultList1=new ArrayList();
		
			if(!strLoadProposeNo.equals(resultList.get(0).toString()))
			{
				strLoadProposeNo=resultList.get(0).toString();
				
				List inList1=new ArrayList();
				inList1.add(poMaster.getEnterpriseNo());
				inList1.add(poMaster.getWarehouseNo());
				inList1.add(strLoadProposeNo);
				inList1.add(poMaster.getUserId());
				inList1.add(poMaster.getPaperUserId());
				resultList1=genDao.execProc(inList1, outList1, "PKLG_ODATA_DELIVER.P_odata_deliver");
				System.out.println(resultList1);
				if(resultList1.get(1).toString().indexOf("N|")!=-1)
				{
					throw new Exception(resultList1.get(1).toString());
				}
			}			
		
		return new MsgRes(true,"封车成功","");
		
		
		
	}
	
	//删除临时表数据
	public MsgRes deleteTmp(String enterpriseNo,String warehouseNo) throws Exception {
		String ip = HttpService.getIpAddr();//InetAddress.getLocalHost().getHostAddress();
		String strSql="delete odata_loadcar_tmp " +
				"where tmp_id='"+ip+
				"' and enterprise_no='"+enterpriseNo+
				"' and warehouse_no='"+warehouseNo+"'";
		
		genDao.updateBySql(strSql);
		return new MsgRes(true,"","");
	}

	//勾选中的单据保存进临时表
	@Override
	public MsgRes saveStockTmp(String enterpriseNo,String warehouseNo, String str) throws Exception {
		
		//删除临时表数据
		deleteTmp(enterpriseNo,warehouseNo);

		String strList[]=str.split(",");
		String ip = HttpService.getIpAddr();//InetAddress.getLocalHost().getHostAddress();
		String strSql="insert into odata_loadcar_tmp " +
				"select '"+ip+"',slm.warehouse_no,slm.label_no,slm.container_no,slm.enterprise_no from stock_label_m slm " +
				"where slm.warehouse_no='"+warehouseNo+"' " +
				"and slm.enterprise_no='"+enterpriseNo+"' "+
				"and slm.label_no='"+strList[0].trim()+"' " +
				"and slm.container_no='"+strList[1].trim()+"' ";
		genDao.updateBySql(strSql);			
		return new MsgRes(true,"","");
	}

}
