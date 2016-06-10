package com.redwood.rp.genericquery.vo.json;

import java.util.List;
import java.util.Map;

import com.redwood.rp.core.vo.json.BaseRestResponseVO;


public class GenericQueryResponseVO  extends BaseRestResponseVO{
	
	private List<Map<String, Object>> result;
	
	private int affectedRows;
	
	public GenericQueryResponseVO() {
		super();
	}

	public int getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	public GenericQueryResponseVO(List<Map<String, Object>> result) {
		super();
		this.result = result;
	}

	public List<Map<String, Object>> getResult() {
		return result;
	}

	public void setResult(List<Map<String, Object>> result) {
		this.result = result;
	}

}
