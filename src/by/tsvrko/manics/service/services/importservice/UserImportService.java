package by.tsvrko.manics.service.services.importservice;

import by.tsvrko.manics.dao.dataimport.vk.implementations.UserImportVKImpl;
import by.tsvrko.manics.dao.dataimport.vk.interfaces.UserImportVK;
import by.tsvrko.manics.model.UserInfo;

import java.util.List;

/**
 * Created by tsvrko on 1/20/2017.
 */
public class UserImportService {

    private static UserImportVK userImportVK = new UserImportVKImpl();

    public  List<UserInfo> getUsers(List<Integer> list) {
        return userImportVK.getUsers(list);
    }
}
