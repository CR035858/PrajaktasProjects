package com.gl.aopproj.config;

import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.gl.aopproj.entity.AuditLog;
import com.gl.aopproj.entity.Employee;
import com.gl.aopproj.repository.AuditRepository;
import com.gl.aopproj.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class AspectConfig {

	@Autowired
	AuditRepository auditRepository;

	@Autowired
	EmployeeService employeeService;

	@Before("execution(public * com.gl.aopproj.service.*.*(..) )")
	public void logBeforeAllMethods(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName() + " Started");
	}

	@After("execution(public * com.gl.aopproj.service.*.*(..) )")
	public void logAfterAllMethods(JoinPoint joinPoint) {
		log.info(joinPoint.getSignature().getName() + " Ended");
	}
	
	@Around("execution(public * com.gl.aopproj.service.*.*(..) )")
	public void logBeforeAndAfterAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info(proceedingJoinPoint.getSignature().getName() + " Started");
		proceedingJoinPoint.proceed();
		log.info(proceedingJoinPoint.getSignature().getName() + " Ended");
	}

	@AfterReturning("execution(public * com.gl.aopproj.service.EmployeeServiceImpl.addEmployee(..) )")
	public void logBeforeAddEmployee(JoinPoint joinPoint) {
		auditRepository.saveAndFlush(AuditLog.builder().createDate(new Date())
				.discription("Details of employee added " + joinPoint.getArgs()[0]).build());
	}
	
	@AfterThrowing("execution(public * com.gl.aopproj.service.EmployeeService.updateEmployee(..) )")
	public void logIfErrorWhileUpdatingEmployee(JoinPoint joinPoint) {
		Employee employee = (Employee) joinPoint.getArgs()[0];
		auditRepository.saveAndFlush(AuditLog.builder().createDate(new Date())
				.discription("Error while updating employee since there is no employee with id " + employee.getId())
				.build());
	}
}
