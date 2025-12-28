package com.crn.code404admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScoreboardAdapter adapter;
    private List<ParticipantModel> participantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.participantRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        participantList = new ArrayList<>();

        adapter = new ScoreboardAdapter(participantList);
        recyclerView.setAdapter(adapter);

        fetchParticipantsFromFirebase();



    }

    private void fetchParticipantsFromFirebase() {
        FirebaseDatabase.getInstance().getReference("Participants")
                .orderByChild("score") // optional: sorts by score
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        participantList.clear();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            String name = data.child("name").getValue(String.class);
                            String mobile = data.child("mobile").getValue(String.class);
                            int score = data.child("score").getValue(Integer.class);
                            String level=data.child("level").getValue(String.class);
                            participantList.add(new ParticipantModel(name, mobile,score,level));
                        }
                        participantList.sort((p1, p2) -> Integer.compare(p2.score, p1.score));
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
