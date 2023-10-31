package egovframework.example.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.example.board.service.BoardService;
import egovframework.example.board.vo.Search;
import egovframework.example.board.vo.BoardVo;
import egovframework.example.board.vo.CmntVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시글 목록
//	@RequestMapping(value = "/testList.do")
//	public String testListDo(boardVo boardVo, Model model) throws Exception {
//
//		model.addAttribute("list", testService.selectTest(boardVo));
//
//		return "test/testList";
//	}

	// 글목록페이지,페이징,검색
	@RequestMapping(value = "/list.do")
	public String testListDo(Model model, @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String keyword, @ModelAttribute("search") Search search) throws Exception {

		// 검색
		model.addAttribute("search", search);
		search.setSearchType(searchType);
		search.setKeyword(keyword);

		// 전체 게시글 개수
		int listCnt = boardService.getBoardListCnt(search);
		
		System.out.println("전체 게시글 개수 : " + listCnt);
		
		// 검색 후 페이지
		search.pageInfo(page, range, listCnt);
		// 페이징
		model.addAttribute("pagination", search);
		// 게시글 화면 출력
		model.addAttribute("list", boardService.selectTest(search));
		

		return "board/list";
	}

	// 게시글 작성 페이지 이동
	@RequestMapping(value = "/register.do")
	public String testRegister() {
		return "board/register";
	}

	// 게시글 등록
	@RequestMapping(value = "/insertTest.do")
	public String write(@ModelAttribute("boardVo") BoardVo boardVo, HttpSession session) throws Exception {
//		System.out.println("내용:"+ boardVo.getContent());

		boardService.insertTest(boardVo);
		return "redirect:list.do";
	}

	// 게시글 상세보기
	@RequestMapping(value = "/detail.do")
	public String viewForm(Model model, CmntVo cmntVo, HttpServletRequest request) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//게시글 상세보기
		BoardVo boardVo = boardService.selectDetail(boardNo);
		model.addAttribute("vo", boardVo);
		
		//댓글 목록
