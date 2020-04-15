package com.everis.apireniec.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReniecResponse {

	private String EntityName;
	private Boolean success;
	
}