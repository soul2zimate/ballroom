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
     *
     * @return the current security context
     */
    SecurityContext getSecurityContext();

    void registerWidget(String id, SecurityContextAware widget);

    void unregisterWidget(String id);

    /**
     * Get the read only attributes a a bean type
     *
     * @param type            an autobean interface
     * @param securityContext
     *
     * @return
     */
    Set<String> getReadOnlyJavaNames(Class<?> type, SecurityContext securityContext);

    /**
     * Get the read only attributes for a bean type and target resource
     *
     * @param type
     * @param resourceAddress
     * @param securityContext
     *
     * @return
     */
    Set<String> getReadOnlyJavaNames(Class<?> type, String resourceAddress, SecurityContext securityContext);

    /**
     * Get the filtered attributes for a bean type and target resource
     *
     * @param type
     * @param resourceAddress
     * @param securityContext
     *
     * @return
     */
    Set<String> getFilteredJavaNames(Class<?> type, String resourceAddress, SecurityContext securityContext);

    /**
     * Get the filtered attributes for a bean type and target resource
     *
     * @param type
     * @param securityContext
     *
     * @return
     */
    Set<String> getFilteredJavaNames(Class<?> type, SecurityContext securityContext);


    /**
     * Get the read only attributes for resource
     * (Used with MBUI based forms)
     *
     * @param resourceAddress
     * @param formItemNames
     * @param securityContext @return
     */
    Set<String> getReadOnlyDMRNames(String resourceAddress, List<String> formItemNames,
                                    SecurityContext securityContext);

    /**
     * Get the filtered attributes for resource
     * (Used with MBUI based forms)
     *
     * @param resourceAddress
     * @param formItemNames
     * @param securityContext @return
     */
    Set<String> getFilteredDMRNames(String resourceAddress, List<String> formItemNames,
                                    SecurityContext securityContext);
}
