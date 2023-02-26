package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ou.trinhngoctinh.QuanLyBanHang.entity.CartEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    @Query(value = "select * from cart where user_id= ?1", nativeQuery = true)
    List<CartEntity> getCartByUserId(int userId);

    @Query(value ="select * from cart where user_id = ?1 and product_id = ?2",nativeQuery = true)
    CartEntity getCartByUserIdAndProductId(int userId, int productId);

    @Transactional
    @Modifying
    @Query(value="update cart set product_quantity= ?2 where id=?1", nativeQuery = true)
    int updateQuantityCart(int id, int qty);

    @Transactional
    @Modifying
    @Query(value="update cart set product_quantity= ?3 where user_id=?1 and product_id=?2", nativeQuery = true)
    int updateProductCart(int userId, int productId, int product_quantity);

    @Transactional
    @Modifying
    @Query(value="delete from cart where product_id = ?1 and user_id=?2", nativeQuery = true)
    int deleteCart(int productId, int userId);
}
