package org.mik.computers.view;

import java.util.List;

import org.mik.computers.dao.BusesDao;
import org.mik.computers.domain.Computer;

/**
 * Controller for MVC design pattern 
 * 
 * @author Joshua Wambua
 *
 */
public class ComputerController {
	
	/**
	 * DAO
	 */
	private BusesDao computerDao;
	/**
	 * Form
	 */
	private ComputerWindow computerWindow;
	
	/**
	 * Current computer 
	 */
	private Computer currentComputer;

	/**
	 * All computer list 
	 */
	private List<Computer> all;

	/**
	 * Constructor
	 */
	public ComputerController () {
		this.computerDao = BusesDao.createComputerDao();
		this.computerWindow = new ComputerWindow(this);
		first();
	}

	/**
	 * Getter for Computer window
	 * 
	 * @return ComputerWindow
	 */
	public ComputerWindow getComputerWindow() {
		return this.computerWindow;
	}
	
	/**
	 * Load all computer list and select first element as current comuter if the list is not empty
	 */
	public void first() {
		this.setCurrent(null);
		this.all=this.computerDao.listOrderById();
		if (this.all.size()>0)
			setCurrent(this.all.get(0));
		updateGUI();
	}
	
	/**
	 * Select previous computer in the list, if the list is not empty and the current computer is not the first element in the list.
	 */
	public void prev() {
		if (this.currentComputer !=null && this.all.indexOf(this.currentComputer)>0) 
			setCurrent(this.all.get(this.all.indexOf(this.currentComputer)-1));
		
		updateGUI();
	}
	
	/**
	 * Select next computer in the list if the list is not empty and the current computer is not the last element in the list.
	 */
	public void next() {
		if (this.currentComputer!=null && this.all.indexOf(this.currentComputer)<this.all.size()-1) {
			setCurrent(this.all.get(this.all.indexOf(this.currentComputer)+1));
		}
		updateGUI();
	}
	
	/**
	 * Create a new Computer and set it to current 
	 */
	public void insert() {
		setCurrent(new Computer());
		updateGUI();
	}
	
	/**
	 * update current computer data to database
	 */
	public void update() {
		setCurrent(this.computerWindow.getComputerPanel().getData());
		if (this.currentComputer.getId()==null) 
			setCurrent(this.computerDao.insert(this.currentComputer));
		else
			this.computerDao.update(this.currentComputer);
		Integer id = this.currentComputer.getId();
		first();
		for(Computer c:this.all) 
			if (c.getId().equals(id)) {
				setCurrent(c);
				updateGUI();
				break;
			}
	}
	
	/**
	 * Delete the current computer from database
	 */
	public void delete() {
		try {
			this.computerDao.delete(this.currentComputer);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		first();
	}
	
	/**
	 * Close the computer window
	 */
	public void close() {
		this.computerWindow.setVisible(false);
	}
	
	public void shutdown() {
		this.computerDao.shutdown();
	}
	
	/**
	 * Upate GUI
	 * 
	 * Set the state of buttons
	 * There some bug in this code, you can fix it
	 */
	private void updateGUI() {
		this.computerWindow.btnDelete.setEnabled(this.currentComputer!=null);
		this.computerWindow.btnUpdate.setEnabled(this.currentComputer!=null);
		int idx = this.all.indexOf(this.currentComputer);
		this.computerWindow.btnPrev.setEnabled(!this.all.isEmpty() && idx>0);
		this.computerWindow.btnNext.setEnabled(!this.all.isEmpty() && idx<this.all.size()-1);
	}
	
	/**
	 * Set the current computer
	 * 
	 * @param c the current computer
	 */
	private void setCurrent(Computer c) {
		this.currentComputer = c;
		this.computerWindow.getComputerPanel().setData(c);
	}
}

