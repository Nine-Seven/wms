package com.sealinkin.protocolExchange;

public class SessionDefine {
	public static final int PACK_ACK_RADIX = 1000;
	
	public static final int GC_Session_Type_Login = 1;//登录
	public static final int GC_Session_Type_UpdatePassword = 2;//修改密码
    public static final int GC_Session_Type_GetProg = 3;//自动更新>>获取更新任务 
    public static final int GC_Session_Type_GetDBConfig = 4;//RF自动更新>>获取db、ftp配置
    public static final int GC_Session_Type_GetRFProg = 5;//RF自动更新>>获取更新任务
    public static final int GC_Session_Type_GetProgVer = 6;//自动更新>>获取更新任务版本
    public static final int GC_Session_Type_GetRFProgVer = 7;//RF自动更新>>获取更新任务版本
    public static final int GC_Session_Type_QtyShow = 8;//获取RF系统允许显示的数量信息
    
	public static final int GC_Session_Type_Idata_DockValidate = 12;
	public static final int GC_SESSION_TYPE_Idata_CHECK_SerialNo = 13;//扫描流水号
	public static final int GC_SESSION_TYPE_Idata_CHECK_LabelNo = 14;//存储IS扫描标签号
    public static final int GC_SESSION_TYPE_Idata_CHECK_Barcode = 15;//扫描商品条码
    public static final int GC_SESSION_TYPE_Idata_CHECK = 16;//存储验收事件    
    public static final int GC_SESSION_TYPE_Idata_CHECK_CloseLabel = 17;//封板事件
    public static final int GC_SESSION_TYPE_Idata_CHECK_GetLot = 18;//获取商品批号信息
    public static final int GC_SESSION_TYPE_Idata_CHECK_CheckExists = 19;//校验是否能验收
    public static final int GC_SESSION_TYPE_IM_CHECK_LogisticsCode = 20;//验收扫描物流码
    public static final int GC_SESSION_TYPE_IM_CHECK_ClearLogisticsCode = 21;//验收扫描物流码退出清除bdef_scan_log表
    public static final int GC_SESSION_TYPE_BoxCodeInput = 22;//条码采集 扫描储位
    public static final int GC_SESSION_TYPE_BoxCodeOK = 23;//条码采集 外箱码确认 
    public static final int GC_SESSION_TYPE_BarCodeSerialNo = 24;//条码采集 扫流水
    public static final int GC_SESSION_TYPE_BarCodeOK = 25;//条码采集 确认
    public static final int GC_SESSION_TYPE_LogisticsCodeOK = 26;//条码采集 物流码确认
    public static final int GC_SESSION_TYPE_BoxCodeSerialNo = 27;//箱码采集 扫流水
    public static final int GC_SESSION_TYPE_LogisticsBarcodeInput = 28;//物流码采集 扫条码
    public static final int GC_SESSION_TYPE_ImportDetailByOver = 30;//超品验收--新增进货单以及汇总单明细 huangbin 20160712
    public static final int GC_SESSION_TYPE_getDeffieldvalInfo = 31;//获取码表信息 huangbin 20160712
    public static final int GC_SESSION_TYPE_Idata_GetArticlePacking = 40;//RF获取商品各包装信息
        
    public static final int GC_SESSION_TYPE_ID_DOCK = 101;//校检码头
    public static final int GC_SESSION_TYPE_ID_SerialNo = 102;//直通ID扫描标签号
    public static final int GC_SESSION_TYPE_ID_LabelNo = 103;//校检板号
    public static final int GC_SESSION_TYPE_ID_Barcode = 104;//直通扫描商品条码
    public static final int GC_SESSION_TYPE_ID_Check = 105;//直通验收确认
    public static final int GC_SESSION_TYPE_ID_CloseLabel = 106;     //直通封板
    public static final int GC_SESSION_TYPE_ID_CheckTTH = 107;//直通验收确认(天天惠)
    public static final int GC_SESSION_TYPE_ID_GetArticleInfoByBarcodeAndPoNo = 108;//天天惠根据条码和原单号获取商品信息
    public static final int GC_SESSION_TYPE_ID_CheckBarcode=109;//校验商品是否可以验收（天天惠）
    public static final int GC_SESSION_TYPE_ID_CHECKSAVEANDCLOSEPAL = 110;//直通验收保存并封板
    public static final int GC_SESSION_TYPE_ID_END = 149;//直通进货结束

