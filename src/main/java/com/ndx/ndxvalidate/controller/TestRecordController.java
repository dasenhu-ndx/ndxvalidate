package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.entity.TestEntity;
import com.ndx.ndxvalidate.data.repository.TestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestRecordController {

    private final TestRepo testRepo;

    public TestRecordController(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @GetMapping("/testRecord")
    public  String pullTestRecord(Model model){
        List<TestEntity> testEntities = testRepo.findAll();

        model.addAttribute("test", testEntities);


        return "record";
    }
}
