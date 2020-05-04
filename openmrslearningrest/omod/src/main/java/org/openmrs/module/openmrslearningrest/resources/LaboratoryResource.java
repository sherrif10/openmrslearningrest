package org.openmrs.module.openmrslearningrest.resources;

import java.util.Arrays;
import java.util.List;

import org.openmrs.api.context.Context;
import org.openmrs.module.openmrslearningrest.Laboratory;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.PropertyGetter;
import org.openmrs.module.webservices.rest.web.annotation.PropertySetter;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.PageableResult;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.EmptySearchResult;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResourceDoesNotSupportOperationException;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;

@Resource(name = RestConstants.VERSION_1 + "/laboratory", supportedClass = Laboratory.class, supportedOpenmrsVersions = {
        "2.0.*", "2.1.*", "2.2.*", "2.3.*", "2.4.*" })
public class LaboratoryResource extends DataDelegatingCrudResource<Laboratory> {
	
	private static final Class<? extends Laboratory> LaboratoryService = null;
	
	@Autowired
	org.openmrs.module.openmrslearningrest.api.LaboratoryService laboratoryService;
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.resource.impl.BaseDelegatingResource#getResourceVersion()
	 */
	public String getResourceVersion() {
		return RestConstants.PROPERTY_FOR_RESOURCE_VERSION;
	}
	
	/**
	 * @see org.openmrs.rest.web.openmrslearningrest.#delegatingCrudResource#
	 *      getRepresentationDescription
	 * @author SHARIF
	 * @should return default representation given instance of defaultrepresentation
	 * @should return full representation given instance of full representation
	 * @should return null representation other than default or full
	 */
	
	public DelegatingResourceDescription getRepresentationDescription(Representation rep) {
		if (rep instanceof DefaultRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("display");
			description.addProperty("voided");
			description.addProperty("aeTitle");
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("Equipments", Representation.REF);
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
			
		} else if (rep instanceof FullRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("display");
			description.addProperty("voided");
			description.addProperty("uuid");
			description.addProperty("name");
			description.addProperty("aeTitle");
			description.addProperty("id");
			description.addProperty("license");
			description.addProperty("auditInfo");
			description.addProperty("Equipments", Representation.FULL);
			description.addSelfLink();
			return description;
			
		} else if (rep instanceof RefRepresentation) {
			DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("uuid");
			description.addProperty("display");
			description.addSelfLink();
			return description;
			
		}
		return null;
	}
	
	@Override
	public DelegatingResourceDescription getCreatableProperties() {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addRequiredProperty("name");
		description.addRequiredProperty("id");
		
		return description;
		
	}
	
	public Model getGetModel(Representation rep) {
		ModelImpl model = (ModelImpl) super.getGETModel(rep);
		
		if (rep instanceof DefaultRepresentation || rep instanceof FullRepresentation) {
			model.property("uuid", new StringProperty()).property("display", new StringProperty())
			        .property("name", new StringProperty()).property("voided", new BooleanProperty())
			        .property("Equipments", new StringProperty())
			        .property("Equipments", new RefProperty("#/definitions/EquipmentGetRef"));
			
		}
		if (rep instanceof DefaultRepresentation) {
			model.property("uuid", new StringProperty()).property("display", new StringProperty())
			        .property("name", new StringProperty()).property("voided", new BooleanProperty())
			        .property("parentLaboratory", new RefProperty("#/definitions/parentLaboratoryGetRef"))
			        .property("equipments", new StringProperty())
			        .property("Equipments", new RefProperty("#/definitions/EquipmentGetRef"));
			
		} else if (rep instanceof FullRepresentation) {
			model
			
			.property("uuid", new StringProperty()).property("display", new StringProperty())
			        .property("voided", new BooleanProperty()).property("auditInfo", new StringProperty())
			        .property("id", new IntegerProperty()).property("name", new StringProperty())
			        .property("Laboratory", new RefProperty("#/definitions/LaboratoryGetRef"))
			        .property("equipments", new StringProperty())
			        .property("Equipments", new RefProperty("#/definitions/EquipmentGet"));
			
		} else if (rep instanceof RefRepresentation) {
			model.property("display", new StringProperty()).property("uuid", new StringProperty());
			
		}
		return model;
		
	}
	
