package org.jboss.ballroom.client.rbac;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heiko Braun
 * @date 7/9/13
 */
public class Constraints {


    private boolean readConfig,writeConfig,readRuntime,writeRuntime;

    private Map<String, Boolean> attributeReadWrite = new HashMap<String,Boolean>();

    private boolean address = true;

    public boolean isAddress() {
        return address;
    }

    public void setAddress(boolean access) {
        this.address = access;
    }

    public void setReadConfig(boolean readConfig) {
        this.readConfig = readConfig;
    }

    public void setWriteConfig(boolean writeConfig) {
        this.writeConfig = writeConfig;
    }

    public void setReadRuntime(boolean readRuntime) {
        this.readRuntime = readRuntime;
    }

    public void setWriteRuntime(boolean writeRuntime) {
        this.writeRuntime = writeRuntime;
    }

    public boolean isReadConfig() {
        return readConfig;
    }

    public boolean isWriteConfig() {
        return writeConfig;
    }

    public boolean isReadRuntime() {
        return readRuntime;
    }

    public boolean isWriteRuntime() {
        return writeRuntime;
    }

    public void setAttributeRead(String name, boolean canBeRead)
    {
        if(canBeRead)
            this.attributeReadWrite.put(name, canBeRead);
        else if(attributeReadWrite.containsKey(name))
            attributeReadWrite.remove(name);
    }

    public boolean isAttributeRead(String name) {
        return attributeReadWrite.containsKey(name);
    }

    public void setAttributeWrite(String name, boolean b)
    {
        this.attributeReadWrite.put(name, b);
    }

    public boolean isAttributeWrite(String name) {
        return attributeReadWrite.containsKey(name) ?
                attributeReadWrite.get(name) : true;
    }
}
