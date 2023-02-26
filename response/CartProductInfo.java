package ou.trinhngoctinh.QuanLyBanHang.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductInfo<String> {
    private int productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String productDescrition;
    private double totalProductPrice;


}
