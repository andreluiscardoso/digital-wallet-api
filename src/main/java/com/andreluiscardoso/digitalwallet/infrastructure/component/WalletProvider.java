package com.andreluiscardoso.digitalwallet.infrastructure.component;

import org.springframework.stereotype.Component;

@Component
public record WalletProvider(String agency, String bank, String description) {
}
