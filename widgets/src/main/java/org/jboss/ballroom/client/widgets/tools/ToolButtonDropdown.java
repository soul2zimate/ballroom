/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.ballroom.client.widgets.tools;

import com.google.gwt.event.dom.client.ClickHandler;
import org.jboss.ballroom.client.rbac.OperationAddressAware;
import org.jboss.ballroom.client.widgets.common.ButtonDropdown;

/**
 * @author Harald Pehl
 */
public class ToolButtonDropdown extends ButtonDropdown implements OperationAddressAware {

    private String resource;
    private String op;

    public ToolButtonDropdown(String text) {
        super(text);
    }

    public ToolButtonDropdown(String text, ClickHandler handler) {
        super(text, handler);
    }

    @Override
    public void setOperationAddress(String resource, String op) {
        this.resource = resource;
        this.op = op;
    }

    @Override
    public String[] getOperationAddress() {
        return new String[] {resource, op};
    }

    @Override
    public boolean hasOperationAddress() {
        return resource!=null && op!=null;
    }
}
