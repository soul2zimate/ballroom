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

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author Harald Pehl
 */
public class SecurityContextChangedEvent extends GwtEvent<SecurityContextChangedHandler> {

    public static final Type<SecurityContextChangedHandler> TYPE = new Type<SecurityContextChangedHandler>();

    public static void fire(HasHandlers source, String resourceAddress) {
        source.fireEvent(new SecurityContextChangedEvent(resourceAddress));
    }

    public static HandlerRegistration register(EventBus eventBus, SecurityContextChangedHandler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

    private final String resourceAddress;

    public SecurityContextChangedEvent(String resourceAddress) {
        this.resourceAddress = resourceAddress;
    }

    public String getResourceAddress() {
        return resourceAddress;
    }

    @Override
    public Type<SecurityContextChangedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SecurityContextChangedHandler handler) {
        handler.onSecurityContextChanged(this);
    }
}
