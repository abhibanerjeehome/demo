package com.redwood.rp.common.document.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
@XmlRootElement(name = "documents_response")
@XmlType(propOrder={"images","banners","videos","termsAndConditions" , "purchaseAgreements", "disclosures", "warranty","description","platMap","additionalPropertyDocuments",
		"titleInformation","dueDiligence","qna","sellerConfidentialdocs","sellerTNCdocs","globalTNCdocs"})
public class AllFilesVO implements Serializable {

	private static final long serialVersionUID = 1L;

	// categories based on Genres
	private DocumentVO images;
	private DocumentVO banners;
	private DocumentVO videos;
	// sections of documents.
	private DocumentVO disclosures; // plat map, additional property, title information, QA.
	private DocumentVO dueDiligence;
	private DocumentVO description; // purchase agreements
	private DocumentVO termsAndConditions;
	private DocumentVO warranty;

	// platinum records.
	private DocumentVO sellerConfidentialdocs;
	private DocumentVO sellerTNCdocs;
	private DocumentVO globalTNCdocs;


	@JsonProperty("images")
	@XmlElement(name="images",type=DocumentVO.class)
	public DocumentVO getImages() {
		return images;
	}
	public void setImages(DocumentVO images) {
		this.images = images;
	}

	@JsonProperty("banners")
	@XmlElement(name="banners",type=DocumentVO.class)
	public DocumentVO getBanners() {
		return banners;
	}
	public void setBanners(DocumentVO banners) {
		this.banners = banners;
	}

	@JsonProperty("videos")
	@XmlElement(name="videos",type=DocumentVO.class)
	public DocumentVO getVideos() {
		return videos;
	}
	public void setVideos(DocumentVO videos) {
		this.videos = videos;
	}


	@JsonProperty("disclosures")
	@XmlElement(name="disclosures",type=DocumentVO.class)
	public DocumentVO getDisclosures() {
		return disclosures;
	}
	public void setDisclosures(DocumentVO disclosures) {
		this.disclosures = disclosures;
	}

	@JsonProperty("due_diligence")
	@XmlElement(name="due_diligence",type=DocumentVO.class)
	public DocumentVO getDueDiligence() {
		return dueDiligence;
	}

	public void setDueDiligence(DocumentVO dueDiligence) {
		this.dueDiligence = dueDiligence;
	}

	@JsonProperty("description")
	@XmlElement(name="description",type=DocumentVO.class)
	public DocumentVO getDescription() {
		return description;
	}
	public void setDescription(DocumentVO description) {
		this.description = description;
	}

	@JsonProperty("terms_conditions")
	@XmlElement(name="terms_conditions", type = DocumentVO.class)	
	public DocumentVO getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(DocumentVO termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	@JsonProperty("warranty")
	@XmlElement(name = "warranty", type = DocumentVO.class)	
	public DocumentVO getWarranty() {
		return warranty;
	}
	public void setWarranty(DocumentVO warranty) {
		this.warranty = warranty;
	}

	/**
	 * @return the sellerConfidentialdocs
	 */
	@JsonProperty("seller_confidential")
	@XmlElement(name="seller_confidential",type=DocumentVO.class)	
	public DocumentVO getSellerConfidentialdocs() {
		return sellerConfidentialdocs;
	}
	/**
	 * @param sellerConfidentialdocs the sellerConfidentialdocs to set
	 */
	public void setSellerConfidentialdocs(DocumentVO sellerConfidentialdocs) {
		this.sellerConfidentialdocs = sellerConfidentialdocs;
	}
	/**
	 * @return the sellerTNCdocs
	 */
	@JsonProperty("seller_platinum_terms_conditions")
	@XmlElement(name="seller_platinum_terms_conditions",type=DocumentVO.class)	
	public DocumentVO getSellerTNCdocs() {
		return sellerTNCdocs;
	}
	/**
	 * @param sellerTNCdocs the sellerTNCdocs to set
	 */
	public void setSellerTNCdocs(DocumentVO sellerTNCdocs) {
		this.sellerTNCdocs = sellerTNCdocs;
	}
	/**
	 * @return the globalTNCdocs
	 */
	@JsonProperty("global_platinum_terms_conditions")
	@XmlElement(name="global_platinum_terms_conditions",type=DocumentVO.class)	
	public DocumentVO getGlobalTNCdocs() {
		return globalTNCdocs;
	}
	/**
	 * @param globalTNCdocs the globalTNCdocs to set
	 */
	public void setGlobalTNCdocs(DocumentVO globalTNCdocs) {
		this.globalTNCdocs = globalTNCdocs;
	}	

}