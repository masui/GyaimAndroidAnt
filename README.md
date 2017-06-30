# Gyaim

* Android用の日本語IMEです。
* MacのGyaimとだいたい同じ感じで使えます。
* antでMakeします。オールドですみません。
* 右シフトキーで日本語モードon/offします。

# 問題

* LocalDict.javaをテストするためにAndroid依存部をなくしている
* そのためにMessageというクラスを作ってLog()のかわりにしている
* テスト時と実機運用で異なるMessage.javaを使うというひどい方法になってる