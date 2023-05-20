package com.example.PayrollApp.Model;

public class AccountInfo {
	private String accType;
	private String accName;
	private String bankName;
	private String branchName;
	private long  accNumber;
	private double currBal;
	
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
}
