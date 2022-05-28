package ma.enset.ecom.services;

import ma.enset.ecom.dtos.ProductDTO;
import ma.enset.ecom.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl {
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        return productDTO;
    }
}
