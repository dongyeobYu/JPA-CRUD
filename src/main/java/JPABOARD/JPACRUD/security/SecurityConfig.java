package JPABOARD.JPACRUD.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Path;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity webSecurity){
        webSecurity.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        webSecurity.ignoring().antMatchers("/favicon.ico", "/resoruces/**", "/error");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/", "/login", "/new-member", "/new-member", "/member-list").permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/")
                .usernameParameter("nickname")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutSuccessUrl("/").permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true);

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
