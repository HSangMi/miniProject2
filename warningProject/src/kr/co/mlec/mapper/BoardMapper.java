package kr.co.mlec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BoardMapper {
	
	public void insertBoard(BoardVO board) throws Exception;
	public int updateBoard(BoardVO board) throws Exception;
	public int deleteBoard(int no) throws Exception;
	public List<BoardVO> selectBoard() throws Exception;
	public BoardVO selectBoardByNo(int no) throws Exception;

	public void insertBoardFile(FileVO fileVO) throws Exception;
	public FileVO selectBoardFileByNo(int fileNo) throws Exception;
	
	public List<CommentVO> selectBoardCommentByNo(int no) throws Exception;
	public void insertBoardComment(CommentVO comment) throws Exception;
	public void deleteBoardComment(int commentNo) throws Exception;
	public void updateBoardComment(CommentVO comment) throws Exception;
	
}