    public static final int GC_SESSION_TYPE_INSTOCK_BEGIN = 150;//进货上架回单开始

    public static final int GC_SESSION_TYPE_INSTOCK_LabelNo = 151;//校检标签
    public static final int GC_SESSION_TYPE_INSTOCK_Receipt = 152;//上架回单
    public static final int GC_SESSION_TYPE_INSTOCK_Barcode = 153;//上架扫条码
    public static final int GC_SESSION_TYPE_INSTOCK_END = 199;//进货上架回单结束
    
    public static final int GC_SESSION_TYPE_OM_GetArticleInfo = 201;        //获取商品信息
    public static final int GC_SESSION_TYPE_OM_GetOutStockDateInfo = 240; //获取拣货信息
    public static final int GC_SESSION_TYPE_OM_PickReceipt = 241;     //拣货回单
    public static final int GC_SESSION_TYPE_OM_ReceiptPicking = 242;     //RF拣货回单 按下架单 SUNL
    public static final int GC_SESSION_TYPE_OM_CheckLabel = 243;     //RF标签检查 SUNL
    public static final int GC_SESSION_TYPE_OM_GetOutStockInfo = 244;     //RF拣货回单 取下架单信息 SUNL
    public static final int GC_SESSION_TYPE_OM_CHECKWorkerNo=245;//校验员工号

    public static final int GC_SESSION_TYPE_OM_GetOutStockDateInfoExpNo = 246; //获取拣货信息
    public static final int GC_SESSION_TYPE_OM_GetOutStockDateExpNo = 247; //扫下架储位（边拣边分）
    public static final int GC_SESSION_TYPE_OM_GetOutStockQtyExpNo = 248; //获取分播数据（边拣边分根据配送对象）
   
    
    
    public static final int GC_SESSION_TYPE_OM_GetDivideSumDate = 300;        //扫描标签号 获取分播信息
    public static final int GC_SESSION_TYPE_OM_SaveDivide = 301;        //分播保存
    public static final int GC_SESSION_TYPE_OM_SaveDivideDPS = 302;        //分播保存DPS
    public static final int GC_SESSION_TYPE_OM_GetNO = 303;        //分播取号
    
    public static final int GC_SESSION_TYPE_OM_ARRANGE_ExistsSLabelNo = 321;//检查源容器
    public static final int GC_SESSION_TYPE_OM_ARRANGE_ExistsDLabelNo = 322;//检查目的容器
    public static final int GC_SESSION_TYPE_OM_ARRANGE_GetSLabelNoArticle = 323;//获取源容器商品信息
    public static final int GC_SESSION_TYPE_OM_Arrange = 324;//容器整理
    public static final int GC_SESSION_TYPE_OM_ArrangeBoxs = 325;//整箱转移
    public static final int GC_SESSION_TYPE_OM_ARRANGECONFIRM_ScanLabel = 326;//整理确认-扫描
    public static final int GC_SESSION_TYPE_OM_ARRANGECONFIRM = 327;//整理确认
    public static final int GC_SESSION_TYPE_OM_CHECKDOCK=328;//校验码头号
    public static final int GC_SESSION_TYPE_OM_PRINTDLABEL=329;//打印目的容器
    public static final int GC_SESSION_TYPE_OM_CHECKCUSTNO=330;//检验客户
    public static final int GC_SESSION_TYPE_OM_GetContainerArrangeNO=331;//容器整理取号
    
