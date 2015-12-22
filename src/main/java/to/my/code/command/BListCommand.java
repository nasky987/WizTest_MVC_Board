package to.my.code.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import to.my.code.dao.BDao;
import to.my.code.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}

}
