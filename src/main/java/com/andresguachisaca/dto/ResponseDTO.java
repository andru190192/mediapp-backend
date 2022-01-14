package com.andresguachisaca.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseDTO {

	private String title;

	private String message;

	private Object data;

	private ErrorDTO error;

	private LocalDateTime timestamp;

}
