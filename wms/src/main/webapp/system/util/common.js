function commonOpenNewOrUpdateView(view,viewid){
	var main = Ext.getCmp("content-panel");
	var panel = main.getComponent(viewid);
	if(!panel){
		panel = Ext.create(view,{
			closable:true,
			closeAction:'destory', 
			autoDestory:true
		});
		openTab(panel, viewid);
		setToolbarAuth(viewid);
	}else{
		main.setActiveTab(viewid);
	}
}

function commonCheckMster(formid){
	var form=Ext.getCmp(formid).items;
	for(i=0;i<form.length;i++)
	{
		for(j=0;j<form.items[i].items.length;j++)
		{
			if(form.items[i].items.items[j].beforeLabelTextTpl===required)
			{
				if(Ext.isEmpty(form.items[i].items.items[j].getValue()))
				{
					Ext.example.msg('提示','请输入'+form.items[i].items.items[j].fieldLabel+'！');				
					return false;
				}
			}
		}
	}
	return true;
}
//检查组件下控件必录项是否已录入
function commonCheckIsInputAll(id){
	var constructor=Ext.ComponentQuery.query('#'+id+' field');
	for(i=0;i<constructor.length;i++)
	{
		if(constructor[i].beforeLabelTextTpl===required){
			if(Ext.isEmpty(constructor[i].value))
			{
				Ext.example.msg('提示','请输入'+constructor[i].fieldLabel+'！');		
				constructor[i].focus();
				return false;
			}
		}
	}
	return true;
}

function commonCheckdetailgrid(gridid,beginindex,endindex){
	var grid=Ext.getCmp(gridid);
	for ( var i =beginindex; i <=endindex; i++) {
		var record = grid.getStore().getAt(i);
		for(var j=0;j<grid.columns.length;j++)
		{
			if(grid.columns[j].cls==="notnull")
			{
				if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[j].dataIndex)))
				{
					Ext.example.msg('提示','请输入'+grid.columns[j].text+'！');
					return false;
				}
			}
		}					
	}
	return true;
}


function commonSetMsterReadOnly(formid,flag){
	var form=Ext.getCmp(formid).items;
	for(i=0;i<form.length;i++)
	{
		for(j=0;j<form.items[i].items.length;j++)
		{
			if(form.items[i].items.items[j].xtype=="fieldset")
			{
				for(m=0;m<form.items[i].items.items[j].items.length;m++)
				{
					for(n=0;n<form.items[i].items.items[j].items.items[m].items.length;n++)
					{
						form.items[i].items.items[j].items.items[m].items.items[n].setReadOnly(flag);
					}
				}
			}else{
				form.items[i].items.items[j].setReadOnly(flag);
			}
		}
	}	
}

function commonSetMsterReadOnlyByArray(arr,flag){
	for(i=0;i<arr.length;i++)
	{
		Ext.getCmp(arr[i]).setReadOnly(flag);				
	}	
}

//设置控件是否只读
function commonSetFieldReadOnly(id,flag){
	var constructor=Ext.ComponentQuery.query('#'+id+' field');
	for(i=0;i<constructor.length;i++)
	{
		constructor[i].setReadOnly(flag);			
	}
}

function commonSetButtonReadOnly(toolbarid,arrdetailToolbarid,flag){
	if(flag){
		if(typeof(Ext.getCmp(toolbarid))!="undefined"){
			Ext.getCmp(toolbarid).items.items[3].enable(true);
			Ext.getCmp(toolbarid).items.items[4].enable(true);
			Ext.getCmp(toolbarid).items.items[5].enable(true);	
		}

		for(j=0;j<arrdetailToolbarid.length;j++){
			if(typeof(Ext.getCmp(arrdetailToolbarid[j]))!="undefined"){
				for(var i=0;i<Ext.getCmp(arrdetailToolbarid[j]).items.length;i++)
				{
					Ext.getCmp(arrdetailToolbarid[j]).items.items[i].enable(true);	
				}
			}
		}
	}else{
		if(typeof(Ext.getCmp(toolbarid))!="undefined"){
			Ext.getCmp(toolbarid).items.items[3].disable(true);
			Ext.getCmp(toolbarid).items.items[4].disable(true);
			Ext.getCmp(toolbarid).items.items[5].disable(true);	
		}

		for(j=0;j<arrdetailToolbarid.length;j++){
			if(typeof(Ext.getCmp(arrdetailToolbarid[j]))!="undefined"){
				for(var i=0;i<Ext.getCmp(arrdetailToolbarid[j]).items.length;i++)
				{
					Ext.getCmp(arrdetailToolbarid[j]).items.items[i].disable(true);
				}
			}
		}
	}
}

