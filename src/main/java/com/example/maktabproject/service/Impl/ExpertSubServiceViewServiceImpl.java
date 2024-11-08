package com.example.maktabproject.service.Impl;

import com.example.maktabproject.model.view.ExpertSubServiceView;
import com.example.maktabproject.repository.ExpertSubServiceViewRepository;
import com.example.maktabproject.service.ExpertSubServiceViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpertSubServiceViewServiceImpl implements ExpertSubServiceViewService {

    private final ExpertSubServiceViewRepository expertSubServiceViewRepository;

    @Override
    public List<ExpertSubServiceView> findAll() {
        return expertSubServiceViewRepository.findAll();
    }
}
