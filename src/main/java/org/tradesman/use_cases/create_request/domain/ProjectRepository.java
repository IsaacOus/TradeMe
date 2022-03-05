package org.tradesman.use_cases.create_request.domain;

import org.tradesman.kernel.Repository;

import java.util.Map;

public interface ProjectRepository extends Repository<ProjectId,Project> {
    Map<ProjectId, Project> findAll();
}
