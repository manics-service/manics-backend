package by.tsvrko.manics.service.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.UserInfo;

import java.util.List;

/**
 * Created by tsvrko on 2/10/2017.
 */
public interface UserImportService {

    List<UserInfo> getUsers(List<Integer> list);
}
