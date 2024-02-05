package org.reyoctavially.myoffice.util.api;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "https://sisga.majalengkakab.go.id/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiServices getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiServices.class);
    }
}
