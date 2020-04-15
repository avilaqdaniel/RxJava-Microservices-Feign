package com.avilaq.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse implements Serializable{
	
	private Long id;
	private String document;
	private boolean fingerprint;
	private boolean balcklist;
}
