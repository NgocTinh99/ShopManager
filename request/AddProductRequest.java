package ou.trinhngoctinh.QuanLyBanHang.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {
    private String productName;
    private double productPrice;
    private String productDescrition;
    private int categoryId;
    private int productId;
    private int productInventory;
}
