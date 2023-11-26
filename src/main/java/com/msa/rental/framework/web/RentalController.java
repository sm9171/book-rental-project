package com.msa.rental.framework.web;

import com.msa.rental.application.usecase.*;
import com.msa.rental.framework.web.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = {"대여 Controller"})
public class RentalController {
    private final RentItemUsecase rentItemUsecase;
    private final ReturnItemUsecase returnItemUsecase;
    private final OverdueItemUsecase overdueItemUsecase;
    private final CreateRentalCardUsecase createRentalCardUsecase;
    private final InquiryUsecase inquiryUsecase;
    private final ClearOverdueItemUsecase clearOverdueItemUsecase;

    @ApiOperation(value = "도서카드 생성",notes = "사용자정보 -> 도서카드정보")
    @PostMapping("/RentalCard/")
    public ResponseEntity<RentalCardOutputDTO> createRentalCard(@RequestBody UserInputDTO userInputDTO) {
        RentalCardOutputDTO createdRentalCard = createRentalCardUsecase.createRentalCard(userInputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRentalCard);
    }

    @ApiOperation(value = "도서카드 조회",notes = "사용자정보(id) -> 도서카드정보")
    @GetMapping("/rental-card/{id}")
    public ResponseEntity<RentalCardOutputDTO> getRentalCard(@PathVariable String id) {
        Optional<RentalCardOutputDTO> rentalCardOutputDTO = inquiryUsecase.getRentalCard(new UserInputDTO(id,""));
        return ResponseEntity.ok(rentalCardOutputDTO.get());
    }

    @ApiOperation(value = "대여도서목록 조회",notes = "사용자정보(id) -> 대여도서목록 조회")
    @GetMapping("/rental-card/{id}/rentbook")
    public ResponseEntity<List<RentItemOutputDTO>> getAllRentItem(@PathVariable String id) {
        Optional<List<RentItemOutputDTO>> rentalCardOutputDTOs = inquiryUsecase.getAllRentItem(new UserInputDTO(id,""));
        return ResponseEntity.ok(rentalCardOutputDTOs.get());
    }
    @ApiOperation(value = "반납도서목록 조회",notes = "사용자정보(id) -> 반납도서목록 조회")
    @GetMapping("/rental-card/{id}/returnbook")
    public ResponseEntity<List<ReturnItemOutputDTO>> getAllReturnItem(@PathVariable String id) {
        Optional<List<ReturnItemOutputDTO>> allReturnItem = inquiryUsecase.getAllReturnItem(new UserInputDTO(id, ""));
        return ResponseEntity.ok(allReturnItem.get());
    }
    @ApiOperation(value = "대여기능",notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/rental-card/rent")
    public ResponseEntity<RentalCardOutputDTO> rentItem(@RequestBody
                                                        UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO resultDTO=
                rentItemUsecase.rentItem(userItemInputDTO);
        return ResponseEntity.ok(resultDTO);
    }
    @ApiOperation(value = "반납기능",notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/rental-card/return")
    public ResponseEntity<RentalCardOutputDTO> returnItem(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO = returnItemUsecase.returnItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }
    @ApiOperation(value = "연체기능",notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/rental-card/overdue")
    public ResponseEntity<RentalCardOutputDTO> overdueItem(@RequestBody UserItemInputDTO userItemInputDTO) throws Exception {
        RentalCardOutputDTO rentalCardOutputDTO = overdueItemUsecase.overDueItem(userItemInputDTO);
        return ResponseEntity.ok(rentalCardOutputDTO);
    }
    @ApiOperation(value = "연체해제기능",notes = "사용자정보,포인트 -> 도서카드정보 ")
    @PostMapping("/rental-card/clearoverdue")
    public ResponseEntity<RentalResultOutputDTO> clearOverdueItem(@RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        RentalResultOutputDTO rentalResultOutputDTO = clearOverdueItemUsecase.clearOverdue(clearOverdueInfoDTO);
        return ResponseEntity.ok(rentalResultOutputDTO);
    }
}
