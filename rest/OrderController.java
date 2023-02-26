package ou.trinhngoctinh.QuanLyBanHang.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.trinhngoctinh.QuanLyBanHang.entity.OrderEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.CreateOrder;
import ou.trinhngoctinh.QuanLyBanHang.service.CartService;
import ou.trinhngoctinh.QuanLyBanHang.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/get_order")
    public ResponseEntity<?> getOrder(@RequestParam int id){
        OrderEntity orderEntity = orderService.getOrder(id);
        if(orderEntity == null)
            return ResponseEntity.ok("Không tồn tại đơn hang có id: "+id);
        return new ResponseEntity<>(orderEntity, HttpStatus.OK);
    }
    @PostMapping("/create_order")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrder createorder){
        OrderEntity orderEntity = orderService.createOrder(createorder.getUserId());
        if(orderEntity == null)
            return ResponseEntity.ok("Không tồn tại đơn hàng của user có id:" +createorder.getUserId());
        return new ResponseEntity<>(orderEntity, HttpStatus.OK);
    }
}
