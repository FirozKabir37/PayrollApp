package com.example.PayrollApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.PayrollApp.Entity.EmpDetail;

@Repository
public interface EmpDetailRepository extends JpaRepository<EmpDetail,Long>{

	  @Query(value = "SELECT EMP_ID  FROM EMP_DETAIL WHERE EMP_ID=:empId", nativeQuery = true)
	  String getEmpId(@Param(value = "empId") String empId);
	  
	  @Query(value = "SELECT e.EMP_ID,e.GRADE,e.ACC_NUMBER  FROM EmpDetail e", nativeQuery = true)
	  List<EmpDetail> getEmpDtls();
	  
	  @Query(value = "SELECT NAME FROM EMP_DETAIL WHERE EMP_ID=:empId", nativeQuery = true)
	  String getEmpNameById(@Param(value = "empId") String empId);
}
