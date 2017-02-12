package by.tsvrko.manics.service.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.service.interfaces.dataimport.UserImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created main.by tsvrko on 1/20/2017.
 */

@Service
public class UserImportServiceImpl implements UserImportService{

    private UserImportVK userImportDAO;

    @Autowired
    public UserImportServiceImpl(UserImportVK userImportDAO) {
        this.userImportDAO = userImportDAO;
    }

    public  List<UserInfo> getUsers(List<Integer> list) {
        return userImportDAO.getUsers(list);
    }
}
