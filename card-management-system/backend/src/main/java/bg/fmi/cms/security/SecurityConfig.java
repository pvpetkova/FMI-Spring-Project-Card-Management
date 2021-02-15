package bg.fmi.cms.security;

import bg.fmi.cms.model.constats.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("customUserDetails")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/settings").hasAnyAuthority()
                .antMatchers("/users/**").hasAuthority(Role.ADMIN.toString())
                .antMatchers("/card-prod").hasAuthority(Role.CARD_PRODUCTION.toString())
                .antMatchers("/requests").hasAuthority(Role.MANAGEMENT.toString())
                .antMatchers("/card").hasAuthority(Role.CARD_PRODUCTION.toString())
                .antMatchers("/bins").hasAuthority(Role.MANAGEMENT.toString())
                .antMatchers("/bin-range").hasAuthority(Role.MANAGEMENT.toString())
                .antMatchers("/authorize").hasAuthority(Role.AUTHORIZATION_SYSTEM.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .permitAll()
                .defaultSuccessUrl("/greeting")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .deleteCookies("JSESSIONID");
    }
}
