package com.example.aoptest.aop;

import lombok.Data;

@Data
public class OperateLogDo {
    private Long orderId;

    private String desc;

    private String result;
}
