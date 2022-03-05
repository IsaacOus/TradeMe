package org.tradesman.use_cases.add_member.domain;

import java.util.Objects;

public final class Provider {
    private final ProviderId id;
    private final String name;

    public Provider(ProviderId id, String name) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
    }

    public static Provider of(ProviderId id, String name){
        return new Provider(id, name);
    }

    public ProviderId getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
