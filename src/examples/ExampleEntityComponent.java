package com.marcus.chiu.springmvc._examples;

import com.kibo.skeleton.model.entity.ExampleEntity;
import com.kibo.skeleton.repository.ExampleEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExampleEntityComponent {

    @Autowired
    private ExampleEntityRepository exampleEntityRepository;

    public Page<ExampleEntity> getFirst10Pages() {
        Pageable pageRequest = new PageRequest(0, 10);
        Page<ExampleEntity> page = exampleEntityRepository.findAll(pageRequest);

        return page;
    }
}

