package com.sealinkin.bdef.service.impl;

import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.oset.model.Oset_DeflineModel;
import com.sealinkin.oset.model.Oset_LineCustModel;
import com.sealinkin.oset.po.Oset_Defline;
import com.sealinkin.oset.po.Oset_LineCust;
import com.sealinkin.protocolExchange.idata.IdataGetArticleInfoIDAnswerModel;
import com.sealinkin.bdef.service.IOset_DefLineService;
import com.sealinkin.util.CommUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class Oset_DefLineServiceImpl implements IOset_DefLineService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	@Override
	public ExtListDataBo<Oset_DeflineModel> getDefLine(String enterpriseNo,String warehouseNo,
			String queryStr,PageBo pageBo,Integer requestFlag)throws Exception {
		String sql="select a.warehouse_no,a.line_no,a.line_name,a.line_fname," +
				"a.deliver_type,a.transport_type," +
				"f_get_fieldtext('N','DELIVER_TYPE',a.deliver_type) delivertypeText," +
				"f_get_fieldtext('N','TRANSPORT_TYPE',a.transport_type) transporttypeText " +
				"from OSET_DEFLINE a where " +
				" a.warehouse_no='"+warehouseNo+
				"' and a.enterprise_no='"+enterpriseNo+"' ORDER BY a.line_no " ;		
        String totsql = "select count(1) " +
        		"from OSET_DEFLINE a where a.warehouse_no='"+warehouseNo+
        		"' and a.enterprise_no='"+enterpriseNo+"'" ;
		if(queryStr!=null && !queryStr.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(queryStr);
			sql=sql+ws;
			totsql=totsql+ws;
		}
		
		List<Oset_DeflineModel> list = null;
		Integer count = 0;
		ExtListDataBo<Oset_DeflineModel> extListBo=null;
		if(requestFlag==1){
			list = genDao.getListByNativeSql(sql,Oset_DeflineModel.class,pageBo.getStart(), pageBo.getPagesize());
			count = genDao.getCountByNativeSql(totsql);
		}else if(requestFlag==2){
			list = genDao.getListByNativeSql(sql,Oset_DeflineModel.class);
			count = list.size();
		}
		extListBo= new ExtListDataBo<Oset_DeflineModel>(list, count);
        return extListBo;
	}

	@Override
	public ExtListDataBo<Oset_LineCustModel> getLineCust(String enterpriseNo,String wheresql,
			String strWarehoueNo)throws Exception {
		String sql="select a.line_no,a.line_name,c.cust_no,c.cust_name,b.line_seq_no " +
				"from OSET_DEFLINE a,OSET_LINE_CUST b,BDEF_DEFCUST c " +
				"where a.line_no=b.line_no and b.cust_no=c.cust_no and " +
				"a.line_no='"+wheresql+"'" +
				"and a.warehouse_no='"+strWarehoueNo+"' " +
				"and a.enterprise_no = b.enterprise_no " +
				"and a.enterprise_no = c.enterprise_no " +
				"and a.enterprise_no='"+enterpriseNo+"' ORDER BY a.line_no,b.line_seq_no ";	
		
		List<Oset_LineCustModel> list = genDao.getListByNativeSql(sql,Oset_LineCustModel.class,0, 1000);
		
		ExtListDataBo<Oset_LineCustModel> extListBo= new ExtListDataBo<Oset_LineCustModel>(list, 0);
		return extListBo;
	}

	@Override
	public ExtListDataBo<Oset_LineCustModel> getCust(String enterpriseNo,String warehouseNo,
			PageBo pageBo, String workerOwner, String strQuery)throws Exception {
		String sql="select a.cust_no,a.cust_name " +
				"from bdef_defcust a " +
				"where a.cust_no not in" +
				"(select b.cust_no from oset_line_cust b where " +
				"b.warehouse_no='"+warehouseNo+
				"' and b.enterprise_no='"+enterpriseNo+
				"') and a.enterprise_no='"+enterpriseNo+"' ";
		String totsql="select count(1) " +
				"from bdef_defcust a " +
				"where a.cust_no not in" +
				"(select b.cust_no from oset_line_cust b where " +
				"b.warehouse_no='"+warehouseNo+
				"' and b.enterprise_no='"+enterpriseNo+
				"') and a.enterprise_no='"+enterpriseNo+"' ";
		
		
		if(workerOwner!=null && !workerOwner.equals(""))
		{
			sql=sql+" and a.owner_no in("+workerOwner+") ";
			totsql=totsql+" and a.owner_no in("+workerOwner+") ";
		}else
		{
			sql=sql+" and 1=2";
			totsql=totsql+" and 1=2";
		}
		
		if(strQuery!=null && !strQuery.equals("")){
			sql=sql+" and a.owner_no='"+strQuery+"' ";
			totsql=totsql+" and a.owner_no='"+strQuery+"'";
		}
		sql=sql+" ORDER BY a.cust_no ";
		List<Oset_LineCustModel> list = genDao.getListByNativeSql(sql,Oset_LineCustModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Oset_LineCustModel> extListBo= new ExtListDataBo<Oset_LineCustModel>(list, count);
		return extListBo;
	}

	@Override
	public boolean saveOset_DefLine(String str)throws Exception {
		Oset_Defline od=(Oset_Defline)JSON.parseObject(str,Oset_Defline.class);
		this.genDao.saveOrUpdateObj(od);
		return true;
	}

	@Override
	public boolean deleteOset_DefLine(String enterpriseNo,String warehouseNo, String lineNo)throws Exception {
		String sql="delete oset_defline a where a.warehouse_no='"+warehouseNo+
				"' and a.line_no='"+lineNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(sql);
		return true;
	}

	@Override
	public boolean saveOset_Line_Cust(String str)throws Exception {
		Collection<Oset_LineCust> lcust=JSONArray.toCollection(JSONArray.fromObject(str),Oset_LineCust.class);
		List<Oset_LineCust> lineCust=(List)lcust;
		//取路顺
		Integer seq=0;
		String sql = "select max(t.line_seq_no) line_seq_no " +
				"from oset_line_cust t where t.enterprise_no='"+lineCust.get(0).getId().getEnterpriseNo()+"' " +
				"and t.warehouse_no='"+lineCust.get(0).getId().getWarehouseNo()+"' " +
				"and t.line_no='"+lineCust.get(0).getId().getLineNo()+"' ";
		List<Oset_LineCust> list=genDao.getListByNativeSql(sql,Oset_LineCust.class);
		if(list.get(0).getLineSeqNo() != null){
			seq = list.get(0).getLineSeqNo();
		}
		for(int i=0;i<lineCust.size();i++){
			if(list.get(0).getLineSeqNo() == null){
				lineCust.get(i).setLineSeqNo(seq);
				seq=seq+1;
			}else{
				seq=seq+1;
				lineCust.get(i).setLineSeqNo(seq);
			}
		}
		
		this.genDao.saveList(lineCust);
		return true;
	}

	@Override
	public boolean deleteOSet_Line_Cust(String enterpriseNo,String warehouseNo, String lineNo,
			String custNo)throws Exception {
		String wheresql1[]=warehouseNo.split(",");
		String wheresql2[]=lineNo.split(",");
		String wheresql3[]=custNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql="delete oset_line_cust a where a.warehouse_no='"+wheresql1[i].trim()+"' and " +
					"a.line_no='"+wheresql2[i].trim()+
					"' and a.cust_no='"+wheresql3[i].trim()+
					"' and a.enterprise_no='"+enterpriseNo+"' ";
			genDao.updateBySql(sql);
		}
		//取该线路的所有没有移库的数据，重整路顺号
		String sql2 = "select * from oset_line_cust t " +
				"where t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+wheresql1[0].trim()+"' " +
				"and t.line_no='"+wheresql2[0].trim()+"' " +
				"order by  t.line_seq_no";
		List<Oset_LineCustModel> list=genDao.getListByNativeSql(sql2,Oset_LineCustModel.class);
		if(list.size()>0){
			int a = 0;
			for(int i=0;i<list.size();i++){
				String sql3 = "update oset_line_cust t set t.line_seq_no='"+a+"' " +
						"where t.enterprise_no='"+enterpriseNo+"' " +
						"and t.warehouse_no='"+wheresql1[0].trim()+"' " +
						"and t.line_no='"+wheresql2[0].trim()+"' "+
						"and t.cust_no='"+list.get(i).getCustNo()+"' ";
				genDao.updateBySql(sql3);
                a=a+1;
			}
		}
		
		return true;
	}

	@Override
	public String checkLineNo(String enterpriseNo,String warehouseNo, String lineNo)
			throws Exception {
		String sql="select a.line_no from OSET_DEFLINE a where a.warehouse_no='"+warehouseNo+
				"' and a.line_no='"+lineNo+
				"' and a.enterprise_no='"+enterpriseNo+"'";
		List<String> list=genDao.getListByNativeSql(sql);
		if(list.size()==0){
			return "0";
		}else{
			return "1";
		}
	}
	
	//上移下移
	@Override
	public MsgRes updateSearchD(String strEnterpriseNo, String strWarehouseNo,
			String lineNo, String custNo, String lineSeqNo, String str)
			throws Exception {
		
		if(str.equals("1")){                     //选中数据上移

			if(!lineSeqNo.equals("0")){            //如果是第一条（路顺），则不处理
				//将选中数据的上一条数据，向下移，即顺序号+1
				String updatePlanDtop=" update oset_line_cust a " +
						              " set a.line_seq_no = a.line_seq_no+1 " +
						              " where a.enterprise_no='"+strEnterpriseNo+"' " +
						              " and a.warehouse_no='"+strWarehouseNo+"' " +
					                  " and a.line_seq_no='"+(Integer.parseInt(lineSeqNo)-1)+"' "+
					                  " and a.line_no='"+lineNo+"' ";
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown=" update oset_line_cust a " +
		              " set a.line_seq_no = a.line_seq_no-1 " +
		              " where a.enterprise_no='"+strEnterpriseNo+"' " +
		              " and a.warehouse_no='"+strWarehouseNo+"' " +
	                  " and a.cust_no='"+custNo+"' " +
	                  " and a.line_no='"+lineNo+"' ";
			   genDao.updateBySql(updatePlanDown);				
			}		
		}else{                                                          //选中数据下移	
		   String sql = " select count(1) from oset_line_cust a "+
				   " where a.enterprise_no='"+strEnterpriseNo+"' " +
		              " and a.warehouse_no='"+strWarehouseNo+"' " +
		              " and a.cust_no='"+custNo+"' " +
	                  " and a.line_no='"+lineNo+"' ";
  
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(lineSeqNo)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown=" update oset_line_cust a " +
			              " set a.line_seq_no = a.line_seq_no-1 " +
			              " where a.enterprise_no='"+strEnterpriseNo+"' " +
			              " and a.warehouse_no='"+strWarehouseNo+"' " +
		                  " and a.line_seq_no='"+(Integer.parseInt(lineSeqNo)+1)+"' "+
		                  " and a.line_no='"+lineNo+"' ";
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop=" update oset_line_cust a " +
		              " set a.line_seq_no = a.line_seq_no+1 " +
		              " where a.enterprise_no='"+strEnterpriseNo+"' " +
		              " and a.warehouse_no='"+strWarehouseNo+"' " +
	                  " and a.cust_no='"+custNo+"' " +
	                  " and a.line_no='"+lineNo+"' ";
			   genDao.updateBySql(updatePlanDtop);		   
		   }
		}	
		return new MsgRes(true,"", "");
	
	}

}
