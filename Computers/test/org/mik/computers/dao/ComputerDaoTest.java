package org.mik.computers.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mik.computers.domain.Computer;

public class ComputerDaoTest {

	private final static String NAME = "My computer"; //$NON-NLS-1$
	private final static String FIND_NAME = "find computer"; //$NON-NLS-1$
	private final static String MODIFIED_NAME = "My new computer"; //$NON-NLS-1$

	private BusesDao dao;

	@Before
	public void init() {
		this.dao = BusesDao.createComputerDao();
	}

	@After
	public void shutdown() {
		this.dao.shutdown();
	}

	@Test
	public void test() {
		insertUpdateDeleteTest();
		deleteAllTest();
		findTest();
		deleteAllTest();
		selectTest();
		deleteAllTest();
	}

	public void insertUpdateDeleteTest() {
		assertNotNull(this.dao);
		Computer computer = new Computer();
		computer.setDiskCapacity(120);
		assertEquals(computer.getDiskCapacity(), 120);
		computer.setMemory(2);
		assertEquals(computer.getMemory(), 2);
		computer.setName(NAME);
		assertEquals(NAME.compareTo(computer.getName()), 0);
		computer.setDisplayDensity(Computer.DISP_MDPI);
		assertEquals(computer.getDisplayDensity(), Computer.DISP_MDPI);
		computer.setType(Computer.TYPE_LAPTOP);
		assertEquals(computer.getType(), Computer.TYPE_LAPTOP);
		Computer newComputer = this.dao.insert(computer);
		Integer newId = newComputer.getId();
		assertNotNull(newId);
		newComputer.setName(MODIFIED_NAME);
		this.dao.update(newComputer);
		assertEquals(MODIFIED_NAME.compareTo(newComputer.getName()),0);
		try {
			this.dao.delete(newComputer);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}		
	}
	
	public void deleteAllTest() {
		assertNotNull(this.dao);
		try {
			this.dao.deleteAll();
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}	
	}
	 
	public void selectTest() {
		assertNotNull(this.dao);
		Computer computer = new Computer();
		computer.setDiskCapacity(120);
		assertEquals(computer.getDiskCapacity(), 120);
		computer.setMemory(2);
		assertEquals(computer.getMemory(), 2);
		computer.setName(NAME);
		assertEquals(NAME.compareTo(computer.getName()), 0);
		computer.setDisplayDensity(Computer.DISP_MDPI);
		assertEquals(computer.getDisplayDensity(), Computer.DISP_MDPI);
		computer.setType(Computer.TYPE_LAPTOP);
		assertEquals(computer.getType(), Computer.TYPE_LAPTOP);
		this.dao.insert(computer);
		
		computer = new Computer();
		computer.setDiskCapacity(130);
		assertEquals(computer.getDiskCapacity(), 130);
		computer.setMemory(4);
		assertEquals(computer.getMemory(), 4);
		computer.setName(MODIFIED_NAME);
		assertEquals(MODIFIED_NAME.compareTo(computer.getName()), 0);
		computer.setDisplayDensity(Computer.DISP_XXHDPI);
		assertEquals(computer.getDisplayDensity(), Computer.DISP_XXHDPI);
		computer.setType(Computer.TYPE_GAMER);
		assertEquals(computer.getType(), Computer.TYPE_GAMER);
		this.dao.insert(computer);
		
		int cnt=0;
		List<Computer> all = this.dao.listOrderById();
		for(Computer c:all) {
			assertNotNull(c.getId());
			++cnt;
		}
		assertEquals(cnt, 2);
	}
	
	public void findTest() {
		assertNotNull(this.dao);
		Computer computer = new Computer();
		computer.setName(FIND_NAME);
		computer.setDiskCapacity(120);
		computer.setDisplayDensity(Computer.DISP_LDPI);
		computer.setType(Computer.TYPE_SERVER);
		Computer nc=this.dao.insert(computer);
		Computer fc=this.dao.findById(nc.getId());
		assertEquals(nc,  fc);
		fc=this.dao.findByName(FIND_NAME);
		assertEquals(fc, nc);
		try {
			this.dao.delete(fc);
		}
		catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
