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
import com.sealinkin.report.service.IAuto_SetService;
import com.sealinkin.util.CommUtil;
import com.sealinkin.wms.model.Wms_Defcustom_DModel;
import com.sealinkin.wms.model.Wms_Defcustom_MenuModel;
import com.sealinkin.wms.po.Wms_DefcustomD;
import com.sealinkin.wms.po.Wms_Defcustom_Menu;
import com.sealinkin.wms.po.Wms_Defcustom_MenuId;


@SuppressWarnings({"unchecked","rawtypes"})
public class Auto_SetImp implements IAuto_SetService {
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
	public List<ComboxBo> getModubleMenu(String enterpriseNo) throws Exception {	

		String strSql="select distinct a.folder_id value, " +
			      " a.menu_caption text, " +
			      " '[' || ltrim(a.folder_id) || ']' || a.menu_caption dropValue " +
			      " from bset_menu_folder a " +
			      " where  a.enable_flag = '1' " +
			      "   and  a.set_flag='2' " +
			      "   and a.enterprise_no='"+enterpriseNo+"' " +
			      "   order by a.folder_id ";
		
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class, 0, 100);
		return (List<ComboxBo>) list;
	}
	
	//保存子设置
	@Override
	public MsgRes saveSubReport(String enterpriseNo, String workerNo,
			String modubleId, String customId, String customName)
			throws Exception {

		
		if(customId.equals("保存时自动生成")){
			//保存wms_defreportformenu
			String sql=" select max(a.custom_id) from  WMS_DEFCUSTOM_MENU a " +
					   " where a.enterprise_no='"+enterpriseNo+"' " +
					   " and a.module_id ='"+modubleId+"' ";
			
			List list=genDao.getListByNativeSql(sql);
			
			if(list !=null && list.size()>=1 && list.get(0)!=null){
				String pmg=list.get(0).toString().substring(list.get(0).toString().length()-3, list.get(0).toString().length());	
				int temp=Integer.parseInt(pmg)+1;				
				customId=modubleId+"_"+String.format("%03d", temp);							
			}else{
				customId=modubleId+"_"+String.format("%03d", 1);
			}
			
			Wms_Defcustom_Menu wdf = new Wms_Defcustom_Menu();
			Wms_Defcustom_MenuId id = new Wms_Defcustom_MenuId();
			
			id.setModuleId(modubleId);
			id.setCustomId(customId);
			id.setEnterpriseNo(enterpriseNo);		
			wdf.setId(id);
			wdf.setShow("1");
			wdf.setRgstDate(new Date());
			wdf.setRgstName(workerNo);
			
			//获取排序
			String strSql="select max(a.seq) from WMS_DEFCUSTOM_MENU a " +
					"where a.enterprise_no='"+enterpriseNo+"' " +
					" and a.module_id='"+modubleId+"' "+
					" and a.seq is not null " ;
			
			List listTmp=genDao.getListByNativeSql(strSql);
			if(listTmp!=null && listTmp.size()>=1 && listTmp.get(0)!=null){
				BigDecimal bd=new BigDecimal(listTmp.get(0).toString());
				wdf.setSeq(bd.add(new BigDecimal("1")));
			}else{
				BigDecimal bd=new BigDecimal("1");
				wdf.setSeq(bd);
			}
			
			this.genDao.save(wdf);
			
			//保存WMS_DEFCUSTOM_M
			String insertSql=" insert into WMS_DEFCUSTOM_M (enterprise_no,custom_id,custom_name)" +
					         " values('"+enterpriseNo+"','"+customId+"','"+customName+"')";
			genDao.updateBySql(insertSql);
			
					
		}else{
			String updateSql=" update WMS_DEFCUSTOM_M a " +
					         " set a.custom_name='"+customName+"' ," +
					         " where a.custom_id='"+customId+"' ";
			genDao.updateBySql(updateSql);
		}
			
		return new MsgRes(true,"","");
	
	}
	@Override
	public ExtListDataBo<Wms_Defcustom_MenuModel> getReportformenu(
			String currEnterpriseNo, String strQuery, PageBo pageBo)
			throws Exception {
		String sql=" select a.module_id, a.custom_id, b.custom_name,  a.show, a.seq , " +
				   "  f_get_fieldtext('N','YESORNO',a.show) showText  "+
				   " from WMS_DEFCUSTOM_MENU a , WMS_DEFCUSTOM_M b    "+
				   "  where a.custom_id=b.custom_id" +
				   " and a.enterprise_no = b.enterprise_no" +
				   " and a.enterprise_no='"+currEnterpriseNo+"' ";
	
		
		String strTotsql="select count(1) from WMS_DEFCUSTOM_MENU a , WMS_DEFCUSTOM_M b    "+
				   "  where a.custom_id=b.custom_id" +
				   " and a.enterprise_no = b.enterprise_no" +
				   " and a.enterprise_no='"+currEnterpriseNo+"' ";
		
		if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			strTotsql=strTotsql+ws;
		}		
		
		sql=sql+" order by a.seq";
		List<Wms_Defcustom_MenuModel> list = genDao.getListByNativeSql(sql,Wms_Defcustom_MenuModel.class,pageBo.getStart(), 1000);
		Integer intCount = genDao.getCountByNativeSql(strTotsql);
		ExtListDataBo<Wms_Defcustom_MenuModel> extListBo = new ExtListDataBo<Wms_Defcustom_MenuModel>(list,intCount);
		return extListBo;
	}
	@Override
	public List<ComboxBo> getReportList(String modubleId) throws Exception {
		String strSql = "select distinct a.custom_id value,b.custom_name text," +
				"'['|| ltrim(a.custom_id)||']'||b.custom_name dropValue, " +
				"a.seq from WMS_DEFCUSTOM_MENU a," +
				"WMS_DEFCUSTOM_M b where a.custom_id=b.custom_id " +
				"and a.show='1' " +
				"and a.module_id='"+modubleId+"' order by a.seq";
		//}
		List<ComboxBo> list=genDao.getListByNativeSql(strSql, ComboxBo.class);
		return list;
	}
	
	//获取参数
	@Override
	public List<Wms_Defcustom_DModel> getCustomD(String enterpriseNo,
			String customId) throws Exception {
		String sql=" select a.custom_id,a.param_name,a.param_type,a.tablename,a.columnname " +
				   "   from wms_defcustom_d a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.custom_id='"+customId+"' ";
		List<Wms_Defcustom_DModel> list = genDao.getListByNativeSql(sql, Wms_Defcustom_DModel.class);
		return list;
	}
	@Override
	public MsgRes saveCustomD(String wmsCustomD) throws Exception {
		List<Wms_DefcustomD> listCust =JSON.parseArray(wmsCustomD,Wms_DefcustomD.class);
		
		if(listCust!=null){
			deleteCustomD(listCust.get(0).getId().getEnterpriseNo(),listCust.get(0).getId().getCustomId());
		}
		this.genDao.saveList(listCust);
		return new MsgRes(true,"保存成功","");
	}
	
	//删除明细
	public void deleteCustomD(String enterpriseNo,String customID){
		String delsql="delete wms_defcustom_d where custom_id='"+customID+"' " +
		         "and enterprise_no='"+enterpriseNo+"'";
		genDao.updateBySql(delsql);
	}
	
	//上移、下移
	@Override
	public MsgRes seqencingDfm(String currEnterpriseNo, String modubleId,
			String customId, String orderNo, String flag) throws Exception {
		
		if(flag.equals("1")){                     //选中数据上移
			System.out.println("上移");
			if(!orderNo.equals("1")){            //如果是第一条，则不处理
				//将选中的数据的上一条数据，顺序号+1，即向下移
				String updatePlanDtop=" update wms_defcustom_menu a " +
						              " set a.order_no = a.order_no+1 " +
						              " where a.enterprise_no='"+currEnterpriseNo+"' " +
					                  " and a.module_id='"+modubleId+"' " +
					                  " and a.order_no="+(Integer.parseInt(orderNo)-1);
			   genDao.updateBySql(updatePlanDtop);
			   
			 //将选中的数顺序号-1，即向上移
			 String updatePlanDown=" update wms_defcustom_menu a " +
						           " set a.seq = a.seq-1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.module_id='"+modubleId+"' " +
					               " and a.custom_id='"+customId+"' ";
			   genDao.updateBySql(updatePlanDown);				
			}		
		}else{                                                          //选中数据下移	
			System.out.println("下移");
		   String sql = " select count(1) from wms_defcustom_menu a "+
				        " where a.enterprise_no='"+currEnterpriseNo+"' " +
				        " and a.module_id='"+modubleId+"' ";
  
		   List list = genDao.getListByNativeSql(sql);
		   
		   if(!list.get(0).toString().equals(orderNo)){  //如果是最后一条数据，则不处理
			 //将选中的数据的下一条数据，顺序号-1，即向上移
				String updatePlanDdown=" update wms_defcustom_menu a " +
						               " set a.seq = a.seq-1  " +
						               " where a.enterprise_no='"+currEnterpriseNo+"' " +
						               " and a.module_id='"+modubleId+"' " +
					                   " and a.seq="+(Integer.parseInt(orderNo)+1);
			   genDao.updateBySql(updatePlanDdown);
			   
			 //将选中的数顺序号+1，即向下移
			 String updatePlanDtop=" update wms_defcustom_menu a " +
						           " set a.seq = a.seq+1 " +
						           " where a.enterprise_no='"+currEnterpriseNo+"' " +
						           " and a.module_id='"+modubleId+"' " +
					               " and a.custom_id='"+customId+"' ";
			   genDao.updateBySql(updatePlanDtop);		   
		   }
		}	
		return new MsgRes(Boolean.valueOf(true),"", "");
	}
	//修改子菜单是否可见
	@Override
	public MsgRes changeShow(String enterpriseNo, String modubleId,
			String customId, String show) throws Exception {
		String sql=" update wms_defcustom_menu a " +
				   " set a.show='"+show+"' " +
				   " where a.enterprise_no='"+enterpriseNo+"' " +
				   " and a.module_id='"+modubleId+"' " +
				   " and a.custom_id='"+customId+"' ";
		genDao.updateBySql(sql);
		return new MsgRes(true,"","");
	}
}
