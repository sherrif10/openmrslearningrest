package org.openmrs.module.openmrslearningrest.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.openmrslearningrest.Laboratory;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for managing laboratory.
 */
@Transactional
public interface LaboratoryService extends OpenmrsService {
	
	/**
	 * @return Gets all list of laboratories
	 */
	@Transactional(readOnly = true)
	public List<Laboratory> getAllLaboratories();
	
	public Laboratory getLaboratoryByUuid(String uuid);
	
	public void addLaboratory(String name, String equipments);
	
	@Transactional(readOnly = true)
	public Laboratory updateLaboratoyName(Laboratory laboratory, Integer id, String name, String Equipment);
	
	@Transactional(readOnly = true)
	public Laboratory getLaboratory(Laboratory laboratory, String searchPhrase);
	
	@Transactional(readOnly = true)
	public Laboratory createLaboratory(Laboratory laboratory, String name);
	
	@Transactional(readOnly = true)
	public Laboratory deleteLaboratory(Laboratory laboratory);
	
	public void saveLaboratory(Laboratory laboratory);
	
	//	public L deleteLaboratory(Laboratory laboratory);
	
	//	public Laboratory getLaboratoryByUuid(String uuid);
	
}