//		List<CmntVo> comment = boardService.listCmnt(boardNo);
//		model.addAttribute("comment",comment);
		
		//대댓글 목록
		List<CmntVo> reply = boardService.listReply(boardNo);
		model.addAttribute("comment",reply);

		return "board/detail";
	}
	
	// 게시글 수정 페이지로 이동
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String updateTest(int boardNo, Model model) throws Exception {
		BoardVo boardVo = this.boardService.selectDetail(boardNo);
		model.addAttribute("vo", boardVo);
		return "board/update";
	}
	
	// 게시글 수정
	@RequestMapping(value = "/updateTest.do", method = RequestMethod.POST)
	public String updateTest(BoardVo boardVo) throws Exception {
		boardService.updateTest(boardVo);
		return "redirect:list.do";
	}

	// 게시글 삭제
	@RequestMapping(value = "/deleteTest.do")
	public String deleteTest(HttpServletRequest request) throws Exception {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		boardService.deleteTest(boardNo);
		return "redirect:list.do";
	}
	
	
	//댓글 등록, 수정
	@RequestMapping(value="/insertCmnt.do")
	public void insertCmnt(
	   
	         @RequestParam HashMap<String, Object> paramMap
	                  ,HttpSession session
	                  ,HttpServletResponse response
	         ) throws Exception {
	      
	      CmntVo myMapping = new CmntVo();
	      myMapping.setCmntContent(paramMap.get("cmntContent").toString());
	      myMapping.setBoardNo(Integer.parseInt(paramMap.get("boardNo").toString()));
	      int result; 
	      
	      if(paramMap.containsKey("cmntNo")){
	         myMapping.setCmntNo(Integer.parseInt(paramMap.get("cmntNo").toString()));
	         result = boardService.updateCmnt(myMapping);
	      } else {
	         myMapping.setMemId(paramMap.get("memId").toString());
	         result = boardService.insertCmnt(myMapping);
	      }

	      response.setContentType("text/html;charset=utf-8");
	      response.getWriter().print(result);
	      response.getWriter().flush();
	      response.getWriter().close();
	      
	   }

	   
	
	
	//댓글 삭제
	@PostMapping(value="/deleteCmnt.do")
	@ResponseBody
	public void deleteCmnt(HttpServletRequest request
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
//		System.out.println("여기까지 오긴 왔나요");
		
		int cmntNo = Integer.parseInt(request.getParameter("cmntNo"));
		boardService.deleteCmnt(cmntNo);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(cmntNo);
		response.getWriter().flush();
		response.getWriter().close();
		
	}
	
	//댓글 수정
	/*
	@RequestMapping(value = "/updateCmnt.do", method = RequestMethod.POST)
	@ResponseBody
	public void updateCmnt(
						 @RequestParam(value="cmntContent") String cmntContent
						,@RequestParam(value="boardNo") int boardNo
						,@RequestParam(value="boardNo") int cmntNo
						
//						@RequestBody CmntVo cmntVo
//						@RequestBody HashMap<String, Object> paramMap
						
						,HttpSession session
						,HttpServletResponse response
						
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????===========");
		
//		Map<String, Object> reMap = new HashMap<String, Object>();
//		reMap.put("reMsg", "OK!!");
//		return reMap;
		
		CmntVo myMapping = new CmntVo();
		myMapping.setCmntContent(cmntContent);
		myMapping.setBoardNo(boardNo);
		myMapping.setCmntNo(cmntNo);
		
		int result = boardService.updateCmnt(myMapping);
//		int result = boardService.updateCmnt(cmntVo);
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
		
//		if (result > 0) {
//			return "success";
//		} else {
//			return "fail";
//		}
		
		
	}	
	*/

	/*
   @PostMapping(value="/updateCmntTemp.do")
   public void updateCmntTmp(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request,
            HttpServletResponse response, HttpSession session
            ) throws Exception{
	   
	   		System.out.println("===========여기까지 왔나요????===========");
	   
            CmntVo myMapping = new CmntVo();
            myMapping.setCmntContent(paramMap.get("cmntContent").toString());
            myMapping.setBoardNo(Integer.parseInt(paramMap.get("boardNo").toString()));
            myMapping.setCmntNo(Integer.parseInt(paramMap.get("cmntNo").toString()));
            
            int result = boardService.updateCmnt(myMapping);
      //      int result = boardService.updateCmnt(cmntVo);
            
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(result);
            response.getWriter().flush();
            response.getWriter().close();
         };
	
	*/
	
	
	//===============대댓글===============
	
	
	//대댓글 등록
	@RequestMapping(value="/insertReply.do")
	@ResponseBody
	public void insertReply(
						 @RequestParam(value="boardNo") int boardNo
						,@RequestParam(value="cmntContent") String cmntContent
						,@RequestParam(value="memId") String memId
						,@RequestParam(value="cmntNo") int cmntNo
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????===========");
		
		CmntVo myMapping = new CmntVo();
		
		myMapping.setCmntContent(cmntContent);
		myMapping.setBoardNo(boardNo);
		myMapping.setCmntGroup(cmntNo);
		myMapping.setMemId(memId);
		int cmntDepth = 1;
		myMapping.setCmntDepth(cmntDepth);
//		myMapping.setUpCmntNo(upCmntNo);
//		
		
		int result = boardService.insertReply(myMapping);
		
		//ajax로 db는 보냈는데 response가 안됐을때 이거 추가해서 해결함 +HttpServletResponse response
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
	}	
	/*
	//대댓글 수정
	@RequestMapping(value="/updateReply.do")
	@ResponseBody
	public void updateReply(
//						 @RequestParam(value="boardNo") int boardNo
						@RequestParam(value="cmntContent") String cmntContent
//						,@RequestParam(value="memId") String memId
//						,@RequestParam(value="cmntNo") int cmntNo
						,HttpSession session
						,HttpServletResponse response
			) throws Exception {
		
		System.out.println("===========여기까지 왔나요????===========");
		
		CmntVo myMapping = new CmntVo();
		
		myMapping.setCmntContent(cmntContent);
//		myMapping.setBoardNo(boardNo);
//		myMapping.setCmntGroup(cmntNo);
//		myMapping.setMemId(memId);
		
		int result = boardService.insertReply(myMapping);
		
		//ajax로 db는 보냈는데 response가 안됐을때 이거 추가해서 해결함 +HttpServletResponse response
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
		
	}	
	
	*/
	

}






