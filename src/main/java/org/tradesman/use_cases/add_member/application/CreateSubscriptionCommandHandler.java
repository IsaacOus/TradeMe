package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.CommandHandler;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;
import org.tradesman.use_cases.add_member.domain.Subscription;
import org.tradesman.use_cases.add_member.domain.SubscriptionId;
import org.tradesman.use_cases.add_member.domain.SubscriptionRepository;

public final class CreateSubscriptionCommandHandler implements CommandHandler<CreateSubscription, SubscriptionId> {
    private final SubscriptionRepository subscriptionRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateSubscriptionCommandHandler(SubscriptionRepository subscriptionRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.subscriptionRepository = subscriptionRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public SubscriptionId handle(CreateSubscription createSubscription) {
        final SubscriptionId subscriptionId = subscriptionRepository.nextIdentity();
        Subscription subscription = new Subscription(subscriptionId, createSubscription.userID, createSubscription.startingDate, createSubscription.duration, createSubscription.monthlyPrice);
        subscriptionRepository.add(subscription);
        eventEventDispatcher.dispatch(new CreateSubscriptionEvent(subscriptionId));
        return subscriptionId;
    }
}

