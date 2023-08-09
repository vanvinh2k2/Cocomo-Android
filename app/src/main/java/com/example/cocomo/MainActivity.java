package com.example.cocomo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cocomo.Const.ReferenceManager;
import com.example.cocomo.Const.Untils;
import com.example.cocomo.adapter.RankAdapter;
import com.example.cocomo.model.Rank;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner listSM, listLQ, listPREC, listPLEX, listRESL, listTEAM,
            listPMAT, listRELY, listDATA, listCPLX, listRUSE, listDOCU,
            listTIME, listSTOR, listPVOL, listACAP, listAEXP, listPCAP,
            listPEXP, listLTEX, listPCON, listTOOL, listSCRED, listSITE;

    RankAdapter adapterPREC, adapterPLEX, adapterRESL, adapterTEAM, adapterLQ,
            adapterPMAT, adapterRELY, adapterDATA, adapterCPLX, adapterRUSE,
            adapterDOCU, adapterTIME, adapterSTOR, adapterPVOL, adapterACAP,
            adapterAEXP, adapterPCAP, adapterPEXP, adapterLTEX, adapterPCON,
            adapterTOOL, adapterSCED, adapterSITE, adapterSM;

    EditText UFP, newSLOC, reusedSLOC, IR, AA, CPM, nameProj;
    LinearLayout function, linecode;
    Button result;
    ImageView img;
    Toolbar tool;
    ReferenceManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getToolBar();
        Untils.dataArray();
        getDataAdapter();
        category();
        getResult();

    }

    private void getToolBar() {
        TextView title = tool.findViewById(R.id.title);
        title.setText("COCOMO II");
        tool.inflateMenu(R.menu.menu_main);
        tool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.history){
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    startActivity(intent);
                }else if(item.getItemId() == R.id.logout){
                    manager.clear();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }

    public void category() {
        Picasso.get().load(manager.getString("avatar")).placeholder(R.drawable.user).into(img);
        if(Untils.arrSM.get(listSM.getSelectedItemPosition()).getValue() == 1){
            function.setVisibility(View.VISIBLE);
            linecode.setVisibility(View.GONE);
        }else{
            function.setVisibility(View.GONE);
            linecode.setVisibility(View.VISIBLE);
        }
    }

    private void getResult() {
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPREC.getSelectedItemPosition();
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sm", Untils.arrSM.get(listSM.getSelectedItemPosition()));
                bundle.putSerializable("prec",  Untils.arrPREC.get(listPREC.getSelectedItemPosition()));
                bundle.putSerializable("plex",  Untils.arrPLEX.get(listPLEX.getSelectedItemPosition()));
                bundle.putSerializable("resl",  Untils.arrRELY.get(listRELY.getSelectedItemPosition()));
                bundle.putSerializable("team",  Untils.arrTEAM.get(listTEAM.getSelectedItemPosition()));
                bundle.putSerializable("lq",  Untils.arrLQ.get(listLQ.getSelectedItemPosition()));
                bundle.putSerializable("pmat",  Untils.arrPMAT.get(listPMAT.getSelectedItemPosition()));
                bundle.putSerializable("rely",  Untils.arrRELY.get(listRELY.getSelectedItemPosition()));
                bundle.putSerializable("data",  Untils.arrDATA.get(listDATA.getSelectedItemPosition()));
                bundle.putSerializable("cplx",  Untils.arrCPLX.get(listCPLX.getSelectedItemPosition()));
                bundle.putSerializable("ruse",  Untils.arrRUSE.get(listRUSE.getSelectedItemPosition()));
                bundle.putSerializable("docu",  Untils.arrDOCU.get(listDOCU.getSelectedItemPosition()));
                bundle.putSerializable("time",  Untils.arrTIME.get(listTIME.getSelectedItemPosition()));
                bundle.putSerializable("stor",  Untils.arrSTOR.get(listSTOR.getSelectedItemPosition()));
                bundle.putSerializable("pvol",  Untils.arrPVOL.get(listPVOL.getSelectedItemPosition()));
                bundle.putSerializable("acap",  Untils.arrACAP.get(listACAP.getSelectedItemPosition()));
                bundle.putSerializable("aexp",  Untils.arrAEXP.get(listAEXP.getSelectedItemPosition()));
                bundle.putSerializable("pcap",  Untils.arrPCAP.get(listPCAP.getSelectedItemPosition()));
                bundle.putSerializable("pexp",  Untils.arrPEXP.get(listPEXP.getSelectedItemPosition()));
                bundle.putSerializable("ltex",  Untils.arrLTEX.get(listLTEX.getSelectedItemPosition()));
                bundle.putSerializable("pcon",  Untils.arrPCON.get(listPCON.getSelectedItemPosition()));
                bundle.putSerializable("tool",  Untils.arrTOOL.get(listTOOL.getSelectedItemPosition()));
                bundle.putSerializable("sced",  Untils.arrSCED.get(listSCRED.getSelectedItemPosition()));
                bundle.putSerializable("site",  Untils.arrSITE.get(listSITE.getSelectedItemPosition()));
                bundle.putSerializable("name", nameProj.getText().toString().trim());

                if(UFP.getText().toString().trim().length()>=1)
                    bundle.putDouble("ufp", Double.parseDouble(UFP.getText().toString().trim()));
                else bundle.putDouble("ufp", 0);

                if(newSLOC.getText().toString().trim().length()>=1)
                    bundle.putDouble("newsloc", Double.parseDouble(newSLOC.getText().toString().trim()));
                else bundle.putDouble("newsloc", 0);

                if(reusedSLOC.getText().toString().trim().length()>=1)
                    bundle.putDouble("reusloc", Double.parseDouble(reusedSLOC.getText().toString().trim()));
                else bundle.putDouble("reusloc", 0);

                if(IR.getText().toString().trim().length()>=1)
                    bundle.putDouble("ir", Double.parseDouble(IR.getText().toString().trim()));
                else bundle.putDouble("ir", 0);

                if(AA.getText().toString().trim().length()>=1)
                    bundle.putDouble("aa", Double.parseDouble(AA.getText().toString().trim()));
                else bundle.putDouble("aa", 0);

                if(CPM.getText().toString().trim().length()>=1)
                    bundle.putDouble("cpm", Double.parseDouble(CPM.getText().toString().trim()));
                else bundle.putDouble("cpm", 0);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });

    }

    private void getDataAdapter() {
        adapterPREC = new RankAdapter(MainActivity.this, Untils.arrPREC);
        listPREC.setAdapter(adapterPREC);

        adapterPLEX = new RankAdapter(MainActivity.this, Untils.arrPLEX);
        listPLEX.setAdapter(adapterPLEX);

        adapterRESL = new RankAdapter(MainActivity.this, Untils.arrRESL);
        listRESL.setAdapter(adapterRESL);

        adapterTEAM = new RankAdapter(MainActivity.this, Untils.arrTEAM);
        listTEAM.setAdapter(adapterTEAM);

        adapterLQ = new RankAdapter(MainActivity.this, Untils.arrLQ);
        listLQ.setAdapter(adapterLQ);

        adapterPMAT = new RankAdapter(MainActivity.this, Untils.arrPMAT);
        listPMAT.setAdapter(adapterPMAT);

        adapterRELY = new RankAdapter(MainActivity.this, Untils.arrRELY);
        listRELY.setAdapter(adapterRELY);

        adapterDATA = new RankAdapter(MainActivity.this, Untils.arrDATA);
        listDATA.setAdapter(adapterDATA);

        adapterCPLX = new RankAdapter(MainActivity.this, Untils.arrCPLX);
        listCPLX.setAdapter(adapterCPLX);

        adapterRUSE = new RankAdapter(MainActivity.this, Untils.arrRUSE);
        listRUSE.setAdapter(adapterRUSE);

        adapterDOCU = new RankAdapter(MainActivity.this, Untils.arrDOCU);
        listDOCU.setAdapter(adapterDOCU);

        adapterTIME = new RankAdapter(MainActivity.this, Untils.arrTIME);
        listTIME.setAdapter(adapterTIME);

        adapterSTOR = new RankAdapter(MainActivity.this, Untils.arrSTOR);
        listSTOR.setAdapter(adapterSTOR);

        adapterPVOL = new RankAdapter(MainActivity.this, Untils.arrPVOL);
        listPVOL.setAdapter(adapterPVOL);

        adapterACAP = new RankAdapter(MainActivity.this, Untils.arrACAP);
        listACAP.setAdapter(adapterACAP);

        adapterAEXP = new RankAdapter(MainActivity.this, Untils.arrAEXP);
        listAEXP.setAdapter(adapterAEXP);

        adapterPCAP = new RankAdapter(MainActivity.this, Untils.arrPCAP);
        listPCAP.setAdapter(adapterPCAP);

        adapterPEXP = new RankAdapter(MainActivity.this, Untils.arrPEXP);
        listPEXP.setAdapter(adapterPEXP);

        adapterLTEX = new RankAdapter(MainActivity.this, Untils.arrLTEX);
        listLTEX.setAdapter(adapterLTEX);

        adapterPCON = new RankAdapter(MainActivity.this, Untils.arrPCON);
        listPCON.setAdapter(adapterPCON);

        adapterTOOL = new RankAdapter(MainActivity.this, Untils.arrTOOL);
        listTOOL.setAdapter(adapterTOOL);

        adapterSCED = new RankAdapter(MainActivity.this, Untils.arrSCED);
        listSCRED.setAdapter(adapterSCED);

        adapterSITE = new RankAdapter(MainActivity.this, Untils.arrSITE);
        listSITE.setAdapter(adapterSITE);

        adapterSM = new RankAdapter(MainActivity.this, Untils.arrSM);
        listSM.setAdapter(adapterSM);

    }
    public void init(){
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
        img = findViewById(R.id.img);
        nameProj = findViewById(R.id.inputName);
        manager = new ReferenceManager(MainActivity.this);
        tool = findViewById(R.id.toolbarmain);
    }
}