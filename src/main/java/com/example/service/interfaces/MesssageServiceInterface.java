package com.example.service.interfaces;

import java.util.List;

import com.example.entity.Message;

public interface MesssageServiceInterface {
    public Message create(Message message);
    public List<Message> getAllMesssages();
    public Message getMessageById(int message_id);
    public int deleteMessage(int message_id);
    public int updateMessageById(Message messageToUpdate);
    public List<Message> getAllMessagesByAccountId(int account_id);
}
