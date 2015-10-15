package piercestudio.com.listmaker;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Item {

	// Database fields
	private SQLiteDatabase database;
	private DatabaseOpenHelper dbHelper;
	private String[] allColumns = { DatabaseOpenHelper.COLUMN_ID,
			DatabaseOpenHelper.ITEM };

	public ItemDataSource(Context context) {
		dbHelper = new DatabaseOpenHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Item createItem(String item) {
		ContentValues values = new ContentValues();
		values.put(DatabaseOpenHelper.ITEM, item);
		long insertId = database.insert(DatabaseOpenHelper.TABLE_NAME, null,
										values);
		Cursor cursor = database.query(DatabaseOpenHelper.TABLE_NAME,
									   allColumns, DatabaseOpenHelper.COLUMN_ID + " = " + insertId, null,
									   null, null, null);
		cursor.moveToFirst();
		Item newItem = cursorToItem(cursor);
		cursor.close();
		return newItem;
	}

	public void deleteComment(Item item) {
		long id = item.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(DatabaseOpenHelper.TABLE_NAME, DatabaseOpenHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Item> getAllComments() {
		List<Item> comments = new ArrayList<Item>();

		Cursor cursor = database.query(DatabaseOpenHelper.TABLE_NAME,
									   allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Item item = cursorToItem(cursor);
			comments.add(item);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return comments;
	}

	private Item cursorToItem(Cursor cursor) {
		Item item= new Item();
		item.setId(cursor.getLong(0));
		item.setItem(cursor.getString(1));
		return item;
	}
}