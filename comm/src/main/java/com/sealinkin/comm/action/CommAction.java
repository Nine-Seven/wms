package com.sealinkin.comm.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.sealinkin.bdef.model.Bdef_DefWorkerModel;
import com.sealinkin.comm.model.SysArg;
import com.sealinkin.comm.service.HttpService;
import com.sealinkin.export.excel.ExcelFontBean;
import com.sealinkin.util.JsonConfigDate;
import com.sealinkin.util.MyCreateExcel;

@SuppressWarnings({"unused","rawtypes"})	
public class CommAction extends ActionSupport
{
    /**
     * 
     */
    private static final long serialVersionUID = -4391271878696445809L;
    
    private Integer pagesize = SysArg.meta_pagesize;
    
    private Integer start = 0;
    
    private String text;
    
    private String addText;
    
    private String id;
    
    private String resource;
    
    private Integer page;
    
    private Integer limit;
    
    private String callback;
    

	private Bdef_DefWorkerModel mdBdef_DefWorker;
    

	public void setMdBdef_DefWorker(Bdef_DefWorkerModel mdBdef_DefWorker)
	{
		this.mdBdef_DefWorker = mdBdef_DefWorker;
	}

	private Integer length;
	
	public Integer getLength()
	{
		return length;
	}

	public void setLength(Integer length)
	{
		this.length = length;
	}

	private Integer draw;
    
    
    
    public Integer getDraw()
	{
		return draw;
	}

	public void setDraw(Integer draw)
	{
		this.draw = draw;
	}

	public String getResource()
    {
        return resource;
    }
    
    public void setResource(String resource)
    {
        this.resource = resource;
    }
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getAddText()
    {
        return addText;
    }
    
    public void setAddText(String addText)
    {
        this.addText = addText;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    public Integer getStart()
    {
        return start;
    }
    
    public void setStart(Integer start)
    {
        this.start = start;
    }
    
    public Integer getPage()
    {
        return page;
    }
    
    public void setPage(Integer page)
    {
        this.page = page;
    }
    
    public Integer getLimit() 
    {
		return limit;
	}

	public void setLimit(Integer limit) 
	{
		this.limit = limit;
	}

	public void writeArray(List<?> list)
    {
        HttpService.setRespsonse();
        JSONArray json = JSONArray.fromObject(list);
        PrintWriter writer = null;
        try
        {
            writer = HttpService.getWriter();
            writer.println(json.toString());
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
        
    }
    
    public void writeArrayFtDate(List<?> list)
    {
        JsonConfigDate jsCfg = new JsonConfigDate();
        HttpService.setRespsonse();
        JSONArray json = JSONArray.fromObject(list, jsCfg);
        PrintWriter writer = null;
        try
        {
            writer = HttpService.getWriter();
            writer.println(json.toString());
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
        
    }
    
    public void writeObj(Object o)
    {
        HttpService.setRespsonse();
        PrintWriter writer = null;
        String jsonstr = JSONObject.fromObject(o).toString();
        try
        {
            writer = HttpService.getWriter();
            writer.println(jsonstr);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }
    
    public void wirteJsonPStr(String o)
    {
    	o = callback+"("+o+")";
    	writeStr(o);
    }
    
    public void writeStr(String o)
    {
        HttpService.setRespsonse();
        PrintWriter writer = null;
        
        try
        {
            writer = HttpService.getWriter();
            writer.println(o);
            writer.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
    }
    
    public void writeObjDateFt(Object o)
    {
        this.writeStr(this.covtObjectToJson(o));
    }
    
    public void writeObjDateJsonPFt(Object o)
    {
        this.wirteJsonPStr(this.covtObjectToJson(o));
    }
    
    public String covtListToJson(Object object){
		JsonConfigDate jsCfg=new JsonConfigDate();
		return JSONArray.fromObject(object, jsCfg).toString();
	}
    public String covtObjectToJson(Object object)
    {
        JsonConfigDate jsCfg = new JsonConfigDate();
        return JSONObject.fromObject(object, jsCfg).toString();
    }
    
    public String covtObjectToJson(Object object, String[] ignoreField)
    {
        JsonConfigDate jsCfg = new JsonConfigDate(ignoreField);
        return JSONObject.fromObject(object, jsCfg).toString();
    }

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public Bdef_DefWorkerModel getMdBdef_DefWorker() {
		return (Bdef_DefWorkerModel)HttpService.getRequest().getSession().getAttribute("loginUser");
	}

	public void setMdBdef_DefWorker(HttpServletRequest request,Bdef_DefWorkerModel mdBdef_DefWorker) {
		mdBdef_DefWorker.setCurrEnterpriseNo(mdBdef_DefWorker.getEnterpriseNo());
		
		request.getSession().setAttribute("loginUser", mdBdef_DefWorker);
	}

	public String getCallback()
	{
		return callback;
	}

	public void setCallback(String callback)
	{
		this.callback = callback;
	}
	/**
	 * 导出报表数据方法
	 * @param title			文件名称
	 * @param threads		字段标题集合四个参数[单据编号,标题名称,字段集合，字段名称集合]
	 * @param map			参数map
	 * @param resultList
	 * @throws Exception
	 */

	public void commExportAction(String title, 
								 String[] threads , 
								 List resultList) throws Exception{
		MyCreateExcel createExcel = new MyCreateExcel(new ExcelFontBean());
		String userA = HttpService.getRequest().getHeader("user-agent");
		createExcel.createExcel03(HttpService.getResponse() , resultList, threads , title , userA);
	}
}
