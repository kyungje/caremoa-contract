package com.caremoa.contract.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;

import java.util.function.Supplier;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PolicyHandler {

    private long errorOccur = 0;

    @Bean
    public Supplier<ContractEnded> producer() {
        return () -> new ContractEnded(1L);
    }

    @Bean
    Consumer<Map<String, Object>> consumer() {
        System.out.println("0");

        return mapData -> {
            ObjectMapper mapper = new ObjectMapper();
            log.debug(mapData.toString());

            switch (mapData.get("eventType").toString()) {

                case "ClaimCreated": // 클레임이 생성될 때
                    ClaimCreated claimCreated = mapper.convertValue(mapData, ClaimCreated.class);
                    System.out.println("1");
                    log.debug("claimCreated : {}", claimCreated.toString());
                    break;

                case "ClaimCompleted": // 클레임이 완료될 때
                    ClaimCompleted claimCompleted = mapper.convertValue(mapData, ClaimCompleted.class);
                    System.out.println("2");
                    log.debug("claimCompleted : {}", claimCompleted.toString());
                    break;

                default: // 처리가 정의되지 않은 이벤트
                    log.debug("Undefined EventType : {}", mapData.get("eventType").toString());
                    System.out.println("3");
                    break;
            }
        };
    }

    @Bean
    Consumer<ErrorMessage> KafkaErrorHandler() {
        return e -> {
            errorOccur++;
            log.error("에러 발생: {}, 횟수: {}", e, errorOccur);
        };
    }
}
