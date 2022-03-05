package org.tradesman.use_cases.create_request.domain;

import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanSkills;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public final class Project {
    private final ProjectId id;
    private final ProviderId adminId;
    private final String name;
    private final ArrayList<WorkmanSkills> requiredSkills;
    private final float dailyPrice;
    private final Date deadline;
    private final String location;

    private ArrayList<WorkmanId> workmanAssigned;

    public Project(ProjectId id, ProviderId adminId, String name, ArrayList<WorkmanSkills> requiredSkills, float dailyPrice, Date deadline, String location, ArrayList<WorkmanId> workmanAssigned) {
        this.id = Objects.requireNonNull(id);
        this.adminId = adminId;
        this.name = Objects.requireNonNull(name);
        this.requiredSkills = requiredSkills;
        this.dailyPrice = dailyPrice;
        this.deadline = deadline;
        this.location = location;
        this.workmanAssigned = workmanAssigned;
    }

    public static Project of(ProjectId id, ProviderId adminId, String name, ArrayList<WorkmanSkills> requiredSkills, float dailyPrice, Date deadline, String location, ArrayList<WorkmanId> workmanAssigned) {
        return new Project(id, adminId, name, requiredSkills, dailyPrice, deadline, location, workmanAssigned);
    }

    public ProjectId getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<WorkmanId> getWorkmanAssigned() {
        return workmanAssigned;
    }

    public void addWorkmanAssigned(WorkmanId workmanId) {
        this.workmanAssigned.add(workmanId);
    }

    public ArrayList<WorkmanSkills> getRequiredSkills() {
        return this.requiredSkills;
    }

    public float getDailyPrice() {
        return this.dailyPrice;
    }

    public String getLocation() {
        return this.location;
    }
}
