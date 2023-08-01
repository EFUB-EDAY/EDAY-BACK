package efub.eday.edayback.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:[3000,8080]")
			.allowedOrigins("https://ewha-day.com")
			.allowedOrigins("https://api.ewha-day.com")
			.allowCredentials(true)
			.allowedHeaders("Authorization", "Content-Type")
			.exposedHeaders("Authorization")
			.allowedMethods("*")
			.maxAge(3600);
	}
}
