package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.dto.memo;
import kr.co.bit.utils.SingletonHelper;

/*

1. DB 연결
2. CRUD 함수 생성(1개 테이블에 대해서) >> memo 테이블
	2.1 전체조회	: select id, email, content from memo;
	2.2 조건조회	: select id, email, content from meno where id = ? (id >>  pk)
	2.3 삽입		: insert into memo(id, email, content) values(?,?,?);
	2.4 수정		: update memo set email = ?, content = ? where id = ?
	2.5 삭제		: delete from meno where id = ?
	2.6 검색 Like(검색): where email like '%naver%'
	
 */
public class memodao {
	Connection conn = null;
	
	public memodao() {
		conn = SingletonHelper.getConnection("oracle"); // singleton
		
	}
	
	//전체 조회
	public List<memo> getMemoList() throws SQLException{
		
		PreparedStatement pstmt = null;
		String sql = "select id, email, content from memo";
		
		pstmt = conn.prepareStatement(sql);
		ResultSet rs =  pstmt.executeQuery();
		
		List<memo> memolist = new ArrayList<memo>();//POINT
		//[new momo()][new momo()][new momo()][new momo()]
		while(rs.next()) {
			memo m = new memo();
			m.setId(rs.getString("id"));
			m.setEmail(rs.getString("email"));
			m.setContent(rs.getString("content"));
			
			memolist.add(m);
		}
		
		SingletonHelper.close(rs);
		SingletonHelper.close(pstmt);
		
		return memolist;
	}
	//조건 조회(where id=? : 데이터 1건 보장 id 컬럼 > unique, primary key
	public memo getMemoListById(String id) {
		
		//조건조회	: select id, email, content from meno where id = ? (id >>  pk)
		//memo m = new memo();
		//return
		
		
		
		return null;
	}
	//삽입
	// public int insertMemo(String id, String email, Stirng content)
	public int insertMemo(memo m) {
		
		int resutlrow = 0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into memo(id, email, content) values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getContent());
			
			resutlrow = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SingletonHelper.close(pstmt);
		}
		
		
		return resutlrow;
	}
	
	//수정
	public int updateMemo(memo m) {
		
		return 0;
	}
	//삭제
	public int deleteMemo(String id) {
		
		return 0;
	}
	//검색
	public memo idSearchByEmail(String email) {
		
		return null;
	}
	//id 유무 함수
	
	public String isCheckById(String id) {
		String ismemoid = null;
		PreparedStatement pstmt = null;
		String sql = "select id from memo where id=?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ismemoid="false";
			}else {
				ismemoid = "true";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			SingletonHelper.close(rs);
			SingletonHelper.close(pstmt);
		}
		
		
		
		return ismemoid;
	}
}






















