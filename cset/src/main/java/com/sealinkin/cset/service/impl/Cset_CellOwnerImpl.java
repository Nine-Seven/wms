package com.sealinkin.cset.service.impl;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;
import com.sealinkin.cset.model.Cset_CellArticleModel;
import com.sealinkin.cset.po.Cset_OwnerCell;
import com.sealinkin.cset.service.ICset_CellOwnerService;
import com.sealinkin.dao.service.IGenericManager;

import java.util.Collection;
import java.util.List;


import net.sf.json.JSONArray;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Cset_CellOwnerImpl implements ICset_CellOwnerService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return this.genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 货主下拉
	 */
	
	@Override
	public List<ComboxBo> getOwnerCombo(String enterpriseNo,String strOwnerNo
			) throws Exception {
		String strSql="select t1.owner_no value,t1.owner_name text," +
				"'['|| ltrim(t1.owner_no)||']'||t1.owner_name dropValue " +
				"from bdef_defowner t1 where t1.enterprise_no='"+enterpriseNo+"' " +
				"and t1.status='1' and t1.fixedcell_flag='1' ";
		
		if(strOwnerNo!=null && !strOwnerNo.equals(""))
		{
			strSql=strSql+" and t1.owner_no in("+strOwnerNo+") ";
		}else
		{
			strSql=strSql+" and 1=2";
		}
		List list = genDao.getListByNativeSql(strSql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}
	
	//获取货位列表，取作业区（包括异常区）并且不允许混货主的的货位
	@Override
	public ExtListDataBo<Cset_AreaBackupMModel> getCset_CellList(
			String enterpriseNo, String warehouseNo, String cellNo) {
		String sql = "select CDC.CELL_NO,cdc.ware_no,cdc.area_no,cdc.stock_no   "+
                 " FROM Cdef_DEFAREA CDA, Cdef_DEFCELL CDC   "+
                 " WHERE CDA.Enterprise_No = CDC.Enterprise_No   "+
                 "  AND CDA.Enterprise_No = '"+enterpriseNo+"'   "+
                 "  AND CDA.warehouse_no = CDC.warehouse_no   "+
                 "  AND CDA.Warehouse_No = '"+warehouseNo+"'   "+
                 "  AND CDA.WARE_NO = CDC.WARE_NO   "+
                 "  AND CDA.AREA_NO = CDC.AREA_NO   "+
                 "  AND CDA.AREA_ATTRIBUTE = '0'   "+
                 "  and CDA.AREA_USETYPE = '1'    "+
                 "  AND CDA.ATTRIBUTE_TYPE = '0'    "+
                 "  and cdc.cell_status = '0'   "+
                 "  and cdc.check_status = '0'   "+
                 "  and cdc.mix_owner='0'   "+
                 "  and cdc.cell_no not in" +
                 " (select l.cell_no from cset_owner_cell l " +
                 "    where l.enterprise_no=cdc.enterprise_no " +
                 "   and l.warehouse_no=cdc.warehouse_no " +
                 "   and l.cell_no=cdc.cell_no)";

		
		if(cellNo != null && !cellNo.equals("")){
			sql=sql+" AND CDC.CELL_NO like '"+cellNo+"%'";
		}
		sql=sql+" ORDER BY cdc.ware_no,cdc.area_no,cdc.stock_no,cdc.cell_no";
		List list = genDao.getListByNativeSql(sql,Cset_CellArticleModel.class );
		ExtListDataBo extListDateBo = new ExtListDataBo(list,0);
		return extListDateBo;
	}

	// 货主货位关系列表
	@Override
	public ExtListDataBo<Cset_AreaBackupMModel> getCset_Cell_OwnerList(
			String enterpriseNo, String warehouseNo, String ownerNo,String cellNo,
			PageBo pageBo) {
		String sql ="select l.owner_no,ow.owner_name,l.cell_no " +
				" from cset_owner_cell l,bdef_defowner ow " +
				" where l.enterprise_no=ow.enterprise_no " +
				"  and l.owner_no=ow.owner_no " +
				"  and l.owner_no='"+ownerNo+"' "+
				"  AND l.Enterprise_No = '"+enterpriseNo+"'   "+
	            "  AND l.Warehouse_No = '"+warehouseNo+"'   ";
		if(cellNo != null && !cellNo.equals("")){
			sql = sql+" AND l.CELL_NO like '"+cellNo+"%' ";
		}
		sql = sql+"  ORDER BY l.owner_no,l.cell_no ";
		String totsql = "select count(1) from ("+sql+")";
		
		List list = genDao.getListByNativeSql(sql,Cset_CellArticleModel.class,pageBo.getStart(), pageBo.getPagesize() );
		Integer count = genDao.getCountByNativeSql(totsql);

		ExtListDataBo extListDateBo = new ExtListDataBo(list,count);
		return extListDateBo;
	}
	//保存货主货位关系
	@Override
	public boolean saveCellOwner(String str) {
			Collection<Cset_OwnerCell> ownerCell=JSONArray.toCollection(JSONArray.fromObject(str),Cset_OwnerCell.class);
			List<Cset_OwnerCell> ownerCellList=(List)ownerCell;
			this.genDao.saveList(ownerCellList);
			return true;
	}

	//移除货主货位对应关系
	@Override
	public boolean deleteCellOwner(String enterpriseNo, String warehouseNo,
			String ownerNo, String cellNo) {
		String wheresql1[]=cellNo.split(",");
		for(int i=0;i<wheresql1.length;i++){
			String sql ="delete cset_owner_cell l " +
					"where l.enterprise_no='"+enterpriseNo+"' " +
					"and l.warehouse_no='"+warehouseNo+"' " +
					"and l.owner_no='"+ownerNo+"' " +
					"and l.cell_no ='"+wheresql1[i].trim()+"'";
			genDao.updateBySql(sql);
		}
		return true;
	}

	
}