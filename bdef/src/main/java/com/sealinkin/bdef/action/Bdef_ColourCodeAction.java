package com.sealinkin.bdef.action;

import com.sealinkin.bdef.model.Bdef_ColourCodeModel;
import com.sealinkin.bdef.service.IBdef_ColourCodeService;
import com.sealinkin.comm.action.CommAction;
import com.sealinkin.comm.model.ExtListDataBo;


public class Bdef_ColourCodeAction extends CommAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IBdef_ColourCodeService bdef_ColourCodeImpl;
	
	
	public void getCodeGroup(){
		try 
		{		
			ExtListDataBo<Bdef_ColourCodeModel> codeGroup =bdef_ColourCodeImpl.getCodeGroup();
			super.writeObj(codeGroup);		
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	
	public void getCodeDistribution(){

		try 
		{		
			ExtListDataBo<Bdef_ColourCodeModel> codeGroup =bdef_ColourCodeImpl.getCodeDistribution();
			super.writeObj(codeGroup);		
		} catch (Exception e){
			e.printStackTrace();
		}
	
	
	}
	
	
	public void getNotCodeDistribution(){
		try 
		{		
			ExtListDataBo<Bdef_ColourCodeModel> codeGroup =bdef_ColourCodeImpl.getNotCodeDistribution();
			super.writeObj(codeGroup);		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	public IBdef_ColourCodeService getBdef_ColourCodeImpl() {
		return bdef_ColourCodeImpl;
	}
	public void setBdef_ColourCodeImpl(IBdef_ColourCodeService bdef_ColourCodeImpl) {
		this.bdef_ColourCodeImpl = bdef_ColourCodeImpl;
	}	
}
