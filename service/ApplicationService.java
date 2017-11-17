package com.bcj.citicreditcardcronjob.service;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.bcj.citicreditcardcronjob.dao.ApplicationDAO;
import com.bcj.citicreditcardcronjob.mailservice.MailSSL;
import com.bcj.citicreditcardcronjob.model.Applicant;
import com.bcj.citicreditcardcronjob.model.ApplicantCreditScore;
import com.bcj.citicreditcardcronjob.model.CreditCard;
import com.bcj.citicreditcardcronjob.restservice.RestService;

public class ApplicationService {

	/*
	 * ApplicationContext factory = new
	 * ClassPathXmlApplicationContext("spring-servlet.xml");
	 * 
	 * 
	 * ApplicationDAO applicationdao = (ApplicationDAO)
	 * factory.getBean("applicationdao");
	 */

	// @Autowired
	ApplicationDAO applicationdao = new ApplicationDAO();

	// @Autowired
	ExecutableThreadService executablethreadservice = new ExecutableThreadService();

	public ApplicationService() {

	}

	public void getStatusResult(Applicant app) {

		RestService rest = new RestService();
		System.out.println("Sent inside");
		ApplicantCreditScore applicantcreditscore = rest.getCreditScore(app);

		System.out.println(applicantcreditscore.getCreditScore());
		System.out.println("SuccessfullyDone");

		String creditScore = applicantcreditscore.getCreditScore();

		System.out.println(app.getSsn());
		System.out.println(applicantcreditscore.getSsn());

		if (applicantcreditscore.getFirstName().equalsIgnoreCase(app.getFirstName())
				&& applicantcreditscore.getLastName().equalsIgnoreCase(app.getLastName())
				&& applicantcreditscore.getSsn().equals(app.getSsn())) {

			/*
			 * BufferedReader br = null;
			 * 
			 * try { br = (new BufferedReader(new FileReader(
			 * "C:\\BCJ-Dec16\\Development\\Work_Space\\Core_Java\\CityCreditCardCronJob\\src\\main\\resources\\ApplicantsInformation.txt"
			 * )));
			 * 
			 * } catch (FileNotFoundException e) {
			 * 
			 * e.printStackTrace(); }
			 * 
			 * String sCurrentLine;
			 * 
			 * try {
			 * 
			 * while ((sCurrentLine = br.readLine()) != null) { String[] info =
			 * sCurrentLine.split(" ");
			 * 
			 * System.out.println(app.toString()); if
			 * (info[0].equalsIgnoreCase(app.getFirstName()) &&
			 * info[1].equalsIgnoreCase(app.getLastName()) &&
			 * info[2].equalsIgnoreCase(app.getSsn())) {
			 */

			if (Integer.parseInt(creditScore) > 700) {
				app.setProcessStatus("Processed");
				app.setApplicationStatus("Approved");
				System.out.println("True" + app.getFirstName());
				String creditcarddetails = generateCreditCard(creditScore, app);
				applicationdao.updateApplicant(app);

				String sub = "City Bank Credit card decision";
				String msg = "Hello" + app.getFirstName()
						+ "Your Credit card has been approved and you will receave card in 10 to 15 Bussiness Days"
						+ creditcarddetails;

				MailSSL.send(app.getEmail(), sub, msg);
			}

			else {

				app.setProcessStatus("Processed");

				app.setApplicationStatus("Declined");

				applicationdao.updateApplicant(app);

				String sub = "City Bank Credit card decision";
				String msg = "Sorry your Credit card has been Declined ";

				MailSSL.send(app.getEmail(), sub, msg);
			}

		}
	}

	public synchronized String generateCreditCard(String creditScore, Applicant app) {

		CreditCard creditcard = new CreditCard();
		Random rnd = new Random();
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR) + 3);
		String month = String.valueOf(now.get(Calendar.MONTH) + 1);

		int creditCard1 = 100000000 + rnd.nextInt(900000000);
		int creditCard2 = 100000 + rnd.nextInt(900000);
		int cvv = 1000 + rnd.nextInt(9000);

		creditcard.setCreditCardNumber(String.valueOf(creditCard1) + String.valueOf(creditCard2));
		creditcard.setNameOnCard(app.getFirstName());
		creditcard.setCvv(String.valueOf(cvv));
		creditcard.setExpiryDate("month/" + year.substring(2));
		creditcard.setCardOwner_ID(app.getId());

		if (Integer.parseInt(creditScore) <= 650) {
			creditcard.setCreditLimit("2000");
		} else if (Integer.parseInt(creditScore) > 650 && Integer.parseInt(creditScore) <= 670) {
			creditcard.setCreditLimit("4000");
		} else if (Integer.parseInt(creditScore) > 670 && Integer.parseInt(creditScore) <= 700) {
			creditcard.setCreditLimit("5000");
		} else if (Integer.parseInt(creditScore) > 700 && Integer.parseInt(creditScore) <= 730) {
			creditcard.setCreditLimit("7000");
		} else if (Integer.parseInt(creditScore) > 730 && Integer.parseInt(creditScore) <= 760) {
			creditcard.setCreditLimit("8000");
		} else {
			creditcard.setCreditLimit("10000");
		}

		System.out.println("before");
		System.out.println(creditcard);
		applicationdao.saveCreditCard(creditcard);
		System.out.println("After");
		System.out.println(creditcard);

		return "Credit Card Number" + creditcard.getCreditCardNumber() + " and " + "Balance is "
				+ creditcard.getCreditLimit();

	}

	public void getApplicant() {

		List<Applicant> applicant = applicationdao.getApplicant();

		executablethreadservice.threadsexicution(applicant);

		System.out.println("Hello getApp");

	}

}
