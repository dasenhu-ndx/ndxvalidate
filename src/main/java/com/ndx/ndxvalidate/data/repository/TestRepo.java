package com.ndx.ndxvalidate.data.repository;

import com.ndx.ndxvalidate.data.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, Long> {

}
