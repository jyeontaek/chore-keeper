package com.yeontaekj.chorekeeper;

import android.os.Parcelable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yeontaekj on 8/24/2017.
 */

public class Chore implements Serializable {

    private String name;
    private String description;
    private UUID mUUID;
    private List<DateTime> dateList;

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
        dateList = new ArrayList<>();


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

    public List<DateTime> getDates() {
        return dateList;
    }

    public void setDateList(List<DateTime> dateList) {
        this.dateList = dateList;
    }

    public void addDate(DateTime date) {
        dateList.add(date);
    }
}
