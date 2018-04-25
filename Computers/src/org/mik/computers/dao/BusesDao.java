package org.mik.computers.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.mik.computers.domain.Computer;

public class BusesDao extends AbstractDao<Computer> {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM computer c WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_FIND_BY_NAME = "SELECT * FROM computer c WHERE name = ?"; //$NON-NLS-1$

	private static final String SQL_LIST_ORDER_BY_ID = "SELECT * FROM computer c ORDER BY id"; //$NON-NLS-1$

	private static final String SQL_INSERT = "INSERT INTO computer (" //$NON-NLS-1$
			+ Computer.COL_NAME + ", " //$NON-NLS-1$
			+ Computer.COL_TYPE + "," //$NON-NLS-1$
			+ Computer.COL_MEMORY + "," //$NON-NLS-1$
			+ Computer.COL_DISK_CAPACITY + "," //$NON-NLS-1$
			+ Computer.COL_DISPLAY_DENSITY + ") VALUES (?, ?, ?, ?, ?)"; //$NON-NLS-1$

	private static final String SQL_UPDATE = "UPDATE Computer SET " //$NON-NLS-1$
			+ Computer.COL_NAME + "=?," //$NON-NLS-1$
			+ Computer.COL_TYPE + "= ?, " //$NON-NLS-1$
			+ Computer.COL_MEMORY + "=?," //$NON-NLS-1$
			+ Computer.COL_DISK_CAPACITY + " = ?, " //$NON-NLS-1$
			+ Computer.COL_DISPLAY_DENSITY + " = ? WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_CREATE_TABLE = "CREATE TABLE if not exists computer ( id integer identity primary key, "+  //$NON-NLS-1$
                              Computer.COL_NAME + " varchar(40), " +  //$NON-NLS-1$
                              Computer.COL_TYPE + " int," +  //$NON-NLS-1$
                              Computer.COL_MEMORY + " int,"+  //$NON-NLS-1$
                              Computer.COL_DISK_CAPACITY + " int,"+ //$NON-NLS-1$
                              Computer.COL_DISPLAY_DENSITY + " int);"; //$NON-NLS-1$
	
	private static final String SQL_DELETE = "DELETE FROM Computer WHERE id = ?"; //$NON-NLS-1$

	private static final String SQL_DELETE_ALL = "DELETE FROM Computer"; //$NON-NLS-1$

	public BusesDao(String url, String user, String password) {
		super(url, user, password);
		checkComputerTable();
	}
	@Override
	protected Computer createDomain(ResultSet rs) {
		return new Computer(rs);
	}

	public Computer findById(Integer id) {
		return find(SQL_FIND_BY_ID, id);
	}

	public Computer findByName(String name) {
		return find(SQL_FIND_BY_NAME, name);
	}

	public List<Computer> listOrderById() {
		return list(SQL_LIST_ORDER_BY_ID);
	}

	@Override
	protected Object[] getDomainValues(Computer domain) {
		return new Object[] { domain.getName(),
				Integer.valueOf(domain.getType()),
				Integer.valueOf(domain.getMemory()),
				Integer.valueOf(domain.getDiskCapacity()),
				Integer.valueOf(domain.getDisplayDensity()) };
	}

	@Override
	protected Object[] getDomainValuesForUpdate(Computer domain) {
		return new Object[] { domain.getName(),
				Integer.valueOf(domain.getType()),
				Integer.valueOf(domain.getMemory()),
				Integer.valueOf(domain.getDiskCapacity()),
				Integer.valueOf(domain.getDisplayDensity()),
				domain.getId()
		};
	}

	@Override
	protected String getInsertSql() {
		return SQL_INSERT;
	}

	@Override
	protected String getDeleteSql() {
		return SQL_DELETE;
	}

	@Override
	protected String getDeleteAllSql() {
		return SQL_DELETE_ALL;
	}

	@Override
	protected String getUpdateSql() {
		return SQL_UPDATE;
	}
	
	public static BusesDao createComputerDao() {
		BusesDao result = new BusesDao(DB_URI, DB_USER, DB_PASSWORD);
		return result;
	}

	private void checkComputerTable() {
		try (Statement stmt = this.connection.createStatement()) {
			stmt.execute(SQL_CREATE_TABLE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
