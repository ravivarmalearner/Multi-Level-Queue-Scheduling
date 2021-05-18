package com.test.multilevelqueue;

import java.util.LinkedList;
import java.util.Queue;

public class PCBQueue {

	// Priority of the queue
	private int priority;
	
	private Queue<Process> qu = null;
	
	public PCBQueue(int priority) {
		qu = new LinkedList<Process>();
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Queue<Process> getQu() {
		return qu;
	}

	public void setQu(Queue<Process> qu) {
		this.qu = qu;
	}
}
