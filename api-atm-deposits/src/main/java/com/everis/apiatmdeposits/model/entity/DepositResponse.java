package com.everis.apiatmdeposits.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fingerprintEntityName;
	private List<ValidAccount> validAccounts;
	private double customerAmount;
	 
}
