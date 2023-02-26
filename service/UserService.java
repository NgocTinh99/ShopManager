package ou.trinhngoctinh.QuanLyBanHang.service;

import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.UserEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.AddUserRequest;
import java.util.List;

@Service
public interface UserService {
    public List<UserEntity> getAllUser();
    public UserEntity getUser(Integer id);
    public UserEntity addUser(AddUserRequest userEntity);
    public UserEntity updateUser(AddUserRequest userEntity, Integer id);
    public UserEntity deleteUser(Integer id);
    public UserEntity loginUser(String userNameLogin, String userPassword);
    public UserEntity findByUserNameLogin(String userName);
}
