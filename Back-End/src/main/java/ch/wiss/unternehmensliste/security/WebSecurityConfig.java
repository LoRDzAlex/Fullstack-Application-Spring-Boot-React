package ch.wiss.unternehmensliste.security;

import ch.wiss.unternehmensliste.security.jwt.AuthTokenFilter;
import ch.wiss.unternehmensliste.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 Diese Klasse konfiguriert die Web-Sicherheit für die Anwendung.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{

    //Objekt von der Klasse UserDetailsServiceImpl 'userDetailsService'
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Objekt von der Klasse AuthenticationEntryPoint 'unauthorizedHandler'
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler;

    //Array, dass die Pfade für die öffentlichen Endpunkte hat
    private final static String[] EVERYONE =
            {
                    "/api/auth/**",
                    "/api/auth/signin",
                    "/api/auth/signup",
                    "/company",
                    "/job",
                    "/contact",
                    "api/test/**"
            };

    //Array, dass die Pfade für die geschützten Endpunkte hat
    private final static String[] SECURE =
            {
                    "/question"
            };

    //Array, dass die erweiterten Rollen hat
    private final static String[] ROLES =
            {
                    "ADMIN",
                    "COMPANY",

            };

    /**

     Erstellt eine Instanz von AuthTokenFilter.
     @return Instanz von AuthTokenFilter.
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter()
    {
        return new AuthTokenFilter();
    }
    /**

     Erstellt eine Instanz von DaoAuthenticationProvider und konfiguriert die Einstellungen.
     @return Instanz von DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    /**

     Erstellt eine Instanz von AuthenticationManager.
     @param authConfig Die Konfiguration für die Authentifizierung.
     @return Instanz von AuthenticationManager.
     @throws Exception Wenn die Konfiguration fehlschlägt.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception
    {
        return authConfig.getAuthenticationManager();
    }

    /**

     Erstellt eine Instanz von PasswordEncoder.
     @return Instanz von PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**

     Konfiguriert die HTTP-Sicherheitsfilterkette.
     @param http HttpSecurity-Objekt, das die Http-Sicherheitskonfiguration speichert.
     @return Eine Instanz von SecurityFilterChain.
     @throws Exception Wenn das Erstellen der SecurityFilterChain fehlschlägt.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((authz) ->
                        authz.requestMatchers(EVERYONE).permitAll()
                                .requestMatchers(SECURE).hasAnyRole(ROLES)
                                .anyRequest().authenticated()
                );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}