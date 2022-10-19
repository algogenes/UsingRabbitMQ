package com.airtime.dtos;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	private String accountNumber;
	private String mobileNumber;
	private double amount;
	private String status;
	
	public Transaction() {}
	public Transaction(String accountNumber, String mobileNumber, Integer amount, String status) {
		
		this.accountNumber = accountNumber;
		this.mobileNumber = mobileNumber;
		this.amount = amount;
		this.status = status;
	}

	public Transaction(String accountNumber, String mobileNumber, String amount, String status) {

		this.accountNumber = accountNumber;
		this.mobileNumber = mobileNumber;
		this.amount = Double.parseDouble(amount);
		this.status = status;


	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Transaction [accountNumber=" + accountNumber + ", mobileNumber=" + mobileNumber + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
	
	
	
}
