package org.jboss.ballroom.client;

import com.google.gwt.core.client.EntryPoint;
import org.jboss.ballroom.resources.fonts.Fonts;

/**
 * @author Heiko Braun
 * @date 7/12/11
 */
public class Bootstrap implements EntryPoint {

    public void onModuleLoad() {
        Fonts.INSTANCE.fontAwesome().ensureInjected();
        Fonts.INSTANCE.fontAwesomeCss().ensureInjected();
    }
}
