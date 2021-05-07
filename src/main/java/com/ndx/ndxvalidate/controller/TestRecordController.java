package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.entity.TestEntity;
import com.ndx.ndxvalidate.data.repository.PracDocRepo;
import com.ndx.ndxvalidate.data.repository.TestRepo;
import com.ndx.ndxvalidate.data.sp_access.PracticeDoc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TestRecordController {

    private final TestRepo testRepo;
    private final PracDocRepo pracDocRepo;

    public TestRecordController(TestRepo testRepo, PracDocRepo pracDocRepo) {
        this.testRepo = testRepo;
        this.pracDocRepo = pracDocRepo;
    }

    @GetMapping("/testRecord")
    public  String pullTestRecord(Model model){
        List<TestEntity> testEntities = testRepo.findAll();
        List<PracticeDoc> practiceDocs = pracDocRepo.getAllNow();

        model.addAttribute("test", testEntities);
        model.addAttribute("getNow", practiceDocs);

        return "record";
    }

}
