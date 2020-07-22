/**
 * 
 */
package com.swapnil.learning.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