    public static final int GC_SESSION_TYPE_OM_GetLoadproposeInfo = 351;        //获取装车信息
    public static final int GC_SESSION_TYPE_OM_InsertLoadproposeM = 352;        //新增装车主挡
    public static final int GC_SESSION_TYPE_OM_InsertLoadproposeD= 353;        //新增装车明细
    public static final int GC_SESSION_TYPE_OM_SaveDeliver = 354;        //封车

    public static final int GC_SESSION_TYPE_OM_GetLoadproposeM = 355;        //天天惠获取装车建议单号，获取不到则新增装车建议单
    public static final int GC_SESSION_TYPE_OM_CheckCustLabelNo = 356;        //天天惠获取获校验客户/标签以及取装车客户装车信息
    public static final int GC_SESSION_TYPE_OM_CustClose = 357;        //天天惠装车客户扫描确认
    public static final int GC_SESSION_TYPE_OM_CreateLoadItem = 358;   //天天惠标签装车
    public static final int GC_SESSION_TYPE_OM_CloseCar_TTH = 359;   //天天惠封车
    public static final int GC_SESSION_TYPE_OM_CloseCar_Save = 360;       //铁越封车
    public static final int GC_SESSION_TYPE_OM_Check_LABEL_CANCEL = 361;        //标签销毁检查
    public static final int GC_SESSION_TYPE_OM_LABEL_CANCEL = 362;        //标签销毁
 
    
    public static final int GC_SESSION_TYPE_OM_LABEL_LABLENO = 363;         //派车单号，并校验是否可装车的过程
    public static final int GC_SESSION_TYPE_ID_DOCKNO = 364;               //铁越码头号        
    public static final int GC_SESSION_TYPE_ID_CONTAINERNO = 365;          //铁越装车确认
    public static final int GC_SESSION_TYPE_ID_GetLoadproposeM = 366;       //铁越装车建议单，获取不到则新增装车建议单

    public static final int GC_SESSION_TYPE_OM_GetSelectIn=368;           //已扫标签
    public static final int GC_SESSION_TYPE_WM_GetSelectIn=369;          //未扫标签
    public static final int GC_SESSION_TYPE_ID_GetLoadproposeMLine = 371; //扫描线路
    public static final int GC_SESSION_TYPE_ID_GetLoadproposeMZ = 372;//扫描路顺确认
    public static final int GC_SESSION_TYPE_ID_GetLoadproposeMS = 373;//装车标签数量确认


    public static final int GC_SESSION_TYPE_OM_GetBufferNo = 375;        //装车获取暂存区编码
    
    public static final int GC_SESSION_TYPE_OM_LineScanLineNo = 376;        //按线路装车---扫描线路
    public static final int GC_SESSION_TYPE_OM_LineScanCustNo = 377;        //按线路装车---扫描客户编码/客户箱标签
    public static final int GC_SESSION_TYPE_OM_LineScanAreaNo = 378;        //按线路装车---扫描 暂存区编码（月台号）+输入物流箱数
    public static final int GC_SESSION_TYPE_OM_LineCloseCar = 379;        //按线路装车---封车
    public static final int GC_SESSION_TYPE_OM_LineCustScanOver = 380;        //按线路装车---客户扫描完成
    
    public static final int GC_SESSION_TYPE_OM_SScanCustNo = 381;        //小嘴普通装车模式---扫描客户、标签
    public static final int GC_SESSION_TYPE_OM_SLoadCar = 382;        //小嘴普通装车模式---装车
    public static final int GC_SESSION_TYPE_OM_SCloseCar = 383;        //小嘴普通装车模式---封车
    public static final int GC_SESSION_TYPE_OM_SCloseCust = 384;        //小嘴普通装车模式---客户扫描完成
    
    public static final int GC_SESSION_TYPE_OM_ScanSkuCancelWithoutDate = 386;  //扫描销毁商品不管生产日期
    public static final int GC_SESSION_TYPE_OM_ScanSkuCancel = 387;  //扫描销毁商品
    public static final int GC_SESSION_TYPE_OM_CheckSku_LABEL_CANCEL = 388;        //SKU标签销毁检查
    public static final int GC_SESSION_TYPE_OM_SkuLABEL_CANCEL = 389;        //SKU标签销毁
    
