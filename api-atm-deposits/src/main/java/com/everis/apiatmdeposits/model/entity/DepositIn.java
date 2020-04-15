package com.everis.apiatmdeposits.model.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositIn implements Serializable{
	
	private String documentNumber;
	private double amount;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
