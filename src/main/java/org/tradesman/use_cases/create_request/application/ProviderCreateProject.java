package org.tradesman.use_cases.create_request.application;

import org.tradesman.kernel.Command;
import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanSkills;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public final class ProviderCreateProject implements Command {
    public final ProviderId adminId;
    public final String name;
    public final ArrayList<WorkmanSkills> requiredSkills;
    public final float dailyPrice ;
    public final Date deadline;
    public final String location;
    public final ArrayList<WorkmanId> workmanAssigned ;

    public ProviderCreateProject(ProviderId adminId, String name, ArrayList<WorkmanSkills> requiredSkills, float dailyPrice, Date deadline, String location, ArrayList<WorkmanId> workmanAssigned) {
        this.adminId = adminId;
        this.name = Objects.requireNonNull(name);
        this.requiredSkills = requiredSkills;
        this.dailyPrice = dailyPrice;
        this.deadline = deadline;
        this.location = location;
        this.workmanAssigned = workmanAssigned;
    }

    public static ProviderCreateProject of(ProviderId adminId, String name, ArrayList<WorkmanSkills> requiredSkills, float dailyPrice, Date deadline, String location, ArrayList<WorkmanId> workmanAssigned){
        return new ProviderCreateProject(adminId, name, requiredSkills, dailyPrice, deadline, location, workmanAssigned);
    }


}
