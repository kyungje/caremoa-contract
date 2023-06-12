package com.caremoa.contract.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ContractDto {
    @Data
    @NoArgsConstructor
    public static class ContractReq {
        Long contractId;
        Long memberId;
        String memberName;
        Long helperId;
        String helperName;
        String helperJobType;
        String targetName;

        @Builder
        public ContractReq(Long contractId, Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName) {
            this.contractId = contractId;
            this.memberId = memberId;
            this.memberName = memberName;
            this.helperId = helperId;
            this.helperName = helperName;
            this.helperJobType = helperJobType;
            this.targetName = targetName;
        }
    }

    @Data
    @NoArgsConstructor
    public static class ContractRes {
        Long contractId;
        Long memberId;
        String memberName;
        Long helperId;
        String helperName;
        String helperJobType;
        String targetName;

        @Builder
        public ContractRes(Long contractId, Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName) {
            this.contractId = contractId;
            this.memberId = memberId;
            this.memberName = memberName;
            this.helperId = helperId;
            this.helperName = helperName;
            this.helperJobType = helperJobType;
            this.targetName = targetName;
        }
    }
}
