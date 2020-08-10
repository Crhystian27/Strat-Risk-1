package co.mba.strat_risk.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.data.dto.ServerError;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (!isOnline()){
            backgroundThreadShortToast(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(R.string.toast_no_internet_connection));
        }
        Request request = chain.request();
        Response response = chain.proceed(request);

        if ((request.method().equals("POST") || request.method().equals("PATCH") || request.method().equals("PUT"))
                && response.code() >= 400 && response.code() < 600) {
            // Server issues
            String body = getResponse(response);
            if("application/json".equals(response.header("Content-Type"))) {
                ServerError serverError = new Gson().fromJson(body, ServerError.class);
                Log.e("ERROR_INTERCEPTED", new Gson().toJson(serverError, serverError.getClass()));
                Log.e("ERROR_INTERCEPTED_URL", request.url().toString());
                //backgroundThreadShortToast(BaseApplication.getAppContext(), "Error: " + response.code() + " mira el Logcat");
            }
            else {
                System.out.println("ERROR--------------------------BEGIN");
                System.out.println("URL: " + request.url().toString());
                System.out.println("STATUS: " + response.code());
                System.out.println(body);
                System.out.println("ERROR--------------------------END");
            }
        }

        return response;
    }

    private String getResponse(Response response){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void backgroundThreadShortToast(final Context context, final String msg)
    {
        if(context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        }
    }

    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    /*public static void toast(Context context, String message)
    {
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_information, null);
        TextView textView = view.findViewById(R.id.txt_dialog_message_title);
        textView.setText(message);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }*/
}
