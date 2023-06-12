package com.caremoa.contract.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class AbstractEvent {
    private String eventType;
    private String timestamp;

    public AbstractEvent(){
        // 정의한 클래스명이 들어감
        this.setEventType(this.getClass().getSimpleName());
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }

    public String toJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }

    public boolean validate(){
        // 정의한 클래스명의 Subscription인지 확인
        return getEventType().equals(getClass().getSimpleName());
    }
}
