package org.tradesman;

import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.SubscriptionId;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanSkills;
import org.tradesman.use_cases.assign_to_request.application.AssignWorkmanToProject;
import org.tradesman.use_cases.assign_to_request.application.AssignWorkmanToProjectCommandHandler;
import org.tradesman.use_cases.create_request.application.ProviderCreateProject;
import org.tradesman.use_cases.create_request.application.ProviderCreateProjectCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateProvider;
import org.tradesman.use_cases.add_member.application.CreateProviderCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateSubscription;
import org.tradesman.use_cases.add_member.application.CreateSubscriptionCommandHandler;
import org.tradesman.use_cases.add_member.application.CreateWorkman;
import org.tradesman.use_cases.add_member.application.CreateWorkmanCommandHandler;
import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);

        CreateProviderCommandHandler providerCommandHandler = applicationContext.getBean(CreateProviderCommandHandler.class);
        CreateProvider createProvider = new CreateProvider("Christophe");
        final ProviderId providerId = providerCommandHandler.handle(createProvider);

        Calendar startingSubscriptionDate =  Calendar.getInstance();
        startingSubscriptionDate.set(2022, Calendar.JANUARY, 1);

        CreateSubscriptionCommandHandler subscriptionCommandHandler = applicationContext.getBean(CreateSubscriptionCommandHandler.class);
        CreateSubscription createProviderSubscription = new CreateSubscription(providerId, startingSubscriptionDate.getTime(), 10,10);
        final SubscriptionId providerSubscriptionId = subscriptionCommandHandler.handle(createProviderSubscription);

        CreateWorkmanCommandHandler workmanCommandHandler = applicationContext.getBean(CreateWorkmanCommandHandler.class);
        ArrayList<WorkmanSkills> workmanSkills = new ArrayList<>();
        workmanSkills.add(WorkmanSkills.ELECTRICIAN);
        CreateWorkman createWorkman = new CreateWorkman("Christophe",workmanSkills ,"Paris",60);
        final WorkmanId workmanId = workmanCommandHandler.handle(createWorkman);


        CreateSubscription createWorkmanSubscription = new CreateSubscription(workmanId, startingSubscriptionDate.getTime() , 10,10);
        final SubscriptionId workmanSubscriptionId = subscriptionCommandHandler.handle(createWorkmanSubscription);

        ProviderCreateProjectCommandHandler providerCreateProjectCommandHandler = applicationContext.getBean(ProviderCreateProjectCommandHandler.class);
        ArrayList<WorkmanSkills> requiredSkills = new ArrayList<>();
        requiredSkills.add(WorkmanSkills.ELECTRICIAN);
        ProviderCreateProject providerCreateProject = new ProviderCreateProject(providerId,"project1",requiredSkills, 70, new Date(),"Paris",new ArrayList<WorkmanId>());
        final ProjectId projectId = providerCreateProjectCommandHandler.handle(providerCreateProject);

        AssignWorkmanToProjectCommandHandler assignWorkmanToProjectCommandHandler = applicationContext.getBean(AssignWorkmanToProjectCommandHandler.class);
        AssignWorkmanToProject assignWorkmanToProject = new AssignWorkmanToProject(providerId,workmanId,projectId);
        final ProjectId assignedProjectId = assignWorkmanToProjectCommandHandler.handle(assignWorkmanToProject);


    }
}
