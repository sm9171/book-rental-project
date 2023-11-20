package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDto;

import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDto userInputDto);
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDto userInputDto);
    public Optional<List<ReturnItemOutputDTO>> getAllReturnItem(UserInputDto userInputDto);
}
