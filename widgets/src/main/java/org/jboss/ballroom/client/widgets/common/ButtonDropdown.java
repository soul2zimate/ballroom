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
package org.jboss.ballroom.client.widgets.common;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A button dropdown similar to http://getbootstrap.com/components/#btn-dropdowns-split.
 * @author Harald Pehl
 */
public class ButtonDropdown extends Composite implements HasClickHandlers {

    public static final String STYLE_NAME = "ballroom-ButtonDropdown";

    private final FlowPanel root;
    private final Button button;
    private final Button caret;
    private final PopupPanel menu;

    public ButtonDropdown(final String text) {
        root = new FlowPanel();
        button = new Button(text);
        caret = new Button(">");
        menu = new PopupPanel(true);
        menu.add(new Label("Not yet implemented!"));

        caret.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                menu.showRelativeTo(caret);
            }
        });
        root.add(button);
        root.add(caret);

        setStyleName(STYLE_NAME);
        initWidget(root);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return button.addClickHandler(handler);
    }
}
