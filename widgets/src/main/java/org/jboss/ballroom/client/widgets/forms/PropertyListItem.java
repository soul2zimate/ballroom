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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Heiko Braun
 * @date 5/12/11
 */
public class PropertyListItem extends FormItem<Map<String,String>> {

    private TextArea textArea;
    private Map<String,String> value = new LinkedHashMap<>();
    private boolean displayOnly;
    private InputElementWrapper wrapper;


    public PropertyListItem(String name, String title) {
        this(name, title, false);
    }
    
    /**
     * Create a new PropertyListItem.
     * 
     * @param name The item name.
     * @param title The displayed title for the item.
     * @param displayOnly If true, never allow editing.
     */
    public PropertyListItem(String name, String title, boolean displayOnly) {
        super(name, title);
        this.textArea = new TextArea();
        this.textArea.setTabIndex(0);

        this.textArea.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                setModified(true);
                setUndefined(event.getValue().equals(""));
            }
        });
        this.displayOnly = displayOnly;
        wrapper = new InputElementWrapper(textArea, this);
        wrapper.setHelpText("One tuple (key=value) per line");
    }
    
    @Override
    public Widget asWidget() {
        return wrapper;
    }

    @Override
    public void setEnabled(boolean b) {
        this.textArea.setEnabled(b && !displayOnly);
    }

    @Override
    public boolean validate(Map<String,String> map) {
        return true;
    }

    @Override
    public Map<String, String> getValue() {

        String[] lines = textArea.getText().split("\n");
        value.clear();

        for(String line : lines) {
            if (!line.equals("")
                    && line.contains("="))
            {
                int delim = line.indexOf("=");
                String key = line.substring(0, delim);
                String val = line.substring(delim+1, line.length());
                value.put(key, val);
            }
        }

        return new LinkedHashMap<>(value);
    }

    @Override
    public void setValue(Map<String,String> map) {

        this.value.clear();

        this.value.putAll(map);

        this.textArea.setText("");
        if (map.size() > 0) {
            this.textArea.setVisibleLines(map.size());
        }

        for(String key : map.keySet())
        {
            textArea.setText(textArea.getText()+ key +"="+map.get(key)+"\n");
        }

        setUndefined(false);
    }

    @Override
    public void clearValue() {
        this.textArea.setText("");

    }

    @Override
    public void setErroneous(final boolean b)
    {
        super.setErroneous(b);
        wrapper.setErroneous(b);
    }

    @Override
    protected void toggleExpressionInput(final Widget target, final boolean flag)
    {
        wrapper.setExpression(flag);
    }
}
