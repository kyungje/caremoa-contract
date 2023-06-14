package com.caremoa.contract.service;

import com.caremoa.contract.adapter.ContractAccepted;
import com.caremoa.contract.adapter.ContractEnded;
import com.caremoa.contract.adapter.KafkaProducer;
import com.caremoa.contract.domain.Contract;
import com.caremoa.contract.dto.ContractDto;
import com.caremoa.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ContractService {
    private final ContractRepository contractRepository;
    private final KafkaProducer kafkaProducer;

    public ContractDto.ContractRes findContractInfo(ContractDto.ContractReq condition){

        Contract contract = contractRepository.findById(condition.getContractId())
                .orElseThrow(()->new RuntimeException("Can't find the contract Id"));

        return toContractRes(contract);
    }

    public ContractDto.ContractRes acceptContract(ContractDto.ContractReq condition){
        ContractAccepted contractAccepted = ContractAccepted.builder()
                .contractId(condition.getContractId())
                .build();

        kafkaProducer.sendMessage(contractAccepted);

        return ContractDto.ContractRes.builder()
                .contractId(condition.getContractId())
                .build();
    }

    private ContractDto.ContractRes toContractRes(Contract contract){
        return ContractDto.ContractRes.builder()
                .contractId(contract.getId())
                .memberId(contract.getMemberId())
                .memberName(contract.getMemberName())
                .helperId(contract.getHelperId())
                .helperName(contract.getHelperName())
                .helperJobType(contract.getHelperJobType())
                .targetName(contract.getTargetName())
                .build();
    }

}