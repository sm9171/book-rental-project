package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalCard;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentalCardOutputDTO {
	private String rentalCardId;
	private String memberId;
	private String memberName;
	//대여가능여부
	private String rentStatus;
	//전체연체료
	private Long totalLateFee;
	//전체대여도서건수
	private Long totalRentalCnt;
	//반납도서건수
	private Long totalReturnCnt;
	//연체중인도서건수
	private Long totalOverdueCnt;
	public static RentalCardOutputDTO mapToDTO(RentalCard rental){
		RentalCardOutputDTO rentDTO = new RentalCardOutputDTO();
		rentDTO.setRentalCardId(rental.getRentalCardNo().getNo().toString());
		rentDTO.setMemberId(rental.getMember().getId().toString());
		rentDTO.setMemberName(rental.getMember().getName());
		rentDTO.setRentStatus(rental.getRentStatus().toString());
		rentDTO.setTotalRentalCnt(rental.getRentalItemList().stream().count());
		rentDTO.setTotalReturnCnt(rental.getReturnItemList().stream().count());

		rentDTO.setTotalOverdueCnt(rental.getRentalItemList().stream().filter(i ->
				i.isOverdue()).count());
		return rentDTO;
	}
}