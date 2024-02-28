package com.gl.aopproj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.aopproj.entity.AuditLog;
import com.gl.aopproj.repository.AuditRepository;


@RestController
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	AuditRepository auditRepository;
	
	@GetMapping("/logs")
	public List<AuditLog> getRecords() {
		return auditRepository.findAll();
	}
}

