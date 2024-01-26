package jp.co.devfox.boardService;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	//insert
	public int insert(Map<String, Object> map) {
		return this.sqlSessionTemplate.insert("board_list.insert", map);
	}
	//select
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("board_list.selectdetail", map); 
	    //sqlSessionTemplate 의 selectOne 메소드는 데이터를 한 개만 가져올 때 사용
	}
	
}
