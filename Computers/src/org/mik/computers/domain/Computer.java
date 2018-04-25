package org.mik.computers.domain;

import java.sql.ResultSet;

/**
 * Computer domain 
 * 
 * Inherits AbstractDomain (id field)
 * @author Joshua Wambua
 *
 */
public class Computer extends AbstractDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of each columns in database
	 *  
	 */
	public final static String COL_NAME="name"; //$NON-NLS-1$
	
	public final static String COL_TYPE="type"; //$NON-NLS-1$
	
	public final static String COL_MEMORY="memory"; //$NON-NLS-1$
	
	public final static String COL_DISK_CAPACITY="disk_capacity"; //$NON-NLS-1$
	
	public final static String COL_DISPLAY_DENSITY="display_density"; //$NON-NLS-1$

	/**
	 * Computer types 
	 */
	
	public final static int TYPE_WORKSTATION=0;
	public final static int TYPE_SERVER=1;
	public final static int TYPE_LAPTOP=2;
	public final static int TYPE_GAMER=3;
	public final static int TYPE_NOTEPAD=4;
	public final static int TYPE_UNKNOWN=5;
	
	/**
	 * Computer type names as string array
	 */
	public final static String TYPE_VALUES[]={"Workstation", "Server", "Laptop", "Gamer", "notepad", "Unknown" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	
	public final static int DISP_LDPI=0;
	public final static int DISP_MDPI=1;
	public final static int DISP_TVDPI=2;
	public final static int DISP_HDPI=3;
	public final static int DISP_XHDPI=4;
	public final static int DISP_XXHDPI=5;
	public final static int DISP_XXXHDPI=6;
	public final static int DISP_UNKNOWN=7;
	
	/**
	 * Display type names as string array
	 */
	public final static String DISP_VALUES[] = {"LDPI","MDPI","TVDPI","HDPI","XHDPI","XXHDPI","XXXHDPI","Unknown"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	
	/**
	 * Name of computer for example: My laptop, vili.pmmf.hu server etc.
	 */
	private String name;
	
	/**
	 * Type of computer 
	 */
	private int type;
	
	/**
	 * Memory of the computer
	 */
	private int memory;
	
	/**
	 * Disk capacity of the computer
	 */
	private int diskCapacity;
	
	/**
	 * Display density of the computer
	 */
	private int displayDensity;
		
	/**
	 * Default constructor (empty computer)
	 */
	public Computer() {
		this.type=TYPE_UNKNOWN;
		this.displayDensity=DISP_UNKNOWN;
	}
	
	/**
	 * Constructor from database resultset.
	 * 
	 * @param rs
	 */
	public Computer(ResultSet rs) {
		super(rs);
		try {
			this.name=rs.getString(rs.findColumn(COL_NAME));
			this.type = rs.getInt(rs.findColumn(COL_TYPE));
			if (this.type<TYPE_UNKNOWN||this.type>TYPE_NOTEPAD)
				this.type=TYPE_UNKNOWN;
			
			this.memory=rs.getInt(rs.findColumn(COL_MEMORY));
			this.diskCapacity = rs.getInt(rs.findColumn(COL_DISK_CAPACITY));
			this.displayDensity = rs.getInt(rs.findColumn(COL_DISPLAY_DENSITY));
			if (this.displayDensity<DISP_UNKNOWN || this.displayDensity>DISP_XXXHDPI)
				this.displayDensity=DISP_UNKNOWN;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Setter for name
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for type
	 * 
	 * @return type as integer
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Getter for type as string
	 * 
	 * @return the type as string
	 */
	public String getTypeAsString() {
		switch (this.type) {
		case TYPE_WORKSTATION:return TYPE_VALUES[0];
		case TYPE_SERVER : return TYPE_VALUES[1];
		case TYPE_LAPTOP : return TYPE_VALUES[2];
		case TYPE_GAMER : return TYPE_VALUES[3];
		case TYPE_NOTEPAD : return TYPE_VALUES[4];
		default : return TYPE_VALUES[5];
		}
	}
	
	/**
	 * Setter for type. 
	 * 
	 * if the new type is out from type interval, it sets to unknown
	 * 
	 * @param type the new type as integer
	 */
	public void setType(int type) {
		this.type = type<0 || type>TYPE_UNKNOWN ? TYPE_UNKNOWN : type;
	}

	/**
	 * Getter for memory in GB
	 * 
	 * @return memory as Gb
	 */
	public int getMemory() {
		return this.memory;
	}

	/**
	 * Setter for memory
	 * 
	 * @param memory the ne memory size
	 */
	public void setMemory(int memory) {
		this.memory = memory;
	}

	/**
	 * Getter for disk capacity in Gb
	 * 
	 * @return disk capacity in Gb
	 */
	public int getDiskCapacity() {
		return this.diskCapacity;
	}

	/**
	 * Setter for disk capacity
	 * 
	 * @param diskCapacity
	 */
	public void setDiskCapacity(int diskCapacity) {
		this.diskCapacity = diskCapacity;
	}

	/**
	 * Getter for display density as integer
	 * 
	 * @return display density
	 */
	public int getDisplayDensity() {
		return this.displayDensity;
	}

	/**
	 * Getter for display density as string
	 * 
	 * @return density as string
	 */
	public String getDisplayDensityAsString() {
		switch (this.displayDensity) {
		case DISP_LDPI : return DISP_VALUES[0];
		case DISP_MDPI : return DISP_VALUES[1];
		case DISP_HDPI : return DISP_VALUES[2];
		case DISP_XHDPI : return DISP_VALUES[3];
		case DISP_XXHDPI : return DISP_VALUES[4];
		case DISP_XXXHDPI : return DISP_VALUES[5];
		default:return DISP_VALUES[6];
		}
	}
	
	/**
	 * Setter for display density
	 * 
	 * If the new density is out of density interval, it sets to unknown
	 * 
	 * @param displayDensity new density
	 */
	public void setDisplayDensity(int displayDensity) {
		this.displayDensity = displayDensity<0 || displayDensity>DISP_UNKNOWN?DISP_UNKNOWN:displayDensity;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + this.diskCapacity;
		result = prime * result + this.displayDensity;
		result = prime * result + this.memory;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + this.type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (this.diskCapacity != other.diskCapacity)
			return false;
		if (this.displayDensity != other.displayDensity)
			return false;
		if (this.memory != other.memory)
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		if (this.type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuffer("Computer ") //$NON-NLS-1$
				.append("id:").append(getId()) //$NON-NLS-1$
				.append(", name:").append(getName()) //$NON-NLS-1$
				.append(", type:").append(getTypeAsString()) //$NON-NLS-1$
				.append(", memory:").append(getMemory()) //$NON-NLS-1$
				.append(", disk capacity:").append(getDiskCapacity()) //$NON-NLS-1$
				.append(", display:").append(getDisplayDensityAsString()) //$NON-NLS-1$
				.toString(); 
	}
	
}
