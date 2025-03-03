package lukaao.github.sawbackend.config;


import lukaao.github.sawbackend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor for SecurityConfiguration.
     *
     * @param userDetailsService A custom user details service that is used for user authentication.
     */
    public SecurityConfiguration(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the HTTP security settings, including CSRF, form login, basic HTTP authentication,
     * and authorization rules for specific HTTP methods.
     *
     * @param http The HttpSecurity object that provides a mechanism for configuring web security.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection.
                .formLogin(Customizer.withDefaults()) // Enable default form login.
                .httpBasic(Customizer.withDefaults()) // Enable default HTTP basic authentication.
                .authorizeHttpRequests(authorize -> authorize
                        // Require ADMIN role for specific HTTP methods and URL patterns.
                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        // Any other request requires authentication.
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService) // Use custom user details service for authentication.
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
            web.ignoring().requestMatchers(
                    "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**"

            );

    }

    /**
     * Configures the password encoder used for password hashing and verification.
     *
     * @return A BCryptPasswordEncoder with a strength of 10.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); // BCrypt encoder with strength of 10.
    }
}
