package org.tradesman.use_cases.add_member.application;

import org.tradesman.use_cases.add_member.domain.WorkmanSkills;
import org.tradesman.kernel.Command;

import java.util.ArrayList;

public final class CreateWorkman implements Command {
    public final String name;
    public final ArrayList<WorkmanSkills> skills;
    public final String location;
    public final float dailyPrice;


    public CreateWorkman(String name, ArrayList<WorkmanSkills> skills, String location, float dailyPrice) {
        this.name = name;
        this.skills = skills;
        this.location = location;
        this.dailyPrice = dailyPrice;
    }

    public static CreateWorkman of(String name, ArrayList<WorkmanSkills> skills, String location, float dailyPrice){
        return new CreateWorkman(name, skills, location, dailyPrice);
    }
}
