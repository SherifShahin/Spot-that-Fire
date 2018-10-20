package spotthatfire.com.spotthatfire.Remote;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import spotthatfire.com.spotthatfire.Model.model2;

public interface api
{

    @GET("notify")
    Call<List<model2>> getnotify();


    @Headers({"Content-Type: application/json"})
    @PUT("notify/{id}")
    Call<model2> makeNotificationTrue(
            @Path("id") String id
    );

}
