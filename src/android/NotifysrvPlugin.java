package com.cesc.ewater.cordovaPlugin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * 安卓的消息通知功能
 * Created by weide on 2018/6/19.
 */

public class NotifysrvPlugin extends CordovaPlugin {
    public static final String TAG = "NotifysrvPlugin";
    public static final String iconname = "launcher_icon";//icon res name

    public NotificationManager nm;
    public Context m_context;
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView webView){
        super.initialize(cordovaInterface,webView);
        m_context=this.cordova.getActivity().getApplicationContext();
        nm= (NotificationManager) m_context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if("send".equals(action)){
            //Toast.makeText(this.cordova.getActivity(),"1111",Toast.LENGTH_LONG).show();
            String title=args.getString(0);
            String text=args.getString(1);
            PendingIntent pendingIntent=PendingIntent.getActivity(this.cordova.getActivity(),
                    0,this.cordova.getActivity().getIntent(),0);
            int iconResID=m_context.getResources().getIdentifier(iconname,"drawable",m_context.getPackageName());
            Notification notification=new NotificationCompat.Builder(m_context)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(iconResID)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            nm.notify(1,notification);
            callbackContext.success();
            return true;
        }
        return false;
    }
}
