package com.example.aoptest.aop;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @RecordOperate(desc = "保存订单", convert = SaveOrderConvert.class)
    public Boolean saveOrder(SaveOrder saveOrder){
        System.out.println("save order " + saveOrder.getId());
        return true;
    }

    @RecordOperate(desc ="更新订单", convert = UpdateOrderConvert.class)
    public Boolean updateOrder(UpdateOrder updateOrder){
        System.out.println("update order " + updateOrder.getOrderId());
        return true;
    }
}
