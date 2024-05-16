package com.example.ecommfullstack.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    String encodedPassword = passwordEncoder().encode("test@123");
	    auth.inMemoryAuthentication().withUser("Yvan").password(encodedPassword).authorities("USER", "ADMIN");
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/**").permitAll()
//                .antMatchers("/auth").permitAll()
//                .antMatchers("/auth/register").permitAll()// Allow access to product APIs without authentication
//                .antMatchers("/api/orders/**").hasAnyRole("USER", "ADMIN") // Require USER or ADMIN role for order APIs
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/auth") // Specify your custom login page URL
//                .loginProcessingUrl("/auth/login") // Specify the URL to which the login form should be submitted
              //  .defaultSuccessUrl("/home", true) // Redirect to this URL upon successful login
                .permitAll()
                .and()
            .logout() // Configure logout
//                .logoutUrl("/logout") // Specify the logout URL
                .logoutSuccessUrl("/login?logout") // Redirect to this URL after successful logout
//                .invalidateHttpSession(true) // Invalidate the HTTP session
//                .deleteCookies("JSESSIONID") // Delete cookies upon logout
                .permitAll();
        	//http.httpBasic();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/assets/**", "/js/**", "/images/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

