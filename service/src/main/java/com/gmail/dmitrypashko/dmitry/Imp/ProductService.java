package com.gmail.dmitrypashko.dmitry.Imp;

import com.gmail.dmitrypashko.dmitry.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gmail.dmitrypashko.dmitry.IProductRepository;
import com.gmail.dmitrypashko.dmitry.model.Product;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productDao;

    @Override
    @Transactional
    public void addProduct(Product product) {
        productDao.saveOrUpData(product);

    }

    @Override
    @Transactional
    public List<Integer> getPaginationList() {
        return productDao.getPaginationList();
    }


    @Transactional
    @Override
    public List<Product> getAllProduct(int pagination) {

        return productDao.getAll(pagination);
    }

    @Transactional
    @Override
    public Product getProduct(Long idProduct) {
        return productDao.findById(idProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(Long idProduct) {
        Product product = productDao.findById(idProduct);
        productDao.delete(product);

    }
}
