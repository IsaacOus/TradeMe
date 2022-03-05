package org.tradesman.use_cases.create_request.domain;

import org.tradesman.kernel.ValueObjectId;

import java.util.Objects;

public final class ProjectId implements ValueObjectId {
    private final int value;

    public ProjectId(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId id = (ProjectId) o;
        return value == id.value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProjectId{"+this.getValue()+"}";
    }
}
