package com.ndx.ndxvalidate.business.service;

import com.ndx.ndxvalidate.data.repository.TestRepo;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepo testRepo;


    public TestService(TestRepo testRepo) {
        this.testRepo = testRepo;
    }


}
