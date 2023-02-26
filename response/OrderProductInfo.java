package ou.trinhngoctinh.QuanLyBanHang.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductInfo {
    private int productId;
    private String productName;
    private double productPrice;
    private int productInventory;
}
