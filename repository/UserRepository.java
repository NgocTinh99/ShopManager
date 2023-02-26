package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ou.trinhngoctinh.QuanLyBanHang.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query (value = "SELECT * FROM user WHERE id = ?1", nativeQuery = true)
    UserEntity getUserId(Integer id);

    @Query(value = "SELECT * FROM  user WHERE  user_name = ?1", nativeQuery = true)
    UserEntity findByUserName(String userName);

    @Query(value = "SELECT * FROM user WHERE user_name = ?1 and  user_password = ?2", nativeQuery = true)
    UserEntity findByUserNameAndPassword(String userName, String userPassword);
}
