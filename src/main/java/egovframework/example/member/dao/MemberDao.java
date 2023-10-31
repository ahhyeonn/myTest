package egovframework.example.member.dao;

import javax.servlet.http.HttpSession;

import egovframework.example.member.vo.MemberVo;

public interface MemberDao {
	
	//로그인 체크
	public boolean loginCheck(MemberVo vo);
	
	//로그인 정보
	public MemberVo viewMember(MemberVo vo);
	
	//로그아웃
	public void logout(HttpSession session);
	
	
}
