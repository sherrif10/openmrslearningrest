package org.openmrs.module.openmrslearningrest.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.openmrslearningrest.Equipments;
import org.openmrs.module.openmrslearningrest.Laboratory;
import org.openmrs.module.openmrslearningrest.api.LaboratoryService;
import org.openmrs.module.openmrslearningrest.api.dao.LaboratoryDao;

public abstract class LaboratoryServiceImpl extends BaseOpenmrsService implements LaboratoryService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private LaboratoryDao dao;
	
	public LaboratoryDao getDao() {
		return dao;
	}
	
	public void setDao(LaboratoryDao dao) {
		this.dao = dao;
	}
	
	List<Laboratory> laboratory = new ArrayList<Laboratory>();
	
	@Override
	public List<Laboratory> getAllLaboratories() {
		return dao.getAllLaboratories();
	}
	
	public Laboratory getLaboratory(Integer id) {
		return dao.getLaboratory(id);
	}
	
	public Laboratory createLaboratory(String name) {
		List<Laboratory> lab = new ArrayList<Laboratory>();
		Equipments equipments = new Equipments();
		equipments.getId();
		equipments.getFreezers();
		equipments.getComputers();
		for (Laboratory laboratory : getAllLaboratories()) {
			if (StringUtils.equals(lab.toString(), lab.toString())) {
				laboratory.getEquipments();
			}
			return null;
		}
		return null;
		
	}
	
	public void purgeLaboratory(String name, Integer id) {
		dao.purgeLaboratory(laboratory);
		
	}
	
}
