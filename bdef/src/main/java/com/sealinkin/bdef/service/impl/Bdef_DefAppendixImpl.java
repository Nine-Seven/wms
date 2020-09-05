package com.sealinkin.bdef.service.impl;

import java.io.File;

import java.util.Date;
import java.util.List;

import com.sealinkin.bdef.model.Bdef_DefappendixModel;
import com.sealinkin.bdef.po.Bdef_Defappendix;
import com.sealinkin.bdef.po.Bdef_DefappendixId;
import com.sealinkin.bdef.service.Ibdef_DefAppendixService;
import com.sealinkin.comm.model.ExtListDataBo;
import com.sealinkin.comm.model.MsgRes;
import com.sealinkin.comm.model.PageBo;
import com.sealinkin.dao.service.IGenericManager;
import com.sealinkin.util.CommUtil;
import com.sealinkin.util.ContextUtil;
import com.sealinkin.util.FileUtilSys;

@SuppressWarnings({"unchecked","rawtypes"})
public class Bdef_DefAppendixImpl implements Ibdef_DefAppendixService {
	private IGenericManager genDao;
	
	public IGenericManager getGenDao() {
		return genDao;
	}
	public void setGenDao(IGenericManager genDao) {
		this.genDao = genDao;
	}
	//获取附录信息
	@Override
	public ExtListDataBo<Bdef_DefappendixModel> getDefAppendixList(
			String enterpriseNo, String warehouseNo, String ownerNo,
			String strQuery, PageBo poPagebo) throws Exception {
		
		String sql="select a.*,f_get_fieldtext('N','FILE_TYPE',a.type) typeText," +
				   "  f_get_fieldtext('N','RELATE_CLASS',a.relate_class) relateClassText " +
				   "  from bdef_defappendix a" +
				   " where a.enterprise_no='"+enterpriseNo+"' " +
				   "   and a.warehouse_no='"+warehouseNo+"' ";
		
	    String totsql ="select count(1) from bdef_defappendix a" +
				      " where a.enterprise_no='"+enterpriseNo+"' " +
				      "   and a.warehouse_no='"+warehouseNo+"' ";
	    
	    if(ownerNo!=null && !ownerNo.equals(""))
		{
			sql=sql+" and a.owner_no in("+ownerNo+") ";
			totsql=totsql+" and a.owner_no in("+ownerNo+")";
		}
	    
	    if(strQuery!=null && !strQuery.equals(""))
		{
			String ws=CommUtil.covtCollectionToWhereSql(strQuery);
			sql=sql+ws;
			totsql=totsql+ws;
		}
	    
	    List<Bdef_DefappendixModel> list=genDao.getListByNativeSql(sql, Bdef_DefappendixModel.class,poPagebo.getStart(),poPagebo.getPagesize());
	    Integer intCount = genDao.getCountByNativeSql(totsql);
	    
		return  new ExtListDataBo<Bdef_DefappendixModel>(list,intCount);
	}
	@Override
	public MsgRes save(String enterpriseNo, String warehouseNo,
			String ownerNo,String name,  String type, String relateOrderNo,
			String relateClass, File file,String fileName, String meno, String workerNo)
			throws Exception {
		String sql=" select a.file_path from  bdef_defappendix a " +
				   "  where a.enterprise_no = '"+enterpriseNo+"' " +
				   "    and a.warehouse_no = '"+warehouseNo+"' " +
				   "    and a.owner_no = '"+ownerNo+"' " +
				   "    and a.file_name = '"+name+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		Bdef_Defappendix appendix= new Bdef_Defappendix();
		Bdef_DefappendixId id= new Bdef_DefappendixId();
		
		id.setEnterpriseNo(enterpriseNo);
		id.setWarehouseNo(warehouseNo);
		id.setOwnerNo(ownerNo);
		id.setFileName(name);
		
		appendix.setId(id);
		appendix.setType(type);
		appendix.setRelateClass(relateClass);
		appendix.setRelateOrderno(relateOrderNo);
		appendix.setMeno(meno);
		appendix.setFilePath("uploadFiles"+System.getProperty ("file.separator")+"fileManage"+System.getProperty ("file.separator")+fileName);
		
		if(list.size()>0){
			System.out.println(list.get(0));
			File relfile = new File(ContextUtil.getWebRootPath()+list.get(0));
			if (relfile.isFile() && relfile.exists()) {  
				relfile.delete();  
			}			
			appendix.setUpdtDate(new Date());
			appendix.setUpdtName(workerNo);
		}else{
			appendix.setRgstName(workerNo);
			appendix.setRgstDate(new Date());
		}
		
		genDao.saveOrUpdateObj(appendix);
		
		FileUtilSys.writeFile(file, fileName, ContextUtil.getWebRootPath()+"uploadFiles"+System.getProperty ("file.separator")+"fileManage"+System.getProperty ("file.separator"));
		return new MsgRes(true,"保存成功","");
	}
	@Override
	public MsgRes delete(String enterpriseNo, String warehouseNo,
			String ownerNo, String name) throws Exception {
		String sql=" select a.file_path from  bdef_defappendix a " +
				   "  where a.enterprise_no = '"+enterpriseNo+"' " +
				   "    and a.warehouse_no = '"+warehouseNo+"' " +
				   "    and a.owner_no = '"+ownerNo+"' " +
				   "    and a.file_name = '"+name+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0){
			File relfile = new File(ContextUtil.getWebRootPath()+list.get(0));
					
			if (relfile.isFile() && relfile.exists()) {  
				relfile.delete();  
			}			
		}
		
