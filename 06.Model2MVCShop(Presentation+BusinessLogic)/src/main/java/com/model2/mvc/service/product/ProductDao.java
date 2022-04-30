package com.model2.mvc.service.product;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

//==> 상품관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface ProductDao {
   
   //상품등록
   public void addProduct(Product product) throws Exception; 
   
   //상품조회
   public Product getProduct(int prodNo) throws Exception; 
   
   //상품 목록조회
   public List<Product> getProductList(Search search) throws Exception;
   
   //상품 정보수정
   public void updateProduct(Product product) throws Exception;
   
   // 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
	
	}