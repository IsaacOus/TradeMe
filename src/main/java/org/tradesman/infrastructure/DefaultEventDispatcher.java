package org.tradesman.infrastructure;

import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;

public final class DefaultEventDispatcher implements EventDispatcher<Event> {
    @Override
    public void dispatch(Event event) {
        System.out.println("Dispatching Event " + event.getClass().getName());
    }
}
