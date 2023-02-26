package ou.trinhngoctinh.QuanLyBanHang.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table (name="product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="product_name")
    private String productName;
    @Column(name="product_price")
    private double productPrice;
    @Column(name="product_descrition")
    private String productDescrition;
    @Column(name = "category_id")
    private int categoryId;
    @Column(name="product_inventory")
    private int productInventory;
}

