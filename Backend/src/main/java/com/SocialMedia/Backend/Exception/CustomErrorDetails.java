package com.SocialMedia.Backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorDetails {

	private LocalDateTime timeStamp;

	private Integer status;

	private String error;

	private String message;

}
