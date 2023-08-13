package efub.eday.edayback.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(servers = {@Server(url = "https://api.ewha-day.com", description = "Default Server URL")})
public class OpenApiConfig {

	private static final String JWT_SCHEME = "JWT Auth";

	@Bean
	public OpenAPI openApi() {
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT_SCHEME);
		Components components = new Components()
			.addSecuritySchemes(JWT_SCHEME, apiSecurityScheme());
		return new OpenAPI()
			.info(apiInfo())
			.addSecurityItem(securityRequirement)
			.components(components);
	}

	private SecurityScheme apiSecurityScheme() {
		return new SecurityScheme()
			.type(SecurityScheme.Type.HTTP)
			.in(SecurityScheme.In.HEADER)
			.name(JWT_SCHEME)
			.scheme("Bearer")
			.bearerFormat("JWT");
	}

	private Info apiInfo() {
		return new Info()
			.version("v1.0.0")
			.title("E-DAY API Documentation")
			.description("EFUB 3rd SWS : E-DAY API 공식 문서입니다.");
	}
}
