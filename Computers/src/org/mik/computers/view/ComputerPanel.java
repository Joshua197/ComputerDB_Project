package org.mik.computers.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.mik.computers.domain.Computer;

/**
 * The domain form
 * 
 * @author Joshua Wambua
 *
 */
public class ComputerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField tfName;
	protected JComboBox<String> cbType;
	protected JSpinner spMemory;
	protected JSpinner spDisk;
	protected JComboBox<String> cbDisplay;
	protected Computer computer;
	
	/**
	 * Create the panel.
	 */
	public ComputerPanel() {
		super();
		createGUI();
	}
	
	/**
	 * Create GUI 
	 */
	private void createGUI() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		/**
		 * Layout is GridbagLayout. 
		 */
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		
		//TODO If you need more rows, you can add a 0 to rowHeights array
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//TODO name label
		JLabel lbName = new JLabel("Name"); //$NON-NLS-1$
		GridBagConstraints gbc_lbName = new GridBagConstraints();
		gbc_lbName.insets=new Insets(10, 5, 5, 5);
		gbc_lbName.anchor = GridBagConstraints.EAST;
		gbc_lbName.insets = new Insets(0, 0, 5, 5);
		gbc_lbName.gridx = 0;							// column index
		gbc_lbName.gridy = 0;							// row index
		add(lbName, gbc_lbName);
		
		//TODO name textfield
		this.tfName = new JTextField();
		GridBagConstraints gbc_tfName = new GridBagConstraints();
		gbc_tfName.insets = new Insets(10, 0, 5, 5);
		gbc_tfName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfName.gridx = 2;							// column index
		gbc_tfName.gridy = 0;							// row index
		add(this.tfName, gbc_tfName);
		this.tfName.setColumns(40);
		this.tfName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (ComputerPanel.this.computer!=null) 
					ComputerPanel.this.computer.setName(ComputerPanel.this.tfName.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				//NC
			}
		});
				
		//TODO label for type
		JLabel lbType = new JLabel("Type"); //$NON-NLS-1$
		GridBagConstraints gbc_lbType = new GridBagConstraints();
		gbc_lbType.anchor = GridBagConstraints.EAST;
		gbc_lbType.insets = new Insets(0, 0, 5, 5);
		gbc_lbType.gridx = 0;						// column index
		gbc_lbType.gridy = 1;						// row index
		add(lbType, gbc_lbType);
		
		//TODO type combo box 
		this.cbType = new JComboBox<>(Computer.TYPE_VALUES);
		GridBagConstraints gbc_cbType = new GridBagConstraints();
		gbc_cbType.insets = new Insets(0, 0, 5, 5);
		gbc_cbType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbType.gridx = 2;						// column index
		gbc_cbType.gridy = 1;						// row index
		this.cbType.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ComputerPanel.this.computer!=null)
					ComputerPanel.this.computer.setType(ComputerPanel.this.cbType.getSelectedIndex());
			}
		});
		add(this.cbType, gbc_cbType);
		
		//TODO label for memory
		JLabel lbMemory = new JLabel("Memory (Gb)"); //$NON-NLS-1$
		GridBagConstraints gbc_lbMemory = new GridBagConstraints();
		gbc_lbMemory.anchor = GridBagConstraints.EAST;
		gbc_lbMemory.insets = new Insets(0, 0, 5, 5);
		gbc_lbMemory.gridx = 0;						// column index
		gbc_lbMemory.gridy = 2;						// row index
		add(lbMemory, gbc_lbMemory);
		
		//TODO memory spinner 
		this.spMemory = new JSpinner();
		// model for spinner first 0 is the current value, second 0 is the minimum value, 128 is the maximum value, 1 is the step
		this.spMemory.setModel(new SpinnerNumberModel(0, 0, 128, 1));		
		GridBagConstraints gbc_spMemory = new GridBagConstraints();
		gbc_spMemory.anchor = GridBagConstraints.WEST;
		gbc_spMemory.fill = GridBagConstraints.HORIZONTAL;
		gbc_spMemory.insets = new Insets(0, 0, 5, 5);
		gbc_spMemory.gridx = 2;						// column index
		gbc_spMemory.gridy = 2;						// row index
		this.spMemory.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (ComputerPanel.this.computer!=null)
					ComputerPanel.this.computer.setMemory(((Number)ComputerPanel.this.spMemory.getValue()).intValue());
			}
		});
		add(this.spMemory, gbc_spMemory);
		
		//TODO disk capacity label
		JLabel lblNewdiskCapacityLabel = new JLabel("NewDisk capacity label"); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewdiskCapacityLabel = new GridBagConstraints();
		gbc_lblNewdiskCapacityLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewdiskCapacityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewdiskCapacityLabel.gridx = 0;		//column index
		gbc_lblNewdiskCapacityLabel.gridy = 3;		//row index
		add(lblNewdiskCapacityLabel, gbc_lblNewdiskCapacityLabel);
		
		//TODO disk capacity spinner
		this.spDisk = new JSpinner();
		// model for spinner first 0 is the current value, second 0 is the minimum value, 5000 is the maximum value, 1 is the step
		this.spDisk.setModel(new SpinnerNumberModel(0, 0, 5000, 1)); 
		GridBagConstraints gbc_spDisk = new GridBagConstraints();
		gbc_spDisk.fill = GridBagConstraints.HORIZONTAL;
		gbc_spDisk.anchor = GridBagConstraints.WEST;
		gbc_spDisk.insets = new Insets(0, 0, 5, 5);
		gbc_spDisk.gridx = 2;						//column index
		gbc_spDisk.gridy = 3;						//row index
		this.spDisk.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (ComputerPanel.this.computer!=null)
					ComputerPanel.this.computer.setDiskCapacity(((Number)ComputerPanel.this.spDisk.getValue()).intValue());
			}
		});
		add(this.spDisk, gbc_spDisk);
		
		//TODO Display type label
		JLabel lblDisplay = new JLabel("Display"); //$NON-NLS-1$
		GridBagConstraints gbc_lblDisplay = new GridBagConstraints();
		gbc_lblDisplay.anchor = GridBagConstraints.EAST;
		gbc_lblDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_lblDisplay.gridx = 0;					//column index
		gbc_lblDisplay.gridy = 4;					//row index
		add(lblDisplay, gbc_lblDisplay);
		
		//TODO display type combobox
		this.cbDisplay = new JComboBox<>(Computer.DISP_VALUES);
		GridBagConstraints gbc_cbDisplay = new GridBagConstraints();
		gbc_cbDisplay.insets = new Insets(0, 0, 0, 5);
		gbc_cbDisplay.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDisplay.gridx = 2;					//column index
		gbc_cbDisplay.gridy = 4;					//row index
		this.cbDisplay.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ComputerPanel.this.computer!=null)
					ComputerPanel.this.computer.setDisplayDensity(ComputerPanel.this.cbDisplay.getSelectedIndex());
			}
		});
		add(this.cbDisplay, gbc_cbDisplay);
	}
	
	/**
	 * Set the current computer data
	 * 
	 * @param computer the current data
	 */
	public void setData(Computer computer) {
		this.computer = computer;
		if (this.computer!=null) {
			this.tfName.setText(computer.getName());
			this.cbType.setSelectedIndex(computer.getType());
			this.spMemory.setValue(Integer.valueOf(computer.getMemory()));
			this.spDisk.setValue(Integer.valueOf(computer.getDiskCapacity()));
			this.cbDisplay.setSelectedIndex(computer.getDisplayDensity());
			return;
		}
		this.tfName.setText(null); 
		this.cbType.setSelectedIndex(Computer.TYPE_UNKNOWN);
		this.spMemory.setValue(Integer.valueOf(0));
		this.spDisk.setValue(Integer.valueOf(0));
		this.cbDisplay.setSelectedIndex(Computer.DISP_UNKNOWN);
	}	
	/**
	 * returns the current data object
	 * 
	 * @return the current data
	 */
	
	public Computer getData() {
		return this.computer;
	}
}
