package com.example.my_second_app.ui.tools;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.model.FirebaseQueryLiveData;
import com.example.my_second_app.model.PackRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class ToolsViewModel extends AndroidViewModel {

    private PackRepository repository;
    private LiveData<List<Pack>> allShippedPacks;
    private final Query query = FirebaseDatabase.getInstance().getReference().child("packs").orderByChild("packStatus").equalTo("SHIPPED");
    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(query);
    public ToolsViewModel(@NonNull Application application) {
        super(application);
        repository = new PackRepository(application);
        //      repository.getHistoryParcels();
        //allShippedPacks=repository.getAllShippedPacks();
    }

    @NonNull
    public LiveData<DataSnapshot> getdataSnapshotLiveData(){
        return liveData;
    }
}