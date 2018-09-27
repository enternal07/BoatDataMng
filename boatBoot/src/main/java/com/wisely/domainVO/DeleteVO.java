package com.wisely.domainVO;

import java.util.List;

public class DeleteVO {
	
	private String pk;
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
	
	
}
