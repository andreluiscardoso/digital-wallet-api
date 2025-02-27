package com.andreluiscardoso.digitalwallet.infrastructure.config;

import com.andreluiscardoso.digitalwallet.infrastructure.component.WalletProvider;
import com.andreluiscardoso.digitalwallet.infrastructure.config.properties.WalletProviderProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WalletProviderProperties.class)
public class WalletProviderConfig {

    @Bean
    public WalletProvider walletProvider(WalletProviderProperties properties) {
        return new WalletProvider(properties.agency(), properties.bank(), properties.name());
    }
}
