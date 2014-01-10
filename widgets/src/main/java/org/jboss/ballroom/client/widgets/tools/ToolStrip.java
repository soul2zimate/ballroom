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

package org.jboss.ballroom.client.widgets.tools;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.ballroom.client.rbac.AuthorisationDecision;
import org.jboss.ballroom.client.rbac.SecurityContext;
import org.jboss.ballroom.client.rbac.SecurityService;
import org.jboss.ballroom.client.spi.Framework;

/**
 * @author Heiko Braun
 * @date 2/28/11
 */
public class ToolStrip extends HorizontalPanel{

    private HorizontalPanel left;
    private HorizontalPanel right;

    static Framework FRAMEWORK  = GWT.create(Framework.class);
    static SecurityService SECURITY_SERVICE = FRAMEWORK.getSecurityService();

    public ToolStrip() {
        super();
        setStyleName("default-toolstrip");
        getElement().setAttribute("role", "toolbar");

        left = new HorizontalPanel();
        right = new HorizontalPanel();

        add(left);
        add(right);

        left.getElement().getParentElement().setAttribute("width", "50%");
        right.getElement().getParentElement().setAttribute("width", "50%");
        right.getElement().getParentElement().setAttribute("align", "right");
    }

    public void addToolButton(ToolButton button)
    {
        left.add(button);
    }

    public void addToolButtonRight(ToolButton button)
    {
        button.getElement().setAttribute("style", "margin-right:5px;");
        button.addStyleName("toolstrip-button-secondary");
        right.add(button);

    }

    public boolean hasButtons() {
        return left.getWidgetCount()>0 || right.getWidgetCount()>0;
    }

    public void addToolWidget(Widget widget) {
        left.add(widget);
    }

    public void addToolWidgetRight(Widget widget) {

        right.add(widget);
    }

    @Override
    protected void onAttach() {

        super.onAttach();

        // access control
        SecurityContext securityContext = getSecurityContext();


        // RBAC: operation privileges
        int visibleItemsLeft = checkOperationPrivileges(left, securityContext);
        int visibleItemsRight = checkOperationPrivileges(right, securityContext);

        // nothing accessible within the toolbar, so we disable it completely
        if(visibleItemsLeft+visibleItemsRight==0)
        {
            setVisible(false);
            getElement().addClassName("rbac-suppressed");
        }

    }

    protected SecurityContext getSecurityContext() {return SECURITY_SERVICE.getSecurityContext();}

    private int checkOperationPrivileges(HorizontalPanel panel, SecurityContext securityContext) {
        boolean overallPrivilege = securityContext.getWritePriviledge().isGranted();
        int visibleItems = 0;
        int visibleButtons = 0;
        for (int i = 0; i < panel.getWidgetCount(); i++) {
            Widget widget = panel.getWidget(i);
            if (widget instanceof ToolButton) {
                ToolButton btn = (ToolButton) widget;
                boolean visible = true;
                if (btn.hasOperationAddress()) // fine grained, doesn't usually apply but can help to overcome dge cases
                {
                    String[] operationAddress = btn.getOperationAddress();
                    AuthorisationDecision operationPriviledge = securityContext
                            .getOperationPriviledge(operationAddress[0], operationAddress[1]);
                    visible = operationPriviledge.isGranted();
                } else {
                    visible = overallPrivilege; // coarse grained, inherited from parent
                }

                if (!visible) {
                    widget.setVisible(false);
                    widget.getElement().addClassName("rbac-suppressed");

                } else {
                    visibleButtons++;
                }
            } else {
                visibleItems++;
            }
        }

        // If there are no buttons, the visibility depends on the number of other widgets
        return visibleButtons == 0 ? visibleItems : visibleItems + visibleButtons;
    }
}
