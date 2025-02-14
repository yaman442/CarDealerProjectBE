package com.binary.carDealerApp.classCarDealerApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Configuration class for Spring Security.
 * This class defines the security settings for the application.
 *
 * Key points:
 * 1. Uses @Configuration to indicate this is a configuration class.
 * 2. @EnableWebSecurity enables Spring Security's web security support.
 * 3. Configures authentication and authorization rules.
 * 4. Sets up CSRF and CORS policies.
 * 5. Defines session management policy.
 * 6. Configures role-based access control for different endpoints.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AuthenticationProvider authenticationProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor for SecurityConfig.
     * Uses constructor injection to inject AuthenticationProvider dependency.
     *
     * @param authenticationProvider The provider responsible for authentication.
     */
    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Configures the SecurityFilterChain bean.
     * This method sets up the security rules for HTTP requests.
     *
     * @param http The HttpSecurity object to be configured.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csfr -> csfr.disable())  // Disables CSRF protection
                .cors(cors -> cors.configurationSource(corFilter()))  // Disables CORS
                .authorizeHttpRequests(ahr ->
                                ahr
                                        .anyRequest().permitAll()
                                    // Permits all GET requests to these endpoints
//                                    .requestMatchers(HttpMethod.GET,"/api/cars/all", "/api/dealers/all", "/api/cars/*", "/api/dealers/*")
//                                    .permitAll()
//                                    // Permits POST requests to create new users
//                                    .requestMatchers(HttpMethod.POST, "/api/users/", "/api/users/login")
//                                    .permitAll()
//                                    // Requires authentication for POST requests to create cars and dealers
//                                    .requestMatchers(HttpMethod.POST,"/api/cars/", "/api/dealers/create")
//                                    .authenticated()
//                                    // Requires DEALER or ADMIN role for PUT requests
//                                    .requestMatchers(HttpMethod.PUT, "/api/cars/*", "/api/dealers/update/*")
//                                    .hasAnyRole("DEALER", "ADMIN")
//                                    // Requires DEALER or ADMIN role for DELETE requests
//                                    .requestMatchers(HttpMethod.DELETE, "/api/cars/*", "/api/dealers/delete/*")
//                                    .hasAnyRole("DEALER", "ADMIN")
                        );
                // Commented out: .httpBasic(Customizer.withDefaults());
                // This line would enable HTTP Basic authentication if uncommented
//                .sessionManagement(ses -> {
//                    ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Sets session policy to stateless
//                })
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Sets the authentication provider

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://127.0.0.1:5173"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Commented out UserDetailsService bean.
     * This method would create an in-memory user store if uncommented.
     * It defines three users: a regular user, a dealer, and an admin.
     * Each user has different roles and permissions.
     * This is typically used for testing or development purposes.
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("userPass"))
//                .roles("USER")
//                .build();
//        UserDetails dealer = User.builder()
//                .username("dealer")
//                .password(passwordEncoder().encode("dealerPass"))
//                .roles("DEALER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin, dealer, user);
//    }

    /**
     * Commented out PasswordEncoder bean.
     * This method would create a BCryptPasswordEncoder if uncommented.
     * BCryptPasswordEncoder is a secure way to encode passwords.
     * It's currently commented out, suggesting that password encoding
     * might be handled elsewhere in the application.
     */
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}