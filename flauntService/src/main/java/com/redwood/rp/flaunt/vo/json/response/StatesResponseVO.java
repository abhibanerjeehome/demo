package com.redwood.rp.flaunt.vo.json.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;
import com.redwood.rp.flaunt.das.dto.StateVO;

@JsonPropertyOrder({ "status", "result" })
@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonWriteNullProperties(false)
public class StatesResponseVO extends BaseRestResponseVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private StateVO state;
	
	private List<StateVO> statesList;
	//private Collection<StateVO> stateList;

	
	public StatesResponseVO(List<StateVO> statesList) {
		super();

		//this.stateList=statesMap.values();
		//for (StateVO states : stateList) {
			
		this.statesList = statesList;
		//}
	}
	


	@JsonProperty("result")	
	public List<StateVO> getStateList() {
		return statesList;
	}

	public void setStateList(List<StateVO> statesList) {
		this.statesList = statesList;
	}
	
	@JsonProperty("states")	
	public StateVO getStates() {
		return state;
	}

	public void setStates(StateVO state) {
		this.state = state;
	}
	


}
