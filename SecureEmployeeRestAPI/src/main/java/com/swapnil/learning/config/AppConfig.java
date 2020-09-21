/**
 * 
 */
package com.swapnil.learning.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.swapnil.learning.interceptor.LoggingInterceptor;

/**
 * @author Swapnil Dangore
 *
 */
@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public LoggingInterceptor loggingInterceptor() {
	    return new LoggingInterceptor();
	}    
	@Bean
	public WebMvcConfigurer adapter() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addInterceptors(InterceptorRegistry registry) {
	            registry.addInterceptor(loggingInterceptor());
	        }
	        
	        @Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/employees").allowedOrigins("http://localhost:4200");
			}
	    };
	}
	
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat =
	      new TomcatServletWebServerFactory() {

	        @Override
	        protected void postProcessContext(Context context) {
	          SecurityConstraint securityConstraint = new SecurityConstraint();
	          securityConstraint.setUserConstraint("CONFIDENTIAL");
	          SecurityCollection collection = new SecurityCollection();
	          collection.addPattern("/*");
	          securityConstraint.addCollection(collection);
	          context.addConstraint(securityConstraint);
	        }
	      };
	  tomcat.addAdditionalTomcatConnectors(createHttpConnector());
	  return tomcat;
	}

	@Value("${server.port.http}")
	private int serverPortHttp;

	@Value("${server.port}")
	private int serverPortHttps;

	private Connector createHttpConnector() {
	  Connector connector =
	      new Connector("org.apache.coyote.http11.Http11NioProtocol");
	  connector.setScheme("http");
	  connector.setSecure(false);
	  connector.setPort(serverPortHttp);
	  connector.setRedirectPort(serverPortHttps);
	  return connector;
	}
}
