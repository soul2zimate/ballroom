package org.jboss.ballroom.client.widgets.tables.pager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.SimplePager;

/**
 * @author Heiko Braun
 * @date 1/30/12
 */
public interface PagerResources extends SimplePager.Resources {

    /**
     * The image used to skip ahead multiple pages.
     */
    @Source("rr.png")
    ImageResource simplePagerFastForward();

    /**
     * The disabled "fast forward" image.
     */
    @Source("rr.png")
    ImageResource simplePagerFastForwardDisabled();

    /**
     * The image used to go to the first page.
     */
    @Source("ll.png")
    ImageResource simplePagerFirstPage();

    /**
     * The disabled first page image.
     */
    @Source("ll.png")
    ImageResource simplePagerFirstPageDisabled();

    /**
     * The image used to go to the last page.
     */
    @Source("rr.png")
    ImageResource simplePagerLastPage();

    /**
     * The disabled last page image.
     */
    @Source("rr.png")
    ImageResource simplePagerLastPageDisabled();

    /**
     * The image used to go to the next page.
     */
    @Source("r.png")
    ImageResource simplePagerNextPage();

    /**
     * The disabled next page image.
     */
    @Source("r.png")
    ImageResource simplePagerNextPageDisabled();

    /**
     * The image used to go to the previous page.
     */
    @Source("l.png")
    ImageResource simplePagerPreviousPage();

    /**
     * The disabled previous page image.
     */
     @Source("l.png")
    ImageResource simplePagerPreviousPageDisabled();

    /**
     * The styles used in this widget.
     */
    @Source("DefaultPager.css")
    SimplePager.Style simplePagerStyle();
}