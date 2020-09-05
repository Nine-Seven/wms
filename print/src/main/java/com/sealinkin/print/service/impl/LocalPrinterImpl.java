package com.sealinkin.print.service.impl;

import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.odata.model.Odata_ExpDModel;
import com.sealinkin.odata.model.Odata_ExpMModel;
import com.sealinkin.odata.po.Odata_CheckLabelD;
import com.sealinkin.odata.po.Odata_ExpM;
import com.sealinkin.print.service.LocalPrinterService;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes","unchecked"})
public class LocalPrinterImpl implements LocalPrinterService {
    private IGenericManager genDao;

    public IGenericManager getGenDao()
    {
        return genDao;
    }
    public void setGenDao(IGenericManager genDao)
    {
        this.genDao = genDao;
    }

    @Override
    public List getExp_MList(String lableNo) throws Exception {
        List list = new ArrayList();
        //获取expNo 出货单号

        String sql = "SELECT EXP_NO expNo FROM ODATA_EXP_M  WHERE SOURCEEXP_NO= '"+lableNo+"'";
        List<Odata_ExpMModel> checkLabelDS = genDao.getListByNativeSql(sql, Odata_ExpMModel.class);
        if (checkLabelDS.size() == 0) return list;
        //获取出货详情
        sql = "select * FROM ODATA_EXP_M  WHERE EXP_NO = '"+checkLabelDS.get(0).getExpNo()+"'";
        List<Odata_ExpM> expMS = genDao.getListByNativeSql(sql,Odata_ExpM.class);
        //出货信息加入返回值
        list.add(expMS.get(0));
        //获取商品详情
        sql = "select a.exp_no,a.article_no,c.barcode,c.article_name," +
                "a.packing_qty," +
                "c.qmin_operate_packing,c.unit_packing, " +
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingUnit,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingUnitQmin,"+
                "f_get_packingunit(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) Unit,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,a.packing_qty) packingSpec,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.qmin_operate_packing) packingSpecQmin,"+
                "f_get_spec(a.enterprise_no,a.owner_no,a.article_no,c.unit_packing) spec,"+
                "a.owner_article_no," +
                "a.article_qty,a.unit_cost," +
                "trunc(a.article_qty/a.packing_qty) as planBox," +
                "trunc(mod(a.article_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) as planQmin," +
                "(a.article_qty - trunc(a.article_qty/a.packing_qty) * a.packing_qty - trunc(mod(a.article_qty,a.packing_qty)/c.QMIN_OPERATE_PACKING) * c.QMIN_OPERATE_PACKING) as planDis," +

                "a.produce_condition as produceCond," +
                "a.produce_value1 as produceV1," +
                "a.produce_value2 as produceV2, " +
                "a.lotno_condition as lotnoCondition,"+
                "a.lotno_value1 as lotnoValue1,"+
                "a.lotno_value2 as lotnoValue2, " +
                "A.SPECIFY_FIELD,A.SPECIFY_CONDITION,A.SPECIFY_VALUE1,A.SPECIFY_VALUE2 "+
                "from Odata_Exp_D a,bdef_defarticle c," +
                "bdef_article_packing d " +
                "where a.enterprise_no=c.enterprise_no and a.enterprise_no=d.enterprise_no(+) and " +
                "a.article_no=c.article_no and " +
                "a.article_no=d.article_no(+) and " +
                "a.packing_qty=d.packing_qty(+) and a.exp_no='"+checkLabelDS.get(0).getExpNo()+"'";
        List<Odata_ExpDModel> expDs=genDao.getListByNativeSql(sql, Odata_ExpDModel.class);
        list.add(expDs);
        return list;
    }
}
