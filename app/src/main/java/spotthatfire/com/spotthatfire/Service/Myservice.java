package spotthatfire.com.spotthatfire.Service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;
import spotthatfire.com.spotthatfire.Model.model2;
import spotthatfire.com.spotthatfire.Remote.Common;

public class Myservice extends IntentService
{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Myservice(String name) {
        super(name);
    }

    public Myservice()
    {
        super("myservice");
    }

    public static boolean work = false;

    public static List<String> true_id =new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

   @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        //Toast.makeText(getApplicationContext(), "Services started", Toast.LENGTH_SHORT).show();

        while(work)
        {

           // Toast.makeText(getApplicationContext(), "Services started", Toast.LENGTH_SHORT).show();

            retrofit2.Call<List<model2>> call = Common.getApi().getnotify();

            call.enqueue(new Callback<List<model2>>() {
                @Override
                public void onResponse(retrofit2.Call<List<model2>> call, Response<List<model2>> response) {


                    if (response.code() != 200)
                    {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        return;
                    }

                   // Toast.makeText(getApplicationContext(), "request started", Toast.LENGTH_SHORT).show();

                    List<model2> list = response.body();

                    for (int i = 0; i < list.size(); i++)
                    {
                        final model2 m = list.get(i);

                        boolean notified = m.isNotified();

                        if (!notified)
                        {
                            Intent intent = new Intent();

                            intent.setAction("spotthatfire.com.Broadcast");

                            intent.putExtra("message", m.getMessage());
                            intent.putExtra("deg",m.getWind().getDeg());
                            intent.putExtra("speed",m.getWind().getSpeed());

                            sendBroadcast(intent);


                            Intent intent2 = new Intent("GPSLocationUpdates");
                            Bundle b = new Bundle();
                            b.putParcelable("Object",m);
                            intent2.putExtra("Object",b);
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent2);

                            true_id.add(m.getId());
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<List<model2>> call, Throwable t)
                {
                    Toast.makeText(getApplicationContext(), "Error 2", Toast.LENGTH_SHORT).show();
                }
            });


            makenotificationtrue(true_id);
            true_id.clear();


            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void makenotificationtrue(List<String> id)
    {
        if( true_id != null)
        {
           // Toast.makeText(getApplicationContext(), "not null", Toast.LENGTH_SHORT).show();
            for (int i = 0; i<id.size(); i++)
            {
                Call<model2> maketrue = Common.getApi().makeNotificationTrue(id.get(i));
                maketrue.enqueue(new Callback<model2>() {
                    @Override
                    public void onResponse(Call<model2> call, Response<model2> response)
                    {
                       // if (response.code() == 200)
                           // Toast.makeText(getApplicationContext(), "changed to notified", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<model2> call, Throwable t) {

                    }
                });
            }
        }
    }

}
