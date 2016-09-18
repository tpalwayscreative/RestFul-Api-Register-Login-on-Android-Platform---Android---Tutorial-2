package co.tpcreatice.restfulapi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncTaskUser extends AsyncTask<String,String,String> {


    private ProgressDialog pDialog ;
    private Activity activity ;
    private User user ;
    private Boolean isSignIn = false;
    private SharedPreferences pre ;
    private SharedPreferences.Editor editor ;

    public AsyncTaskUser(Activity activity , User user,Boolean isSignIn){

        this.activity = activity ;
        this.user = user;
        this.isSignIn = isSignIn;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage(activity.getResources().getString(R.string.txt_Show));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {

        RequestParams params = new RequestParams();
        params.add("name",user.getName());
        params.add("email",user.getEmail());
        params.add("password",user.getPassword());

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(strings[0],params,new AsyncHttpResponseHandler(){


            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pDialog.dismiss();
                    }
                });

            }

            @Override
            public void onFailure(Throwable error, final String content) {
                super.onFailure(error, content);


                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(activity,content,Toast.LENGTH_SHORT).show();


                    }
                });

            }

            @Override
            public void onSuccess(int statusCode,final String content) {
                super.onSuccess(statusCode, content);

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity,content,Toast.LENGTH_SHORT).show();
                    }
                });

                if(isSignIn)
                {
                    try {

                        JSONObject json = new JSONObject(content);
                        String name = json.getString("name");

                        Intent i = new Intent(activity,MainActivity.class);
                        pre = activity.getSharedPreferences("store",activity.MODE_PRIVATE);
                        editor = pre.edit();
                        editor.putString("name",name);
                        editor.commit();
                        activity.startActivity(i);

                    }
                    catch (JSONException e){

                    }

                }
                else
                {
                    activity.finish();

                }



            }

        });

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