	public Model getCREATEModel(Representation representation) {
		ModelImpl model = new ModelImpl().property("equipment", new StringProperty()).property("name", new StringProperty())
		        .property("id", new StringProperty())
		        .property("Equipements", new RefProperty("#/definitions/LaboratoryEquipmentsCreate"));
		return model;
	}
	
	public Model getUPDATEModel(Representation representation) {
		return new ModelImpl().property("Name", new StringProperty());
	}
	
	@PropertyGetter("display")
	public String getDisplayString(Laboratory laboratory) {
		return laboratory.getName();
	}
	
	@PropertyGetter("auditInfo")
	public String getAudit(Laboratory laboratory) {
		return laboratory.getName() + "Auditor";
	}
	
	@PropertyGetter("name")
	public String getName(Laboratory laboratory) {
		return laboratory.getName() + "name";
	}
	
	@PropertyGetter("auditInfo")
	public String id(Laboratory laboratory) {
		return laboratory.getUuid() + "Uuid";
	}
	
	@PropertySetter("Equipments")
	public static void setMappings(Laboratory instance, List<Laboratory> laboratory) {
		for (Laboratory lab : laboratory) {
			lab.setId(instance.getId());
		}
	}
	
	/**
	 * @see org.openmrs.openmrslearningrest.rest.web.baseDelegatingresource#delete(java.lang.object)
	 *      , org.openmrs.web.rest.RequestContext
	 * @should return resourceDoesNotSupportOperationException
	 */
	@Override
	protected void delete(Laboratory laboratory, String reason, RequestContext context)
	        throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
		
	}
	
	/**
	 * @see org.openmrs.openmrslearningrest.rest.web.baseDelegatingresource#purge(java.lang.object),
	 *      org.openmrs.web.rest.RequestContext
	 * @should return resourceDoesNotSupportOperationException
	 */
	
	protected void purge(Laboratory laboratory, String reason, RequestContext requestcontext)
	        throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	/**
	 * @see org.openmrs.openmrslearningrest.rest.BaseDelegatingresource#newDelegate
	 */
	@Override
	public Laboratory newDelegate() throws ResourceDoesNotSupportOperationException {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	/**
	 * @see org.openmrs.openmrslearningrest.BaseDelegatingresource3getproperties toexpose
	 *      subresource
	 */
	@Override
	public List<String> getPropertiesToExposeAsSubResources() {
		return Arrays.asList("terms");
	}
	
	@Override
	public Laboratory save(Laboratory laboratory) {
		throw new ResourceDoesNotSupportOperationException();
	}
	
	@Override
	public Laboratory getByUniqueId(String uuid) {
		return Context.getService(org.openmrs.module.openmrslearningrest.api.LaboratoryService.class).getLaboratoryByUuid(
		    uuid);
		
	}
	
	@Override
	protected PageableResult doSearch(RequestContext context) {
		List<Laboratory> laboratories = Context.getService(LaboratoryService).getLaboratories(context.getParameter("q"));
		//				.getCohorts(context.getParameter("q"));
		return new NeedsPaging<Laboratory>(laboratories, context);
		//		String LaboratoryUuid = context.getRequest().getParameter("LaboratoryUuid");
		//		if(LaboratoryUuid!= null) {
		//			  Laboratory laboratory = Context.getService(Laboratory.class).getResourceSupportedByClass(Laboratory.class).getByUniqueId(LaboratoryUuid);
		//			      if(laboratory==null) {
		//			    	  return new EmptySearchResult();
		//			    	  return new NeedsPaging<Laboratory>(Context.getService(LaboratoryService.getClass(),context);
		////			    	  return new NeedsPaging<Laboratory>(Context.getPatientService().getAllergies(patient), context);
		//			      }
		
	}
	
	@Override
	public void purge(Laboratory delegate, RequestContext context) throws ResponseException {
		// TODO Auto-generated method stub
		
	}
	
}
