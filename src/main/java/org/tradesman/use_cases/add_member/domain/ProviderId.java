package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.ValueObjectId;

import java.util.Objects;

public final class ProviderId implements ValueObjectId {
    private final int value;

    public ProviderId(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderId id = (ProviderId) o;
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
        return "ProviderId{"+this.getValue()+"}";
    }
}
