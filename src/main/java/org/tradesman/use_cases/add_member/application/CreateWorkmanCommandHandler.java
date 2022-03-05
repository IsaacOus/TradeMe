package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.CommandHandler;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;
import org.tradesman.use_cases.add_member.domain.Workman;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanRepository;

public final class CreateWorkmanCommandHandler implements CommandHandler<CreateWorkman, WorkmanId> {
    private final WorkmanRepository workmanRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateWorkmanCommandHandler(WorkmanRepository workmanRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.workmanRepository = workmanRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public WorkmanId handle(CreateWorkman createWorkman) {
        final WorkmanId workmanId = workmanRepository.nextIdentity();
        Workman workman = new Workman(workmanId, createWorkman.name, createWorkman.skills, createWorkman.location, createWorkman.dailyPrice);
        workmanRepository.add(workman);
        eventEventDispatcher.dispatch(new CreateWorkmanEvent(workmanId));
        return workmanId;
    }
}
