package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Item {
	private Integer no;
	private String title;

	public Item(Integer no, String title) {
		this.no = no;
		this.title = title;
	}
}
