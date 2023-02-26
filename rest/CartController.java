package ou.trinhngoctinh.QuanLyBanHang.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.trinhngoctinh.QuanLyBanHang.entity.CartEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.CartRequest;
import ou.trinhngoctinh.QuanLyBanHang.response.CartResponse;
import ou.trinhngoctinh.QuanLyBanHang.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/get_byUserId")
    public ResponseEntity<?> getCartById(@RequestParam int id){
        CartResponse cartResponse = cartService.getCartByUserId(id);
        if(cartResponse == null)
            return ResponseEntity.ok("Không tồn tại giỏ hàng có user id: " + id);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    @PostMapping("/add_product_to_cart")
    public ResponseEntity<?> addProductToCart(@RequestBody CartRequest cartRequest){
        CartEntity cartEntity = cartService.addProductToCart(cartRequest);
        if(cartEntity==null)
            return ResponseEntity.ok("Không tồn tại");
        return new ResponseEntity<CartEntity>(cartEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete_product_in_cart")
    public ResponseEntity<?> deleteProductInCart(@RequestBody CartRequest cartRequest){
        CartEntity cartEntity = cartService.deleteProductInCart(cartRequest);
        if(cartEntity==null)
            return ResponseEntity.ok("Không có thông tin để xóa!");
        new ResponseEntity<>(cartEntity, HttpStatus.OK);
        return ResponseEntity.ok("Xóa thành công!");
    }
    @PutMapping("/update_product_in_cart")
    public ResponseEntity<?> updateProductInCart(@RequestBody CartRequest cartRequest){
        CartEntity cartEntity = cartService.updateProductInCart(cartRequest);
        if(cartEntity == null)
            return ResponseEntity.ok("Không có sản phẩm để sửa!");
        new ResponseEntity<CartEntity>(cartEntity, HttpStatus.OK);
        return ResponseEntity.ok("Sửa thành công!");
    }
}
