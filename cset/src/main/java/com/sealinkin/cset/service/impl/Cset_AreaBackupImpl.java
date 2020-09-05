package com.sealinkin.cset.service.impl;

import com.alibaba.fastjson.JSON;
import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_AreaBackupDModel;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;
import com.sealinkin.cset.po.Cset_AreaBackupD;
import com.sealinkin.cset.po.Cset_AreaBackupM;
import com.sealinkin.cset.service.ICset_AreaBackupService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.TipUtil;
import java.util.List;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Cset_AreaBackupImpl implements ICset_AreaBackupService {
	private IGenericManager genDao;

	public IGenericManager getGenDao() {
		return this.genDao;
	}

	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}

	// 保存或更新保级别
	public MsgRes saveOrUpdatecsetAreaBackupLevel(String workerNo, String str,String flag) {
		System.out.println(str);
		Cset_AreaBackupD cad = (Cset_AreaBackupD) JSON.parseObject(str,
				Cset_AreaBackupD.class);
		
		String sql ="select * from Cset_Area_Backup_D a " +
				   "  where a.warehouse_no='"+ cad.getId().getWarehouseNo()+
				   "' and a.enterprise_no='"+cad.getId().getEnterpriseNo()+
				   "' and a.line_id='"+ cad.getId().getLineId()+
				   "' and a.ware_no='"+ cad.getId().getWareNo()+
				   "' and a.area_no='"+ cad.getId().getAreaNo()+
				   "' and a.stock_no='"+ cad.getId().getStockNo() + "'";
		
		List list = this.genDao.getListByNativeSql(sql,
				Cset_AreaBackupDModel.class, 0, 2000);
		if (list.size() == 0 || flag.equals("edit")) {
			sql = sql + " and a.a_level='"+ cad.getId().getALevel()+ "'";				
			list = this.genDao.getListByNativeSql(sql,
				Cset_AreaBackupDModel.class, 0, 2000);
			if (list.size() == 0) {
				cad.setUpdtDate(null);
				cad.setUpdtName(null);
			} else {
				cad.setRgstDate(((Cset_AreaBackupDModel) list.get(0)).getRgstDate());
				cad.setRgstName(((Cset_AreaBackupDModel) list.get(0)).getRgstName());
			}
			this.genDao.saveOrUpdateObj(cad);
			String logSql = "insert into cset_area_backup_d_log(enterprise_no,warehouse_no, line_id, ware_no, area_no, stock_no, a_level, keep_cells, merger_flag, stock_flag, floor_flag, bay_flag, sort_flag, stockx_flag, rgst_name, rgst_date, updt_name, updt_date, serialid, modiattr, moditime, modiopid)"
					+ "values('"
					+ cad.getId().getEnterpriseNo()
					+ "','"
					+ cad.getId().getWarehouseNo()
					+ "','"
					+ cad.getId().getLineId()
					+ "','"
					+ cad.getId().getWareNo()
					+ "','"
					+ cad.getId().getAreaNo()
					+ "',"
					+ "'"
					+ cad.getId().getStockNo()
					+ "','"
					+ cad.getId().getALevel()
					+ "','"
					+ cad.getKeepCells()
					+ "','"
					+ cad.getMergerFlag()
					+ "',"
					+ "'"
					+ cad.getSortFlag()
					+ "','"
					+ cad.getFloorFlag()
					+ "','"
					+ cad.getBayFlag()
					+ "','"
					+ cad.getSortFlag()
					+ "',"
					+ "'"
					+ cad.getStockxFlag()
					+ "','"
					+ cad.getRgstName()
					+ "',sysdate,'"
					+ cad.getRgstName()
					+ "',"
					+ "sysdate,SEQ_Cset_Area_Backup_D_LOG.nextval, 'A',sysdate,'"
					+ workerNo + "')";
			
			this.genDao.updateBySql(logSql);
			return new MsgRes(Boolean.valueOf(true),
					TipUtil.getTipsByKey("E40202"), null);
		}
		return new MsgRes(Boolean.valueOf(false),
				"保拣线级别已经存在", null);
	}

	// 获取保拣线
	public ExtListDataBo<Cset_AreaBackupMModel> getCset_AreaBackupMList(
			String enterpriseNo,String strWarehouseNo, String strOwnerNo, String strQuery,
			PageBo pageBo) {
		String sql = "select cabm.*,  "
				+ "f_get_fieldtext('N','DEFAULT_FLAG',cabm.default_flag) as defaultFlagText "
				+ "from Cset_Area_Backup_M cabm " 
				+" where warehouse_no='"+ strWarehouseNo + "' " 
				+" and enterprise_No='"+enterpriseNo+"' ";
		String totallSql = "select count(1) from Cset_Area_Backup_M cabm " +
				         "where warehouse_no='"+ strWarehouseNo + "'"
				         +" and enterprise_No='"+enterpriseNo+"' ";;
		if ((strQuery != null) && (!strQuery.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
			totallSql = totallSql + ws;
		}
		sql += " order by cabm.line_id";
		List list = this.genDao.getListByNativeSql(sql,
				Cset_AreaBackupMModel.class, pageBo.getStart(),
				pageBo.getPagesize());
		Integer count = this.genDao.getCountByNativeSql(totallSql);

		ExtListDataBo extListBo = new ExtListDataBo(list, count);

		return extListBo;
	}

	// 获取保拣级别
	public ExtListDataBo<Cset_AreaBackupDModel> getCset_AreaBackupDList(
			String enterpriseNo,String strWarehouseNo, String strQuery, Integer intStart,
			Integer intPagesize, Integer intRequestFlag) {
		String sql = " select  cabd.*,b.ware_name,c.area_name, "
				+ "f_get_fieldtext('Cset_Area_Backup','MERGER_FLAG',cabd.MERGER_FLAG) as mergerFlagText, "
				+ "f_get_fieldtext('Cset_Area_Backup','STOCK_FLAG',cabd.STOCK_FLAG) as stockFlagText,"
				+ "f_get_fieldtext('Cset_Area_Backup','FLOOR_FLAG',cabd.FLOOR_FLAG) as floorFlagText, "
				+ "f_get_fieldtext('Cset_Area_Backup','BAY_FLAG',cabd.BAY_FLAG) as bayFlagText, "
				+ "f_get_fieldtext('Cset_Area_Backup','SORT_FLAG',cabd.SORT_FLAG) as sortFlagText, "
				+ "f_get_fieldtext('Cset_Area_Backup','STOCKX_FLAG',cabd.STOCKX_FLAG) as stockxFlagText "
				+ "from Cset_Area_Backup_D cabd,  cdef_defware b,cdef_defarea c " 
				+ " where cabd.warehouse_no=b.warehouse_no  " 
				+ " and cabd.enterprise_no=b.enterprise_no "
				+ " and cabd.ware_no=b.ware_no  " 
				+ " and cabd.warehouse_no=c.warehouse_no "
				+ " and cabd.enterprise_no=c.enterprise_no " 
				+ " and cabd.ware_no=c.ware_no "
				+ " and cabd.area_no=c.area_no " 
				+ " and cabd.warehouse_no = '"+ strWarehouseNo + "' " 
				+ " and cabd.enterprise_no= '"+enterpriseNo+"' ";

		String totallSql = "select count(1) from Cset_Area_Backup_D cabd" +
				" where warehouse_no='"+ strWarehouseNo + "'"
				+" and cabd.enterprise_no= '"+enterpriseNo+"' ";
		if ((strQuery != null) && (!strQuery.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
			totallSql = totallSql + ws;
		}
		sql = sql
				+ " order by cabd.a_level, cabd.ware_no, cabd.area_no,  cabd.stock_no";
		List list = null;
		Integer count = Integer.valueOf(0);
		ExtListDataBo extListBo = null;
		new ExtListDataBo(list, count);
		if (intRequestFlag.intValue() == 1) {
			list = this.genDao.getListByNativeSql(sql,
					Cset_AreaBackupDModel.class, intStart.intValue(),
					intPagesize.intValue());
			count = this.genDao.getCountByNativeSql(totallSql);
		} else if (intRequestFlag.intValue() == 2) {
			list = this.genDao.getListByNativeSql(sql,
					Cset_AreaBackupDModel.class);
			count = Integer.valueOf(list.size());
		}
		extListBo = new ExtListDataBo(list, count);
		return extListBo;
	}

	// 删除保拣级别
	public MsgRes deleteGrade(String strQuery,String strWorkerNo) {
		String strlog = "insert into cset_area_backup_d_log(enterprise_no,warehouse_no, line_id, ware_no, area_no, stock_no, a_level, keep_cells, merger_flag, stock_flag, floor_flag, bay_flag, sort_flag, stockx_flag, rgst_name, rgst_date, updt_name, updt_date, serialid, modiattr, moditime, modiopid) " +
				"select cabd.enterprise_no,cabd.warehouse_no,cabd.line_id,cabd.ware_no,cabd.area_no,cabd.stock_no,cabd.a_level,cabd.keep_cells,cabd.merger_flag,cabd.stock_flag,cabd.floor_flag,cabd.bay_flag,cabd.sort_flag,stockx_flag,cabd.rgst_name,cabd.rgst_date,cabd.updt_name,cabd.updt_date," +
				"SEQ_Cset_Area_Backup_D_LOG.nextval, 'D',sysdate,'"+strWorkerNo+"' " +
				"from Cset_Area_Backup_D cabd where 1=1 ";
				
		
		String sql = "delete from Cset_Area_Backup_D cabd where 1=1";
		
		if ((strQuery != null) && (!strQuery.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
			strlog+=ws;
		}
		this.genDao.updateBySql(strlog);
		this.genDao.updateBySql(sql);
		return new MsgRes(Boolean.valueOf(true),
				TipUtil.getTipsByKey("E40205"), null);
	}

	// 删除保拣线
	public MsgRes deleteLine(String enterpriseNo,String warehouseNo, String strLineId,
			String strWorkerNo) {
		String sql=" select a.line_id from cset_cell_article a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' " +
				   "    and a.line_id='"+strLineId+"' ";
		List<String> list =genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			return new MsgRes(false,"该保拣线已经设置的商品拣货位", null);
		}
		
		String strLog = "  insert into cset_area_backup_m_log(  "
				+ "enterprise_no, warehouse_no, line_id, line_name, default_flag, "
				+ "rgst_name, rgst_date, serialid, modiattr, moditime, "
				+ "modiopid,s_ware_no,s_area_no,s_stock_no ) " 
				+ "select enterprise_no, warehouse_no, line_id, line_name, "
				+ "default_flag, rgst_name, rgst_date,  "
				+ "SEQ_Cset_Area_Backup_M_LOG.nextval, 'D',sysdate,'"
				+ strWorkerNo + "',s_ware_no,s_area_no,s_stock_no "
				+ " from cset_area_backup_m cabm where cabm.warehouse_no='"+ warehouseNo + "' " 
				+ " and cabm.enterprise_no='" + enterpriseNo + "'"
				+ " and cabm.line_id='" + strLineId + "'";
		this.genDao.updateBySql(strLog);

		String logSql2 = " insert into Cset_Area_Backup_D_log "
				+ "(enterprise_no, warehouse_no, line_id, ware_no, area_no, "
				+ "stock_no, a_level, keep_cells, merger_flag, "
				+ "stock_flag, floor_flag, bay_flag, sort_flag, "
				+ "stockx_flag, rgst_name, rgst_date, updt_name, "
				+ "updt_date, serialid, modiattr, moditime, modiopid) "
				+ "select enterprise_no,warehouse_no, line_id, ware_no, area_no, "
				+ "stock_no, a_level, keep_cells, merger_flag, "
				+ "stock_flag, floor_flag, bay_flag, sort_flag, "
				+ "stockx_flag, rgst_name, rgst_date, updt_name, "
				+ "updt_date, SEQ_Cset_Area_Backup_D_LOG.nextval, 'D',sysdate,'"
				+ strWorkerNo + "' " + " from Cset_Area_Backup_D "
				+ " where warehouse_no='" + warehouseNo + "' "
				+ " and enterprise_no='" + enterpriseNo + "'"
				+ " and line_id='" + strLineId + "'";
		this.genDao.updateBySql(logSql2);

		String strSql = "delete from Cset_Area_Backup_M where warehouse_no='"
				+ warehouseNo + "'" + " and line_id='" + strLineId + "' " +
				  "and enterprise_no='" + enterpriseNo + "' ";
		this.genDao.updateBySql(strSql);
		String strSqlDeleteGrade = "delete from cset_area_backup_d " +
				"                   where line_id = '"+ strLineId + "' " +
						             "and warehouse_no='"+ warehouseNo + "'" +
				                     "and enterprise_no='" + enterpriseNo + "'";
		this.genDao.updateBySql(strSqlDeleteGrade);

		return new MsgRes(Boolean.valueOf(true),
				TipUtil.getTipsByKey("E40204"), null);
	}

	// 校验保拣线是否重复
	public MsgRes existsAreaBackup(String queryStr) {
		String sql = "select cabm.* from Cset_Area_Backup_M cabm where 1=1 ";
		String totsql = "select count(*) from Cset_Area_Backup_M cabm where 1=1  ";
		if ((queryStr != null) && (!queryStr.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(queryStr);
			sql = sql + ws;
			totsql = totsql + ws;
		}
		List list = this.genDao.getListByNativeSql(sql,
				Cset_AreaBackupMModel.class, 0, 10000);
		Integer count = this.genDao.getCountByNativeSql(totsql);
		ExtListDataBo extListBo = new ExtListDataBo(list, count);
		if (extListBo.getRootList().size() > 0) {
			return new MsgRes(Boolean.valueOf(false),
					TipUtil.getTipsByKey("E40201"), null);
		}
		return new MsgRes(Boolean.valueOf(true), TipUtil.getTipsByKey(""), null);
	}

	// 保存或更新保拣线
	public MsgRes saveOrUpdatecset_AreaBackupM(String workerNo, String str) {
		Cset_AreaBackupM cam = (Cset_AreaBackupM) JSON.parseObject(str,Cset_AreaBackupM.class);
		this.genDao.saveOrUpdateObj(cam);
		String strLog = "insert into cset_area_backup_m_log"
				+ "(enterprise_no,warehouse_no, line_id, line_name, default_flag, rgst_name, "
				+ "rgst_date, serialid, modiattr, moditime, modiopid,s_ware_no,s_area_no,s_stock_no) "
				+ "values('"+cam.getId().getEnterpriseNo()+"','" 
				+ cam.getId().getWarehouseNo() + "','"
				+ cam.getId().getLineId() + "','" + cam.getLineName() + "',"
				+ "'" + cam.getDefaultFlag() + "','" + cam.getRgstName()
				+ "',sysdate,SEQ_Cset_Area_Backup_M_LOG.nextval,'A',sysdate,'"
				+ workerNo + "','"+cam.getSWareNo()+"','"+ cam.getSAreaNo()+"','" 
				+cam.getSStockNo()+ "') ";
		this.genDao.updateBySql(strLog);
		return new MsgRes(Boolean.valueOf(true),
				TipUtil.getTipsByKey("E40202"), null);
	}

	// 储区下拉
	public List<ComboxBo> getCdef_DefAreaCombo(String str) {
		String sql = "select distinct a.area_no value,a.area_Name text,"
				+ "'['|| ltrim(a.area_no)||']'||a.area_Name dropValue "
				+ " from cdef_defarea a where 1=1 and a.area_attribute='0' "
				+ "and a.Attribute_Type='0' and a.area_usetype in('1','5','6') ";

		if ((str != null) && (!str.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		List list = this.genDao
				.getListByNativeSql(sql, ComboxBo.class, 0, 1000);
		return list;
	}

	// 通道下拉（包括拣货位的通道下拉1）
	public List<ComboxBo> getCdef_DefStockCombo(String enterpriseNo,String strWarehouseNo,
			String str, String strFlag,String ownerNo) {
		String sql = "";
		if (strFlag != null && strFlag.equals("1")) {
			sql = "select distinct a.stock_no value, a.stock_no text,"
					+ "'['|| ltrim(a.stock_no)||']'||a.stock_no  dropValue  "
					+ "from cdef_defcell  a ,cdef_defarea b where  a.ware_no=b.ware_no "
					+ "and a.warehouse_no=b.warehouse_no and a.enterprise_no=b.enterprise_no " 
					+ "and a.area_no=b.area_no  "
					+ "and a.warehouse_No='" + strWarehouseNo + "'" 
					+ "and a.enterprise_no='"+enterpriseNo+"' ";
		} else {
			sql = "select distinct a.stock_no value, a.stock_no text,"
					+ "'['|| ltrim(a.stock_no)||']'||a.stock_no  dropValue  "
					+ "from cdef_defcell a ,cdef_defarea b where  a.ware_no=b.ware_no "
					+ "and a.warehouse_no=b.warehouse_no  and a.area_no=b.area_no  "
					+ "and b.area_attribute='0' and b.Attribute_Type='0' "
					+ "and b.area_usetype in('1','5','6') and a.warehouse_No='"+ strWarehouseNo + "' "
					+ "and a.enterprise_no='"+enterpriseNo+"' ";;
		}

		if ((str != null) && (!str.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		//如果货主有绑定货位，则只能下拉绑定的货位的所属通道
		if(ownerNo!=null && !ownerNo.equals("")){
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+ownerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List flag = genDao.getListByNativeSql(sql1);
			if(flag.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.stock_no in (select distinct cl.stock_no " +
						"from cset_owner_cell l,cdef_defcell cl " +
						"where l.enterprise_no=cl.enterprise_no and l.warehouse_no=cl.warehouse_no "+
						"and l.cell_no=cl.cell_no " +
						" and l.owner_no='"+ownerNo+"' "+
						" and l.warehouse_No='"+strWarehouseNo+"' "+
						" and l.enterprise_no='"+enterpriseNo+"' and cl.area_no=a.area_no)  ";
		   }
		}
		List list = this.genDao.getListByNativeSql(sql, ComboxBo.class);
		return list;
	}

	// 库区下拉
	public List<ComboxBo> getCdef_DefWareCombo(String enterpriseNo,String strWarehouseNo,
			String str, int i, int j) {
		String sql = "select distinct a.ware_no value,a.ware_name text,"
				+ "'['|| ltrim(a.ware_no)||']'||a.ware_name dropValue  "
				+ "from cdef_defware a ,cdef_defarea b where  "
				+ "a.ware_no=b.ware_no and a.warehouse_no=b.warehouse_no  "
				+ "and b.area_attribute='0' and b.Attribute_Type='0' "
				+ "and b.area_usetype in('1','5','6')" 
				+" and a.warehouse_No='"+ strWarehouseNo + "' "
				+" and a.enterprise_no='"+ enterpriseNo + "' ";
		List list = this.genDao
				.getListByNativeSql(sql, ComboxBo.class, 0, 1000);
		return list;
	}

	// 校验保拣级别是否重复
	public MsgRes existsAreaBackupLevel(String strQuery) {
		String sql = "select cabd.* from Cset_Area_Backup_D cabd where 1=1 ";
		String totsql = "select count(*) from Cset_Area_Backup_D cabd where 1=1  ";
		if ((strQuery != null) && (!strQuery.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(strQuery);
			sql = sql + ws;
			totsql = totsql + ws;
		}
		List list = this.genDao.getListByNativeSql(sql,
				Cset_AreaBackupDModel.class, 0, 10000);
		Integer count = this.genDao.getCountByNativeSql(totsql);
		ExtListDataBo extListBo = new ExtListDataBo(list, count);
		if (extListBo.getRootList().size() > 0) {
			return new MsgRes(Boolean.valueOf(false),
					TipUtil.getTipsByKey("E40203"), null);
		}
		return new MsgRes(Boolean.valueOf(true), TipUtil.getTipsByKey(""), null);
	}
	
    //库区储区下拉（拣货位模块有使用2：储区；3：库存；）；
	@Override
	public List<ComboxBo> getCdef_DefAreaCombo2(String enterpriseNo,String warehouseNo, String str,
			String flag,String ownerNo) {
		String sql = "";
		if (flag.equals("1")) {
			sql = "select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue  "
					+ "from cdef_defarea a where " 
					+" a.warehouse_No='"+ warehouseNo+"' "
					+" and a.AREA_USETYPE=1 and a.AREA_ATTRIBUTE=0 ";
		} else if (flag.equals("2")) {
			sql = "select a.area_no value,a.area_Name text,'['|| ltrim(a.area_no)||']'||a.area_Name dropValue  "
					+ "from cdef_defarea a where a.warehouse_No='"+ warehouseNo
					+ "' and a.AREA_USETYPE=1 and a.AREA_ATTRIBUTE=0 and a.AREA_PICK=1 ";
		} else if (flag.equals("3")) {
			sql = "select distinct a.ware_no value,b.ware_Name text,'['|| ltrim(a.ware_no)||']'||b.ware_Name dropValue  "
					+ "from cdef_defarea a inner join cdef_defware b " 
					+ " on a.warehouse_no=b.warehouse_no and a.enterprise_no=b.enterprise_no "
					+ " and a.ware_no=b.ware_no and a.warehouse_No='"
					+ warehouseNo
					+ "' and a.AREA_USETYPE=1 and a.AREA_ATTRIBUTE=0 ";//2016 07 07 update by czh 删除and a.AREA_PICK=1 
		}
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		
		if(enterpriseNo!=null && !enterpriseNo.equals(""))
		{
			sql = sql+" and a.enterprise_no = '"+enterpriseNo+"'";
		}
		
		if(ownerNo!=null && !ownerNo.equals(""))
		{
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+ownerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List fixedcell = genDao.getListByNativeSql(sql1);
			if(fixedcell.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.area_no in (select distinct cl.area_no " +
						"from cset_owner_cell l,cdef_defcell cl " +
						"where l.enterprise_no=cl.enterprise_no "+
						"and l.cell_no=cl.cell_no " +
						" and l.owner_no='"+ownerNo+"' "+
						" and l.warehouse_No='"+warehouseNo+"' "+
						" and l.enterprise_no='"+enterpriseNo+"' " +
						"and cl.ware_no=a.ware_no) ";
				}
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	/**
	 * 储位下拉(商品储位对应关系、商品类别储位对应关系)
	 */
	public List<ComboxBo> getCdef_DefCellCombo(
			String enterpriseNo,String strWarehouseNo, String strJson, 
			String str, String ownerNo,String strArticle) {
		String sql = "select a.cell_no value,a.cell_no text,a.cell_no dropValue  "
				+ "from Cdef_Defcell a "
				+ "where a.cell_status='0' "
				+ "and a.warehouse_No='" + strWarehouseNo + "' "
				+" and a.enterprise_no = '"+enterpriseNo+"' " +
				"  and a.cell_no not in(select t.cell_no from cset_cell_article t " +
				" where t.enterprise_no=a.enterprise_no " +
				"  and t.warehouse_no=a.warehouse_no) ";
				
		if(strArticle != null && !strArticle.equals("")){
			sql = sql + "  and a.cell_no not in(select te.cell_no from stock_content te " +
					" where te.enterprise_no=a.enterprise_no " +
					"  and te.warehouse_no=a.warehouse_no and te.article_no<>'"+strArticle+"')  ";
		}
		if (strJson != null && !strJson.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(strJson);
			sql = sql + ws;
		}
		if (str != null && !str.equals("")) {
			sql = sql + " and a.cell_no like '%" + str + "%' ";
		}
		
		if(ownerNo!=null && !ownerNo.equals(""))
		{
			String sql1="select a.fixedcell_flag from " +
					"bdef_defowner a where a.owner_no='"+ownerNo+"' " +
					" and a.enterprise_no='"+enterpriseNo+"' " ;
			List fixedcell = genDao.getListByNativeSql(sql1);
			if(fixedcell.get(0).equals("1")){//0：不绑定固定储位；1：绑定固定储位；
				sql=sql+" and a.cell_no in (select ca.cell_no from cset_owner_cell  ca " +
						"where ca.owner_no='"+ownerNo+"' "+
						" and ca.warehouse_No='"+strWarehouseNo+"' "+
						" and ca.enterprise_no='"+enterpriseNo+"')  ";
		     }
		}
		List list = genDao.getListByNativeSql(sql, ComboxBo.class);
		return (List<ComboxBo>) list;
	}

	//库区下拉（M表）
	@Override
	public List<ComboxBo> getCdef_DefWareComboByCsetM(String str, int i, int j) {
		String sql = " select distinct c.ware_no value,c.ware_name text," +
				     "        '['|| ltrim(a.ware_no)||']'||c.ware_name dropValue " +
				     "   from cdef_defcell a ,cdef_defarea b,cdef_defware c " +
				     "  where a.enterprise_no=c.enterprise_no " +
				     "    and a.warehouse_no=c.warehouse_no " +
				     "    and a.ware_no=c.ware_no " +
				     "    and a.enterprise_no=b.enterprise_no " +
				     "    and a.warehouse_no=b.warehouse_no " +
				     "    and a.area_no=b.area_no " +
				     "    and b.area_attribute='0' " +
				     "    and b.attribute_type='0' " +
				     "    and b.area_usetype in('1','5','6') " +
				     "    and b.area_pick='1' ";

				
		List list = this.genDao
				.getListByNativeSql(sql, ComboxBo.class, 0, 1000);
		return list;
	}

	//储区下拉（M表）
	@Override
	public List<ComboxBo> getCdef_DefAreaComboByCsetM(String str) {
		String sql = " select distinct a.area_no value, b.area_name text," +
			     "        '['|| ltrim(b.area_no)||']'||b.area_name dropValue  " +
			     "   from cdef_defcell a ,cdef_defarea b " +
			     "   where a.enterprise_no=b.enterprise_no " +
			     "     and a.warehouse_no=b.warehouse_no " +
			     "     and a.area_no=b.area_no " +
			     "     and b.area_attribute='0' " +
			     "     and b.attribute_type='0' " +
			     "     and b.area_usetype in('1','5','6') " +
			     "     and b.area_pick='1'";
		if (str != null && !str.equals("")) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}	
		sql=sql+" order by a.area_no";
		List list = this.genDao.getListByNativeSql(sql, ComboxBo.class, 0, 1000);
		return list;
	}

	//通道下拉
	@Override
	public List<ComboxBo> getCdef_DefStockComboByCsetM(String str) {
		String sql=" select distinct a.stock_no value, a.stock_no text, " +
				   "        '['|| ltrim(a.stock_no)||']'||a.stock_no dropValue  " +
				   "  from cdef_defcell a ,cdef_defarea b " +
				   " where a.enterprise_no=b.enterprise_no " +
				   "   and a.warehouse_no=b.warehouse_no " +
				   "   and a.area_no=b.area_no " +
				   "   and b.area_attribute='0' " +
				   "   and b.attribute_type='0' " +
				   "   and b.area_usetype in('1','5','6') " +
				   "   and b.area_pick='1' ";
		
		if ((str != null) && (!str.equals(""))) {
			String ws = CommUtil.covtCollectionToWhereSql(str);
			sql = sql + ws;
		}
		sql=sql+" order by a.stock_no";
		
		List list = this.genDao.getListByNativeSql(sql, ComboxBo.class, 0, 1000);
		return list;
	}

	@Override
	public MsgRes checkAreaNo(String enterpriseNo, String warehouseNo,String str) {
		String sql=" select a.s_area_no from cset_area_backup_m a " +
				   "  where a.enterprise_no='"+enterpriseNo+"' " +
				   "    and a.warehouse_no='"+warehouseNo+"' " +
				   "    and a.s_area_no='"+str+"' " +
				   "     and a.default_flag='1' ";
		List<String> list=genDao.getListByNativeSql(sql);
		
		if(list.size()>=1){
			return new MsgRes(true,"该拣货区已经是默认保拣线","");
		}else{
			return new MsgRes(false,"","");
		}
	}
}