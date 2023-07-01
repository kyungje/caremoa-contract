package com.caremoa.contract.dto;

import com.caremoa.contract.domain.enum4dom.ContractStatus;
import com.caremoa.contract.domain.enum4dom.DeleteYn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
        ContractStatus contractStatus;
        DeleteYn deleteYn;

        @Builder
        public ContractReq(Long contractId, Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName, ContractStatus contractStatus, DeleteYn deleteYn) {
            this.contractId = contractId;
            this.memberId = memberId;
            this.memberName = memberName;
            this.helperId = helperId;
            this.helperName = helperName;
            this.helperJobType = helperJobType;
            this.targetName = targetName;
            this.contractStatus = contractStatus;
            this.deleteYn = deleteYn;
        }
    }

    @Data
    @NoArgsConstructor
    public static class ContractResList {
        List<ContractRes> contractResList;

        @Builder
        public ContractResList(List<ContractRes> contractResList) {
            this.contractResList = contractResList;
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
        ContractStatus contractStatus;
        DeleteYn deleteYn;
        String careRange;


        @Builder
        public ContractRes(Long contractId, Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName, ContractStatus contractStatus, DeleteYn deleteYn, String careRange) {
            this.contractId = contractId;
            this.memberId = memberId;
            this.memberName = memberName;
            this.helperId = helperId;
            this.helperName = helperName;
            this.helperJobType = helperJobType;
            this.targetName = targetName;
            this.contractStatus = contractStatus;
            this.deleteYn = deleteYn;
            this.careRange = careRange;
        }
    }

    @Data
    @NoArgsConstructor
    public static class PaymentReq {
        Long contractId;
        Long memberId;
        String memberName;
        Long helperId;
        String helperName;
        Long requestAmount;

        @Builder
        public PaymentReq(Long contractId, Long memberId, String memberName, Long helperId, String helperName, Long requestAmount) {
            this.contractId = contractId;
            this.memberId = memberId;
            this.memberName = memberName;
            this.helperId = helperId;
            this.helperName = helperName;
            this.requestAmount = requestAmount;
        }
    }
}
