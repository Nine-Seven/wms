package com.sealinkin.report.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.report.service.IReport_SetService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_DefSearch_DModel;
import com.sealinkin.wms.model.Wms_DefSearch_MModel;
import com.sealinkin.wms.model.Wms_DefreportformenuModel;
import com.sealinkin.wms.po.Wms_DefSearch_D;
import com.sealinkin.wms.po.Wms_DefSearch_M;
import com.sealinkin.wms.po.Wms_DefSearch_MId;
import com.sealinkin.wms.po.Wms_Defmodulequerycolumn;
import com.sealinkin.wms.po.Wms_Defreportformenu;
import com.sealinkin.wms.po.Wms_DefreportformenuId;

@SuppressWarnings({"rawtypes","unchecked"})
public class Report_SetImpl implements IReport_SetService {
private IGenericManager genDao;
    
	public IGenericManager getGenDao()
    {
        return genDao;
    }
	public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }

	@Override
	public List<ComboxBo> getModubleMenu(String currEnterpriseNo)
			throws Exception {	
//		String strSql="select distinct a.module_id value,b.menu_caption text," +
//					"'['|| ltrim(a.module_id)||']'||b.menu_caption dropValue " +
//					"from wms_defreportformenu a,bset_menu_folder b " +
//					"where a.enterprise_no=b.enterprise_no " +
//					"and a.module_id=b.folder_id " +
//					"and a.enterprise_no='"+currEnterpriseNo+"' " ;
		
		String strSql="select distinct a.folder_id value, " +
				      " a.menu_caption text, " +
				      " '[' || ltrim(a.folder_id) || ']' || a.menu_caption dropValue " +
				      " from bset_menu_folder a " +
				      " where  a.enable_flag = '1' " +
				      "   and  a.set_flag='1' " +
				      "   and a.enterprise_no='"+currEnterpriseNo+"' " +
				      "   order by a.folder_id ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//获取子菜单
	@Override
	public ExtListDataBo<Wms_DefreportformenuModel> getReportformenu(
			String currEnterpriseNo,String strQuery,PageBo pageBo) throws Exception {
	
		String sql=" select a.module_id, a.pgm_id,b.proc_name,a.show,a.order_no, " +
				   " f_get_fieldtext('N','YESORNO',a.show) showText "+
				   " from wms_defreportformenu a ,wms_defsearch_m b  "+
				   " where a.pgm_id=b.pgm_id " +
				   " and a.enterprise_no='"+currEnterpriseNo+"' ";
	
		
		String strTotsql="select count(1) from wms_defreportformenu a ,wms_defsearch_m b  "+
				   " where a.pgm_id=b.pgm_id " +
				   " and a.enterprise_no='"+currEnterpriseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		
		sql=sql+" order by a.order_no";
		List<Wms_DefreportformenuModel> list = genDao.getListByNativeSql(sql,Wms_DefreportformenuModel.class,pageBo.getStart(), 1000);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_DefreportformenuModel> extListBo = new ExtListDataBo<Wms_DefreportformenuModel>(list,intCount);
		return extListBo;
	}
	@Override
	public Wms_DefSearch_MModel getDefSerchM(String currEnterpriseNo, String pmgId)
			throws Exception {
		String sql=" select * from wms_defsearch_m a " +
				   " where a.pgm_id='"+pmgId+"' " +
				   " and a.enterprise_no='"+currEnterpriseNo+"' ";
		List<Wms_DefSearch_MModel> list = genDao.getListByNativeSql(sql,Wms_DefSearch_MModel.class,0,1);
		
		if(list.size()==1){
			return list.get(0);		
		}
		return null;
	}
	@Override
	public ExtListDataBo<Wms_DefSearch_DModel> getWmsDefsearchD(String enterpriseNo,String strQuery)
			throws Exception {
		String sql=" select a.*, f_get_fieldtext('N','YESORNO',a.statistics_flag) statisticsFlagText "+
				   " from wms_defsearch_d a " +
				   " where a.enterprise_no='"+enterpriseNo+"' ";
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
		}		
		sql=sql+" order by a.seq ";
		
		List<Wms_DefSearch_DModel> list = genDao.getListByNativeSql(sql,Wms_DefSearch_DModel.class,0,1000);
		ExtListDataBo<Wms_DefSearch_DModel> extListBo = new ExtListDataBo<Wms_DefSearch_DModel>(list,1000);
		return extListBo;
	}
	@Override
	public MsgRes saveSubReport(String enterpriseNo, String workerNo,
			String modubleId, String pmgId, String procName,
			String needWarehouseNo, String needOwner) throws Exception {
		
		if(pmgId.equals("保存时自动生成")){
			//保存wms_defreportformenu
			String sql=" select max(a.pgm_id) from  wms_defreportformenu a " +
					   " where a.enterprise_no='"+enterpriseNo+"' " +
					   " and a.module_id ='"+modubleId+"' ";
			
			List list=genDao.getListByNativeSql(sql);
			
			if(list.get(0)!=null && list.size()>=1){
				String pmg=list.get(0).toString().substring(list.get(0).toString().length()-3, list.get(0).toString().length());	
				int temp=Integer.parseInt(pmg)+1;				
				pmgId=modubleId+"_"+String.format("%03d", temp);							
			}else{
				pmgId=modubleId+"_"+String.format("%03d", 1);
			}
			
			Wms_Defreportformenu wdf = new Wms_Defreportformenu();
			Wms_DefreportformenuId id = new Wms_DefreportformenuId();
			
			id.setModuleId(modubleId);
			id.setPgmId(pmgId);
			id.setEnterpriseNo(enterpriseNo);		
			wdf.setId(id);
			wdf.setShow("1");
			wdf.setRgstDate(new Date());
			wdf.setRgstName(workerNo);
			
			//获取排序
			String strSql="select max(a.order_no) from wms_defreportformenu a " +
					"where a.enterprise_no='"+enterpriseNo+"' " +
					" and a.module_id='"+modubleId+"' "+
					" and a.order_no is not null " ;
			
			List listTmp=genDao.getListByNativeSql(strSql);
			if(listTmp.get(0)!=null && listTmp.size()>=1){
				BigDecimal bd=new BigDecimal(listTmp.get(0).toString());
				wdf.setOrderNo(bd.add(new BigDecimal("1")));
			}else{
				BigDecimal bd=new BigDecimal("1");
				wdf.setOrderNo(bd);
			}
			
			this.genDao.save(wdf);
			
			//保存wms_defsearch_m
			String insertSql=" insert into wms_defsearch_m (enterprise_no,pgm_id,proc_name,need_enterprise_no)" +
					         " values('"+enterpriseNo+"','"+pmgId+"','"+procName+"','1')";
			genDao.updateBySql(insertSql);
			
					
		}else{
			String updateSql=" update wms_defsearch_m a " +
					         " set a.proc_name='"+procName+"' ," +
					         " a.need_loc='"+needWarehouseNo+"', " +
					         " a.need_owner='"+needOwner+"' " +
					         " where a.pgm_id='"+pmgId+"' ";
			genDao.updateBySql(updateSql);
		}
			
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes changeShow(String enterpriseNo, String modubleId,
			String pmgId, String show) throws Exception {
		
		String sql=" update wms_defreportformenu a " +
				   " set a.show='"+show+"' " +
				   " where a.enterprise_no='"+enterpriseNo+"' " +
				   " and a.module_id='"+modubleId+"' " +
				   " and a.pgm_id='"+pmgId+"' ";
		genDao.updateBySql(sql);
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes changeSql(String enterpriseNo, String pmgId,
			String beforeTreatment, String preparedSql, String afterTreatment)
			throws Exception {
		
		String sql=" select * from wms_defsearch_m a " +
				   " where a.pgm_id='"+pmgId+"' " +
				   " and a.enterprise_no='"+enterpriseNo+"' ";
		List<Wms_DefSearch_MModel> list = genDao.getListByNativeSql(sql,Wms_DefSearch_MModel.class,0,1);
		
		if(list!=null && list.size()==1){
						
			Wms_DefSearch_MId id = new Wms_DefSearch_MId();
			id.setEnterpriseNo(enterpriseNo);
			id.setPgmId(pmgId);
			
			Wms_DefSearch_M wdw = new Wms_DefSearch_M();							
			wdw.setId(id);
			wdw.setPreparedSql(preparedSql);
			wdw.setTotalSql(list.get(0).getTotalSql());
			wdw.setNeedOwner(list.get(0).getNeedOwner());
			wdw.setOwnerExpression(list.get(0).getOwnerExpression());
			wdw.setTableAlias(list.get(0).getTableAlias());
			wdw.setNeedLoc(list.get(0).getNeedLoc());
			wdw.setLocExpression(list.get(0).getLocExpression());
			wdw.setProcName(list.get(0).getProcName());
			wdw.setNeedEnterpriseNo(list.get(0).getNeedEnterpriseNo());
			wdw.setBeforeTreatment(beforeTreatment);
			wdw.setAfterTreatment(afterTreatment);
								
			this.genDao.saveOrUpdateObj(wdw);
		}
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes saveSearchD(String searchD, String workerNo) throws Exception {
		Wms_DefSearch_D d=(Wms_DefSearch_D)JSON.parseObject(searchD,Wms_DefSearch_D.class);
		
		String strSql="select a.seq from wms_defsearch_d a " +
				"where a.enterprise_no='"+d.getId().getEnterpriseNo()+"' "+
				" and a.pgm_id='"+d.getId().getPgmId()+"' " +
				" and a.seq is not null " +
				" order by a.seq desc";
		
		List list=genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			BigDecimal bd=new BigDecimal(list.get(0).toString());
			d.setSeq(bd.add(new BigDecimal("1")));
		}else{
			BigDecimal bd=new BigDecimal("1");
			d.setSeq(bd);
		}
		
		d.setRgstDate(new Date());
		d.setRgstName(workerNo);
		this.genDao.save(d);
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes deleteSearchD(String currEnterpriseNo, String pmgId,
			String fieldId, String seq) throws Exception {
		String strSql=" delete from wms_defsearch_d a " +
					  " where a.enterprise_no='"+currEnterpriseNo+"' "+
				       " and a.pgm_id='"+pmgId+"' " +
				       " and a.field_id='"+fieldId+"' " ;
		this.genDao.exceuteSql(strSql);
		
		String updateSql=" update wms_defsearch_d a " +
		                 " set a.seq = a.seq-1 " +
		                 " where a.seq>"+Integer.parseInt(seq)+
		                 " and a.pgm_id='"+pmgId+"' " +
		                 " and a.enterprise_no='"+currEnterpriseNo+"' ";
		genDao.updateBySql(updateSql);

		
		return new MsgRes(true,"","");
	}
	@Override
	public List<ComboxBo> getFieldType() throws Exception {
		String strSql=" select a.text value,a.text text,a.text dropValue " +
				      " from wms_deffieldval a " +
				      " where a.table_name='N' and a.colname='FIELD_TYPE' ";
	
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes changeSearchD(String searchD, String workerNo)
			throws Exception {
		Wms_DefSearch_D d=(Wms_DefSearch_D)JSON.parseObject(searchD,Wms_DefSearch_D.class);
		
		String sql=" update wms_defsearch_d a " +
				   " set a.field_name='"+d.getFieldName()+"'," +
				   " a.field_type='"+d.getFieldType()+"'," +
				   " a.width='"+d.getWidth()+"',"+
				   " a.statistics_flag='"+d.getStatisticsFlag()+"' "+
				   " where a.enterprise_no='"+d.getId().getEnterpriseNo()+"' " +
				   " and a.pgm_id='"+d.getId().getPgmId()+"' " +
				   " and a.field_id='"+d.getId().getFieldId()+"'" ;
		genDao.updateBySql(sql);
		return new MsgRes(true,"","");
	}
	@Override
	public List<ComboxBo> getXType() throws Exception {
		String strSql=" select a.text value,a.text text,a.text dropValue " +
			      " from wms_deffieldval a " +
			      " where a.table_name='N' and a.colname='X_TYPE' ";

	List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
	return (List<ComboxBo>) list;
	}
	@Override
	public MsgRes saveQuery(String searchD) throws Exception {
		Wms_Defmodulequerycolumn d=(Wms_Defmodulequerycolumn)JSON.parseObject(searchD,Wms_Defmodulequerycolumn.class);
		
		String strSql="select a.orderno from WMS_DefModuleQueryColumn a " +
				"where a.enterprise_no='"+d.getId().getEnterpriseNo()+"' "+
				" and a.moduleid='"+d.getId().getModuleid()+"' " +
				" order by a.orderno desc";
		
		List list=genDao.getListByNativeSql(strSql);
		if(list.size()>0){
			BigDecimal bd=new BigDecimal(list.get(0).toString());
			d.setOrderno(bd.add(new BigDecimal("1")));
		}else{
			BigDecimal bd=new BigDecimal("1");
			d.setOrderno(bd);
		}
		
		this.genDao.save(d);
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes deleteQuery(String currEnterpriseNo, String moduleId,
			String columnid, String orderNo) throws Exception{
		String strSql="delete from wms_defmodulequerycolumn a " +
				  " where a.enterprise_no='"+currEnterpriseNo+"' "+
			       " and a.moduleid='"+moduleId+"' " +
			       " and a.columnid='"+columnid+"' " ;
	this.genDao.exceuteSql(strSql);
	
	
	String updateSql=" update wms_defmodulequerycolumn a " +
			         " set a.orderno = a.orderno-1 " +
			         " where a.orderno>"+Integer.parseInt(orderNo)+
			         " and a.enterprise_no='"+currEnterpriseNo+"' "+
			         " and a.moduleid='"+moduleId+"' ";
	genDao.updateBySql(updateSql);
	
	return new MsgRes(true,"","");
	}
	@Override
	public MsgRes changeQuery(String searchD) throws Exception {
		Wms_Defmodulequerycolumn d=(Wms_Defmodulequerycolumn)JSON.parseObject(searchD,Wms_Defmodulequerycolumn.class);
		
		String sql=" update wms_defmodulequerycolumn a " +
				   " set a.columnname='"+d.getColumnname()+"'," +
				   " a.xtype='"+d.getXtype()+"'," +
				   " a.fieldtable='"+d.getFieldtable()+"',"+
				   " a.fieldcolumn='"+d.getFieldcolumn()+"' "+
				   " where a.enterprise_no='"+d.getId().getEnterpriseNo()+"' " +
				   " and a.moduleid='"+d.getId().getModuleid()+"' " +
				   " and a.columnid='"+d.getId().getColumnid()+"'" ;
		genDao.updateBySql(sql);
		
		return new MsgRes(true,"","");
	}
	@Override
	public MsgRes seqencingDfm(String currEnterpriseNo, String modubleId,
			String pmgId,String orderNo, String flag) {
		

		if(flag.equals("1")){                     //选中数据上移
			System.out.println("上移");
			if(!orderNo.equals("1")){            //如果是第一条，则不处理
				//将选中的数据的上一条数据，顺序号+1，即向下移
				String updatePlanDtop=" update wms_defreportformenu a " +
						              " set a.order_no = a.order_no+1 " +
						              " where a.enterprise_no='"+currEnterpriseNo+"' " +
					                  " and a.module_id='"+modubleId+"' " +
					                  " and a.order_no="+(Integer.parseInt(orderNo)-1);
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown=" update wms_defreportformenu a " +
						           " set a.order_no = a.order_no-1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.module_id='"+modubleId+"' " +
					               " and a.pgm_id='"+pmgId+"' ";
			   genDao.updateBySql(updatePlanDown);				
			}		
		}else{                                                          //选中数据下移	
			System.out.println("下移");
		   String sql = " select count(1) from wms_defreportformenu a "+
				        " where a.enterprise_no='"+currEnterpriseNo+"' " +
				        " and a.module_id='"+modubleId+"' ";
  
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(orderNo)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown=" update wms_defreportformenu a " +
						               " set a.order_no = a.order_no-1  " +
						               " where a.enterprise_no='"+currEnterpriseNo+"' " +
						               " and a.module_id='"+modubleId+"' " +
					                   " and a.order_no="+(Integer.parseInt(orderNo)+1);
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop=" update wms_defreportformenu a " +
						           " set a.order_no = a.order_no+1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.module_id='"+modubleId+"' " +
					               " and a.pgm_id='"+pmgId+"' ";
			   genDao.updateBySql(updatePlanDtop);		   
		   }
		}	
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	@Override
	public MsgRes seqencingQuery(String currEnterpriseNo, String moduleId,
			String columnid, String orderNo, String flag) {
		
		if(flag.equals("1")){                     //选中数据上移
			System.out.println("上移");
			if(!orderNo.equals("1")){            //如果是第一条，则不处理
				//将选中的数据的上一条数据，顺序号+1，即向下移
				String updatePlanDtop=" update wms_defmodulequerycolumn a " +
						              " set a.orderno = a.orderno+1 " +
						              " where a.enterprise_no='"+currEnterpriseNo+"' " +
					                  " and a.moduleid ='"+moduleId+"' " +
					                  " and a.orderno="+(Integer.parseInt(orderNo)-1);
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown=" update wms_defmodulequerycolumn a " +
						           " set a.orderno = a.orderno-1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.moduleid ='"+moduleId+"' " +
					               " and columnid='"+columnid+"' ";
			   genDao.updateBySql(updatePlanDown);				
			}		
		}else{                                                          //选中数据下移	
			System.out.println("下移");
		   String sql = " select count(1) from wms_defmodulequerycolumn a "+
				        " where a.enterprise_no='"+currEnterpriseNo+"' " +
				        " and a.moduleid ='"+moduleId+"' ";
  
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(orderNo)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown=" update wms_defmodulequerycolumn a " +
						               " set a.orderno = a.orderno-1  " +
						               " where a.enterprise_no='"+currEnterpriseNo+"' " +
						               " and a.moduleid ='"+moduleId+"' " +
					                   " and a.orderno="+(Integer.parseInt(orderNo)+1);
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop=" update wms_defmodulequerycolumn a " +
						           " set a.orderno = a.orderno+1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.moduleid ='"+moduleId+"' " +
						           " and a.columnid='"+columnid+"' ";
			   genDao.updateBySql(updatePlanDtop);		   
		   }
		}	
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	@Override
	public MsgRes seqencingSearchD(String currEnterpriseNo, String pmgId,
			String fieldId, String seq, String flag) throws Exception {
		
		if(flag.equals("1")){                     //选中数据上移

			if(!seq.equals("1")){            //如果是第一条，则不处理
				//将选中的数据的上一条数据，顺序号+1，即向下移
				String updatePlanDtop=" update wms_defsearch_d a " +
						              " set a.seq = a.seq+1 " +
						              " where a.enterprise_no='"+currEnterpriseNo+"' " +
					                  " and a.pgm_id='"+pmgId+"' " +
					                  " and a.seq="+(Integer.parseInt(seq)-1);
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown=" update wms_defsearch_d a " +
						           " set a.seq = a.seq-1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.pgm_id='"+pmgId+"' " +
					               " and a.field_id='"+fieldId+"' ";
			   genDao.updateBySql(updatePlanDown);				
			}		
		}else{                                                          //选中数据下移	
			System.out.println("下移");
		   String sql = " select count(1) from wms_defsearch_d a "+
				        " where a.enterprise_no='"+currEnterpriseNo+"' " +
				        " and a.pgm_id='"+pmgId+"' ";
  
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(seq)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown=" update wms_defsearch_d a " +
						               " set a.seq = a.seq-1  " +
						               " where a.enterprise_no='"+currEnterpriseNo+"' " +
						               " and a.pgm_id='"+pmgId+"' " +
					                   " and a.seq="+(Integer.parseInt(seq)+1);
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop=" update wms_defsearch_d a " +
						           " set a.seq = a.seq+1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.pgm_id='"+pmgId+"' " +
					               " and a.field_id='"+fieldId+"' ";
			   genDao.updateBySql(updatePlanDtop);		   
		   }
		}	
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	
}
