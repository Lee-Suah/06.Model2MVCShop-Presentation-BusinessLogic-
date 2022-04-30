package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;


	public interface PurchaseService{
		
		public void  addPurchase(Purchase purchase) throws Exception; // 구매를 위한 비즈니스 수행  
		
		public Purchase getPurchase(int tranNo)throws Exception;  // 구매정보 상세조회를 위한   
		
		public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;  // 구매목록 보기를 위한 
		
		public void updatePurchase(Purchase purchase) throws Exception;  // 구매 정보 수정을 위한 
		
		public void updateTranCode(Purchase purchase) throws Exception; // 구매 상태코드 수정을 위한

		
	}

