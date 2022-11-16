package sof3021.ph18485;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ConfigErrorMessage {

	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasenames("classpath:messages/register", "classpath:messages/product");
		ms.addBasenames("classpath:messages/category", "classpath:messages/order");
		ms.addBasenames("classpath:messages/order-detail");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
