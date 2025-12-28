package bd.edu.seu.ss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        IO.println("Security Configured");

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/profile").authenticated()
                .requestMatchers("/settings", "/payment", "/change-password").fullyAuthenticated()
                .requestMatchers("/admin", "/admin/*", "/admin/**").denyAll()
                .requestMatchers("/images/*.png").permitAll()
                .requestMatchers("/contact-us","/about-us").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .logout(Customizer.withDefaults())
        .rememberMe(Customizer.withDefaults());

        return http.build();
    }

}
