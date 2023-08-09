package com.example.cocomo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocomo.Const.ReferenceManager;
import com.example.cocomo.Const.Untils;
import com.example.cocomo.model.Rank;
import com.example.cocomo.retrofit.ApiCocomo;
import com.example.cocomo.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResultActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button save;
    Rank sm, lq, prec, plex, resl, team,
            pmat, rely, data, cpxl, ruse, docu,
            time, stor, pvol, acap, aexp, pcap,
            pexp, ltex, pcon, tool, sced, site;

    double ufp, ir, newsloc, reusedsloc, aa, cpm;
    double effort, schedule, cost, total, eaf;
    TextView a00, a01, a02, a03, a10, a11, a12, a13,
            a20, a21, a22, a23, a30, a31, a32, a33,
            b00, b01, b02, b03, b10, b11, b12, b13,
            b20, b21, b22, b23, b30, b31, b32, b33,
            b40, b41, b42, b43, b50, b51, b52, b53,
            b60, b61, b62, b63;
    TextView efforttxt, scheduletxt, costtxt, totaltxt, eaftxt;
    String name;
    ApiCocomo apiCocomo;
    CompositeDisposable disposable = new CompositeDisposable();
    ReferenceManager manager;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();
        getToolbar();
        getData();
        caculater();
        save();
    }

    private void save() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disposable.add(apiCocomo.addCocomo(
                        manager.getString("_id"),
                        name,
                        category,
                        lq.getTitle(),
                        ufp,
                        newsloc,
                        reusedsloc,
                        ir,
                        aa,
                        prec.getTitle(),
                        plex.getTitle(),
                        resl.getTitle(),
                        team.getTitle(),
                        pmat.getTitle(),
                        rely.getTitle(),
                        data.getTitle(),
                        cpxl.getTitle(),
                        ruse.getTitle(),
                        docu.getTitle(),
                        acap.getTitle(),
                        pcap.getTitle(),
                        pcon.getTitle(),
                        aexp.getTitle(),
                        pexp.getTitle(),
                        ltex.getTitle(),
                        time.getTitle(),
                        stor.getTitle(),
                        pvol.getTitle(),
                        tool.getTitle(),
                        site.getTitle(),
                        sced.getTitle(),
                        cpm,
                        eaf,
                        effort,
                        schedule,
                        total,
                        cost
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                resultModel -> {
                                    if(resultModel.isSuccess()){
                                        Toast.makeText(ResultActivity.this, resultModel.getMessage()+"", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(ResultActivity.this, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
        });
    }

    private void gettable(double effort, double schedule, double cost) {
        a00.setText(Math.round(5.9818/100*effort*10)/10.0+"");
        a10.setText(Math.round(24.0572/100*effort*10)/10.0+"");
        a20.setText(Math.round(75.9428/100*effort*10)/10.0+"");
        a30.setText(Math.round(11.9636/100*effort*10)/10.0+"");

        a01.setText(Math.round(12.3288/100*schedule*10.0)/10.0+"");
        a11.setText(Math.round(37.6712/100*schedule*10.0)/10.0+"");
        a21.setText(Math.round(62.3288/100*schedule*10.0)/10.0+"");
        a31.setText(Math.round(12.3288/100*schedule*10.0)/10.0+"");

        a02.setText(Math.round(((5.9818/100*effort)/(12.3288/100*schedule))*10.0)/10.0+"");
        a12.setText(Math.round(((24.0572/100*effort)/(37.6712/100*schedule))*10.0)/10.0+"");
        a22.setText(Math.round(((75.9428/100*effort)/(62.3288/100*schedule))*10.0)/10.0+"");
        a32.setText(Math.round(((11.9636/100*effort)/(12.3288/100*schedule))*10.0)/10.0+"");

        a03.setText("$"+Math.round(5.9857/100*cost));
        a13.setText("$"+Math.round(24.0/100*cost));
        a23.setText("$"+Math.round(76.0/100*cost));
        a33.setText("$"+Math.round(12.0/100*cost));

        b00.setText(Math.round((5.9818/100*effort)*(13.9344/100)*10)/10.0+"");
        b10.setText(Math.round((5.9818/100*effort)*(10.1093/100)*10)/10.0+"");
        b20.setText(Math.round((5.9818/100*effort)*(37.9781/100)*10)/10.0+"");
        b30.setText(Math.round((5.9818/100*effort)*(19.1257/100)*10)/10.0+"");
        b40.setText(Math.round((5.9818/100*effort)*(7.9235/100)*10)/10.0+"");
        b50.setText(Math.round((5.9818/100*effort)*(7.9235/100)*10)/10.0+"");
        b60.setText(Math.round((5.9818/100*effort)*(3.0055/100)*10)/10.0+"");

        b01.setText(Math.round((24.0572/100*effort)*(12.0137/100)*10)/10.0+"");
        b11.setText(Math.round((24.0572/100*effort)*(7.9833/100)*10)/10.0+"");
        b21.setText(Math.round((24.0572/100*effort)*(18.0205/100)*10)/10.0+"");
        b31.setText(Math.round((24.0572/100*effort)*(35.9727/100)*10)/10.0+"");
        b41.setText(Math.round((24.0572/100*effort)*(12.9693/100)*10)/10.0+"");
        b51.setText(Math.round((24.0572/100*effort)*(10.0341/100)*10)/10.0+"");
        b61.setText(Math.round((24.0572/100*effort)*(3.0034/100)*10)/10.0+"");

        b02.setText(Math.round((75.9428/100*effort)*(10.0022/100)*10)/10.0+"");
        b12.setText(Math.round((75.9428/100*effort)*(5.0011/100)*10)/10.0+"");
        b22.setText(Math.round((75.9428/100*effort)*(7.9975/100)*10)/10.0+"");
        b32.setText(Math.round((75.9428/100*effort)*(15.9948/100)*10)/10.0+"");
        b42.setText(Math.round((75.9428/100*effort)*(33.9944/100)*10)/10.0+"");
        b52.setText(Math.round((75.9428/100*effort)*(23.9922/100)*10)/10.0+"");
        b62.setText(Math.round((75.9428/100*effort)*(2.9963/100)*10)/10.0+"");

        b03.setText(Math.round((11.9636/100*effort)*(14.0518/100)*10)/10.0+"");
        b13.setText(Math.round((11.9636/100*effort)*(5.0477/100)*10)/10.0+"");
        b23.setText(Math.round((11.9636/100*effort)*(3.9563/100)*10)/10.0+"");
        b33.setText(Math.round((11.9636/100*effort)*(3.9563/100)*10)/10.0+"");
        b43.setText(Math.round((11.9636/100*effort)*(18.9632/100)*10)/10.0+"");
        b53.setText(Math.round((11.9636/100*effort)*(24.0109/100)*10)/10.0+"");
        b63.setText(Math.round((11.9636/100*effort)*(30.0136/100)*10)/10.0+"");
    }

    private void caculater() {
        if(sm.getValue() == 0){//line of code
            category = "SLOC";
            total = newsloc + reusedsloc*(aa/100 + 0.3*ir/100);

        }else{//function points
            category = "FP";
            total = lq.getValue() * ufp;
        }
        eaf = rely.getValue()*data.getValue()*cpxl.getValue()*ruse.getValue()*
                docu.getValue()*time.getValue()*stor.getValue()*pvol.getValue()*
                acap.getValue()*aexp.getValue()*pcap.getValue()*pexp.getValue()*
                ltex.getValue()*pcon.getValue()*tool.getValue()*sced.getValue()*
                site.getValue();
        double kloc = total/1000;
        double b = 0.91 + 0.01*(prec.getValue()+plex.getValue()+ resl.getValue()+ team.getValue()+pmat.getValue());
        effort = 2.94*Math.pow(kloc,b)*eaf;
        schedule = 3.67*Math.pow(effort,0.3179);
        cost = Math.round(effort*cpm);

        efforttxt.setText("Effort = "+Math.round(effort*10.0)/10.0+" Person-months");
        scheduletxt.setText("Schedule = "+Math.round(schedule*10.0)/10.0+" Months");
        costtxt.setText("Cost = "+cost);
        totaltxt.setText("Total Equivalent Size = "+Math.round(total*10.0)/10.0+" SLOC");
        eaftxt.setText("Effort Adjustment Factor (EAF) = "+eaf);
        gettable(effort, schedule, cost);
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        sm = (Rank) bundle.getSerializable("sm");;
        lq = (Rank) bundle.getSerializable("lq");
        prec = (Rank) bundle.getSerializable("prec");
        plex = (Rank) bundle.getSerializable("plex");
        resl = (Rank) bundle.getSerializable("resl");
        team = (Rank) bundle.getSerializable("team");
        ufp = bundle.getDouble("ufp");
        ir = bundle.getDouble("ir");
        pmat = (Rank) bundle.getSerializable("pmat");
        rely = (Rank) bundle.getSerializable("rely");
        data = (Rank) bundle.getSerializable("data");
        cpxl = (Rank) bundle.getSerializable("cplx");
        ruse = (Rank) bundle.getSerializable("ruse");
        docu = (Rank) bundle.getSerializable("docu");
        time = (Rank) bundle.getSerializable("time");
        stor = (Rank) bundle.getSerializable("stor");
        pvol = (Rank) bundle.getSerializable("pvol");
        acap = (Rank) bundle.getSerializable("acap");
        aexp = (Rank) bundle.getSerializable("aexp");
        pcap = (Rank) bundle.getSerializable("pcap");
        pexp = (Rank) bundle.getSerializable("pexp");
        ltex = (Rank) bundle.getSerializable("ltex");
        pcon = (Rank) bundle.getSerializable("pcon");
        tool = (Rank) bundle.getSerializable("tool");
        sced = (Rank) bundle.getSerializable("sced");
        site = (Rank) bundle.getSerializable("site");
        newsloc = bundle.getDouble("newsloc");
        reusedsloc = bundle.getDouble("reusloc");
        aa = bundle.getDouble("aa");
        cpm = bundle.getDouble("cpm");
        name = bundle.getString("name");
    }

    private void getToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        toolbar = findViewById(R.id.toolBar);
        efforttxt = findViewById(R.id.effort);
        scheduletxt = findViewById(R.id.schedule);
        apiCocomo = RetrofitClient.getRetrofit(Untils.BASE_URL).create(ApiCocomo.class);
        costtxt = findViewById(R.id.cost);
        manager = new ReferenceManager(ResultActivity.this);
        totaltxt = findViewById(R.id.total);
        eaftxt = findViewById(R.id.eaf);
        a00 = findViewById(R.id.a00);
        a01 = findViewById(R.id.a01);
        a02 = findViewById(R.id.a02);
        a03 = findViewById(R.id.a03);
        a10 = findViewById(R.id.a10);
        a11 = findViewById(R.id.a11);
        a12 = findViewById(R.id.a12);
        a13 = findViewById(R.id.a13);
        a20 = findViewById(R.id.a20);
        a21 = findViewById(R.id.a21);
        a22 = findViewById(R.id.a22);
        a23 = findViewById(R.id.a23);
        a30 = findViewById(R.id.a30);
        a31 = findViewById(R.id.a31);
        a32 = findViewById(R.id.a32);
        a33 = findViewById(R.id.a33);
        b00 = findViewById(R.id.b00);
        b01 = findViewById(R.id.b01);
        b02 = findViewById(R.id.b02);
        b03 = findViewById(R.id.b03);
        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);
        b13 = findViewById(R.id.b13);
        b20 = findViewById(R.id.b20);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);
        b23 = findViewById(R.id.b23);
        b30 = findViewById(R.id.b30);
        b31 = findViewById(R.id.b31);
        b32 = findViewById(R.id.b32);
        b33 = findViewById(R.id.b33);
        b40 = findViewById(R.id.b40);
        b41 = findViewById(R.id.b41);
        b42 = findViewById(R.id.b42);
        b43 = findViewById(R.id.b43);
        b50 = findViewById(R.id.b50);
        b51 = findViewById(R.id.b51);
        b52 = findViewById(R.id.b52);
        b53 = findViewById(R.id.b53);
        b60 = findViewById(R.id.b60);
        b61 = findViewById(R.id.b61);
        b62 = findViewById(R.id.b62);
        b63 = findViewById(R.id.b63);
        save = findViewById(R.id.save);
    }
    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}