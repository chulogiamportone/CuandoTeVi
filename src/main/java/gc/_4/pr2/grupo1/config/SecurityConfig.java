package gc._4.pr2.grupo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;
    

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
		.formLogin(login -> login.loginPage("/Admin/init/new").defaultSuccessUrl("/Admin/init/new")).csrf(AbstractHttpConfigurer::disable);
				/*requestMatchers("/img/**").permitAll().requestMatchers("/js/**")
				.permitAll().requestMatchers("/fonts/**").permitAll().requestMatchers("/css/**").permitAll()
				.requestMatchers("/uploads/**").permitAll()
				.requestMatchers("/Adminget").permitAll()
				.requestMatchers("/listproductos").permitAll()
				.requestMatchers("/").permitAll().requestMatchers("/productos").permitAll()
				.requestMatchers("/Admin").permitAll()
				.requestMatchers(HttpMethod.POST,"/Admin","/Admin/init/new").permitAll()
				.requestMatchers("/**")
				.hasAnyRole("ADMIN").anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/Admin").defaultSuccessUrl("/Admin/init/new"));*/
		return http.build();
	}
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(userAuthProvider()) // add your custom authentication provider(s)
            .build();
    }
    
    @Bean
    public DaoAuthenticationProvider userAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService); // inject your user details service
        return provider;
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
