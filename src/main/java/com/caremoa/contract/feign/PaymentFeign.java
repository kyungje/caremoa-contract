package com.caremoa.contract.feign;

import com.caremoa.contract.dto.ContractDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "paymentFeign", url = "${prop.feign.url}")
public interface PaymentFeign {
    @PostMapping(value = "/payments", produces = "application/json", consumes = "application/json")
    ResponseEntity postPayment(ContractDto.PaymentReq paymentReq);
}
