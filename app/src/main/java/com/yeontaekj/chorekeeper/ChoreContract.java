package com.yeontaekj.chorekeeper;

import android.provider.BaseColumns;

/**
 * Created by yeontaekj on 8/31/2017.
 */

public final class ChoreContract {

    private ChoreContract() {}

    public static final String TABLE_NAME = "chore_table";

    public static class ChoreEntry implements BaseColumns {
        public static final String COLUMN_CHORE_NAME = "name";
        public static final String COLUMN_CHORE_DESCRIPTION = "description";
    }
}
