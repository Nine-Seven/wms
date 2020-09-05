/**
 * 返配批次管理service
 * @author hcx
 */
package com.sealinkin.ridata.service.impl;

import java.util.List;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.model.Ridata_BsetDefbatchModel;
import com.sealinkin.ridata.service.IRidata_BatchService;

@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_BatchImpl implements IRidata_BatchService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取反配列表
	@Override
	public ExtListDataBo<Ridata_BsetDefbatchModel> getRidata_BatchList(
			String enterpriseNo,String warehouseNo, PageBo pageBo,
				String operateDate,String batchNo) throws Exception {		
			String sql="select to_char(t1.operate_date,'yyyy-mm-dd') operateDateText , t1.batch_no, " +
					"'['|| ltrim(t2.warehouse_no)||']'||t2.warehouse_name warehouseText, "+
					"f_get_fieldtext('BSET_DEFBATCH','STATUS',t1.status) statusText "+
					"from bset_defbatch t1 ,bdef_defloc t2 where 1=1 "+
					"and t1.warehouse_no = t2.warehouse_no "+
					"and t1.warehouse_no ='"+warehouseNo+"' " +
					"and t1.enterprise_no=t2.enterprise_no " +
					"and t1.enterprise_no='"+enterpriseNo+"' ";			
			String strTotsql = "select count(1) from bset_defbatch t1 where 1=1 "+
					"and t1.warehouse_no ='"+warehouseNo+"' and t1.enterprise_no='"+enterpriseNo+"' ";
			
			if(operateDate!=null && !operateDate.equals(""))
			{
				sql=sql+" and to_date('"+operateDate+"','yyyy/mm/dd') = t1.operate_date ";
				strTotsql=strTotsql+" and to_date('"+operateDate+"','yyyy/mm/dd') = t1.operate_date ";
			}
			if(batchNo!=null && !batchNo.equals(""))
			{
				sql=sql+" and t1.batch_no = '"+batchNo+"' ";
				strTotsql=strTotsql+" and t1.batch_no = '"+batchNo+"' ";
			}
			sql = sql +"and t1.status = '0' or t1.status = '20'";
			strTotsql = strTotsql +"and t1.status = '0' or t1.status = '20'";
			List<Ridata_BsetDefbatchModel> list = genDao.getListByNativeSql(sql,Ridata_BsetDefbatchModel.class,pageBo.getStart(), pageBo.getPagesize());
			Integer intCount = genDao.getCountByNativeSql(strTotsql);
			ExtListDataBo<Ridata_BsetDefbatchModel> extListBo = new ExtListDataBo<Ridata_BsetDefbatchModel>(list,intCount);
			return extListBo;
		}
	
	@Override
	public List<ComboxBo> getBatchNoForUI(String enterpriseNo,String warehouseNo,String operateDate) throws Exception {
			String strSql="select distinct t1.batch_no value , t1.batch_no text," +
	                "t1.batch_no dropValue " +
					"from bset_defbatch t1 where 1=1 "+
					"and t1.warehouse_no ='"+warehouseNo+"' " +
					"and t1.enterprise_no='"+enterpriseNo+"' ";
			
			if(operateDate!=null && !operateDate.equals(""))
			{
				strSql=strSql+" and to_date('"+operateDate+"','yyyy/mm/dd') = t1.operate_date";
			}
			List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
			return (List<ComboxBo>) list;
		}
	@Override
	public MsgRes endSweep(String enterpriseNo,String warehouseNo, String operateDate,
			String batchNo) throws Exception {
            String strSql="update bset_defbatch t1 set t1.status='15', " +
            	"t1.end_time = (select to_char(sysdate,'hh24:mi') from dual) "+
				"where t1.warehouse_no = '"+warehouseNo+"' " +
				"and t1.enterprise_no='"+enterpriseNo+"' "+
				"and to_date('"+operateDate+"','yyyy/mm/dd') = t1.operate_date "+
				"and t1.batch_no = '"+batchNo+"'";
            genDao.updateBySql(strSql);	
    		return new MsgRes(true, "结束扫描成功！", "");
	}
}
