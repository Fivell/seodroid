package com.seoman.seoman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

import java.net.URI;
import java.util.ArrayList;


import android.os.AsyncTask;
import com.seoman.seoman.system.IResult;
import com.seoman.seoman.ui.ItemArrayAdapter;
import com.seoman.seoman.ui.ResultItem;

public class SeoManActivity extends Activity {


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
                "LinkedInSharesCount"
        };

        for (String jobClassName : classes) {
            try {
                IResult iResult = (IResult) (Class.forName("com.seoman.seoman.jobs." + jobClassName).
                        getConstructor().newInstance());

                iResult.setUri(URI.create(input));
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

        input = inputView.getText().toString();

        if (!isUrlValid(input)) {
            Toast.makeText(this, "Url is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!input.toLowerCase().startsWith("http")) {
            input = "http://" + input;
        }

        clearState();
        initState();


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
                    new ResultItem(iResult.getName(), response, iResult.getIconName())

            );
            arrayAdapter.notifyDataSetChanged();

        }
    }

    private boolean isUrlValid(String url) {
        return android.util.Patterns.WEB_URL.matcher(url).matches();
    }

}
