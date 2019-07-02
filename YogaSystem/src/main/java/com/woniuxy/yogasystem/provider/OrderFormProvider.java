package com.woniuxy.yogasystem.provider;

import com.woniuxy.yogasystem.pojo.Order_Form;
import org.apache.ibatis.jdbc.SQL;

public class OrderFormProvider {
    public String selectOrder(Order_Form order_form){
        SQL sql = new SQL().SELECT("*").FROM("order_form");

        if(order_form.getCid()>0){
            sql.WHERE("cid="+order_form.getCid());
        }
        if(order_form.getTid()>0){
            sql.WHERE("tid="+order_form.getTid());
        }
        if(order_form.getVid()>0){
            sql.WHERE("vid="+order_form.getVid());
        }
        return sql.toString();
    }
}
