/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
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

package org.jboss.ballroom.client.widgets.window;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.jboss.ballroom.client.widgets.common.DefaultButton;
import org.jboss.ballroom.resources.UIConstants;

/**
 * @author Heiko Braun
 * @date 4/15/11
 */
public class DialogueOptions extends HorizontalPanel {

    private DefaultButton submit;
    private DefaultButton cancel;

    public DialogueOptions(ClickHandler submitHandler, ClickHandler cancelHandler) {
        this(UIConstants.INSTANCE.common_label_save(), submitHandler, UIConstants.INSTANCE.common_label_cancel(), cancelHandler);
    }

    public DialogueOptions(
            String submitText, ClickHandler submitHandler,
            String cancelText, ClickHandler cancelHandler) {

        getElement().setAttribute("style", "margin-top:10px;width:100%");

        submit = new DefaultButton(submitText);
        submit.getElement().setAttribute("style", "min-width:60px;");
        submit.addStyleName("primary");
        submit.addClickHandler(submitHandler);


        cancel = new DefaultButton(cancelText);
        cancel.getElement().setAttribute("style", "min-width:60px;");
        cancel.addClickHandler(cancelHandler);

        getElement().setAttribute("style", "margin-top:8px; width:100%");

        HTML spacer = new HTML("&nbsp;");
        add(spacer);

        add(cancel);
        add(spacer);
        add(submit);

        cancel.getElement().getParentElement().setAttribute("align", "right");
        cancel.getElement().getParentElement().setAttribute("width", "100%");
    }

    public DialogueOptions showCancel(boolean b)
    {
        cancel.setVisible(b);
        return this;
    }

    public Element getSubmit() {
        return submit.getElement();
    }

    public Element getCancel() {
        return cancel.getElement();
    }
}
