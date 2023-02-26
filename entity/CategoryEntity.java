package ou.trinhngoctinh.QuanLyBanHang.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="category_descrition")
    private String categoryDescrition;
}
