package com.kh.prac.member.model.service;

import static com.kh.prac.common.JdbcTemplate.*;
import java.sql.Connection;

import com.kh.prac.member.model.dao.MemberDao;
import com.kh.prac.member.model.dto.Member;

public class MemberService {

	private MemberDao memberDao = new MemberDao();
	/**
	 * DQL 요청 - service
	 * 1. Connection 객체 생성
	 * 2. Dao 요청 & Connection 전달
	 * 3. Connection 반환(close)
	 */

	public Member findById(String memberId) {
		
		Connection conn = getConnection();
		Member member = memberDao.findById(conn, memberId);
		close(conn);
		return member;
	}
	
	/**
	 * DML 요청 - service
	 * 1. Connection 객체 생성
	 * 2. Dao 요청 & Connection 전달
	 * 3. 트랜잭션 처리(정상처리 commit, 예외발생시 rollback)
	 * 4. Connection 객체 반환(close)
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	
}
