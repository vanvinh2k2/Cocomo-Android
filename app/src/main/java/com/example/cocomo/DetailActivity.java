package com.example.cocomo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cocomo.model.Cocomo;

public class DetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtSize, txtSizeMethod, txtLanguage, txtnewSloc, txtReusedSloc,
            txtIR, txtAA, txtPREC, txtFLEX, txtRESL, txtTEAM, txtPMAT, txtRELY,
            txtDATA, txtCPLX, txtRUSE, txtDOCU, txtACAP, txtPCAP, txtPCON, txtAEXP,
            txtPEXP, txtTIME, txtSTOR, txtPVOL, txtTOOL, txtSITE, txtSCED, txtCPM,
            txtLTEX, schedual, effort, cost, SLOC, EAF;
    Cocomo cocomo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        getData();
        getToolBar();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        cocomo = (Cocomo) bundle.getSerializable("cocomo");

        if(cocomo.getSizeType().compareTo("FP") == 0){
            txtSize.setText("Unadjusted Function Point: "+cocomo.getFunctionPoints());
            txtLanguage.setText("Language: "+cocomo.getLanguage());
            txtSize.setVisibility(View.VISIBLE);
            txtLanguage.setVisibility(View.VISIBLE);
        }else{
            txtnewSloc.setText("New SLOC: "+cocomo.getNewSize());
            txtReusedSloc.setText("Reused SLOC: "+cocomo.getReusedSize());
            txtIR.setText("% Integration Required: "+cocomo.getReusedIM());
            txtAA.setText("% Assessment and Assimilation (0% - 8%): "+cocomo.getReusedAA());
            txtnewSloc.setVisibility(View.VISIBLE);
            txtReusedSloc.setVisibility(View.VISIBLE);
            txtIR.setVisibility(View.VISIBLE);
            txtAA.setVisibility(View.VISIBLE);
        }

        txtSizeMethod.setText("Sizing Method: "+cocomo.getSizeType());
        txtPREC.setText("Precedentedness: "+cocomo.getPREC());
        txtFLEX.setText("Development Flexibility: "+cocomo.getFLEX());
        txtRESL.setText("Architecture / Risk Resolution: "+cocomo.getRESL());
        txtTEAM.setText("Team Cohesion: "+cocomo.getTEAM());
        txtPMAT.setText("Process Maturity: "+cocomo.getPMAT());
        txtRELY.setText("Required Software Reliability: "+cocomo.getRELY());
        txtDATA.setText("Data Base Size: "+cocomo.getDATA());
        txtCPLX.setText("Product Complexity: "+cocomo.getCPLX());
        txtRUSE.setText("Developed for Reusability: "+cocomo.getRUSE());
        txtDOCU.setText("Documentation Match to Lifecycle Needs: "+cocomo.getDOCU());
        txtACAP.setText("Analyst Capability"+cocomo.getACAP());
        txtPCAP.setText("Programmer Capability: "+cocomo.getPCAP());
        txtPCON.setText("Personnel Continuity: "+cocomo.getPCON());
        txtAEXP.setText("Application Experience: "+cocomo.getAEXP());
        txtPEXP.setText("Platform Experience: "+cocomo.getPEXP());
        txtLTEX.setText("Language and Toolset Experience: "+cocomo.getLTEX());
        txtTIME.setText("Time Constraint: "+cocomo.getTIME());
        txtSTOR.setText("Storage Constraint: "+cocomo.getSTOR());
        txtPVOL.setText("Platform Volatility: "+cocomo.getPVOL());
        txtTOOL.setText("Use of Software Tools: "+cocomo.getTOOL());
        txtSITE.setText("Multisite Development: "+cocomo.getSITE());
        txtSCED.setText("Required Development Schedule: "+cocomo.getSCED());
        txtCPM.setText("Cost per Person-Month (Dollars): "+cocomo.getSoftwareLaborCostPerPM());

        schedual.setText("Schedual = "+Math.round(cocomo.getSoftwareSchedule()*10.0)/10.0+" Months");
        effort.setText("Effort =  "+Math.round(cocomo.getSoftwareEffort()*10.0)/10.0+" Person-months");
        cost.setText("Cost = $"+Math.round(cocomo.getCost()));
        EAF.setText("Effort Adjustment Factor (EAF) ="+cocomo.getSoftwareEAF());
        SLOC.setText("Total Equivalent Size = "+cocomo.getTotalEquivalentSize()+ " SLOC");
    }

    private void getToolBar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(cocomo.getProjectName());
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        txtSize = findViewById(R.id.txtSize);
        txtSizeMethod = findViewById(R.id.txtSM);
        txtLanguage = findViewById(R.id.txtLanguage);
        txtnewSloc = findViewById(R.id.txtNewSLOC);
        txtReusedSloc = findViewById(R.id.txtoldSLOC);
        txtIR = findViewById(R.id.txtIR);
        txtAA = findViewById(R.id.txtAA);
        txtPREC = findViewById(R.id.txtPREC);
        txtFLEX = findViewById(R.id.txtFLEX);
        txtRESL = findViewById(R.id.txtRESL);
        txtTEAM = findViewById(R.id.txtTEAM);
        txtPMAT = findViewById(R.id.txtPMAT);
        txtRELY = findViewById(R.id.txtRELY);
        txtDATA = findViewById(R.id.txtDATA);
        txtCPLX = findViewById(R.id.txtCPLX);
        txtRUSE = findViewById(R.id.txtRUSE);
        txtDOCU = findViewById(R.id.txtDOCU);
        txtACAP = findViewById(R.id.txtACAP);
        txtPCAP = findViewById(R.id.txtPCAP);
        txtPCON = findViewById(R.id.txtPCON);
        txtAEXP = findViewById(R.id.txtAEXP);
        txtPEXP = findViewById(R.id.txtPEXP);
        txtTIME = findViewById(R.id.txtTIME);
        txtSTOR = findViewById(R.id.txtSTOR);
        txtPVOL = findViewById(R.id.txtPVOL);
        txtTOOL = findViewById(R.id.txtTOOL);
        txtSITE = findViewById(R.id.txtSITE);
        txtSCED = findViewById(R.id.txtSCED);
        txtLTEX = findViewById(R.id.txtLTEX);
        txtCPM = findViewById(R.id.txtCPM);
        schedual = findViewById(R.id.txtSchedual);
        cost = findViewById(R.id.txtCost);
        effort = findViewById(R.id.txtEffort);
        EAF = findViewById(R.id.txtEAF);
        SLOC = findViewById(R.id.txtSLOC);
    }
}