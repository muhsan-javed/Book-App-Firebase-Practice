package com.muhsantech.recyclerviewtestapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhsantech.recyclerviewtestapp.Adapters.BookAdapter;
import com.muhsantech.recyclerviewtestapp.Models.BookModel;
import com.muhsantech.recyclerviewtestapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<BookModel> list = new ArrayList<>();

//        list.add(new BookModel(R.drawable.book1, "The World Versus Everything beyond"));
//        list.add(new BookModel(R.drawable.book2, "Death of Ever And Ever"));
//        list.add(new BookModel(R.drawable.book3, "ANight In the Woods"));
//        list.add(new BookModel(R.drawable.book4, "Time And Work"));
//        list.add(new BookModel(R.drawable.book5, "The DarkSide of Winter"));
//        list.add(new BookModel(R.drawable.book6, "Journey Stars"));
//        list.add(new BookModel(R.drawable.book7, "The Black woods"));
//        list.add(new BookModel(R.drawable.book8, "Don't fall aSleep"));
//        list.add(new BookModel(R.drawable.book9, "Love"));

       /* list.add(new BookModel(R.drawable.book1, "The World Versus Everything beyond"));
        list.add(new BookModel(R.drawable.book2, "Death of Ever And Ever"));
        list.add(new BookModel(R.drawable.book3, "ANight In the Woods"));
        list.add(new BookModel(R.drawable.book4, "Time And Work"));
        list.add(new BookModel(R.drawable.book5, "The DarkSide of Winter"));
        list.add(new BookModel(R.drawable.book6, "Journey Stars"));
        list.add(new BookModel(R.drawable.book7, "The Black woods"));
        list.add(new BookModel(R.drawable.book8, "Don't fall aSleep"));
        list.add(new BookModel(R.drawable.book9, "Love"));

        list.add(new BookModel(R.drawable.book1, "The World Versus Everything beyond"));
        list.add(new BookModel(R.drawable.book2, "Death of Ever And Ever"));
        list.add(new BookModel(R.drawable.book3, "ANight In the Woods"));
        list.add(new BookModel(R.drawable.book4, "Time And Work"));
        list.add(new BookModel(R.drawable.book5, "The DarkSide of Winter"));
        list.add(new BookModel(R.drawable.book6, "Journey Stars"));
        list.add(new BookModel(R.drawable.book7, "The Black woods"));
        list.add(new BookModel(R.drawable.book8, "Don't fall aSleep"));
        list.add(new BookModel(R.drawable.book9, "Love"));*/


        BookAdapter adapter = new BookAdapter(list, MainActivity.this);
        binding.recyclerView.setAdapter(adapter);

        // LinearLayoutManager
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        binding.recyclerView.setLayoutManager(linearLayoutManager);

            // Scroll View Right or Left and  Used Horizontal or vertical
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
//        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // GridLayoutManager
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        binding.recyclerView.setLayoutManager(gridLayoutManager);

        // StaggeredGridLayoutManager Best
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(staggeredGridLayoutManager);


        // New Code
        // Firebase DataBase Used Now
        FirebaseDatabase.getInstance().getReference().child("books")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    BookModel model = snapshot1.getValue(BookModel.class);
                    list.add(model);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}