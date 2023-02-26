package ou.trinhngoctinh.QuanLyBanHang.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.trinhngoctinh.QuanLyBanHang.entity.UserEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.AddUserRequest;
import ou.trinhngoctinh.QuanLyBanHang.request.LoginRequest;
import ou.trinhngoctinh.QuanLyBanHang.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get_alluser")
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> userEntityList = userService.getAllUser();
        return new ResponseEntity<List<UserEntity>>(userEntityList, HttpStatus.OK);
    }

    @GetMapping("/get_byId/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        UserEntity userEntity = userService.getUser(id);
        if(userEntity == null){
            return ResponseEntity.ok("Không tồn tại user có id: " + id);
        } else{
            return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
        }
    }
    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest userEntity) {
        UserEntity userEntity1 = userService.addUser(userEntity);
        if(userEntity1 == null){
            if(userEntity.getUserName() == null)
                return ResponseEntity.ok("Vui lòng không để trống username!");
            else
                if(userEntity.getUserPassword() == null)
                    return ResponseEntity.ok("Vui lòng không để trống password!");
                else {
                    if (userEntity.getUserName().length() < 6)
                        return ResponseEntity.ok("Tên đăng nhập không được nhỏ hơn 6 kí tự!");
                    if (userEntity.getUserPassword().length() < 8)
                        return ResponseEntity.ok("Password không được nhỏ hơn 8 kí tự!");
                }
        }
        return ResponseEntity.ok("Đăng ký thành công");
    }

    @PutMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestBody AddUserRequest userEntity) {
       UserEntity userEntity1 = userService.updateUser(userEntity, userEntity.getUserId());
       if(userEntity1 == null)
           return ResponseEntity.ok("Không tồn tại user có id: " + userEntity.getUserId());
       new ResponseEntity<UserEntity>(userEntity1, HttpStatus.OK);
       return ResponseEntity.ok("Sửa user thành công");
    }

    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        UserEntity userEntity1 = userService.getUser(id);
        if (userEntity1 == null)
            return ResponseEntity.ok("Không tồn tại user có id: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok("Xóa thành công user có id:"+ id);
    }

    @PostMapping("/login_user")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest userEntity){
        UserEntity userEntityLogin = userService.findByUserNameLogin(userEntity.getUserName());
        if(userEntityLogin == null){
            return ResponseEntity.ok("Không tồn tại username: "+ userEntity.getUserName());
        }else {
            UserEntity userEntity1 = userService.loginUser(userEntity.getUserName(), userEntity.getUserPassword());
            if (userEntity1 == null)
                return ResponseEntity.ok("Sai mật khẩu! Đăng nhập không thành công!");
            return ResponseEntity.ok("Đăng nhập thành công!");
        }
    }
}
