package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editTextTodo;
    private List<Todo> todos;
    private int todosLastId;
    private TodoAdapter listViewTodoAdapter;
    private int index;

    private void addTodo(String title) {
        Todo newTodo = new Todo(++todosLastId, title);
        todos.add(newTodo);
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void deleteTodo(int indexToDelete) {
        todos.remove(indexToDelete);
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void deleteAllTodo() {
        todos.clear();
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void makeTestData() {
        for (int i = 0; i < 100; i++) {
            addTodo("할일" + (i + 1));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("할일 리스트");

        todos = new ArrayList<>();
        todosLastId = 0;

        editTextTodo = findViewById(R.id.activity_main__editTextTodo);

        ListView listViewTodo = findViewById(R.id.main_activity__listViewTodo);

        View.OnClickListener btnDeleteClicked = view -> {
            final int indexToDelete = (int) view.getTag();

            DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteTodo(indexToDelete);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            };

            new AlertDialog.Builder(this)
                    .setMessage("삭제하시겠습니까?")
                    .setPositiveButton("예", onClickListener)
                    .setNegativeButton("아니오", onClickListener)
                    .show();

        };

        View.OnClickListener btnDetailClicked = view -> {
            final int indexToDelete = (int) view.getTag();
            Intent intent = new Intent(this, DetailActivity.class);
            Todo todo = todos.get(indexToDelete);
            intent.putExtra("todoId", todo.getId());
            intent.putExtra("todoTitle", todo.getTitle());
            startActivity(intent);
        };

        View.OnClickListener btnShowModifyClicked = view -> {
            Toast.makeText(this,"수정시작", Toast.LENGTH_SHORT).show();
        };

        View.OnClickListener btnModifyClicked = view -> {
            Toast.makeText(this,"수정적용", Toast.LENGTH_SHORT).show();

        };

        View.OnClickListener btnCancelModifyClicked = view -> {
            Toast.makeText(this,"수정취소", Toast.LENGTH_SHORT).show();
        };

        listViewTodoAdapter = new TodoAdapter(todos, btnDeleteClicked, btnDetailClicked, btnShowModifyClicked, btnModifyClicked, btnCancelModifyClicked);
        listViewTodo.setAdapter(listViewTodoAdapter);

        makeTestData();

    }

    public void btnAddTodoClicked(View view) {
        String newTodo = editTextTodo.getText().toString().trim();
        editTextTodo.setText(newTodo);

        if (newTodo.length() == 0) {
            Toast.makeText(this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextTodo.requestFocus();

            return;
        }

        addTodo(newTodo);

        editTextTodo.setText("");
        editTextTodo.requestFocus();

        Log.d(TAG, "todos : " + todos);
    }

    public void btnDeleteAllTodosClicked(View view) {
        if (todos.size() == 0) {
            Toast.makeText(this, "삭제할 리스트가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteAllTodo();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        new AlertDialog.Builder(this)
                .setMessage("정말 전부 삭제하시겠습니까?")
                .setPositiveButton("예", onClickListener)
                .setNegativeButton("아니오", onClickListener)
                .show();

    }

    public void btnFinishAppClicked(View view) {
        finish();
    }
}