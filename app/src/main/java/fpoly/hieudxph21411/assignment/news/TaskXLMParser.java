package fpoly.hieudxph21411.assignment.news;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

import fpoly.hieudxph21411.assignment.R;
import fpoly.hieudxph21411.assignment.adapter.NewsAdapter;
import fpoly.hieudxph21411.assignment.model.TinTuc;

public class TaskXLMParser extends AsyncTask<String, Void, ArrayList<TinTuc>> {
    NewsActivity activity;
    RecyclerView rcv;

    public TaskXLMParser(NewsActivity activity, RecyclerView rcv) {
        this.activity = activity;
        this.rcv = rcv;
    }
    ArrayList<TinTuc> list = null;
    InputStream stream = null;

    @Override
    protected ArrayList<TinTuc> doInBackground(String... strings) {
        try {
            stream = downloadUrl(strings[0]);
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            XLMParserHandler parserHandler = new XLMParserHandler();
            xmlReader.setContentHandler(parserHandler);
            xmlReader.parse(new InputSource(stream));
            list = (ArrayList<TinTuc>) parserHandler.getItemList();
            Log.d("TAG", "itemList: " + parserHandler.getItemList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<TinTuc> list) {
        super.onPostExecute(list);

        NewsAdapter adapter = new NewsAdapter(list, activity, R.layout.item_rcv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
    }
    private InputStream downloadUrl(String urlString) throws IOException {
        java.net.URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

}
