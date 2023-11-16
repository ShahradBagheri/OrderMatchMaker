package com.example.maktabproject.controller;

import com.example.maktabproject.dto.OfferMapper;
import com.example.maktabproject.dto.OfferRequestDto;
import com.example.maktabproject.dto.OfferResponseDto;
import com.example.maktabproject.exception.*;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OfferServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferServiceImpl offerService;
    private final OfferMapper offerMapper;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<OfferResponseDto> submitOffer(@RequestBody OfferRequestDto offerRequestDto) throws ExpertNotFoundException, OrderNotFoundException, InvalidPriceException, InvalidTimeException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        Offer offer = offerMapper.dtoToOffer(offerRequestDto);
        offer.setExpert(expert);
        OfferResponseDto offerResponseDto = offerMapper.offerToDto(offerService.register(offer));
        return new ResponseEntity<>(offerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/findAllByScore")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OfferResponseDto>> findAllOfferByScore() throws CustomerNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long customerId = customerService.findByUsername(username).getId();

        return new ResponseEntity<>(offerService.findByCustomerScoreOrder(customerId).stream().map(offerMapper::offerToDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/findAllByPrice")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OfferResponseDto>> findAllOfferByPrice() throws CustomerNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long customerId = customerService.findByUsername(username).getId();

        return new ResponseEntity<>(offerService.findByCustomerPriceOrder(customerId).stream().map(offerMapper::offerToDto).toList(), HttpStatus.OK);
    }
}
