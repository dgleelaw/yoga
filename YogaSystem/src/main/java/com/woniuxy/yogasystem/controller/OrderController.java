package com.woniuxy.yogasystem.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.woniuxy.yogasystem.pojo.Coach;
import com.woniuxy.yogasystem.pojo.Order_Evaluation;
import com.woniuxy.yogasystem.pojo.Order_Form;
import com.woniuxy.yogasystem.pojo.Trainee;
import com.woniuxy.yogasystem.pojo.Venues;
import com.woniuxy.yogasystem.service.CourseService;
import com.woniuxy.yogasystem.service.OrderEvaluationService;
import com.woniuxy.yogasystem.service.OrderService;
import com.woniuxy.yogasystem.service.UserService;
import com.woniuxy.yogasystem.util.AlipayConfig;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;
	@Autowired
    private OrderEvaluationService orderEvaluationService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
	// 学员查看我的订单
	@RequestMapping("/findOrderMsg")
	public String findOrderMsg(HttpSession session, ModelMap map) {
		int uid = (int)session.getAttribute("uid");
		int role = (int)session.getAttribute("role");
		// 根据uid查出我的订单角色
		if (role == 0) {
			System.out.println(role);
			List<Order_Form> orders = orderService.findOrderMsg(uid);
			System.out.println(orders);
			map.put("orders", orders);
			map.put("role", role);
			
		}
		if (role == 1) {
			List<Order_Form> orders = orderService.findCoachOrderMsg(uid);
			map.put("orders", orders);
			map.put("role", role);
		}
		return "/html/order.html";
	}

	@RequestMapping("/payment")
	public void payment(HttpServletRequest request, HttpServletResponse response, String money,HttpSession session) throws Exception {
		// 设置编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		// 设置请求参数
		AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
		aliPayRequest.setReturnUrl(AlipayConfig.return_url);
		aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);
		// 商户订单号，后台可以写一个工具类生成一个订单号，必填
		String order_number = new String(UUID.randomUUID().toString())+"/"+session.getAttribute("uid");
		// 付款金额，从前台获取，必填
		String total_amount = new String(money);
		// 订单名称，必填
		String subject = new String("充值");
		aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求
		String result = alipayClient.pageExecute(aliPayRequest).getBody();
		// 输出
		out.println(result);
	}

	@RequestMapping("/notify_url")
	public String notify_url(HttpSession session,HttpServletRequest request, String out_trade_no, String trade_no, String trade_status,
			String total_amount) throws AlipayApiException {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			/*
			 * valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			 */
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
				AlipayConfig.sign_type); // 调用SDK验证签名
		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		 * 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */

		if (signVerified) {
			if (trade_status.equals("TRADE_SUCCESS")) {
				String id = out_trade_no.split("/")[1];
				int uid = Integer.parseInt(id);
				//String total = total_amount.replaceAll(".00", "");
				double aa = Double.parseDouble(total_amount);
				int money = (int)aa;
				// 根据uid改变余额数量
				//int money = Integer.parseInt(total);
				orderService.updateMoney(uid, money);
			}
		}
		return "/showAdd2.html";
	}

	@RequestMapping("/showMoney")
	public String showMoney(HttpSession session, ModelMap map) {
		int uid = (int) session.getAttribute("uid");
		int money = orderService.showMoney(uid);
		map.put("money", money);
		return "/html/wallet.html";
	}

	@RequestMapping("/pay")
	public String pay(int vuid,int cuid,HttpSession session,ModelMap map, int price, String number) {
		int uid = (int)session.getAttribute("uid");
		int money = orderService.showMoney(uid);
		if (money > Math.abs(price)) {
			// 支付
			orderService.updateMoney(vuid,uid, price,cuid);
			// 改变订单状态
			orderService.updateStatus(number);
			map.put("result", "支付成功");
		} else {
			// 账户余额不足，请充值
			map.put("result", "你的账户余额不足，请充值");
		}
		return "/html/paysucess.html";
	}

	@RequestMapping("/deleteOrder")
	public String deleteOrder(String number, ModelMap map) {
		// 改变状态
		orderService.updateFlag(number);
		map.put("result", "删除成功");
		return "redirect:/order/findOrderMsg?uid=1";
	}

	// 评论
	@RequestMapping("/comment")
	public String comment(String number, ModelMap map) {

		return "/html/comment.html";
	}
	
	//我上完课功能:修改订单状态
	@RequestMapping("/updateOrder")
	public String updateOrder(String number, ModelMap map){
		String result=orderService.updateOrder(number);
		return "/html/1.html";
	}
	
	
	
	 @RequestMapping("/myevalution")
	    public ModelAndView findMyEvalution(HttpServletRequest request){
	        ModelAndView model =new ModelAndView();
	        Order_Form order_form=new Order_Form();
	        //1、确认用户角色(status=1,为用户可以添加评论)
	        int role= (int) request.getSession().getAttribute("role");
	        Integer uid=(Integer)request.getSession().getAttribute("uid");
	        
	        //将trainee、coach、venue中id保存到order_form中
	        if(role==0){
	            Trainee trainee=userService.selectTrainee(uid);
	            order_form.setTid(trainee.getId());
	        }else if(role==1){
	            Coach coach=userService.selectCoach(uid);
	            order_form.setCid(coach.getId());
	        }else if(role==2){
	            Venues venues=userService.selectVenue(uid);
	            order_form.setVid(venues.getId());
	        }
	        //判断页面显示
	        List<Order_Form> orders=orderEvaluationService.findOrder(order_form);
	        for(Order_Form order_form1:orders){
	            if(order_form1.getFlag()==0&&!order_form1.getOrder_eva_id().equals("1")&&!order_form1.getOrder_eva_id().equals("0")) {
	                order_form1.setDetail(orderEvaluationService.selectEval(order_form1.getOrder_eva_id()).getDetail());
	                order_form1.setLevel(orderEvaluationService.selectEval(order_form1.getOrder_eva_id()).getLevel());
	            }

	            if(order_form1.getStatus()==2){
	                if(order_form1.getOrder_eva_id().equals("0")){
	                    //订单付款未评价
	                    order_form1.setStatus(1);
	                }else if(order_form1.getOrder_eva_id().equals("1")){
	                    //订单已评论，删除无法再次评论
	                    order_form1.setStatus(4);
	                }else{
	                    //订单付款已评价
	                    order_form1.setStatus(2);
	                }
	            }else{
	                //订单未付款无法评价
	                order_form1.setStatus(3);
	            }

	            //查找姓名
	            order_form1.setVname(courseService.selectVname(order_form1.getVid()).getName());
	            order_form1.setCname(courseService.selectCname(order_form1.getCid()).getName());
	            order_form1.setTname(courseService.selectTname(order_form1.getTid()).getName());
	        }

	        model.addObject("evaluations",orders);
	        if(role==0){
	        	model.addObject("role","trainee");
	        }
	        if(role==1){
	        	model.addObject("role","coach");
	        }
	        if(role==2){
	        	model.addObject("role","venue");
	        }
	        

	        model.setViewName("html/evaluation.html");

	        return model;
	    }

	    /**
	     * 跳转到评论添加页面
	     * @return
	     */
	    @RequestMapping("/toaddEvaluation")
	    public ModelAndView toaddEvaluation(String number){
	        ModelAndView model=new ModelAndView();
	        model.addObject("number",number);
	        model.setViewName("html/add_evaluation.html");
	        return model;
	    }

	    /**
	     * 添加订单评论
	     * @param order_form
	     * @return
	     */
	    @RequestMapping("addEvaluation")
	    public ModelAndView addEvaluation(Order_Form order_form){
	        order_form.setLevel(6-order_form.getLevel());
	        //插入新增评论
	        Order_Evaluation evaluation=new Order_Evaluation();
	        evaluation.setId(order_form.getNumber());
	        evaluation.setDetail(order_form.getDetail());
	        evaluation.setLevel(order_form.getLevel());
	        orderEvaluationService.addEvaluation(evaluation);
	        //订单中插入评论id(与订单id相同)
	        orderEvaluationService.addEidToOrderFrom(evaluation.getId());
	        ModelAndView model=new ModelAndView();
	        model.setViewName("redirect:/order/myevalution");
	        return model;
	    }
	    /**
	     * 删除评论
	     * @return
	     */
	    @RequestMapping("/deleteEvaluation")
	    public ModelAndView deleteEvaluation(String id){
	        orderEvaluationService.delEid(id);
	        orderEvaluationService.delEvaluation(id);
	        ModelAndView model=new ModelAndView();
	        model.setViewName("redirect:/order/myevalution");
	        return model;
	    }

}