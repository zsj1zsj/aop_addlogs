package com.example.aoptest.aop;

public class SaveOrderConvert implements Convert<SaveOrder> {

    @Override
    public OperateLogDo convert(SaveOrder o) {
        OperateLogDo logDo = new OperateLogDo();
        logDo.setOrderId(o.getId());
        return logDo;
    }
}
