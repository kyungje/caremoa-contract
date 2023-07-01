package com.caremoa.contract.service;

import com.caremoa.contract.adapter.ContractCompleted;
import com.caremoa.contract.adapter.KafkaProducer;
import com.caremoa.contract.domain.Contract;
import com.caremoa.contract.domain.enum4dom.ContractStatus;
import com.caremoa.contract.dto.ContractDto;
import com.caremoa.contract.feign.PaymentFeign;
import com.caremoa.contract.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ContractService {
    private final ContractRepository contractRepository;
    private final KafkaProducer kafkaProducer;
    private final PaymentFeign paymentFeign;

    public List<ContractDto.ContractRes> findContractAll(){

        List<Contract> contractList = contractRepository.findAll();

        return contractList.stream().map(data -> toContractRes(data)).collect(Collectors.toList());
    }

    public Contract findContractById(ContractDto.ContractReq condition){

        Contract contract = contractRepository.findById(condition.getContractId())
                .orElseThrow(()->new RuntimeException("Can't find the contract Id"));

        return contract;
    }

    @Transactional
    public ResponseEntity acceptContract(ContractDto.ContractReq condition){
        Contract contract = findContractById(condition);

        contract.changeContractStatus(condition.getContractStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity rejectContract(ContractDto.ContractReq condition){
        Contract contract = findContractById(condition);

        contract.changeContractStatus(condition.getContractStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity claimContract(ContractDto.ContractReq condition){
        Contract contract = findContractById(condition);

        contract.changeContractStatus(condition.getContractStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity payContract(ContractDto.ContractReq condition){
        Contract contract = findContractById(condition);

        //결제 모듈 호출 feign
        ResponseEntity responseEntity = paymentFeign.postPayment(toPaymentReq(contract));
        log.info("payment responseEntity", responseEntity);
        contract.changeContractStatus(condition.getContractStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @Transactional
    public ResponseEntity endContract(ContractDto.ContractReq condition){

        Contract contract = findContractById(condition);

        kafkaProducer.sendMessage(toContractEnded(contract));

        contract.changeContractStatus(condition.getContractStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public void saveContract(Contract contract){
        contractRepository.save(contract);
    }

    private ContractCompleted toContractEnded(Contract contract){
        return ContractCompleted.builder()
                .contractId(contract.getId())
                .memberId(contract.getMemberId())
                .memberName(contract.getMemberName())
                .helperId(contract.getHelperId())
                .helperName(contract.getHelperName())
                .helperJobType(contract.getHelperJobType())
                .targetName(contract.getTargetName())
                .contractStatus(ContractStatus.COMPLETED)
                .careRange(contract.getCareRange())
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
                .contractStatus(contract.getContractStatus())
                .deleteYn(contract.getDeleteYn())
                .careRange(contract.getCareRange())
                .build();
    }

    private ContractDto.PaymentReq toPaymentReq(Contract contract) {
        return ContractDto.PaymentReq.builder()
                .contractId(contract.getId())
                .memberId(contract.getMemberId())
                .memberName(contract.getMemberName())
                .helperId(contract.getHelperId())
                .helperName(contract.getHelperName())
                .requestAmount(contract.getExpense())
                .build();
    }

}
