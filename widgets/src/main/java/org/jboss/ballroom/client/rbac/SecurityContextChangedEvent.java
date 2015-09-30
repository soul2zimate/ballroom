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
import com.google.gwt.user.client.Command;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.Presenter;

/**
 * Instructs the security framework to recompute the context for a given key.
 * This results in a selection of a child context and force the {@link SecurityContextAware} widgets to evaluate the selected context.
 * A common scenario is the selection of a resource that belongs to a scoped role (i.e. server group, server or host).
 *
 * @author Harald Pehl
 * @author Heiko Braun
 */
public class SecurityContextChangedEvent extends GwtEvent<SecurityContextChangedHandler> {

    public static final Type<SecurityContextChangedHandler> TYPE = new Type<SecurityContextChangedHandler>();

    @FunctionalInterface
    public interface AddressResolver<T> {
        String resolve(T template);
    }

    /**
     * Change the security context and invoke the {@link SecurityContextChangedEvent#getPostContruct()} afterwards.
     *
     * @param source
     * @param postConstruct
     */
    public static void fire(Presenter source, Command postConstruct, AddressResolver resolver) {
        source.fireEvent(new SecurityContextChangedEvent(postConstruct, resolver));
    }

    public static void fire(Presenter source, AddressResolver resolver) {
        source.fireEvent(new SecurityContextChangedEvent(resolver));
    }

    /**
     * Reset previous child context selections. See usage of {@link SecurityContextChangedEvent#isReset()}
     * @param source
     */
    public static void fire(Presenter source) {
        source.fireEvent(new SecurityContextChangedEvent());
    }

    public static HandlerRegistration register(EventBus eventBus, SecurityContextChangedHandler handler) {
        return eventBus.addHandler(TYPE, handler);
    }

    private final Command postContruct;
    private final boolean isReset;
    private final AddressResolver resolver;

    public SecurityContextChangedEvent() {
        this.postContruct = null;
        this.isReset=true;
        this.resolver = null;
    }

    private SecurityContextChangedEvent(Command postContruct, AddressResolver resolver) {
        this.postContruct = postContruct;
        this.resolver = resolver;
        this.isReset = false;
    }

    private SecurityContextChangedEvent(AddressResolver resolver) {
        this.postContruct = null;
        this.resolver = resolver;
        this.isReset = false;
    }

    public AddressResolver getResolver() {
        return resolver;
    }

    public Command getPostContruct() {
        return postContruct;
    }

    public boolean isReset() {
        return isReset;
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
