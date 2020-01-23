package com.example.my_second_app.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.my_second_app.entities.PackShow;
import com.example.my_second_app.model.PackRepository;

import java.util.List;

public class PackViewModel extends AndroidViewModel {
    private PackRepository repository;
    private LiveData<List<PackShow>> allPacks;

    public PackViewModel(@NonNull Application application) {
        super(application);
        repository = new PackRepository(application);
        allPacks = repository.getAllPacks();
    }

    public void insert(PackShow pack) {
        repository.insert(pack);
    }

    public void update(PackShow pack) {
        repository.update(pack);
    }

    public void delete(PackShow pack) {
        repository.delete(pack);
    }

    public void deleteAllPacks() {
        repository.deleteAllPacks();
    }

    public LiveData<List<PackShow>> getAllPacks() {
        return allPacks;
    }
}
