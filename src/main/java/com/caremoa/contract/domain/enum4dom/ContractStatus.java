package com.caremoa.contract.domain.enum4dom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@RequiredArgsConstructor
public enum ContractStatus {
    CREATED("생성됨")
    , SIGNED("서명")
    , HOLED("보류됨")
    , REJECTED("거부됨")
    , COMPLETED("완료됨")
    , CANCELED("취소됨");

    private final String description;

    public static ContractStatus getAContractStatus(String contractStatusStr) {
        ContractStatus contractStatus = StringUtils.hasText(contractStatusStr) ?
                ContractStatus.valueOf(contractStatusStr) : null;
        return contractStatus;
    }

}
