package by.tsvrko.manics.dao.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.UserInfo;

import java.util.List;

/**
 * Created main.by tsvrko on 1/20/2017.
 */
public interface UserImportVK {

    List <UserInfo> getUsers (List<Integer> list);
}
