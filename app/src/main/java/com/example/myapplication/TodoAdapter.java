package com.example.myapplication;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TodoAdapter extends BaseAdapter {
    List<Todo> todos = null;
    private View.OnClickListener btnDeleteClicked;
    private View.OnClickListener btnDetailClicked;

    private View.OnClickListener btnShowModifyClicked;
    private View.OnClickListener btnModifyClicked;
    private View.OnClickListener btnCancelModifyClicked;

    public TodoAdapter(List<Todo> todos, View.OnClickListener btnDeleteClicked, View.OnClickListener btnDetailClicked
    , View.OnClickListener btnShowModifyClicked, View.OnClickListener btnModifyClicked, View.OnClickListener btnCancelModifyClicked) {
        this.todos = todos;
        this.btnDeleteClicked = btnDeleteClicked;
        this.btnDetailClicked = btnDetailClicked;

        this.btnShowModifyClicked = btnShowModifyClicked;
        this.btnModifyClicked = btnModifyClicked;
        this.btnCancelModifyClicked = btnCancelModifyClicked;

    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return todos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.textViewId = convertView.findViewById(R.id.item_todo__textViewId);
            viewHolder.textViewTitle = convertView.findViewById(R.id.item_todo__textViewTitle);
            viewHolder.editTextTitle = convertView.findViewById(R.id.item_todo__editTextTitle);
            viewHolder.btnShowModify = convertView.findViewById(R.id.item_todo__btnShowModify);
            viewHolder.btnModify = convertView.findViewById(R.id.item_todo__btnModify);
            viewHolder.btnCancelModify = convertView.findViewById(R.id.item_todo__btnCancelModify);
            viewHolder.btnDelete = convertView.findViewById(R.id.item_todo__btnDelete);
            viewHolder.btnDetail = convertView.findViewById(R.id.item_todo__btnDetail);

            viewHolder.textViewTitle.setOnClickListener(btnDetailClicked);
            viewHolder.btnShowModify.setOnClickListener(btnShowModifyClicked);
            viewHolder.btnModify.setOnClickListener(btnModifyClicked);
            viewHolder.btnCancelModify.setOnClickListener(btnCancelModifyClicked);
            viewHolder.btnDelete.setOnClickListener(btnDeleteClicked);
            viewHolder.btnDetail.setOnClickListener(btnDetailClicked);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Todo todo = todos.get(position);

        viewHolder.textViewId.setText(todo.getId() + "");
        viewHolder.textViewTitle.setText( todo.getTitle());
        viewHolder.textViewTitle.setTag(position);
        viewHolder.btnShowModify.setTag(position);
        viewHolder.btnModify.setTag(position);
        viewHolder.btnCancelModify.setTag(position);
        viewHolder.btnDelete.setTag(position);
        viewHolder.btnDetail.setTag(position);

        return convertView;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewTitle;
        EditText editTextTitle;

        Button btnDelete;
        Button btnDetail;

        Button btnShowModify;
        Button btnCancelModify;
        Button btnModify;
    }
}
