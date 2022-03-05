package org.tradesman.use_cases.add_member.application;

import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.kernel.ApplicationEvent;

public final class CreateProviderEvent implements ApplicationEvent {
    private final ProviderId providerId;

    public CreateProviderEvent(ProviderId providerId) {
        this.providerId = providerId;
    }
}
