package com.woniuxy.yogasystem.dao;

import com.woniuxy.yogasystem.pojo.Order_Evaluation;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.provider.OrderFormProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface OrderFormDao {
    /**
     * 查询订单信息
     * 按照tid、cid、vid查找
     * @param order
     * @return
     */
    @SelectProvider(type = OrderFormProvider.class,method = "selectOrder")
    public List<Order_Form> selectById(Order_Form order);

    /**
     * 教练生成订单
     */
    @Insert("insert into order_form values(#defualt,#{tid},#{cid},#{courseid},#{price},#{classtime},#{startTime},#{vid})")
    public void coachAddOrder(Order_Form order_form);

    /**
     * 修改订单状态：0、教练生成 1、学员已付款  2、学员已评价(前端不现实评价按钮)
     */
    @Update("update order set status=#{status} where id=#{id}")
    public void updateOrderStatus(Order_Form order_form);

    /**
     * 生成订单评价
     * @param order_evaluation
     */
    @Insert("insert into order_evaluation values (#defualt,#{vid},#{level},#{detail},#defualt)")
    public void addOrderEvaluation(Order_Evaluation order_evaluation);

    /**
     * 先根据教练、学员、场馆查询订单号，再用订单号查询评价详情
     * @param id(vid为订单号)
     * @return
     */
    @Select("select * from order_evaluation where vid=#{vid}")
    public Order_Evaluation selectEvaluation(Integer id);
}
