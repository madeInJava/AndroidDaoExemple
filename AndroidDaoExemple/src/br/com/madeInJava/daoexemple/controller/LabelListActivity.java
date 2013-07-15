package br.com.madeInJava.daoexemple.controller;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.madeInJava.daoexemple.R;
import br.com.madeInJava.daoexemple.model.entity.Label;
import br.com.madeInJava.daoexemple.model.resources.dao.LabelDao;
import br.com.madeInJava.daoexemple.view.adapter.LabelListAdapter;

public class LabelListActivity extends ListActivity implements OnItemClickListener {

	private LabelDao labelDao;
	private List<Label> labels;
	private LabelListAdapter labelListAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.labelDao = new LabelDao(this);
		this.labels = labelDao.findAll();
		this.labelListAdapter = new LabelListAdapter(this, this.labels);
		super.setListAdapter(labelListAdapter);
		ListView listView = super.getListView();
		listView.setOnItemClickListener(this);
		super.registerForContextMenu(listView);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		String description = ((LabelListAdapter) adapterView.getAdapter()).getItem(position).getDescription();
		Toast.makeText(this, "Description: " + description, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.label_item_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (R.id.remove == item.getItemId()) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			Label label = this.labels.get(info.position);
			this.labelDao.remove(label);
			this.labels.remove(label);
			super.getListView().invalidateViews();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.label_list_menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.newLabel:
			startActivity(new Intent(this, LabelPersistActivity.class));
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}
	}

	@Override
	protected void onRestart() {
		this.labels.clear();
		this.labels.addAll(labelDao.findAll());
		super.getListView().invalidateViews();
		super.onRestart();
	}

}