    public static final int GC_SESSION_TYPE_OM_MergePal_ScanPlateNo = 401;//装并板 扫箱扫板
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_WaitLabel = 402;//整理确认 获取待整理确认的标签信息
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_LabelBoxCount = 403;//整理确认 扫描板标签后，返回当前板标签下的标签个数
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_GetPlateLabelInfo = 404;//整理确认 扫描板标签后，返回当前板标签下的波次号，配送对象，暂存区，总箱数
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_SaveMergePalAndAerrange = 405;//自动装并板+整理确认
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_GetWaitAerrangeInfo = 406;//获取待整理的数据

    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_OutStockRegister = 407;//获取待整理的数据
    public static final int GC_SESSION_TYPE_OM_AerrangeConfirm_GetOutStockArticleInfo = 408;//获取下架单所属的商品明细信息
    
    public static final int GC_SESSION_TYPE_OM_Check_GetSourceExpNo = 409;//获取上位系统下传的出货单号 huangb 20160519
    public static final int GC_SESSION_TYPE_OM_Check_ExpCheckScanOrder = 410;//出库扫描-整单扫描 huangb 20160520
    public static final int GC_SESSION_TYPE_OM_Check_GetArticleInfoByScan = 411;//出库扫描-扫描商品 huangb 20160520
    public static final int GC_SESSION_TYPE_OM_Check_ExpCheckScanBarcode = 412;//出库扫描-商品扫描保存数据 huangb 20160523
    
    public static final int GC_SESSION_TYPE_OM_CheckClaimDock = 413;//拣货索单 校验码头号 sunl 20160526
    public static final int GC_SESSION_TYPE_OM_ClaimOrder = 414;//拣货索单 索单 sunl 20160526
    
    public static final int GC_SESSION_TYPE_OM_Load_GetLoadNoByShipperNo = 415;//面单交接-根据承运商编号获取装车单号 huangb 20160606
    public static final int GC_SESSION_TYPE_OM_Load_InserLoadDByShipper = 416;//面单交接-新增装车建议单明细 huangb 20160606
    public static final int GC_SESSION_TYPE_OM_Load_DeliverCarByShipper = 417;//面单交接-根据承运商封车 huangb 20160606
    
    public static final int GC_SESSION_TYPE_OM_CheckScanDataByWave = 418;//按波次装车--扫描客户或标签 huangb 20160707
    public static final int GC_SESSION_TYPE_OM_LoadCarByWaveNo = 419;//按波次装车  huangb 20160707
    public static final int GC_SESSION_TYPE_OM_GetCarInfo = 420;//按波次装车--获取车辆或司机信息  huangb 20160708
    public static final int GC_SESSION_TYPE_OM_DeliverCarByWaveNo = 421;//按波次封车  huangb 20160708
    
    public static final int GC_SESSION_TYPE_OM_SaveLabelWeigh = 422;//保存标签重量  huangb 20160720
    public static final int GC_SESSION_TYPE_OM_GetLabelInfo = 423;//称重获取标签信息  huangb 20160720

    public static final int GC_SESSION_TYPE_OM_CollectGoods_ScanPO_NO = 431;//集货作业--扫提单号 sunl 20160714
    public static final int GC_SESSION_TYPE_OM_CollectGoods_ScanSourceExpNO = 432;//集货作业--扫订单号 sunl 20160714
    
    public static final int GC_SESSION_TYPE_RIDATA_Begin = 700;        //返配开始
    public static final int GC_SESSION_TYPE_RIDATA_InPutSerialNo = 701;        //扫描流水号
    public static final int GC_SESSION_TYPE_RIDATA_InPutBarcodeNo = 702;        //扫描条码
    public static final int GC_SESSION_TYPE_RIDATA_CheckSave = 703;        //验收保存
    public static final int GC_SESSION_TYPE_RIDATA_CheckLabel = 704;        //校验板号  
    public static final int  GC_SESSION_TYPE_RI_CHECK_DOCKTY=705;         //校验码头
    public static final int GC_SESSION_TYPE_RIDATA_ScanBarcodeNoTY=706;   //扫条码确认 
    //天天惠
  
