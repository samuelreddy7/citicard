package com.bcj.citicreditcardcronjob.service;



import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bcj.citicreditcardcronjob.model.Applicant;



public class ExecutableThreadService implements Runnable {

	
private Applicant app;
	


ExecutableThreadService(){
	
       }
     
ExecutableThreadService(Applicant app) {
		this.app = app;
	}

	

	public void run() {

		System.out.println(Thread.currentThread().getName() + " (Start) message = " +app.getFirstName());

		ApplicationService appicationservice = new ApplicationService();
		
		appicationservice.getStatusResult(app);

		processmessage();

		System.out.println(Thread.currentThread().getName() + " (End)");

	}

	private void processmessage() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Applicant threadsexicution(List<Applicant> applicant) {

		System.out.println("Hello getApp");

		ExecutorService executor = Executors.newFixedThreadPool(3);

		Iterator<Applicant> itr = applicant.iterator();

		System.out.println("Hello getApp1");

		
		
		
		while (itr.hasNext()) {
			Applicant app = (Applicant) itr.next();

			Runnable worker = new ExecutableThreadService(app);

			executor.execute(worker);

		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}

		System.out.println("Finished all threads");

		return null;

	}

}
