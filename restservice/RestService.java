package com.bcj.citicreditcardcronjob.restservice;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.bcj.citicreditcardcronjob.model.Applicant;
import com.bcj.citicreditcardcronjob.model.ApplicantCreditScore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestService {

	
	
	
	
	public ApplicantCreditScore getCreditScore(Applicant app){
		
		
		
		
		
		ApplicantCreditScore applicantcreditscore = new ApplicantCreditScore();

		

			ObjectMapper mapper = new ObjectMapper();

			String num = app.getSsn();

			try {

				Client client = Client.create();

				WebResource webresource = client.resource("http://localhost:8080/RestHibernate/webapi/json/product/get")
						.queryParam("ssn", num);

				ClientResponse response = webresource.accept("application/json").get(ClientResponse.class);

				if (response.getStatus() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}

				String output = response.getEntity(String.class);

				System.out.println("Output is :");
				System.out.println(output);

				try {
					 applicantcreditscore = mapper.readValue(output, ApplicantCreditScore.class);

					String creditscore = applicantcreditscore.getCreditScore();
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
       
			} finally {
				System.out.println("Success");
			}
			 return applicantcreditscore;
		}
	}
	
	
	
	

