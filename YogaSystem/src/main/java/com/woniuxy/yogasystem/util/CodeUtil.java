package com.woniuxy.yogasystem.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class CodeUtil {
	//产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIPO8k10rij53K";  // TODO 修改成自己的accessKeyId
    static final String accessKeySecret = "qHnYMmhV1pJVhBz1SyBfWuNJ1XrhTB";   // TODO 修改成自己的accessKeySecret

    /**
     * :调用方法发送验证码
     *
     * @param telephone 电话
     * @param code      验证码
     * @throws ClientException
     * @return SendSmsResponse
     */
    public static SendSmsResponse sendSms(String telephone, String code,int i) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(telephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("yoga运动");    // TODO 修改成自己的
        //必填:短信模板-可在短信控制台中找到
        if(i==2){
        	 request.setTemplateCode("SMS_168306968"); //	修改成自己的,传过来的参数是2了，此时为重置密码验证
        }else if(i==1){
        	request.setTemplateCode("SMS_168306974");    // TODO 修改成自己的,传过来的参数是1了，此时为注册验证
        }else{
        	request.setTemplateCode("SMS_169101804");    // TODO 修改成自己的,传过来的参数是3了，此时为验证登陆
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
       if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }

   /*public static void main(String[] args) throws ClientException, InterruptedException {
        //使用时需要自己指定验证码
        int i = (int)((Math.random()*9+1)*1000);
        String code = Integer.toString(i);
        System.out.println("发送的验证码为：" + code);
        //发短信
        SendSmsResponse response = sendSms("13240866880", code,2); // TODO 填写你需要测试的手机号码
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
    }*/
}
