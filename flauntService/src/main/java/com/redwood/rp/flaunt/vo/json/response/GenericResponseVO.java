package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class GenericResponseVO extends BaseRestResponseVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Object payload;
	
	public GenericResponseVO(Object payload) {
		super();
		this.payload = payload;
	}
	
	public GenericResponseVO() {
	}

	@JsonProperty("result")	
	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