function setToolbarAuth(id)
{
	var toolbarid='toolbar'+id.substring(0,id.length-2);
	var authValue=0;
	if(typeof(Ext.getCmp('listUiTbar'+id.substring(0,id.length-2)))!="undefined" 
		&& typeof(Ext.getCmp('listUiTbar'+id.substring(0,id.length-2)))!="commMenuWidget3"){
		var authValue=Ext.getCmp('listUiTbar'+id.substring(0,id.length-2)).items.items[2].getValue();	
	}
	
	if(typeof(Ext.getCmp(toolbarid))!="undefined" && typeof(Ext.getCmp(toolbarid))!="commMenuWidget"){
		if((authValue&2)==0){
			Ext.getCmp(toolbarid).items.items[3].setVisible(false);
		}else{
			Ext.getCmp(toolbarid).items.items[3].setVisible(true);
		}
		
		if((authValue&4)==0){
			Ext.getCmp(toolbarid).items.items[2].setVisible(false);
		}else{
			Ext.getCmp(toolbarid).items.items[2].setVisible(true);
		}
		
		if((authValue&8)==0){
			Ext.getCmp(toolbarid).items.items[4].setVisible(false);
		}else{
			Ext.getCmp(toolbarid).items.items[4].setVisible(true);
		}
		
		if((authValue&16)==0){
			Ext.getCmp(toolbarid).items.items[6].setVisible(false);
		}else{
			Ext.getCmp(toolbarid).items.items[6].setVisible(true);
		}

		if((authValue&32)==0){
			Ext.getCmp(toolbarid).items.items[5].setVisible(false);
		}else{
			Ext.getCmp(toolbarid).items.items[5].setVisible(true);
		}
	}
}

function addCommMenu5(id){
	if(typeof(Ext.getCmp(id))!="undefined")
	{
		if(Ext.getCmp(id).xtype=='commMenuWidget5')
		{
			Ext.getCmp(id).items.items[1].setVisible(false);
			Ext.getCmp(id).items.items[2].setVisible(false);
			Ext.getCmp(id).items.items[3].setVisible(true);
			Ext.getCmp(id).items.items[4].setVisible(true);
			Ext.getCmp(id).items.items[5].setVisible(true);
		}
	}
}

function updateCommMenu5(id){
	if(typeof(Ext.getCmp(id))!="undefined")
	{
		if(Ext.getCmp(id).xtype=='commMenuWidget5')
		{
			Ext.getCmp(id).items.items[1].setVisible(true);
			Ext.getCmp(id).items.items[2].setVisible(true);
			Ext.getCmp(id).items.items[3].setVisible(false);
			Ext.getCmp(id).items.items[4].setVisible(true);
			Ext.getCmp(id).items.items[5].setVisible(true);
		}
	}
}

function browseCommMenu5(id){
	if(typeof(Ext.getCmp(id))!="undefined")
	{
		if(Ext.getCmp(id).xtype=='commMenuWidget5')
		{
			Ext.getCmp(id).items.items[1].setVisible(true);
			Ext.getCmp(id).items.items[2].setVisible(true);
			Ext.getCmp(id).items.items[5].setVisible(true);
			Ext.getCmp(id).items.items[3].setVisible(false);
			Ext.getCmp(id).items.items[4].setVisible(false);
		}
	}
}

//历史单据界面，按钮显示控制
function historyCommMenu4(id){
	if(typeof(Ext.getCmp(id))!="undefined")
	{
		if(Ext.getCmp(id).xtype=='commMenuWidget4')
		{   
			Ext.getCmp(id).items.items[0].disable(true);
			Ext.getCmp(id).items.items[1].disable(true);
			Ext.getCmp(id).items.items[4].disable(true);
			Ext.getCmp(id).items.items[2].disable(true);
			Ext.getCmp(id).items.items[3].disable(true);
			Ext.getCmp(id).items.items[5].disable(true);
			Ext.getCmp(id).items.items[6].enable(true);
			Ext.getCmp(id).items.items[7].enable(true);
			Ext.getCmp(id).items.items[8].enable(true);
			Ext.getCmp(id).items.items[9].enable(true);
		}
	}
}

/**
 * 设置form是否只读
 * @param {} formid 
 * @param {} flag
 */
function commonSetFormReadOnly(formid,flag){
	var form=Ext.getCmp(formid).items;
	for(i=0;i<form.length;i++)
	{
		Ext.getCmp(formid).items.items[i].setReadOnly(flag);
	}	
}
/**
 * 设置基础资料弹出界面上条记录、下条记录按钮
 * @param {} CommMenu5id
 * @param {} gridid
 * @param {} rowindex
 */
function commonSetCommMenu5PrevOrNext(CommMenu5id,gridid,rowindex){
	if(rowindex%appConfig.getPageSize()==0)
	{
		Ext.getCmp(CommMenu5id).items.items[1].disable(true);	
	}else{
		Ext.getCmp(CommMenu5id).items.items[1].enable(true);
	}
	if(rowindex%appConfig.getPageSize()==Ext.getCmp(gridid).getStore().getCount()-1)
	{		
		Ext.getCmp(CommMenu5id).items.items[2].disable(true);
	}else{		
		Ext.getCmp(CommMenu5id).items.items[2].enable(true);
	}
}


