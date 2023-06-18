package com.caremoa.contract.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractAccepted extends AbstractEvent{
    private Long memberId;
    private String memberName;
    private Long helperId;
    private String helperName;
    private String helperJobType;
    private String targetName;
    private String careRange;
    private Long expense;
    private String location;
    private String helperPhoneNumber;
    private String memberPhoneNumber;

    @Builder
    public ContractAccepted(Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName, String careRange, Long expense, String location) {
        super();
        this.memberId = memberId;
        this.memberName = memberName;
        this.helperId = helperId;
        this.helperName = helperName;
        this.helperJobType = helperJobType;
        this.targetName = targetName;
        this.careRange = careRange;
        this.expense = expense;
        this.location = location;
    }
}