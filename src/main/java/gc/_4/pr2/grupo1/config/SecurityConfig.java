package gc._4.pr2.grupo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;





@Configuration
@EnableWebSecurity
public class SecurityConfig {

		

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/img/**").permitAll().requestMatchers("/js/**")
				.permitAll().requestMatchers("/fonts/**").permitAll().requestMatchers("/css/**").permitAll()
				.requestMatchers("/uploads/**").permitAll().requestMatchers("/listproductos").permitAll()
				.requestMatchers("/").permitAll().requestMatchers("/productos").permitAll().requestMatchers("/Admin")
				.permitAll().requestMatchers("/**").hasAnyRole("ADMIN").anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/Admin").defaultSuccessUrl("/Admin/init/new"));
		return http.build();
	}

	
}
