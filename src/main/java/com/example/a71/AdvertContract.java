package com.example.a71;

import android.provider.BaseColumns;

public final class AdvertContract {

    private AdvertContract() {} // Private constructor to prevent instantiation

    public static class AdvertEntry implements BaseColumns {
        public static final String TABLE_NAME = "adverts";
        public static final String COLUMN_POST_TYPE = "post_type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_LOCATION = "location";
    }
}

