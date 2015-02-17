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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A button dropdown similar to http://getbootstrap.com/components/#btn-dropdowns-split.
 *
 * @author Harald Pehl
 */
public class ButtonDropdown extends Composite implements HasClickHandlers {

    public static final String STYLE_NAME = "ballroom-ButtonDropdown";
    public static final String CARET_STYLE_NAME = STYLE_NAME + "-Caret";
    public static final String POPUP_STYLE_NAME = STYLE_NAME + "-Popup";
    public static final String POPUP_ITEM_STYLE_NAME = STYLE_NAME + "-PopupItem";

    private static final Templates TEMPLATES = GWT.create(Templates.class);

    private final Button button;
    private final FlowPanel menu;
    private PopupPanel popup;

    public ButtonDropdown(final String text) {
        this(text, null);
    }

    public ButtonDropdown(final String text, ClickHandler handler) {
        button = new Button(text);
        menu = new FlowPanel();
        popup = new PopupPanel(true);
        popup.setStyleName(POPUP_STYLE_NAME);
        popup.add(menu);

        final FlowPanel root = new FlowPanel();
        final Button caret = new Button(TEMPLATES.caret());
        caret.addStyleName(CARET_STYLE_NAME);
        root.add(button);
        root.add(caret);
        caret.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                popup.showRelativeTo(button);
            }
        });

        initWidget(root);
        setStyleName(STYLE_NAME);

        if (handler != null) {
            addClickHandler(handler);
        }
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return button.addClickHandler(handler);
    }

    public void addItem(final String text, final Scheduler.ScheduledCommand command) {
        Label label = new Label(text);
        label.setStyleName(POPUP_ITEM_STYLE_NAME);
        menu.add(label);
        label.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Scheduler.get().scheduleDeferred(command);
                popup.hide();
            }
        });
    }

    interface Templates extends SafeHtmlTemplates {
        @Template("<i class=\"icon-caret-down\"></i> ")
        SafeHtml caret();
    }
}
