package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalItem;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate rentDate;
    private boolean overdue;
    //반납 예정일
    private LocalDate overdueDate;
    public static RentItemOutputDTO mapToDTO(RentalItem rentItem)
    {
        RentItemOutputDTO rentItemOutputDTO = new RentItemOutputDTO();
        rentItemOutputDTO.setItemNo(rentItem.getItem().getNo());
        rentItemOutputDTO.setItemTitle(rentItem.getItem().getTitle());
        rentItemOutputDTO.setRentDate(rentItem.getRentDate());
        rentItemOutputDTO.setOverdue(rentItem.isOverdue());
        rentItemOutputDTO.setOverdueDate(rentItem.getOverdueDate());
        return rentItemOutputDTO;
    }
}
