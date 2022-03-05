package org.tradesman.use_cases.create_request.application;

import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.kernel.ApplicationEvent;

public final class ProviderCreateProjectEvent implements ApplicationEvent {
    private final ProjectId projectId;

    public ProviderCreateProjectEvent(ProjectId projectId) {
        this.projectId = projectId;
    }
}
