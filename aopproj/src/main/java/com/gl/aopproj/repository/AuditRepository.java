package com.gl.aopproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.aopproj.entity.AuditLog;


@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer>{

}
