package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.azul.crs.client.Response;
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
       // Implementation of getAllMesssages method from interface for retreiving all messages request
       List<Message> messageList=new ArrayList<>();
       messageList=messageRepository.findAll();
       return messageList; 
    }

    @Override
    public Message getMessageById(int message_id) {
         // Implementation of getMessageById method from interface for retreiving single message by given message Id
         if(message_id>0){
            Optional<Message> messageRetrieved=messageRepository.findById(message_id);
            if(!messageRetrieved.isEmpty()){
                return messageRetrieved.get();
            }else{
                return null;
            }
           
         }else{
            return null;
         }
    }

    @Override
    public int deleteMessage(int message_id) {
           // Implementation of deleteMessage method from interface for deletion of single message by given message Id
           int rowAffected=0;
           if(message_id>0){
            Message messageToDelete=getMessageById(message_id);
            if(messageToDelete!=null){
                messageRepository.deleteById(message_id);
                return rowAffected=1;
            }else{
                return rowAffected;
            }
           
           }else{
            return 0;
           }
    }

    @Override
    public Message updateMessageById(int message_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessageById'");
    }

    @Override
    public List<Message> getAllMessagesByAccountId(int account_id) {
        // Implementation of getAllMessagesByAccountId method from interface for retreiving list of message by given account Id
        List<Message> messageList=new ArrayList<>();
        List<Message> emptyMessageList=new ArrayList<>();
        messageList=messageRepository.findByPostedBy(account_id);
        if(messageList.size()>0){
            return messageList;
        }else{
            return emptyMessageList;
        }

    }

}
