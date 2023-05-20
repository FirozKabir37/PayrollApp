package com.example.PayrollApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PayrollApp.Entity.AccountDetail;
import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Entity.SalaryCalc;
import com.example.PayrollApp.Model.AccountInfo;
import com.example.PayrollApp.Model.EmployeeInfo;
import com.example.PayrollApp.Service.AccountDetailService;
import com.example.PayrollApp.Service.EmpDetailService;

@RestController
@RequestMapping("/empDetail")
@CrossOrigin
public class EmpDetailController {
	
	@Autowired
	private EmpDetailService empDetailService;
	@Autowired
	private AccountDetailService accountDetailService;
	
	private EmpDetail empDetail;
	private List<EmployeeInfo>listEmpInfo;
	String empid="";
	
	@PostMapping("/saveEmpDetail")
	public EmpDetail saveEmpDetail(@RequestBody EmpDetail empDetail) {
		try {
			  
			  empid=empDetailService.getEmpId(empDetail.getEmpId());			   
			 if(empid==null) {
				 empDetail=empDetailService.saveEmpDetail(empDetail);
				 if(empDetail==null) {
					 System.out.print("Data not saved"); 
				 }
				 else {
					 System.out.print("Data saved Successfully"); 
				 }
			 }
		}
		catch(Exception e) {
			System.out.print("Duplicate Employee id not allowed");
		}
		
		 return empDetail; 
	}
	@GetMapping("/getBalance/{accNum}")
	public void saveTran(@PathVariable("accNum") long accNum) {
		try {
			System.out.print(accountDetailService.getBalance(accNum));
		}
		catch(Exception e) {
			e.printStackTrace();
		}		  	
	}
	
	@GetMapping("/getEmpDtls")
	public List<EmpDetail> getEmpDtls(){
		listEmpInfo=empDetailService.getempInfoList11();
		if(listEmpInfo.size()>0) {
			 for(EmployeeInfo emp:listEmpInfo) {
				System.out.print(emp.getName());
				System.out.print(emp.getGrade());
				System.out.print(emp.getGrossSalary());
			 }
		}
		
		return null;
	} 

	public EmpDetail getEmpDetail() {
		return empDetail;
	}

	public void setEmpDetail(EmpDetail empDetail) {
		this.empDetail = empDetail;
	}

	public List<EmployeeInfo> getListEmpInfo() {
		return listEmpInfo;
	}

	public void setListEmpInfo(List<EmployeeInfo> listEmpInfo) {
		this.listEmpInfo = listEmpInfo;
	}
		
}
