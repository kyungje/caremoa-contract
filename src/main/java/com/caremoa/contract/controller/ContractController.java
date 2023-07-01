package com.caremoa.contract.controller;

import com.caremoa.contract.dto.ContractDto;
import com.caremoa.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@Slf4j
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/contract/all")
    public List<ContractDto.ContractRes> findContractList() {

        List<ContractDto.ContractRes> contractlist = contractService.findContractAll();

        return contractlist;
    }

    @PutMapping("/contract/accept")
    public ResponseEntity acceptContract(@RequestBody ContractDto.ContractReq request) {

        return contractService.acceptContract(request);
    }

    @PutMapping("/contract/reject")
    public ResponseEntity rejectContract(@RequestBody ContractDto.ContractReq request) {

        return contractService.rejectContract(request);
    }

    @PutMapping("/contract/pay")
    public ResponseEntity payContract(@RequestBody ContractDto.ContractReq request) {

        return contractService.payContract(request);
    }

    @PutMapping("/contract/claim")
    public ResponseEntity claimContract(@RequestBody ContractDto.ContractReq request) {

        return contractService.claimContract(request);
    }

    @PutMapping("/contract/end")
    public ResponseEntity endContract(@RequestBody ContractDto.ContractReq request) {

        return contractService.endContract(request);
    }
}
