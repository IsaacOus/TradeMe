package org.tradesman.use_cases.assign_to_request.infrastructure;

import org.tradesman.kernel.exception.NoSuchEntityException;
import org.tradesman.kernel.ValueObjectId;
import org.tradesman.use_cases.add_member.domain.Subscription;
import org.tradesman.use_cases.add_member.domain.SubscriptionId;
import org.tradesman.use_cases.add_member.domain.SubscriptionRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemorySubscriptionRepository implements SubscriptionRepository {
    private final Map<SubscriptionId, Subscription> data = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Map<SubscriptionId, Subscription> findAll() {
        return data;
    }

    @Override
    public ArrayList<Subscription> findByUserId(ValueObjectId userId) {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        for (Subscription subscription : data.values()) {
            if(subscription.getUserId().equals(userId)){
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }

    @Override
    public SubscriptionId nextIdentity() {
        return new SubscriptionId(count.incrementAndGet());
    }

    @Override
    public Subscription findById(SubscriptionId id) throws NoSuchEntityException {
        final Subscription subscription = data.get(id);
        if (subscription == null) {
            assert false;
            throw new RuntimeException("No provider for " + subscription);
        }
        return subscription;
    }

    @Override
    public void add(Subscription entity) {
        data.put(entity.getId(), entity);
    }

    @Override
    public void delete(SubscriptionId id) {
        data.remove(id);
    }
}
