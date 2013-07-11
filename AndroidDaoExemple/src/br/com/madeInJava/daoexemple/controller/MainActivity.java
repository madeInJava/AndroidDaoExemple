package br.com.madeInJava.daoexemple.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import br.com.madeInJava.daoexemple.R;
import br.com.madeInJava.daoexemple.model.entity.Label;
import br.com.madeInJava.daoexemple.model.resources.dao.LabelDao;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LabelDao labelDao = new LabelDao(this);
		
		Label label = new Label();
		label.setName("Name");
		label.setDescription("This is a new label");
		
		labelDao.insert(label);
		
		labelDao.close();
		
		Toast.makeText(this, labelDao.findAll().toString(), Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
