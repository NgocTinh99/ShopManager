package ou.trinhngoctinh.QuanLyBanHang.ServiceImpl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.UserEntity;
import ou.trinhngoctinh.QuanLyBanHang.repository.UserRepository;
import ou.trinhngoctinh.QuanLyBanHang.request.AddUserRequest;
import ou.trinhngoctinh.QuanLyBanHang.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        return userEntityList;
    }

    @Override
    public UserEntity getUser(Integer id) {
        UserEntity userEntity = userRepository.getUserId(id);
        if(userEntity == null)
            return null;
        return userEntity;
    }

    @Override
    public UserEntity addUser(AddUserRequest request) {
        UserEntity userEntityCheckUserName = userRepository.findByUserName(request.getUserName());
        if (userEntityCheckUserName == null)
            if (request.getUserName().length() < 6 || request.getUserPassword().length() < 8)
                return null;
            else{
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(request.getUserName());
                userEntity.setCustomerName(request.getCustomerName());
                userEntity.setUserAddress(request.getUserAddress());
                userEntity.setUserPassword(request.getUserPassword());
                userEntity.setUserPhone(request.getUserPhone());
                userEntity = userRepository.save(userEntity);
                return userEntity;
            }
        return null;
    }

    @Override
    public UserEntity updateUser(AddUserRequest request, Integer id) {
        UserEntity userEntity1 = userRepository.getUserId(id);
        if (userEntity1 == null){
            return null;
        }else {
            userEntity1.setUserName(request.getUserName());
            userEntity1.setCustomerName(request.getCustomerName());
            userEntity1.setUserAddress(request.getUserAddress());
            userEntity1.setUserPassword(request.getUserPassword());
            userEntity1.setUserPhone(request.getUserPhone());
            userEntity1 = userRepository.save(userEntity1);
            return userEntity1;
        }
    }

    @Override
    public UserEntity deleteUser(Integer id) {
        UserEntity userEntity1 = userRepository.getUserId(id);
        if(userEntity1 == null)
            return null;
        userRepository.deleteById(id);
        return userEntity1;
    }

    @Override
    public UserEntity loginUser(String userNameLogin, String userPasswordLogin){
        UserEntity userEntity = userRepository.findByUserNameAndPassword(userNameLogin, userPasswordLogin);
        if(userEntity == null)
            return null;
        return userEntity;
    }

    @Override
    public UserEntity findByUserNameLogin(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        if(userEntity == null)
            return null;
        return userEntity;
    }
}














