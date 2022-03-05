package org.tradesman.use_cases.add_member.application;

import org.tradesman.use_cases.add_member.domain.Provider;
import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.ProviderRepository;
import org.tradesman.kernel.CommandHandler;
import org.tradesman.kernel.Event;
import org.tradesman.kernel.EventDispatcher;

public final class CreateProviderCommandHandler implements CommandHandler<CreateProvider, ProviderId> {
    private final ProviderRepository providerRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateProviderCommandHandler(ProviderRepository providerRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.providerRepository = providerRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public ProviderId handle(CreateProvider createProvider) {
        final ProviderId providerId = providerRepository.nextIdentity();
        Provider provider = new Provider(providerId, createProvider.name);
        providerRepository.add(provider);
        eventEventDispatcher.dispatch(new CreateProviderEvent(providerId));
        return providerId;
    }
}
