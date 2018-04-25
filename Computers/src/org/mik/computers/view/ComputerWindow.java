package org.mik.computers.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Computer form
 * 
 * You can make a better architecture for form handling
 * 
 * @author Joshua Wambua
 *
 */
public class ComputerWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Computer view
	 */
	private ComputerPanel computerView;
	/**
	 * prev buton
	 */
	protected JButton btnPrev;
	/**
	 * insert button
	 */
	protected JButton btnInsert;
	/**
	 * update button
	 */
	protected JButton btnUpdate;
	/**
	 * delete button
	 */
	protected JButton btnDelete;
	/**
	 * next button
	 */
	protected JButton btnNext;
	/**
	 * close form button
	 */
	protected JButton btnClose;
	/**
	 * Controller
	 */
	protected ComputerController controller;
	
	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public ComputerWindow(ComputerController controller) {
		super();
		setLayout(new BorderLayout());
		this.controller = controller;
		createGUI();
		pack();
	}

	/**
	 * getter for compute view
	 * @return
	 */
	public ComputerPanel getComputerPanel() {
		return this.computerView;
	}

	/**
	 * Getter for prev button
	 * @return
	 */
	public JButton getPrevButton() {
		return this.btnPrev;
	}
	
	/**
	 * Getter for insert button
	 * @return
	 */
	public JButton getInsertButton() {
		return this.btnInsert;
	}
	
	/**
	 * Getter for update button
	 * @return
	 */
	public JButton getUpdateButton() {
		return this.btnUpdate;
	}
	
	/**
	 * Getter for delete button
	 * @return
	 */
	public JButton getDeleteButton() {
		return this.btnDelete;
	}
	
	/**
	 * Getter for next button
	 * @return
	 */
	public JButton getNextButton() {
		return this.btnNext;
	}

	/**
	 * Create gui 
	 */
	private void createGUI() {
		this.computerView = new ComputerPanel();
		add(this.computerView, BorderLayout.CENTER);
		add(createButtons(), BorderLayout.SOUTH);
	}
	
	/**
	 * Create buttons
	 * 
	 * @return
	 */
	private JPanel createButtons() {
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout());
		this.btnPrev = new JButton("Prev"); //$NON-NLS-1$
		this.btnPrev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.prev();
			}
		});
		result.add(this.btnPrev);
		
		this.btnInsert = new JButton("Insert"); //$NON-NLS-1$
		this.btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.insert();
			}
		});
		result.add(this.btnInsert);
		
		this.btnUpdate=new JButton("Update"); //$NON-NLS-1$
		this.btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.update();
			}
		});
		result.add(this.btnUpdate);
		
		this.btnDelete = new JButton("Delete"); //$NON-NLS-1$
		this.btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.delete();
			}
		});
		result.add(this.btnDelete);
		
		this.btnNext = new JButton("Next"); //$NON-NLS-1$
		this.btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.next();
			}
		});
		result.add(this.btnNext);
		
		this.btnClose = new JButton("Close"); //$NON-NLS-1$
		this.btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ComputerWindow.this.controller.close();
			}
		});
		result.add(this.btnClose);
		
		return result;
	}
}
