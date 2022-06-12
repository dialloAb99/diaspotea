package com.diaspotea.diaspoteaserver.security;

import com.diaspotea.diaspoteaserver.services.JpaUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, securedEnabled = true, jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JpaUserDetailService jpaUserDetailService;

    public WebSecurityConfig(JpaUserDetailService userDetailService) {
        this.jpaUserDetailService = userDetailService;
    }

    @Override
    //pour configurer les autorisations
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        // autorisation pour toutes les toutes les pages
        httpSecurity.authorizeRequests().antMatchers("/","/petit-dejeuner","/dessert","/boisson","/monpanier/{id}","/inscription","/resources/**","/webjars/**","/panier/ajouter/menu").permitAll()
                .anyRequest().authenticated();
        httpSecurity.formLogin();


    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of( "http://localhost:8080", "self"));
        config.setMaxAge(3600L);
        config.setAllowedHeaders(List.of("Origin", "X-Requested-With", "Content-Type", "Accept", "authorization"));
        config.setAllowedMethods(List.of("POST", "GET", "PUT", "OPTIONS", "DELETE"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jpaUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
