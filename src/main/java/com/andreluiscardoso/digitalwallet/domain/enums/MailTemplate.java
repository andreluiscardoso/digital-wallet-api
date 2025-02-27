package com.andreluiscardoso.digitalwallet.domain.enums;

import lombok.Getter;

@Getter
public enum MailTemplate {
    TEST_HTML("mail/test-email.html"),
    TEST_TXT("mail/test-email.txt");

    private final String path;

    MailTemplate(String path) {
        this.path = path;
    }
}
