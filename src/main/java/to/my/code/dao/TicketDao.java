package to.my.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import to.my.code.dto.TicketDto;

public class TicketDao {
	JdbcTemplate template;
	TransactionTemplate transactionTemplate;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public TicketDao() {
		System.out.println(template);
	}
	
	public void buyTicket(final TicketDto dto) {
		System.out.println("buyTicket()");
		System.out.println("dto.getConsumerId() : " + dto.getConsumerId());
		System.out.println("dto.getAmount() : " + dto.getAmount());
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						String query = "INSERT INTO CARD (consumerId, amount) VALUES(?, ?)";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, dto.getConsumerId());
						preparedStatement.setString(2, dto.getAmount());
						
						return preparedStatement;
					}
				});
				
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						String query = "INSERT INTO TICKET (consumerId, countnum) VALUES(?, ?)";
						PreparedStatement preparedStatement = connection.prepareStatement(query);
						preparedStatement.setString(1, dto.getConsumerId());
						preparedStatement.setString(2, dto.getAmount());
						
						return preparedStatement;
					}
				});
			}
		});
	}
}
