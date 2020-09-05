package com.sealinkin.bdef.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_ColourCodeModel;
import com.sealinkin.bdef.service.IBdef_ColourCodeService;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.dao.service.IGenericManager;


@SuppressWarnings({"rawtypes"})
public class Bdef_ColourCodeImpl implements IBdef_ColourCodeService {
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
	public ExtListDataBo<Bdef_ColourCodeModel> getCodeGroup() throws Exception {
		
		List<Bdef_ColourCodeModel> list =new ArrayList<Bdef_ColourCodeModel>();
		Bdef_ColourCodeModel bcc= new Bdef_ColourCodeModel();
		bcc.setCodeGroup("100001");
		bcc.setCodeName("红色组");
		list.add(bcc);
	
		ExtListDataBo<Bdef_ColourCodeModel> extListBo = new ExtListDataBo<Bdef_ColourCodeModel>(list,1);
		return extListBo;	
	}
	
	
	@Override
	public ExtListDataBo<Bdef_ColourCodeModel> getCodeDistribution()
			throws Exception {
		
		List<Bdef_ColourCodeModel> list =new ArrayList<Bdef_ColourCodeModel>();
		Bdef_ColourCodeModel bcc= new Bdef_ColourCodeModel();
		bcc.setCodeName("铁锈红");
		list.add(bcc);
		
		Bdef_ColourCodeModel bcc1= new Bdef_ColourCodeModel();
		bcc1.setCodeName("西施红");
		list.add(bcc1);
		
		Bdef_ColourCodeModel bcc2= new Bdef_ColourCodeModel();
		bcc2.setCodeName("西紫红");
		list.add(bcc2);
		
		Bdef_ColourCodeModel bcc3= new Bdef_ColourCodeModel();
		bcc3.setCodeName("玫红");
		list.add(bcc3);
		
		Bdef_ColourCodeModel bcc4= new Bdef_ColourCodeModel();
		bcc4.setCodeName("桃红");
		list.add(bcc4);
		
		Bdef_ColourCodeModel bcc5= new Bdef_ColourCodeModel();
		bcc5.setCodeName("粉红");
		list.add(bcc5);
		
		Bdef_ColourCodeModel bcc6= new Bdef_ColourCodeModel();
		bcc6.setCodeName("浅红");
		list.add(bcc6);
		
		ExtListDataBo<Bdef_ColourCodeModel> extListBo = new ExtListDataBo<Bdef_ColourCodeModel>(list,7);
		return extListBo;	
	}
	@Override
	public ExtListDataBo<Bdef_ColourCodeModel> getNotCodeDistribution()
			throws Exception {
		
		List<Bdef_ColourCodeModel> list =new ArrayList<Bdef_ColourCodeModel>();
		Bdef_ColourCodeModel bcc= new Bdef_ColourCodeModel();
		bcc.setCodeName("白色");
		list.add(bcc);
		
		Bdef_ColourCodeModel bcc1= new Bdef_ColourCodeModel();
		bcc1.setCodeName("牙白");
		list.add(bcc1);
		
		Bdef_ColourCodeModel bcc2= new Bdef_ColourCodeModel();
		bcc2.setCodeName("黑色");
		list.add(bcc2);
		
		Bdef_ColourCodeModel bcc3= new Bdef_ColourCodeModel();
		bcc3.setCodeName("黑白");
		list.add(bcc3);
		
		Bdef_ColourCodeModel bcc4= new Bdef_ColourCodeModel();
		bcc4.setCodeName("黑红");
		list.add(bcc4);
		
		Bdef_ColourCodeModel bcc5= new Bdef_ColourCodeModel();
		bcc5.setCodeName("肉色");
		list.add(bcc5);
		
		Bdef_ColourCodeModel bcc6= new Bdef_ColourCodeModel();
		bcc6.setCodeName("灰色");
		list.add(bcc6);
		
		Bdef_ColourCodeModel bcc7= new Bdef_ColourCodeModel();
		bcc7.setCodeName("银灰");
		list.add(bcc7);
		
		Bdef_ColourCodeModel bcc8= new Bdef_ColourCodeModel();
		bcc8.setCodeName("蓝色");
		list.add(bcc8);
		
		Bdef_ColourCodeModel bcc9= new Bdef_ColourCodeModel();
		bcc9.setCodeName("深蓝色");
		list.add(bcc9);
		
		ExtListDataBo<Bdef_ColourCodeModel> extListBo = new ExtListDataBo<Bdef_ColourCodeModel>(list,7);
		return extListBo;	
	}

}
