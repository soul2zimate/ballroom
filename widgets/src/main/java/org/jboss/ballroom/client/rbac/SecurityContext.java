package org.jboss.ballroom.client.rbac;

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
public interface SecurityContext {

    // v1
    public Facet getFacet();

    public AuthorisationDecision getReadPriviledge();

    public AuthorisationDecision getWritePriviledge();

    public AuthorisationDecision getAttributeWritePriviledge(final String name);

    public void seal();


    // v2

    public AuthorisationDecision getOperationPriviledge(String resourceAddress, String operationName);
}
