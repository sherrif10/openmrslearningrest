package org.openmrs.module.openmrslearningrest.web.controller;

import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ws/rest/vi/openmrslearningrest/LaboratoryResource")
public class OpenmrslearningrestResourceController extends MainResourceController {
	
	@Override
	public String getNamespace() {
		return "v1/openmrslearningrest";
	}
}
