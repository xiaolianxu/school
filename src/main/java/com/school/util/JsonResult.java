package com.school.util;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = -4699713095477151086L;

    private Object data;
    private Object message;
    private boolean success;
    private Integer size;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}


}