    public static final int GC_SESSION_TYPE_RIDATA_ScanDock = 720;                  //扫描码头
    public static final int GC_SESSION_TYPE_RIDATA_ScanUntreadNoAndWallNo = 721;    //扫描返配单号和墙号进行资源试算
    public static final int GC_SESSION_TYPE_RIDATA_ScanBarcodeNo = 722;             //扫描条码
    public static final int GC_SESSION_TYPE_RIDATA_GetCellNo = 723;                 //获取过季品建议储位
    public static final int GC_SESSION_TYPE_RIDATA_GetQualityByWallNo = 724;        //根据分播墙获取商品的类型
    public static final int GC_SESSION_TYPE_RIDATA_CloseLabel = 725;                //封箱
    public static final int GC_SESSION_TYPE_RIDATA_RI_CHECK=727;
    public static final int GC_SESSION_TYPE_RIDATA_GetPoNO = 726;					//获取单号
    public static final int GC_SESSION_TYPE_RI_CHECK_CheckExists=728;               //反配验收校验
    public static final int GC_SESSION_TYPE_RI_CHECK_CloseLabel=729;                //封板
    public static final int GC_SESSION_TYPE_RI_CHECK_GetLot=732;                    //获取批次信息
    
    //返配上架整理
    public static final int GC_SESSION_TYPE_RIDATA_CHECKLABELANDGETINFO = 730;      //检验标签号并且获取商品信息
    public static final int GC_SESSION_TYPE_RIDATA_ARRANGECOMFIRE = 731;            //返配上架整理确认
    
    //铁越 RF 反配 
    public static final int GC_SESSION_TYPE_RIDATA_RI_ScanBarcodeNO = 741;      //扫描条码 
    public static final int GC_SESSION_TYPE_RIDATA_RI_GetLotInfo = 742;      //获取批次信息
    public static final int GC_SESSION_TYPE_RIDATA_RI_CheckSave = 743;          //铁越验收保存
    public static final int GC_SESSION_TYPE_RIDATA_RI_ClosePal = 744;          //铁越验收封板
    public static final int GC_SESSION_TYPE_RIDATA_RI_GetCheckInfo = 745;          //铁越获取已验收的信息
    
    
    public static final int GC_SESSION_TYPE_RIDATA_End = 799;        //返配结束
    
    
    public static final int GC_SESSION_TYPE_CH_BEGIN = 800;        //盘点开始
    public static final int GC_SESSION_TYPE_CH_GetQualityDate = 801;        //盘点获取品质
    public static final int GC_SESSION_TYPE_CH_CheckSerialNo = 802;        //扫描流水号
    public static final int GC_SESSION_TYPE_CH_CheckCellNo = 803;        //验证储位
    public static final int GC_SESSION_TYPE_CH_GetCheckDateByBarcode = 804;        //扫描商品条码
    public static final int GC_SESSION_TYPE_CH_CheckSave = 805;        //盘点保存
    public static final int GC_SESSION_TYPE_CH_CheckReceipt = 806;        //盘点确认
    public static final int GC_SESSION_TYPE_CH_GetPackingInfoByBarcode = 807;        //扫描商品条码获取包装信息
    public static final int GC_SESSION_TYPE_CH_InputBarcode = 808;        //扫描条码
    public static final int GC_SESSION_TYPE_CH_GetPackingInfoByArticleNo = 809;        //根据商品编码获取包装信息
    public static final int GC_SESSION_TYPE_CH_StockContentQuery = 810;        //库存查询
    