//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(){     
	var rx = /INPUT|TEXTAREA/i;
	var rxT = /RADIO|CHECKBOX|SUBMIT/i;
	$(document).bind("keydown keypress", function (e) 
	{
        var preventKeyPress;
        if (e.keyCode == 8) {
            var d = e.srcElement || e.target;
            if (rx.test(e.target.tagName)) {
                var preventPressBasedOnType = false;
                if (d.attributes["type"]) {
                    preventPressBasedOnType = rxT.test(d.attributes["type"].value);
                }
                preventKeyPress = d.readOnly || d.disabled || preventPressBasedOnType;
            } else {preventKeyPress = true;}
        } else { preventKeyPress = false; }
        if (preventKeyPress) e.preventDefault();
	});
};

function commonEditButton(id,button){
	if(button=='dbclick'){
		Ext.getCmp(id).items.items[2].enable(true);
		Ext.getCmp(id).items.items[3].enable(true);
		Ext.getCmp(id).items.items[4].disable(true);
		Ext.getCmp(id).items.items[5].disable(true);
		Ext.getCmp(id).items.items[6].enable(true);
		Ext.getCmp(id).items.items[7].enable(true);
	}else if(button=='add'){
		Ext.getCmp(id).items.items[0].disable(true);
		Ext.getCmp(id).items.items[1].disable(true);
		Ext.getCmp(id).items.items[2].disable(true);
		Ext.getCmp(id).items.items[3].disable(true);
		Ext.getCmp(id).items.items[4].enable(true);
		Ext.getCmp(id).items.items[5].enable(true);	
		Ext.getCmp(id).items.items[6].disable(true);
		Ext.getCmp(id).items.items[7].disable(true);
	}else if(button=='edit'){
		Ext.getCmp(id).items.items[0].enable(true);
		Ext.getCmp(id).items.items[1].enable(true);
		Ext.getCmp(id).items.items[2].disable(true);
		Ext.getCmp(id).items.items[3].disable(true);
		Ext.getCmp(id).items.items[4].enable(true);
		Ext.getCmp(id).items.items[5].enable(true);
		Ext.getCmp(id).items.items[6].disable(true);
		Ext.getCmp(id).items.items[7].enable(true);
	}else if(button=='undo'){
		Ext.getCmp(id).items.items[2].enable(true);
		Ext.getCmp(id).items.items[3].disable(true);
		Ext.getCmp(id).items.items[4].disable(true);
		Ext.getCmp(id).items.items[5].disable(true);
		Ext.getCmp(id).items.items[6].enable(true);
		Ext.getCmp(id).items.items[7].disable(true);
	}else if(button=='save'){
		Ext.getCmp(id).items.items[2].enable(true);
		Ext.getCmp(id).items.items[3].enable(true);
		Ext.getCmp(id).items.items[4].disable(true);
		Ext.getCmp(id).items.items[5].disable(true);
		Ext.getCmp(id).items.items[6].enable(true);
		Ext.getCmp(id).items.items[7].enable(true);
	}else if(button=='send'){
		Ext.getCmp(id).items.items[0].enable(true);
		Ext.getCmp(id).items.items[2].enable(true);
		Ext.getCmp(id).items.items[3].enable(true);
		Ext.getCmp(id).items.items[4].disable(true);
		Ext.getCmp(id).items.items[5].disable(true);
		Ext.getCmp(id).items.items[6].enable(true);
		Ext.getCmp(id).items.items[7].enable(true);
	}
};

function commonMenu4Button(id,button){
	if(button=='add'){
		Ext.getCmp(id).items.items[0].setDisabled(true);
		Ext.getCmp(id).items.items[1].setDisabled(true);
		Ext.getCmp(id).items.items[3].setDisabled(false);
		Ext.getCmp(id).items.items[4].setDisabled(false);
		Ext.getCmp(id).items.items[5].setDisabled(true);
		Ext.getCmp(id).items.items[6].setDisabled(true);
		Ext.getCmp(id).items.items[7].setDisabled(true);
		Ext.getCmp(id).items.items[8].setDisabled(true);
		Ext.getCmp(id).items.items[9].setDisabled(true);
	}else if(button=='edit'){
		Ext.getCmp(id).items.items[1].setDisabled(true);
		Ext.getCmp(id).items.items[2].setDisabled(true);
		Ext.getCmp(id).items.items[3].setDisabled(false);
		Ext.getCmp(id).items.items[4].setDisabled(false);
		Ext.getCmp(id).items.items[5].setDisabled(true);
		Ext.getCmp(id).items.items[6].setDisabled(true);
		Ext.getCmp(id).items.items[7].setDisabled(true);
		Ext.getCmp(id).items.items[8].setDisabled(true);
		Ext.getCmp(id).items.items[9].setDisabled(true);
	}else if(button=='undo'){
		Ext.getCmp(id).items.items[0].setDisabled(false);
		Ext.getCmp(id).items.items[1].setDisabled(false);
		Ext.getCmp(id).items.items[2].setDisabled(false);
		Ext.getCmp(id).items.items[3].setDisabled(true);
		Ext.getCmp(id).items.items[4].setDisabled(true);
		Ext.getCmp(id).items.items[5].setDisabled(false);
		Ext.getCmp(id).items.items[6].setDisabled(false);
		Ext.getCmp(id).items.items[7].setDisabled(false);
		Ext.getCmp(id).items.items[8].setDisabled(false);
		Ext.getCmp(id).items.items[9].setDisabled(false);
	}else if(button=='save'){
		Ext.getCmp(id).items.items[0].setDisabled(false);
		Ext.getCmp(id).items.items[1].setDisabled(false);
		Ext.getCmp(id).items.items[2].setDisabled(false);
		Ext.getCmp(id).items.items[3].setDisabled(true);
		Ext.getCmp(id).items.items[4].setDisabled(true);
		Ext.getCmp(id).items.items[5].setDisabled(false);
		Ext.getCmp(id).items.items[6].setDisabled(false);
		Ext.getCmp(id).items.items[7].setDisabled(false);
		Ext.getCmp(id).items.items[8].setDisabled(false);
		Ext.getCmp(id).items.items[9].setDisabled(false);
	}
};

