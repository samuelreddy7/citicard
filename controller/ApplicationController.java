 package com.bcj.citicreditcardcronjob.controller;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bcj.citicreditcardcronjob.service.ApplicationService;







public class ApplicationController {

	
	public static void main(String[] args) {

	
		  ApplicationContext factory = new ClassPathXmlApplicationContext("spring-servlet.xml");
		
		
		  ApplicationService service = (ApplicationService) factory.getBean("applicationservice");

		  service.getApplicant();

	}
}
