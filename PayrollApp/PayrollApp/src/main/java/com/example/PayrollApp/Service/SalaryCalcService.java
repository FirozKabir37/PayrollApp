package com.example.PayrollApp.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PayrollApp.Entity.AccountDetail;
import com.example.PayrollApp.Entity.EmpDetail;
import com.example.PayrollApp.Entity.SalaryCalc;
import com.example.PayrollApp.Entity.Transaction;
import com.example.PayrollApp.Repository.AccountDetailRepository;
import com.example.PayrollApp.Repository.EmpDetailRepository;
import com.example.PayrollApp.Repository.SalaryCalcRepository;
import com.example.PayrollApp.Repository.TransactionRepository;

 

@Service
@Transactional
public class SalaryCalcService {

	@Autowired
	private SalaryCalcRepository salaryCalcRepository;
	
	@Autowired
	private EmpDetailRepository empDetailRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountDetailRepository accountDetailRepository;
	
	private List<EmpDetail> listEmp;
	private List<SalaryCalc> salaryList;
	private SalaryCalc salaryCalc;
	private Transaction transaction;
	private AccountDetail accountDetail;
	
	private double netSalaryGrade6=0.00;
	private double grossSalary=0.00;
	private String empId="";
	private String empGrade="";	 
	private long accNumber=0;
	private double tranAmt=0.00;
	private double prevcurrBal=0.00;
	private double prevcreditBal=0.00;
	private double prevdebitBal=0.00;
	private double presentcurrBal=0.00;
	private double presentcreditBal=0.00;
	private double presentdebitBal=0.00;
	private long companyAccNum=10000007;
	private double totalEpmlSalary=0.00;
	
	public  SalaryCalc calc(String grade,double basicAmt) {
	try {
		if(grade.equalsIgnoreCase("Grade-6")) {
			if(basicAmt!=0) {
				netSalaryGrade6=basicAmt+(basicAmt*0.15+basicAmt*0.20);
				getEmpDtls();
			}
			}
	   }
	  catch(Exception e) {
		e.printStackTrace();
	}
		
	  return salaryCalc;
	}
	public void insertSalary() {	
		SalaryCalc sal=new SalaryCalc();		    
			try {
				sal.setEmpId(empId);
				sal.setAccNumber(accNumber);
				sal.setGrade(empGrade);
				if(empGrade.equalsIgnoreCase("Grade-6")) 
				  sal.setGrossAmount(netSalaryGrade6);
				else if(empGrade.equalsIgnoreCase("Grade-5")) 
					sal.setGrossAmount(netSalaryGrade6+5000);
				else if(empGrade.equalsIgnoreCase("Grade-4")) 
					sal.setGrossAmount(netSalaryGrade6+10000);
				else if(empGrade.equalsIgnoreCase("Grade-3")) 
					sal.setGrossAmount(netSalaryGrade6+15000);
				else if(empGrade.equalsIgnoreCase("Grade-2")) 
					sal.setGrossAmount(netSalaryGrade6+20000);
				else if(empGrade.equalsIgnoreCase("Grade-1")) 
					sal.setGrossAmount(netSalaryGrade6+25000);
				salaryCalc=salaryCalcRepository.save(sal);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		    
	}
	public void getEmpDtls() {
		try {
			listEmp=empDetailRepository.findAll();
			if(listEmp.size()>0) {
				for(EmpDetail empDetail:listEmp) {
					empId=empDetail.getEmpId();
					empGrade=empDetail.getGrade();
					accNumber=empDetail.getAccNumber();
					insertSalary();
				}
			}	
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
	public void updateSalaryDtls() {
		try {
			salaryList=salaryCalcRepository.findAll();
			if(salaryList.size()>0) {
				for(SalaryCalc obj:salaryList) {
					accNumber=obj.getAccNumber();
					tranAmt=obj.getGrossAmount();	
					totalEpmlSalary=totalEpmlSalary+tranAmt;
					transactionHist();					
					EmplAccCredit(accNumber);
					
				}
			}
			CompanyAccDebit(companyAccNum);
		}
		catch(Exception e) {e.printStackTrace();}		
	}
	
	public void transactionHist() {	
		Transaction tran=new Transaction();		    
			try {
				tran.setAccNum(accNumber);
				tran.setTranAmount(tranAmt);
				tran.setTranType("CREDIT");
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  	  
				tran.setTranDate(dtf.format(now));				 
				transaction=transactionRepository.save(tran);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		    
	}
	 	
	public void EmplAccCredit(long accNum) {
      try {
    	  prevcurrBal=accountDetailRepository.getEmployeeCurrBal(accNum);
    	 
    	  presentcurrBal=prevcurrBal+tranAmt; 
    	  accountDetailRepository.updateCurrBal(presentcurrBal,accNumber);
    	  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void CompanyAccDebit(long companyAccNum) {
		  prevcurrBal=accountDetailRepository.getEmployeeCurrBal(companyAccNum);  	      
  		  presentcurrBal=prevcurrBal+tranAmt;  
  	    
  	    if(prevcurrBal>totalEpmlSalary) {
  	    	presentcurrBal=prevcurrBal-totalEpmlSalary;
  	    	accountDetailRepository.updateCurrBal(presentcurrBal,accNumber);
  	    }
  	    else {
  	    	System.out.print("Company Account Balance Not Sufficient");
  	    }
	}
	 

	public List<EmpDetail> getListEmp() {
		return listEmp;
	}

	public void setListEmp(List<EmpDetail> listEmp) {
		this.listEmp = listEmp;
	}
	public SalaryCalc getSalaryCalc() {
		return salaryCalc;
	}
	public void setSalaryCalc(SalaryCalc salaryCalc) {
		this.salaryCalc = salaryCalc;
	}
	public List<SalaryCalc> getSalaryList() {
		return salaryList;
	}
	public void setSalaryList(List<SalaryCalc> salaryList) {
		this.salaryList = salaryList;
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