/**
 * datagrid开启编辑跳转模式
 * @param gridId		表格ID
 * @param rowIndex		行索引
 * @param toIndex		跳转到cell index
 */
function startGridCell(gridId,rowIndex,toIndex){
	Ext.getCmp(gridId).editingPlugin.startEdit(rowIndex,toIndex);
}

function  commonEnterGridStartEdit(editor,e,eOpts){
	for(var i=e.colIdx+1;i<editor.grid.columns.length;i++){
		if(editor.grid.columns[i].cls=="notnull"){
			editor.grid.editingPlugin.startEdit(e.record.index,i);
			break; 
		}
		if(i==editor.grid.columns.length-1){
			if(e.record.index==e.record.store.totalCount){
				editor.grid.editingPlugin.startEdit(0,1);
			}else{
				editor.grid.editingPlugin.startEdit(e.record.index+1,1);
			}
		}
	}
}

/**
 * 通用调用
 * 如有新增按钮 则 新增函数在控制器里面统一改为 detailAdd
 * @param grid			表格ID
 * @param isAdd			是否有新增函数	false否 true有
 * @param controllerId	控制器ID  demo:(cms.controller.fcdata.fcdata_ReceiptController)这个控制器的ID是fcdata.fcdata_ReceiptController 需要把目录也加上
 * @param enKey			键盘按键
 */
function commEnterGridStatEdit(grid,isAdd,controllerId,enKey){
	//定义调用函数
	function s(){
		//获取选中的单元格
		var pos = grid.getSelectionModel().getCurrentPosition();
		if (enKey == 13){ 
	        grid.plugins[0].tabPressed = true ;
	        for(var i=pos.column;i<grid.columns.length;i++){
	        	//判断是否为最后一个可录入的
	        	if(!getAfterCellIsNull(grid,pos)){
		        	if(pos.row+1 == grid.getStore().getCount() && isAdd){
						_myAppGlobal.getController(controllerId).detailAdd();
		        	}else if(pos.row+1 < grid.getStore().getCount()){
		        		var firstCellIndex = getFirstCellIsNull(grid);
		        		grid.editingPlugin.startEdit(pos.row+1,firstCellIndex);
		        	}
		        	break;
		        }
	        	//进入下一个可录入的单元格
        		if(!grid.columns[i+1].hidden){
	        		if(grid.columns[i+1].cls=="notnull"||grid.columns[i+1].cls=="allownull"){
		        		grid.editingPlugin.startEdit(pos.row,i+1);
						break; 
					}
	        	}
	        }
	    }
	}
	//因为先会进入specialkey所以线程休眠10毫秒
	setTimeout(s,10);
}

/**
 * 获取单元格之后是否有需要录入的
 * @param grid
 * @param pos
 * @returns {Boolean}
 */
function getAfterCellIsNull(grid,pos){
	for(var i=pos.column+1;i<grid.columns.length;i++){
		if(grid.columns[i].cls=="notnull" || grid.columns[i].cls=="allownull"){
			return true;
		}
	}
}

/**
 * 获取第一个单元格能输入的
 * @param grid
 * @returns {Number}
 */
function getFirstCellIsNull(grid){
	for(var i=1;i<grid.columns.length;i++){
		if(grid.columns[i].cls=="notnull" || grid.columns[i].cls=="allownull"){
			return i;
		}
	}
}

/**
 * 显示与设置工作站
 */
