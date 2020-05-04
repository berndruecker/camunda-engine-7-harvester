package io.berndruecker.camunda.harvest.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.berndruecker.camunda.harvest.domain.CamundaEngineInfo;

public interface CamundaEngineRepository extends CrudRepository<CamundaEngineInfo, Long> {

    List<CamundaEngineInfo> findByName(@Param("name") String name);
    boolean existsByName(@Param("name") String name);
}
