package com.example.my_second_app.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.model.PackRepository;

import java.util.List;

public class PackViewModel extends AndroidViewModel {
    private PackRepository repository;
    private LiveData<List<Pack>> allPacks;

    public PackViewModel(@NonNull Application application) {
        super(application);
        repository = new PackRepository(application);
        allPacks = repository.getAllPacks();
    }

    public void insert(Pack pack) {
        repository.insert(pack);
    }

    public void update(Pack pack) {
        repository.update(pack);
    }

    public void delete(Pack pack) {
        repository.delete(pack);
    }

    public void deleteAllPacks() {
        repository.deleteAllPacks();
    }

    public LiveData<List<Pack>> getAllPacks() {
        return allPacks;
    }
}
