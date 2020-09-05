package com.sealinkin.comm.service.impl;

import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefOwnerModel;
import com.sealinkin.cdef.model.Cdef_DefcellModel;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.ICheckCellService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class CheckCellImpl implements ICheckCellService{
	
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/*
	 * 储位校验(non-Javadoc)
	 * A、如果选中明细对应货主是绑定储位的（即fixedcell_flag=1）则该储位必须是该货主对应的储位（查CSET_OWNER_CELL表中该owner_no 对应的储位cell_no）;
		如果选中明细对应货主是非绑定储位的（即fixedcell_flag=0）则查询没有其他货主的商品的储位。
		B、是存储区储位：area_usetype=1,5,6(1表示普通存储区),area_attribute=0（0表示作业区，1表示暂存区）(cdef_defarea)
		C、是可用混载储位：cell_status=0,2(可用),mix_flag=2（混商品），check_status=0(非盘点)

	 */
	public MsgRes checkCell(String strOwnerNo, String strCellNo,String strWarehouseNo,String strEnterpriseNo) {
		String strSql = "select cdc.*,cda.area_usetype,cda.area_attribute " +
				"from cdef_defcell cdc,cdef_defarea cda " +
				"where cdc.warehouse_no=cda.warehouse_no " +
				"and cdc.ware_no=cda.ware_no " +
				"and cdc.area_no=cda.area_no " +
				"and cdc.warehouse_no='"+strWarehouseNo+"' " +
				"and cdc.enterprise_no='"+strEnterpriseNo+"' " +
				"and cdc.cell_no='"+strCellNo+"' ";
		List<Cdef_DefcellModel> listCell = genDao.getListByNativeSql(strSql,Cdef_DefcellModel.class);
		if(listCell.size()==0)
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21317"),null);//储位不存在
		}
		
		if(!"1".equals(listCell.get(0).getAreaUsetype()) &&
				!"5".equals(listCell.get(0).getAreaUsetype()) &&
						!"6".equals(listCell.get(0).getAreaUsetype()))
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21311"),null);//该储位不在普通、异常和贵重品区
		}
		
		if(!"0".equals(listCell.get(0).getAreaAttribute()))
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21312"),null);//该储位不在作业区         
		}
		
		if(!(listCell.get(0).getCellStatus().equals("0")) &&
				!listCell.get(0).getCellStatus().equals("2"))
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21313"),null);//该储位不是可用储区         
		}
		
		if(!(listCell.get(0).getMixFlag()==2))
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21314"),null);//该储位为不可 混商品储位
		}
		
		if(!listCell.get(0).getCheckStatus().equals("0"))
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21315"),null);//该储位为盘点状态
		}
		
		strSql = "select * " +
				"from " +
					"bdef_defowner a " +
				"where " +
					"a.owner_no='"+strOwnerNo+"' " +
					"and a.enterprise_no='"+strEnterpriseNo+"'";
		List<Bdef_DefOwnerModel> listOwner = genDao.getListByNativeSql(strSql, Bdef_DefOwnerModel.class);
		if(listOwner.size()==0)
		{
			return new MsgRes(false,TipUtil.getTipsByKey("E21307"),null);//对应货主不存在
		}
		if("1".equals(listOwner.get(0).getFixedcellFlag()))
		{
			strSql="select * " +
					"from " +
						"CSET_OWNER_CELL a " +
					"where " +
						"a.warehouse_no='"+strWarehouseNo+"' " +
						"and a.enterprise_no='"+strEnterpriseNo+"' "+
						"and a.owner_no='"+strOwnerNo+"' " +
						"and a.cell_no='"+strCellNo+"'";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()==0)
			{
				return new MsgRes(false,TipUtil.getTipsByKey("E21310"),null);//该储位不为货主绑定储位
			}
		}else
		{
			strSql="select * " +
					"from " +
						"CSET_OWNER_CELL a " +
					"where " +
						"a.warehouse_no='"+strWarehouseNo+"' " +
						"and a.enterprise_no='"+strEnterpriseNo+"' "+
						"and a.owner_no<>'"+strOwnerNo+"' " +
						"and a.cell_no='"+strCellNo+"'";
			List list = genDao.getListByNativeSql(strSql);
			if(list.size()>0)
			{
				return new MsgRes(false,TipUtil.getTipsByKey("E21318"),null);//该储位已绑定其它货主
			}
		}
		return new MsgRes(true,"",null);			
	}
}
