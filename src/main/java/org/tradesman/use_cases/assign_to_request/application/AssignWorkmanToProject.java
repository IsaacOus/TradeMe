package org.tradesman.use_cases.assign_to_request.application;

import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.kernel.Command;

public final class AssignWorkmanToProject implements Command {
    public final ProviderId providerId;
    public final WorkmanId workmanId;
    public final ProjectId projectId;

    public AssignWorkmanToProject(ProviderId providerId, WorkmanId workmanId, ProjectId projectId) {
        this.providerId = providerId;
        this.workmanId = workmanId;
        this.projectId = projectId;
    }

    public static AssignWorkmanToProject of(ProviderId providerId, WorkmanId workmanId, ProjectId projectId){
        return new AssignWorkmanToProject(providerId, workmanId, projectId);
    }
}
