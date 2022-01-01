package utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class DnslogUtils {
    private static final String url = "http://www.dnslog.cn/";
    public static String  subdomain="";
    private static final HashMap<String,String> cookies= new HashMap<String, String>();
    public static void getSubdomain()
    {
        HttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url+"getdomain.php");
        HttpResponse response=null;
        try {

            response=httpClient.execute(httpGet);
            HttpEntity httpEntity=response.getEntity();
            subdomain=EntityUtils.toString(httpEntity);
            System.out.println(subdomain);
            Header[] headers=response.getHeaders("Set-Cookie");
            for (Header header:headers) {
                System.out.println(header.getValue());
                cookies.put("Cookie",header.getValue());
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static boolean getRecord()
    { HttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url+"getrecords.php");
        httpGet.addHeader("Cookie",cookies.get("Cookie"));
        HttpResponse response=null;
        try {

            response=httpClient.execute(httpGet);
            HttpEntity httpEntity=response.getEntity();
            String records=EntityUtils.toString(httpEntity);
            System.out.println(records);
            if(records.length()>4){
                return true;
            }
            else{
                return false;
            }



        }catch (Exception e)
        {
            e.printStackTrace();
            return  false;
        }

    }

}
