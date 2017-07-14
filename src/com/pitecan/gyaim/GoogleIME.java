package com.pitecan.gyaim;

import org.json.*;
import java.net.*;

import java.io.*;

import org.json.JSONArray;

import java.util.ArrayList;

public class GoogleIME {
    //static public ArrayList<String>  convert(String q){
    static public String[] convert(String q){
        String urlstr = "http://google.co.jp/transliterate?langpair=ja-Hira%7cja&text=" + q;
        
        final int maxSuggestions = 20;
        // String[] suggestions = new String[maxSuggestions+1];
        ArrayList<String> suggestions = new ArrayList<String>();
        ////int nsuggest = 0;
        String jsonText = "[[\"\",[]]]";
        
        try {
            URL url = new URL(urlstr);
            
            // HTTP 接続オブジェクトの取得
            
            URLConnection http = url.openConnection();
            //http.setRequestMethod("GET")
            // 接続
            http.connect();
            
            // コンテンツの取得と表示
            BufferedInputStream bis = new BufferedInputStream(http.getInputStream());
            InputStreamReader inReader = new InputStreamReader(bis);
            BufferedReader bufReader = new BufferedReader(inReader);
            
            StringBuilder result = new StringBuilder();
            
            String line;
            while (true) {
                line = bufReader.readLine();
                if (line == null) break;
                result.append(line);
            }
            bufReader.close();
            
            jsonText = result.toString();
            
            //Message.message("Gyaim","jsonText = $jsonText")
            // http://www.google.co.jp/ime/cgiapi.html
            // "ここではきものをぬぐ" のようなパタンを与えたとき、
            // Google日本語入力は以下のようなJSONテキストを返す
            // [
            //   ["ここでは",
            //     ["ここでは", "個々では", "此処では"],
            //   ],
            //   ["きものを",
            //     ["着物を", "きものを", "キモノを"],
            //   ],
            //   ["ぬぐ",
            //     ["脱ぐ", "ぬぐ", "ヌグ"],
            //   ],
            // ]
            // これを読んで適当に候補を生成する
            try {
                JSONArray ja1, ja2, ja3;
                int len1, len3;
                ja1 = new JSONArray(jsonText);
                len1 = ja1.length();
                int i = 0;
                ja2 = ja1.getJSONArray(i);
                ja3 = ja2.getJSONArray(1); // 第2要素 = 変換候補
                len3 = ja3.length();
                ////for(nsuggest=0; nsuggest<len3 && nsuggest < maxSuggestions; nsuggest++){
                //    suggestions[nsuggest] = ja3.getString(nsuggest);
                //}
                for(i=0; i<len3 && i< maxSuggestions; i++){
                    suggestions.add(ja3.getString(i));
                }
                ////suggestions[nsuggest] = "";
                for(i=1; i<len1; i++){
                    ja2 = ja1.getJSONArray(i);
                    // String s = ja2.getString(0); // 第1要素 = 元の文字列
                    ja3 = ja2.getJSONArray(1); // 第2要素 = 変換候補
                    for(int j=0; j<suggestions.size(); j++){
                        suggestions.set(j,suggestions.get(j) + ja3.getString(0)); // ふたつめ以降は最初の候補を連結する
                    }
                    //for(String s: suggestions){
                    //    s += ja3.getString(0); // ふたつめ以降は最初の候補を連結する
                    //}
                }
            } catch (JSONException e) {
                Message.message("Gyaim", "JSON Exception " + e);
            }
        } catch (Exception e) {
            Message.message("Gyaim", "GoogleIME error " + e);
        }
        
        return suggestions.toArray(new String[suggestions.size()]);
        //return suggestions;
    }
}
