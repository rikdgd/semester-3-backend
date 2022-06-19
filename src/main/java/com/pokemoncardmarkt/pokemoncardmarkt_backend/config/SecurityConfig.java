package com.pokemoncardmarkt.pokemoncardmarkt_backend.config;

import com.pokemoncardmarkt.pokemoncardmarkt_backend.filter.CustomAuthenticationFilter;
import com.pokemoncardmarkt.pokemoncardmarkt_backend.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        String authUser = "USER";
        String authAdmin = "ADMIN";

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests().antMatchers(POST, "/api/v1/create_account").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/refreshtoken/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/allCards/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/card/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/allCards/**").permitAll();
        http.authorizeRequests().antMatchers("/message/**").permitAll();
        http.authorizeRequests().antMatchers("/private-message/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/allExpansions/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/Expansion/**").permitAll();


        http.authorizeRequests().antMatchers(GET, "/api/v1/users/**").hasAnyAuthority(authUser, authAdmin);
        http.authorizeRequests().antMatchers(POST, "/api/v1/role/**").hasAnyAuthority(authAdmin);
        http.authorizeRequests().antMatchers(POST, "/api/v1/create_card/").hasAnyAuthority(authAdmin);
        http.authorizeRequests().antMatchers(POST, "/api/v1/AddToExpansion/**").hasAnyAuthority(authAdmin);
        http.authorizeRequests().antMatchers(GET, "/api/v1/collection/**").hasAnyAuthority(authUser, authAdmin);
        http.authorizeRequests().antMatchers(POST, "/api/v1/collection/**").hasAnyAuthority(authUser, authAdmin);
        http.authorizeRequests().antMatchers(GET, "/api/v1/user_collection/**").hasAnyAuthority(authUser, authAdmin);
        http.authorizeRequests().antMatchers(POST, "/api/v1/create_expansion/**").hasAnyAuthority(authAdmin);


        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
