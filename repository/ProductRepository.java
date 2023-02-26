//package ou.trinhngoctinh.QuanLyBanHang.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import ou.trinhngoctinh.QuanLyBanHang.entity.ProductEntity;
//
//@Repository
//public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
//
//    @Query(value = "SELECT * FROM product WHERE id = ?1", nativeQuery = true)
//    ProductEntity getProductId(Integer id);
//
//    @Query(value = "select c.category_name from category c left join product p on c.id = p.category_id where c.category_name = ?1", nativeQuery = true)
//    ProductEntity getProductByCategoryName(String categoryName);
//}

package ou.trinhngoctinh.QuanLyBanHang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;
        import ou.trinhngoctinh.QuanLyBanHang.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    @Query(value = "SELECT * FROM product WHERE id = ?1", nativeQuery = true)
    ProductEntity getProductId(Integer id);

    @Query(value = "select c.category_name from category c left join product p on c.id = p.category_id where c.category_name = ?1", nativeQuery = true)
    ProductEntity getProductByCategoryName(String categoryName);
}
