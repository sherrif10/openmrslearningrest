package org.openmrs.module.openmrslearningrest.api.dao;

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.openmrslearningrest.Laboratory;

public class LaboratoryDao {
	
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
	
	public List<Laboratory> getAllLaboratories() {
		return sessionFactory.getCurrentSession().createCriteria(Laboratory.class).list();
	}
	
	public Laboratory getLaboratory(Integer Lab_id) {
		return (Laboratory) sessionFactory.getCurrentSession().get(Laboratory.class, Lab_id);
	}
	
	public Laboratory saveLaboratory(Laboratory laboratory) {
		sessionFactory.getCurrentSession().save(laboratory);
		return laboratory;
	}
	
	public void purgeLaboratory(List<Laboratory> laboratory) {
		sessionFactory.getCurrentSession().delete(laboratory);
		
	}
	
}
