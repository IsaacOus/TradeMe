package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.Command;

import java.util.Objects;

public final class CreateProvider implements Command {
    public final String name;

    public CreateProvider(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public static CreateProvider of(String name){
        return new CreateProvider(name);
    }
}
