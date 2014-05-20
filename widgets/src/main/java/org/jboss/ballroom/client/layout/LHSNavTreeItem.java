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

package org.jboss.ballroom.client.layout;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.TreeItem;
import org.jboss.ballroom.client.spi.Framework;
import org.jboss.ballroom.client.util.Places;
import org.uberfire.mvp.PlaceRequest;

/**
 * An LHS navigation item that carries a reference to view.
 * When it's clicked the {@link org.jboss.ballroom.client.spi.Framework#getPlaceManager()} is invoked to reveal the place.
 *
 * The state is managed by the {@link LHSNavTree} that own this item.
 *
 * @author Heiko Braun
 * @date 3/24/11
 */
public class LHSNavTreeItem extends TreeItem {

    private static final Framework framework = GWT.create(Framework.class);

    public LHSNavTreeItem(String text, String token) {
        setText(text);

        setStyleName("lhs-tree-item");
        getElement().setAttribute("token", token);
    }

    public void setKey(String key) {
        getElement().setAttribute("lhs-nav-key", key);
    }

    public String getKey() {
        String key = getElement().getAttribute("lhs-nav-key");
        if(null == key) key = ""; // graceful
        return key;
    }

    public void setActive(boolean active) {

        if(active)
        {
            addStyleName("lhs-tree-item-selected");
        }
        else
        {
            removeStyleName("lhs-tree-item-selected");
        }

        /*super.setSelected(active);*/
    }

    void reveal() {

        // reveal view
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

            public void execute() {
                String token = LHSNavTreeItem.this.getElement().getAttribute("token");
                List<PlaceRequest> placeRequests = Places.fromString(token);
                if (!placeRequests.isEmpty()) {
                    // TODO Handle multiple place requests!?
                    framework.getPlaceManager().goTo(placeRequests.get(0));
                }
            }
        });

    }
}
