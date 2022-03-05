package org.tradesman.exposition;

import org.tradesman.use_cases.create_request.application.RetrieveProject;
import org.tradesman.use_cases.create_request.application.RetrieveProjectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ProjectController {

    private final RetrieveProjectHandler retrieveProjectHandler;

    @Autowired
    private ProjectController(RetrieveProjectHandler retrieveProjectHandler) {
        this.retrieveProjectHandler = retrieveProjectHandler;
    }

    @GetMapping(value = "/projects/all")
    public String getAll() {
        return retrieveProjectHandler.handle(new RetrieveProject()).toString();
    }
}
