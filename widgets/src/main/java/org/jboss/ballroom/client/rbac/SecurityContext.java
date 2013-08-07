package org.jboss.ballroom.client.rbac;

import com.google.gwt.safehtml.shared.SafeHtml;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The security context has access to the authorisation meta data and provides policies to reason over it.
 * Each security context is associated with a specific {@link com.gwtplatform.mvp.client.proxy.PlaceRequest}.
 *
 * @see SecurityService
 * @see com.gwtplatform.mvp.client.proxy.PlaceManager
 *
 * @author Heiko Braun
 * @date 7/3/13
 */
public class SecurityContext {


    /**
     * Influences the decision tree when reasoning over access control meta data
     */
    Facet facet;

    /**
     * the place name token (url)
     */
    String nameToken;

    /**
     * Set of required resources.
     * Taken from access control meta data
     */
    Set<String> requiredResources;

    /**
     * A list of access constraint definitions
     * (result of :read-resource-description(access-control=true))
     */
    Map<String, Constraints> accessConstraints = new HashMap<String, Constraints>();

    /**
     * A sealed context cannot be modified
     */
    private boolean sealed;


    public SecurityContext(String nameToken, Set<String> requiredResources) {
        this.nameToken = nameToken;
        this.requiredResources = requiredResources;
    }

    public Facet getFacet() {
        return facet;
    }

    public void setFacet(Facet facet) {
        this.facet = facet;
    }

    public SafeHtml asHtml() {
        return RBACUtil.dump(this);
    }

    public interface Priviledge {
        boolean isGranted(Constraints c);
    }

    private AuthorisationDecision checkPriviledge(Priviledge p) {
        AuthorisationDecision decision = new AuthorisationDecision(true);
        for(String address : requiredResources)
        {
            final Constraints model = accessConstraints.get(address);
            if(model!=null && !p.isGranted(model))
            {
                decision.setGranted(false);
                decision.getErrorMessages().add(address);
                break;
            }
        }

        return decision;
    }

    /**
     * If any of the required resources is not accessible, overall access will be rejected
     * @return
     */
    public AuthorisationDecision getReadPriviledge() {

        assert sealed : "Should be sealed before policy decisions are evaluated";

        AuthorisationDecision decision = new AuthorisationDecision(true);

        for(String address : requiredResources)
        {
            final Constraints model = accessConstraints.get(address);
            boolean readable = facet.equals(Facet.CONFIGURATION) ? model.isReadConfig() : model.isReadRuntime();
            if(model!=null && !readable)
            {
                decision.getErrorMessages().add(address);
            }
        }

        if(decision.hasErrorMessages())
        {
            decision.setGranted(false);
        }

        return decision;
    }

    public AuthorisationDecision getWritePriviledge() {
        return checkPriviledge(new Priviledge() {
            @Override
            public boolean isGranted(Constraints c) {
                boolean writable = facet.equals(Facet.CONFIGURATION) ? c.isWriteConfig() : c.isWriteRuntime();
                return writable;
            }
        });
    }

    public AuthorisationDecision getAttributeWritePriviledge(final String name) {
        return checkPriviledge(new Priviledge() {
            @Override
            public boolean isGranted(Constraints c) {
                return c.isAttributeWrite(name);
            }
        });
    }

    public void updateResourceConstraints(String resourceAddress, Constraints model) {

        assert !sealed : "Sealed security context cannot be modified";

        accessConstraints.put(resourceAddress, model);
    }

    public void seal() {
        this.sealed = true;

        // TODO: move all policies that can be evaluated once into this method
    }



}
