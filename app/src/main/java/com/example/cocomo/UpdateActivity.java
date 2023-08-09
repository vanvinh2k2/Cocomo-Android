package com.example.cocomo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cocomo.Const.ReferenceManager;
import com.example.cocomo.Const.Untils;
import com.example.cocomo.adapter.RankAdapter2;
import com.example.cocomo.model.Cocomo;
import com.example.cocomo.model.Rank;
import com.example.cocomo.retrofit.ApiCocomo;
import com.example.cocomo.retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateActivity extends AppCompatActivity {
    Toolbar toolbar;
    Spinner listSM, listLQ, listPREC, listPLEX, listRESL, listTEAM,
            listPMAT, listRELY, listDATA, listCPLX, listRUSE, listDOCU,
            listTIME, listSTOR, listPVOL, listACAP, listAEXP, listPCAP,
            listPEXP, listLTEX, listPCON, listTOOL, listSCRED, listSITE;

    RankAdapter2 adapterPREC, adapterPLEX, adapterRESL, adapterTEAM, adapterLQ,
            adapterPMAT, adapterRELY, adapterDATA, adapterCPLX, adapterRUSE,
            adapterDOCU, adapterTIME, adapterSTOR, adapterPVOL, adapterACAP,
            adapterAEXP, adapterPCAP, adapterPEXP, adapterLTEX, adapterPCON,
            adapterTOOL, adapterSCED, adapterSITE, adapterSM;

    EditText UFP, newSLOC, reusedSLOC, IR, AA, CPM, nameProj;
    double effort, schedule, cost, total, eaf;
    LinearLayout function, linecode;
    Button result;
    ReferenceManager manager;
    Cocomo cocomo;
    ApiCocomo apiCocomo;
    CompositeDisposable disposable = new CompositeDisposable();
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        init();
        Untils.dataArray();
        getDataAdapter();
        getData();
        getToolbar();
        category();
        getResult();
    }
    private void getResult() {
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ufp = 0, newsloc=0, reusloc=0, ir=0, aa=0, cpm=0;
                if(UFP.getText().toString().trim().length()>=1)
                    ufp = Double.parseDouble(UFP.getText().toString().trim());

                if(newSLOC.getText().toString().trim().length()>=1)
                    newsloc = Double.parseDouble(newSLOC.getText().toString().trim());

                if(reusedSLOC.getText().toString().trim().length()>=1)
                    reusloc = Double.parseDouble(reusedSLOC.getText().toString().trim());

                if(IR.getText().toString().trim().length()>=1)
                    ir = Double.parseDouble(IR.getText().toString().trim());

                if(AA.getText().toString().trim().length()>=1)
                    aa = Double.parseDouble(AA.getText().toString().trim());

                if(CPM.getText().toString().trim().length()>=1)
                    cpm = Double.parseDouble(CPM.getText().toString().trim());
                eaf = Untils.arrRELY.get(listRELY.getSelectedItemPosition()).getValue()*
                        Untils.arrDATA.get(listDATA.getSelectedItemPosition()).getValue()*
                        Untils.arrCPLX.get(listCPLX.getSelectedItemPosition()).getValue()*
                        Untils.arrRUSE.get(listRUSE.getSelectedItemPosition()).getValue()*
                        Untils.arrDOCU.get(listDOCU.getSelectedItemPosition()).getValue()*
                        Untils.arrTIME.get(listTIME.getSelectedItemPosition()).getValue()*
                        Untils.arrSTOR.get(listSTOR.getSelectedItemPosition()).getValue()*
                        Untils.arrPVOL.get(listPVOL.getSelectedItemPosition()).getValue()*
                        Untils.arrACAP.get(listACAP.getSelectedItemPosition()).getValue()*
                        Untils.arrAEXP.get(listAEXP.getSelectedItemPosition()).getValue()*
                        Untils.arrPCAP.get(listPCAP.getSelectedItemPosition()).getValue()*
                        Untils.arrPEXP.get(listPEXP.getSelectedItemPosition()).getValue()*
                        Untils.arrLTEX.get(listLTEX.getSelectedItemPosition()).getValue()*
                        Untils.arrPCON.get(listPCON.getSelectedItemPosition()).getValue()*
                        Untils.arrTOOL.get(listTOOL.getSelectedItemPosition()).getValue()*
                        Untils.arrSCED.get(listSCRED.getSelectedItemPosition()).getValue()*
                        Untils.arrSITE.get(listSITE.getSelectedItemPosition()).getValue();
                if(Untils.arrSM.get(listSM.getSelectedItemPosition()).getValue() == 0){//line of code
                    category = "SLOC";
                    total = newsloc + reusloc*(aa/100 + 0.3*ir/100);

                }else{//function points
                    category = "FP";
                    total = Untils.arrLQ.get(listLQ.getSelectedItemPosition()).getValue() * ufp;
                }
                double kloc = total/1000;
                double b = 0.91 + 0.01*(
                        Untils.arrPREC.get(listPREC.getSelectedItemPosition()).getValue()+
                        Untils.arrPLEX.get(listPLEX.getSelectedItemPosition()).getValue()+
                        Untils.arrRESL.get(listRESL.getSelectedItemPosition()).getValue()+
                        Untils.arrTEAM.get(listTEAM.getSelectedItemPosition()).getValue()+
                        Untils.arrPMAT.get(listPMAT.getSelectedItemPosition()).getValue());
                effort = 2.94*Math.pow(kloc,b)*eaf;
                schedule = 3.67*Math.pow(effort,0.3179);
                cost = Math.round(effort*cpm);
                //Toast.makeText(UpdateActivity.this, total+" "+effort, Toast.LENGTH_SHORT).show();

                disposable.add(apiCocomo.updateCocomo(
                                manager.getString("_id"),
                                cocomo.get_id(),
                                nameProj.getText().toString().trim(),
                                category,
                                Untils.arrLQ.get(listLQ.getSelectedItemPosition()).getTitle(),
                                ufp,
                                newsloc,
                                reusloc,
                                ir,
                                aa,
                                Untils.arrPREC.get(listPREC.getSelectedItemPosition()).getTitle(),
                                Untils.arrPLEX.get(listPLEX.getSelectedItemPosition()).getTitle(),
                                Untils.arrRESL.get(listRESL.getSelectedItemPosition()).getTitle(),
                                Untils.arrTEAM.get(listTEAM.getSelectedItemPosition()).getTitle(),
                                Untils.arrPMAT.get(listPMAT.getSelectedItemPosition()).getTitle(),
                                Untils.arrRELY.get(listRELY.getSelectedItemPosition()).getTitle(),
                                Untils.arrDATA.get(listDATA.getSelectedItemPosition()).getTitle(),
                                Untils.arrCPLX.get(listCPLX.getSelectedItemPosition()).getTitle(),
                                Untils.arrRUSE.get(listRUSE.getSelectedItemPosition()).getTitle(),
                                Untils.arrDOCU.get(listDOCU.getSelectedItemPosition()).getTitle(),
                                Untils.arrACAP.get(listACAP.getSelectedItemPosition()).getTitle(),
                                Untils.arrPCAP.get(listPCAP.getSelectedItemPosition()).getTitle(),
                                Untils.arrPCON.get(listPCON.getSelectedItemPosition()).getTitle(),
                                Untils.arrAEXP.get(listAEXP.getSelectedItemPosition()).getTitle(),
                                Untils.arrPEXP.get(listPEXP.getSelectedItemPosition()).getTitle(),
                                Untils.arrLTEX.get(listLTEX.getSelectedItemPosition()).getTitle(),
                                Untils.arrTIME.get(listTIME.getSelectedItemPosition()).getTitle(),
                                Untils.arrSTOR.get(listSTOR.getSelectedItemPosition()).getTitle(),
                                Untils.arrPVOL.get(listPVOL.getSelectedItemPosition()).getTitle(),
                                Untils.arrTOOL.get(listTOOL.getSelectedItemPosition()).getTitle(),
                                Untils.arrSITE.get(listSITE.getSelectedItemPosition()).getTitle(),
                                Untils.arrSCED.get(listSCRED.getSelectedItemPosition()).getTitle(),
                                cpm,
                                eaf,
                                effort,
                                schedule,
                                total,
                                cost)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                resultModel -> {
                                    if(resultModel.isSuccess()){
                                        Toast.makeText(UpdateActivity.this, resultModel.getMessage()+"", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(UpdateActivity.this, throwable.getMessage()+"", Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        cocomo = (Cocomo) bundle.getSerializable("cocomo");

        if(cocomo.getSizeType().compareTo("FP") == 0){
            listSM.setSelection(1);
            category = "FP";
            UFP.setText(cocomo.getFunctionPoints()+"");
        }else{
            listSM.setSelection(0);
            category = "SLOC";
            newSLOC.setText(cocomo.getNewSize()+"");
            reusedSLOC.setText(cocomo.getReusedSize()+"");
            IR.setText(cocomo.getReusedIM()+"");
            AA.setText(cocomo.getReusedAA()+"");
        }
        nameProj.setText(cocomo.getProjectName());
        CPM.setText(cocomo.getSoftwareLaborCostPerPM()+"");

        int position = 0;
        for (int i = 0; i < Untils.arrLQ.size(); i++) {
            Rank rank = Untils.arrLQ.get(i);
            if (rank.getTitle().equals(cocomo.getLanguage())) {
                position = i;
                break;
            }
        }
        listLQ.setSelection(position);
    }
    public void category() {
        if(Untils.arrSM.get(listSM.getSelectedItemPosition()).getValue() == 1){
            function.setVisibility(View.VISIBLE);
            linecode.setVisibility(View.GONE);
        }else{
            function.setVisibility(View.GONE);
            linecode.setVisibility(View.VISIBLE);
        }
    }
    private void getDataAdapter() {
        adapterPREC = new RankAdapter2(UpdateActivity.this, Untils.arrPREC);
        listPREC.setAdapter(adapterPREC);

        adapterPLEX = new RankAdapter2(UpdateActivity.this, Untils.arrPLEX);
        listPLEX.setAdapter(adapterPLEX);

        adapterRESL = new RankAdapter2(UpdateActivity.this, Untils.arrRESL);
        listRESL.setAdapter(adapterRESL);

        adapterTEAM = new RankAdapter2(UpdateActivity.this, Untils.arrTEAM);
        listTEAM.setAdapter(adapterTEAM);

        adapterLQ = new RankAdapter2(UpdateActivity.this, Untils.arrLQ);
        listLQ.setAdapter(adapterLQ);

        adapterPMAT = new RankAdapter2(UpdateActivity.this, Untils.arrPMAT);
        listPMAT.setAdapter(adapterPMAT);

        adapterRELY = new RankAdapter2(UpdateActivity.this, Untils.arrRELY);
        listRELY.setAdapter(adapterRELY);

        adapterDATA = new RankAdapter2(UpdateActivity.this, Untils.arrDATA);
        listDATA.setAdapter(adapterDATA);

        adapterCPLX = new RankAdapter2(UpdateActivity.this, Untils.arrCPLX);
        listCPLX.setAdapter(adapterCPLX);

        adapterRUSE = new RankAdapter2(UpdateActivity.this, Untils.arrRUSE);
        listRUSE.setAdapter(adapterRUSE);

        adapterDOCU = new RankAdapter2(UpdateActivity.this, Untils.arrDOCU);
        listDOCU.setAdapter(adapterDOCU);

        adapterTIME = new RankAdapter2(UpdateActivity.this, Untils.arrTIME);
        listTIME.setAdapter(adapterTIME);

        adapterSTOR = new RankAdapter2(UpdateActivity.this, Untils.arrSTOR);
        listSTOR.setAdapter(adapterSTOR);

        adapterPVOL = new RankAdapter2(UpdateActivity.this, Untils.arrPVOL);
        listPVOL.setAdapter(adapterPVOL);

        adapterACAP = new RankAdapter2(UpdateActivity.this, Untils.arrACAP);
        listACAP.setAdapter(adapterACAP);

        adapterAEXP = new RankAdapter2(UpdateActivity.this, Untils.arrAEXP);
        listAEXP.setAdapter(adapterAEXP);

        adapterPCAP = new RankAdapter2(UpdateActivity.this, Untils.arrPCAP);
        listPCAP.setAdapter(adapterPCAP);

        adapterPEXP = new RankAdapter2(UpdateActivity.this, Untils.arrPEXP);
        listPEXP.setAdapter(adapterPEXP);

        adapterLTEX = new RankAdapter2(UpdateActivity.this, Untils.arrLTEX);
        listLTEX.setAdapter(adapterLTEX);

        adapterPCON = new RankAdapter2(UpdateActivity.this, Untils.arrPCON);
        listPCON.setAdapter(adapterPCON);

        adapterTOOL = new RankAdapter2(UpdateActivity.this, Untils.arrTOOL);
        listTOOL.setAdapter(adapterTOOL);

        adapterSCED = new RankAdapter2(UpdateActivity.this, Untils.arrSCED);
        listSCRED.setAdapter(adapterSCED);

        adapterSITE = new RankAdapter2(UpdateActivity.this, Untils.arrSITE);
        listSITE.setAdapter(adapterSITE);

        adapterSM = new RankAdapter2(UpdateActivity.this, Untils.arrSM);
        listSM.setAdapter(adapterSM);

    }
    private void init() {
        toolbar = findViewById(R.id.toolBar);
        listSM = findViewById(R.id.listSM);
        UFP = findViewById(R.id.inputUFP);
        listLQ = findViewById(R.id.listLanguage);
        newSLOC = findViewById(R.id.newSLOC);
        reusedSLOC = findViewById(R.id.oldSLOC);
        IR = findViewById(R.id.inputIR);
        AA = findViewById(R.id.inputAA);
        listPREC = findViewById(R.id.listPREC);
        listPLEX = findViewById(R.id.listFLEX);
        listRESL = findViewById(R.id.listRESL);
        listTEAM = findViewById(R.id.listTEAM);
        listPMAT = findViewById(R.id.listPMAT);
        listRELY = findViewById(R.id.listRELY);
        listDATA = findViewById(R.id.listDATA);
        listCPLX = findViewById(R.id.listCPLX);
        listRUSE = findViewById(R.id.listRUSE);
        listDOCU = findViewById(R.id.listDOCU);
        listTIME = findViewById(R.id.listTIME);
        listSTOR = findViewById(R.id.listSTOR);
        listPVOL = findViewById(R.id.listPVOL);
        listACAP = findViewById(R.id.listACAP);
        listAEXP = findViewById(R.id.listAEXP);
        listPCAP = findViewById(R.id.listPCAP);
        listPEXP = findViewById(R.id.listPEXP);
        listLTEX = findViewById(R.id.listLTEX);
        listPCON = findViewById(R.id.listPCON);
        listTOOL = findViewById(R.id.listTOOL);
        listSCRED = findViewById(R.id.listSCED);
        listSITE = findViewById(R.id.listSITE);
        CPM = findViewById(R.id.inputCPM);
        result = findViewById(R.id.result);
        function = findViewById(R.id.function);
        linecode = findViewById(R.id.linecode);
        nameProj = findViewById(R.id.inputName);
        manager = new ReferenceManager(UpdateActivity.this);
        apiCocomo = RetrofitClient.getRetrofit(Untils.BASE_URL).create(ApiCocomo.class);
    }

    private void getToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Update Project");
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        super.onDestroy();
    }
}