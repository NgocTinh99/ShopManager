package ou.trinhngoctinh.QuanLyBanHang.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table (name="cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="product_id")
    private int productId;
    @Column(name="product_quantity")
    private int productQuantity;
    @Column(name="user_id")
    private int userId;
}
