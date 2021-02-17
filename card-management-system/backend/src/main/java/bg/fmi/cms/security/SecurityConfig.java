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
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
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
        authProvider.setAuthoritiesMapper(authoritiesMapper());
        return authProvider;
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setPrefix("ROLE_"); // this line is not required
        mapper.setConvertToUpperCase(true); // convert your roles to uppercase
        mapper.setDefaultAuthority("USER"); // set a default role

        return mapper;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/settings").hasAnyAuthority()
                .antMatchers("/users/**").hasRole(Role.ADMIN.toString())
                .antMatchers("/card-prod").hasRole(Role.CARD_PRODUCTION.toString())
                .antMatchers("/requests").hasAnyRole(Role.MANAGEMENT.toString(), Role.AUDIT.toString())
                .antMatchers("/card").hasRole(Role.CARD_PRODUCTION.toString())
                .antMatchers("/bins").hasRole(Role.MANAGEMENT.toString())
                .antMatchers("/bin-range").hasRole(Role.MANAGEMENT.toString())
                .antMatchers("/keys").hasRole(Role.MANAGEMENT.toString())
                .antMatchers("/authorize").hasRole(Role.AUTHORIZATION_SYSTEM.toString())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
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
