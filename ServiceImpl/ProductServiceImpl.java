package ou.trinhngoctinh.QuanLyBanHang.ServiceImpl;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.trinhngoctinh.QuanLyBanHang.entity.ProductEntity;
import ou.trinhngoctinh.QuanLyBanHang.repository.ProductRepository;
import ou.trinhngoctinh.QuanLyBanHang.request.AddProductRequest;
import ou.trinhngoctinh.QuanLyBanHang.service.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProduct() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productEntityList;
    }

    @Override
    public JSONObject getProductId(Integer id) {
        JSONObject data = new JSONObject();
        try {
            ProductEntity productEntity = productRepository.getProductId(id);
            data.put("productEntity",productEntity);
        }catch (Exception ex){
            data.put("Error","Error");
        }
        return data;
    }

    @Override
    public ProductEntity getProductCategoryName(String categoryName){
        ProductEntity productEntity = productRepository.getProductByCategoryName(categoryName);
        if (productEntity == null)
            return null;
        else
            return productEntity;
    }

    @Override
    public ProductEntity addProduct(AddProductRequest request) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(request.getProductName());
        productEntity.setProductPrice(request.getProductPrice());
        productEntity.setProductDescrition(request.getProductDescrition());
        productEntity.setCategoryId(request.getCategoryId());
        productEntity.setProductInventory(request.getProductInventory());
        productEntity = productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity updateProduct(AddProductRequest request, Integer id) {
        ProductEntity productEntity = productRepository.getProductId(id);
        if (productEntity == null)
            return null;
        else {
            productEntity.setProductName(request.getProductName());
            productEntity.setProductPrice(request.getProductPrice());
            productEntity.setProductDescrition(request.getProductDescrition());
            productEntity.setProductInventory(request.getProductInventory());
            productEntity = productRepository.save(productEntity);
            return productEntity;
        }
    }

    @Override
    public ProductEntity deleteProduct(Integer id) {
        ProductEntity productEntity = productRepository.getProductId(id);
        if (productEntity == null)
            return null;
        productRepository.deleteById(id);
        return productEntity;
    }
}
