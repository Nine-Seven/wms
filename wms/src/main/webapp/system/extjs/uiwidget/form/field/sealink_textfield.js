Ext.define('Ext.uiwidget.form.field.sealink_textfield',{
	extend:'Ext.form.field.Text',
	alias:'widget.sealink_textfield', 
	max:10,//支付输入长度
	oldValue:'',
    onRender: function() {
        this.autoEl = Ext.apply({}, this.initialConfig, this.autoEl);
        this.callParent(arguments);
        /**
         * el代表 Ext.Element
         * on(eventName, fn, [scope], [options])
         */
        this.el.on('change', this.change, this);
    },
    change:function(obj , newValue){
    	
    	//^\x00-\x80(注意有个非) 是全角字符的范围
    	var strChinese = newValue.value.match(/[^\x00-\x80]/g);
    	var chineseLength = 0;						//中文的长度
    	var totalLength = newValue.value.length;	//总长度
    	
    	//如果对字符进行正则取值后 不为NULL则把结果赋给中文长度
    	if(strChinese!=null)
    	{
    		chineseLength = strChinese.length;
    	}
    	
    	var ohterLength = totalLength - chineseLength;//其他长度
    	
    	var realLength  = ohterLength + (chineseLength*2);//真实长度
    	
    	//如果真实长度大于配置的长度 提示
    	if(realLength > this.max)
    	{
    		alert('长度大于'+this.max);
    		this.setValue(this.oldValue);
    	}else{
    		this.oldValue = newValue.value;
    	}
    }
});