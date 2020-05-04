package org.openmrs.module.openmrslearningrest.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openmrslearningrest.Equipments;
import org.openmrs.module.openmrslearningrest.Laboratory;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for managing laboratory.
 */
@Transactional
public interface EquipmentsService extends OpenmrsService {
	
	/**
	 * @return Gets all list of laboratories
	 */
	@Transactional(readOnly = true)
	public List<Equipments> getAllEquipments();
	
	public Equipments getEquipmentByUuid(String uuid);
	
	public void addEquipment(String name, String equipments);
	
	@Transactional(readOnly = true)
	public Equipments updateEquipmentName(Equipments equipments, Integer id, String name, String Equipment);
	
	@Transactional(readOnly = true)
	public Equipments getEquipment(Equipments equipments, String searchPhrase);
	
	@Transactional(readOnly = true)
	public Equipments createEquipment(Equipments equipment, String name);
	
	@Transactional(readOnly = true)
	public Equipments deleteEquipment(Equipments equipment);
	
	public void saveEquipment(Equipments equipment);
	
	//	public L deleteLaboratory(Laboratory laboratory);
	
	//	public Laboratory getLaboratoryByUuid(String uuid);
	
}
