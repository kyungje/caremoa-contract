package com.caremoa.contract.domain;

import com.caremoa.contract.domain.enum4dom.ContractStatus;
import com.caremoa.contract.domain.enum4dom.DeleteYn;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@Getter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_id")
    private Long id;

    private Long memberId;

    private String memberName;

    private Long helperId;

    private String helperName;

    private String helperJobType;

    private String targetName;

    private Long expense;

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    @Enumerated(EnumType.STRING)
    private DeleteYn deleteYn;

    private String location;

    private String careRange;

    private String memberPhoneNumber;

    private String helperPhoneNumber;

    @OneToMany(mappedBy = "contract")
    private List<ContractDetail> contractDetailList = new ArrayList<>();

    @Builder
    public Contract(Long memberId, String memberName, Long helperId, String helperName, String helperJobType, String targetName, Long expense, ContractStatus contractStatus, DeleteYn deleteYn, String location, String careRange, String memberPhoneNumber, String helperPhoneNumber) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.helperId = helperId;
        this.helperName = helperName;
        this.helperJobType = helperJobType;
        this.targetName = targetName;
        this.expense = expense;
        this.contractStatus = contractStatus;
        this.deleteYn = deleteYn;
        this.location = location;
        this.careRange = careRange;
        this.memberPhoneNumber = memberPhoneNumber;
        this.helperPhoneNumber = helperPhoneNumber;
    }
}
