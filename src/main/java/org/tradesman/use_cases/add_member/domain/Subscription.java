package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.ValueObjectId;

import java.util.Calendar;
import java.util.Date;

public final class Subscription {
    private final SubscriptionId id;
    private final ValueObjectId userId;
    private final Date startingDate;
    private final int duration;
    private final float monthlyPrice;

    public Subscription(SubscriptionId id, ValueObjectId userId, Date startingDate, int duration, float monthlyPrice) {
        this.id = id;
        this.userId = userId;
        this.startingDate = startingDate;
        this.duration = duration;
        this.monthlyPrice = monthlyPrice;
    }

    public SubscriptionId getId() {
        return this.id;
    }

    public ValueObjectId getUserId() {
        return this.userId;
    }

    public boolean isValid(){
        Date now = new Date();
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(this.startingDate);
        calendar.add(Calendar.MONTH, this.duration);
        return !now.before(calendar.getTime()) || !now.after(this.startingDate);
    }
}
