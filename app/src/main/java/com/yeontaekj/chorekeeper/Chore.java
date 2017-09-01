package com.yeontaekj.chorekeeper;

import java.util.UUID;

/**
 * Created by yeontaekj on 8/24/2017.
 */

public class Chore {

    private String name;
    private String description;
    private UUID mUUID;

    public Chore() {
        this(null, null, null);
    }

    public Chore(String name) {
        this(name, null, null);
    }

    public Chore(String name, String description) {
        this(name, description, null);
    }

    public Chore(String name, String description, String uuid) {
        this.name = name;
        this.description  = description;
        if (uuid == null) {
            mUUID = UUID.randomUUID();
        } else {
            try {
                mUUID = UUID.fromString(uuid);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description;}

    public String getUUID() {
        return mUUID.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
