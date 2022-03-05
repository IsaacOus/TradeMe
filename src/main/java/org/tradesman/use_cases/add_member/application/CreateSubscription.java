package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.Command;
import org.tradesman.kernel.ValueObjectId;

import java.util.Date;

public final class CreateSubscription implements Command {
    public final ValueObjectId userID;
    public final Date startingDate;
    public final int duration;
    public final float monthlyPrice;

    public CreateSubscription(ValueObjectId userID, Date startingDate, int duration, float monthlyPrice) {
        this.userID = userID;
        this.startingDate = startingDate;
        this.duration = duration;
        this.monthlyPrice = monthlyPrice;
    }
}
