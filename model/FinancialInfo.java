package com.bcj.citicreditcardcronjob.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  
@Table(name= "financialinformation")
public class FinancialInfo {

	
	@Id  
	@Column(name = "Financial_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	
	
	private String annualIncome;
	private String rentMortage;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getRentMortage() {
		return rentMortage;
	}
	public void setRentMortage(String rentMortage) {
		this.rentMortage = rentMortage;
	}
	
	
	
}
