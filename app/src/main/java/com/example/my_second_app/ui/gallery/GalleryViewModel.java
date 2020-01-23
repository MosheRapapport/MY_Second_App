package com.example.my_second_app.ui.gallery;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.model.PackRepository;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {

    private PackRepository repository;
    private LiveData<List<Pack>> allPacks;

    public GalleryViewModel(@NonNull Application application) {
        super(application);
        repository = new PackRepository(application);
        repository.getHistoryParcels();
        allPacks=repository.getAllPacks();
    }

    public LiveData<List<Pack>> getAllPacks(){return allPacks;}
}