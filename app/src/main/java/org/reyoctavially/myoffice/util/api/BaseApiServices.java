package org.reyoctavially.myoffice.util.api;
import org.reyoctavially.myoffice.response.ResponseAbsensi;
import org.reyoctavially.myoffice.response.ResponseCuti;
import org.reyoctavially.myoffice.response.ResponseKesehatan;
import org.reyoctavially.myoffice.response.ResponsePengajuanCuti;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiServices {
    @FormUrlEncoded
    @POST("auth")
    Call<ResponseBody> auth(@Field("email_pegawai") String email_pegawai,
                             @Field("pass_pegawai") String pass_pegawai);

    @GET("cuti/index/kode_pegawai/{kode_pegawai}")
    Call<ResponseCuti> getCuti(@Path("kode_pegawai") String kode_pegawai);

    @GET("pengajuancuti/index/kode_pegawai/{kode_pegawai}")
    Call<ResponsePengajuanCuti> getPengajuanCuti(@Path("kode_pegawai") String kode_pegawai);

    @GET("absensi/index/kode_pegawai/{kode_pegawai}")
    Call<ResponseAbsensi> getAbsensi(@Path("kode_pegawai") String kode_pegawai);

    @GET("kesehatan/index/kode_pegawai/{kode_pegawai}")
    Call<ResponseKesehatan> getKesehatan(@Path("kode_pegawai") String kode_pegawai);

    @FormUrlEncoded
    @POST("pengajuancuti")
    Call<ResponseBody> addPengajuanCuti(@Field("alasan_pengajuan_cuti") String alasan_pengajuan_cuti,
                                        @Field("tgl_mulai_cuti") String tgl_mulai_cuti,
                                        @Field("tgl_selesai_cuti") String tgl_selesai_cuti,
                                        @Field("kode_pegawai") String kode_pegawai,
                                        @Field("nip_kasi") String nip_kasi);

    @FormUrlEncoded
    @POST("kesehatan")
    Call<ResponseBody> addKesehatan(@Field("jenis_pekerjaan") String jenis_pekerjaan,
                                        @Field("suhu_tubuh_pegawai") String suhu_tubuh_pegawai,
                                        @Field("hasil_swab_pegawai") String hasil_swab_pegawai,
                                        @Field("status_vaksinasi_pegawai") String status_vaksinasi_pegawai,
                                        @Field("kode_pegawai") String kode_pegawai,
                                        @Field("nip_kasi") String nip_kasi);

    @FormUrlEncoded
    @POST("absensi")
    Call<ResponseBody> absenMasuk(@Field("kode_pegawai") String kode_pegawai,
                                  @Field("nip_kasi") String nip_kasi);

    @FormUrlEncoded
    @PUT("absensi")
    Call<ResponseBody> absenKeluar(@Field("kode_pegawai") String kode_pegawai);

}
