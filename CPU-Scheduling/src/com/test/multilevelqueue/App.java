package com.test.multilevelqueue;

import java.util.Queue;
import java.util.Random;

public class App {
	
	private static final int MAX_QUEUE = 3;
	
	// Assume arrival time is 0 for all the processes
	private static final int ARRIVAL_TIME = 0;
	
	// Burst time rages from 1 to 10
	private static final int BURST_TIME_RANGE = 10;
	
	// Declaring 3 queues
	private static PCBQueue[] pcbQueues = new PCBQueue[MAX_QUEUE];
	
	// boolean array to check the process id has generated already
	// Scheduling 16 processes
	static boolean[] isPidExist = new boolean[16]; 
	
	public static int avgTunAroundTime = 0;
	
	public static int avgWaitingTime = 0;
	
	// Creating processes
	private static void createProcesses(int num) {
		for(int i = 0; i < num; i++) {
			Random rand = new Random();
			
			// Generate random id and assign to process 
			int randomPid = rand.nextInt(num);
			while(isPidExist[randomPid] == true) {
				randomPid = rand.nextInt(num);
			}
			isPidExist[randomPid] = true;
			
			// Generate random number and to know which queue the process will insert. 
			int randomPriority = rand.nextInt(MAX_QUEUE);
			
			// Generate random burst time
			int randomBurstTime = rand.nextInt(BURST_TIME_RANGE) + 1;
			
			// Creating Process
			Process process = new Process(randomPid, randomPriority, ARRIVAL_TIME, randomBurstTime);
			
			// Insert created process to the queue 
			Queue<Process> qu = pcbQueues[randomPriority].getQu();
			qu.offer(process);
			
			pcbQueues[randomPriority].setQu(qu);
		}
	}
	
	// Scheduling processes
	private static void schedule() {
		
		// Iterating all the queues and apply FCFS algorithm
		for(int i = pcbQueues.length - 1; i >= 0; i--) {
			int prevClosingTime = 0;
			System.err.println("Current processing queue is:: "+ i);
			
			Queue<Process> queue = pcbQueues[i].getQu();
			while(queue.size() > 0) {
				Process p = queue.peek();
				int pid = p.getPid();
				int arrivalTime = p.getArrivalTime();
				int burstTime = p.getBurstTime();
				
				System.out.println("Process " +pid + " enters to queue and starts running");
				System.out.println("Process " +pid + " arrived at " + arrivalTime);
				System.out.println("Process " +pid + " burst time is " + burstTime);
				
				int completionTime = prevClosingTime + burstTime;
				prevClosingTime = completionTime;
				System.out.println("Process " +pid +" is completed at " + completionTime);
				
				int turnAroundTime = completionTime - arrivalTime;
				int waitTime = turnAroundTime - burstTime;
				avgTunAroundTime += turnAroundTime;
				avgWaitingTime += waitTime;
				System.out.println("Process " +pid +" turnaround time is " + turnAroundTime);
				System.out.println("Process " +pid +" wait time is " + waitTime);
				queue.poll();
				System.out.println("==============================");
			}
		}
	}
	
	private static void disPlayQueues() {
		for(int i = pcbQueues.length - 1; i >= 0; i--) {
			System.out.println("Processes in queue no "+ i);
			Queue<Process> queue = pcbQueues[i].getQu();
			for(Process p : queue) {
				System.out.print(p.getPid()+"\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		// Initialize Multi-level queue
		for(int i = 0; i < pcbQueues.length; i++){
			pcbQueues[i] = new PCBQueue(i);
		}
		
		// Create 16 processes 
		int numberOfProcesses = 16;
		createProcesses(numberOfProcesses);
		
		// Displaying all the processes present in queues
		disPlayQueues();
		
		// Scheduling processes in the queue
		schedule();	
		
		avgWaitingTime = avgWaitingTime / numberOfProcesses;
		System.out.println("Average waiting time:"+ avgWaitingTime);
		
		avgTunAroundTime = avgTunAroundTime / numberOfProcesses;
		System.out.println("Average turnaround time:"+ avgTunAroundTime);
	}
}
