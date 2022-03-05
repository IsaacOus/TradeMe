package org.tradesman.use_cases.add_member.application;

import org.tradesman.use_cases.add_member.domain.SubscriptionId;
import org.tradesman.kernel.ApplicationEvent;

public final class CreateSubscriptionEvent implements ApplicationEvent {
    private final SubscriptionId subscriptionId;

    public CreateSubscriptionEvent(SubscriptionId subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
