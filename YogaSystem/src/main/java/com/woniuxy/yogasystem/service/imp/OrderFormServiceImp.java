package com.woniuxy.yogasystem.service.imp;

import com.woniuxy.yogasystem.dao.OrderFormDao;
import com.woniuxy.yogasystem.pojo.Order_Evaluation;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("orderFormService")
public class OrderFormServiceImp implements OrderFormService {

    @Autowired
    private OrderFormDao orderFormDao;

    @Override
    public List<Order_Form> selectById(Order_Form order) {
        return orderFormDao.selectById(order);
    }

    @Override
    public void coachAddOrder(Order_Form order_form) {
        orderFormDao.coachAddOrder(order_form);
    }

    @Override
    public void addOrderEvaluation(Order_Evaluation order_evaluation) {
        orderFormDao.addOrderEvaluation(order_evaluation);
    }

    @Override
    public Order_Evaluation selectEvaluation(Integer id) {
        return orderFormDao.selectEvaluation(id);
    }

    @Override
    public void updateOrderStatus(Order_Form order_form) {
        orderFormDao.updateOrderStatus(order_form);
    }
}