		String del="delete from bdef_defappendix a" +
				   " where a.enterprise_no = '"+enterpriseNo+"' " +
				   "  and a.warehouse_no = '"+warehouseNo+"' " +
				   "  and a.owner_no = '"+ownerNo+"' " +
				   "  and a.file_name = '"+name+"' ";
		genDao.exceuteSql(del);
		return new MsgRes(true,"删除成功","");
	}
	@Override
	public MsgRes update(String enterpriseNo, String warehouseNo,
			String ownerNo, String name, String type, String relateOrderNo,
			String relateClass, File file, String fileName, String meno,
			String workerNo) throws Exception {
		
		String sql=" select a.file_path from  bdef_defappendix a " +
				   "  where a.enterprise_no = '"+enterpriseNo+"' " +
				   "    and a.warehouse_no = '"+warehouseNo+"' " +
				   "    and a.owner_no = '"+ownerNo+"' " +
				   "    and a.file_name = '"+name+"' ";
		List list = genDao.getListByNativeSql(sql);
		
		if(list.size()>0 && file!=null){
			File relfile = new File(ContextUtil.getWebRootPath()+list.get(0));
			if (relfile.isFile() && relfile.exists()) {  
				relfile.delete();  
			}
			
			String updateSql=" update bdef_defappendix a " +
					         "    set a.type='"+type+"', " +
					         "        a.relate_orderno='"+relateOrderNo+"', " +
					         "        a.relate_class ='"+relateClass+"', " +
					         "        a.meno='"+meno+"', " +
					         "        a.file_path='"+"uploadFiles"+System.getProperty ("file.separator")+"fileManage"+System.getProperty ("file.separator")+fileName+"', " +
					         "        a.updt_name='"+workerNo+"', " +
					         "        a.updt_date=sysdate " +
					         "  where a.enterprise_no = '"+enterpriseNo+"' " +
							 "    and a.warehouse_no = '"+warehouseNo+"' " +
							 "    and a.owner_no = '"+ownerNo+"' " +
							 "    and a.file_name = '"+name+"' ";
			genDao.exceuteSql(updateSql);
			FileUtilSys.writeFile(file, fileName, ContextUtil.getWebRootPath()+"uploadFiles"+System.getProperty ("file.separator")+"fileManage"+System.getProperty ("file.separator"));			
		}else{
			String updateSql=" update bdef_defappendix a " +
			         "    set a.type='"+type+"', " +
			         "        a.relate_orderno='"+relateOrderNo+"', " +
			         "        a.relate_class ='"+relateClass+"', " +
			         "        a.meno='"+meno+"', " +
			         "        a.updt_name='"+workerNo+"', " +
			         "        a.updt_date=sysdate " +
			         "  where a.enterprise_no = '"+enterpriseNo+"' " +
					 "    and a.warehouse_no = '"+warehouseNo+"' " +
					 "    and a.owner_no = '"+ownerNo+"' " +
					 "    and a.file_name = '"+name+"' ";
			genDao.exceuteSql(updateSql);
		}
		return new MsgRes(true,"修改成功","");
		
	}
	
}
