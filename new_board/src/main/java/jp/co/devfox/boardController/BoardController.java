package jp.co.devfox.boardController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.devfox.boardService.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	//리스트
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("board/list");
	}
	
	//write
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write() {
		return new ModelAndView("board/write");
	}
	
	//write save
	@RequestMapping(value = "/WriteServlet", method = RequestMethod.POST)
	public ModelAndView boardSave(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String no = this.boardService.create(map);
	    if (no == null) {
	        mav.setViewName("redirect:/board/write");
	    }else {
	        mav.setViewName("redirect:/board/detail?Id=" + no); 
	    }  

	    return mav;
	}
	
	@RequestMapping(value = "/WriteServletGET", method = RequestMethod.GET)
	public ModelAndView boardSaveGET() {
	    // GET 요청을 처리하는 로직 추가
	    return new ModelAndView("board/getRequestPage");
	}
	
	//detail
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.boardService.detail(map); //안들어가고있음
	    
	    System.out.println(detailMap); //null
	    
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap); // "data"라는 이름으로 detailMap을 저장
	    
	    String id = map.get("no").toString(); 
	    mav.addObject("no", id); 
	    
	    mav.setViewName("/board/detail");

	    return mav;
	}

}
