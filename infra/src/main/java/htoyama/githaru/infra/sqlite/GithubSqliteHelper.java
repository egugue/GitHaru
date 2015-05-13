package htoyama.githaru.infra.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by toyamaosamuyu on 2015/05/12.
 */
public class GithubSqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = GithubSqliteHelper.class.getSimpleName();

    private static final String DB_NAME = "timetable.db";
    private static final int DB_VERSION = 1;

    public GithubSqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
