package com.example.maktabproject.controller;

import com.example.maktabproject.dto.ExpertMapper;
import com.example.maktabproject.dto.ExpertResponseDto;
import com.example.maktabproject.exception.ExpertNotFoundException;
import com.example.maktabproject.service.Impl.ExpertServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {

    private final ExpertServiceImpl expertService;
    private final ExpertMapper expertMapper;

    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('EXPERT')")
    public ResponseEntity<ExpertResponseDto> changePassword(@RequestParam String newPassword) throws ExpertNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long expertId = expertService.findByUsername(username).getId();

        return new ResponseEntity<>(expertMapper.expertToDto(expertService.changePassword(expertId, newPassword)), HttpStatus.OK);
    }

    @GetMapping("/checkScore")
    @PreAuthorize("hasRole('EXPERT')")
    public float checkScore() throws ExpertNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long expertId = expertService.findByUsername(username).getId();

        return expertService.findById(expertId).getScore();
    }
}
