# Gyaim

* Android用の日本語IMEです。
* MacのGyaimとだいたい同じ感じで使えます。
* antでMakeします。オールドですみません。
* 右シフトキーで日本語モードon/offします。

### TODO

* 確定後に助詞を候補として表示

### 問題

* テストについて
 * LocalDict.javaをテストするためにAndroidに依存しないソースにしている
 * そのためにMessageというクラスを作ってLog()のかわりにしている
 * テスト時と実機運用で異なるMessage.javaを使うというひどい方法になってる
* ```org.apache.http```について
 * このライブラリは酷く古いらしいが、```libs/httpclient-4.1.1.jar```みたいなのを使ってしのいでいる
* コンパイラ
 * ```for(String word: words){```みたいな構文が使えない
 * コンパイラが古いからだと思うがどうすればいいのかわからない