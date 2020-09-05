package com.sealinkin.comm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.service.ICheckDivideLabelNoService;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_DivideDModel;
import com.sealinkin.stock.model.Stock_LabelMModel;
import com.sealinkin.util.TipUtil;

@SuppressWarnings({"rawtypes","unchecked"})
public class CheckDivideLabelNoImpl implements ICheckDivideLabelNoService {

	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	
	/**
	 * 分播回单容器号校验
	 * （hkl:log将java代码屏蔽，调用的底层pkcheck_odata.P_DivideCheckDLabelNo来进行校验）
	 */
	@Override
	public MsgRes checkDivideLabelNo(String strEnterpriseNo,String strWarehouseNo, String ownerNo,
			String strSContainerNo,String strLabelNo,
			String strCustNo, String strArticleNo,String deliverObj) throws Exception{
	
		List inList=new ArrayList();
		List outList=new ArrayList();
		List resultList=new ArrayList();
		
		outList.add("S");
		inList.add(strEnterpriseNo);
		inList.add(strWarehouseNo);
		inList.add(ownerNo);
		inList.add(strSContainerNo);
		inList.add(strLabelNo);
		inList.add(deliverObj);
		inList.add(strCustNo);
		inList.add(strArticleNo);
		
		resultList = genDao.execProc(inList, outList, "pkcheck_odata.P_DivideCheckDLabelNo");
		if(resultList.get(0).toString().indexOf("N|") != -1){
			throw new Exception(resultList.get(0).toString());
		}
		
		/*String g_strDivideFixPal = "";
	    String g_strDivideCheckWave = "";
	  //获取系统参数
	    List inList1  =  new ArrayList();
		List outList1  =  new ArrayList();
		List resultList1  =  new ArrayList();
		
		outList1.add("S");
		outList1.add("S");
		outList1.add("S");
		inList1.add(strEnterpriseNo);
		inList1.add(strWarehouseNo);
		inList1.add(oddList.get(0).getOwnerNo());
		inList1.add("Divide_FixPal");
		inList1.add("O");
		inList1.add("O_DIVIDE");
		resultList1  =  genDao.execProc(inList1, outList1, "PKLG_WMS_BASE.p_GetBasePara");
		if(resultList1.get(2).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList1.get(2).toString());
		}
		g_strDivideFixPal = resultList1.get(0).toString();
		//wd.setNdefine(Double.parseDouble(resultList.get(1)==null ? "0":resultList.get(1).toString()));
		List inList2  =  new ArrayList();
		List outList2  =  new ArrayList();
		List resultList2  =  new ArrayList();
		
		//获取系统参数
		outList2.add("S");
		outList2.add("S");
		outList2.add("S");
		inList2.add(strEnterpriseNo);
		inList2.add(strWarehouseNo);
		inList2.add(oddList.get(0).getOwnerNo());
		inList2.add("Divide_CheckWave");
		inList2.add("O");
		inList2.add("O_DIVIDE");
		resultList2  =  genDao.execProc(inList2, outList2, "PKLG_WMS_BASE.p_GetBasePara");
		if(resultList2.get(2).toString().indexOf("N|") != -1)
		{
			throw new Exception(resultList2.get(2).toString());
		}
		 g_strDivideCheckWave = resultList2.get(0).toString();
				
		String strSql5="select slm.*,sld.wave_no " +
				"from stock_label_m slm, stock_label_d sld " +
				"where slm.warehouse_no=sld.warehouse_no " +
				"and slm.enterprise_no=sld.enterprise_no " +
				"and slm.container_no=sld.container_no " +
				"AND slm.container_type=sld.container_type " +
				"and slm.owner_container_no in(select container_no " +
				"from stock_label_m a where a.warehouse_no='"+strWarehouseNo+"' " +
						"and a.label_no='"+strLabelNo+"' and a.use_type='1' " +
						"and a.enterprise_no='"+strEnterpriseNo+"')";
		
		//String strSql5 = ""
		//读取标签信息表校验此标签是否在标签表存在
		if(list.size() != 0)
		{
			for(int i=0; i<list.size(); i++)
			{
				//若存在，必须是当前待分播的客户标签；
				if(list.get(i).getUseType().equals("1"))
				{
					String status = "";
					//判断目的标签的客户与当前标签是否是同一客户：如果不是—》开解
					if(!list.get(i).getCustNo().equals(strCustNo))
					{
						return new MsgRes(false,TipUtil.getTipsByKey("E22502"),null);//数据错误
					}
					String strSql2 = "select distinct cld.exp_type, wds.flow_value " +
							" from (select owner_lm.warehouse_no,owner_lm.enterprise_no, owner_lm.container_no, lm.status" +
							" from stock_label_m owner_lm, stock_label_m lm" +
							" where owner_lm.warehouse_no = lm.warehouse_no " +
							" and owner_lm.enterprise_no=lm.enterprise_no" +
							" and owner_lm.owner_container_no = lm.container_no" +
							" and lm.container_no = '"+strSContainerNo+"') clm" +
							" inner join stock_label_d cld" +
							" on clm.container_no = cld.container_no " +
							" and clm.enterprise_no = cld.enterprise_no" +
							" and clm.warehouse_no = cld.warehouse_no" +
							" inner join wms_deflabel_status wds" +
							" on wds.status_type = clm.status" +
							" where clm.warehouse_no = '"+strWarehouseNo+"' " +
							" and clm.enterprise_no='"+strEnterpriseNo+"' and rownum = 1";
					List list2 = genDao.getListByNativeSql(strSql2);
					if(list2.size()==0)
					{
						return new MsgRes(false,TipUtil.getTipsByKey("E22506"),null);//读取不到工作流
					}else
					{
						Object[] obj=(Object[])list2.get(0);
						String strSql3 = "select a.status_type as status " +
								" from (select wds.status_type" +
								" from wms_warehouse_outorder_flow wwof" +
								" inner join wms_deflabel_status wds" +
								" on wds.flow_value = wwof.flow_value" +
								" and wds.flow_flag = '2' " +//--0：为非工作流节点，1：为进货工作流节点，2：为出货工作流节点 
								" where wwof.flow_flag = '1'  " +//--标志 0：不执行；1：执行
								" and wwof.exp_type = '"+obj[0].toString()+"'" +
								" and wwof.flow_value > '"+obj[1].toString()+"'" +
								" and wwof.enterprise_no = '"+strEnterpriseNo+"'"  +
								" and wwof.warehouse_no = '"+strWarehouseNo+"'"  +
								" order by wds.status_type) a where rownum = 1";
						String strSql4 = "select a.status_type as status " +
								" from (select wds.status_type" +
								" from wms_outorder_flow wof" +
								" inner join wms_deflabel_status wds" +
								" on wds.flow_value = wof.flow_value" +
								" and wds.flow_flag = '2' " +//--0：为非工作流节点，1：为进货工作流节点，2：为出货工作流节点 
								" where wof.flow_flag = '1' " +//--标志 0：不执行；1：执行 
								" and wof.exp_type = '"+obj[0].toString()+"'" +
								" and wof.flow_value > '"+obj[1].toString()+"'" +
								" and wof.enterprise_no = '"+strEnterpriseNo+"'" +
								" order by wds.status_type) a where rownum = 1";
						List list3 = genDao.getListByNativeSql(strSql3);
						if(list3.size()==0)
						{
							List list4 = genDao.getListByNativeSql(strSql4);
							if(list4.size()==0)
							{
								return new MsgRes(false,TipUtil.getTipsByKey("E22507"),null);//读取不到工作流的状态
							}else
							{
								Object[] obj4=(Object[])list4.get(0);
								status = list4.get(0).toString();
							}
						}else
						{
							Object[] obj3=(Object[])list3.get(0);
							status = list3.get(0).toString();
						}
					}
					//若存在，必须是当前待分播的客户标签；标签状态可为60（新取号，不做配送对象、线路、波次一致性的校验）
					if(!list.get(i).getStatus().equals(status))
					{
						return new MsgRes(false,TipUtil.getTipsByKey("E22508"),null);//该标签状态不在下一个工作流范围内！
					}
					//目的标签的配送对象和当前分播数据的配送对象、线路必须一致；
					if(list.get(i).getStatus().equals("60") && 
							list.get(i).getLineNo() !=oddList.get(0).getLineNo() && 
							list.get(i).getDeliverObj() != oddList.get(0).getDeliverObj())
					{
						return new MsgRes(false,TipUtil.getTipsByKey("E22508"),null);//该标签状态不在下一个工作流范围内！
					}
					//读取系统参数Divide_CheckWave，若参数为0，目的标签的波次需跟当前标签一致，若参数为1，可不一致。
					if(g_strDivideCheckWave.equals("0"))
					{
						List<Stock_LabelMModel> list5 = genDao.getListByNativeSql(strSql5, Stock_LabelMModel.class);
						if(list5.size()==0)
						{
							return new MsgRes(false,TipUtil.getTipsByKey("E22509"),null);//读取不到波次号
						}else
						{
							for(int j=0; j<list5.size(); j++)
							{
								if(!list5.get(j).getWaveNo().equals(list.get(i).getWaveNo()))
								{
									return new MsgRes(false,TipUtil.getTipsByKey("E22503"),null);//源标签号与目的标签号波次不一样
								}
							}
						}
						
					}
				}else
				{
					return new MsgRes(false,TipUtil.getTipsByKey("E22501"),null);//该标签号为非客户标签，且已被占用！
				}
			}
		}else//若此标签不存在，检查库存里是否有此标签，若有，拦截；
		{
			String strSql6 = "select * from stock_content a where a.label_no='"+strLabelNo+"' and a.enterprise_no='"+strEnterpriseNo+"' ";
			List list6 = genDao.getListByNativeSql(strSql6);
			if(list6.size()!=0)
			{
				return new MsgRes(false,TipUtil.getTipsByKey("E22502"),null);//该标签已被占用！	
			}else//若库存不存在此标签，根据系统参数Divide_FixPal，若参数设置为0，系统给予拦截； 若参数设置为1，根据此标签号产生标签信息。
			{
				if(g_strDivideFixPal.equals("0"))
				{
					return new MsgRes(false,TipUtil.getTipsByKey("E22510"),null);//此标签不存在
				}
			}
		}*/
		return new MsgRes(true,"",null);
	}
}
