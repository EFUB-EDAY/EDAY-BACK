package efub.eday.edayback.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import efub.eday.edayback.domain.member.service.MemberService;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {
	MemberService memberService;

	@Value("${spring.jwt.secret}")
	private String secretKey;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.httpBasic().disable()
			.csrf().disable()
			.cors().and()
			.authorizeRequests()
			.antMatchers("/members/**", "/infos/**", "querys/**", "titles/**", "quiz/**").permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 사용하는 경우 사용
			.and()
			.addFilterBefore(new JwtFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtExceptionFilter(objectMapper), JwtFilter.class)
			.build()
			;
	}
}
