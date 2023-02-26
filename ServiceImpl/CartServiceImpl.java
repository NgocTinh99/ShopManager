package ou.trinhngoctinh.QuanLyBanHang.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.CartEntity;
import ou.trinhngoctinh.QuanLyBanHang.entity.ProductEntity;
import ou.trinhngoctinh.QuanLyBanHang.entity.UserEntity;
import ou.trinhngoctinh.QuanLyBanHang.repository.CartRepository;
import ou.trinhngoctinh.QuanLyBanHang.repository.ProductRepository;
import ou.trinhngoctinh.QuanLyBanHang.repository.UserRepository;
import ou.trinhngoctinh.QuanLyBanHang.request.CartRequest;
import ou.trinhngoctinh.QuanLyBanHang.response.CartProductInfo;
import ou.trinhngoctinh.QuanLyBanHang.response.CartResponse;
import ou.trinhngoctinh.QuanLyBanHang.service.CartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CartResponse getCartByUserId(int id) {
        List<CartEntity> cartEntityList = cartRepository.getCartByUserId(id);
        if(cartEntityList == null)
            return null;
        UserEntity userEntity = userRepository.getUserId(id);
        CartResponse cartResponse = new CartResponse();
        cartResponse.setUserId(userEntity.getId());
        cartResponse.setUserName(userEntity.getUserName());
        cartResponse.setCustomerName(userEntity.getCustomerName());
        List<CartProductInfo> cartProductInfos = new ArrayList<>();
        double totalMoney = 0.0;
        for(int i = 0; i < cartEntityList.size(); i++){
            CartEntity cartEntity = cartEntityList.get(i);
            ProductEntity productEntity = productRepository.getProductId(cartEntity.getProductId());
            CartProductInfo cartProductInfo = new CartProductInfo();
            cartProductInfo.setProductId(productEntity.getId());
            cartProductInfo.setProductName(productEntity.getProductName());
            cartProductInfo.setProductPrice(productEntity.getProductPrice());
            cartProductInfo.setProductQuantity(cartEntity.getProductQuantity());
            cartProductInfo.setTotalProductPrice(cartProductInfo.getProductPrice()*cartProductInfo.getProductQuantity());
            cartProductInfos.add(cartProductInfo);
            totalMoney += cartProductInfo.getTotalProductPrice();
        }
        cartResponse.setTotalMoney(totalMoney);
        cartResponse.setCartProductInfos(cartProductInfos);
        return cartResponse;
    }

    @Override
    public CartEntity addProductToCart(CartRequest request) {
        UserEntity userEntity = userRepository.getUserId(request.getUserId());
        if(userEntity == null)
            return null;
        ProductEntity productEntity = productRepository.getProductId(request.getProductId());
        if(productEntity == null)
            return null;
        CartEntity cartEntity1 = cartRepository.getCartByUserIdAndProductId(request.getUserId(), request.getProductId());
        if(cartEntity1 != null) {
            int qty = cartEntity1.getProductQuantity() + request.getProductQuantity();
            cartRepository.updateQuantityCart(cartEntity1.getId(),qty);
            return cartRepository.getCartByUserIdAndProductId(request.getUserId(), request.getProductId());
        }
        else {
            CartEntity cartEntity = new CartEntity();
            cartEntity.setUserId(request.getUserId());
            cartEntity.setProductQuantity(request.getProductQuantity());
            cartEntity.setProductId(request.getProductId());
            cartEntity = cartRepository.save(cartEntity);
            return cartEntity;
        }
    }

    @Override
    public CartEntity deleteProductInCart(CartRequest cartRequest) {
        ProductEntity productEntity = productRepository.getProductId(cartRequest.getProductId());
        if(productEntity==null)
            return null;
        UserEntity userEntity = userRepository.getUserId(cartRequest.getUserId());
        if(userEntity == null)
            return null;
        CartEntity cartEntity = cartRepository.getCartByUserIdAndProductId(cartRequest.getUserId(), cartRequest.getProductId());
        if(cartEntity == null)
            return null;
        cartRepository.deleteCart(cartRequest.getProductId(), cartRequest.getUserId());
        return cartEntity;
        }

    @Override
    public CartEntity updateProductInCart(CartRequest cartRequest) {
        ProductEntity productEntity = productRepository.getProductId(cartRequest.getProductId());
        if(productEntity==null)
            return null;
        UserEntity userEntity = userRepository.getUserId(cartRequest.getUserId());
        if(userEntity == null)
            return null;
        CartEntity cartEntity = cartRepository.getCartByUserIdAndProductId(cartRequest.getUserId(), cartRequest.getProductId());
        if(cartEntity == null)
            return null;
        cartEntity.setProductQuantity(cartRequest.getProductQuantity());
        cartRepository.updateProductCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getProductQuantity());
        return cartEntity;
    }
}
