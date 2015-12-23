package to.my.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import to.my.code.dto.BDto;
import to.my.code.util.Constant;

public class BDao {
	DataSource dataSource;
	JdbcTemplate template;
	
	public BDao() {
		template = Constant.template;
	}
	
	public ArrayList<BDto> list() {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent ");
		query.append("FROM MVC_BOARD ");
		query.append("ORDER BY bGroup desc, bStep asc ");	

		return (ArrayList<BDto>) template.query(query.toString(), new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void write(final String bName, final String bTitle, final String bContent) {
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				StringBuffer query = new StringBuffer();
				
				query.append("INSERT INTO MVC_BOARD(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ");
				query.append("VALUES (MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, MVC_BOARD_SEQ.CURRVAL, 0, 0) ");
				
				PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
				return preparedStatement;
			}
		});
	}
	
	public BDto contentView(final String bId) {
		upHit(bId);
		
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT * ");
		query.append("FROM MVC_BOARD ");
		query.append("WHERE bId = ? ");
		
		return template.queryForObject(query.toString(), new Object[]{bId}, new BeanPropertyRowMapper<BDto>(BDto.class));
	}

	public void modify(final String bId, final String bName, final String bTitle, final String bContent) {
		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE MVC_BOARD ");
		query.append("SET bName = ?, bTitle = ?, bContent = ? ");
		query.append("WHERE bId = ? ");
		
		template.update(query.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt(bId));
			}
		});
	}
	
	public void delete(final String bId) {
		StringBuffer query = new StringBuffer();
		
		query.append("DELETE FROM MVC_BOARD ");
		query.append("WHERE bId = ? ");
		
		template.update(query.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, Integer.parseInt(bId));
			}
		});
	}
	
	public BDto replyView(final String bId) {
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT * ");
		query.append("FROM MVC_BOARD ");
		query.append("WHERE bId = ? ");
		
		return template.queryForObject(query.toString(), new Object[]{bId}, new BeanPropertyRowMapper<BDto>(BDto.class));
	}
	
	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent) {
		replyShape(bGroup, bStep);
		
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO MVC_BOARD(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ");
		query.append("VALUES (MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, ?, ?) ");
		
		template.update(query.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt(bGroup));
				preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
				preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			}
		});
	}
	
	public void upHit(final String bId) {
		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE MVC_BOARD ");
		query.append("SET bHit = bHit + 1 ");
		query.append("WHERE bId = ?");
		
		template.update(query.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
	}
	
	public void replyShape(final String bGroup, final String bStep) {
		StringBuffer query = new StringBuffer();
		
		query.append("UPDATE MVC_BOARD ");
		query.append("SET bStep = bStep + 1 ");
		query.append("WHERE bGroup = ? ");
		query.append("AND bStep > ? ");
		
		template.update(query.toString(), new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, Integer.parseInt(bGroup));
				preparedStatement.setInt(2, Integer.parseInt(bStep));
			}
		});
	}
}
