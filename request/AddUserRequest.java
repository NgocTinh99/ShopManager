package ou.trinhngoctinh.QuanLyBanHang.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String userName;
    private String userPassword;
    private String customerName;
    private String userAddress;
    private int userPhone;
    private int userId;
}
