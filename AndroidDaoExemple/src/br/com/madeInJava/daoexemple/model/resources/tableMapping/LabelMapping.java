package br.com.madeInJava.daoexemple.model.resources.tableMapping;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import br.com.madeInJava.daoexemple.model.entity.Label;

public class LabelMapping {

	public static final String TABLE = "label";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public static final String[] COLUNS = { ID, NAME, DESCRIPTION };

	private static Label bind(Cursor cursor) {
		Label label = new Label();
		label.setId(cursor.getInt(cursor.getColumnIndex(ID)));
		label.setName(cursor.getString(cursor.getColumnIndex(NAME)));
		label.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
		return label;
	}

	public static Label bindLabel(Cursor cursor) {
		if (cursor.moveToNext()) {
			Label label = bind(cursor);
			return label;
		}
		return null;
	}

	public static List<Label> bindLabels(Cursor cursor) {
		List<Label> labels = new ArrayList<Label>();
		while (cursor.moveToNext()) {
			Label label = bind(cursor);
			labels.add(label);
		}
		return labels;
	}

}