function setWorkSpace(){
		var setWorkWin = Ext.create('Ext.window.Window', {
		    title: '请选择工作站',
	    	modal:true,
		    closable:true,
		    height: 100,
		    width: 350,
		    layout:'hbox',
		    items: [{
				 	xtype:'combo',
				 	id:'workspaceCombo',
					fieldLabel : '工作站',//$i18n.sex,
					labelAlign:'right',
					labelWidth : 90,
					forceSelection : true,
					margin : '20 5 5 5',
				    displayField: 'workstationName',
				    valueField: 'workstationNo',
				    store:Ext.create('Ext.data.Store', {
				        fields: ['workstationNo',
				         'warehouseNo',
				         'workstationName'],
					     proxy: {
					         type: 'ajax',
					         url: 'bdef_DefWorkstationAction_getWorkstationCombo.action',
					         reader: {
					             type: 'json',
					             root: 'rootList'
					         }
					     }
				    }),
			    	listConfig : {
						loadingText : '查询中...',
						emptyText : '没有找到相应的数据..',
						getInnerTpl : function() {
					    	return '[{workstationNo}]{workstationName}';
						}
					},
				    beforeLabelTextTpl : required
		    },{
		    	xtype: 'button',
		    	margin : '20 5 5 5',
				text : '确定',
				 handler: function() {
					 var workspaceComboValue = Ext.getCmp('workspaceCombo').getValue();
					 var workspaceComboRwaValue = Ext.getCmp('workspaceCombo').getRawValue();
			        if(workspaceComboValue==null||workspaceComboValue==""){
			        	Ext.example.msg('提示','请选择工作站！');
			        }/*else if(workspaceComboValue==workspaceComboRwaValue&&(workspaceComboValue!=null||workspaceComboRwaValue!=""))
			        {
			        	setWorkWin.close();
			        }*/else{
			        	Ext.Ajax.request({
			        		url:'bdef_DefWorkstationAction_setUserWorkSpaceNo.action',
			        		params:{
			        			workspaceNo:workspaceComboValue,
			        			workSpaceName:workspaceComboRwaValue
			        		},
			        		success:function(response){
			        			var data = Ext.decode(response.responseText);
			        			if(data.isSucc){
			        				Ext.example.msg("提示",data.msg);
			    					workSpaceNo=workspaceComboValue;
			    					workSpaceName=workspaceComboRwaValue;
			    					setWorkSpaceTips(workspaceComboRwaValue);
			    					setWorkWin.close();
			    				}else{
			    					Ext.example.msg($i18n.prompt,data.msg);
			    				}
			        		}
			        	});
				        
			        }
			    }
		    }]
		});
	if(workSpaceName!=""&&workSpaceNo!=""){
		Ext.getCmp('workspaceCombo').setValue(workSpaceNo);
		Ext.getCmp('workspaceCombo').setRawValue(workSpaceName);
	}
	setWorkWin.show();
}

/**
 * 选定combo后 设置工作站提示
 * @param name
 */
function setWorkSpaceTips(name){
	document.getElementById("workSpaceTips").innerHTML="("+name+")";
}
 
/**
 * 根据session 设置工作站提示
 */
function setWorkNameTips(){
	if(workSpaceName!=""){
		setWorkSpaceTips(workSpaceName);
	}else{
		setWorkSpaceTips("暂未设置");
	}
}



if (!Object.keys) Object.keys = function(o)
{
	  if (o !== Object(o))
	    throw new TypeError('Object.keys called on a non-object');
	  var k=[],p;
	  for (p in o) if (Object.prototype.hasOwnProperty.call(o,p)) k.push(p);
	  return k;
};

/**
 * 导出Excel
 * @param gridId		表格ID
 */
function commExport(gridId){
	Ext.Msg.confirm('提示','确定要导出数据吗?',function(button){
		if(button === 'yes'){
			debugger;
			var queryStr = "";
			var proxy =  Ext.getCmp(gridId).getStore().proxy;
			var url = proxy.url;
			if(proxy.url.indexOf("./")!=-1){
				url = proxy.url.substring(2,proxy.url.length);
			}
			var params = proxy.extraParams;
			var paramsStr = "";
			var values = Object.keys(params);
			for (var i = 0; i < values.length; i++) {
				if(!Ext.isEmpty(params[values[i]]))
				{
					paramsStr += values[i]+"="+params[values[i]]+"&";
				}
			}			
			window.open(url+"?"+paramsStr+"requestFlag=2&page=1&start=0&limit=20");
		}
	});
}

function ReportInfoBo(){
	this.sourceNo="";
	this.reportId="";
};

/**
 * 通用打印程序
 * @param sourceNo
 * @param reportId
 * @param args
 */
function commPrint( sourceNo, reportId , args, callback){
	var returnFlag = false;
	if(workSpaceNo==null||workSpaceNo=="")
	{
		Ext.example.msg("提示","未设置工作站不能打印,请先设置您的工作站.");
	}else
	{
		try{
			Ext.Msg.confirm('提示','确定要打印数据吗?',function(button){
				if(button=="yes" )
				{
					proMsgWin = commonMegShow("执行打印中...");
					var array = new Array();
					if(args==undefined||args==null && Ext.String.trim(sourceNo)!=""&&Ext.String.trim(reportId)!=""){
						var bo = new ReportInfoBo();
						bo.sourceNo=sourceNo;
						bo.reportId=reportId;
						array.push(bo);
					}else if(args.length>0){
						array = args;
					}else{
						Ext.example.msg("提示","调用调用接口参数不完整,请联系管理员.");
					}
					Ext.Ajax.request({
						url:'printerAction_Batch.action',
						async: false,  
						params:{
							printStr:Ext.encode(array)
						},
						success:function(response){
							var data = Ext.decode(response.responseText);
							Ext.example.msg("提示",data.msg);
							proMsgWin.close();
							if(data.isSucc)
							{
								returnFlag =  true;
								if(callback!=null){callback();}
							}
						}
					});
				}
			});
		}catch(error){
			return returnFlag;
		}
	}
	return returnFlag;
}

