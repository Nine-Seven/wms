package com.sealinkin.comm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.comm.service.IWmsJobConfigService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.wms.model.Wms_JobConfigModel;
/**
 * 后台管理控制
 * @author hkl
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class WmsJobConfigImpl implements IWmsJobConfigService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	//后台定时调用存储过程（类似JOB）
	@Override
	public MsgRes tscjobConfig() throws Exception {
		/** 
		 * 创建一个thread，然后让它在while循环里一直运行着， 
		 * 通过sleep方法来达到定时任务的效果 
		 * 
		 */ 
        final long timeInterval = 10000;  
         
        Runnable runnable = new Runnable() {  
            public void run() {  
                while (true) {
                   //调用存储过程
                	try {
						tscjobConfigOrcl();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                    try {  
                        Thread.sleep(timeInterval); //停止10000毫秒，10秒
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        };  
        Thread thread = new Thread(runnable);  
        thread.start();  
		  
	    return new MsgRes(true,"","");	
			
	}
	
	//调用存储过程的公用方法(包含校验，根据配置表wms_job_config状态判断是否可以调用)
	public MsgRes tscjobConfigOrcl() throws Exception {
		
		    List inList=new ArrayList();
			List outList=new ArrayList();
			List resultList=new ArrayList();
			
			outList.add("S");
			
			resultList = genDao.execProc(inList, outList, "PKLG_WMS_JOB.p_wmsJobConfig");
			if(resultList.get(0).toString().indexOf("N|")!=-1)
			{
				throw new Exception(resultList.get(0).toString());
			}
		return null;
		
		
	}
	
	//获取后台管理控制表list
	@Override
	public ExtListDataBo<Wms_JobConfigModel> getWms_JobConfugList(
			String currEnterpriseNo, String warehouseNo, PageBo pageBo)
			throws Exception {
		String sql = "select t.*," +
				"f_get_fieldtext('BDEF_DEFARTICLE','STATUS', t.execute_status) as executeStatusText," +
				"f_get_fieldtext('WMS_JOB_CONFIG','RUN_COUNT_TYPE', t.run_time_interval ) runCountTypeText  " +
				"from wms_job_config t ";
	    String totsql = "select count(*) from ("+sql+")";
		
		List<Wms_JobConfigModel> list = genDao.getListByNativeSql(sql,Wms_JobConfigModel.class,pageBo.getStart(), pageBo.getPagesize());
		Integer count = genDao.getCountByNativeSql(totsql);
		ExtListDataBo<Wms_JobConfigModel> extListBo= new ExtListDataBo<Wms_JobConfigModel>(list, count);
        return extListBo;
	
	}
	
	//修改后台管理配置的状态
	@Override
	public MsgRes updateWmsJobConfig(String enterpriseNo,
			String warehouseNo,String procName,String flag) throws Exception {
		//flag。1为禁用；0为启用
		String sql = "update wms_job_config t set " +
				"t.execute_status='"+flag+"' " +
				"where t.enterprise_no='"+enterpriseNo+"' " +
				"and t.warehouse_no='"+warehouseNo+"' " +
				"and t.proc_name='"+procName+"'";
		genDao.updateBySql(sql);
		return new MsgRes(true,"","");
	}
}
