package org.jboss.ballroom.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

/**
 * @author Heiko Braun
 * @date 1/9/12
 */
public interface UIConstants extends Constants {

    public static final UIConstants INSTANCE = GWT.create(UIConstants.class);

    String common_label_edit();

    String common_label_save();

    String common_label_delete();

    String common_label_add();

    String common_label_remove();

    String common_label_cancel();

    String common_label_confirm();
}
