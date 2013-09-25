package dao.common;

import java.util.List;

public class PaginationResult<T> {
	public List<T> list;
	public int offset;
	public int limit;
	public int totalCount;
	
}
