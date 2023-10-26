package com.example.maktabproject.service.Impl;

import com.example.maktabproject.exception.MainServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceNotFoundException;
import com.example.maktabproject.exception.SubServiceTwoMainServiceException;
import com.example.maktabproject.model.MainService;
import com.example.maktabproject.model.SubService;
import com.example.maktabproject.repository.SubServiceRepository;
import com.example.maktabproject.service.SubServiceService;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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

        try{
            return subServiceRepository.save(subService);
        } catch (ConstraintViolationException | DataAccessException e){
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(SubService subService) {

        subServiceRepository.delete(subService);
    }

    @Override
    public SubService findById(Long id) throws SubServiceNotFoundException {

        return subServiceRepository.findById(id).orElseThrow(
                SubServiceNotFoundException::new
        );
    }

    @Override
    public List<SubService> findAll() {

        return subServiceRepository.findAll();
    }

    @Override
    public SubService addMainService(Long subServiceId, Long mainServiceId) throws SubServiceNotFoundException, SubServiceTwoMainServiceException, MainServiceNotFoundException {

        SubService subService = findById(subServiceId);

        if(subService.getMainService() != null)
            throw new SubServiceTwoMainServiceException();

        MainService mainService = mainServiceService.findById(mainServiceId);

        subService.setMainService(mainService);
        return register(subService);
    }

    @Override
    public SubService removeMainService(Long subServiceId) throws SubServiceNotFoundException, MainServiceNotFoundException {

        SubService subService = findById(subServiceId);

        if(subService.getMainService() == null)
            throw new MainServiceNotFoundException();

        subService.setMainService(null);
        return register(subService);
    }

    @Override
    public List<SubService> findByMainService(Long mainServiceId) throws MainServiceNotFoundException {

        MainService mainService = mainServiceService.findById(mainServiceId);

        return subServiceRepository.findAllByMainService(mainService);
    }

    @Override
    public SubService editPrice(Long subServiceId, Double newBasePrice) throws SubServiceNotFoundException {

        SubService subService = findById(subServiceId);
        subService.setBasePrice(newBasePrice);

        return register(subService);
    }

    @Override
    public SubService editComment(Long subServiceId, String newComment) throws SubServiceNotFoundException {

        SubService subService = findById(subServiceId);
        subService.setComment(newComment);

        return register(subService);
    }
}
