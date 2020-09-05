package com.sealinkin.protocolExchange.comm;

public class CommShowQtyModel {
	/// <summary>
    /// 是否显示箱数
    /// </summary>
	private boolean ShowBox;
    /// <summary>
    /// 是否显示最小操作包装数
    /// </summary>
	private boolean ShowQmin;
    /// <summary>
    /// 是否显示基本量
    /// </summary>
	private boolean ShowBase;
	public boolean isShowBox() {
		return ShowBox;
	}
	public void setShowBox(boolean showBox) {
		ShowBox = showBox;
	}
	public boolean isShowQmin() {
		return ShowQmin;
	}
	public void setShowQmin(boolean showQmin) {
		ShowQmin = showQmin;
	}
	public boolean isShowBase() {
		return ShowBase;
	}
	public void setShowBase(boolean showBase) {
		ShowBase = showBase;
	}

	
}
