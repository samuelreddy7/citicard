package com.bcj.citicreditcardcronjob.dao;



import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bcj.citicreditcardcronjob.model.Applicant;
import com.bcj.citicreditcardcronjob.model.CreditCard;
import com.bcj.citicreditcardcronjob.util.HibernateUtil;





@Transactional
@Repository
public class ApplicationDAO {

	
	
	public List<Applicant> getApplicant() {

		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

		Query query = session.createQuery("FROM Applicant WHERE applicationStatus = :status");

		query.setString("status", "New");

		List<Applicant> applicant = query.list();

		System.out.println("Hello dao");

		return applicant;

	}

	public void updateApplicant(Applicant app) {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		session.update(app);
		tx.commit();
		session.close();

	}

	public void saveCreditCard(CreditCard creditcard) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		
		session.persist(creditcard);
		
		tx.commit();
		session.close();

	}

}
