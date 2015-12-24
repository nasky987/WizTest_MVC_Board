package to.my.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import to.my.code.command.TicketCommand;
import to.my.code.dto.TicketDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private TicketCommand ticketCommand;
	
	@Autowired
	public void setTicketCommand(TicketCommand ticketCommand) {
		this.ticketCommand = ticketCommand;
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "ticket/buy_ticket";
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(TicketDto ticketDto, Model model) {
		System.out.println("buy_ticket_card");
		System.out.println("ticketDto = " + ticketDto.getConsumerId());
		System.out.println("ticketDto = " + ticketDto.getAmount());
		
		ticketCommand.execute(ticketDto);
		
		model.addAttribute("ticketInfo", ticketDto);
		
		return "ticket/buy_ticket_end";
	}
}
