package efub.eday.edayback.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowCredentials(true)
			.exposedHeaders("Authorization")
			.allowedOrigins("http://localhost:3000")
			.allowedOrigins("https://ewha-day.com")
			.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name())
			.maxAge(3600);
	}
}
