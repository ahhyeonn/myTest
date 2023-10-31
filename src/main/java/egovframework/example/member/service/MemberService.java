package egovframework.example.member.service;

import javax.servlet.http.HttpSession;

import egovframework.example.member.vo.MemberVo;

public interface MemberService {
	
	//로그인 체크
	public boolean loginCheck(MemberVo vo, HttpSession session);
	
	//로그인 정보
	public MemberVo viewMember(MemberVo vo);
	
	//로그아웃
	public void logout(HttpSession session);
	
	
}
