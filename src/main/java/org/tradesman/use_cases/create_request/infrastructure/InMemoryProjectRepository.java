package org.tradesman.use_cases.create_request.infrastructure;

import org.tradesman.kernel.exception.NoSuchEntityException;
import org.tradesman.use_cases.create_request.domain.Project;
import org.tradesman.use_cases.create_request.domain.ProjectId;
import org.tradesman.use_cases.create_request.domain.ProjectRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryProjectRepository implements ProjectRepository {
    private final Map<ProjectId, Project> data = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Map<ProjectId, Project> findAll() {
        return data;
    }

    @Override
    public ProjectId nextIdentity() {
        return new ProjectId(count.incrementAndGet());
    }

    @Override
    public Project findById(ProjectId id) throws NoSuchEntityException {
        final Project project = data.get(id);
        if (project == null) {
            assert false;
            throw new RuntimeException("No project for " + project.getName());
        }
        return project;      }

    @Override
    public void add(Project entity) {
        data.put(entity.getId(), entity);
    }

    @Override
    public void delete(ProjectId id) {
        data.remove(id);
    }
}
