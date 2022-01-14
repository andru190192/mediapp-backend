package com.andresguachisaca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

	private String code;

	private String title;

	private String message;

}
