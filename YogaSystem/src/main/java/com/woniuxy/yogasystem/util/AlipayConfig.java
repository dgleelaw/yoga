package com.woniuxy.yogasystem.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id ="2016100100637032";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCL2QPt8jmxmJKAblBnU6p8fIbLipZm1tlvsHBuKsuMdRO80C3OKwPvlKFISK2g5nslGfJEUdGE6ws8aeB6vbIYJKmK6gIlzKf3L4OL7momx/SlZVLczWNNt+6/Ww3ttbrzLLafG3wOKTRnrVP/+Q6rwNt4TGpotq1eXu8Bxm2aqziU8CQBPEdtHm4t5n8IPg7zvIHnk50E67TxJMP4Vgf8QyKMZzIyEaXFsspe7pRX4LzG+zoAsux10bRvtgRhgaQqAW9jv32btQ6QGVyRrJnzyp16Qc+2TKUyY5Oapt1dChbbqFcUs/cFC4EUN+bHOqt37h+yZoZjOpozAj5G/eELAgMBAAECggEAZm2XzDMLAMHwI7MontWP3EfU7sXd7QrWDVEcuAQOPB0QTM3pjsTAyDyDeI8K5PuLHJyJbnLC0+jXvPMLYLsJ81b++uj0k7z3FTwuzFHeW/M+g9nXvDOO1UCv1GYbqIzflfac96JI1ZJmeL1h9ez0SuYhmVXW5kVOa/16b0Fny80gKH7+Lv/epkqGbZlCnQpNx7LJlhEPCz70/WphjIDkZccuvJLLpfKHyPVnmqVNQSAJ0X5H+w4hUR5w2gfOwvD5ueZpkGahGI+PILvcWnIkNaScj0c11NvGoT0DtZDUWSyWMzCY//sBdyMqmB9Xc5TbtPijLK26uc930PBGwRt5WQKBgQDRzQ0uMVraCXCMvRSG+9r4ysuRQ0VPltbkZnET9xqZhTrOB88jdjsErHo4NwzlZOAd2j96hNICTnlC57K+0lqi8d9mcVN8GpO/t0oEyE+R2yz93tg3a4Nm6DwR6F7tGY4Rt9vc/9o0QZrlcoHYt9mWIhzanFKCpsIvh8ds6UH8lQKBgQCqpIrg37Q20EtxBsE1+IgrXsF08Z+ct6cxvj18zRj5XXu6SE9KNhHO4w+kCa4XIEkGYY6doHbBQHt0zE0Zwj78FYOm2p/AYSHzG8z0AtImqXjp79E5Ybn0qLxPI8OGl1QRIU8n41j+O8JjlVPnkhQv+b+KnSBlbAXknxn89/FfHwKBgCRq447RImQWn2LOr3F+5lOpYAynELDwDf03iwlcK1Havopk8J/HNhdpmQ2JAMQQ7pY//CTYSVv9Iy9p6K0W9oSyV4UAxu0T7/Gnb0HFQ4qd8QFgXUfBcd27EjJJk7C0buTEdXW3M7k+ahTJ+6ASycI1yaxo1im3qJ13hDZzhvDBAoGALwt2DlEeSg5a/ioykkKWp/5Tr3391yL6wUgFg6a3v+f5+H1+/qKxfiwzX7IbgNyxxUSYIPTgSJ8Sajz49KUM4Eq+JP4ZFNyyk2eStaMhdO88GqK3N+6taborUFY3zZ9i2eYpAII5Qr4O9Y1xuGIZlrBwEjMHf26soT8L1yY0MVkCgYEAtwh+ZJawXDd10lPekKlGSdEOSUlXJaSGGhsGg8A0r2QKNCx61P45mOC6isMEJxFkVoEWjdAO7Ph+1f/J2iN6J7UAqi8Kx7Eu0zq2F7SXffMA53OHEFGnPqQwH3m/xjgG1aeycDEFSZEGI7Zo8/1fiyjP50rkKgmebyU969KHSVE=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg+WeP/2uZFkTY5Ba3xOkIjfC0dMJfOdq/SJP0WBKsqqnQm21hRnHanZp+xLnxup8D+UYmTcUD+YlS7GjfDH6ZF+f6CMLXg/43Zo1+yI+eZbZOFJRsfDlP/cxPNDBd29nmg8qbOJogxlSQZTc2Eqm2sqtzGesJtKJTHFY5Ebe4QGBP++Rtlb9LBs+e+FLHGiRS0QJOc4FedNvCWkYe7ykOiMpkTZylhG6h5uBPTzRZCY/UYuSpkKCFbNnfT8Vcg+Q6eeH9fixPZ2+P6UhsFsmqZJ08NHQs/PVjG3n0F5Gff+rZkXQ0oVb7c+R0d2aQqD+59+emfO7zEFgcBAOKyCCEwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://qm25480868.wicp.vip/order/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://qm25480868.wicp.vip/order/showMoney";
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";
//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

