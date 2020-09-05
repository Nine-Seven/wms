package com.sealinkin.cset.action;

import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.cset.service.ICset_AreaBackupService;
import com.sealinkin.util.ExceptionUtil;
import com.sealinkin.comm.model.ComboxBo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"serial","rawtypes","unused"})
public class Cset_AreaBackupAction extends CommAction {
	private ICset_AreaBackupService cset_AreaBackupImpl;
	private MsgRes msgRes;
	private String str;
	private String enterpriseNo;
	private String warehouseNo;
	private String ownerNo;
	private String wheresql;
	private String strWheresql;
	private String strQuery;
	private String flag;
	private Integer requestFlag = Integer.valueOf(1);
	private String strLineId;
	private String strArticle;

	// 保存或更新保拣线
	public void saveOrUpdatecset_AreaBackupM() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl.saveOrUpdatecset_AreaBackupM(
					super.getMdBdef_DefWorker().getWorkerNo(), getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		}
	}

	// 保存或更新保级别
	public void saveOrUpdatecsetAreaBackupLevel() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl.saveOrUpdatecsetAreaBackupLevel(
							super.getMdBdef_DefWorker().getWorkerNo(), 
							getStr(), 
							this.getFlag());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		}
	}

	// 获取保拣线
	public void getCset_AreaBackupMList() {
		try {
			PageBo pageBo = new PageBo(getStart().intValue(), getLimit()
					.intValue());
			ExtListDataBo list = this.cset_AreaBackupImpl.getCset_AreaBackupMList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					super.getMdBdef_DefWorker().getWorkerOwner(), 
					getStrQuery(), pageBo);
			super.writeObj(covtObjectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取保拣级别

	public void getCset_AreaBackupDList() {
		try {
			ExtListDataBo list = this.cset_AreaBackupImpl.getCset_AreaBackupDList(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					getStrQuery(), getStart(),
					getLimit(), this.requestFlag);
			if (this.requestFlag.intValue() == 1) {
				super.writeStr(covtObjectToJson(list));
			} else if (this.requestFlag.intValue() == 2) {
				Map map = new HashMap();

				String title = "保拣关系表";
				String[] arrayOfString = {
						"sheet1",
						"保拣关系",
						"warehouseNo,wareNo,wareName,areaNo,areaName,aLevel,sWareNo,sAreaNo,keepCells,locateFlag,pickTopFlag,stockNo,stockX,sStockNo,sStockX,rgstName,rgstDate,updtName,updtDate",
						"仓别,顶级库区编码,顶级库区名称,顶级储区编码,顶级储区名称,级别,库区编码,储区编码,可用储位比,作业区域状态标志,顶级储格列,顶级通道编码,顶级储格列,通道编码,储格列,建立人员,建立日期,更新人员,更新日期" };
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除保拣线
	public void deleteLine() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl.deleteLine(
					getEnterpriseNo(),
					getWarehouseNo(),
					getStrLineId(), super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		} finally {
			writeObj(this.msgRes);
		}
	}

	// 删除保拣级别
	public void deleteGrade() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl.deleteGrade(getStrQuery(),super.getMdBdef_DefWorker().getWorkerNo());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
			super.writeObj(new MsgRes(Boolean.valueOf(false), ExceptionUtil
					.getCacseMessage(e), ""));
		} finally {
			writeObj(this.msgRes);
		}
	}

	// 校验保拣线是否重复
	public void existsAreaBackup() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl.existsAreaBackup(getStrQuery());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 校验保拣级别是否重复
	public void existsAreaBackupLevel() {
		try {
			MsgRes msg = this.cset_AreaBackupImpl
					.existsAreaBackupLevel(getStrQuery());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//库区下拉（M表）
	public void getCdef_DefWareComboByCsetM(){
		try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefWareComboByCsetM(getStr(), 0, 1000);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	// 库区下拉
	public void getCdef_DefWareCombo() {
		try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefWareCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), getStr(), 0, 100);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//储区下来（M表）
    public void getCdef_DefAreaComboByCsetM(){
    	try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefAreaComboByCsetM(getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	// 储区下拉
	public void getCdef_DefAreaCombo() {
		try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefAreaCombo(getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通道下拉（M表）
	public void getCdef_DefStockComboByCsetM(){
		try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefStockComboByCsetM(getStr());
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	// 通道下拉（包括拣货位的通道下拉1）
	public void getCdef_DefStockCombo() {
		try {
			List list = new ArrayList();
			list = this.cset_AreaBackupImpl.getCdef_DefStockCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					getStr(), flag,ownerNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //库区下拉（拣货位有用）
	public void getCdef_DefAreaCombo2() {
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = cset_AreaBackupImpl.getCdef_DefAreaCombo2(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), getStr(), flag,ownerNo);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 储位下拉
	 */
	public void getCdef_DefCellCombo() {
		try {
			List<ComboxBo> list = new ArrayList<ComboxBo>();
			list = cset_AreaBackupImpl.getCdef_DefCellCombo(
					super.getMdBdef_DefWorker().getCurrEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(), 
					str, strWheresql,ownerNo,strArticle);
			super.writeArray(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkAreaNo(){
		try {
			MsgRes msg = this.cset_AreaBackupImpl.checkAreaNo(
					super.getMdBdef_DefWorker().getEnterpriseNo(),
					super.getMdBdef_DefWorker().getWarehouseNo(),
					getStr());
			super.writeObj(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getRequestFlag() {
		return this.requestFlag;
	}

	public void setRequestFlag(Integer requestFlag) {
		this.requestFlag = requestFlag;
	}

	public String getStrQuery() {
		return this.strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getWheresql() {
		return this.wheresql;
	}

	public void setWheresql(String wheresql) {
		this.wheresql = wheresql;
	}

	public ICset_AreaBackupService getCset_AreaBackupImpl() {
		return this.cset_AreaBackupImpl;
	}

	public void setCset_AreaBackupImpl(
			ICset_AreaBackupService cset_AreaBackupImpl) {
		this.cset_AreaBackupImpl = cset_AreaBackupImpl;
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getOwnerNo() {
		return this.ownerNo;
	}

	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}

	public MsgRes getMsgRes() {
		return this.msgRes;
	}

	public void setMsgRes(MsgRes msgRes) {
		this.msgRes = msgRes;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getStrLineId() {
		return this.strLineId;
	}

	public void setStrLineId(String strLineId) {
		this.strLineId = strLineId;
	}

	public String getStrWheresql() {
		return strWheresql;
	}

	public void setStrWheresql(String strWheresql) {
		this.strWheresql = strWheresql;
	}

	public String getEnterpriseNo() {
		return enterpriseNo;
	}

	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}

	public String getStrArticle() {
		return strArticle;
	}

	public void setStrArticle(String strArticle) {
		this.strArticle = strArticle;
	}
	
	
}