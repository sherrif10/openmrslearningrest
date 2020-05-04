package org.openmrs.module.openmrslearningrest.api.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.openmrslearningrest.Equipments;
import org.openmrs.module.openmrslearningrest.Laboratory;

public class EquipmentsDao {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public List<Equipments> getAllEquipments() {
		return sessionFactory.getCurrentSession().createCriteria(Equipments.class).list();
	}
	
	public Equipments getEquipments(Integer Lab_id) {
		return (Equipments) sessionFactory.getCurrentSession().get(Equipments.class, Lab_id);
	}
	
	public Equipments saveEquipments(Equipments equipment) {
		sessionFactory.getCurrentSession().save(equipment);
		return equipment;
	}
	
	public void purgeEquipments(List<Equipments> equipment) {
		sessionFactory.getCurrentSession().delete(equipment);
		
	}
	
}
