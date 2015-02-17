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
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasFocus;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * A button dropdown similar to http://getbootstrap.com/components/#btn-dropdowns-split.
 *
 * @author Harald Pehl
 */
public class ButtonDropdown extends Composite implements HasClickHandlers, HasDoubleClickHandlers, HasFocus, HasEnabled,
        HasAllDragAndDropHandlers, HasAllFocusHandlers, HasAllGestureHandlers,
        HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers {

    public static final String STYLE_NAME = "ballroom-ButtonDropdown";
    public static final String CARET_STYLE_NAME = STYLE_NAME + "-Caret";
    public static final String POPUP_STYLE_NAME = STYLE_NAME + "-Popup";
    public static final String POPUP_ITEM_STYLE_NAME = STYLE_NAME + "-PopupItem";

    private static final Templates TEMPLATES = GWT.create(Templates.class);

    private final Button button;
    private final FlowPanel menu;
    private PopupPanel popup;
    private Button caret;

    public ButtonDropdown(final String text) {
        this(text, null);
    }

    public ButtonDropdown(final String text, ClickHandler handler) {
        button = new Button(text);
        caret = new Button(TEMPLATES.caret());
        menu = new FlowPanel();
        popup = new PopupPanel(true);
        popup.setStyleName(POPUP_STYLE_NAME);
        popup.add(menu);

        final FlowPanel root = new FlowPanel();
        root.add(button);
        root.add(caret);
        caret.addStyleName(CARET_STYLE_NAME);
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


    // ------------------------------------------------------ button delegates

    public void click() {
        button.click();
    }

    public String getHTML() {
        return button.getHTML();
    }

    public String getText() {
        return button.getText();
    }

    public void setHTML(SafeHtml html) {
        button.setHTML(html);
    }

    public void setHTML(String html) {
        button.setHTML(html);
    }

    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public boolean isEnabled() {
        return button.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
        caret.setEnabled(enabled);
    }

    @Override
    public int getTabIndex() {
        return button.getTabIndex();
    }

    @Override
    public void setAccessKey(char key) {
        button.setAccessKey(key);
    }

    @Override
    public void setFocus(boolean focused) {
        button.setFocus(focused);
    }

    @Override
    public void setTabIndex(int index) {
        button.setTabIndex(index);
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return button.addBlurHandler(handler);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return button.addClickHandler(handler);
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return button.addDoubleClickHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
        return button.addDragEndHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
        return button.addDragEnterHandler(handler);
    }

    @Override
    public HandlerRegistration addDragHandler(DragHandler handler) {
        return button.addDragHandler(handler);
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
        return button.addDragLeaveHandler(handler);
    }

    @Override
    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
        return button.addDragOverHandler(handler);
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
        return button.addDragStartHandler(handler);
    }

    @Override
    public HandlerRegistration addDropHandler(DropHandler handler) {
        return button.addDropHandler(handler);
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return button.addFocusHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
        return button.addGestureChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
        return button.addGestureEndHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
        return button.addGestureStartHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return button.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return button.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return button.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return button.addMouseDownHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return button.addMouseMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return button.addMouseOutHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return button.addMouseOverHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return button.addMouseUpHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
        return button.addMouseWheelHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
        return button.addTouchCancelHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
        return button.addTouchEndHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
        return button.addTouchMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
        return button.addTouchStartHandler(handler);
    }

    @Override
    @Deprecated
    public void addFocusListener(FocusListener listener) {
        button.addFocusListener(listener);
    }

    @Override
    @Deprecated
    public void addKeyboardListener(KeyboardListener listener) {
        button.addKeyboardListener(listener);
    }

    @Override
    @Deprecated
    public void removeFocusListener(FocusListener listener) {
        button.removeFocusListener(listener);
    }

    @Override
    @Deprecated
    public void removeKeyboardListener(KeyboardListener listener) {
        button.removeKeyboardListener(listener);
    }


    // ------------------------------------------------------ safe html template

    interface Templates extends SafeHtmlTemplates {
        @Template("<i class=\"icon-caret-down\"></i> ")
        SafeHtml caret();
    }
}
