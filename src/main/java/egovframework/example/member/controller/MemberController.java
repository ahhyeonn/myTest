package egovframework.example.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import egovframework.example.member.service.MemberService;
import egovframework.example.member.vo.MemberVo;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	MemberService memberService;

	// 로그인 화면
	@RequestMapping(value = "/login.do")
	public String login() {
		return "member/login"; // login.jsp
	}

	// 로그인
	@RequestMapping(value = "/loginCheck.do")
	public ModelAndView loginCheck(@ModelAttribute MemberVo vo, HttpSession session) {
//		System.out.println("로그인!!!!!!!!!!!!!!!!");
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
//        System.out.println("result : " + result);

		if (result == true) { // 로그인 성공
			// main.jsp로 이동
			mav.setViewName("member/main");
			mav.addObject("msg", "success");

		} else { // 로그인 실패
			// login.jsp로 이동
			mav.setViewName("member/login");
			mav.addObject("msg", "failure");
		}
		return mav;

	}

	// 로그아웃
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpSession session) {
		memberService.logout(session);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/logout");
		mav.addObject("msg", "logout");
		return mav;
	}

	// 회원가입 화면으로 가기
	@RequestMapping(value = "/join.do")
	public String join_view() {

		return "member/join";
	}

	
	
}









