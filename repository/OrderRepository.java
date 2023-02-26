package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ou.trinhngoctinh.QuanLyBanHang.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query(value="SELECT * FROM orderr WHERE id = ?1", nativeQuery = true)
    OrderEntity getOrderById(Integer id);
}
