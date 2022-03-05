package org.tradesman.use_cases.add_member.infrastructure;

import org.tradesman.use_cases.add_member.domain.Workman;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanRepository;
import org.tradesman.kernel.exception.NoSuchEntityException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryWorkmanRepository  implements WorkmanRepository {
    private final Map<WorkmanId, Workman> data = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Map<WorkmanId, Workman> findAll() {
        return data;
    }

    @Override
    public WorkmanId nextIdentity() {
        return new WorkmanId(count.incrementAndGet());
    }

    @Override
    public Workman findById(WorkmanId id) throws NoSuchEntityException {
        final Workman workman = data.get(id);
        if (workman == null) {
            assert false;
            throw new RuntimeException("No workman for " + workman.getName());
        }
        return workman;    }

    @Override
    public void add(Workman entity) {
        data.put(entity.getId(), entity);
    }

    @Override
    public void delete(WorkmanId id) {
        data.remove(id);
    }
}
