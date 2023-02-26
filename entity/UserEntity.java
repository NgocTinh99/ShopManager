package ou.trinhngoctinh.QuanLyBanHang.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table (name="user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="user_name")
    private String userName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name ="customer_name")
    private String customerName;
    @Column(name="user_address")
    private String userAddress;
    @Column(name="user_phone")
    private int userPhone;
}
