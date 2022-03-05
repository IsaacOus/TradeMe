package org.tradesman.use_cases.create_request.application;

import org.tradesman.use_cases.create_request.domain.Project;
import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.create_request.domain.ProjectRepository;
import org.tradesman.kernel.QueryHandler;

import java.util.Map;

public final class RetrieveProjectHandler implements QueryHandler<RetrieveProject, Map<ProjectId,Project>> {

    private final ProjectRepository projectRepository;

    public RetrieveProjectHandler(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Map<ProjectId, Project> handle(RetrieveProject query) {
        return  projectRepository.findAll();
    }
}
