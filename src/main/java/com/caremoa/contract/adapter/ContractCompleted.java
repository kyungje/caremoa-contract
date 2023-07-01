package com.caremoa.contract.adapter;

import com.caremoa.contract.domain.enum4dom.ContractStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractCompleted extends AbstractEvent{
    private Long contractId; // -- ID\
    private Long memberId;
    private String memberName;
    private Long helperId;
    private String helperName;
    private String helperJobType;
    private String targetName;
    private String careRange;
    private ContractStatus contractStatus;

    @Builder
    public ContractCompleted(Long contractId, Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName, String careRange, ContractStatus contractStatus) {
        super();
        this.contractId = contractId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.helperId = helperId;
        this.helperName = helperName;
        this.helperJobType = helperJobType;
        this.targetName = targetName;
        this.careRange = careRange;
        this.contractStatus = contractStatus;
    }
}