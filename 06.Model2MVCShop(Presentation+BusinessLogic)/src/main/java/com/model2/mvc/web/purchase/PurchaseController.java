package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;

//==> ȸ������ Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method ���� ����
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml ���� �Ұ�
	//==> �Ʒ��� �ΰ��� �ּ��� Ǯ�� �ǹ̸� Ȯ�� �Ұ�
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@RequestParam("prod_no") int prodNo) throws Exception{
		System.out.println("/addPurchaseView.do");
		Product product = productService.getProduct(prodNo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@ModelAttribute("purchase") Purchase purchase, 
			@RequestParam("prodNo") int prodNo, HttpSession session) throws Exception{
		
		Product product = productService.getProduct(prodNo);
		
		product.setProTranCode("1");
		productService.updateProduct(product);
		purchase.setTranCode("1");
		purchase.setPurchaseProd(product);
		
		purchase.setBuyer((User)session.getAttribute("user"));
		purchaseService.addPurchase(purchase);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		modelAndView.addObject("purchase", purchase);
		
		return modelAndView;
	}
	
	
	@RequestMapping("/getPurchase.do")
	public ModelAndView getPurchase( @RequestParam("tranNo") int tranNo) throws Exception {
		
		System.out.println("/getPurchase.do");
		//Business Logic
		Purchase vo = purchaseService.getPurchase(tranNo);
		// Model �� View ����
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/getPurchaseView.jsp");
		modelAndView.addObject("vo", vo);
		
		return modelAndView;
	}
	
	
//	
//	
//	@RequestMapping("/updateProductView.do")
//	public String updateProductView( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
//
//		System.out.println("/updateProductView.do");
//		//Business Logic
//		Product product = productService.getProduct(prodNo);
//		// Model �� View ����
//		model.addAttribute("product", product); // �ٽ� Ȯ��,, 
//		
//		return "forward:/product/updateProductView.jsp";
//	}
//	
//	@RequestMapping("/updateProduct.do")
//	public String updateProduct( @ModelAttribute("product") Product product , Model model , HttpSession session) throws Exception{
//		
//		System.out.println("/updateProduct.do");
//		//Business Logic
//		productService.updateProduct(product);
//		
//		//String sessionId=((Product)session.getAttribute("user")).getUserId();
//		//if(sessionId.equals(user.getUserId())){
//		//	session.setAttribute("user", user);
//		//}
//		
//		return "redirect:/getProduct.do?prodNo="+product.getProdNo();
//	}
//	
//
	@RequestMapping("/listPurchase.do")
	public ModelAndView listPurchase( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listPurchase.do");
		
		String menu = request.getParameter("menu");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic ����
		Map<String , Object> map=purchaseService.getPurchaseList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model �� View ����
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		if(menu.equals("search")) {
			return "forward:/product/listProductView.jsp"; // ��ǰ �����ȸ ui �̵� 
		}else {
			return "forward:/product/listSaleView.jsp"; //  ��ǰ���� ui �� �̵� 
		}
	}
}