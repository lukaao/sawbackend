package lukaao.github.sawbackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "SAW Backend API",
                description = "API for managing the SAW backend system.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Lucas Barcelos",
                        email = "lukaaobarcelos@gmail.com",
                        url = "https://github.com/lukaao/sawbackend"
                )
        )
)
public class OpenApiConfiguration {
}
