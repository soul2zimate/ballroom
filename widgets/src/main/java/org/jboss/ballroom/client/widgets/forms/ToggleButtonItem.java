/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2016, Red Hat, Inc., and individual contributors
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

package org.jboss.ballroom.client.widgets.forms;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author wangc
 *
 */
public class ToggleButtonItem extends FormItem<Boolean> {
    private ToggleButton button;

    public ToggleButtonItem(String name, String title, String upText, String downText) {
        super(name, title);
        this.button = new ToggleButton(upText, downText);
        setModified(false);
        setUndefined(false);
    }

    @Override
    public Boolean getValue() {
        return button.getValue();
    }

    @Override
    public void setValue(Boolean value) {
        button.setValue(value);

    }

    @Override
    public Widget asWidget() {
        return button;
    }

    @Override
    public void setEnabled(boolean b) {
        button.setEnabled(b);

    }

    @Override
    public boolean validate(Boolean value) {
        return true;
    }

    @Override
    public void clearValue() {
    }

    public void addClickHandler(ClickHandler handler) {
        this.button.addClickHandler(handler);
    }
}
