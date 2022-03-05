package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.ValueObjectId;

import java.util.Objects;

public final class SubscriptionId implements ValueObjectId {
    private final int value;

    public SubscriptionId(int value) {
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId id = (SubscriptionId) o;
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
        return "SubscriptionId{"+this.getValue()+"}";
    }
}
