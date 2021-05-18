package com.test.multilevelqueue;

public class Process {
	
	// Process id
	private int pid;
	
	// Process priority
	private int priority;
	
	// Process arrival time
	private int arrivalTime;
	
	// Process burst time
	private int burstTime;

	public Process() {}
	
	
	public Process(int pid, int priority, int arrivalTime, int burstTime) {
		this.pid = pid;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
}
