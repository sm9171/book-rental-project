package com.msa.rental.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalCardNo {
	private String no;

	public RentalCardNo(String no) {
		this.no = no;
	}

	public static RentalCardNo createRentalCardNo() {
		UUID uuid = UUID.randomUUID();
		String year = String.valueOf(LocalDate.now().getYear());
		String no = year + '-' + uuid;
		RentalCardNo rentalCardNo = new RentalCardNo(no);
		return rentalCardNo;
	}

	public static RentalCardNo sample() {
		return RentalCardNo.createRentalCardNo();
	}
}
