package ou.trinhngoctinh.QuanLyBanHang.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private List<OrderProductInfo> OderProductInfos;
    private int userId;
    private String customerName;
    private double totalBill;
    private Date dateSell;
}
