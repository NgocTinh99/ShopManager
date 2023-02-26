package ou.trinhngoctinh.QuanLyBanHang.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private int productId;
    private int productQuantity;
    private int userId;
}
