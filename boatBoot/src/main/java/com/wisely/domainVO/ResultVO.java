package com.wisely.domainVO;

import java.io.Serializable;

/**
 * @author zhanglei0
 *
 */
public class ResultVO implements Serializable{

	private static final long serialVersionUID = -545712146633028245L;
	
	private boolean success;
	
	private String message;
	
	private Object data;
	
	/**
	 *Ϊsuccess  false
	 */
	public ResultVO() {
		success = false;
	}
	
	public ResultVO(boolean success) {
	    this.success = success;
	}
	
	/**
	 * 
	 * success  false
	 */
	public ResultVO(String message) {
	    this.success = false;
	    this.message = message;
	}
	
	public ResultVO(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	/**
	 * 
	 * @param success
	 * @return ResultVO
	 * @author guodzh1
	 */
	public ResultVO setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResultVO setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResultVO setData(Object data) {
		this.data = data;
		return this;
	}

}
