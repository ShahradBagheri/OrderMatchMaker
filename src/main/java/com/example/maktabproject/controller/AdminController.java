package com.example.maktabproject.controller;

import com.example.maktabproject.model.*;
import com.example.maktabproject.model.dto.customer.CustomerMapper;
import com.example.maktabproject.model.dto.customer.CustomerResponseDto;
import com.example.maktabproject.model.dto.expert.ExpertMapper;
import com.example.maktabproject.model.dto.expert.ExpertResponseDto;
import com.example.maktabproject.model.dto.expertSubService.ExpertSubServiceViewMapper;
import com.example.maktabproject.model.dto.expertSubService.ExpertSubServiceViewResponseDto;
import com.example.maktabproject.model.dto.mainSevice.EditMainServiceRequestDto;
import com.example.maktabproject.model.dto.mainSevice.MainServiceMapper;
import com.example.maktabproject.model.dto.mainSevice.MainServiceRequestDto;
import com.example.maktabproject.model.dto.mainSevice.MainServiceResponseDto;
import com.example.maktabproject.model.dto.order.*;
import com.example.maktabproject.model.dto.subService.SubServiceEditRequestDto;
import com.example.maktabproject.model.dto.subService.SubServiceMapper;
import com.example.maktabproject.model.dto.subService.SubServiceRequestDto;
import com.example.maktabproject.model.dto.subService.SubServiceResponseDto;
import com.example.maktabproject.model.dto.user.UserFilterRequestDto;
import com.example.maktabproject.model.dto.user.UserMapper;
import com.example.maktabproject.model.dto.user.UserResponseDto;
import com.example.maktabproject.model.enums.ExpertStatus;
import com.example.maktabproject.model.view.ExpertSubServiceView;
import com.example.maktabproject.model.view.OrderView;
import com.example.maktabproject.service.AdminService;
import com.example.maktabproject.service.CustomerService;
import com.example.maktabproject.service.ExpertService;
import com.example.maktabproject.service.ExpertSubServiceViewService;
import com.example.maktabproject.service.Impl.MainServiceServiceImpl;
import com.example.maktabproject.service.Impl.SubServiceServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MainServiceServiceImpl mainServiceService;
    private final SubServiceServiceImpl subServiceService;
    private final MainServiceMapper mainServiceMapper;
    private final SubServiceMapper subServiceMapper;
    private final ExpertMapper expertMapper;
    private final ExpertSubServiceViewMapper expertSubServiceViewMapper;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ExpertSubServiceViewService expertSubServiceViewService;

    @GetMapping("/allMainService")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MainServiceResponseDto> getMainServices() {

        List<MainService> mainServiceList = mainServiceService.findAll();
        return mainServiceList.stream().map(mainServiceMapper::mainServiceToDto).toList();
    }

    @GetMapping("/allExperts")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ExpertResponseDto> getAllExperts(){
        List<Expert> experts = expertService.findAll();
        return experts.stream().map(expertMapper::expertToDto).toList();
    }

    @GetMapping("/allCustomer")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResponseDto> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        return customers.stream().map(customerMapper::customerToDto).toList();
    }

    @PostMapping("/register/mainService")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MainServiceResponseDto> registerMainService(@RequestBody MainServiceRequestDto mainServiceRequestDto) {

        MainService mainService = mainServiceService.register(mainServiceMapper.mainServiceDtoToMainService(mainServiceRequestDto));
        return new ResponseEntity<>(mainServiceMapper.mainServiceToDto(mainService), HttpStatus.OK);
    }

    @PostMapping("/remove/mainService/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeMainService(@PathVariable Long id){
        MainService mainService = mainServiceService.findById(id);
        mainServiceService.delete(mainService);
    }

    @PostMapping("/remove/subService/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void removeSubService(@PathVariable Long id){
        SubService subService = subServiceService.findById(id);
        subServiceService.delete(subService);
    }

    @PostMapping("/register/subService")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubServiceResponseDto> registerSubService(@RequestBody SubServiceRequestDto subServiceRequestDto) {

        SubService subService = subServiceMapper.subServiceDtoToSubService(subServiceRequestDto);
        return new ResponseEntity<>(subServiceMapper.subServiceToDto(subServiceService.register(subService)), HttpStatus.OK);
    }

    @PostMapping("/subService/editComment")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubServiceResponseDto> editSubServiceComment(@RequestParam Long subServiceId,
                                                                       @RequestParam String newComment) {

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(adminService.editSubServiceComment(subServiceId, newComment)), HttpStatus.OK);
    }

    @PostMapping("/subService/editBasePrice")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubServiceResponseDto> editSubServiceBasePrice(@RequestParam Long subServiceId,
                                                                         @RequestParam Double newBasePrice) {

        return new ResponseEntity<>(subServiceMapper.subServiceToDto(adminService.editSubServicePrice(subServiceId, newBasePrice)), HttpStatus.OK);
    }

    @PostMapping("/expert/addToSubService")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExpertResponseDto> addExpertToSubService(@RequestParam Long expertId,
                                                                   @RequestParam Long subServiceId) {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.addExpertSubService(expertId, subServiceId)), HttpStatus.OK);
    }

    @PostMapping("/expert/removeToSubService")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExpertResponseDto> removeExpertToSubService(@RequestParam Long expertId,
                                                                      @RequestParam Long subServiceId) {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.removeExpertSubService(expertId, subServiceId)), HttpStatus.OK);
    }

    @GetMapping("/expert/loadNotConfirm")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExpertResponseDto>> loadAllAWaitingConfirmationExperts(){
        List<ExpertResponseDto> notEnabledExperts = adminService.loadAWaitingConfirmationExperts()
                .stream()
                .map(expertMapper::expertToDto)
                .toList();
        return new ResponseEntity<>(notEnabledExperts, HttpStatus.OK);
    }

    @PostMapping("/expert/approveExpert")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExpertResponseDto> approveExpert(@RequestParam Long expertId) {

        return new ResponseEntity<>(expertMapper.expertToDto(adminService.updateExpertStatus(expertId, ExpertStatus.APPROVED)), HttpStatus.OK);
    }

    @PostMapping("/users/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> filterAllUsers(@RequestBody UserFilterRequestDto userFilterRequestDto) {

        List<User> users = adminService.filterUsers(userMapper.filterRequestToCriteriaDto(userFilterRequestDto));
        return new ResponseEntity<>(users.stream().map(userMapper::userToDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/orders/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> filterAllOrders(@RequestBody OrderFilterRequestDto orderFilterRequestDto) {

        List<Order> orders = adminService.filterOrders(orderMapper.dtoToCriteria(orderFilterRequestDto));
        return new ResponseEntity<>(orders.stream().map(orderMapper::orderToDto).toList(), HttpStatus.OK);
    }

    @PostMapping("/orderView/filter")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderViewResponseDto>> filterAllOrderViews(@RequestBody OrderViewFilterCriteriaDto orderViewFilterCriteriaDto) {

        List<OrderView> orders = adminService.filterOrderViews(orderViewFilterCriteriaDto);
        return new ResponseEntity<>(orders.stream().map(orderMapper::viewToDto).toList(), HttpStatus.OK);
    }

    @GetMapping("/customer/numberOfOrders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> customerOrderCount(@RequestParam Long customerId) {

        return new ResponseEntity<>(adminService.customerOrderSubmits(customerId), HttpStatus.OK);
    }

    @GetMapping("/expert/numberOfOrdersFinished")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> expertOrdersFinishedCount(@RequestParam Long expertId) {

        return new ResponseEntity<>(adminService.expertOrdersFinished(expertId), HttpStatus.OK);
    }

    @GetMapping("/customer/registrationDate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LocalDateTime> customerRegistrationDate(@RequestParam Long customerId) {

        return new ResponseEntity<>(adminService.customerSignedUpTime(customerId), HttpStatus.OK);
    }

    @GetMapping("/expert/registrationDate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LocalDateTime> expertRegistrationDate(@RequestParam Long expertId) {

        return new ResponseEntity<>(adminService.expertSignedUpTime(expertId), HttpStatus.OK);
    }

    @PostMapping("/mainService/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> editMainService(@RequestBody @Valid EditMainServiceRequestDto editMainServiceDto){
        MainService mainService = mainServiceService.findById(editMainServiceDto.id());
        mainService.setName(editMainServiceDto.name());
        mainServiceService.register(mainService);
        return new ResponseEntity<>(mainService.getId(), HttpStatus.OK);
    }

    @PostMapping("/subService/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> editMainService(@RequestBody @Valid SubServiceEditRequestDto subServiceEditRequestDto){
        SubService subService = subServiceService.findById(subServiceEditRequestDto.id());
        if(subServiceEditRequestDto.name() != null && !subServiceEditRequestDto.name().isBlank())
            subService.setName(subServiceEditRequestDto.name());
        if(subServiceEditRequestDto.basePrice() != null)
            subService.setBasePrice(subServiceEditRequestDto.basePrice());
        if(subServiceEditRequestDto.mainServiceId() != null){
            MainService mainService = mainServiceService.findById(subServiceEditRequestDto.mainServiceId());
            if(mainService != null)
                subService.setMainService(mainService);
        }
        subServiceService.register(subService);
        return new ResponseEntity<>(subService.getId(), HttpStatus.OK);
    }

    @GetMapping("/expertSubServiceView/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExpertSubServiceViewResponseDto>> filterAllOrderViews() {
        List<ExpertSubServiceView> expertSubServiceViews = expertSubServiceViewService.findAll();
        return new ResponseEntity<>(expertSubServiceViews.stream().map(expertSubServiceViewMapper::viewToDto).toList(), HttpStatus.OK);
    }
}
