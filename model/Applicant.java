package com.bcj.citicreditcardcronjob.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applicant")
public class Applicant {

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", dob=" + dob + ", ssn=" + ssn
				+ ", processStatus=" + processStatus + ", applicationStatus=" + applicationStatus + ", address="
				+ address + ", financialinfo=" + financialinfo + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String dob;
	private String ssn;
	
	private String processStatus ;
	
	private String applicationStatus ;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Address_ID")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Financial_ID")
	private FinancialInfo financialinfo;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "creditcard_id")
	private CreditCard creditcard;

	public FinancialInfo getFinancialinfo() {
		return financialinfo;
	}

	public void setFinancialinfo(FinancialInfo financialinfo) {
		this.financialinfo = financialinfo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	
	
}
