package org.tradesman.use_cases.add_member.infrastructure;

import org.tradesman.kernel.exception.NoSuchEntityException;
import org.tradesman.use_cases.add_member.domain.Provider;
import org.tradesman.use_cases.add_member.domain.ProviderId;
import org.tradesman.use_cases.add_member.domain.ProviderRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryProviderRepository implements ProviderRepository {
    private final Map<ProviderId, Provider> data = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Map<ProviderId, Provider> findAll() {
        return data;
    }

    @Override
    public ProviderId nextIdentity() {
        return new ProviderId(count.incrementAndGet());
    }

    @Override
    public Provider findById(ProviderId id) throws NoSuchEntityException {
        final Provider provider = data.get(id);
        if (provider == null) {
            assert false;
            throw new RuntimeException("No provider for " + provider.getName());
        }
        return provider;
    }

    @Override
    public void add(Provider entity) {
        data.put(entity.getId(), entity);
    }

    @Override
    public void delete(ProviderId id) {
        data.remove(id);
    }
}
