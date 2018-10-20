package spotthatfire.com.spotthatfire.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import spotthatfire.com.spotthatfire.notification.Notificationtest1;

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        final Bundle bundle=intent.getExtras();


        if(intent.getAction().equalsIgnoreCase("spotthatfire.com.Broadcast"))
        {
            Notificationtest1 notificationtest1=new Notificationtest1();
            notificationtest1.wind_text="wind degree: "+bundle.getString("deg")+"\n"+"wind speed: "+bundle.getString("speed");
            notificationtest1.notify(context,bundle.getString("message"),123);
        }

    }
}
