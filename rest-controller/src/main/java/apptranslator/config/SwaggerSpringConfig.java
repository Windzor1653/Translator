package apptranslator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerSpringConfig {

    @Value("${springdoc.api-docs.title}")
    private String title;

    @Value("${springdoc.api-docs.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(getInfo());
        return openAPI;
    }

    private Info getInfo() {
        return new Info()
                .title(title)
                .version(version);
    }
}

