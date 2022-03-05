package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.QueryHandler;
import org.tradesman.use_cases.add_member.domain.Provider;
import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.ProviderRepository;

import java.util.Map;

public final class RetrieveProviderHandler implements QueryHandler<RetrieveProvider, Map<ProviderId, Provider>> {

    private final ProviderRepository providerRepository;

    public RetrieveProviderHandler(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Map<ProviderId, Provider> handle(RetrieveProvider query) {
        return providerRepository.findAll();
    }
}
