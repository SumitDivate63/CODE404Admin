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

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
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
        FirebaseFirestore.getInstance().collection("Participants")
                .orderBy("score", Query.Direction.DESCENDING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (value != null) {
                        participantList.clear();
                        for (DocumentSnapshot doc : value.getDocuments()) {
                            ParticipantModel participant = doc.toObject(ParticipantModel.class);
                            if (participant != null) {
                                participantList.add(participant);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
