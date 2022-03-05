package org.tradesman.use_cases.add_member.application;

import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.kernel.ApplicationEvent;

public final class CreateWorkmanEvent implements ApplicationEvent {
    private final WorkmanId workmanId;

    public CreateWorkmanEvent(WorkmanId workmanId) {
        this.workmanId = workmanId;
    }
}
