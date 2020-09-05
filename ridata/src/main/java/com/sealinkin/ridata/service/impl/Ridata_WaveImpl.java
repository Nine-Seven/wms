/**
 * 返配波次管理service
 * @author hkl
 */
package com.sealinkin.ridata.service.impl;

import java.util.List;

import com.sealinkin.bset.model.Bset_WaveManageModel;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.ridata.service.IRidata_WaveService;

@SuppressWarnings({"unchecked","rawtypes"})
public class Ridata_WaveImpl implements IRidata_WaveService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取波次列表
	@Override
	public ExtListDataBo<Bset_WaveManageModel> getRidata_WaveList(
			String enterpriseNo,String warehouseNo, PageBo pageBo,
				String status) throws Exception {		
			String sql="select a.enterprise_no,a.wave_no,a.start_time,a.end_time," +
					"a.status,f_get_fieldtext('BSET_DEFBATCH','STATUS',a.status) statusText," +
					"a.operate_date,a.curr_batch,a.wave_type," +
					"f_get_fieldtext('N','WAVE_TYPE',a.wave_type) waveTypeText " +
					"from bset_wave_manage a " +
					"where a.enterprise_no='"+enterpriseNo+"' " +
					"and a.warehouse_no='"+warehouseNo+"' ";		
		
			if(status!=null && !status.equals(""))
			{
				sql=sql+" and a.status = '"+status+"' ";
			}
			String strTotsql = "select count(1) from ("+sql+") ";
			List<Bset_WaveManageModel> list = genDao.getListByNativeSql(sql,Bset_WaveManageModel.class,pageBo.getStart(), pageBo.getPagesize());
			Integer intCount = genDao.getCountByNativeSql(strTotsql);
			ExtListDataBo<Bset_WaveManageModel> extListBo = new ExtListDataBo<Bset_WaveManageModel>(list,intCount);
			return extListBo;
		}
	
	//结束波次
	@Override
	public MsgRes endSweep(String enterpriseNo,String warehouseNo,String workerNo,
			String waveNo) throws Exception {
        String strSql="update bset_wave_manage e set e.status='15', " +
        		"e.updt_date=sysdate,updt_name = '"+workerNo+"' " +
        		"where e.enterprise_no='"+enterpriseNo+"'  " +
        		"and e.warehouse_no='"+warehouseNo+"'  " +
        		"and e.wave_no='"+waveNo+"' and e.status='0' ";
        genDao.updateBySql(strSql);
        String strSql2="update cset_cell_supplier e set e.status='0' " +
        		"where e.enterprise_no='"+enterpriseNo+"'  " +
        		"and e.warehouse_no='"+warehouseNo+"'  " +
        		"and e.wave_no='"+waveNo+"' and e.status='1' ";
        genDao.updateBySql(strSql2);	
		return new MsgRes(true, "结束扫描成功！", "");
}
}
