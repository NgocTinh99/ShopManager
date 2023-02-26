package ou.trinhngoctinh.QuanLyBanHang.service;

import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.OrderEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.CartRequest;

@Service
public interface OrderService {
    public OrderEntity getOrder(int id);
    public OrderEntity createOrder(int userId);
}
