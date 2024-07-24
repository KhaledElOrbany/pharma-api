package eg.pharma.api.helpers.services;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageService {

    private final ResourceBundleMessageSource messageSource;

    public MessageService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
