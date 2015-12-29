package to.my.code.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import to.my.code.dao.ContentDao;
import to.my.code.dto.ContentDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MyBatisController {
	
	ContentDao cdao;
	
	@Autowired
	public void setCdao(ContentDao cdao) {
		this.cdao = cdao;
	}
	
	@RequestMapping("/mybatis/list")
	public String list(Model model) {
		ArrayList<ContentDto> dtos = cdao.listDao();
		model.addAttribute("list", dtos);
		
		return "/mybatis/list";
	}
	
	@RequestMapping("/mybatis/writeForm")
	public String writeForm() {
		return "/mybatis/writeForm";
	}
	
	@RequestMapping("/mybatis/write")
	public String write(HttpServletRequest request, Model model) {
		cdao.writeDao(request.getParameter("mWriter"), request.getParameter("mContent"));
		
		return "redirect:/mybatis/list";
	}
	
	@RequestMapping("/mybatis/view")
	public String view() {
		return "/mybatis/view";
	}
	
	@RequestMapping("/mybatis/delete")
	public String delete(HttpServletRequest request, Model model){
		cdao.deleteDao(request.getParameter("mId"));
		
		return "redirect:/mybatis/list";
	}
	
	
}
