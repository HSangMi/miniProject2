package kr.co.torrent.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.web.Controller;
import org.springframework.web.ModelAndView;
import org.springframework.web.RequestMapping;
import org.springframework.web.RequestParam;
import org.springframework.web.WebUtil;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import common.db.MyAppSqlConfig;
import kr.co.torrent.mapper.BoardMapper;

@Controller
public class Board4Controller {
	private SqlSession session = null;
	private BoardMapper mapper = null;
	
	public Board4Controller() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		mapper = session.getMapper(BoardMapper.class);
	}
	
	
}