/**
 * 通用打印程序
 * @param args
 * @param htyFlag 
 */
function commPrint(args,htyFlag,labelNoList,printNum, callback){

	var returnFlag = false;
	if(workSpaceNo==null||workSpaceNo=="")
	{
		Ext.example.msg("提示","未设置工作站不能打印,请先设置您的工作站.");
	}else
	{
		try{
			Ext.Msg.confirm('提示','确定要打印数据吗?',function(button){
				if(button=="yes" )
				{
					proMsgWin = commonMegShow("执行打印中...");
					var array = new Array();
					if(args==undefined||args==null && Ext.String.trim(sourceNo)!=""&&Ext.String.trim(reportId)!=""){
						var bo = new ReportInfoBo();
						bo.sourceNo=sourceNo;
						bo.reportId=reportId;
						array.push(bo);
					}else if(args.length>0){
						array = args;
					}else{
						Ext.example.msg("提示","调用调用接口参数不完整,请联系管理员.");
					}
					Ext.Ajax.request({
						url:'printerAction_Batch.action',
						async: false,  
						params:{
							printStr:Ext.encode(array),
							htyFlag:htyFlag,
							labelNoList:labelNoList,
							printNum:printNum
						},
						success:function(response){
							var data = Ext.decode(response.responseText);
							Ext.example.msg("提示",data.msg);
							proMsgWin.close();
							if(data.isSucc)
							{
								returnFlag =  true;
								if(callback!=null){callback();}
							}
						}
					});
				}
			});
		}catch(error){
			return returnFlag;
		}
	}
	return returnFlag;
}

/**
 * 
 * @param {} element
 * @return {Boolean}
 */
function elementIs(array,element) { 
	for (var i = 0; i < array.length; i++) {   
		if (array[i].articleNo === element.get('articleNo') && array[i].packingQty === element.get('packingQty')) {   
			return i;   
		}   
	}   
	return -1;   
}   

function commonMegShow(msg){
   return Ext.MessageBox.show({
           msg: msg,
           progressText: '...',
           width:300,
           wait:true,
           
           icon:'ext-mb-download', //custom class in msg-box.html
           animateTarget: Ext.getBody()
       	});
}

/**
 * 修改密码
 */
function updatePwd(){
	Ext.create('Ext.window.Window', {
	    title: '修改密码',
    	modal:true,
	    closable:true,
	    height: 160,
	    width: 350,
	    layout:'fit',
	    items: [{
			xtype:'form',
			frame : true,
			defaults : {
				xtype : 'textfield',
			  	inputType: 'password',
				margin : '5 5 5 5',
				labelAlign:'right',
				allowBlank: false,
				labelWidth : 80,
			 	msgTarget: 'side',//under
				width : 320
			},
			items:[{
	                fieldLabel: '旧密码',
	                name: 'oldpass',
	                id: 'oldpass',
					beforeLabelTextTpl : required
	            },{
	                fieldLabel: '新密码',
	                name: 'newpass',
	                id: 'newpass',
	                //regex: /^([a-zA-Z0-9]{6,})$/i,
      				regexText: '密码必须同时包含字母和数字,且最少有6位',
					beforeLabelTextTpl : required
	            },{
	                fieldLabel: '确认新密码',
	                name: 'cfrmpass',
	                id:'cfrmpass',
	                validator: function(value){
						var pw = this.previousSibling().value;
						if(value != pw){
							return '两次输入的密码不一致';
						}else{
							return true;
						}
					},
					beforeLabelTextTpl : required
	            },{
			    	xtype: 'button',
					text : '确定',
					width:80,
					margin : '5 5 5 90',
				 	handler: function() {
					 	if(this.ownerCt.form.isValid())
					 	{
					 		updatePassWindow=this.ownerCt.ownerCt;
					 		Ext.Ajax.request({
				        		url:'authAction_updateWorkerPass.action',
				        		params:{
				        			strOldPass:this.ownerCt.form.getFields().items[0].getValue(),
				        			strNewPass:this.ownerCt.form.getFields().items[1].getValue()
				        		},
				        		success:function(response){
				        			var data = Ext.decode(response.responseText);
				        			if(data.isSucc){
				        				Ext.example.msg("提示",data.msg);					    
				    					updatePassWindow.close();
				    				}else{
				    					Ext.example.msg($i18n.prompt,data.msg);
				    				}
				        		}
				        	});
					 	}
					 }	        
			    }
			]
		}]
	}).show();
}

