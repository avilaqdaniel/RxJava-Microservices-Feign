package com.everis.apiatmdeposits.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FingerprintResponse {

	private String EntityName;
	private Boolean success;
}
