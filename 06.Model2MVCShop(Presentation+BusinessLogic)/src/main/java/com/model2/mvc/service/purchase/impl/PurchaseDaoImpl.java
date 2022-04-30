// 마이바티스에서 mapper사용 알아서 생성해서 주입해줌. mapper.xml에 쿼리 있음. 
package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;


//==> 구매관리 DAO CRUD 구현
@Repository("purchaseDaoImpl") //Persistence Layer(데이터저장 및 관리)와 1:1 매칭이 가능하다.
public class PurchaseDaoImpl implements PurchaseDAO{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	// 아직 구현X 
	///Method
	public void addPurchase(Purchase purchase) throws Exception {
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

   public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
      
      Map<String, Object> map = new HashMap<String, Object>();
      Purchase purchase = new Purchase();
      
      map.put("endRowNum",  search.getEndRowNum()+"" );
      map.put("startRowNum",  search.getStartRowNum()+"" );
      map.put("userId",userId);
      
      List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
      map.put("list", list);
      System.out.println("@@@@@@@@@@@@@@@@"+map);
      return map;
   }
   
   
   public void updateTranCode(Purchase purchase) throws Exception {
		sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}

	   
   
   
   
   
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", search);
	}
	
} // end of class