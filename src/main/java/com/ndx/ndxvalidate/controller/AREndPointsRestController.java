package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.repository.MiniChecksRepo;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarDoctor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/form/endpoints")
public class AREndPointsRestController {

    private final MiniChecksRepo miniChecksRepo;

//    This is the controller that houses all the logic for restfulAPI used to make account request form dynamic

    public AREndPointsRestController(MiniChecksRepo miniChecksRepo) {
        this.miniChecksRepo = miniChecksRepo;
    }

//    End points to get existing customer by Id
//     form/endpoints/{labName}/{customerId}

    @GetMapping("/{labName}/{customerId}")
    public CheckExistingCustomer getExistingCustomerByCustomerId(@PathVariable(name = "labName") String labName,
                                                                 @PathVariable(name = "customerId") String customerId) {

        return miniChecksRepo.getExistingCustomerByCustomerId(labName, customerId);
    }

//    Endpoints to get existing customer by customer id and phone
//     form/endpoints/{labName}/{customerId}/{phone}


    @GetMapping("/{labName}/{customerId}/{phone}")
    public CheckExistingCustomer getExistingCustomerByCustomerIdAndPhone(@PathVariable(name = "labName") String labName,
                                                                         @PathVariable(name = "customerId") String customerId,
                                                                         @PathVariable(name = "phone") String phone) {

        return miniChecksRepo.getExistingCustomerByCustomerIdAndPhone(labName, customerId, phone);
    }

//    Endpoints to get similar customer by address
//    form/endpoints/{labName}/{add1}

    @GetMapping("/{labName}/{add1}")
    public List<CheckSimilarCustomer> getSimilarCustomersByAddress(@PathVariable(name = "labName") String labName,
                                                                   @PathVariable(name = "add1") String add1) {


        return miniChecksRepo.getSimilarCustomerByAddress(labName, add1);
    }

//    EndPoint to get Similar Customers by Address and Phone
//    form/endpoints/{labName}/{add1}/{phone}

    @GetMapping("/{labName}/{add1}/{phone}")
    public List<CheckSimilarCustomer> getSimilarCustomersByAddressAndPhone(@PathVariable(name = "labName") String labName,
                                                                           @PathVariable(name = "add1") String add1,
                                                                           @PathVariable(name = "phone") String phone){

        return miniChecksRepo.getSimilarCustomerByAddressAndPhone(labName,add1, phone);

    }

//    Endpoint to get Similar doctor by doctor name
//    form/endpoints/{labName}/{fName}/{lName}

    @GetMapping("/{labName}/{fName}/{lName}")
    public List<CheckSimilarDoctor> getSimilarDoctorByName(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "fName") String fName,
                                                           @PathVariable(name = "lName") String lName){

        return miniChecksRepo.getSimilarDoctorByName(labName, fName, lName);
    }

//    Endpoint to get similar doctor by doctor name and license no
//    form/endpoints/{labName}/{fName}/{lName}/{licenseNo}


    @GetMapping("/{labName}/{fName}/{lName}/{licenseNo}")
    public List<CheckSimilarDoctor> getSimilarDoctorByNameAndLicenseNo(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "fName") String fName,@PathVariable(name = "lName") String lName,
                                                                       @PathVariable(name = "licenseNo") String licenseNo){

        return miniChecksRepo.getSimilarDoctorNameAndLicense(labName, fName, lName,licenseNo);
    }

//  End point to get existing doctor by  doctor id
//    form/endpoints/{labName}/{docId}

    @GetMapping("/{labName}/{docId}")
    public CheckExistingDoctor getExistingDoctorByDoctorId(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "docId") String docId){

        return miniChecksRepo.getExistingDoctorByDocId(labName, docId);
    }

}
