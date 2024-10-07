package com.example.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping("/")
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService=accountService;
        this.messageService=messageService;
    }

    //Handler method to process registering request from endpoint:localhost:8080/register
    @PostMapping("register")
    public ResponseEntity<Account> registerHandler(@RequestBody Account account){
        Account accountCreated=accountService.create(account);
        if(accountCreated!=null){
            return ResponseEntity.status(HttpStatus.OK).body(accountCreated);
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }
    //Handler method to processs logging request from endpoint:localhost:8080/login
    @PostMapping("login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account account){
        Account accountRetrieved=accountService.login(account);
        if(accountRetrieved!=null){
            return ResponseEntity.status(HttpStatus.OK).body(accountRetrieved);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    //Handler method to processs creating message request from endpoint:localhost:8080/messages
    @PostMapping("messages")
    public ResponseEntity<Message> createMessageHandler(@RequestBody Message message){
        Message messageCreated=messageService.create(message);
        if(messageCreated!=null){
            return ResponseEntity.status(HttpStatus.OK).body(messageCreated);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    //Handler method to processs retreiving message request from endpoint:localhost:8080/messages
    @GetMapping("messages")
    public ResponseEntity<Object> getAllMessagesHandler(){
        return new ResponseEntity<Object>(messageService.getAllMesssages(),HttpStatus.OK);
    }
}
