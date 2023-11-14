package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalCard {
	private RentalCardNo rentalCardNo;
	private IDName member;
	private RentStatus rentStatus;
	private LateFee latefee;
	private List<RentalItem> rentalItemList = new ArrayList<RentalItem>();
	private List<ReturnItem> returnItemList = new ArrayList<ReturnItem>();

	@Builder
	public RentalCard(RentalCardNo rentalCardNo, IDName member, RentStatus rentStatus, LateFee latefee, List<RentalItem> rentalItemList, List<ReturnItem> returnItemList) {
		this.rentalCardNo = rentalCardNo;
		this.member = member;
		this.rentStatus = rentStatus;
		this.latefee = latefee;
		this.rentalItemList = rentalItemList;
		this.returnItemList = returnItemList;
	}

	public void addRentalItem(RentalItem rentalItem) {
		rentalItemList.add(rentalItem);
	}

	public void removeRentalItem(RentalItem rentalItem) {
		rentalItemList.remove(rentalItem);
	}

	public void addReturnItem(ReturnItem returnItem) {
		returnItemList.add(returnItem);
	}

	public static RentalCard createRentalCard(IDName creator) {
		RentalCard rentalCard = RentalCard.builder()
				.rentalCardNo(RentalCardNo.createRentalCardNo())
				.member(creator)
				.rentStatus(RentStatus.RENT_AVAILABLE)
				.latefee(LateFee.createLateFee())
				.build();
		return rentalCard;
	}

	public RentalCard rentItem(Item item) {
		checkRentalAvailable();
		this.addRentalItem(RentalItem.createRentalItem(item));
		return this;
	}

	private void checkRentalAvailable() {
		if (this.rentStatus == RentStatus.RENT_UNAVAILABLE) {
			throw new IllegalArgumentException("대여불가상태입니다.");
		}
		if (this.rentalItemList.size() > 5) {
			throw new IllegalArgumentException("이미 5권을 대여했습니다.");
		}
	}

	public RentalCard returnItem(Item item, LocalDate returnDate) {
		RentalItem rentalItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
		calculateLateFee(rentalItem, returnDate);
		this.addReturnItem(ReturnItem.createReturnItem(rentalItem));
		this.removeRentalItem(rentalItem);
		return this;
	}

	private void calculateLateFee(RentalItem rentalItem, LocalDate returnDate) {
		if (returnDate.compareTo(rentalItem.getOverdueDate()) > 0) {
			long point = Period.between(rentalItem.getOverdueDate(), returnDate).getDays() * 10;
			this.latefee.addPoint(point);
		}
	}

	public RentalCard overdueItem(Item item) {
		RentalItem rentalItem = this.rentalItemList.stream().filter(i -> i.getItem().equals(item)).findFirst().get();
		rentalItem.changeOverdue(true);
		this.rentStatus = RentStatus.RENT_UNAVAILABLE;
		rentalItem.changeOverdueDate(LocalDate.now().minusDays(1));
		return this;
	}

	public long makeAvailableRental(long point) throws Exception {

		if (this.rentalItemList.size() != 0) {
			throw new IllegalArgumentException("모든 도서가 반납되어야 정지를 해제할 수 있습니다.");
		}
		if (this.getLatefee().getPoint() != point) {
			throw new IllegalArgumentException("해당 포인트로 연체를 해제할");
		}

		this.changeLateFee(latefee.removePoint(point));
		if (this.getLatefee().getPoint() == 0) {
			this.rentStatus = RentStatus.RENT_AVAILABLE;
		}
		return this.getLatefee().getPoint();
	}

	private void changeLateFee(LateFee changedLateFee) {
		this.latefee = changedLateFee;
	}
}
