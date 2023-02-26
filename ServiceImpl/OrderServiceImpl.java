package ou.trinhngoctinh.QuanLyBanHang.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.*;
import ou.trinhngoctinh.QuanLyBanHang.repository.*;
import ou.trinhngoctinh.QuanLyBanHang.response.CartResponse;
import ou.trinhngoctinh.QuanLyBanHang.response.OrderProductInfo;
import ou.trinhngoctinh.QuanLyBanHang.response. OrderResponse;
import ou.trinhngoctinh.QuanLyBanHang.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public OrderEntity getOrder(int id) {
        OrderEntity orderEntity = orderRepository.getOrderById(id);
        if(orderEntity == null)
            return null;
        return orderEntity;
    }
    
    @Override
    public OrderEntity createOrder(int userId) {
        List<CartEntity> cartEntityList = cartRepository.getCartByUserId(userId);
        if(cartEntityList == null && !cartEntityList.isEmpty())
            return null;
        UserEntity userEntity = userRepository.getUserId(userId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setUserId(userId);
        orderResponse.setCustomerName(userEntity.getCustomerName());
        List<OrderProductInfo> orderProductInfos = new ArrayList<>();
        double totalMoney = 0.0;
        for(int i = 0; i<cartEntityList.size(); i++){
            CartEntity cartEntity = cartEntityList.get(i);
            ProductEntity productEntity = productRepository.getProductId(cartEntity.getProductId());
            if(productEntity.getProductInventory()<cartEntity.getProductQuantity()) {
                return null;
            }
            OrderProductInfo orderProductInfo = new OrderProductInfo();
            orderProductInfo.setProductInventory(productEntity.getProductInventory());
            orderProductInfo.setProductId(productEntity.getId());
            orderProductInfo.setProductName(productEntity.getProductName());
            orderProductInfo.setProductPrice(productEntity.getProductPrice());
            totalMoney += (cartEntity.getProductQuantity()*productEntity.getProductPrice());
            orderProductInfos.add(orderProductInfo);
        }
        orderResponse.setTotalBill(totalMoney);
        Date date = new Date();
        orderResponse.setDateSell(date);
        orderResponse.setOderProductInfos(orderProductInfos);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(orderResponse.getUserId());
        orderEntity.setDateSell(orderResponse.getDateSell());
        orderEntity.setTotalBill(orderResponse.getTotalBill());
        orderEntity = orderRepository.save(orderEntity);
        double intoMoney = 0.0;
        for(int i = 0; i<cartEntityList.size(); i++){
            CartEntity cartEntity = cartEntityList.get(i);
            ProductEntity productEntity = productRepository.getProductId(cartEntity.getProductId());
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setProductId(cartEntity.getProductId());
            orderDetailEntity.setOrderId(orderEntity.getId());
            orderDetailEntity.setDateSell(date);
            intoMoney = (cartEntity.getProductQuantity()*productEntity.getProductPrice());
            orderDetailEntity.setIntoMoney(intoMoney);
            orderDetailEntity.setProductQuantity(cartEntity.getProductQuantity());
            orderDetailRepository.updateOrderProductInventory((productEntity.getProductInventory()-cartEntity.getProductQuantity()),productEntity.getId());
            orderDetailRepository.deleteCartOrder(cartEntity.getId());
            orderDetailRepository.save(orderDetailEntity);
        }
        return orderEntity;
    }

    private int getProductInventory(int productId){
        return 0;
    }
}
