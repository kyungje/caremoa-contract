package com.caremoa.contract.repository;

import com.caremoa.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Override
    List<Contract> findAll();

    @Override
    Optional<Contract> findById(Long id);

    @Override
    Contract save(Contract contract);
}
