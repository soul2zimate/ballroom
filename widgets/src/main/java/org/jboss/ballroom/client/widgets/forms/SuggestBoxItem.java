package org.jboss.ballroom.client.widgets.forms;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Heiko Braun
 * @date 2/27/12
 */
public class SuggestBoxItem extends FormItem<String> {
    protected TextBox textBox;
    private InputElementWrapper wrapper;
    private SuggestBox suggestBox;
    private SuggestOracle oracle;
    private boolean willBeFiltered = false;

    public SuggestBoxItem(String name, String title) {
        super(name, title);

        textBox = new TextBox();
        textBox.setName(name);
        textBox.setTitle(title);
        textBox.setTabIndex(0);
        textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                setModified(true);
                setUndefined(event.getValue().equals(""));
            }
        });


        this.errMessage = "No whitespace, no special chars allowed";
    }

    public SuggestBoxItem(String name, String title, boolean isRequired) {
        super(name, title);

        setRequired(isRequired);

        textBox = new TextBox();
        textBox.setName(name);
        textBox.setTitle(title);
        textBox.setTabIndex(0);
        textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                setModified(true);
                setUndefined(event.getValue().equals(""));
            }
        });

        this.errMessage = "No whitespace, no special chars allowed";
    }

    @Override
    public void setFiltered(boolean filtered) {
        super.setFiltered(filtered);
        super.toggleAccessConstraint(textBox, filtered);
        textBox.setEnabled(!filtered);
        willBeFiltered = filtered;
    }

    @Override
    public Element getInputElement() {
        return textBox.getElement();
    }

    public void setOracle(SuggestOracle oracle) {
        this.oracle = oracle;
    }

    @Override
    public Widget asWidget() {

        if(null==oracle)
            throw new RuntimeException("oracle required!");

        this.suggestBox = new SuggestBox(oracle, textBox);

        suggestBox.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                setModified(true);
                setUndefined(event.getValue().equals(""));
            }
        });

        suggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            @Override
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> suggestionSelectionEvent) {
                setModified(true);
                setUndefined(suggestBox.getValue().equals(""));
            }
        });


        wrapper = new InputElementWrapper(suggestBox, this);

        wrapper.setConstraintsApply(willBeFiltered);

        return wrapper;
    }

    @Override
    public String getValue() {
        return textBox.getValue();
    }

    @Override
    public void resetMetaData() {
        super.resetMetaData();
        textBox.setValue(null);
    }

    @Override
    public void setExpressionValue(String expr) {
        this.expressionValue = expr;
        if(expressionValue!=null)
        {
            toggleExpressionInput(textBox, true);
            textBox.setValue(expressionValue);
        }
    }

    @Override
    public void setValue(String value) {
        toggleExpressionInput(textBox, false);
        textBox.setValue(value.trim());
    }

    @Override
    public void setEnabled(boolean b) {
        textBox.setEnabled(b);
    }

    @Override
    public void setErroneous(boolean b) {
        super.setErroneous(b);
        wrapper.setErroneous(b);
    }

    @Override
    public boolean validate(String value) {

        if(expressionValue!=null || isExpressionScheme(textBox.getValue()))
        {
            return true;
        }
        else if(isRequired() && value.equals(""))
        {
            return false;
        }
        else
        {
            String updated = value.replace(" ", ""); // contains white space?
            return updated.equals(value);
        }
    }

    @Override
    public void clearValue() {
        textBox.setText("");
    }

    @Override
    protected void toggleExpressionInput(Widget target, boolean flag) {
        wrapper.setExpression(flag);
    }
}
