package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.entity.TestEntity;
import com.ndx.ndxvalidate.data.repository.TestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

    private final TestRepo testRepo;

    public TestController(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @ModelAttribute("newTestAtt")
    public TestEntity getInstance(){


        return new TestEntity();
    }


    @PostMapping("/addTest")
    public String addTestInstance(@ModelAttribute("newTestAtt")TestEntity testEntity, Model model){

        testRepo.save(testEntity);

        model.addAttribute("saved", "This test is saved see database");

        return "test";
    }
}
