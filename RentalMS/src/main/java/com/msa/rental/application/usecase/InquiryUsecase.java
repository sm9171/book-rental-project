package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;

import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDto);
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDto);
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDTO userInputDto);
}