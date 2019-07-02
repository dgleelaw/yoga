package com.woniuxy.yogasystem.service;

import java.util.List;

import com.woniuxy.yogasystem.pojo.Order_Form;

public interface OrderService {
	// 学员查看我的订单信息
	public List<Order_Form> findOrderMsg(int tid);

	// 根据用户id查看用户余额
	public int showMoney(int uid);

	// 根据用户id更新钱包金额
	public void updateMoney(int vuid,int uid, int money, int cuid);
	public void updateMoney(int uid, int money);
	// 根据订单号，改变订单状态
	public void updateStatus(String number);

	// 根据订单号号，删除订单
	public void updateFlag(String number);

	// 根据uid，查看用户角色
	public int findRole(int uid);

	// 教练查看，我的订单信息
	public List<Order_Form> findCoachOrderMsg(int uid);
	//根据订单号，修改订单状态
	public String updateOrder(String number);
}
