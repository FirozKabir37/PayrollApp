package com.example.PayrollApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Entity.Transaction;
import com.example.PayrollApp.Service.EmpDetailService;
import com.example.PayrollApp.Service.TransactionService;

@RestController
@RequestMapping("/tran")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	private Transaction transaction;
	
	@PostMapping("/saveTran")
	public Transaction saveTran(@RequestBody Transaction transaction) {
		try {
			transaction=transactionService.saveTransaction(transaction);
			if(transaction!=null) {
				System.out.print("Data saved Successfully"); 			
			 }
			 else {
				 System.out.print("Data not saved"); 
			 }	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return transaction; 
		
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
