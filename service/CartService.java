package ou.trinhngoctinh.QuanLyBanHang.service;

import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.CartEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.CartRequest;
import ou.trinhngoctinh.QuanLyBanHang.response.CartResponse;

@Service
public interface CartService {
    public CartResponse getCartByUserId(int id);
    public CartEntity addProductToCart(CartRequest cartRequest);
    public CartEntity deleteProductInCart(CartRequest cartRequest);
    public CartEntity updateProductInCart(CartRequest cartRequest);
}
