package com.caremoa.contract.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ClaimCompleted extends AbstractEvent{
    private Long contractId; // -- ID\

    @Builder
    public ClaimCompleted(Long contractId) {
        super();
        this.contractId = contractId;
    }
}
