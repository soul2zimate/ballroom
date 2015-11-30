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
import org.jboss.ballroom.client.util.StringTokenizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Heiko Braun
 * @date 5/12/11
 */
public class PropertyListItem extends FormItem<Map<String,String>> {

    private TextArea textArea;
    private Map<String,String> value = new LinkedHashMap<>();
    private boolean displayOnly;
    private InputElementWrapper wrapper;
    private String transientError=null;


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
        unsetErrorState();
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

        boolean validInput = false;
        if(transientError!=null)
        {
            setErroneous(true);
            setErrMessage(transientError);
            validInput=false;
        }
        else
        {
            validInput=true;
        }

        return validInput;

    }

    @Override
    public Map<String, String> getValue() {

        unsetErrorState();

        String[] lines = textArea.getText().split("\n");
        value.clear();

        StringTokenizer tok = new StringTokenizer(textArea.getText(), "\n");

        while(tok.hasMoreTokens()) {
            String line = tok.nextToken();
            if (!line.equals("")
                    && line.contains("=")) {
                int delim = line.indexOf("=");
                String key = line.substring(0, delim);
                String val = line.substring(delim + 1, line.length());
                value.put(key, val);
            } else {
                transientError = "Invalid syntax";
            }
        }

        return new LinkedHashMap<>(value);
    }

    private void unsetErrorState() {
        this.transientError = null;
        setErrMessage(null);
        this.setErroneous(false);
    }

    @Override
    public void setValue(Map<String,String> map) {

        unsetErrorState();
        this.value.clear();

        this.value.putAll(map);

        this.textArea.setText("");
        if (map.size() > 0) {
            this.textArea.setVisibleLines(map.size());
        }

        StringBuffer sb = new StringBuffer();
        for(String key : map.keySet())
        {
            sb.append(key +"="+map.get(key)+"\n");
        }

        textArea.setText(sb.toString());
        setUndefined(false);
    }

    @Override
    public void clearValue() {
        unsetErrorState();
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
