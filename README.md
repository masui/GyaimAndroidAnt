# Gyaim

* Android用の日本語IMEです。
* MacのGyaimとだいたい同じ感じで使えます。
* antでMakeします。オールドですみません。
* 右シフトキーで日本語モードon/offします。

### TODO

* 確定後に助詞を候補として表示
* 候補ウィンドウをカーソルのそばに表示
* 候補単語をタッチで選択できるように
* 外付けキーボードで動くように

### 問題

* テストについて
    * LocalDict.javaをテストするためにAndroidに依存しないソースにしている
    * そのためにMessageというクラスを作ってLog()のかわりにしている
    * テスト時と実機運用で異なるMessage.javaを使う

* コンパイラが古い問題
    * ```for(String word: words){```みたいな構文が使えない
    * コンパイラが古いからだと思うがどうすればいいのかわからない
    * 別に困らないから良いんだけど