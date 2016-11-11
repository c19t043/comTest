package com.kybaby.newbussiness.doctorsign.vo;

import java.util.List;

public class TableList {
	private String time;
	private String count;
	private List<?> objs;
	
	public TableList(String time,String count,List<?> objs) {
		this.time = time;
		this.count = count;
		this.objs = objs;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	public List<?> getObjs() {
		return objs;
	}

	public void setObjs(List<?> objs) {
		this.objs = objs;
	}
}
