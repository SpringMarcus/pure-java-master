package com.marcus.chiu.springmvc._examples;

import com.kibo.skeleton.model.entity.ExampleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * http://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 */
@Repository
//public interface ExampleEntityRepository extends CrudRepository<ExampleEntity, Long> {
public interface ExampleEntityRepository extends PagingAndSortingRepository<ExampleEntity, Long> {

    public ExampleEntity findByIndex1(String index1);
}