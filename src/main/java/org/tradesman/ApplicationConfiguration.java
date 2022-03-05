package org.tradesman;


import org.tradesman.use_cases.add_member.infrastructure.InMemoryProviderRepository;
import org.tradesman.use_cases.add_member.infrastructure.InMemoryWorkmanRepository;
import org.tradesman.use_cases.assign_to_request.application.AssignWorkmanToProjectCommandHandler;
import org.tradesman.use_cases.assign_to_request.infrastructure.InMemorySubscriptionRepository;
import org.tradesman.use_cases.create_request.application.ProviderCreateProjectCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateProviderCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateSubscriptionCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateWorkmanCommandHandler;
import org.tradesman.use_cases.create_request.application.RetrieveProjectHandler;
import org.tradesman.use_cases.add_member.application.RetrieveProviderHandler;
import org.tradesman.use_cases.add_member.application.RetrieveWorkmanHandler;
import org.tradesman.use_cases.create_request.domain.ProjectRepository;
import org.tradesman.use_cases.add_member.domain.ProviderRepository;
import org.tradesman.use_cases.add_member.domain.SubscriptionRepository;
import org.tradesman.use_cases.add_member.domain.WorkmanRepository;
import org.tradesman.infrastructure.*;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;
import org.tradesman.use_cases.create_request.infrastructure.InMemoryProjectRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        return new DefaultEventDispatcher();
    }

    @Bean
    public ProviderRepository providerRepository() {
        return new InMemoryProviderRepository();
    }

    @Bean
    public ProjectRepository projectRepository() {
        return new InMemoryProjectRepository();
    }

    @Bean
    public WorkmanRepository workmanRepository() {
        return new InMemoryWorkmanRepository();
    }

    @Bean
    public SubscriptionRepository subscriptionRepository() {
        return new InMemorySubscriptionRepository();
    }

    @Bean
    public ProviderCreateProjectCommandHandler providerCreateProjectCommandHandler() {
        return new ProviderCreateProjectCommandHandler(projectRepository(), eventEventDispatcher());
    }

    @Bean
    public CreateWorkmanCommandHandler createWorkmanCommandHandler() {
        return new CreateWorkmanCommandHandler(workmanRepository(), eventEventDispatcher());
    }

    @Bean
    public CreateProviderCommandHandler createProviderCommandHandler() {
        return new CreateProviderCommandHandler(providerRepository(), eventEventDispatcher());
    }

    @Bean
    public AssignWorkmanToProjectCommandHandler assignWorkmanToProjectCommandHandler(){
        return new AssignWorkmanToProjectCommandHandler(providerRepository(),workmanRepository(),projectRepository(), subscriptionRepository(), eventEventDispatcher());
    }

    @Bean
    public CreateSubscriptionCommandHandler createSubscriptionCommandHandler(){
        return new CreateSubscriptionCommandHandler(subscriptionRepository(),eventEventDispatcher());
    }

    @Bean
    public RetrieveProjectHandler retrieveProjectHandler() {
        return new RetrieveProjectHandler(projectRepository());
    }

    @Bean
    public RetrieveWorkmanHandler retrieveWorkmanHandler() {
        return new RetrieveWorkmanHandler(workmanRepository());
    }

    @Bean
    public RetrieveProviderHandler retrieveProviderHandler() { return new RetrieveProviderHandler(providerRepository()); }
}
