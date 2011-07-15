package org.jboss.as.console.client;

import com.google.gwt.autobean.shared.AutoBeanFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import org.jboss.as.console.client.model.BeanFactory;
import org.jboss.as.console.client.spi.Framework;

/**
 * @author Heiko Braun
 * @date 7/12/11
 */
public class ShowcaseFramework implements Framework {

    private final static BeanFactory factory = GWT.create(BeanFactory.class);

    @Override
    public EventBus getEventBus() {
        return Showcase.MODULES.getEventBus();
    }

    @Override
    public PlaceManager getPlaceManager() {
        return Showcase.MODULES.getPlaceManager();
    }

    @Override
    public AutoBeanFactory getBeanFactory() {
        return factory;
    }
}