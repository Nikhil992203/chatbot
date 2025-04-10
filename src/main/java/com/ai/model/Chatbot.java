package com.ai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.StaticMetamodel;
import lombok.Data;
@Data
@Entity
public class Chatbot {
	  @Id
	    private String keyword;

	    @Column
	    private String value;
}
