package com.example.PayrollApp.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PayrollApp.Entity.AccountDetail;
import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Entity.SalaryCalc;
import com.example.PayrollApp.Model.EmployeeInfo;
import com.example.PayrollApp.Repository.AccountDetailRepository;
import com.example.PayrollApp.Repository.EmpDetailRepository;
import com.example.PayrollApp.Repository.SalaryCalcRepository;

@Service
@Transactional
public class EmpDetailService {
	@Autowired
	private EmpDetailRepository empDetailRepository;
	
	@Autowired
	private SalaryCalcRepository salaryCalcRepository;
	
	private List<SalaryCalc> salaryList;
	private String empId;
	private String grade;
	private double grossSalary;
	private String empName;
	
	private List<EmployeeInfo>listEmplInfo=new ArrayList<EmployeeInfo>();
	
	@Autowired
	private AccountDetailRepository accountDetailRepository;
	private long maxAccNum=0;
	
	public EmpDetail saveEmpDetail(EmpDetail empDetail) {
		maxAccNum=getmaxAccNumber();
		
		saveAccountDetail();
		if(maxAccNum==0) 
		empDetail.setAccNumber(10000001);
		else
			empDetail.setAccNumber(maxAccNum+1);	
	    return 	empDetailRepository.save(empDetail);
	}
	void saveAccountDetail() {
		AccountDetail accDetail=new AccountDetail();		 
		if(maxAccNum==0)  
			accDetail.setAccNumber(10000001);	
		else
			accDetail.setAccNumber(maxAccNum+1);
		
		accDetail.setCurrBal(0.00);
		accDetail.setCreditBal(0.00);
		accDetail.setDebitBal(0.00);
		accountDetailRepository.save(accDetail);
	}
	
public	List<EmployeeInfo> getempInfoList11(){
		EmployeeInfo empInfo=new EmployeeInfo();
		salaryList=salaryCalcRepository.findAll();
		if(salaryList.size()>0) {
			 for(SalaryCalc sal:salaryList) {
				    empId=sal.getEmpId();
					grade=sal.getGrade();
					grossSalary=sal.getGrossAmount();
					empName=empDetailRepository.getEmpNameById(empId);
					empInfo.setEmpId(empId);
					empInfo.setGrade(grade);
					empInfo.setName(empName);
					empInfo.setGrossSalary(grossSalary);
					listEmplInfo.add(empInfo); 
			 }				
			}								
		return 	listEmplInfo;
	}
	
	public String getEmpId(String empId) {
		
		return empDetailRepository.getEmpId(empId);
	}
	
	public  long getmaxAccNumber() {		
		return accountDetailRepository.getMaxAccNum();
	}
	public List<SalaryCalc> getSalaryList() {
		return salaryList;
	}
	public void setSalaryList(List<SalaryCalc> salaryList) {
		this.salaryList = salaryList;
	}
	 	 		
}
