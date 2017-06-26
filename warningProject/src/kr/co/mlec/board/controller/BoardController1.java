package kr.co.mlec.board.controller;

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
import kr.co.mlec.mapper.BoardMapper;
import kr.co.mlec.vo.BoardVO;
import kr.co.mlec.vo.CommentVO;

@Controller
public class BoardController1 {
	private SqlSession session = null;
	private BoardMapper mapper = null;
	
	public BoardController1() {
		session = MyAppSqlConfig.getSqlSessionInstance();
		mapper = session.getMapper(BoardMapper.class);
	}
	
	@RequestMapping("/board/commendDelete.do")
	public String commentDeleteAjax(CommentVO comment) throws Exception {
		mapper.deleteBoardComment(comment.getCommentNo());
		session.commit();
		List<CommentVO> commentList = mapper.selectBoardCommentByNo(comment.getNo());
		
		return "Ajax:" + new Gson().toJson(commentList);
	}
	@RequestMapping("/board/commentList.do")
	public String commentListAjax(int no) throws Exception {
		List<CommentVO> commentList = mapper.selectBoardCommentByNo(no);
		String result = new Gson().toJson(commentList);
		return "Ajax:" + result;
	}
	@RequestMapping("/board/commentRegist.do")
	public String commentRegistAjax(CommentVO comment) throws Exception {
		mapper.insertBoardComment(comment);
		session.commit();
		
		List<CommentVO> commentList = mapper.selectBoardCommentByNo(comment.getNo());
		return"Ajax:" + new Gson().toJson(commentList);
	}
	@RequestMapping("/board/commentUpdate.do")
	public String commentUpdateAjax(CommentVO comment) throws Exception {
		
		mapper.updateBoardComment(comment);
		session.commit();
		
		List<CommentVO> commentList = mapper.selectBoardCommentByNo(comment.getNo());
		return "Ajax:" + new Gson().toJson(commentList);
	}
	
	@RequestMapping("/board/delete.do")
	public void delete(int no) throws Exception {
		
		mapper.deleteBoard(no);
		session.commit();
		
	}
	
	@RequestMapping("/board/detail.do")
	public ModelAndView detail(int no) throws Exception {
		
		BoardVO board = mapper.selectBoardByNo(no);
		
		FileVO fileVO = mapper.selectBoardFileByNo(no);
		
		ModelAndView mav = new ModelAndView("/board/detail");
		mav.addObject("board", board);
		mav.addObject("file", fileVO);
		
		return mav;
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView list(@RequestParam(value="pageNo", defaultValue="1") int pageNo) throws Exception {
		List<BoardVO> list = mapper.selectBoard();
		
		ModelAndView mav = new ModelAndView("/board/list");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/board/update.do")
	public void update(BoardVO boardVO) throws Exception {
		mapper.updateBoard(boardVO);
		session.commit();
		
	}
	
	@RequestMapping("/board/updateForm.do")
	public ModelAndView updateForm(int no) throws Exception {
		BoardVO board = mapper.selectBoardByNo(no);
		
		ModelAndView mav = new ModelAndView("/board/updateForm");
		mav.addObject("board", board);
		return mav;
	}

	@RequestMapping("/board/write.do")
	public void write(HttpServletRequest request) throws Exception {
		
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("/upload");
		
		SimpleDateFormat sdf = new SimpleDateFormat(
			"/yyyy/MM/dd"	
		);
		String datePath = sdf.format(new Date());
		
		uploadPath += datePath;
		
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		BoardVO comment = (BoardVO) WebUtil.toVO(request, BoardVO.class);
		mapper.insertBoard(comment);
	}
	@RequestMapping("/board/writeForm.do")
	public void writeForm() throws Exception {}
	
	
}
