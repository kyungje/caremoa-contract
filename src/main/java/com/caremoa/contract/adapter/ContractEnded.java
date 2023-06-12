package com.caremoa.contract.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ContractEnded extends AbstractEvent{
    private Long contractId; // -- ID\

    @Builder
    public ContractEnded(Long contractId) {
        super();
        this.contractId = contractId;
    }
}