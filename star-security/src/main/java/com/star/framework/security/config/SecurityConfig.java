/**create by liuhua at 2017年5月19日 上午11:51:49**/
package com.star.framework.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.star.framework.security.core.RestAuthenticationFailureHandler;
import com.star.framework.security.core.StarAccessDecisionManager;
import com.star.framework.security.core.StarAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
//		web.ignoring().antMatchers("/static/**", "/swagger/**", "/**/*.jsp", "/bui/*.css", "/bui/*.js", "/bui/**/*.css", "/bui/**/*.js");
		web.ignoring().antMatchers("/*.txt", "/swagger-resources/**", "/swagger-ui.html","/webjars/**", "/common/**", "/intro**", "/lottery**", "/o**", "/policy**", "/api/**", "/index**", "/index/**", "/petitioner/**", "/v2/api-docs", "/favicon.ico", "/static/**", "/px/**", "/swagger/**", "/**/*.jsp", "/bui/*?/**/*.*");
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.authorizeRequests().antMatchers("/interface/**").permitAll()
	// .antMatchers("/mgr/**").access("hasAuthority('USER')")
	// .antMatchers("/mgr/**").fullyAuthenticated()
	// .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
	//// .successHandler(new RestAuthenticationSuccessHandler())
	//// .failureHandler(new RestAuthenticationFailureHandler())
	//// .and().rememberMe().rememberMeParameter("remember-me").rememberMeCookieName("remember-me")
	// .and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true)
	// .and().and().logout().logoutUrl("/logout")
	//// .logoutSuccessHandler(new RestLogoutSuccessHandler())
	// .and().exceptionHandling()
	//// .authenticationEntryPoint(new RestAuthenticationEntryPoint())
	//// .accessDeniedHandler(new RestAccessDeniedHandler())
	// ;
	//
	// // session管理
	//// http.sessionManagement().sessionFixation().changeSessionId().maximumSessions(1).expiredUrl("/");
	//
	// // RemeberMe
	//// http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
	//
	// }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {  
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {  
                fsi.setSecurityMetadataSource(starFilterInvocationSecurityMetadataSource);
                fsi.setAccessDecisionManager(accessDecisionManager());
                try {
					fsi.setAuthenticationManager(authenticationManagerBean());
				} catch (Exception e) {
					e.printStackTrace();
				}  
                return fsi;  
            }  
        }).and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).and().logout().logoutSuccessUrl("/").permitAll();  
        // 自定义accessDecisionManager访问控制器,并开启表达式语言  
        //http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());  
  
//		http.authorizeRequests().antMatchers("/**").authenticated().antMatchers("/login*").permitAll();
        // 自定义登录页面  
//        http.csrf().disable();  
        
		// 自定义登录页面
				http.csrf().disable().formLogin()
					.loginPage("/login")
//					.failureUrl("/login?error=1")
					.loginProcessingUrl("/j_spring_security_check")
//					.successHandler(new RestAuthenticationSuccessHandler())
					.failureHandler(new RestAuthenticationFailureHandler("/login"))
//					.failureForwardUrl("/login")
					.usernameParameter("j_username")
					.passwordParameter("j_password").permitAll()
					.and().exceptionHandling()
//					.accessDeniedHandler(new RestAccessDeniedHandler())
					.accessDeniedPage("/accessDenied.jsp");
				
//		// 自定义accessDecisionManager访问控制器,并开启表达式语言
//		http.authorizeRequests()
////			.accessDecisionManager(accessDecisionManager())
////			.expressionHandler(webSecurityExpressionHandler())
//		
//			.antMatchers("/mgr/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEST")//.hasAnyRole("ADMIN", "USER")
////			.antMatchers("/interface/**").fullyAuthenticated()
//			.antMatchers("/login").permitAll()
//			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//
//				@Override
//				public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//					object.setObserveOncePerRequest(false);
//					object.setAccessDecisionManager(starAccessDecisionManager);
//					object.setSecurityMetadataSource(starFilterInvocationSecurityMetadataSource);
//					return object;
//				}
//			})
//			.and().exceptionHandling().accessDeniedPage("/login");

		http.headers().frameOptions().disable();
		// 自定义注销
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true);

		// session管理
		http.sessionManagement().sessionFixation().changeSessionId().maximumSessions(1).expiredUrl("/");
		// RemeberMe
		http.rememberMe().rememberMeParameter("remember-me").rememberMeCookieName("remember-me").key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
	}
	
	@Bean  
	AccessDeniedHandler accessDeniedHandler() {  
		AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();  
		accessDeniedHandler.setErrorPage("/securityException/accessDenied");  
		return accessDeniedHandler;  
	}
	
	/* 
     *  
     * 这里可以增加自定义的投票器 
     */  
    @Bean(name = "starAccessDecisionManager")  
    public AccessDecisionManager accessDecisionManager() {  
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();  
        decisionVoters.add(new RoleVoter());  
        decisionVoters.add(new AuthenticatedVoter());  
        decisionVoters.add(webExpressionVoter());// 启用表达式投票器  
        StarAccessDecisionManager accessDecisionManager = new StarAccessDecisionManager(decisionVoters);  
        return accessDecisionManager;  
    }  
    
    /* 
     * 表达式控制器 
     */  
    @Bean(name = "expressionHandler")  
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {  
        DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();  
        return webSecurityExpressionHandler;  
    }  
    
    /* 
     * 表达式投票器 
     */  
    @Bean(name = "expressionVoter")  
    public WebExpressionVoter webExpressionVoter() {  
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();  
        webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());  
        return webExpressionVoter;  
    }  
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置内存用户角色
		// auth.inMemoryAuthentication().withUser("user").password("123456")
		// .roles("USER").and().withUser("admin").password("654321")
		// .roles("USER", "ADMIN");

		// 自定义UserDetailsService
		
		auth.userDetailsService(securtityUserServiceDetails).passwordEncoder(new Md5PasswordEncoder());
		auth.authenticationProvider(starAuthenticationProvider);

	}
	
	@Autowired
	private UserDetailsService securtityUserServiceDetails;
	
	@Autowired
	private StarAuthenticationProvider starAuthenticationProvider;
	
	@Autowired
	private FilterInvocationSecurityMetadataSource starFilterInvocationSecurityMetadataSource;
}
