/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.jboss.ballroom.client.rbac;

import com.google.gwt.event.logical.shared.HasAttachHandlers;

/**
 * Intended for widgets that need to take a security context into account.
 *
 * @author Harald Pehl
 */
public interface SecurityContextAware extends HasAttachHandlers {

    /**
     * If set, acts as a filter when a {@link org.jboss.ballroom.client.rbac.SecurityContextChangedEvent} comes in.
     * The {@link #updateSecurityContext(SecurityContext)} method is only called if the filter and the resource
     * address in the event match.
     */
    void setFilter(String resourceAddress);

    String getFilter();

    void updateSecurityContext(SecurityContext securityContext);

    /**
     * The token for which the widget's context was registered  (upon creation time of the widget)
     * @return
     */
    String getToken();
}
