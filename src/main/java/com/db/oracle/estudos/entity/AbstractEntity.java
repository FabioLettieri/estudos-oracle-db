package com.db.oracle.estudos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

	@Column(name = "CREATE_DATE", updatable = false)
	private Date createDate;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	@Column(name = "DELETED")
	private Boolean deleted;
	
	
	@PrePersist
    protected void onCreate() {
		this.createDate = new Date();
		this.updateDate = new Date();
		this.deleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }
	
}
