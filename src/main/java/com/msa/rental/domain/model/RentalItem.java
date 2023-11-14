package com.msa.rental.domain.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalItem {
	private Item item;
	private LocalDate rendDate;
	private boolean overdue;
	private LocalDate overdueDate;

	public RentalItem(Item item, LocalDate rendDate, boolean overdue, LocalDate overdueDate) {
		this.item = item;
		this.rendDate = rendDate;
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
