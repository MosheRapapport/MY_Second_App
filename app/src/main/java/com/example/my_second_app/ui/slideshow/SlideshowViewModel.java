package com.example.my_second_app.ui.slideshow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.model.PackRepository;

import java.util.List;

public class SlideshowViewModel extends AndroidViewModel {

    private PackRepository repository;
    private LiveData<List<PackShow>> allPacks;

    public SlideshowViewModel(@NonNull Application application) {
        super(application);
        repository = new PackRepository(application);
        //      repository.getHistoryParcels();

        allPacks=repository.getAll_OFFER_TO_COLLECT_PacksShow();
    }

    public LiveData<List<PackShow>> getAll_OFFER_TO_COLLECT_PacksShow(){return allPacks;}

}