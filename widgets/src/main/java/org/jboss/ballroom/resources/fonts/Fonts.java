package org.jboss.ballroom.resources.fonts;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import org.helios.gwt.fonts.client.FontName;
import org.helios.gwt.fonts.client.FontResource;

/**
 * @author Heiko Braun
 * @date 3/7/12
 */
public interface Fonts extends ClientBundle {

    public static final Fonts INSTANCE = GWT.create(Fonts.class);

    @FontName("FontAwesome")
    @Source({"fontawesome-webfont.ttf", "fontawesome-webfont.eot"})
    FontResource fontAwesome();

    @CssResource.NotStrict
    @Source("font-awesome.min.css")
    CssResource fontAwesomeCss();
}
