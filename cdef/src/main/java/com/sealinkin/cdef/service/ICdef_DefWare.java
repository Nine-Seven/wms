package com.sealinkin.cdef.service;

import java.io.File;
import java.util.List;

import com.sealinkin.cdef.model.Cdef_DefareaModel;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.cdef.model.Cdef_DefwareModel;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.stock.model.Stock_ContentModel;

@SuppressWarnings("rawtypes")
public interface ICdef_DefWare {
	public Boolean saveCdef_DefWare(String str,String strLog,String strWorkerNo)throws RuntimeException;
	
	public ExtListDataBo<Cdef_DefwareModel> getCdef_DefWare(	
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String str,
			PageBo pageBo,
			Integer requestFlag);
	
	public String getWareNoOfLength(String enterpriseNo, String warehouseNo) throws Exception;
	
	public Boolean saveCdef_DefArea(String str,String strLog,String strWorkerNo)throws Exception;
	
	public ExtListDataBo<Cdef_DefareaModel> getCdef_DefArea(
			String enterpriseNo,
			String warehouseNo,
			String workerOwner,
			String str,
			PageBo pageBo,
			Integer requestFlag);
	
	public String getAreaNoOfLength(String enterpriseNo, String warehouseNo) throws Exception;
	
	public MsgRes saveCdef_DefCell(String str)throws Exception;
	
	public List<Cdef_DefcellModel> getCdef_DefCellDetails(String enterpriseNo,
			String warehouseNo,
			String str);
	
	public MsgRes updateCellWareArea(String enterpriseNo,String strWarehouseNo,String str,
			String strWareNo,String strAreaNo)throws Exception;

	public List getCdef_DefCell(String str,String enterpriseNo,String strWarehouseNo);
	
	public List<ComboxBo> getCdef_DefWareCombo(
			String enterpriseNo,
			String warehouseNo,
			String str,
			int start,
			int pagesize) ;
	
	public List<ComboxBo> getCdef_DefAreaCombo(
			String enterpriseNo,
			String warehouseNo,
			String str,
			int start,
			int pagesize) ;
	
	public List<ComboxBo> getCdef_DefCellCombo(
			String enterpriseNo,
			String warehouseNo,
			String jsonStr,
			String str,
		    int start,
			int pagesize) ;
	
	public List<ComboxBo> getCdef_DefCellAllCombo(
			String enterpriseNo,
			String warehouseNo,
			String jsonStr,
			String str) ;
	
	public List<ComboxBo> getCombo(String[] str,
			int start,
			int pagesize) ;
	
	public ExtListDataBo<Stock_ContentModel> getStock_Content(String str,
			PageBo pageBo);

	public ExtListDataBo<Cdef_DefareaModel> existsAreaList(String enterpriseNo,String queryStr,
			String warehouseNo);

	public ExtListDataBo<Cdef_DefwareModel> existsWareList(String enterpriseNo,String queryStr,
			String warehouseNo);
	
	/**
	 * 删除库区
	 */
    public MsgRes deleteCdefDefware(String enterpriseNo,String strWarehouseNo,String wareNo)throws Exception;
    
    /**
     * 删除储区
     */
    public MsgRes deleteCdefDefarea(String enterpriseNo,String strWarehouseNo,String strWareNo,String strAreaNo)throws Exception;

	public List<ComboxBo> getReplenishRuleCombo( int i, int j);

	//获取储区属性
	public ExtListDataBo<Cdef_DefareaModel> getAttribute(String currEnterpriseNo,
			String warehouseNo, String strWareNo, String strAreaNo);
	

//	private String nMinStockX;
//	private String nMaxStockX;
//	private String nMinBayX;
//	private String nMaxBayX;
//	private String strCodePrefix
	//生产储位
	public MsgRes produceCell(String str, 
			String nMinStockNo, 
			String nMaxStockNo,
			String nMinStockY, 
			String nMaxStockY, 
			String nMinStockX, 
			String nMaxStockX,
			String nMinBayX, 
			String nMaxBayX, 
			String strCodePrefix, 
			String perf1, 
			String perf2, 
			String perf3, 
			String perf4)throws Exception;

	//获取储区（储位添加窗口）
	public List<ComboxBo> getCdef_DefAreaComboforWindow(
			String currEnterpriseNo, String warehouseNo, String str, int i,
			int j);

	//通道下拉
	public List<ComboxBo> getCdef_DefStockCombo(String currEnterpriseNo,
			String warehouseNo, String str, int i, int j);
	
	//格下拉
	public List<ComboxBo> getCdef_DefStockXCombo(String currEnterpriseNo,
			String warehouseNo, String str)throws Exception;
	
	//位下拉
	public List<ComboxBo> getCdef_DefbayXCombo(String currEnterpriseNo,
			String warehouseNo, String str)throws Exception;
	
	//层下拉
	public List<ComboxBo> getCdef_DefStockYCombo(String currEnterpriseNo,
			String warehouseNo, String str);

	//检查储位是否重复
	public MsgRes checkCell(String enterpriseNo, String warehouseNo,
			String strWareNo, String strAreaNo,String minStockNo,
			String maxStockNo, String minStockY,String maxStockY, 
			String minStockX, String maxStockX,String minBayX, String maxBayX);

	//获取库区（储位添加窗口）
	public List<ComboxBo> getCdef_DefWareComboforWindow(
			String currEnterpriseNo, String warehouseNo, String str, int i,
			int j);
	//储位导入
	public MsgRes tscDefcellUpLoad(String currEnterpriseNo, String warehouseNo,
			String workerNo, String ownerNo, File file)throws Exception;

	//批量修改储位
	public MsgRes updateCellStatus(String currEnterpriseNo, String warehouseNo,
			String str, String flag)throws Exception;

	
}
