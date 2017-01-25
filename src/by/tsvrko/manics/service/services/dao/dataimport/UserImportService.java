package by.tsvrko.manics.service.services.dao.dataimport;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.UserImportVK;
import by.tsvrko.manics.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsvrko on 1/20/2017.
 */

@Service("userImportService")
public class UserImportService {

    @Autowired
    private UserImportVK userImportVkDAO;

    public  List<UserInfo> getUsers(List<Integer> list) {
        return userImportVkDAO.getUsers(list);
    }
}
