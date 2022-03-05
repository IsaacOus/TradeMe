package org.tradesman.exposition;

import org.tradesman.use_cases.add_member.application.RetrieveWorkman;
import org.tradesman.use_cases.add_member.application.RetrieveWorkmanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class WorkmanController {
    private final RetrieveWorkmanHandler retrieveWorkmanHandler;

    @Autowired
    private WorkmanController(RetrieveWorkmanHandler retrieveWorkmanHandler) {
        this.retrieveWorkmanHandler = retrieveWorkmanHandler;
    }

    @GetMapping(value = "/workmans/all")
    public String getAll() {
        return retrieveWorkmanHandler.handle(new RetrieveWorkman()).toString();
    }
}
