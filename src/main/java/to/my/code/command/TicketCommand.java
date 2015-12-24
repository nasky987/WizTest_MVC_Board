package to.my.code.command;

import to.my.code.dto.TicketDto;

public interface TicketCommand {
	public void execute(TicketDto ticketDto);
}
