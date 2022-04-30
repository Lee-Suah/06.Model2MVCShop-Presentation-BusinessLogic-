package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.purchase.PurchaseDAO;


//==> 상품관리 서비스 구현
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	///Field
		@Autowired
		@Qualifier("purchaseDaoImpl")
		private PurchaseDAO purchaseDAO;
		public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
			this.purchaseDAO = purchaseDAO;
		}
		
		///Constructor
		public PurchaseServiceImpl() {
			System.out.println(this.getClass());
		}	
		
		///Method
		public void addPurchase(Purchase purchase) throws Exception {
			purchaseDAO.addPurchase(purchase);
		}
	
		public Purchase getPurchase(int tranNo) throws Exception {
			return purchaseDAO.getPurchase(tranNo);
		}
		
		
//		public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
//		      
//		      int totalCount = purchaseDao.getTotalCount(search);
//		      
//		      Map<String, Object> map =  purchaseDao.getPurchaseList(search, buyerId);
//		      map.put("totalCount", new Integer(totalCount));
//		      
//		      return map;
//		   }
		   
		public Map<String , Object> getPurchaseList(Search search, String userId) throws Exception {
			//List<Purchase> list= purchaseDAO.getPurchaseList(search, userId);
			int totalCount = purchaseDAO.getTotalCount(search);
		
			 Map<String, Object> map =  purchaseDAO.getPurchaseList(search, userId);
			//Map<String, Object> map = new HashMap<String, Object>();
			//map.put("list", list );
			map.put("totalCount", new Integer(totalCount));
			
			return map;
		}
		
		
		public void updatePurchase(Purchase purchase) throws Exception {
			purchaseDAO.updatePurchase(purchase);
		}
		
		
		public void updateTranCode(Purchase purchase) throws Exception {
			purchaseDAO.updateTranCode(purchase);
		}
		
		
		

   
} // end of class