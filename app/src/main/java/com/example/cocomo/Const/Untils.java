package com.example.cocomo.Const;

import com.example.cocomo.model.Rank;

import java.util.ArrayList;
import java.util.List;

public class Untils {
    public static String BASE_URL = "http://172.25.218.1:5000/api/v1/";
    public static int CODE_GOOGLE = 1000;
    public static List<Rank> arrPREC, arrPLEX, arrRESL, arrTEAM, arrLQ, arrSM,
            arrPMAT, arrRELY, arrDATA, arrCPLX, arrRUSE, arrDOCU,
            arrTIME, arrSTOR, arrPVOL, arrACAP, arrAEXP, arrPCAP,
            arrPEXP, arrLTEX, arrPCON, arrTOOL, arrSCED, arrSITE;
    public static void dataArray() {
        arrPREC = new ArrayList<>();
        arrPREC.add(new Rank(3.72,"Nominal"));
        arrPREC.add(new Rank(6.20,"Very Low"));
        arrPREC.add(new Rank(4.96,"Low"));
        arrPREC.add(new Rank(2.48,"High"));
        arrPREC.add(new Rank(1.24,"Very High"));
        arrPREC.add(new Rank(0.00,"Extra high"));

        arrPLEX = new ArrayList<>();
        arrPLEX.add(new Rank(3.04,"Nominal"));
        arrPLEX.add(new Rank(5.07,"Very Low"));
        arrPLEX.add(new Rank(4.05,"Low"));
        arrPLEX.add(new Rank(2.03,"High"));
        arrPLEX.add(new Rank(1.01,"Very High"));
        arrPLEX.add(new Rank(0.00,"Extra high"));

        arrRESL = new ArrayList<>();
        arrRESL.add(new Rank(4.24,"Nominal"));
        arrRESL.add(new Rank(7.07,"Very Low"));
        arrRESL.add(new Rank(5.65,"Low"));
        arrRESL.add(new Rank(2.38,"High"));
        arrRESL.add(new Rank(1.41,"Very High"));
        arrRESL.add(new Rank(0.00,"Extra high"));

        arrTEAM = new ArrayList<>();
        arrTEAM.add(new Rank(3.29,"Nominal"));
        arrTEAM.add(new Rank(5.48,"Very Low"));
        arrTEAM.add(new Rank(4.38,"Low"));
        arrTEAM.add(new Rank(2.19,"High"));
        arrTEAM.add(new Rank(1.10,"Very High"));
        arrTEAM.add(new Rank(0.00,"Extra high"));

        arrLQ = new ArrayList<>();
        arrLQ.add(new Rank(53,"C++"));
        arrLQ.add(new Rank(640,"Machine Code"));
        arrLQ.add(new Rank(128,"C"));
        arrLQ.add(new Rank(91,"Pascal"));
        arrLQ.add(new Rank(53,"Java"));
        arrLQ.add(new Rank(21,"Perl"));

        arrPMAT = new ArrayList<>();
        arrPMAT.add(new Rank(4.68,"Nominal"));
        arrPMAT.add(new Rank(7.80,"Very Low"));
        arrPMAT.add(new Rank(6.24,"Low"));
        arrPMAT.add(new Rank(3.12,"High"));
        arrPMAT.add(new Rank(1.56,"Very High"));
        arrPMAT.add(new Rank(0.00,"Extra high"));

        arrRELY = new ArrayList<>();
        arrRELY.add(new Rank(1.00,"Nominal"));
        arrRELY.add(new Rank(0.82,"Very Low"));
        arrRELY.add(new Rank(0.92,"Low"));
        arrRELY.add(new Rank(1.10,"High"));
        arrRELY.add(new Rank(1.26,"Very High"));

        arrDATA = new ArrayList<>();
        arrDATA.add(new Rank(1.00,"Nominal"));
        arrDATA.add(new Rank(0.90,"Low"));
        arrDATA.add(new Rank(1.14,"High"));
        arrDATA.add(new Rank(1.28,"Very High"));

        arrCPLX = new ArrayList<>();
        arrCPLX.add(new Rank(1.00,"Nominal"));
        arrCPLX.add(new Rank(0.73,"Very Low"));
        arrCPLX.add(new Rank(0.87,"Low"));
        arrCPLX.add(new Rank(1.17,"High"));
        arrCPLX.add(new Rank(1.34,"Very High"));
        arrCPLX.add(new Rank(1.74,"Extra high"));

        arrRUSE = new ArrayList<>();
        arrRUSE.add(new Rank(1.00,"Nominal"));
        arrRUSE.add(new Rank(0.95,"Low"));
        arrRUSE.add(new Rank(1.07,"High"));
        arrRUSE.add(new Rank(1.15,"Very High"));
        arrRUSE.add(new Rank(1.24,"Extra high"));

        arrDOCU = new ArrayList<>();
        arrDOCU.add(new Rank(1.00,"Nominal"));
        arrDOCU.add(new Rank(0.81,"Very Low"));
        arrDOCU.add(new Rank(0.91,"Low"));
        arrDOCU.add(new Rank(1.11,"High"));
        arrDOCU.add(new Rank(1.23,"Very High"));

        arrTIME = new ArrayList<>();
        arrTIME.add(new Rank(1.00,"Nominal"));
        arrTIME.add(new Rank(1.11,"High"));
        arrTIME.add(new Rank(1.29,"Very High"));
        arrTIME.add(new Rank(1.63,"Extra high"));

        arrSTOR = new ArrayList<>();
        arrSTOR.add(new Rank(1.00,"Nominal"));
        arrSTOR.add(new Rank(1.05,"High"));
        arrSTOR.add(new Rank(1.17,"Very High"));
        arrSTOR.add(new Rank(1.46,"Extra high"));

        arrPVOL = new ArrayList<>();
        arrPVOL.add(new Rank(1.00,"Nominal"));
        arrPVOL.add(new Rank(0.87,"Low"));
        arrPVOL.add(new Rank(1.15,"High"));
        arrPVOL.add(new Rank(1.30,"Very High"));

        arrACAP = new ArrayList<>();
        arrACAP.add(new Rank(1.00,"Nominal"));
        arrACAP.add(new Rank(1.42,"Very Low"));
        arrACAP.add(new Rank(1.19,"Low"));
        arrACAP.add(new Rank(0.85,"High"));
        arrACAP.add(new Rank(0.71,"Very High"));

        arrAEXP = new ArrayList<>();
        arrAEXP.add(new Rank(1.00,"Nominal"));
        arrAEXP.add(new Rank(1.22,"Very Low"));
        arrAEXP.add(new Rank(1.10,"Low"));
        arrAEXP.add(new Rank(0.88,"High"));
        arrAEXP.add(new Rank(0.81,"Very High"));

        arrPCAP = new ArrayList<>();
        arrPCAP.add(new Rank(1.00,"Nominal"));
        arrPCAP.add(new Rank(1.34,"Very Low"));
        arrPCAP.add(new Rank(1.15,"Low"));
        arrPCAP.add(new Rank(0.88,"High"));
        arrPCAP.add(new Rank(0.76,"Very High"));

        arrPEXP = new ArrayList<>();
        arrPEXP.add(new Rank(1.00,"Nominal"));
        arrPEXP.add(new Rank(1.19,"Very Low"));
        arrPEXP.add(new Rank(1.09,"Low"));
        arrPEXP.add(new Rank(0.91,"High"));
        arrPEXP.add(new Rank(0.85,"Very High"));

        arrLTEX = new ArrayList<>();
        arrLTEX.add(new Rank(1.00,"Nominal"));
        arrLTEX.add(new Rank(1.20,"Very Low"));
        arrLTEX.add(new Rank(1.09,"Low"));
        arrLTEX.add(new Rank(0.91,"High"));
        arrLTEX.add(new Rank(0.84,"Very High"));

        arrPCON = new ArrayList<>();
        arrPCON.add(new Rank(1.00,"Nominal"));
        arrPCON.add(new Rank(1.29,"Very Low"));
        arrPCON.add(new Rank(1.12,"Low"));
        arrPCON.add(new Rank(0.90,"High"));
        arrPCON.add(new Rank(0.81,"Very High"));

        arrTOOL = new ArrayList<>();
        arrTOOL.add(new Rank(1.00,"Nominal"));
        arrTOOL.add(new Rank(1.17,"Very Low"));
        arrTOOL.add(new Rank(1.09,"Low"));
        arrTOOL.add(new Rank(0.90,"High"));
        arrTOOL.add(new Rank(0.78,"Very High"));

        arrSCED = new ArrayList<>();
        arrSCED.add(new Rank(1.00,"Nominal"));
        arrSCED.add(new Rank(1.43,"Very Low"));
        arrSCED.add(new Rank(1.14,"Low"));
        arrSCED.add(new Rank(1.00,"High"));
        arrSCED.add(new Rank(1.00,"Very High"));

        arrSITE = new ArrayList<>();
        arrSITE.add(new Rank(1.00,"Nominal"));
        arrSITE.add(new Rank(1.22,"Very Low"));
        arrSITE.add(new Rank(1.09,"Low"));
        arrSITE.add(new Rank(0.93,"High"));
        arrSITE.add(new Rank(0.86,"Very High"));
        arrSITE.add(new Rank(0.80,"Extra high"));

        arrSM = new ArrayList<>();
        arrSM.add(new Rank(0,"Source Lines of Code"));
        arrSM.add(new Rank(1,"Function Points"));
    }
}
