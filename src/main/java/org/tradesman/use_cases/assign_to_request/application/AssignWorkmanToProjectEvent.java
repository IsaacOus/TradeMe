package org.tradesman.use_cases.assign_to_request.application;

import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.kernel.ApplicationEvent;

public final class AssignWorkmanToProjectEvent implements ApplicationEvent {
    private final ProjectId projectId;
    private final WorkmanId workmanId;

    public AssignWorkmanToProjectEvent(ProjectId projectId, WorkmanId workmanId) {
        this.projectId = projectId;
        this.workmanId = workmanId;
    }
}
