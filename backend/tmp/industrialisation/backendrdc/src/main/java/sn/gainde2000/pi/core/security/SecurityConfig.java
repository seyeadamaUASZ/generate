/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.gainde2000.pi.core.security;

import sn.gainde2000.pi.core.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import sn.gainde2000.pi.core.repository.HistoriqueSessionRepository;
import sn.gainde2000.pi.core.repository.SessionRepository;
import sn.gainde2000.pi.core.repository.UtilisateurRepository;
import sn.gainde2000.pi.core.repository.ParametreRepository;
import sn.gainde2000.pi.core.service.IMenuService;

/**
 *
 * @author leyu
 */
@Configuration
@EnableWebSecurity
@CrossOrigin
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfig jwtConfig;
    private String[] USERS_ACCESS = {"ADMIN", "OPERATEUR"};

    @Autowired
    UserService userService;
    @Autowired
    UtilisateurRepository repository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    HistoriqueSessionRepository historiqueSessionRepository;
    @Autowired
    ParametreRepository parametreRepository;
    @Autowired
    IMenuService menuService;
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig,repository,sessionRepository,historiqueSessionRepository,parametreRepository,menuService))
                .addFilter(new JwtTokenAuthenticationFilter(jwtConfig, authenticationManager()))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers("/users/recover").permitAll()
                .antMatchers("/users/veriftoken/**").permitAll()
                .antMatchers("/parametre").permitAll()
                .antMatchers("/menu").permitAll()
               // .antMatchers("/socket").permitAll()
                .anyRequest().authenticated(); 
                http.cors();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
//        return new CustomBasicAuthenticationEntryPoint();
//    }
    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
