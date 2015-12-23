package to.my.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import to.my.code.dto.BDto;

public class BDao {
	DataSource dataSource;
	
	public BDao() {
		try {
			dataSource = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("SELECT bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent ");
			query.append("FROM MVC_BOARD ");
			query.append("ORDER BY bGroup desc, bStep asc ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dtos.add(new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != resultSet) try{ resultSet.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return dtos;
	}
	
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try{
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("INSERT INTO MVC_BOARD(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ");
			query.append("VALUES (MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, MVC_BOARD_SEQ.CURRVAL, 0, 0) ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			
			int rn = preparedStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public BDto contentView(String bId) {
		upHit(bId);
		
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("SELECT * ");
			query.append("FROM MVC_BOARD ");
			query.append("WHERE bId = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, Integer.parseInt(bId));
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int _bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(_bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != resultSet) try{ resultSet.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return dto;
	}
	
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("UPDATE MVC_BOARD ");
			query.append("SET bName = ?, bTitle = ?, bContent = ? ");
			query.append("WHERE bId = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			
			int rn = preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	
	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("DELETE FROM MVC_BOARD ");
			query.append("WHERE bId = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			
			preparedStatement.setInt(1, Integer.parseInt(bId));
			
			int rn = preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public BDto replyView(String bId) {
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("SELECT * ");
			query.append("FROM MVC_BOARD ");
			query.append("WHERE bId = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, Integer.parseInt(bId));
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int _bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BDto(_bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != resultSet) try{ resultSet.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return dto;
	}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try{
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("INSERT INTO MVC_BOARD(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) ");
			query.append("VALUES (MVC_BOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, ?, ?) ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep) + 1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1);
			
			int rn = preparedStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public void upHit(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("UPDATE MVC_BOARD ");
			query.append("SET bHit = bHit + 1 ");
			query.append("WHERE bId = ?");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, bId);
			
			int rn = preparedStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
	public void replyShape(String bGroup, String bStep) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		StringBuffer query = null;
		
		try {
			connection = dataSource.getConnection();
			query = new StringBuffer();
			
			query.append("UPDATE MVC_BOARD ");
			query.append("SET bStep = bStep + 1 ");
			query.append("WHERE bGroup = ? ");
			query.append("AND bStep > ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, Integer.parseInt(bGroup));
			preparedStatement.setInt(2, Integer.parseInt(bStep));
			
			int rn = preparedStatement.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(null != query) { query.setLength(0); query = null; }
			if(null != preparedStatement) try{ preparedStatement.close(); } catch(Exception e) { e.printStackTrace(); }
			if(null != connection) try{ connection.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
}
