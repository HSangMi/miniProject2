package kr.co.torrent.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.Controller;
import org.springframework.web.RequestMapping;

import common.db.MyAppSqlConfig;
import kr.co.torrent.mapper.BoardMapper;

@Controller
public class MainController{
	
	@RequestMapping("/main/main.do")
	public String service() throws Exception {
		
		return "/view/main";
	}
} 
