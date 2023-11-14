package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IDName {
	private String id;
	private String name;

	public IDName(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
