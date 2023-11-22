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
public class RentalItem {
	@Embedded
	private Item item;
	private LocalDate rentDate;
	private boolean overdue;
	private LocalDate overdueDate;

	public RentalItem(Item item, LocalDate rentDate, boolean overdue, LocalDate overdueDate) {
		this.item = item;
		this.rentDate = rentDate;
		this.overdue = overdue;
		this.overdueDate = overdueDate;
	}

	public static RentalItem createRentalItem(Item item) {
		return new RentalItem(item, LocalDate.now(), false, LocalDate.now().plusDays(14));
	}

	public void changeOverdue(boolean changedOverdue) {
		this.overdue = changedOverdue;
	}

	public void changeOverdueDate(LocalDate changedOverDueDay) {
		this.overdueDate = changedOverDueDay;
	}
}
