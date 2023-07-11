package io.github.ferdynandariza.marketplace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {

    @Bean
//    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf((csrf)->csrf.disable())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/resources/**", "/account/**", "/api/**").permitAll()
                        .requestMatchers("/shop/**", "/cart/**", "/profile/add-balance").hasAuthority("Buyer")
                        .requestMatchers("/merchandise/**").hasAuthority("Seller")
                        .requestMatchers("/shipment/**", "/admin/**", "/history/**").hasAuthority("Admin")
//                        .requestMatchers("/profile/{username}", "/cart/{username}", "/merchandise/{username}" ).hasRole("USER")
                        .requestMatchers("/profile/**").hasAnyAuthority("Buyer", "Seller")
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/account/login")
                        .loginProcessingUrl("/submit-login"))
                .logout((logout) -> logout
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .exceptionHandling((handler) -> handler
                        .accessDeniedPage("/account/access-denied"))
        ;
        return http.build();
    }


}
