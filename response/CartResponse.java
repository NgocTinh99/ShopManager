package ou.trinhngoctinh.QuanLyBanHang.response;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CartResponse {
    private List<CartProductInfo> cartProductInfos;
    private int userId;
    private String userName;
    private String customerName;
    private double totalMoney;
}
