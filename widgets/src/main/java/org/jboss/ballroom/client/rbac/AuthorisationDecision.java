package org.jboss.ballroom.client.rbac;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Heiko Braun
 * @date 7/25/13
 */
public class AuthorisationDecision {
    private boolean granted;
    private Set<String> errorMessages;

    public AuthorisationDecision(boolean defaultAccess) {
        this.granted = defaultAccess;
    }

    public boolean isGranted() {
        return granted;
    }

    public void setGranted(boolean granted) {
        this.granted = granted;
    }

    public Set<String> getErrorMessages() {
        if(null==errorMessages)
            this.errorMessages = new HashSet<String>();
        return errorMessages;
    }

    public boolean hasErrorMessages() {
        return this.errorMessages!=null && this.errorMessages.size()>0;
    }
}
