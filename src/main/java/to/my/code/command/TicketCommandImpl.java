package to.my.code.command;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import to.my.code.dao.TicketDao;
import to.my.code.dto.TicketDto;

public class TicketCommandImpl implements TicketCommand {
	private TicketDao ticketDao;
	private TransactionTemplate transactionTemplate2;
	
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public TicketDao getTicketDao() {
		return ticketDao;
	}
	
	@Override
	public void execute(final TicketDto ticketDto) {
		transactionTemplate2.execute(new TransactionCallbackWithoutResult(){

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				ticketDto.setAmount("1");
				ticketDao.buyTicket(ticketDto);
				
				ticketDto.setAmount("5");
				ticketDao.buyTicket(ticketDto);
			}
		});
	}
}
