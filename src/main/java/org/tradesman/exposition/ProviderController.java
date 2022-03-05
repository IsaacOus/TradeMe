package org.tradesman.exposition;

import org.tradesman.use_cases.add_member.application.RetrieveProvider;
import org.tradesman.use_cases.add_member.application.RetrieveProviderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ProviderController {
    private final RetrieveProviderHandler retrieveProviderHandler;

    @Autowired
    private ProviderController(RetrieveProviderHandler retrieveProviderHandler) {
        this.retrieveProviderHandler = retrieveProviderHandler;
    }

    @GetMapping(value = "/providers/all")
    public String getAll() {
        return retrieveProviderHandler.handle(new RetrieveProvider()).toString();
    }
}
