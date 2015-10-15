package piercestudio.com.listmaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "table";
	public static final String COLUMN_ID = "_id";
	public static final String ITEM = "item";

	private static final String LIST_TABLE_CREATE =
			"CREATE TABLE " +
					TABLE_NAME +
					" (" +
					COLUMN_ID + " int, " +
					ITEM + " TEXT" +
					");";

	DatabaseOpenHelper(Context context) {
		super(context, TABLE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(LIST_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		onCreate(db);
	}
}
