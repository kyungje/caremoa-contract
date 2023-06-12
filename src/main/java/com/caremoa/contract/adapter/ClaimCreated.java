package com.caremoa.contract.adapter;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class ClaimCreated extends AbstractEvent{
    private Long contractId; // -- ID\

    @Builder
    public ClaimCreated(Long contractId) {
        super();
        this.contractId = contractId;
    }
}
