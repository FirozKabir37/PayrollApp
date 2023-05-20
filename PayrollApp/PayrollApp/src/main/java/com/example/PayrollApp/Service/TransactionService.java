package com.example.PayrollApp.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PayrollApp.Entity.AccountDetail;
import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Entity.Transaction;
import com.example.PayrollApp.Repository.AccountDetailRepository;
import com.example.PayrollApp.Repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountDetailRepository accountDetailRepository;
	
	private Transaction transaction;
	private AccountDetail accountDetail;
	
	private double prevcurrBal=0.00;
	private double prevcreditBal=0.00;
	private double prevdebitBal=0.00;
	private double presentcurrBal=0.00;
	private double presentcreditBal=0.00;
	private double presentdebitBal=0.00;
	
	public Transaction saveTransaction(Transaction transaction) {
		try {
			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  	  
			   transaction.setTranDate(dtf.format(now));
			   transaction=transactionRepository.save(transaction);
			   
			   AccBalanceUpdate(transaction.getAccNum(),transaction.getTranAmount());
		   }
		catch(Exception e) {e.printStackTrace();}
		   
		return transaction;
	}
	
	public void AccBalanceUpdate(long accNum,double tranAmt) {
	      try {
	    	  
	    	      prevcurrBal=accountDetailRepository.getEmployeeCurrBal(accNum);
	    	   
	    		  presentcurrBal=prevcurrBal+tranAmt;  
	    	 
	    	  
	    	  accountDetailRepository.updateCurrBal(presentcurrBal,accNum);
	    	  
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public AccountDetail getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}
	
}
