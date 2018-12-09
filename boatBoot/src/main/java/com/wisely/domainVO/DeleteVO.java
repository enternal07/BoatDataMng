package com.wisely.domainVO;

import java.util.List;

public class DeleteVO {
	
	private String pk;
	private String name;
	private List<String> pks;

	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}

	public List<String> getPks() {
		return pks;
	}

	public void setPks(List<String> pks) {
		this.pks = pks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
