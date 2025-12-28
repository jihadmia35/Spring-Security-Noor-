package bd.edu.seu.ss;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        IO.println("Security Configured");

        http
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/login").permitAll()
                .requestMatchers("/", "/profile").authenticated()
                .requestMatchers("/forget", "/register", "/registration").anonymous()
                .requestMatchers("/settings", "/payment", "/change-password").fullyAuthenticated()
                .requestMatchers("/admin", "/admin-dashboard", "/admin/*", "/admin/**").hasAuthority("READ_INVOICE")
                .requestMatchers("/images/*.png").permitAll()
                .requestMatchers("/contact-us","/about-us").permitAll()
                .anyRequest().authenticated()
        )

        .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("mobile")
                .passwordParameter("pin")
                .defaultSuccessUrl("/dashboard", false)
                .failureUrl("/login?error")
                .permitAll())

        .logout(logout -> logout
                .logoutUrl("/signout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember")
        )

        .rememberMe(rm -> rm
                .rememberMeParameter("remember")
                .rememberMeCookieName("remember-cookie")
                .tokenValiditySeconds(9000)
        )

        ;


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.withUsername(username).password(" ").build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
