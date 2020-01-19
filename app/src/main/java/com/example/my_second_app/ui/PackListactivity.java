package com.example.my_second_app.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_second_app.entities.Pack;
import com.example.my_second_app.viewModel.PackViewModel;

import java.util.List;

public class PackListactivity extends AppCompatActivity {
    private LiveData<List<Pack>> packs;
    private PackViewModel packViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        packViewModel = ViewModelProviders.of(this).get(PackViewModel.class);
        //pack=new Pack(PackType.BIGֹ_PACKAGE, PackWeight.BETWEEN_0g_TO_500g,true,new Person(),new  AddressAndLocation(), PackStatus.SHIPPED, new Date(),new Date(), "deliveryName");
        //packViewModel.insert(pack);
        packs=packViewModel.getAllPacks();
    }
}
