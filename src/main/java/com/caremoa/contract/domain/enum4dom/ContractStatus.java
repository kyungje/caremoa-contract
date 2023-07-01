package com.caremoa.contract.domain.enum4dom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum ContractStatus {
    CREATED("생성")
    , SIGNED("서명")
    , HOLED("보류")
    , PAID("결제")
    , REJECTED("거부")
    , CLAIMED("클레임")
    , COMPLETED("완료")
    , CANCELED("취소");

    private final String description;

    public static ContractStatus getAContractStatus(String contractStatusStr) {
        ContractStatus contractStatus = StringUtils.hasText(contractStatusStr) ?
                ContractStatus.valueOf(contractStatusStr) : null;
        return contractStatus;
    }

}
