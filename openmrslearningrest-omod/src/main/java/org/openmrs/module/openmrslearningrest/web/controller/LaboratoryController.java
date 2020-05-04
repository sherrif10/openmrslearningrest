package org.openmrs.module.openmrslearningrest.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.module.openmrslearningrest.Laboratory;
import org.openmrs.module.openmrslearningrest.api.LaboratoryService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller behind the webservices module's "settings.jsp" page.
 */
@Controller("webservices.rest.LaboratoryFormController")
@RequestMapping("/module/openmrslearningrest/webservices/rest/laboratory.htm")
public class LaboratoryController {
	
	private final String VIEW = "/module/openmrslearningrest/webservices/rest/laboratory.htm";
	
	static final String LABORATORY_FORM_VIEW = "/module/openmrslearningrest/webservices/rest/laboratory.htm";
	
	@Autowired
	UserService userService;
	
	@Autowired
	private LaboratoryService laboratoryService;
	
	/**
	 * Handles requests for a new {@code laboratory}.
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView getLaboratory() {
		ModelAndView mv = new ModelAndView("LABORATORY_FORM_VIEW");
		mv.addObject(new Laboratory());
		return mv;
		
	}
	
	/**
	 * Handles requests for getting existing {@laboratory's}.
	 * 
	 * @param laboratory the existing laboratory which should be returned
	 * @should populate model and view with given laboratory
	 */
	@RequestMapping(method = RequestMethod.GET, params = "labId")
	protected ModelAndView getLaboratory(@RequestParam("labId") Laboratory laboratory) {
		
		final ModelAndView mv = new ModelAndView("LABORATORY_FORM_VIEW");
		mv.addObject(new Laboratory());
		return mv;
	}
	
	/**
	 * Handles requests for saving a new {@code laboratory}
	 * 
	 * @param request the http servlet request issued to save the laboratory
	 * @param laboratory the laboratory to be saved
	 * @should not redirect and set session attribute with openmrs error if api exception is thrown
	 *         by save laboratory module
	 */
	@RequestMapping(method = RequestMethod.POST, params = "saveLaboratory")
	protected ModelAndView saveLaboratory(HttpServletRequest request, @ModelAttribute Laboratory laboratory,
	        BindingResult resultLaboratory) {
		
		final ModelAndView modelAndView = new ModelAndView(LABORATORY_FORM_VIEW);
		
		if (resultLaboratory.hasErrors()) {
			modelAndView.addObject(laboratory);
			return modelAndView;
		}
		try {
			laboratoryService.saveLaboratory(laboratory);
			request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "laboratory.laboratory module .saved");
			modelAndView.setViewName("redirect:" + LABORATORY_FORM_VIEW + "?labId=" + laboratory.getName());
			return modelAndView;
		}
		
		catch (APIException apiException) {
			request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, apiException.getMessage());
		}
		
		modelAndView.addObject(laboratory);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "deleteLaboratory")
	protected ModelAndView deleteLaboratory(HttpServletRequest request, @ModelAttribute Laboratory laboratory,
	        BindingResult resultLaboratory) {
		
		final ModelAndView modelAndView = new ModelAndView(LABORATORY_FORM_VIEW);
		
		if (resultLaboratory.hasErrors()) {
			modelAndView.addObject(laboratory);
			return modelAndView;
		}
		
		try {
			laboratoryService.deleteLaboratory(laboratory);
			request.getSession().setAttribute(WebConstants.OPENMRS_MSG_ATTR, "laboratory.laboratory got deleted.deleted");
			modelAndView.setViewName("redirect:" + LABORATORY_FORM_VIEW + "?modalityId=" + laboratory.getName());
			return modelAndView;
		}
		catch (APIException apiException) {
			request.getSession().setAttribute(WebConstants.OPENMRS_ERROR_ATTR, apiException.getMessage());
		}
		
		modelAndView.addObject(laboratory);
		return modelAndView;
	}
	
	//	private final String VIEW = "/module/openmrslearningrest/laboratory.htm";
	//	/**
	//	 * Handles request for new laboratory
	//	 * @param httpSession
	//	 * @param anyRequestObject
	//	 * @param errors
	//	 * @return
	//	 */
	//	@RequestMapping(method = RequestMethod.POST)
	//	public String onPost(HttpSession httpSession, @ModelAttribute("anyRequestObject") Object anyRequestObject,
	//	        BindingResult errors) {
	//		
	//		if (errors.hasErrors()) {
	//			// return error view
	//		}
	//		
	//		return VIEW;
	//	}
	@ModelAttribute("laboratory")
	protected List<User> getUsers() throws Exception {
		List<User> users = userService.getAllUsers();
		return users;
	}
}
