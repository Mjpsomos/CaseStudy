package tesksystems.psomos_michael_casestudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tesksystems.psomos_michael_casestudy.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //Authorized and non-authorized can access these pages
                .antMatchers("/pub/**", "/error/**", "/login/**", "/index").permitAll()
                //only authorized users can access these pages.
                .antMatchers("/admin/**", "/user/**", "/meetuppost/**","/wateractivity/**").authenticated()
                .and()
                .formLogin()
                // this is the URL of the login page
                .loginPage("/login/login")
                // this is the URL where the login page will submit
                .loginProcessingUrl("/login/loginSubmit")
                //After a succesfull log in user will be directed to user profile
                .defaultSuccessUrl("/user/profile")
                .and()
                .logout()
                .invalidateHttpSession(true)
                // this is the URL to log the user out
                .logoutUrl("/login/logout")
                // the URL that the user goes to after they log out
                .logoutSuccessUrl("/login/login")
                .and()
                .exceptionHandling()
                //IF there is a stack trace error or path error you will be directed to corresponding error page
                .accessDeniedPage("/error/**");
    }


    @Bean
    // Set user authentication details through spring security
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Override
    //Configures if the users details are authenticated
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean(name = "passwordEncoder")
    //Reads encrypted passwords
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

