package com.andreluiscardoso.digitalwallet.util;

import com.andreluiscardoso.digitalwallet.domain.enums.MailTemplate;
import org.thymeleaf.context.Context;

public record Mail(
        Context context,
        MailTemplate template,
        String sender,
        String recipient,
        String subject,
        String message) {
}
