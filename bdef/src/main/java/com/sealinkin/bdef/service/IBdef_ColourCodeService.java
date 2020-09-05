package com.sealinkin.bdef.service;

import com.sealinkin.bdef.model.Bdef_ColourCodeModel;
import com.sealinkin.comm.model.ExtListDataBo;

public interface IBdef_ColourCodeService {

	ExtListDataBo<Bdef_ColourCodeModel> getCodeGroup() throws Exception;

	ExtListDataBo<Bdef_ColourCodeModel> getCodeDistribution() throws Exception;

	ExtListDataBo<Bdef_ColourCodeModel> getNotCodeDistribution() throws Exception;

}
