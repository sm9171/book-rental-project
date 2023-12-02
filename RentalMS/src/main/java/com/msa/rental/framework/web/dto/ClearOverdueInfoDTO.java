package com.msa.rental.framework.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClearOverdueInfoDTO {
	private String userId;
	private String userNm;
	private Integer point;
}
