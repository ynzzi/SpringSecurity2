package com.example.securityEx01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정하는 클래스들에 붙여주는 어노테이션
@EnableWebSecurity  // 인가
public class SecurityConfig {
	
	@Bean // 이 메서드의 반환 자료가 Bean으로 등록 -> http.build()가 Bean으로 등록되나?
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Security의 FilterChain을 반환하고 함수형은 filterChain이고
		http.authorizeHttpRequests(auth -> auth
				.anyRequest().authenticated() //root 경로는 permitALl(모두에게 개방해라) -> 보안 기능을 넣지 않겠다
				// anyRequest는 모든 요청을 허용하겠다
				);
		http.formLogin(auth -> auth
				.loginPage("/login")
				.loginProcessingUrl("/loginProc")
				.defaultSuccessUrl("/welcome")
				.permitAll()
				);
		// http.formLogin(Customizer.withDefaults());
		// http.httpBasic(Customizer.withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		
		http.logout(Customizer.withDefaults()); // 기본 /logout 처리
		return http.build(); //http.build()
	}
}