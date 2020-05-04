package org.openmrs.module.openmrslearningrest;

import java.io.Serializable;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.module.openmrslearningrest.api.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class Equipments extends BaseOpenmrsMetadata implements Serializable {
	
	@Autowired
	LaboratoryService laboratoryService;
	
	private static final long serialVersionUID = 1L;
	
	private static final int Equipments = 0;
	
	public String computers;
	
	public String freezers;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setId(Integer arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String getComputers() {
		return computers;
	}
	
	/**
	 * @param computers the computers to set
	 */
	
	public void setComputers(String computers) {
		this.computers = computers;
	}
	
	public String getFreezers() {
		return freezers;
	}
	
	/**
	 * @param freezer the freezers to set
	 */
	public void setFreezers(String freezers) {
		this.freezers = freezers;
	}
	
	public String getAllEquipments() {
		return null;
	}
	
}
