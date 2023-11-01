package com.example.maktabproject.controller;

import com.example.maktabproject.dto.OfferMapper;
import com.example.maktabproject.dto.OfferRequestDto;
import com.example.maktabproject.dto.OfferResponseDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.service.Impl.OfferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferServiceImpl offerService;
    private final OfferMapper offerMapper;

    @PostMapping("/submit")
    public ResponseEntity<OfferResponseDto> submitOffer(@RequestBody OfferRequestDto offerRequestDto) throws ExpertNotFoundException, OrderNotFoundException, InvalidPriceException, InvalidTimeException {

        Offer offer = offerMapper.dtoToOffer(offerRequestDto);
        OfferResponseDto offerResponseDto = offerMapper.offerToDto(offerService.register(offer));
        return new ResponseEntity<>(offerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/findByScore")
    public ResponseEntity<List<Offer>> findOfferByScore(@RequestParam Long customerId) throws CustomerNotFoundException {

        return new ResponseEntity<>(offerService.findByCustomerScoreOrder(customerId),HttpStatus.OK);
    }

    @GetMapping("/findByPrice")
    public ResponseEntity<List<Offer>> findOfferByPrice(@RequestParam Long customerId) throws CustomerNotFoundException {

        return new ResponseEntity<>(offerService.findByCustomerPriceOrder(customerId),HttpStatus.OK);
    }
}
