package com.example.PayrollApp.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AccountDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long  Id;
	private long  accNumber;
	private double currBal;
	private double debitBal;
	private double creditBal;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	 
	public long getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}
	public double getCurrBal() {
		return currBal;
	}
	public void setCurrBal(double currBal) {
		this.currBal = currBal;
	}
	public double getDebitBal() {
		return debitBal;
	}
	public void setDebitBal(double debitBal) {
		this.debitBal = debitBal;
	}
	public double getCreditBal() {
		return creditBal;
	}
	public void setCreditBal(double creditBal) {
		this.creditBal = creditBal;
	}
	

}
