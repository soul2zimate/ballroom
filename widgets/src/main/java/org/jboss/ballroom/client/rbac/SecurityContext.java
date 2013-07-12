package org.jboss.ballroom.client.rbac;

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
    private Facet facet;

    /**
     * the place name token (url)
     */
    private String nameToken;

    /**
     * Set of required resources.
     * Taken from access control meta data
     */
    private Set<String> requiredResources;

    /**
     * A list of access constraint definitions
     * (result of :read-resource-description(access-control=true))
     */
    private Map<String, Constraints> accessConstraints = new HashMap<String, Constraints>();

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

    public interface Priviledge {
        boolean isGranted(Constraints c);
    }

    private boolean checkPriviledge(Priviledge p) {
        boolean granted = true;
        for(String address : requiredResources)
        {
            final Constraints model = accessConstraints.get(address);
            if(model!=null && !p.isGranted(model))
            {
                granted = false;
                break;
            }
        }

        return granted;
    }

    /**
     * If any of the required resources is not accessible, overall access will be rejected
     * @return
     */
    public boolean doesGrantPlaceAccess() {

        assert sealed : "Should be sealed before policy decisions are evaluated";

        boolean accessGranted = true;
        for(String address : requiredResources)
        {
            final Constraints model = accessConstraints.get(address);
            if(model!=null && !model.isReadConfig())
            {
                accessGranted = false;
                break; // the first rule that fails rejects access
            }
        }

        return accessGranted;
    }

    public boolean isWriteConfig() {
        return checkPriviledge(new Priviledge() {
            @Override
            public boolean isGranted(Constraints c) {
                return c.isWriteConfig();
            }
        });
    }

    public boolean isReadRuntime() {
        return checkPriviledge(new Priviledge() {
            @Override
            public boolean isGranted(Constraints c) {
                return c.isReadRuntime();
            }
        });
    }

    public boolean isWriteRuntime() {
        return checkPriviledge(new Priviledge() {
            @Override
            public boolean isGranted(Constraints c) {
                return c.isWriteRuntime();
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
