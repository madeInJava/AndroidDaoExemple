package br.com.madeInJava.daoexemple.controller;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.madeInJava.daoexemple.R;
import br.com.madeInJava.daoexemple.model.entity.Label;
import br.com.madeInJava.daoexemple.model.resources.dao.LabelDao;

public class LabelPersistActivity extends Activity {

	public static final String LABEL_PARAM = "label";

	private Label label;

	private LabelDao labelDao;

	private EditText name;
	private EditText description;
	private Button save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.label_persist);
		this.labelDao = new LabelDao(this);
		this.initLabel();
		this.initComponents();
		this.bindComponentsWithLabel();
	}

	private void initLabel() {
		if (getIntent().getExtras() != null) {
			Serializable externalParam = getIntent().getExtras().getSerializable(LABEL_PARAM);
			this.label = (Label) externalParam;
		} else {
			this.label = new Label();
		}
	}

	private void initComponents() {
		this.name = (EditText) findViewById(R.id.name);
		this.description = (EditText) findViewById(R.id.description);
		this.save = (Button) findViewById(R.id.save);
		this.save.setOnClickListener(this.save());
	}

	private void bindLabelWithComponents() {
		this.label.setName(this.name.getText().toString());
		this.label.setDescription(this.description.getText().toString());
	}

	private void bindComponentsWithLabel() {
		this.name.setText(this.label.getName());
		this.description.setText(this.label.getDescription());
	}

	private OnClickListener save() {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				bindLabelWithComponents();
				if (label.getId() == null)
					label.setId((int) labelDao.save(label));
				else
					labelDao.update(label);
				Toast.makeText(LabelPersistActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
			}
		};
	}

	@Override
	protected void onDestroy() {
		this.labelDao.close();
		super.onDestroy();
	}

}
