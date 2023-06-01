package sg.edu.rp.c346.id22011199.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eToDo;

    Button btnAdd;
    Button btnClear;

    Button btnDel;
    ListView listView;

    Spinner spnToDo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        listView = findViewById(R.id.listView);
        eToDo = findViewById(R.id.editTextToDo);
        spnToDo = findViewById(R.id.spinner);
        btnDel = findViewById(R.id.buttonDel);

        ArrayList<String> alToDo = new ArrayList<String>();

        ArrayAdapter aaToDo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alToDo);

        listView.setAdapter(aaToDo);

        spnToDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Main","onItemSelected" + position);
                switch (position) {
                    case 0:
                        eToDo.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;                    case 1:
                        eToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ToDo = eToDo.getText().toString();
                alToDo.add(ToDo);
                aaToDo.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();


            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexStr=eToDo.getText().toString();
                if (!indexStr.isEmpty()){
                    int index=Integer.parseInt(indexStr);
                    if (index>=0&&index<alToDo.size()){
                        alToDo.remove(index);
                        aaToDo.notifyDataSetChanged();

                    }else{
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}