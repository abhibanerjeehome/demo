package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;

@JsonPropertyOrder({ "status", "result" })
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class RolesResponseVO extends BaseRestResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RolesVO> rolesVO;
	
	@JsonProperty("result")
	public List<RolesVO> getRolesVO() {
		return rolesVO;
	}
	public void setRolesVO(List<RolesVO> rolesVO) {
		this.rolesVO = rolesVO;
	}

}
