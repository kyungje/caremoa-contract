package com.caremoa.contract.adapter;

import com.caremoa.contract.domain.Contract;
import com.caremoa.contract.domain.enum4dom.ContractStatus;
import com.caremoa.contract.domain.enum4dom.DeleteYn;
import com.caremoa.contract.service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;

import java.util.function.Consumer;

import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {

    private long errorOccur = 0;
    private final ContractService contractService;

    @Bean
    Consumer<String> consumer() {

        return data -> {
            ObjectMapper mapper = new ObjectMapper();
            log.info("mapData: {}",data.toString());

            Map<String, Object> mapData = null;

            try {
                mapData = jsonToMap(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            switch (mapData.get("eventType").toString()) {
                /* Example
                {"eventType":"ContractAccepted","memberId":3,"memberName":"testMember3","helperId":3,"helperName":"testHelper3","helperJobType":"1Type3","targetName":"testTarget2","careRange":"testCare2","expense":1000,"location":"서울","helperPhoneNumber":"1234","memberPhoneNumber":"2345"}
                 */
                case "ContractAccepted":
                    ContractAccepted contractAccepted = mapper.convertValue(mapData, ContractAccepted.class);
                    log.info("contractAccepted : {}", contractAccepted.toString());
                    contractService.saveContract(toContract(contractAccepted));
                    break;
                default: // 처리가 정의되지 않은 이벤트
                    log.debug("Undefined EventType : {}", mapData.get("eventType").toString());
                    break;
            }
        };
    }

    private Map<String, Object> jsonToMap(String result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> returnMap = mapper.readValue(result, Map.class);
        return returnMap;
    }

    private Contract toContract(ContractAccepted contractAccepted){
        return Contract.builder()
                .memberId(contractAccepted.getMemberId())
                .memberName(contractAccepted.getMemberName())
                .helperId(contractAccepted.getHelperId())
                .helperName(contractAccepted.getHelperName())
                .helperJobType(contractAccepted.getHelperJobType())
                .targetName(contractAccepted.getTargetName())
                .contractStatus(ContractStatus.CREATED)
                .deleteYn(DeleteYn.N)
                .careRange(contractAccepted.getCareRange())
                .expense(contractAccepted.getExpense())
                .location(contractAccepted.getLocation())
                .helperPhoneNumber(contractAccepted.getHelperPhoneNumber())
                .memberPhoneNumber(contractAccepted.getMemberPhoneNumber())
                .build();
    }

    @Bean
    Consumer<ErrorMessage> KafkaErrorHandler() {
        return e -> {
            errorOccur++;
            log.error("에러 발생: {}, 횟수: {}", e, errorOccur);
        };
    }
}