/**
 * 改变按钮状态
 * @param type		类型：0恢复按钮 1置灰按钮
 * @param btnId		按钮ID
 * @param text		按钮的Text
 * demo
 * disableButtonFunc(1,'printRepairPrintBtnId','处理中');
 * disableButtonFunc(0,'printRepairPrintBtnId','打印');
 */
function disableButtonFunc(type,btnId,text){
	if(type == 1)
	{
		Ext.ComponentQuery.query(btnId)[0].setText(text);
		Ext.ComponentQuery.query(btnId)[0].disable();
	}else if(type == 0)
	{
		Ext.ComponentQuery.query(btnId)[0].setText(text);
		Ext.ComponentQuery.query(btnId)[0].enable();
	}
}

function commonMenu5PrevOrNext(strCommMenu5id,strGridId,intFlag)
{
	var intPageSize=Ext.getCmp(strGridId).getStore().pageSize;
	var intCurSelectIndex=Ext.getCmp(strGridId).getSelectionModel().getSelection()[0].index;
	var intNextSelectIndex=intCurSelectIndex%intPageSize+intFlag;
	Ext.getCmp(strGridId).getSelectionModel().select(intNextSelectIndex);
	
	if(intNextSelectIndex==0)
	{
		Ext.getCmp(strCommMenu5id).items.items[1].disable(true);	
	}else{
		Ext.getCmp(strCommMenu5id).items.items[1].enable(true);
	}
	if(intNextSelectIndex==intPageSize-1
	||(intNextSelectIndex==Ext.getCmp(strGridId).getStore().totalCount-1)
	)
	{		
		Ext.getCmp(strCommMenu5id).items.items[2].disable(true);
	}else{		
		Ext.getCmp(strCommMenu5id).items.items[2].enable(true);
	}
}

/**
 * 获取系统参数
 */
function commonGetSystemParams(
		strOwnerNo,strColName,strGroupNo,strSubGroupNo)
{
	var sysParams='';
	var params=
	{
		strOwnerNo:strOwnerNo,
		strColName:strColName,
		strGroupNo:strGroupNo,
		strSubGroupNo:strSubGroupNo
	};
	Ext.Ajax.request({
		method:'POST',
		url:'getSystemParameterAction_getSystemParameterList.action',
		params:params,
		async:false,
		success:function(response)
		{
			sysParams = Ext.decode(response.responseText);
		}
	});
	return sysParams;
}
/**
 * 获取进货策略  add by huangcx 20160716
 */
function commonGetIdataType(
		strOwnerNo,strImportType,strColName)
{
	var sysParams='';
	var params=
	{
		strOwnerNo:strOwnerNo,
		strImportType:strImportType,
		strColumnName:strColName
	};
	Ext.Ajax.request({
		method:'POST',
		url:'getSystemParameterAction_getSystemIdataTypeStrategy.action',
		params:params,
		async:false,
		success:function(response)
		{
			sysParams = Ext.decode(response.responseText);
		}
	});
	return sysParams;
}
/**
 * 获取界面显示配置hkl//企业和仓别在action取得
 */
function commonGetModuleField(strModuleId,strFieldName)
{
	var sysParams='';
	var params=
	{
			strModuleId:strModuleId,
			strFieldName:strFieldName
	};
	Ext.Ajax.request({
		method:'POST',
		url:'getSystemParameterAction_getGetModuleFieldList.action',
		params:params,
		async:false,
		success:function(response)
		{
			sysParams = Ext.decode(response.responseText);
		}
	});
	return sysParams;
}

/**
 * 分播回单目的容器号校验
 */
