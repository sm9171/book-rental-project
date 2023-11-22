package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class IDName {
	private String id;
	private String name;

	public IDName(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
