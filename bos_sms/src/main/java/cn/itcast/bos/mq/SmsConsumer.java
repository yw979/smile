package cn.itcast.bos.mq;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Service;

@Service
public class SmsConsumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;

		// 调用sms服务发送短信
		try {
			// String result=SmsUtils.sendSmsByHTTP(model.getTelephone(), msg);
			String result = "000/xxxx";
			if (result.startsWith("000")) {
				// 发送成功
				System.out.println("发送短信成功,手机号:"
						+ mapMessage.getString("telephone") + ",验证码:"
						+ mapMessage.getString("msg"));
			} else {
				// 发送失败
				throw new RuntimeException("短信发送失败,信息码:" + result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
