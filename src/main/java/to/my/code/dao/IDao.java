package to.my.code.dao;

import java.util.ArrayList;

import to.my.code.dto.ContentDto;

public interface IDao {
	public ArrayList<ContentDto> listDao();
	public void writeDao(final String mWriter, final String mContent);
	public ContentDto viewDao(String strId);
	public void deleteDao(final String mId);
}
