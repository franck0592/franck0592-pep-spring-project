package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.*;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.interfaces.MesssageServiceInterface;

@Service
public class MessageService implements MesssageServiceInterface {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Message create(Message message) {
         // Implementation of create method from interface for creating message request

         //check for blank message
        if(message.getMessageText().isBlank()){
            return null;
        } 
        //check for message length under 255 and real user
        if(message.getMessageText().length()<255){
            int messagePostedById=message.getPostedBy();
            Optional<Account> messagePostedByAccount=accountRepository.findById(messagePostedById);
            if(!messagePostedByAccount.isEmpty()){
                return messageRepository.save(message);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public List<Message> getAllMesssages() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMesssages'");
    }

    @Override
    public Message getMessageById(int message_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessageById'");
    }

    @Override
    public Message deleteMessage(int message_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMessage'");
    }

    @Override
    public Message updateMessageById(int message_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessageById'");
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int account_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllMessagesByAccountId'");
    }

}
