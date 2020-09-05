/**
 * 键盘Enter键 向下一个Input Foucs
 * @param obj
 */
function bindEnterSkip(obj){
	//$(obj).on('keydown', 'input,textarea:visible:not(:disabled)', function(e){
	$(obj).on('keydown', 'input:visible:not(:disabled):not([readonly="readonly"]),textarea:visible:not(:disabled):not([readonly="readonly"])', function(e){
		if (e.keyCode == '13') {
			//var inputs = $(obj).find('input,textarea:visible:not(:disabled)');
			var inputs = $(obj).find('input:visible:not(:disabled):not([readonly="readonly"]),textarea:visible:not(:disabled):not([readonly="readonly"])');
			var idx = inputs.index($(this));
			idx = idx + 1;
			if (idx >= inputs.length) {
				if(Ext.getCmp($(this).attr("id").replace("-inputEl",""))!="undefined"
				&&Ext.getCmp($(this).attr("id").replace("-inputEl","")).xtype=="datefield"){
					Ext.getCmp($(this).attr("id").replace("-inputEl","")).triggerBlur();
				}
				inputs.eq(0).focus();
			}else{
				if(Ext.getCmp($(this).attr("id").replace("-inputEl",""))!="undefined"
				&&Ext.getCmp($(this).attr("id").replace("-inputEl","")).xtype=="datefield"){
					Ext.getCmp($(this).attr("id").replace("-inputEl","")).triggerBlur();
				}
				inputs.eq(idx).focus();
			}
		}
	});
};