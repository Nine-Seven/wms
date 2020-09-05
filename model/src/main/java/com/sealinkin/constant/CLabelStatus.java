package com.sealinkin.constant;

public class CLabelStatus {

	//入库状态
	public static final String RECEIVED = "0"; //已验入
	public static final String LOCATE_TOCELL="25";//定位完成
	public static final String GETTask_TOCELL="26";//上架发单
	public static final String INSTOCK_COMFIRE="F1";//上架回单
	
	//出货状态
	public static final String PICK_HAND_OUT="50";//拣货发单
	public static final String PICK_RETURN_ZERO="FF";//拣货零回
	public static final String HasRelevanceBox="51";//已关联物流箱
	public static final String PICK_END="52";//拣货完成（分播）
	public static final String PICK_MOVING="55";//拣货输送线运输中
	public static final String NEW_LABEL_NO="60";//新取号
	public static final String RECEIVING="61";//整理中(接收品项并入)
	public static final String LOADED_IN_PAL="62";//已并板
	public static final String DIVIDE_FROM_PAL="63";//拆板生成
	public static final String SORTING_PASS="65";//已过分拣
	public static final String CONFIRM="66";//整理确认
	public static final String INNER_CHECKING="6A";//内复核中
	public static final String INNER_CHECKED="6B";//内复核完成
	public static final String WAIT_OUTER_CHECK="6C";//待外复核
	public static final String OUTER_CHECKING="6D";//外复核中
	public static final String OUTER_CHECKED="6E";//外复核完成
	public static final String WAIT_TURN_AREA="90";//已装区
	public static final String WAIT_LOAD_CAR="A0";//待装车
	public static final String LOADED_IN_CAR="A1";//已装车
	public static final String DELIVERIED="A2";//已配送
	public static final String OM_CANCEL_Lock="C0";//撤票锁定
	public static final String DIVIDED="F3";//全部分播完毕（释放）
	public static final String DIVIDED_CANCEL="FB";//分播取消
	public static final String ARRANGE_COMPLETE="F4";//整理完成
	public static final String PICK_CLOSE="F5";//拣货转移完成，销毁
	public static final String PICK_CANCEL="FC";//拣货取消
	public static final String INSIDE_CHECK_CANCEL="FD";//内复核取消
	public static final String OUT_CHECK_CANCEL="FE";//外复核取消
	public static final String SPLIT_LABEL_CANCEL="FH";//拆板销毁
	public static final String BillCANCEL_LABEL_CANCEL="FI";//撤票销毁
	
	//移库（补货）
	public static final String MOVE_HAND_OUT="40";//移库发单
	public static final String OUTSTOCKING="41";//移库下架
	public static final String MOVING="42";//移库搬运
	public static final String INSTOCKING="43";//移库部分上架
	public static final String INSTOCKED="F1";//移库上架完成（补货上架完成）
}
