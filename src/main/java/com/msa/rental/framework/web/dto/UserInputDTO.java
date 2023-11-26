package com.msa.rental.framework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInputDTO {
	private String userId;
	private String userNm;

	public UserInputDTO(String userId, String userNm) {
		this.userId = userId;
		this.userNm = userNm;
	}
}
