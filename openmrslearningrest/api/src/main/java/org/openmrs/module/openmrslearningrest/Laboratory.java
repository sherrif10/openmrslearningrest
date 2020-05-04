package org.openmrs.module.openmrslearningrest;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.module.openmrslearningrest.api.LaboratoryService;

@Entity(name = "laboratory")
@Table(name = "laboratory")
public class Laboratory extends BaseOpenmrsData {
	
	public static final long serialVersionUID = 285L;
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Lab_Id")
	public Integer Id;
	
	@Column(name = "Laboratory_name")
	public String name;
	
	@Column(name = "equipments")
	private Equipments equipments;
	
	public Equipments getEquipments() {
		return equipments;
	}
	
	public void setEquipments(Equipments equipments) {
		this.equipments = equipments;
	}
	
	public Laboratory(String name, int labId) {
		super();
		this.name = name;
		this.Id = Id;
	}
	
	public Laboratory() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getid() {
		return Id;
	}
	
	public void setLabId(int labId) {
		this.Id = Id;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setId(Integer arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Laboratory getResourceSupportedByClass(Class<Laboratory> classs) {
		return null;
	}
	
	public Laboratory getByUniqueId(String laboratoryUuid) {
		return getByUniqueId(laboratoryUuid);
	}
	
	public List<Laboratory> getLaboratories(String parameter) {
		return getLaboratories(null);
	}
	
}
