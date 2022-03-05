package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.Repository;

import java.util.Map;

public interface WorkmanRepository extends Repository<WorkmanId,Workman> {
    Map<WorkmanId, Workman> findAll();
}
