package ou.trinhngoctinh.QuanLyBanHang.service;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.ProductEntity;
import ou.trinhngoctinh.QuanLyBanHang.request.AddProductRequest;
import java.util.List;

@Service
public interface ProductService {
    public List<ProductEntity> getAllProduct();
    public JSONObject getProductId(Integer id);
    public ProductEntity getProductCategoryName(String categoryName);
    public ProductEntity addProduct (AddProductRequest productEntity);
    public ProductEntity updateProduct(AddProductRequest productEntity, Integer id);
    public ProductEntity deleteProduct(Integer id);
}
