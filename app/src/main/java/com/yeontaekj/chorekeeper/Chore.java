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
        this(null, null);
    }

    public Chore(String name) {
        this(name, null);
    }

    public Chore(String name, String description) {
        this.name = name;
        this.description = description;
        mUUID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description;}

    public String getUUID() {
        return mUUID.toString();
    }
}
