package com.woniuxy.yogasystem.service.imp;

import com.woniuxy.yogasystem.dao.OrderEvalutionDao;
import com.woniuxy.yogasystem.pojo.Order_Evaluation;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.service.OrderEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("orderEvaluationService")
public class OrderEvaluationServiceImp  implements OrderEvaluationService{

    @Autowired
    private OrderEvalutionDao evalutionDao;


    @Override
    public Order_Evaluation selectEval(String id) {
        return evalutionDao.selectEval(id);
    }

    @Override
    public List<Order_Form> findOrder(Order_Form order) {
        return evalutionDao.findOrder(order);
    }

    @Override
    public Order_Evaluation selectEvaluation(String id) {
        return evalutionDao.selectEvaluation(id);
    }

    @Override
    public void addEvaluation(Order_Evaluation evaluation) {
        evalutionDao.addEvaluation(evaluation);
    }

    @Override
    public void delEid(String number) {
        evalutionDao.delEid(number);
    }

    @Override
    public void addEidToOrderFrom(String order) {
        evalutionDao.addEidToOrderFrom(order);
    }

    @Override
    public void delEvaluation(String id) {
        evalutionDao.delEvaluation(id);
    }
}
