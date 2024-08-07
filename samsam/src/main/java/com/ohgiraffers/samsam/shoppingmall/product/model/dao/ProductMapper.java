package com.ohgiraffers.samsam.shoppingmall.product.model.dao;

import com.ohgiraffers.samsam.shoppingmall.product.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> findProductList(String ProductName);

    void registNewProduct(ProductDTO newProduct);


    ProductDTO findByProductSeq(int productSeq);


    void update(ProductDTO updateProduct);


    void delete(int productSeq);

    ProductDTO findProductDetail(int productNo);
}
