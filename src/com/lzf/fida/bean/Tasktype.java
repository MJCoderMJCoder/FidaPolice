package com.lzf.fida.bean;

/**
 * 任务类型（fragment_tasktype里面的ListView）
 */
public class Tasktype {
	private int time;
	private String type;
	private int inspect;
	private int inspected;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getInspect() {
		return inspect;
	}

	public void setInspect(int inspect) {
		this.inspect = inspect;
	}

	public int getInspected() {
		return inspected;
	}

	public void setInspected(int inspected) {
		this.inspected = inspected;
	}

	public Tasktype(int time, String type, int inspect, int inspected) {
		super();
		this.time = time;
		this.type = type;
		this.inspect = inspect;
		this.inspected = inspected;
	}

}
