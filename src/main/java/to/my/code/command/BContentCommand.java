package to.my.code.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import to.my.code.dao.BDao;
import to.my.code.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto dto = null;//dao.contentView(bId);
		
		model.addAttribute("content_view", dto);
	}

}
