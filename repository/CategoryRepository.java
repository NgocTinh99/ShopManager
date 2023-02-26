package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ou.trinhngoctinh.QuanLyBanHang.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
