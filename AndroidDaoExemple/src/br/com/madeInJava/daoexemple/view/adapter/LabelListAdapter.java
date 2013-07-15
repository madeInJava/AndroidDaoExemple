package br.com.madeInJava.daoexemple.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.madeInJava.daoexemple.R;
import br.com.madeInJava.daoexemple.model.entity.Label;

public class LabelListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Label> labels;

	public LabelListAdapter(Context context, List<Label> labels) {
		this.labels = labels;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return labels.size();
	}

	@Override
	public Label getItem(int position) {
		return labels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return labels.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Label label = labels.get(position);
		view = inflater.inflate(R.layout.label_list, null);

		((TextView) view.findViewById(R.id.name)).setText(label.getName());
		((TextView) view.findViewById(R.id.id)).setText("Label id: " + label.getId());

		return view;
	}

}
