package to.my.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import to.my.code.dto.ContentDto;

public class ContentDao implements IDao {

	JdbcTemplate template2;
	
	@Autowired
	public void setTemplate2(JdbcTemplate template2) {
		this.template2 = template2;
	}
	
	@Override
	public ArrayList<ContentDto> listDao() {
		String query = "SELECT * FROM board ORDER BY mId DESC";
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>)template2.query(query, 
				new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
		
		return dtos;
	}
	
	@Override
	public void writeDao(final String mWriter, final String mContent) {
		System.out.println("writeDao()");
		
		this.template2.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String query = "INSERT INTO board (mId, mWriter, mContent) VALUES (BOARD_SEQ.NEXTVAL, ?, ?)";
				
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, mWriter);
				preparedStatement.setString(2, mContent);
				
				return preparedStatement;
			}
		});
	}
	
	@Override
	public ContentDto viewDao(String strId) {
		System.out.println("viewDao()");
		
		String query = "SELECT * FROM BOARD WHERE mId = " + strId;
		return template2.queryForObject(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
		
	}
	
	@Override
	public void deleteDao(final String mId) {
		System.out.println("deleteDao()");
		
		String query = "DELETE FROM board WHERE mId = ?";
		this.template2.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, Integer.parseInt(mId));;
			}
		});
	}
	
}
