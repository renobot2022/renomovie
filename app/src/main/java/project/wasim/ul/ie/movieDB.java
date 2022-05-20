/*
name: Wasim Ghazal Aswad
ID: 17193559
Last update: 13/05/2020
 */

package project.wasim.ul.ie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class movieDB {

    public static final String KEY_ID = "_id";

    public static final String KEY_MOVIE_NAME =
            "movie_name";
    public static final String KEY_DIRECTOR =
            "director_name";
    public static final String KEY_YEAR =
            "year";
    public static final String KEY_GENRE =
            "genre";
    public static final String KEY_PHOTO =
            "pic";
    public static final String KEY_RATE =
            "rate";
    public static final String KEY_STORY =
            "story";
    public static final String KEY_URL =
            "url";
    public static final String KEY_AWARDS =
            "AWARDS";

    private static SQLiteOpenHelper moduleDBOpenHelper;

    private Context context;



    public movieDB(Context context) {
        this.context = context;
        moduleDBOpenHelper = new ModuleDBOpenHelper(context, ModuleDBOpenHelper.DATABASE_NAME, null,
                ModuleDBOpenHelper.DATABASE_VERSION);


        if (getAll().length == 0) {

            this.addRow("1917", "Todd Phillips", 2019 ,"Crime, Drama, Thriller" , R.mipmap.mov_1917_foreground, 8.4f , context.getString(R.string.mov1917story),"https://www.youtube.com/watch?v=gZjQROMAh_s", context.getString(R.string.awards1917));
            this.addRow("Avatar", "James Cameron", 2009, "Action, Adventure, Fantasy, Sci-Fi" , R.mipmap.avatar_foreground , 7.8f , context.getString(R.string.avatarstory),"https://www.youtube.com/watch?v=5PSNL1qE6VY", context.getString(R.string.awardsavatar));
            this.addRow("Ford v Ferrari", "James Mangold", 2019, "Action, Biography, Drama" , R.mipmap.fvf , 7.8f , context.getString(R.string.FordVFerraristory),"https://www.youtube.com/watch?v=zyYgDtY2AMY", context.getString(R.string.awardsFvF));
            this.addRow("Inception", "Christopher Nolan", 2010, "Action, Adventure, Sci-Fi" , R.mipmap.inception , 8.8f , context.getString(R.string.Inceptionstory),"https://www.youtube.com/watch?v=YoHD9XEInc0", context.getString(R.string.awardsIncetion));
            this.addRow("Joker", "Todd Phillips", 2019,"Crime, Drama, Thriller" , R.mipmap.joker_foreground , 8.6f, context.getString(R.string.jokerstory),"https://www.youtube.com/watch?v=zAGVQLHvwOY", context.getString(R.string.awardsjoker));
            this.addRow("Once Upon a Time in Hollywood", "Vladislav Kozlov", 2019,"Drama" , R.mipmap.hollywood_foreground , 7.7f , context.getString(R.string.hollywoodstory),"https://www.youtube.com/watch?v=ELeMaP8EPAA", context.getString(R.string.awardshollywood));
            this.addRow("Parasite", "Bong Joon Ho", 2019,"Comedy, Drama, Thriller" , R.mipmap.par , 8.6f , context.getString(R.string.parasitestory),"https://www.youtube.com/watch?v=5xH0HfJHsaY", context.getString(R.string.awardsparasite));
            this.addRow("The Dictator", "Larry Charles", 2012,"Comedy" , R.mipmap.dictator , 6.4f , context.getString(R.string.dictatorstory),"https://www.youtube.com/watch?v=cYplvwBvGA4", context.getString(R.string.awardsdictator));
            this.addRow("The Gentlemen", "Guy Ritchie", 2019,"Action, Comedy, Crime" , R.mipmap.gentleman , 7.9f , context.getString(R.string.gentlemenstory),"https://www.youtube.com/watch?v=Ify9S7hj480", context.getString(R.string.awardsgentleman));
            this.addRow("The Irishman", "Martin Scorsese", 2019,"Biography, Crime, Drama" , R.mipmap.irish_foreground , 8.0f , context.getString(R.string.irishmanstory),"https://www.youtube.com/watch?v=RS3aHkkfuEI", context.getString(R.string.awardsIrish));
            this.addRow("The Matrix", "Lana Wachowski, Lilly Wachowski", 1999,"Action, Sci-Fi" , R.mipmap.matrix , 7.9f , context.getString(R.string.matrixstory),"https://www.youtube.com/watch?v=vKQi3bBA1y8", context.getString(R.string.awardsmatrix));
        }
    }

    public void addRow(String movieName, String director, int year, String genre , int pic , float rate , String story , String url , String AWARDS) {

        ContentValues newValues = new ContentValues();

        newValues.put(KEY_MOVIE_NAME, movieName);
        newValues.put(KEY_DIRECTOR, director);
        newValues.put(KEY_YEAR, year);
        newValues.put(KEY_GENRE, genre);
        newValues.put(KEY_PHOTO, pic);
        newValues.put(KEY_RATE, rate);
        newValues.put(KEY_STORY, story);
        newValues.put(KEY_URL, url);
        newValues.put(KEY_AWARDS, AWARDS);

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        db.insert(ModuleDBOpenHelper.DATABASE_TABLE, null, newValues);
    }


    public String[] getAll() {

        ArrayList<String> outputArray = new ArrayList<String>();
        String[] result_columns = new String[]{

                KEY_MOVIE_NAME, KEY_DIRECTOR, KEY_YEAR , KEY_GENRE};

        String movieName;
        String directorName;
        int year;

        String genre;
        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);

        boolean result = cursor.moveToFirst();
        while (result) {
            movieName = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_NAME));
            directorName = cursor.getString(cursor.getColumnIndex(KEY_DIRECTOR));
            year = cursor.getInt(cursor.getColumnIndex(KEY_YEAR));
            genre = cursor.getString(cursor.getColumnIndex(KEY_GENRE));
            outputArray.add(movieName + " "+ directorName +  year + " " + genre);
            result = cursor.moveToNext();

        }
        return outputArray.toArray(new String[outputArray.size()]);
    }

    public String[] getMovieNames() {

        ArrayList<String> outputArray = new ArrayList<String>();
        String[] result_columns = new String[]{
                KEY_MOVIE_NAME};

        String movieName;

        String where = null;
        String whereArgs[] = null;
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        //
        boolean result = cursor.moveToFirst();
        while (result) {
            movieName = cursor.getString(cursor.getColumnIndex(KEY_MOVIE_NAME));

            outputArray.add(movieName);
            result = cursor.moveToNext();
        }
        return outputArray.toArray(new String[outputArray.size()]);
    } // 317

    public String geturl(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_URL};

        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnGenre = cursor.getColumnIndex(KEY_URL);
            return cursor.getString(columnGenre);
        } else return null;
    }

    public String getAwards(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_AWARDS};

        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnGenre = cursor.getColumnIndex(KEY_AWARDS);
            return cursor.getString(columnGenre);
        } else return null;
    }

    public Integer getYear(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_YEAR};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnYear = cursor.getColumnIndex(KEY_YEAR);
            return cursor.getInt(columnYear);
        } else return 0;
    } // 342

    public String getGenre(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_GENRE};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnGenre = cursor.getColumnIndex(KEY_GENRE);
            return cursor.getString(columnGenre);
        } else return null;
    }

    public String getStory(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_STORY};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnStory = cursor.getColumnIndex(KEY_STORY);
            return cursor.getString(columnStory);
        } else return null;
    }

    public Integer getPhoto(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_PHOTO};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnPhoto = cursor.getColumnIndex(KEY_PHOTO);
            return cursor.getInt(columnPhoto);
        } else return 0;
    }


    public float getRate(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_RATE};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnRate = cursor.getColumnIndex(KEY_RATE);
            return cursor.getFloat(columnRate);
        } else return 0;
    }

    public String getDirector(String movieName) {
        String[] result_columns = new String[]{
                KEY_ID, KEY_DIRECTOR};


        String where = KEY_MOVIE_NAME + "= ?";
        String whereArgs[] = {movieName};
        String groupBy = null;
        String having = null;
        String order = null;

        SQLiteDatabase db = moduleDBOpenHelper.getWritableDatabase();
        Cursor cursor = db.query(ModuleDBOpenHelper.DATABASE_TABLE,
                result_columns, where,
                whereArgs, groupBy, having, order);
        if (cursor.moveToFirst()) {
            int columnDirector = cursor.getColumnIndex(KEY_DIRECTOR);
            return cursor.getString(columnDirector);
        } else return null;
    } // 388




    private static class ModuleDBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String DATABASE_TABLE = "Movies";
        private static final int DATABASE_VERSION = 1;

        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + " (" + KEY_ID +
                " integer primary key autoincrement, " +
                KEY_MOVIE_NAME + " text not null, " +
                KEY_DIRECTOR + " text , " +
                KEY_YEAR + " int , " +
                KEY_GENRE + " text , " +
                KEY_PHOTO + " int , " +
                KEY_RATE + " float , " +
                KEY_STORY + " text , " +
                KEY_URL + " text , " +
                KEY_AWARDS + " text );";

        public ModuleDBOpenHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }


}
