package com.seodroid.seodroid;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.*;
import android.view.View;


import java.util.ArrayList;
import  android.content.Intent;
import android.widget.AdapterView.OnItemClickListener;
import android.os.AsyncTask;
import com.seodroid.seodroid.system.IResult;
import com.seodroid.seodroid.system.Link;
import com.seodroid.seodroid.ui.ItemArrayAdapter;
import com.seodroid.seodroid.ui.ResultItem;

public class SeoDroidActivity extends Activity {


    EditText inputView;
    String input;
    ListView listView;
    ItemArrayAdapter arrayAdapter;
    ArrayList<SeoTask> tasks;
    ImageButton goButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        goButton = (ImageButton)   findViewById(R.id.goButton);
        inputView = (EditText) findViewById(R.id.UrlInput);

        listView = (ListView) findViewById(R.id.listView);

        ArrayList<ResultItem> list = new ArrayList<ResultItem>();
        arrayAdapter = new ItemArrayAdapter(this,
                R.layout.row, list);
        listView.setAdapter(arrayAdapter);

        tasks = new ArrayList<SeoTask>();

    }

    public void initState() throws Exception {
        String[] classes = {
                "AlexaRank",
                "GooglePageRank",
                "GoogleIndex",
                "BingIndex",
                "Ip",
                "Dmoz",
                "WebArchive",
                "TweetsCount",
                "FacebookSharesCount",
                "LinkedInSharesCount",
                "BingIpCount",
                "GoogleBackLinks",
                "ContentLength",
                "ContentType",
                "Yandex",
                "Links"
        };

        for (String jobClassName : classes) {
            try {
                IResult iResult = (IResult) (Class.forName("com.seodroid.seodroid.jobs." + jobClassName).
                        getConstructor().newInstance());

                iResult.setLink(new Link(input));
                SeoTask seoTask = new SeoTask();
                tasks.add(seoTask);
                seoTask.execute(iResult);


            } catch (Exception e) {
                throw e;
            }

        }
    }

    public void clearState() {


        for (SeoTask task : tasks) {
            task.cancel(false);
        }
        tasks.clear();

        arrayAdapter.clear();
        arrayAdapter.notifyDataSetChanged();
    }



    public void btnGoClick(View v) throws Exception {

        input = inputView.getText().toString().trim();

        if (!isUrlValid(input)) {
            Toast.makeText(this, "Url is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!input.toLowerCase().startsWith("http")) {
            input = "http://" + input;
        }

        clearState();
        initState();
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                   openUriForItemPosition(position);

            }
        });

    }

    public void openUriForItemPosition(int position){

        String browserUrl = arrayAdapter.getItem(position).getUrl();
        if(browserUrl == null){

            return;
        }

        Toast.makeText(this,
                "Opening " +
                arrayAdapter.getItem(position).getUrl() , Toast.LENGTH_SHORT).show();

        startActivity(new Intent(Intent.ACTION_VIEW).setData( android.net.Uri.parse(arrayAdapter.getItem(position).getUrl())));

    }


    class SeoTask extends AsyncTask<IResult, Void, Void> {
        String response;
        IResult iResult;

        @Override
        protected Void doInBackground(IResult... params) {
            iResult = params[0];
            try {
                response = iResult.getResult();
            } catch (Exception e) {
                response = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (isCancelled()){
                return;
            }
            arrayAdapter.add(
                    new ResultItem(iResult.getName(), response, iResult.getIconName(), iResult.getBrowserUrl())

            );
            arrayAdapter.notifyDataSetChanged();

        }
    }

    private boolean isUrlValid(String url) {
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }

}
