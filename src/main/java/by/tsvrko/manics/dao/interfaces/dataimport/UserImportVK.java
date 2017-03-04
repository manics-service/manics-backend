package main.java.by.tsvrko.manics.dao.interfaces.dataimport;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;

import java.util.List;

/**
 * Created main.main.java.by tsvrko on 1/20/2017.
 */
public interface UserImportVK {

    List <UserInfo> getUsers (List<Integer> list, AuthInfo authInfo);

    UserInfo getUser(AuthInfo authInfo);
}
