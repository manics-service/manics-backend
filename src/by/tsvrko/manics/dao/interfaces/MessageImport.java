package by.tsvrko.manics.dao.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;

import java.util.ArrayList;

/**
 * Created by irats on 1/5/2017.
 */
public interface MessageImport {

    public ArrayList<Message> getMessages(Chat chat);

}
