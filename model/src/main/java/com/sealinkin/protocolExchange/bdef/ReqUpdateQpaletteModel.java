package com.sealinkin.protocolExchange.bdef;

import java.io.Serializable;

/**
 * 堆叠采集》更新商品堆叠StuUpdateQpaletteReq
 * @author lich
 *
 */
@SuppressWarnings("serial")
public class ReqUpdateQpaletteModel implements Serializable{
	private String articleNo;                        
	private Double packQty;
	private Double palBaseBox;//每层箱数
	private Double palHeightBox;//层高
	private Double qpalette;//堆叠量
	private Double a_length;
	private Double a_width;
	private Double a_height;
	private Double packingWeight;
	private String packingUnit;
	private String spec;
	private String mpackingQty;
	private String mpackingUnit;
	private String  palHeightQbox;
	private String  sorterFlag;
	private String  ruleFlag;
	private String  createFlag;
	private String  rgstDate;
	private String  rgstName;
	private String  updtName;
	private String  updtdate;
	private String barcode;
    private String articleName;
    private String ownerNo;//委托业主（多业主用）
    private String article_identifier;
    public String getArticle_identifier() {
		return article_identifier;
	}
	public void setArticle_identifier(String article_identifier) {
		this.article_identifier = article_identifier;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	private String itemType;
    private String units; 
	 
	    
	   
	
	
	public String getBarcode() {
			return barcode;
		}
		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}
		public String getArticleName() {
			return articleName;
		}
		public void setArticleName(String articleName) {
			this.articleName = articleName;
		}
		public String getItemType() {
			return itemType;
		}
		public void setItemType(String itemType) {
			this.itemType = itemType;
		}
		public String getUnits() {
			return units;
		}
		public void setUnits(String units) {
			this.units = units;
		}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getMpackingQty() {
		return mpackingQty;
	}
	public void setMpackingQty(String mpackingQty) {
		this.mpackingQty = mpackingQty;
	}
	public String getMpackingUnit() {
		return mpackingUnit;
	}
	public void setMpackingUnit(String mpackingUnit) {
		this.mpackingUnit = mpackingUnit;
	}
	public String getPalHeightQbox() {
		return palHeightQbox;
	}
	public void setPalHeightQbox(String palHeightQbox) {
		this.palHeightQbox = palHeightQbox;
	}
	public String getSorterFlag() {
		return sorterFlag;
	}
	public void setSorterFlag(String sorterFlag) {
		this.sorterFlag = sorterFlag;
	}
	public String getRuleFlag() {
		return ruleFlag;
	}
	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}
	public String getCreateFlag() {
		return createFlag;
	}
	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}
	public String getRgstDate() {
		return rgstDate;
	}
	public void setRgstDate(String rgstDate) {
		this.rgstDate = rgstDate;
	}
	public String getRgstName() {
		return rgstName;
	}
	public void setRgstName(String rgstName) {
		this.rgstName = rgstName;
	}
	public String getUpdtName() {
		return updtName;
	}
	public void setUpdtName(String updtName) {
		this.updtName = updtName;
	}
	public String getUpdtdate() {
		return updtdate;
	}
	public void setUpdtdate(String updtdate) {
		this.updtdate = updtdate;
	}
	private String enterpriseNo ;
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	public String getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}
	public Double getPackQty() {
		return packQty;
	}
	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}
	public Double getPalBaseBox() {
		return palBaseBox;
	}
	public void setPalBaseBox(Double palBaseBox) {
		this.palBaseBox = palBaseBox;
	}
	public Double getPalHeightBox() {
		return palHeightBox;
	}
	public void setPalHeightBox(Double palHeightBox) {
		this.palHeightBox = palHeightBox;
	}
	public Double getQpalette() {
		return qpalette;
	}
	public void setQpalette(Double qpalette) {
		this.qpalette = qpalette;
	}

	public Double getA_length() {
		return a_length;
	}
	public void setA_length(Double a_length) {
		this.a_length = a_length;
	}
	public Double getA_width() {
		return a_width;
	}
	public void setA_width(Double a_width) {
		this.a_width = a_width;
	}
	public Double getA_height() {
		return a_height;
	}
	public void setA_height(Double a_height) {
		this.a_height = a_height;
	}
	public Double getPackingWeight() {
		return packingWeight;
	}
	public void setPackingWeight(Double packingWeight) {
		this.packingWeight = packingWeight;
	}
	
	
}
