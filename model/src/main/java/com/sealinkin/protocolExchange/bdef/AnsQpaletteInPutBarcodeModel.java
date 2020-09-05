package com.sealinkin.protocolExchange.bdef;

import java.io.Serializable;
import java.util.List;

import com.sealinkin.protocolExchange.fcdata.AnsPackingModel;
import com.sealinkin.protocolExchange.fcdata.ArticleInfoModel;
/**
 * 堆叠采集》条码录入 对应客户端StuQpaletteInPutBarcodeAns
 * @author lich
 *
 */
@SuppressWarnings("serial")
public class AnsQpaletteInPutBarcodeModel implements Serializable{
   private String  ArtcicleNum; //是否一码对应多品项（1：没有对应任何品项，2：有且只对应一个品项，3：对应多个品项）
   
   private List<AnsPackingModel> listPacking;
   private List<ArticleInfoModel> listArticlinfo;
       	
   public String getArtcicleNum() {
		return ArtcicleNum;
	}
	public void setArtcicleNum(String artcicleNum) {
	ArtcicleNum = artcicleNum;
	}
	public List<AnsPackingModel> getListPacking() {
		return listPacking;
	}
	public void setListPacking(List<AnsPackingModel> listPacking) {
		this.listPacking = listPacking;
	}
	public List<ArticleInfoModel> getListArticlinfo() {
		return listArticlinfo;
	}
	public void setListArticlinfo(List<ArticleInfoModel> listArticlinfo) {
		this.listArticlinfo = listArticlinfo;
	}
}
