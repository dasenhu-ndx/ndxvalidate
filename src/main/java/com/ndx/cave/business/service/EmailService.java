package com.ndx.cave.business.service;

import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.EmailSPRepository;
import com.ndx.cave.data.repository.MiniChecksRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmailService {


    private final MiniChecksRepo miniChecksRepo;
    private final AccountRequestRepo accountRequestRepo;
    private  final MTUserService mtUserService;
    private  final EmailSPRepository emailSPRepository;

    public EmailService(MiniChecksRepo miniChecksRepo, AccountRequestRepo accountRequestRepo, MTUserService mtUserService, EmailSPRepository emailSPRepository) {
        this.miniChecksRepo = miniChecksRepo;
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.emailSPRepository = emailSPRepository;
    }

    public void  emailServiceSender(AccountRequest accountRequest, String message){
        emailSPRepository.sendNoteONNewAccount(accountRequest.getLabName(), message, accountRequest.getMtUserName(), mtUserService.currentUserName());
        System.out.println("Email sent");

        accountRequest.setStatus(0);
        accountRequestRepo.save(accountRequest);
    }
}
