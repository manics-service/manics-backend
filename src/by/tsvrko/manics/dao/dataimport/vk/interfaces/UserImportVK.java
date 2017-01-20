package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.UserInfo;

import java.util.List;

/**
 * Created by tsvrko on 1/20/2017.
 */
public interface UserImportVK {

    List <UserInfo> getUsers (List<Integer> list);
}
