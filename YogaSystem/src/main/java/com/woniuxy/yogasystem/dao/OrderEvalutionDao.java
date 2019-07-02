package com.woniuxy.yogasystem.dao;

import com.woniuxy.yogasystem.pojo.Order_Evaluation;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.provider.OrderFormProvider;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderEvalutionDao {

    /**
     * 查询订单评论
     * 先到order_form中查出所有的订单，再根据订单查询评论
     */
    @SelectProvider(type = OrderFormProvider.class,method = "selectOrder")
    public List<Order_Form> findOrder(Order_Form order);

    @Select("select * from order_evaluation where id=#{id} and flag=0")
    public Order_Evaluation selectEvaluation(String id);

    @Select("select * from order_evaluation where id=#{id}")
    public Order_Evaluation selectEval(String id);

    /**
     * 添加评论
     * @param evaluation
     */
    @Insert("insert into order_evaluation(id,level,detail) values(#{id},#{level},#{detail})")
    public void addEvaluation(Order_Evaluation evaluation);

    /**
     * 在订单表中添加评论id
     * @param number
     */
    @Update("update order_form set order_eva_id=#{number} where number=#{number}")
    public void addEidToOrderFrom(String number);

    /**
     * 删除评论
     * 1、删除评论表中的评论,评论删除后无法评论
     * 2、修改订单中评论id为0
     * @param number
     */
    @Update("update order_form set order_eva_id=1 where number=#{number}")
    public void delEid(String number);

    @Delete("delete from order_evaluation where id=#{number}")
    public void delEvaluation(String number);
}
