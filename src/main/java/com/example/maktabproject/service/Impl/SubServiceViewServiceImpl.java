package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.view.SubServiceView;
import com.example.maktabproject.repository.SubServiceViewRepository;
import com.example.maktabproject.service.SubServiceViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubServiceViewServiceImpl implements SubServiceViewService {

    private final SubServiceViewRepository repository;

    @Override
    public List<SubServiceView> loadAll() {
        return repository.findAll();
    }
}
