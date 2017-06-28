package com.pitecan.gyaim;

import android.os.AsyncTask;
import android.util.Log;

//                                       <Params, Progress, Result> で型を指定する
public class SearchTask extends AsyncTask<String, Integer, Candidate[]> {

    private CandView candView;
    private boolean useGoogle;

    public SearchTask(CandView candView, boolean useGoogle){
	this.candView = candView;
	this.useGoogle = useGoogle;
    }

    public void progress(int i){
	publishProgress(0);
    }

    protected Candidate[] doInBackground(String... searchParams){ // Result の型を返す 引数はParamsの型
	Candidate[] res;
	String pat = searchParams[0];
	String word = searchParams[1];
	if(pat != ""){
	    res = Search.search(pat,word,useGoogle,this); // this.cancel()が呼ばれるとthis.isCancelled()がtrueになる
	    if(isCancelled()){
	    }
	}
	else {
	    Search.ncands = 0;
	    Search.addCandidateWithLevel("が","ga",0);
	    Search.addCandidateWithLevel("は","ha",0);
	    Search.addCandidateWithLevel("の","no",0);
	    Search.addCandidateWithLevel("に","ni",0);
	    Search.addCandidateWithLevel("を","wo",0);
	    /*
	    Search.addCandidateWithLevel("と","to",0);
	    Search.addCandidateWithLevel("も","mo",0);
	    Search.addCandidateWithLevel("へ","he",0);
	    Search.addCandidateWithLevel("で","de",0);
	    Search.addCandidateWithLevel("ね","ne",0);
	    Search.addCandidateWithLevel("か","ka",0);
	    Search.addCandidateWithLevel("で","de",0);
	    Search.addCandidateWithLevel("だ","da",0);
	    Search.addCandidateWithLevel("です","desu",0);
	    Search.addCandidateWithLevel("でした","deshita",0);
	    */
	    Search.addCandidateWithLevel("。",".",0);
	    Search.addCandidateWithLevel("、",",",0);
	    res = Search.candidates;
	}

	// Log.v("Gyaim","doInBackground end");
	return res;
    }

    private void updateView(){
	int nbuttons = 0;
	if(Search.ncands > 0){
	    for(;nbuttons<CandView.candButtons.length && nbuttons <Search.ncands;nbuttons++){
		CandView.candButtons[nbuttons].text = Search.candidates[nbuttons].word;
		CandView.candButtons[nbuttons].pat = Search.candidates[nbuttons].pat;
	    }
	}
	for(int i = nbuttons;i<CandView.candButtons.length;i++){
	    CandView.candButtons[i].text = "";
	    CandView.candButtons[i].pat = "";
	}
	////candView.drawDefault();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) { // Progressの型
	// setProgressPercent(progress[0]);
	//
	// 検索ルーチンで候補がみつかったとき適宜publishProgress()を呼ぶことにより候補表示を速くする。
	// publishProgress()が呼ばれるとこのスレッドのonProgressUpdate()が呼ばれる。
	//
	updateView();
    }

    @Override
    protected void onPostExecute(Candidate[] candidates) { // Result の型の値が引数に入る doInBackgroundの返り値
	// Log.v("Gyaim","onPostExecute");
	// ここで候補を表示する
	updateView();
    }

    protected void onCancelled(Candidate[] candidates){
	// Log.v("Gyaim","onCancelled");
    }
}
