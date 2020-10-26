package com.sealinkin.print.service.impl;

import com.sealinkin.bdef.po.Bdef_DefOwner;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.model.Odata_OutstockDModel;
import com.sealinkin.print.service.LocalPrinterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LocalPrinterImpl implements LocalPrinterService {
    private IGenericManager genDao;

    public IGenericManager getGenDao() {
        return genDao;
    }

    public void setGenDao(IGenericManager genDao) {
        this.genDao = genDao;
    }

    @Override
    public List getExp_MList(List<String> expNos) throws Exception {
        //获取expNo 出货单号
        StringBuilder sb = new StringBuilder();
        if (expNos.size() == 0) return null;
        //获取出货详情
        sb.setLength(0);
        sb.append("select * FROM ODATA_EXP_M  WHERE EXP_NO in (");
        for (String expNo : expNos) {
            sb.append("'").append(expNo).append("',");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") order by  exp_no");

        List<Odata_ExpMModel> expMS = genDao.getListByNativeSql(sb.toString(), Odata_ExpMModel.class);
        List oms = new ArrayList();
        for (Odata_ExpMModel m : expMS) {
            Map om = new HashMap();
            //判断是否为拼多多订单
            if (m.getRsvVarod5() != null) {
                om.put("status",0);
                //获取拼多多信息
                String sql = "select * FROM BDEF_DEFOWNER  WHERE OWNER_NO = '" + m.getOwnerNo() + "'";
                List<Bdef_DefOwner> owner = genDao.getListByNativeSql(sql, Bdef_DefOwner.class);
                om.put("owner",owner.get(0));
            } else {
                om.put("status",1);
            }

            om.put("expMS", m);
            //获取商品详情
            String sql = "SELECT a.exp_no,a.article_no,a.OWNER_ARTICLE_NO,c.article_name," +
                    "trunc(a.article_qty / a.packing_qty) AS planbox " +
                    "FROM  odata_exp_d  a left JOIN  bdef_defarticle  c " +
                    "ON a.ARTICLE_NO = c.ARTICLE_NO " +
                    "where EXP_NO = '" + m.getExpNo() + "'";
            List<Odata_ExpDModel> expDs = genDao.getListByNativeSql(sql, Odata_ExpDModel.class);
            om.put("expDS", expDs);
            //循环打印次数
            sql = "update odata_exp_m  set WAYBILL_PRINT_STATUS=WAYBILL_PRINT_STATUS+1 WHERE EXP_NO = '" + m.getExpNo() + "'";
            this.genDao.updateBySql(sql);
            oms.add(om);
        }


        return oms;
    }

    @Override
    public List getPickList(String waveNo) {
        String sql = "SELECT\n" +
                "	ood.s_cell_no,\n" +
                "	ood.realqty,\n" +
                "	ood.outstock_no,\n" +
                "	bd.owner_article_no,\n" +
                "	bd.article_name,\n" +
                "	bd.UNIT,\n" +
                "	bd.SPEC \n" +
                "FROM\n" +
                "	bdef_defarticle bd,\n" +
                "	( SELECT SUM( article_qty ) realqty, s_cell_no, article_no, outstock_no, wave_no FROM odata_outstock_d GROUP BY s_cell_no, article_no, outstock_no, wave_no ) ood \n" +
                "WHERE\n" +
                "	ood.article_no = bd.article_no \n" +
                "	AND ood.wave_no = '"+waveNo+"'";
        List<Odata_OutstockDModel> list=genDao.getListByNativeSql(sql, Odata_OutstockDModel.class);
        return list;
    }

    @Override
    public List getExpNos(String waveNo) {
        String sql = " SELECT exp_no  FROM odata_exp_m   WHERE wave_no = '"+waveNo+"' order by  exp_no";
        return  genDao.getListByNativeSql(sql);
    }
}
