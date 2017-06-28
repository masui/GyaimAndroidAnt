# Gyaim

* Android用の日本語IMEです。
* まだ雛形しかありませんが。
* antでMakeします。オールドですみません。

# 問題

* LocalDict.javaをテストするためにAndroid依存部をなくしている
* そのためにMessageというクラスを作ってLog()のかわりにしている
* テスト時と実機運用で異なるMessage.javaを使うというひどい方法になってる