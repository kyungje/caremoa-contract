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

            Contract contract1 = Contract.builder()
                    .memberId(1L)
                    .memberName("송중기")
                    .helperId(1L)
                    .helperName("한소희")
                    .helperJobType("동물케어")
                    .targetName("토토리")
                    .expense(100000L)
                    .contractStatus(ContractStatus.CREATED)
                    .deleteYn(DeleteYn.N)
                    .location("서울,은평구")
                    .careRange("강아지산책")
                    .memberPhoneNumber("010-1234-5678")
                    .helperPhoneNumber("010-2345-6789")
                    .build();

            entityManager.persist(contract1);

            Contract contract2 = Contract.builder()
                    .memberId(2L)
                    .memberName("차은우")
                    .helperId(2L)
                    .helperName("아이유")
                    .helperJobType("동물케어")
                    .targetName("차이펑")
                    .expense(200022L)
                    .contractStatus(ContractStatus.COMPLETED)
                    .deleteYn(DeleteYn.N)
                    .location("경기,수원")
                    .careRange("고양이밥주기")
                    .memberPhoneNumber("010-4321-9876")
                    .helperPhoneNumber("010-9876-4321")
                    .build();

            entityManager.persist(contract2);

        }
    }


}
