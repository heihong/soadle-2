package fr.soat.soadle.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Security Configuration  
 * 
 * @author hakim
 * 
 */
@Configuration
@EnableGlobalAuthentication
@EnableOAuth2Client
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The OAuth 2 security context
     */
    private OAuth2ClientContext oauth2ClientContext;
    
    /**
     *  Details for an OAuth2-protected resource.
     */
    private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;
    
    /**
     * Configuration properties for OAuth2 Resources.
     */
    private ResourceServerProperties resourceServerProperties;
    

    /**
     * @param oauth2ClientContext
     */
    @Autowired
    public void setOauth2ClientContext(OAuth2ClientContext oauth2ClientContext) {
        this.oauth2ClientContext = oauth2ClientContext;
    }

    /**
     * @param authorizationCodeResourceDetails
     */
    @Autowired
    public void setAuthorizationCodeResourceDetails(AuthorizationCodeResourceDetails authorizationCodeResourceDetails) {
        this.authorizationCodeResourceDetails = authorizationCodeResourceDetails;
    }

    /**
     * @param resourceServerProperties
     */
    @Autowired
    public void setResourceServerProperties(ResourceServerProperties resourceServerProperties) {
        this.resourceServerProperties = resourceServerProperties;
    }

    /**
     *  This method is for overriding the default AuthenticationManagerBuilder.
     *  We can specify how the user details are kept in the application. It may
     *  be in a database, LDAP or in memory.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    /**
     * This method is for overriding some configuration of the WebSecurity
     * If you want to ignore some request or request patterns then you can
     * specify that inside this method.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * This method is used for override HttpSecurity of the web Application.
     * We can specify our authorization criteria inside this method.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // Starts authorizing configurations.
                .authorizeRequests()
                // Ignore the "/", "/index.html and .js"
                .antMatchers("/", "/**.html", "/**.js", "/**.css", "/assets/**").permitAll()
                .and()
                .authorizeRequests()
                // Ignore swagger 
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
                // Authenticate all remaining URLs.
                .anyRequest().fullyAuthenticated()
                .and()
                // Setting the logout URL "/logout" - default logout URL.
                .logout()
                // After successful logout the application will redirect to "/" path.
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                // Setting the filter for the URL "/google/login".
                .addFilterAt(filter(), BasicAuthenticationFilter.class)
                // Disable CRSF for H2
                .csrf().disable()
                // Disable X-Frame-Options for H2
                .headers().frameOptions().disable();
    }

    /**
     * This method for creating filter for OAuth authentication.
     */
    private OAuth2ClientAuthenticationProcessingFilter filter() {
        //Creating the filter for "/google/login" url
        OAuth2ClientAuthenticationProcessingFilter oAuth2Filter = new OAuth2ClientAuthenticationProcessingFilter(
                "/google/login");

        //Creating the rest template for getting connected with OAuth service.
        //The configuration parameters will inject while creating the bean.
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails,
                oauth2ClientContext);
        oAuth2Filter.setRestTemplate(oAuth2RestTemplate);

        // Setting the token service. It will help for getting the token and
        // user details from the OAuth Service.
        oAuth2Filter.setTokenServices(new UserInfoTokenServices(resourceServerProperties.getUserInfoUri(),
                resourceServerProperties.getClientId()));

        return oAuth2Filter;
    }
    
}
