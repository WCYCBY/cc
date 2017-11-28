package com.zp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.llmjcommon.authClient.LlmjAuthorityInterceptor;
import com.llmjcommon.authClient.LlmjBasicAuthorizeFilter;


@SpringBootApplication
@ServletComponentScan
public class AuthorityApplication extends WebMvcConfigurerAdapter{

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthorityApplication.class, args);
	}
	
	public void addInterceptors(InterceptorRegistry regisry) {
		InterceptorRegistration addInterceptor = regisry.addInterceptor(new LlmjAuthorityInterceptor()); 
		addInterceptor.addPathPatterns("/*/*/*");
		addInterceptor.addPathPatterns("/*/*/*/*");
	}
	
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		LlmjBasicAuthorizeFilter llmjBasicAuthorizeFilter = new LlmjBasicAuthorizeFilter();
		registrationBean.setFilter(llmjBasicAuthorizeFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("*.html");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	

	@Bean(name = {"multipartResolver"})
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxInMemorySize(1024);
        return cmr;
    }
	
	@Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        return registration;
    }
	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: hiddenHttpMethodFilter
	 * @Description: <功能详细描述>
	 * @author chenbaiyu
	 * @date 2017年5月5日 下午2:57:33 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	 @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter(){
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                filterChain.doFilter(request, response);
            }
        };
    }
}
