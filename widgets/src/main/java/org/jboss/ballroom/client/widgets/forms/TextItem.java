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

package org.jboss.ballroom.client.widgets.forms;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Heiko Braun
 * @date 3/1/11
 */
public class TextItem extends FormItem<String> {

    private HTML html;

    public TextItem(String name, String title) {
        super(name, title);
        this.html = new HTML();
        isModified = false; // will be ignored
    }

    @Override
    public String getValue() {
        return html.getText();
    }

    @Override
    public void resetMetaData() {
        isUndefined = true;
    }

    @Override
    public void setValue(String value) {
        html.setText(value);
    }

    @Override
    public Widget asWidget() {
        return html;
    }

    @Override
    public void setEnabled(boolean b) {
        // it's not editable anyway
    }

    @Override
    public boolean validate(String value) {
        return true;
    }

    @Override
    public void clearValue() {
        this.html.setText("");
    }
}