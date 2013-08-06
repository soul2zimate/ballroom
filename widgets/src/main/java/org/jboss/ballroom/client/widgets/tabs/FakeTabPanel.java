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

package org.jboss.ballroom.client.widgets.tabs;

import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * A title bar to be displayed at the top of a content view -
 * contains a label and/or an icon.
 *
 * @author Heiko Braun
 */
public class FakeTabPanel extends LayoutPanel {


    public FakeTabPanel(String title) {
        super();

        setStyleName("title-bar-panel");

        TabHeader widget = new TabHeader(title);
        add(widget);

        setWidgetBottomHeight(widget, 0, Style.Unit.PX, 40, Style.Unit.PX);
        setWidgetLeftWidth(widget, 0, Style.Unit.PX, 100, Style.Unit.PCT);
    }

    public void setIcon(ImageResource icon) {

    }

}
