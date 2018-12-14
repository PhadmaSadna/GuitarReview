package com.someone.someone.myuasnew.Rest;

import com.someone.someone.myuasnew.Model.GetGuitar;
import com.someone.someone.myuasnew.Model.GetLogin;
import com.someone.someone.myuasnew.Model.GetReview;
import com.someone.someone.myuasnew.Model.GetUser;
import com.someone.someone.myuasnew.Model.PostPutDelReview;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {

        @GET("reviewguitar/review")
        Call<GetReview> getReview();

        @FormUrlEncoded
        @POST("reviewguitar/review")
        Call<PostPutDelReview> postReview
                (@Field("user_id_fk") String idUser,
                 @Field("jumlah_vote") String jmlVote,
                 @Field("review_id_fk") String idReview);

        @FormUrlEncoded
        @PUT("reviewguitar/review")
        Call<PostPutDelReview> putReview(
                @Field("id_transac") String idTransac,
                @Field("user_id_fk") String idUser,
                @Field("review_id_fk") String idReview,
                @Field("jml_vote") String jmlVote);

        @FormUrlEncoded
        @HTTP(method = "DELETE", path = "reviewguitar/review", hasBody = true)
        Call<PostPutDelReview> deleteReview(@Field("id_transac") String idTransac);

        /********* PEMBELI *********/
        @GET("loginuser/login")
        Call<GetUser> getUser();

        @Multipart
        @POST("loginuser/login")
        Call<GetUser> postUser(
                @Part MultipartBody.Part file,
                @Part("fullname") RequestBody fullname,
                @Part("username") RequestBody username,
                @Part("password") RequestBody password,
                @Part("email") RequestBody email,
                @Part("action") RequestBody action
        );

        @Multipart
        @POST("loginuser/login")
        Call<GetUser> putUser(
                @Part MultipartBody.Part file,
                @Part("fullname") RequestBody fullname,
                @Part("username") RequestBody username,
                @Part("password") RequestBody password,
                @Part("email") RequestBody email,
                @Part("action") RequestBody action
        );

        @Multipart
        @POST("loginuser/login")
        Call<GetUser> deleteUser(
                @Part("user_id") RequestBody idUser,
                @Part("action") RequestBody action);

        /********* TIKET *********/
        @GET("guitar/guitar")
        Call<GetGuitar> getGuitar();

        @FormUrlEncoded
        @POST("guitar/guitar")
        Call<GetGuitar> getGuitarForUser(
                @Field("user_id") String idUser
        );

        @FormUrlEncoded
        @POST("guitar/guitar")
        Call<GetGuitar> getGuitar(
                @Field("user_id") String idUser
        );

        @FormUrlEncoded
        @POST("guitar/guitar")
        Call<GetGuitar> postGuitar(
                @Field("review_tittle") String reviewTittle,
                @Field("merk_type") String merkTyype,
                @Field("jns_guitar") String jnsGuitar,
                @Field("review_detail") String reviewDetail,
                @Field("harga") String harga,
                @Field("action") String action
        );

        @FormUrlEncoded
        @POST("guiter/guitar")
        Call<GetGuitar> putGuitar(
                @Field("review_tittle") String reviewTittle,
                @Field("merk_type") String merkTyype,
                @Field("jns_guitar") String jnsGuitar,
                @Field("review_detail") String reviewDetail,
                @Field("harga") String harga,
                @Field("action") String action
        );

        @FormUrlEncoded
        @POST("guitar/guitar")
        Call<GetGuitar> deleteGuitar(
                @Field("review_id") String idReview,
                @Field("action") String action);

        /********* Login *********/
        // Ingat, tambahkan dulu fungsi login_post() pada controller Pembeli di REST server
        @FormUrlEncoded
        @POST("loginuser/login")
        Call<GetLogin> loginUser(
                @Field("username") String username,
                @Field("password") String password);

    }
