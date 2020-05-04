package org.openmrs.module.openmrslearningrest.resources;

import org.openmrs.module.openmrslearningrest.Equipments;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.RefRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

public class EquipmentsResource extends MetadataDelegatingCrudResource<Equipments> {
	
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
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("display");
			description.addProperty("name");
			description.addProperty("id");
			description.addProperty("computers");
			description.addProperty("freezers");
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof FullRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
			description.addProperty("display");
			description.addProperty("name");
			description.addProperty("name");
			description.addProperty("id");
			description.addProperty("computers");
			description.addProperty("freezers");
			description.addProperty("license");
			description.addSelfLink();
			description.addLink("full", ".?v=" + RestConstants.REPRESENTATION_FULL);
			return description;
		} else if (rep instanceof RefRepresentation) {
			final DelegatingResourceDescription description = new DelegatingResourceDescription();
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
		description.addRequiredProperty("computers");
		description.addRequiredProperty("freezers");
		
		return description;
		
	}
	
	@Override
	public Equipments newDelegate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Equipments save(Equipments delegate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Equipments getByUniqueId(String uniqueId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void purge(Equipments delegate, RequestContext context) throws ResponseException {
		// TODO Auto-generated method stub
		
	}
}
