/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.EasyCart.service.impl;

import com.example.EasyCart.Enum.ProductStatusEnum;
import com.example.EasyCart.entity.Product;
import com.example.EasyCart.exception.BadRequestException;
import com.example.EasyCart.exception.NotFoundException;
import com.example.EasyCart.exception.ProductNotExistException;
import com.example.EasyCart.repository.ProductRepository;
import com.example.EasyCart.service.ProductService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ACER
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    CategoryService categoryService;
    
   

   

    @Override
    public Page<Product> findUpAll(Pageable pageable) {
        return productRepository.findAllByStatusOrderByProductIdAsc(ProductStatusEnum.UP.getCode(),pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByProductId(pageable);
    }

//    @Override
//    public Page<Product> findAllInCategory(Integer categoryType, Pageable pageable) {
//        return productInfoRepository.findAllByCategoryTypeOrderByProductIdAsc(categoryType, pageable);
//    }

    @Override
    @Transactional
    public void increaseStock(Integer productId, int amount)  throws BadRequestException {
        Product product = findOne(productId);
        if (product == null) throw new  BadRequestException();

        int update = product.getStock() + amount;
        product.setStock(update);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void decreaseStock(Integer productId, int amount) throws BadRequestException{
        Product product = findOne(productId);
        if (product == null) throw new BadRequestException();

        int update = product.getStock() - amount;
        if(update <= 0) throw new BadRequestException();

        product.setStock(update);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public Product offSale(Integer productId) throws BadRequestException{
        Product product = findOne(productId);
        if (product == null) throw new  BadRequestException();

        if (product.getStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new BadRequestException();
        }

       
        product.setStatus(ProductStatusEnum.DOWN.getCode());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product onSale(Integer productId) throws NotFoundException,BadRequestException{
        Product product = findOne(productId);
        if (product == null) throw new NotFoundException();

        if (product.getStatus() == ProductStatusEnum.UP.getCode()) {
            throw new BadRequestException();
        }

        
        product.setStatus(ProductStatusEnum.UP.getCode());
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product)throws NotFoundException,BadRequestException {

        // if null throw exception
//        categoryService.findByCategoryType(productInfo.getCategoryType());
        if(product.getStatus() > 1) {
            throw new BadRequestException();
        }


        return productRepository.save(product);
    }
    
//    @Override
//    @Transactional
//  public Product getProductById(Integer productId) throws ProductNotExistException {
//       <Optional>Product optionalProduct = productRepository.findByProductId(productId);
//        if (!optionalProduct.isPresent())
//            throw new ProductNotExistException("Product id is invalid " + productId);
////            throw new ProductNotExistException();
//        return optionalProduct.get();
//    }
  
   @Override
    public Product findOne(Integer productId) {

        Product product = productRepository.findByProductId(productId);
        return product;
    }

    @Override
    public Product save(Product product) {
        return update(product);
    }

    @Override
    public void delete(Integer productId) throws NotFoundException {
        Product product = findOne(productId);
        if (product == null) throw new NotFoundException();
        productRepository.delete(product);

    }


}
