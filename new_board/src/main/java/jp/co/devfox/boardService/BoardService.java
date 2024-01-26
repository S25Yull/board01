package jp.co.devfox.boardService;

import java.util.Map;

public interface BoardService {

	String create(Map<String, Object> map);

	Map<String, Object> detail(Map<String, Object> map);


}
