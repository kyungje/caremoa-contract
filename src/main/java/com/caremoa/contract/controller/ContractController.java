package com.caremoa.contract.controller;

import com.caremoa.contract.dto.ContractDto;
import com.caremoa.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@Slf4j
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/contract")
    public ContractDto.ContractRes findContractListByCond(@RequestBody ContractDto.ContractReq request) {

        ContractDto.ContractRes contractInfo = contractService.findContractInfo(request);

        return contractInfo;
    }

    @PutMapping("/contract/accept")
    public ContractDto.ContractRes endContract(@RequestBody ContractDto.ContractReq request) {

        ContractDto.ContractRes contractInfo = contractService.acceptContract(request);

        return contractInfo;
    }
}
