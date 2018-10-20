package spotthatfire.com.spotthatfire.Remote;

public class Common
{

    private static  final String BASE_URL="http://192.168.43.180:5000/api/";

    public static api getApi()
    {
        return RetrofitClient.getClient(BASE_URL).create(api.class);
    }

}
