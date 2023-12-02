package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReturnItem {
	@Embedded
	private RentalItem rentalItem;
	private LocalDate returnDate;

	public ReturnItem(RentalItem rentalItem, LocalDate returnDate) {
		this.rentalItem = rentalItem;
		this.returnDate = returnDate;
	}

	public static ReturnItem createReturnItem(RentalItem rentalItem) {
		return new ReturnItem(rentalItem, LocalDate.now());
	}
}
