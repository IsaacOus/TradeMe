package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.Repository;
import org.tradesman.kernel.ValueObjectId;

import java.util.ArrayList;
import java.util.Map;

public interface SubscriptionRepository extends Repository<SubscriptionId, Subscription> {
    Map<SubscriptionId, Subscription> findAll();

    ArrayList<Subscription> findByUserId(ValueObjectId userId);
}
