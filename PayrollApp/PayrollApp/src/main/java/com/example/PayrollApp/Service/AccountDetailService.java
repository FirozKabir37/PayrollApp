package com.example.PayrollApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PayrollApp.Entity.AccountDetail;
import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Repository.AccountDetailRepository;
import com.example.PayrollApp.Repository.EmpDetailRepository;

@Service
public class AccountDetailService {

	@Autowired
	private AccountDetailRepository accountDetailRepository;
	
	public AccountDetail saveAccountDetail(AccountDetail accountDetail) {
		
	    return 	accountDetailRepository.save(accountDetail);
	}
	
    public double getBalance(long accNum) {
		
	    return 	accountDetailRepository.getEmployeeCurrBal(accNum);
	}
}
