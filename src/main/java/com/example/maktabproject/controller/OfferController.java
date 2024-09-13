package com.example.maktabproject.controller;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.Customer;
import com.example.maktabproject.model.dto.offer.OfferMapper;
import com.example.maktabproject.model.dto.offer.OfferRequestDto;
import com.example.maktabproject.model.dto.offer.OfferResponseDto;
import com.example.maktabproject.model.Expert;
import com.example.maktabproject.model.Offer;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.service.Impl.CustomerServiceImpl;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import com.example.maktabproject.service.Impl.OfferServiceImpl;
import com.example.maktabproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferServiceImpl offerService;
    private final OfferMapper offerMapper;
    private final ExpertServiceImpl expertService;
    private final CustomerServiceImpl customerService;
    private final OrderService orderService;

    @PostMapping("/submit")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<OfferResponseDto> submitOffer(@RequestBody OfferRequestDto offerRequestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Expert expert = expertService.findByUsername(username);

        if(expert.getExpertStatus() != ExpertStatus.APPROVED)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        Offer offer = offerMapper.dtoToOffer(offerRequestDto);
        offer.setExpert(expert);
        OfferResponseDto offerResponseDto = offerMapper.offerToDto(offerService.register(offer));
        return new ResponseEntity<>(offerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/findAllByScore")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OfferResponseDto>> findAllOfferByScore() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long customerId = customerService.findByUsername(username).getId();

        return new ResponseEntity<>(offerService.findByCustomerScoreOrder(customerId).stream().map(offerMapper::offerToDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/findAllByPrice")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<OfferResponseDto>> findAllOfferByPrice() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long customerId = customerService.findByUsername(username).getId();

        return new ResponseEntity<>(offerService.findByCustomerPriceOrder(customerId).stream().map(offerMapper::offerToDto).toList(), HttpStatus.OK);
    }

    @PostMapping("/selectOffer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void selectOffer(@RequestParam Long offerId, @RequestParam Long orderId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findByUsername(username);

        if (!Objects.equals(orderService.findById(orderId).getCustomer().getId(), customer.getId()))
            throw new CustomExceptions.NotOrderOwnerException("you dont own this order!");

        orderService.choseOffer(offerId, orderId);
    }
}
