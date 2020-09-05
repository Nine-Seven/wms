package com.sealinkin.cset.service;

import com.sealinkin.comm.model.ComboxBo;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.model.Cset_AreaBackupDModel;
import com.sealinkin.cset.model.Cset_AreaBackupMModel;
import java.util.List;

public abstract interface ICset_AreaBackupService {
	// 获取保拣线
	public abstract ExtListDataBo<Cset_AreaBackupMModel> getCset_AreaBackupMList(
			String enterpriseNo,
			String paramString1, String paramString2, String paramString3,
			PageBo paramPageBo);

	// 获取保拣级别
	public abstract ExtListDataBo<Cset_AreaBackupDModel> getCset_AreaBackupDList(
			String enterpriseNo,
			String paramString1, String paramString2, Integer paramInteger1,
			Integer paramInteger2, Integer paramInteger3);

	// 校验保拣线是否重复
	public abstract MsgRes existsAreaBackup(String paramString);

	// 保存或更新保拣线
	public abstract MsgRes saveOrUpdatecset_AreaBackupM(String paramString1,
			String paramString2);

	// 库区下拉
	public abstract List<ComboxBo> getCdef_DefWareCombo(String enterpriseNo,String paramString1,
			String paramString2, int paramInt1, int paramInt2);

	// 储区下拉
	public abstract List<ComboxBo> getCdef_DefAreaCombo(String paramString1);

	// 通道下拉
	public abstract List<ComboxBo> getCdef_DefStockCombo(String enterpriseNo,String paramString1,
			String paramString2, String flag,String ownerNo);

	// 保存或更新保级别
	public abstract MsgRes saveOrUpdatecsetAreaBackupLevel(String paramString1,
			String paramString2, String flag);

	// 校验保拣级别是否重复
	public abstract MsgRes existsAreaBackupLevel(String paramString);

	// 删除保拣线
	public abstract MsgRes deleteLine(String enterpriseNo,String paramString1, String paramString2,
			String workerNo);

	// 删除保拣级别
	public abstract MsgRes deleteGrade(String paramString, String strWorkerNo);

	public List<ComboxBo> getCdef_DefAreaCombo2(String enterpriseNo,String warehouseNo, String str,
			String flag, String ownerNo);

	// 储位下拉	
	public abstract List<ComboxBo> getCdef_DefCellCombo(String enterpriseNo,String warehouseNo,
			String str, String strWheresql, String ownerNo,String strArticle);

	//库区下拉（M表）
	public abstract List<ComboxBo> getCdef_DefWareComboByCsetM(String str, int i, int j);

	//储区下拉（M表）
	public abstract List<ComboxBo> getCdef_DefAreaComboByCsetM(String str);
	//通道下拉（M表）
	public abstract List<ComboxBo> getCdef_DefStockComboByCsetM(String str);

	//判断拣货区是否配置了保拣线
	public abstract MsgRes checkAreaNo(String enterpriseNo, String warehouseNo,String str);

}