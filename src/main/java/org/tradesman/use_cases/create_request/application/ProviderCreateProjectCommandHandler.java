package org.tradesman.use_cases.create_request.application;

import org.tradesman.use_cases.create_request.domain.Project;
import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.create_request.domain.ProjectRepository;
import org.tradesman.kernel.CommandHandler;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;

public final class ProviderCreateProjectCommandHandler implements CommandHandler<ProviderCreateProject,ProjectId> {
    private final ProjectRepository projectRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public ProviderCreateProjectCommandHandler(ProjectRepository projectRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.projectRepository = projectRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    //TODO verifier l'abonnement lors de la creation
    @Override
    public ProjectId handle(ProviderCreateProject providerCreateProject) {
        final ProjectId projectId = projectRepository.nextIdentity();
        Project project = new Project(projectId, providerCreateProject.adminId, providerCreateProject.name, providerCreateProject.requiredSkills, providerCreateProject.dailyPrice, providerCreateProject.deadline, providerCreateProject.location, providerCreateProject.workmanAssigned);
        projectRepository.add(project);
        eventEventDispatcher.dispatch(new ProviderCreateProjectEvent(projectId));
        return projectId;
    }
}
