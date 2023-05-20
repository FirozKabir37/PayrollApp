package com.example.PayrollApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PayrollApp.Entity.SalaryCalc;
import com.example.PayrollApp.Entity.Transaction;

@Repository
public interface SalaryCalcRepository extends JpaRepository<SalaryCalc,Long>{

}
