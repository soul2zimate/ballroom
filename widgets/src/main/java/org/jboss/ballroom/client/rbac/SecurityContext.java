package org.jboss.ballroom.client.rbac;

/**
 * The security context has access to the authorisation meta data and provides policies to reason over it.
 * Each security context is associated with a specific {@link com.gwtplatform.mvp.shared.proxy.PlaceRequest}.
 *
 * @see SecurityService
 * @see com.gwtplatform.mvp.client.proxy.PlaceManager
 *
 * @author Heiko Braun
 * @date 7/3/13
 */
public interface SecurityContext {

    AuthorisationDecision getReadPriviledge();

    AuthorisationDecision getWritePriviledge();

    AuthorisationDecision getAttributeWritePriviledge(final String attributeName);

    AuthorisationDecision getAttributeReadPriviledge(final String attributeName);

    AuthorisationDecision getAttributeWritePriviledge(String resourceAddress, String attributeName);

    AuthorisationDecision getAttributeReadPriviledge(String resourceAddress, String attributeName);

    AuthorisationDecision getReadPrivilege(String resourceAddress);

    AuthorisationDecision getWritePrivilege(String resourceAddress);

    AuthorisationDecision getOperationPriviledge(String resourceAddress, String operationName);

    boolean hasChildContext(Object resourceAddress, String resolvedKey);

    void activateChildContext(Object resourceAddress, String resolvedKey);

    void seal();

}
