package com.example.cocomo.retrofit;

import com.example.cocomo.model.CocomoModel;
import com.example.cocomo.model.ResultModel;
import com.example.cocomo.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiCocomo {

    @POST("auth/register")
    @FormUrlEncoded
    Observable<ResultModel> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("auth/login")
    @FormUrlEncoded
    Observable<UserModel> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("construction/list-construction/{idUser}/")
    Observable<CocomoModel> getCocomo(
            @Path("idUser") String id
    );

    @DELETE("construction/delete-construction/{idUser}/{idCon}")
    Observable<ResultModel> delete(
            @Path("idUser") String idUser,
            @Path("idCon") String idCon
    );

    @POST("construction/save-construction/{idUser}/")
    @FormUrlEncoded
    Observable<ResultModel> addCocomo(
            @Path("idUser") String user,
            @Field("projectName") String projectName,
            @Field("sizeType") String sizeType,
            @Field("language") String language,
            @Field("functionPoints") double functionPoints,
            @Field("newSize") double newSize,
            @Field("reusedSize") double reusedSize,
            @Field("reusedIM") double reusedIM,
            @Field("reusedAA") double reusedAA,
            @Field("PREC") String PREC,
            @Field("FLEX") String FLEX,
            @Field("RESL") String RESL,
            @Field("TEAM") String TEAM,
            @Field("PMAT") String PMAT,
            @Field("RELY") String RELY,
            @Field("DATA") String DATA,
            @Field("CPLX") String CPLX,
            @Field("RUSE") String RUSE,
            @Field("DOCU") String DOCU,
            @Field("ACAP") String ACAP,
            @Field("PCAP") String PCAP,
            @Field("PCON") String PCON,
            @Field("AEXP") String AEXP,
            @Field("PEXP") String PEXP,
            @Field("LTEX") String LTEX,
            @Field("TIME") String TIME,
            @Field("STOR") String STOR,
            @Field("PVOL") String PVOL,
            @Field("TOOL") String TOOL,
            @Field("SITE") String SITE,
            @Field("SCED") String SCED,
            @Field("softwareLaborCostPerPM") double softwareLaborCostPerPM,
            @Field("softwareEAF") double softwareEAF,
            @Field("softwareEffort") double softwareEffort,
            @Field("softwareSchedule") double softwareSchedule,
            @Field("totalEquivalentSize") double totalEquivalentSize,
            @Field("cost") double cost
    );

    @PUT("construction/update-construction/{idUser}/{idCon}")
    @FormUrlEncoded
    Observable<ResultModel> updateCocomo(
            @Path("idUser") String user,
            @Path("idCon") String idCon,
            @Field("projectName") String projectName,
            @Field("sizeType") String sizeType,
            @Field("language") String language,
            @Field("functionPoints") double functionPoints,
            @Field("newSize") double newSize,
            @Field("reusedSize") double reusedSize,
            @Field("reusedIM") double reusedIM,
            @Field("reusedAA") double reusedAA,
            @Field("PREC") String PREC,
            @Field("FLEX") String FLEX,
            @Field("RESL") String RESL,
            @Field("TEAM") String TEAM,
            @Field("PMAT") String PMAT,
            @Field("RELY") String RELY,
            @Field("DATA") String DATA,
            @Field("CPLX") String CPLX,
            @Field("RUSE") String RUSE,
            @Field("DOCU") String DOCU,
            @Field("ACAP") String ACAP,
            @Field("PCAP") String PCAP,
            @Field("PCON") String PCON,
            @Field("AEXP") String AEXP,
            @Field("PEXP") String PEXP,
            @Field("LTEX") String LTEX,
            @Field("TIME") String TIME,
            @Field("STOR") String STOR,
            @Field("PVOL") String PVOL,
            @Field("TOOL") String TOOL,
            @Field("SITE") String SITE,
            @Field("SCED") String SCED,
            @Field("softwareLaborCostPerPM") double softwareLaborCostPerPM,
            @Field("softwareEAF") double softwareEAF,
            @Field("softwareEffort") double softwareEffort,
            @Field("softwareSchedule") double softwareSchedule,
            @Field("totalEquivalentSize") double totalEquivalentSize,
            @Field("cost") double cost
    );
}
