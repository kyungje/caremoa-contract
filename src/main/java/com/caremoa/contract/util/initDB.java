package com.caremoa.contract.util;

import com.caremoa.contract.domain.Contract;
import com.caremoa.contract.domain.enum4dom.ContractStatus;
import com.caremoa.contract.domain.enum4dom.DeleteYn;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;


@Profile({"local"})
@Component
@RequiredArgsConstructor
@Slf4j
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init() {

        try {
            initService.dbInit();
        } catch (Exception e) {
            log.info("Contract has already been made in Table.");
        }
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager entityManager;

        public void dbInit() throws Exception {

            Contract contract = Contract.builder()
                    .memberId(1L)
                    .memberName("testMember")
                    .helperId(1L)
                    .helperName("testHelper")
                    .helperJobType("1Type")
                    .targetName("testTarget")
                    .expense(1000L)
                    .contractStatus(ContractStatus.CREATED)
                    .deleteYn(DeleteYn.N)
                    .location("testLoc")
                    .careRange("testCare")
                    .memberPhoneNumber("testMemberPhone")
                    .helperPhoneNumber("testHelperPhone")
                    .build();

            entityManager.persist(contract);

        }
    }


}