    /**
     * 电子标签储位查询
     */
    public static final int GC_SESSION_TYPE_DPSCELL_SCANLABEL = 811;           //扫描标签获取商品
    public static final int GC_SESSION_TYPE_DPSCELL_DPSCELLSEL = 812;          //获取商品电子标签分播储位
    public static final int GC_SESSION_TYPE_DPSCELL_GETDPSCELLINFO = 813;      //获取商品分播信息

    public static final int GC_SESSION_TYPE_CH_GetProductDateByArticleNO = 814; //根据商品编码获取最老生产日期  add by sunl 2016年8月4日
    
    public static final int GC_SESSION_TYPE_CH_END = 899;          //盘点结束
    
    public static final int GC_SESSION_TYPE_HM_BEGIN = 900;        //移库开始
    public static final int GC_SESSION_TYPE_HM_ScanLabelNo = 901;//移库扫描标签
    public static final int GC_SESSION_TYPE_HM_OutStock = 902;//移库下架
    public static final int GC_SESSION_TYPE_HM_InStock = 903;//移库上架
    public static final int GC_SESSION_TYPE_HM_OutCellNo = 904;//即时移库扫描储位
    public static final int GC_SESSION_TYPE_HM_Barcode = 905;//即时移库扫描条码
    public static final int GC_SESSION_TYPE_HM_InCellNo = 906;//即时移库扫描上架储位
    public static final int GC_SESSION_TYPE_HM_SaveMoveCell = 907;//即时移库保存
    public static final int GC_SESSION_TYPE_HM_RIGetDCellNo = 908;//返配标签移库获取建议储位
    public static final int GC_SESSION_TYPE_HM_RISaveMoveCell = 909;//返配标签移库保存
    public static final int GC_SESSION_TYPE_HM_GetDCellNo = 910;  //即使移库获取目的储位号
    public static final int GC_SESSION_TYPE_HM_GetLabelInfo = 911;//过季转应季标签整理获取标签信息
    public static final int GC_SESSION_TYPE_HM_ArticleArrange = 912;//过季转应季标签整理商品整理
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_GetStockInfo = 913;//库存录标签获取库存信息
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_ScanSave = 914;//库存录标签扫描保存
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_CloseBox = 915;//库存录标签封箱
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_LabelNoValidate = 916;//库存录标签校验标签
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_DockValidate = 917;//库存录标签校验码头
    public static final int GC_SESSION_TYPE_HM_StockSetLabel_CellNoValidate = 918;//库存录标签校验储位
    
    public static final int GC_SESSION_TYPE_HM_END = 999; 		//移库结束
    
    /***
     * 退厂
     */
    public static final int GC_Session_Type_RO_Begin = 1100; 				//退厂开始
    
    public static final int GC_SESSION_TYPE_RO_GetOutStockDateInfo = 1101; 	//退厂获取拣货信息
    public static final int GC_SESSION_TYPE_RO_GetArticleInfo = 1102; 		//退厂获取扫码商品信息
    public static final int GC_SESSION_TYPE_RO_PickReceipt = 1103; 			//退厂下架回单
    
