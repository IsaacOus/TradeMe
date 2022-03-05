package org.tradesman.use_cases.add_member.domain;

import org.tradesman.kernel.Repository;

import java.util.Map;

public interface ProviderRepository extends Repository<ProviderId,Provider> {
    Map<ProviderId, Provider> findAll();
}
