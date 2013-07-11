package br.com.madeInJava.daoexemple.model.resources.dao;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.madeInJava.daoexemple.model.entity.Label;
import br.com.madeInJava.daoexemple.model.resources.DatabaseHelper;
import br.com.madeInJava.daoexemple.model.resources.tableMapping.LabelMapping;

public class LabelDao {

	private DatabaseHelper helper;
	private SQLiteDatabase db;

	public LabelDao(Context context) {
		this.helper = new DatabaseHelper(context);
	}

	private SQLiteDatabase getDb() {
		if (this.db == null) {
			this.db = this.helper.getWritableDatabase();
		}
		return this.db;
	}

	public void close() {
		this.helper.close();
		this.db = null;
	}

	public long insert(Label label) {
		ContentValues values = new ContentValues();
		values.put(LabelMapping.NAME, label.getName());
		values.put(LabelMapping.DESCRIPTION, label.getDescription());
		return this.getDb().insert(LabelMapping.TABLE, null, values);
	}

	public long update(Label label) {
		ContentValues values = new ContentValues();
		values.put(LabelMapping.ID, label.getId());
		values.put(LabelMapping.NAME, label.getName());
		values.put(LabelMapping.DESCRIPTION, label.getDescription());
		String[] args = { label.getId().toString() };
		return this.getDb().update(LabelMapping.TABLE, values, LabelMapping.ID + " = ?", args);
	}

	public boolean remove(Label label) {
		String whereClause = LabelMapping.ID + " = ?";
		String[] args = { label.getId().toString() };
		int rowsAffected = getDb().delete(LabelMapping.TABLE, whereClause, args);
		return rowsAffected > 0;
	}

	public List<Label> findAll() {
		Cursor cursor = getDb().query(LabelMapping.TABLE, LabelMapping.COLUNS, null, null, null, null, LabelMapping.NAME);
		List<Label> labels = LabelMapping.bindLabels(cursor);
		cursor.close();
		return labels;
	}

	public Label findById(int id) {
		String selection = LabelMapping.ID + " = ?";
		String[] args = { String.valueOf(id) };
		Cursor cursor = getDb().query(LabelMapping.TABLE, LabelMapping.COLUNS, selection, args, null, null, null);
		Label label = LabelMapping.bindLabel(cursor);
		cursor.close();
		return label;
	}

}
