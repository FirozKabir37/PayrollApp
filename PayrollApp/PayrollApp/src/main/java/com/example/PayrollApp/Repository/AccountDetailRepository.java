package com.example.PayrollApp.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PayrollApp.Entity.AccountDetail;
 

@Repository
public interface AccountDetailRepository extends JpaRepository<AccountDetail,Long>{
	
	@Query(value = "SELECT NVL(max(ACC_NUMBER),0) FROM ACCOUNT_DETAIL", nativeQuery = true)
	 public long getMaxAccNum();
	
	@Query(value = "SELECT NVL(a.CURR_BAL,0) FROM ACCOUNT_DETAIL a WHERE a.ACC_NUMBER=:accNum", nativeQuery = true)
	public double getEmployeeCurrBal(@Param(value = "accNum") long accNum);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE ACCOUNT_DETAIL a SET a.CURR_BAL=:presentBal WHERE a.ACC_NUMBER=:accNum", nativeQuery = true)
	public void updateCurrBal(@Param("presentBal") double presentBal,@Param("accNum") long accNum);
	
	 
}
