package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.domain.model.Item;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ReturnItemInputPort implements ReturnItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        RentalCard rentalCard = rentalCardOutputPort.loadRentalCard(returnDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));

        Item returnItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        rentalCard.returnItem(returnItem, LocalDate.now());

        return RentalCardOutputDTO.mapToDTO(rentalCard);
    }
}
