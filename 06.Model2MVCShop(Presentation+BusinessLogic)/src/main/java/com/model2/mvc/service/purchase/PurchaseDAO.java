package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

//==> 구매관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface PurchaseDAO {
 
//구매 등록 
 public void addPurchase(Purchase purchase) throws Exception;
 
 //구매 상세조회
 public Purchase getPurchase(int tranNo)throws Exception;
 
 //구매 이력 조회
 public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
 
 //구매 수정 
 public void updatePurchase(Purchase purchase) throws Exception;
 
// //구매 트랜코드 수정 
 public void updateTranCode(Purchase purchase) throws Exception;
// 
 // 게시판 Page 처리를 위한 전체Row(totalCount)  return
 public int getTotalCount(Search search) throws Exception ;
	
}