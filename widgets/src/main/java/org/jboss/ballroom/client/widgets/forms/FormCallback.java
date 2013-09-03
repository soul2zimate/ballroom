package org.jboss.ballroom.client.widgets.forms;

import java.util.Map;

/**
 * @author Heiko Braun
 * @date 9/3/13
 */
public interface FormCallback<T> {
    void onSave(Map<String, Object> changeset);
    void onCancel(T entity);
}
