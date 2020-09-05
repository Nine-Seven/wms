package com.sealinkin.cset.service;

import java.util.List;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_BufferModel;
import com.sealinkin.cset.model.Cset_CellArticleModel;

public interface ICset_BufferService {
	// 获取暂存区信息列表
	public ExtListDataBo<Cset_BufferModel> getBufferList(
			String strEnterpriseNo,
			String strWarehouseNo,String strOwnerNo,
			String strQuery, PageBo pageBo, Integer requestFlag)throws Exception;
	//保存暂存区信息
	public MsgRes saveBuffer_Ware(String enterpriseNo,String strWarehouseNo,String str,String strWorkerNo, String strwareNo,String strareaNo,String strbufferName,String strstatus)throws Exception ;
	/**
	 * 获取库区下拉框
	 * @param strenterpriseNo
	 * @param strwarehouseNo
	 * @param str
	 * @param start
	 * @param pagesize
	 * @author panzhenxing
	 */
	public List<ComboxBo> getBufferWareCombo(
			String strenterpriseNo,
			String strwarehouseNo,
			String str,
			int start,
			int pagesize) ;
	/**
	 * 获取储区下拉框
	 * @param strenterpriseNo
	 * @param strwarehouseNo
	 * @param str
	 * @param start
	 * @param pagesize
	 * @author panzhenxing
	 */
	public List<ComboxBo> getBufferAreaCombo(
			String strenterpriseNo,
			String strwarehouseNo,
			String str,
			int start,
			int pagesize) ;
	
	//通道下拉
		public List<ComboxBo> getBufferStockCombo(String currEnterpriseNo,
				String warehouseNo, String str, int i, int j);
		/**
		 * 获取库区下拉框(Windows)
		 * @param strenterpriseNo
		 * @param strwarehouseNo
		 * @param str
		 * @param start
		 * @param pagesize
		 * @return
		 */
		public List<ComboxBo> getBufferAddWareCombo(
				String strenterpriseNo,
				String strwarehouseNo,
				String str,
				int start,
				int pagesize) ;
		//判断暂存区货位是否唯一
		public List<String> cellCheck(String enterpriseNo,String strWarehouseNo,
				 String strwareNo,String strareaNo,String strstockNo,String strQuery1) throws Exception;
		
		/**
		 * 获取储区下拉框(Windows)
		 * @param strenterpriseNo
		 * @param strwarehouseNo
		 * @param str
		 * @param start
		 * @param pagesize
		 * @return
		 */
		public List<ComboxBo> getBufferAddAreaCombo(
				String strenterpriseNo,
				String strwarehouseNo,
				String str,
				int start,
				int pagesize) ;
		
		//通道下拉
			public List<ComboxBo> getBufferAddStockCombo(String currEnterpriseNo,
					String warehouseNo,String str);
			//储位下拉	
			public abstract List<ComboxBo> getBufferAddCellCombo(String enterpriseNo,String warehouseNo,
					 String str,String strQuery);
			/**
			 * 删除暂存区
			 */
		    public MsgRes deleteBuffer(String enterpriseNo,String strWarehouseNo,String str,String wareNo,String areaNo,String stockNo,String cellNo)throws Exception;
}
