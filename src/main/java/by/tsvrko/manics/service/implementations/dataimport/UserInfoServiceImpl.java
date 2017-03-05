package by.tsvrko.manics.service.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created main.main.java.by tsvrko on 1/20/2017.
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserImportVK userImportDAO;

    @Autowired
    public UserInfoServiceImpl(UserImportVK userImportDAO) {
        this.userImportDAO = userImportDAO;
    }

    @Override
    public  List<UserInfo> getUsers(List<Integer> list, AuthInfo authInfo) {
        return userImportDAO.getUsers(list,authInfo);
    }

    @Override
    public UserInfo getUser(AuthInfo authInfo) {
        return userImportDAO.getUser(authInfo);
    }
}
