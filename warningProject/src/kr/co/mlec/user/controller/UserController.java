package kr.co.mlec.user.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.ModelAndView;
import org.springframework.web.RequestMapping;

import common.db.MyAppSqlConfig;
import kr.co.mlec.mapper.UserMapper;

public class UserController{

	private SqlSession session = null;
	private UserMapper mapper = null;
	
	public UserController() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		mapper = session.getMapper(UserMapper.class);
	}
	
	@RequestMapping("/user/login.do")
	public ModelAndView login(HttpServletRequest request) throws Exception {
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		UserVO param = new UserVO();
		param.setId(id);
		param.setPass(pass);
		
		UserVO user = mapper.selectLogin(param);
			
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				ModelAndView mav = new ModelAndView("redirect:" + request.getContextPath() + "/main/main.do");
				return mav;
			}
			else {
				ModelAndView mav = new ModelAndView("loginForm.do");
				mav.addObject(
						"error", 
						"입력하신 정보가 올바르지 않습니다."
				);
				return mav;
			}
	}
	
	@RequestMapping("/user/loginForm.do")
	public void loginForm() throws Exception {
	}
	
	@RequestMapping("/user/logout.do")
	public String logout(HttpServletRequest request) throws Exception {
		
		HttpSession session = 
				request.getSession();
		session.invalidate();
		
		return "redirect:" + request.getContextPath() + "/main/main.do";
	}
	
}




















