package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ou.trinhngoctinh.QuanLyBanHang.entity.OrderDetailEntity;

import javax.transaction.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update product set product_inventory = ?1 where id = ?2", nativeQuery = true)
    int updateOrderProductInventory(int productInventory, int id);

    @Transactional
    @Modifying
    @Query(value="delete from cart where id=?1", nativeQuery = true)
    int deleteCartOrder(int id);
}
