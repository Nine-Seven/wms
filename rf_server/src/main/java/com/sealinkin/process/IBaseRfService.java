package com.sealinkin.process;

import com.sealinkin.comm.model.socket.CBizAnswerModel;

public interface IBaseRfService {
	public CBizAnswerModel doRfApplication(Integer nReqType,String strRecvData);
}
