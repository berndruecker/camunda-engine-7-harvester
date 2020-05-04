package io.berndruecker.camunda.harvest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.berndruecker.camunda.harvest.domain.CamundaEngineInfo;
import io.berndruecker.camunda.harvest.repository.CamundaEngineRepository;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    @Autowired
    private CamundaEngineRepository engineRepository;

    @GetMapping(path = "/engine/{id}")
    public CamundaEngineInfo getEngineById(@PathVariable("id") long id) {
        return engineRepository.findById(id).map(engine -> {
            LOG.info("Reading engine with id " + id + " from database.");
            return engine;
        }).orElseThrow(() -> new CamundaEngineNotFoundException(id));
    }

    @GetMapping(path = "/engine")
    public Iterable<CamundaEngineInfo> getEngines() {
        return engineRepository.findAll();
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(value = "{_:^(?!index\\.html|api).*$}")
    public String redirectApi() {
        LOG.info("URL entered directly into the Browser, so we need to redirect...");
        return "forward:/";
    }

}
