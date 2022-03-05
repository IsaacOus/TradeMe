package org.tradesman.use_cases.add_member.application;

import org.tradesman.kernel.QueryHandler;
import org.tradesman.use_cases.add_member.domain.Workman;
import org.tradesman.use_cases.add_member.domain.WorkmanId;
import org.tradesman.use_cases.add_member.domain.WorkmanRepository;

import java.util.Map;

public final class RetrieveWorkmanHandler implements QueryHandler<RetrieveWorkman, Map<WorkmanId, Workman>> {

    private final WorkmanRepository workmanRepository;

    public RetrieveWorkmanHandler(WorkmanRepository workmanRepository) {
        this.workmanRepository = workmanRepository;
    }

    @Override
    public Map<WorkmanId, Workman> handle(RetrieveWorkman query) {
        return workmanRepository.findAll();
    }
}
