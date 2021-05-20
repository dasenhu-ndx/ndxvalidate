package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.repository.OtherSPRepo;
import com.ndx.ndxvalidate.data.sp_access.LabUserPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AppController {

    private  final MTUserService mtUserService;
    private final OtherSPRepo otherSPRepo;

    private final NdxModeService ndxModeService;

//    This is the application general controller that opens html pages not mapped already to the web.
//    It also alLows us to pass models and attributes we want available on all pages
//    Note that userPermission is coming from the AdminService business logic and it helps you identify when a user is from the LMS Team.

    @Autowired
    public AppController(MTUserService mtUserService, OtherSPRepo otherSPRepo, NdxModeService ndxModeService) {
        this.mtUserService = mtUserService;
        this.otherSPRepo = otherSPRepo;
        this.ndxModeService = ndxModeService;
    }



    @ModelAttribute("newMember")
    public Admin getInstanceAdmin(){


        return new Admin();
    }



    @ModelAttribute("newAccount")
    public AccountRequest getInstanceAc(){


        return new AccountRequest();
    }

    @ModelAttribute("updateAccountRequest")
    public AccountRequest updateAccountRequest(){


        return new AccountRequest();
    }
    @GetMapping("/request")
    public String getRequestPage(Model model){
        List<NdxMode> ndxModes = ndxModeService.listModes();
        List<LabUserPair> labUserPairList = otherSPRepo.getLabUserPairList(mtUserService.currentUserName());
        model.addAttribute("labUserPair", labUserPairList);
        model.addAttribute("ndxMode", ndxModes);

        return "request";
    }



}
