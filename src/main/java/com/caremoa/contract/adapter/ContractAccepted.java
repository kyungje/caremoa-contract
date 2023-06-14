package com.caremoa.contract.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractAccepted extends AbstractEvent{
    private Long contractId; // -- ID\

    @Builder
    public ContractAccepted(Long contractId) {
        super();
        this.contractId = contractId;
    }
}