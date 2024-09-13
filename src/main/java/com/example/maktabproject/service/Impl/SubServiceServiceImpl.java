package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.CustomExceptions;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.repository.SubServiceRepository;
import com.example.maktabproject.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final MainServiceServiceImpl mainServiceService;

    @Override
    public SubService register(SubService subService) {

        return subServiceRepository.save(subService);
    }

    @Override
    public void delete(SubService subService) {

        subServiceRepository.delete(subService);
    }

    @Override
    public SubService findById(Long id) {

        return subServiceRepository.findById(id).orElseThrow(
                () -> new CustomExceptions.SubServiceNotFoundException("subService not found!")
        );
    }

    @Override
    public List<SubService> findAll() {

        return subServiceRepository.findAll();
    }

    @Override
    public SubService addMainService(Long subServiceId, Long mainServiceId) {

        SubService subService = findById(subServiceId);

        if (subService.getMainService() != null)
            throw new CustomExceptions.SubServiceTwoMainServiceException("subService cant have two mainServices!");

        MainService mainService = mainServiceService.findById(mainServiceId);

        subService.setMainService(mainService);
        return register(subService);
    }

    @Override
    public SubService removeMainService(Long subServiceId) {

        SubService subService = findById(subServiceId);

        if (subService.getMainService() == null)
            throw new CustomExceptions.MainServiceNotFoundException("mainService not found!");

        subService.setMainService(null);
        return register(subService);
    }

    @Override
    public List<SubService> findByMainService(Long mainServiceId) {

        MainService mainService = mainServiceService.findById(mainServiceId);

        return subServiceRepository.findAllByMainService(mainService);
    }

    @Override
    public List<SubService> findByExpertId(Long expertId) {
        return subServiceRepository.findAllByExperts_Id(expertId);
    }


}
