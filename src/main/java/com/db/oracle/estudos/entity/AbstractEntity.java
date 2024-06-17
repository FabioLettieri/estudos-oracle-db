package com.db.oracle.estudos.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -100564688856439289L;

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
