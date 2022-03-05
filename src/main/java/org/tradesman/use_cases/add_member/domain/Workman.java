package org.tradesman.use_cases.add_member.domain;

import java.util.ArrayList;
import java.util.Objects;

public final class Workman {
    private final WorkmanId id;
    private final String name;
    private final ArrayList<WorkmanSkills> skills;
    private final String location;
    private final float dailyPrice;


    public Workman(WorkmanId id, String name, ArrayList<WorkmanSkills> skills, String location, float dailyPrice) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.skills = skills;
        this.location = location;
        this.dailyPrice = dailyPrice;
    }

    public static Workman of(WorkmanId id, String name, ArrayList<WorkmanSkills> skills, String location, float dailyPrice) {
        return new Workman(id, name, skills, location, dailyPrice);
    }

    public WorkmanId getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<WorkmanSkills> getSkills() {
        return this.skills;
    }

    public float getDailyPrice() {
        return this.dailyPrice;
    }

    public String getLocation() {
        return this.location;
    }
}
