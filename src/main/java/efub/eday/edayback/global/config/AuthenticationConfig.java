package efub.eday.edayback.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import efub.eday.edayback.global.jwt.JwtExceptionFilter;
import efub.eday.edayback.global.jwt.JwtFilter;
import efub.eday.edayback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class AuthenticationConfig {

	private final JwtProvider jwtProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.httpBasic().and()
			.cors().and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 대신 jwt 사용하는 경우 사용
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/member/auth", "/docs", "/swagger-ui/*", "/v3/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtExceptionFilter(), JwtFilter.class)
			.build();
	}

}
