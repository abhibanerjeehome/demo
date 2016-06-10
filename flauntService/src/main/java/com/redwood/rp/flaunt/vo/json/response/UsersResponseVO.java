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
public class UsersResponseVO extends BaseRestResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<UserVO> userVO;
	
	@JsonProperty("result")
	public List<UserVO> getUserVO() {
		return userVO;
	}
	public void setUserVO(List<UserVO> userVO) {
		this.userVO = userVO;
	}

}
