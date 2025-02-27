package com.andreluiscardoso.digitalwallet.infrastructure.config.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "api.provider")
public record WalletProviderProperties(
        @NotBlank String agency,
        @NotBlank String bank,
        @NotBlank String name) {
}