    public static final int GC_Session_Type_RO_End = 1199; 					//退厂结束
    /**
     * 基础信息采集
     */
    public static final int GC_SESSION_TYPE_BM_BEGIN = 1400;//开始
    public static final int GC_SESSION_TYPE_BM_ArticleQpaletteBarcodeInput = 1401;//堆叠采集条码扫描
    public static final int GC_SESSION_TYPE_BM_GetPackingInfoByArticleNo = 1402;//堆叠采集>>获取商品包装
    public static final int GC_SESSION_TYPE_BM_UpdateArticleQpalette = 1403;//堆叠采集>>更新商品堆叠 
    public static final int GC_SESSION_TYPE_BM_PickCellBarcodeInput = 1404;//储位采集》条码扫描
    public static final int GC_SESSION_TYPE_BM_UpdatePickCell = 1405;//储位采集>>更新   
    public static final int GC_SESSION_TYPE_BM_GetPackingArticleQpalette=1406;//堆叠采集》扫码商品条码/助记码/业主编码/编码
    public static final int GC_SESSION_TYPE_BM_GetPackingArticleQpalettes=1407;//新增商品包装/条码
    public static final int GC_SESSION_TYPE_BM_GetPackingArticleNOQpalettes=1408;//堆叠采集》扫码商品编码
    public static final int GC_SESSION_TYPE_BM_GetArticlePackingInfo=1409;//拣货位采集-获取商品包装，单位，规格，委托业主，商品名称 huangb 20160525
    public static final int GC_SESSION_TYPE_BM_getArticleCellInfo=1410;//拣货位采集-获取商品拣货位信息 huangb 20160525
    public static final int GC_SESSION_TYPE_BM_getCheckArticleCell=1411;//拣货位采集-判断商品储位的合法性 huangb 20160526
    public static final int GC_SESSION_TYPE_BM_tscDeleteArticleCell=1412;//拣货位采集-删除当前储位与原有商品的对应关系 huangb 20160526
    public static final int GC_SESSION_TYPE_BM_tscSaveArticleCell=1413;//拣货位采集-保存当前储位与商品的对应关系 huangb 20160526
   
    public static final int GC_SESSION_TYPE_BM_SelectArticlePackingInfo = 1420;//获取商品的所有包装数量及单位
    
    
    public static final int GC_Session_Type_Get_All_DpsAddr = 2001;   //获取所有标签地址
    public static final int GC_Session_Type_Verify_User = 2002;       //校验用户
    public static final int GC_Session_Type_RISUP_Get_Divide_Detail = 2101;  //分播物流箱数据
    public static final int GC_Session_Type_RISUP_Save_Divide_Data = 2102;  //分播数据保存
    public static final int GC_Session_Type_RISUP_Cut_BOX = 2103;  //封箱
    
    
    /***
     * 出货分播
     */
    public static final int GC_Session_Type_ODIVIDE_Begin = 2200;       //出货分播预留开始
    public static final int GC_Session_Type_ODIVIDE_CheckFlag = 2201;  //判断与读取物流箱信息
    public static final int GC_Session_Type_ODIVIDE_GetUserTask = 2203;  //读取用户当前区域任务
    public static final int GC_Session_Type_ODIVIDE_GetBox_UserList = 2204; //读取物流箱已分播用户
    public static final int GC_Session_Type_ODIVIDE_Change_Group = 2205; //更换设备组
    public static final int GC_Session_Type_ODIVIDE_Get_Divide_Detail = 2210;  //分播物流箱数据
    public static final int GC_Session_Type_ODIVIDE_Save_Divide_Data = 2215;  //分播数据保存
    public static final int GC_Session_Type_ODIVIDE_Cut_BOX = 2220;  //封箱
    
    
    public static final int GC_Session_Type_ODIVIDE_End = 2299;      //出货分播预留结束    
    /***
     * DPS拣货
     */
    public static final int GC_Session_Type_DPS_Begin = 2300;  
    public static final int GC_Session_Type_DPS_GET_MIN_AREA = 2331;    //DPS获取当前任务号所在的巷道，区域，最小箱码
    public static final int GC_Session_Type_DPS_GET_ALL_TAST =2332;     //DPS获取当前任务所在巷道的拣货任务。
    public static final int GC_Session_Type_DPS_SAVE_LABELNO = 2333;    //DPS拣货任务回单
    
    public static final int GC_Session_Type_DPS_End = 2399;  
    
    public static final int GC_Session_Type_DPSSystem_Begin = 3000;       //系统预留开始
    public static final int GC_Session_Type_Get_PrintTask = 3001;       //获取打印任务
    public static final int GC_Session_Type_Update_PrintTask = 3002;    //更新打印任务
    public static final int GC_Session_Type_Get_ReportInfo = 3010;    //获取报表信息
    public static final int GC_Session_Type_Get_FieldInfo = 3090;    //获取字段信息表
    public static final int GC_Session_Type_DPSSystem_End = 3099;       //系统预留结束
   
   
    
}
