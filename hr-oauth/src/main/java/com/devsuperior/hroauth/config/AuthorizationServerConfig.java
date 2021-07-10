package com.devsuperior.hroauth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${oauth.client.name}")
    private String MYAPPNAME;
    @Value("${oauth.client.secret}")
    private String MYAPPSECRET;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final JwtTokenStore tokenStore;
    private final AuthenticationManager authenticationManager;

    public AuthorizationServerConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                                     JwtAccessTokenConverter jwtAccessTokenConverter,
                                     JwtTokenStore tokenStore,
                                     AuthenticationManager authenticationManager) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permiteAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(MYAPPNAME)
                .secret(bCryptPasswordEncoder.encode(MYAPPSECRET))
                .scopes("read", "write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(86400);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
    }
}
