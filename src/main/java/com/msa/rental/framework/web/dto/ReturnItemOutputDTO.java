package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.ReturnItem;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDTO rentItemOutputDTO = new ReturnItemOutputDTO();

        rentItemOutputDTO.setItemNo(returnItem.getRentalItem().getItem().getNo());

        rentItemOutputDTO.setItemTitle(returnItem.getRentalItem().getItem().getTitle());
        rentItemOutputDTO.setReturnDate(returnItem.getReturnDate());
        return rentItemOutputDTO;
    }
}