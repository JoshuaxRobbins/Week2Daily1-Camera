package com.example.josh.week2daily1_camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class PersonListActivity extends AppCompatActivity {
    ArrayList<Person> personList = new ArrayList<>();
    String[] people;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personlist);

        personList = getIntent().getParcelableArrayListExtra("personList");
        people = new String[personList.size()];
        for(int i = 0;i < personList.size();i++) {
            people[i] = personList.get(i).toString();
        }

        listView = findViewById(R.id.lvList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PersonListActivity.this,android.R.layout.simple_list_item_1,people);
        listView.setAdapter(arrayAdapter);



    }
}
