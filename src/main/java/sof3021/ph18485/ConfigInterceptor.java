package sof3021.ph18485;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sof3021.ph18485.interceptors.AdminInterceptor;
import sof3021.ph18485.interceptors.GuestInterceptor;
import sof3021.ph18485.interceptors.UserInterceptor;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

	@Autowired
	private GuestInterceptor guestInterceptor;
	@Autowired
	private UserInterceptor userInterceptor;
	@Autowired
	private AdminInterceptor adminInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(guestInterceptor)
		.addPathPatterns("/user/**", "/admin/**", "/cart/checkout")
		.excludePathPatterns("/login", "/register", "/logout");
		
		registry.addInterceptor(userInterceptor)
		.addPathPatterns("/login", "/register", "/admin/**");
		
		registry.addInterceptor(adminInterceptor)
		.addPathPatterns("/login", "/register");
	}

}
