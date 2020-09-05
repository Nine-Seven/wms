/**
 * 模块名称：线路与客户维护
 * 模块编码：1J01
 * 创建：Jun
 */
var rowindex1J01=0;
var saveType1J01=0;
Ext.define('cms.controller.oset.oset_DefLineController',{
	extend:'Ext.app.Controller',
	requires:[
			 'cms.view.oset.oset_DefLineUI',
			 'cms.view.oset.window.oset_DefLineAddorEditWindow'
			 ],
	model:'',
	store:'',
	init:function(){
		this.control({//新增
			'oset_DefLineUI button[name=detailAdd]':{
				click:this.detailAdd
			},//修改
			'oset_DefLineUI button[name=detailEdit]':{
				click:this.detailEdit				
			},//删除
			'oset_DefLineUI button[name=detailDelete]':{
				click:this.detailDelete
			},//浏览
			'oset_DefLineUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//查找
			'oset_DefLineUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'oset_DefLineUI button[name=detailExport]':{
				click:this.detailExport
			},//线路移除客户
			'oset_DefLineUI button[id=left1J01]':{
				click:this.left1J01
			},//线路添加客户
			'oset_DefLineUI button[id=right1J01]':{
				click:this.right1J01
			},//GRID选择
			'oset_DefLineUI grid[id=grid_01_1J01]':{
				selectionchange:this.grid_01_1J01change
			},//上一条记录
			'oset_DefLineAddorEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'oset_DefLineAddorEditWindow button[name=next]':{
				click:this.next
			},//新增前加载
			'oset_DefLineAddorEditWindow button[name=add]':{
				click:this.add
			},//保存
			'oset_DefLineAddorEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'oset_DefLineAddorEditWindow button[name=close]':{
				click:this.close
			},//验证线路代码唯一性
			'oset_DefLineAddorEditWindow form textfield[id=line_no1J01]':{
				blur:this.lineNoBlur
			},
			'oset_DefLineUI combo[id=cmbFormOwnerUI1J01]':{
				change:this.getCustom
			},//上移
			'oset_DefLineUI button[name=prevButton]':{
				click:this.prevButton
			},//下移
			'oset_DefLineUI button[name=nextButton]':{
				click:this.nextButton
			},//选择客户线路关系
			'oset_DefLineUI grid[id=grid_03_1J01]':{
				selectionchange:this.grid_03_1J01Selection,
			}
		});
	},
	
	getCustom:function(){
		var wheresql=
		{
			strQuery:Ext.getCmp('cmbFormOwnerUI1J01').getValue()
		};
		Ext.apply(Ext.getCmp('grid_02_1J01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_1J01').getStore().removeAll();
		Ext.getCmp('grid_02_1J01').getStore().load();
	},
	//控制上下移的选中行
	grid_03_1J01Selection:function(){
		var data = Ext.getCmp('grid_03_1J01').getSelectionModel().getSelection();
		if(data != 0){
			Ext.getCmp('grid_03_1J01_Id').setValue(data[0].get("custNo"));
		}
		
	},
	//上移
	prevButton:function(){
		this.sortSearchD(1);
	},
	
	//下移
	nextButton:function(){
		this.sortSearchD(-1);	
	},
	sortSearchD:function(flag){
		var data = Ext.getCmp('grid_03_1J01').getSelectionModel().getSelection();
		if(data.length!=0){
			var params = 
			{				
				custNo:data[0].get("custNo"),
				lineNo:data[0].get("lineNo"),
				lineSeqNo:data[0].get("lineSeqNo"),
				str:flag
			};
			Ext.Ajax.request({
				method:'POST',
				params:params,
				url:'oset_DefLineAction_updateSearchD',
				success:function(response)
				{
					data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('grid_03_1J01').getStore().load();
					}else{
						Ext.example.msg($i18n_prompt.prompt,data.msg);
					}			
				}
			});				
			
		}else{
			Ext.example.msg($i18n_prompt.prompt,'请选择要移动的数据');
		}
	
	},
	detailAdd:function(){
		Ext.create('cms.view.oset.window.oset_DefLineAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		bindEnterSkip($('#form_01_1J01'));//调用键盘处理方法
		Ext.getCmp('line_no1J01').focus(false, 10);
		addDefLine1J01();
		addCommMenu5('oset_DefLine1J01');
		saveType1J01=0;
	},
	
	detailEdit:function(){
		var data=Ext.getCmp('grid_01_1J01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg('提示','请选择一条数据修改');
		}else{
			Ext.create('cms.view.oset.window.oset_DefLineAddorEditWindow',{
				title:$i18n.titleupdate//修改
			}).show();
			Ext.getCmp('warehouse1J01').disable();
			Ext.getCmp('line_no1J01').disable();
			rowindex1J01=data[0].index;
			loadDefLine1J01(rowindex1J01);
			commonSetCommMenu5PrevOrNext('oset_DefLine1J01','grid_01_1J01',rowindex1J01);
			updateCommMenu5('oset_DefLine1J01');
			saveType1J01=1;
		}
	},
	
	detailDelete:function(){
		var data=Ext.getCmp('grid_01_1J01').getSelectionModel().getSelection();
		if(data.length==0){
			Ext.example.msg('提示','请先选中要删除的行');
		}else{
			Ext.Msg.confirm('提示','确定删除数据?',function(button,text){
				if(button=='yes'){
					Ext.Ajax.request({
						url:'oset_DefLineAction_deleteOset_DefLine',
						params:{
							warehouseNo:data[0].get('warehouseNo'),
							lineNo:data[0].get('lineNo')
						},
						success:function(response){
							Ext.getCmp('grid_01_1J01').getStore().load();
						}
					});
				}
			});
		}
	},
	
	detailBrowse:function(){
		var data = Ext.getCmp('grid_01_1J01').getSelectionModel().getSelection();
		if (data.length != 0) {
			Ext.create('cms.view.oset.window.oset_DefLineAddorEditWindow',{
				title:$i18n.titlebrowse
			}).show(); 
			rowindex1J01=data[0].index;
			loadDefLine1J01(rowindex1J01);
			commonSetFieldReadOnly('form_01_1J01',true);
			commonSetCommMenu5PrevOrNext('oset_DefLine1J01','grid_01_1J01',rowindex1J01);
        }else{
        	Ext.example.msg('提示','请选择一条数据浏览');
        }
        browseCommMenu5('oset_DefLine1J01');
	},
	
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='1J01';
		queryGrid='grid_01_1J01';
	},
	
	detailExport:function(){
		commExport('grid_01_1J01');
	},
	
	grid_01_1J01change:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var wheresql={
				wheresql:record.data.lineNo
			};
			Ext.apply(Ext.getCmp('grid_03_1J01').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_1J01').getStore().removeAll();
			Ext.getCmp('grid_03_1J01').getStore().load();
		}else{
			Ext.getCmp('grid_03_1J01').getStore().removeAll();
		}
	},
	left1J01:function(){
		var defLine=Ext.getCmp('grid_01_1J01').getSelectionModel().getSelection();
		var data = Ext.getCmp('grid_03_1J01').getSelectionModel().getSelection();
		if(defLine.length!=0){
			if(data.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				for(var i=0;i<data.length;i++){
					detail1.push(defLine[0].get('warehouseNo'));
					detail2.push(data[i].get('lineNo'));
					detail3.push(data[i].get('custNo'));
				};
				Ext.Ajax.request({
					url:'oset_DefLineAction_deleteOSet_Line_Cust',
					params:{
						warehouseNo:detail1,
						lineNo:detail2,
						custNo:detail3
					},
					success:function(response){
						Ext.getCmp('grid_03_1J01').getStore().load();
						Ext.getCmp('grid_02_1J01').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请先选中要编辑的行');
			}
		}else{
			Ext.example.msg('提示','请先选择一条路线');
		}
			
	},
	
	right1J01:function(){
		var defLine=Ext.getCmp('grid_01_1J01').getSelectionModel().getSelection();
		var data = Ext.getCmp('grid_02_1J01').getSelectionModel().getSelection();
		var lineSeqNo = 0;

		if(defLine.length!=0){
			if(data.length!=0){
				var detail=[];
				for(var i=0;i<data.length;i++){
					var lineCust={
					id:{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:defLine[0].get('warehouseNo'),
						lineNo:defLine[0].get('lineNo'),
						custNo:data[i].get('custNo')
					},
					rgstName:Ext.get('workerNo').getValue(),
					rgstDate:new Date()
					};
					detail.push(lineCust);
				}
				var str=Ext.encode(detail);
				Ext.Ajax.request({
					url:'oset_DefLineAction_saveOset_Line_Cust',
					params:{
						str:str
					},
					success:function(response){
						Ext.getCmp('grid_02_1J01').getStore().load();
						Ext.getCmp('grid_03_1J01').getStore().load();
					}
				});
			}else{
				Ext.example.msg('提示','请先选中要编辑的行');
			}
		}else{
			Ext.example.msg('提示','请先选择一条路线');
		}
		
	},
	
	prev:function(){
		rowindex1J01=rowindex1J01-1;
		loadDefLine1J01(rowindex1J01);
		commonSetCommMenu5PrevOrNext('oset_DefLine1J01','grid_01_1J01',rowindex1J01);
	},
	
	next:function(){
		rowindex1J01=rowindex1J01+1;
		loadDefLine1J01(rowindex1J01);
		commonSetCommMenu5PrevOrNext('oset_DefLine1J01','grid_01_1J01',rowindex1J01);
	},
	
	add:function(th){
		addDefLine1J01();
	},
	
	save:function(){
		saveDefLine1J01();
	},
	
	close:function(){
		closeWindow1J01();
	},
	
	lineNoBlur:function(){
		if(saveType1J01==0){
			Ext.Ajax.request({
			method:'post',
			url:'oset_DefLineAction_checkLineNo',
			params:{
				lineNo:Ext.getCmp('line_no1J01').getValue()
		    },
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res=='1'){
		    		Ext.example.msg('提示','该线路代码已有，请重新录入！');
		    		Ext.getCmp('line_no1J01').setValue('');
		    		Ext.getCmp('line_no1J01').focus();
		    	}
		    }
			});
		}
	}
});

