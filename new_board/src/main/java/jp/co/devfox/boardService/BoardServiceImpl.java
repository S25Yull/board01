package jp.co.devfox.boardService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.boardDao.insert(map);
	    if (affectRowCount ==  1) {// 삽입 작업이 성공적인지 확인 (1행이 영향을 받음)
	        return map.get("no").toString(); // 성공한 경우, 맵에서 'no' 키의 값을 반환
	    }
	    return null;// 삽입이 성공하지 않은 경우, null 반환
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
	    return this.boardDao.selectDetail(map);
	}
}
