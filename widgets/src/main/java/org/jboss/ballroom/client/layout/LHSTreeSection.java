package org.jboss.ballroom.client.layout;

import com.google.gwt.user.client.ui.TreeItem;

/**
 * A LHS navigation tree section (bold header look&feel).
 */
public class LHSTreeSection extends TreeItem {

    private final static String ID_PREFIX = "nav_section_";

    public LHSTreeSection(String title) {
        this(title, false);
    }

    public LHSTreeSection(String title, boolean first) {
        super();
        addStyleName("tree-section");
        setText(title);
        getElement().setId(ID_PREFIX + title.replace(' ', '_').toLowerCase());

        if (first) {
            addStyleName("tree-section-first");
            getElement().setAttribute("tabindex", "-1");
        }
    }


    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) { addStyleName("tree-section-selected"); } else { removeStyleName("tree-section-selected"); }
    }
}
