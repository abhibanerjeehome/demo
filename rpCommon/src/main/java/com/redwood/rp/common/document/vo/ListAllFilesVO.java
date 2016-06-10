package com.redwood.rp.common.document.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(false)
@XmlRootElement(name = "list_documents_response")
public class ListAllFilesVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("list_all_files_vo")
	@XmlElementWrapper(name="list_documents_response")
	@XmlElements(value = { @XmlElement(name="documents_response",type=AllFilesVO.class)})
	private List<AllFilesVO> listAllFilesVo;

	public List<AllFilesVO> getListAllFilesVo() {
		return listAllFilesVo;
	}

	public void setListAllFilesVo(List<AllFilesVO> listAllFilesVo) {
		this.listAllFilesVo = listAllFilesVo;
	}

}