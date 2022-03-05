package org.tradesman.use_cases.assign_to_request.application;

import org.tradesman.kernel.CommandHandler;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;
import org.tradesman.kernel.exception.LocationsNotMatchException;
import org.tradesman.kernel.exception.NoSubscriptionException;
import org.tradesman.kernel.exception.PricesNotMatchException;
import org.tradesman.kernel.exception.SkillsNotMatchException;
import org.tradesman.use_cases.add_member.domain.*;
import org.tradesman.use_cases.create_request.domain.Project;
import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.create_request.domain.ProjectRepository;

import java.util.ArrayList;

public final class AssignWorkmanToProjectCommandHandler implements CommandHandler<AssignWorkmanToProject, ProjectId> {
    private final ProviderRepository providerRepository;
    private final WorkmanRepository workmanRepository;
    private final ProjectRepository projectRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public AssignWorkmanToProjectCommandHandler(ProviderRepository providerRepository, WorkmanRepository workmanRepository, ProjectRepository projectRepository, SubscriptionRepository subscriptionRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.providerRepository = providerRepository;
        this.workmanRepository = workmanRepository;
        this.projectRepository = projectRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public boolean verifySubscription(ProviderId providerId, WorkmanId workmanId) {
        ArrayList<Subscription> providerSubscriptions = this.subscriptionRepository.findByUserId(providerId);
        ArrayList<Subscription> workmanSubscriptions = this.subscriptionRepository.findByUserId(workmanId);
        if (providerSubscriptions.size() <= 0) {
            throw new NoSubscriptionException("Provider not have available subscription!");
        }
        if (workmanSubscriptions.size() <= 0) {
            throw new NoSubscriptionException("Workman not have available subscription!");
        }
        for (Subscription providerSubscription : providerSubscriptions) {
            if(providerSubscription.isValid()){
                throw new NoSubscriptionException("Provider not have available subscription!");
            }
        }
        for (Subscription workmanSubscription : workmanSubscriptions) {
            if(workmanSubscription.isValid()){
                throw new NoSubscriptionException("Workman not have available subscription!");
            }
        }
        return true;
    }

    public boolean verifySkills(ProjectId projectId, WorkmanId workmanId) {
        ArrayList<WorkmanSkills> workmanSkills = workmanRepository.findById(workmanId).getSkills();
        ArrayList<WorkmanSkills> requiredSkills = projectRepository.findById(projectId).getRequiredSkills();
        for (WorkmanSkills workmanSkill : workmanSkills) {
            boolean hasSkill = false;
            for (WorkmanSkills requiredSkill : requiredSkills) {
                if (workmanSkill == requiredSkill) {
                    hasSkill = true;
                    break;
                }
            }
            if (!hasSkill) throw new SkillsNotMatchException("Skills of workman and required do not match!");
        }
        return true;
    }

    public boolean verifyPrices(ProjectId projectId, WorkmanId workmanId) {
        float workmanDailyPrice = workmanRepository.findById(workmanId).getDailyPrice();
        float projectDailyPrice = projectRepository.findById(projectId).getDailyPrice();
        if (workmanDailyPrice >= projectDailyPrice) {
            throw new PricesNotMatchException("Daily prices of workman and project not match!");
        }
        return true;
    }

    public boolean verifyLocations(ProjectId projectId, WorkmanId workmanId) {
        String workmanLocation = workmanRepository.findById(workmanId).getLocation();
        String projectLocation = projectRepository.findById(projectId).getLocation();
        if (!workmanLocation.equals(projectLocation)) {
            throw new LocationsNotMatchException("Locations of workman and project not match!");
        }
        return true;
    }

    public boolean verifyBeforeAssign(ProviderId providerId, WorkmanId workmanId, ProjectId projectId) {
        return verifySubscription(providerId, workmanId) && verifySkills(projectId, workmanId) && verifyPrices(projectId, workmanId) && verifyLocations(projectId, workmanId);
    }

    @Override
    public ProjectId handle(AssignWorkmanToProject assignWorkmanToProject) {
        if (verifyBeforeAssign(assignWorkmanToProject.providerId, assignWorkmanToProject.workmanId, assignWorkmanToProject.projectId)) {
            Project project = projectRepository.findById(assignWorkmanToProject.projectId);
            project.addWorkmanAssigned(assignWorkmanToProject.workmanId);
            this.projectRepository.add(project);
            eventEventDispatcher.dispatch(new AssignWorkmanToProjectEvent(assignWorkmanToProject.projectId, assignWorkmanToProject.workmanId));

            return assignWorkmanToProject.projectId;
        } else {
            throw new RuntimeException("The verifications for assign workman to project not passed!");
        }
    }
}