function commonCheckLabelNo(strSContainerNo,
       strCustContainerNo,strCustNo,strArticleNo)
{
	var params=
	{
		strSContainerNo:strSContainerNo,
		strCustContainerNo:strCustContainerNo,
		strCustNo:strCustNo,
		strArticleNo:strArticleNo
	};
	Ext.Ajax.request({
		method:'POST',
		url:'checkDivideLabelNoAction_checkDivideLabelNo.action',
		params:params,
		async:false,
		success:function(response)
		{
			var data=Ext.decode(response.responseText);
			if(!data.isSucc)
			{
				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});
}

/**
 * 上架回单储位校验
 */
function commonCellCheck(strOwnerNo,strCellNo)
{
	var params=
	{
		strOwnerNo:strOwnerNo,
		strCellNo:strCellNo
	};
	Ext.Ajax.request({
		method:'POST',
		url:'checkCellAction_checkCell.action',
		params:params,
		async:false,
		success:function(response)
		{
			var data=Ext.decode(response.responseText);
			if(data.isSucc)
			{
				Ext.example.msg($i18n.prompt,data.msg);
			}else
			{
				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});
}

function commonSelectWareHouseNO()
{
	Ext.create('Ext.window.Window', {
    title: '请选择作业仓别',
	modal:true,
    closable:false,
    height: 100,
    width: 350,
    layout:'hbox',
    items: [
    	{
	 		xtype:'combo',
			fieldLabel : '作业仓别',//$i18n.sex,
			labelAlign:'right',
			labelWidth : 90,
			forceSelection : true,
			margin : '20 5 5 5',
		    displayField: 'dropValue',
		    valueField: 'value',
		    store:Ext.create('Ext.data.Store', {
		        fields: ['dropValue',
		         'text',
		         'value'],
			     proxy: {
			         type: 'ajax',
			         url: 'bset_Worker_LocAction_getBset_Worker_LocCombo.action',
			         reader: {
			             type: 'json',
			             root: 'rootList'
			         }
			     }
		    }),
		    beforeLabelTextTpl : required
    },{
    	xtype: 'button',
    	margin : '20 5 5 5',
		text : '确定',
		 handler: function() {
	        if(this.previousSibling().getValue()==null){
	        	Ext.example.msg('提示','请选择作业仓别！');
	        }else{
	        	Ext.Ajax.request({
				method:'POST',
				url:'loginAction_setSelectWarehouseNo.action',
				params:{
					str:this.previousSibling().getValue()
				}
				});
		        document.getElementById('warehouseNo').value=this.previousSibling().getValue();
		        document.getElementById('warehouseName').value=this.previousSibling().getRawValue();
		        document.getElementById('currentWarehouseNo').innerHTML=this.previousSibling().getValue();
		        this.ownerCt.close();
		        //7-13添加       选择仓别后立即加载相应仓别的报表数据
		        Ext.Ajax.request({
					url:'authAction_getHomeData.action',
					async:false,
					success : function(response, options) { 
						dataHome = Ext.decode(response.responseText);
						for(var i=0; i<dataHome.length ;i++){
							Ext.getCmp('chart'+dataHome[i].itemId).getStore().reload();
						}
					}
				});
	        }
	    }
    }]
}).show();
}

function countList(gridId,dataList,position){
	var arrayObj = new Array();
	for (var j=0; j<dataList.length;j++){
		arrayObj[j]=0;
	}
    var gridcount=Ext.getCmp(gridId).getStore().getCount();
    if(gridcount>0){
		for(var i=0; i<gridcount;i++){
			var record=Ext.getCmp(gridId).getStore().getAt(i);
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=arrayObj[j]+parseFloat(record.get(dataList[j])==''?0:record.get(dataList[j]));
			}
		}
		
		for(var i=0; i<gridcount;i++){
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=Math.round(parseFloat(arrayObj[j])*100)/100;
			}
		}
		
		
	}	
    
    var model = new Object(); 
    if(position!=""&&position!=null){
    	model[position]='合计';
    }
    
    for (var i=0; i<dataList.length;i++){
		model[dataList[i]]=arrayObj[i];		
    }
	Ext.getCmp(gridId).getStore().add(model);	
}
//计费管理使用
function countList2(gridId,dataList,position){
	var arrayObj = new Array();
	for (var j=0; j<dataList.length;j++){
		arrayObj[j]=0;
	}
    var gridcount=Ext.getCmp(gridId).getStore().getCount();
    if(gridcount>0){
		for(var i=0; i<gridcount;i++){
			var record=Ext.getCmp(gridId).getStore().getAt(i);
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=arrayObj[j]+parseFloat(record.get(dataList[j]));
			}
		}
		
		for(var i=0; i<gridcount;i++){
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=Math.round(parseFloat(arrayObj[j])*100000)/100000;
			}
		}
		
		
	}	
    
    var model = new Object(); 
    if(position!=""&&position!=null){
    	model[position]='合计';
    }
    
    for (var i=0; i<dataList.length;i++){
		model[dataList[i]]=arrayObj[i];		
    }
	Ext.getCmp(gridId).getStore().add(model);	
}

function countListBySelect(gridId,dataList,position,flag){
	
	var gridcount=Ext.getCmp(gridId).getStore().getCount();	
	var lastDate=Ext.getCmp(gridId).getStore().getAt(gridcount-1);
	
	if(lastDate.get(position)=='合计'){
		Ext.getCmp(gridId).getStore().remove(lastDate)
	}
	
	var arrayObj = new Array();
	for (var j=0; j<dataList.length;j++){
		arrayObj[j]=0;
	}
	
	var grid=Ext.getCmp(gridId).getSelectionModel().getSelection();
	
    if(grid.length>0){
		for(var i=0; i<grid.length;i++){
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=arrayObj[j]+parseFloat(grid[i].get(dataList[j]));
			}
		}
		
		for(var i=0; i<gridcount;i++){
			for (var j=0; j<dataList.length;j++){
				arrayObj[j]=Math.round(parseFloat(arrayObj[j])*100)/100;
			}
		}
	}	
    
    var model = new Object(); 
    if(position!=""&&position!=null){
    	model[position]='合计';
    }
    
    for (var i=0; i<dataList.length;i++){
		model[dataList[i]]=arrayObj[i];		
    }
	Ext.getCmp(gridId).getStore().add(model);	
}