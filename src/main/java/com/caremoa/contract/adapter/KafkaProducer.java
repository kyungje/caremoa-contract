package com.caremoa.contract.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final StreamBridge streamBridge;

    public void sendMessage(AbstractEvent abstractEvent){
        log.info("sendMessage: {}" , abstractEvent);
        streamBridge.send("producer-out-0", abstractEvent);
    }
}
