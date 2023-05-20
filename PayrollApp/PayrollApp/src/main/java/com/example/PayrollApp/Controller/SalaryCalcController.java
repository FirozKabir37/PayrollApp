package com.example.PayrollApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayrollApp.Entity.SalaryCalc;
import com.example.PayrollApp.Entity.Transaction;
import com.example.PayrollApp.Service.SalaryCalcService;
import com.example.PayrollApp.Service.TransactionService;

@RestController
@RequestMapping("/salaryCalc")
public class SalaryCalcController {

	@Autowired
	private SalaryCalcService salaryCalcService;
	private SalaryCalc salaryCalc;
	
	@PostMapping("/saveSalary/{grade}/{basicAmt}")
	public SalaryCalc saveTran(@PathVariable("grade") String grade,@PathVariable("basicAmt") double basicAmt) {
		try {
			salaryCalc=salaryCalcService.calc(grade, basicAmt);
			if(salaryCalc!=null) {
				System.out.print("Data saved Successfully"); 			
			 }
			 else {
				 System.out.print("Data not saved"); 
			 }	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return salaryCalc; 
		
	}
	
	@PostMapping("/disburseSalary")
	public SalaryCalc disburseSalary() {
		try {
			salaryCalcService.updateSalaryDtls();
			 	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 return salaryCalc; 
		
	}
	
	public SalaryCalcService getSalaryCalcService() {
		return salaryCalcService;
	}
	public void setSalaryCalcService(SalaryCalcService salaryCalcService) {
		this.salaryCalcService = salaryCalcService;
	}
	public SalaryCalc getSalaryCalc() {
		return salaryCalc;
	}
	public void setSalaryCalc(SalaryCalc salaryCalc) {
		this.salaryCalc = salaryCalc;
	}
	
}
