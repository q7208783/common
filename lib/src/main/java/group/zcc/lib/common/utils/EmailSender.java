package group.zcc.lib.common.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zcc.login.common.exception.ServiceException;
import com.zcc.login.config.PasswordConfig;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created by ZhangChicheng on 2017/12/6.
 */
@Component
@CommonsLog
public class EmailSender {

	private SingleSendMailRequest request;
	private IClientProfile profile;
	private IAcsClient client;

	@Autowired
	private PasswordConfig passwordConfig;

	@PostConstruct
	public void init() {
		profile = DefaultProfile.getProfile("cn-hangzhou", passwordConfig.getAliyunAccessKeyId(),
			passwordConfig.getAliyunAccessSecret());
		client = new DefaultAcsClient(profile);
		request = new SingleSendMailRequest();
	}

	public void send(List<String> emailAddressArray, String title, String content) throws ServiceException{
		try {
			log.debug("emails:"+emailAddressArray.toString()+"\n"+"title:"+title+"\n"+"url:"+content+"\n");
			request.setAccountName("batch@mail.zhangcc.group");
			request.setFromAlias("皮条张");
			request.setAddressType(1);
			request.setTagName("HomeLink");
			request.setReplyToAddress(true);
			request.setSubject(title);
			request.setHtmlBody("您订阅的二手房监控发现一个新房源满足您的需求\n" +
				"你可以点击以下链接查看：" + formateUrl(content));
			for (int i = 0; i < emailAddressArray.size(); ++i) {
				request.setToAddress(emailAddressArray.get(i));
				SingleSendMailResponse httpResponse = client.getAcsResponse(request);
			}
		} catch (ServerException e) {
			throw new ServiceException(e.getErrCode(),e.getErrMsg());
		} catch (ClientException e) {
			throw new ServiceException(e.getErrCode(),e.getErrMsg());
		}
	}

	private String formateUrl(String url) {
		return "<a href=”" + url + "”>" + url + "</a>";
	}
}
