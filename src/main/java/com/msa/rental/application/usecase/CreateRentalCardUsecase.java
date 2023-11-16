package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDto;

public interface CreateRentalCardUsecase {
	public RentalCardOutputDTO createRentalCard(UserInputDto userInputDto);
}
