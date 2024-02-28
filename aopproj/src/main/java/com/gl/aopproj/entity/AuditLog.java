package com.gl.aopproj.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="audit_log")
public class AuditLog {
	@Id
	private Integer id;
	
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date createDate;
	
	@Column
	private String discription; 
}
