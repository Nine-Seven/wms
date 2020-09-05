Ext.define('cms.view.Login',{
	alias:'widget.loginForm',
	extend:'Ext.window.Window',
	requires: ['cms.view.CheckCode'],
	constructor:function(config){
		Ext.apply(this,{
            height: 160,
            width: 280,
            title: '<h1>跨境电商仓储系统登陆</h1>',
            closeAction: 'hide',
            closable : false, 
            iconCls: 'login',
            layout: 'fit',
            modal : true, 
            plain : true,
            resizable: false,
            items:[Ext.create('Ext.form.Panel',{
    			border: false,
                bodyPadding: 10,
                fieldDefaults: {
                    labelAlign: 'left',
                    labelWidth: 55,
                    labelStyle: 'font-weight:bold'
                },
                defaults: {
                    margins: '0 0 10 0'
                }, 
                items:[{
                    xtype: 'textfield',
                    fieldLabel: '用户名',
                    blankText : '用户名不能为空',
                    id:'usernames',
                    allowBlank: false,
                    width:240
                },{
                    xtype: 'textfield',
                    fieldLabel: '密&nbsp;&nbsp;&nbsp;码',
                    id:'password',
                    allowBlank: false,
                    blankText : '密码不能为空',
                    width:240,
                    inputType : 'password' 
                },Ext.create('cms.view.CheckCode',{
        			cls : 'key',
                    fieldLabel : '验证码',
                    name : 'CheckCode',
                    id : 'CheckCode',
                    allowBlank : false,
                    isLoader:true,
                    blankText : '验证码不能为空(注意大小写)',
                    codeUrl: 'showCode.action',
                    width : 160 
        		})],
                buttons:[{
                    text:'登录',
                    handler:function(){
                        var form = this.up('form').getForm();
                        var win = this.up('window');
                        if(form.isValid()){
                        	Ext.Ajax.request({
                        		url:'./login.action',
                        		params:{
                        			str0:Ext.getCmp('usernames').getValue(),
                        			str1:Ext.getCmp('password').getValue(),
                        			code:Ext.getCmp('CheckCode').getValue()
                        		},
                        		success:function(response){
                        			var data = Ext.decode(response.responseText);
                        			if(data.isSucc){
                        				document.getElementById('userName').value  = data.obj.userName;
                        				document.getElementById('userId').value  = data.obj.userId;
                        				document.getElementById('stockId').value  = data.obj.stockId;
                        				document.getElementById('guestId').value  = data.obj.guestId;
                        				win.hide();
                        				var pageShow = new Ext.LoadMask(Ext.getBody(), {msg:"页面构建中,请稍后..."});
                        	        	pageShow.show();
                        	        	setTimeout(function(){pageShow.hide();},1000);
                        	        	Ext.create('cms.view.Viewport', {
                        	                layout: 'fit',
                        	                items: {
                        	                    xtype: 'cmsmenu'
                        	                }
                        	            });
                        			}else{
                        				Ext.Msg.alert('提示',data.msg);
                        			}
                        		}
                        	});
                        }
                    }
                },{
                    text:'取消',
                    handler:function(){
                    	this.up('form').getForm().reset();
                    }
                }]
    		})]
        });
        this.callParent(arguments); 
	}
});