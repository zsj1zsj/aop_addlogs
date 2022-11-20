package com.example.aoptest.aop;

public class UpdateOrderConvert implements Convert<UpdateOrder>{
    @Override
    public OperateLogDo convert(UpdateOrder updateOrder) {
        OperateLogDo logDo = new OperateLogDo();
        logDo.setOrderId(updateOrder.getOrderId());
        return logDo;
    }
}