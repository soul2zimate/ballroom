package org.jboss.ballroom.client.rbac;

import java.util.List;
import java.util.Set;

/**
 * API for interaction units to leverage security facilities.
 *
 * @author Heiko Braun
 * @date 7/3/13
 */
public interface SecurityService {

    /**
     * Get the security context associated with the current dialog
     * @return the current security context
     */
    SecurityContext getSecurityContext();

    /**
     * Meta data utilities for working with AutoBean's
     *
     * @param type an autobean interface
     * @param securityContext
     * @return
     */
    Set<String> getReadOnlyJavaNames(Class<?> type, SecurityContext securityContext);

    /**
     * Meta data utilities for working with ModelNode's
     *
     * @param formItems
     * @param securityContext
     * @return
     */
    Set<String> getReadOnlyDMRNames(List<String> formItems, SecurityContext securityContext);
}
