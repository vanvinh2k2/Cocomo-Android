package com.example.cocomo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cocomo.Const.ReferenceManager;
import com.example.cocomo.Const.Untils;
import com.example.cocomo.adapter.CocomoAdapter;
import com.example.cocomo.model.Cocomo;
import com.example.cocomo.retrofit.ApiCocomo;
import com.example.cocomo.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HistoryActivity extends AppCompatActivity {
    Toolbar toolbar;
    CocomoAdapter adapter;
    List<Cocomo> arrCocomo;
    RecyclerView listCocomo;
    ApiCocomo apiCocomo;
    ReferenceManager manager;

    CompositeDisposable disposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
        getToolbar();
        getdata();
    }

    private void getdata() {
        disposable.add(apiCocomo.getCocomo(manager.getString("_id"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cocomoModel -> {
                            if(cocomoModel.isSuccess()){
                                arrCocomo = cocomoModel.getData();
                                adapter = new CocomoAdapter(HistoryActivity.this, arrCocomo, R.layout.item_history);
                                listCocomo.setAdapter(adapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    public void delete(String idCon){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Notification")
                .setIcon(R.drawable.baseline_notifications_24)
                .setMessage("You want to delete this Project?")
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           disposable.add(apiCocomo.delete(manager.getString("_id"), idCon)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(
                                           cocomoModel -> {
                                               if(cocomoModel.isSuccess()){
                                                   Toast.makeText(HistoryActivity.this, cocomoModel.getMessage()+"", Toast.LENGTH_SHORT).show();
                                                   getdata();
                                               }
                                           },
                                           throwable -> {
                                               Toast.makeText(HistoryActivity.this, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                                           }
                                   ));
                       }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                });
        dialog.show();

    }

    public void update(String idCon){
        disposable.add(apiCocomo.delete(manager.getString("_id"), idCon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        cocomoModel -> {
                            if(cocomoModel.isSuccess()){
                                Toast.makeText(this, cocomoModel.getMessage()+"", Toast.LENGTH_SHORT).show();
                                getdata();
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void getToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("History");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void init(){
        toolbar = findViewById(R.id.toolbarmain);
        arrCocomo = new ArrayList<>();
        listCocomo = findViewById(R.id.listHistory);
        apiCocomo = RetrofitClient.getRetrofit(Untils.BASE_URL).create(ApiCocomo.class);
        manager = new ReferenceManager(HistoryActivity.this);
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}