//保存
function saveDefLine1J01(){
	if(Ext.getCmp('form_01_1J01').getForm().isValid()){
		var text1=getComboDisplay1J01(Ext.getCmp('deliver_type1J01'));
		var text2=getComboDisplay1J01(Ext.getCmp('transportation1J01'));
		var deliverTypeText=text1.substr(text1.indexOf("]")+1, text1.length);
		var transportTypeText=text2.substr(text2.indexOf("]")+1, text2.length);
		var defLine={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.getCmp('warehouse1J01').getValue(),
				lineNo:Ext.getCmp('line_no1J01').getValue()
			},
			deliverType:Ext.getCmp('deliver_type1J01').getValue(),
			transportType:Ext.getCmp('transportation1J01').getValue(),
			lineName:Ext.getCmp('line_name1J01').getValue(),
			lineFname:deliverTypeText +'-'+ transportTypeText +'-'+ Ext.getCmp('line_name1J01').getValue(),
			lineRemark:Ext.getCmp('line_remark1J01').getValue(),
			rgstName:Ext.get('workerNo').getValue(),
			rgstDate:new Date(),
			updtName:Ext.get('workerNo').getValue(),
			updtDate:new Date()
		};
		var str=Ext.encode(defLine);
		Ext.Ajax.request({
			url:'oset_DefLineAction_saveOset_DefLine',
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('grid_01_1J01').getStore().load();
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	}
};

//新增前加载
function addDefLine1J01(){
	Ext.getCmp('form_01_1J01').getForm().reset();
	Ext.getCmp('warehouse1J01').setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('deliver_type1J01').setValue('1');
	Ext.getCmp('transportation1J01').setValue('1');
	Ext.getCmp('line_full_name1J01').setValue('保存时自动生成');
};

//关闭窗口
function closeWindow1J01(){
	Ext.getCmp('oset_DefLineAddorEditWindow').close();
	Ext.getCmp('grid_01_1J01').getStore().load();
};

//填充数据
function loadDefLine1J01(rowindex1J01){
	var line=Ext.getCmp('grid_01_1J01').getStore().getAt(rowindex1J01-(Ext.getCmp('grid_01_1J01').getStore().currentPage-1)*appConfig.getPageSize());
	Ext.getCmp('warehouse1J01').setValue(line.data.warehouseNo);
	Ext.getCmp('line_no1J01').setValue(line.data.lineNo);
	Ext.getCmp('deliver_type1J01').setValue(line.data.deliverType);
	Ext.getCmp('transportation1J01').setValue(line.data.transportType);
	Ext.getCmp('line_name1J01').setValue(line.data.lineName);
	Ext.getCmp('line_full_name1J01').setValue(line.data.lineFname);
	Ext.getCmp('line_remark1J01').setValue(line.data.lineRemark);
};

//获得Combo显示的值
function getComboDisplay1J01(combo) {
    var value = combo.getValue();
    var valueField = combo.valueField;
    var record;
    combo.getStore().each(function(r){
        if(r.data[valueField] == value){
            record = r;
            return false;
        }
    });
    return record ? record.get(combo.displayField) : null;
};