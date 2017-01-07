package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;

import java.util.ArrayList;

/**
 * Created by irats on 1/5/2017.
 */
public interface MessageDAO {

    public ArrayList<Message> getMessages(Chat chat);

}
