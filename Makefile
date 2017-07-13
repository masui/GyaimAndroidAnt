VERSIONCODE=1
VERSION=0.0.3

build: local.properties
	sed -e "s/VERSIONCODE/${VERSIONCODE}/" AndroidManifest.template | sed -e "s/VERSION/${VERSION}/" > AndroidManifest.xml
	ant debug

install:
	adb install -r bin/Gyaim-debug.apk
uninstall:
	adb uninstall com.pitecan.gyaim
debug:
	adb logcat | grep Gyaim
clean:
	/bin/rm -r -f bin gen
	cd test; make clean
push:
	git push pitecan.com:/home/masui/git/GyaimAndroid.git
	git push git@github.com:masui/GyaimAndroid.git
upload:
	scp bin/Gyaim-debug.apk pitecan.com:/www/www.pitecan.com/gyaim-${VERSION}.apk

#
# 辞書と検索のテスト
#
.PHONY: test
test:
	cd test; make test

#
# ゼロからテスト/ビルド
#
all: clean test build

#
# 署名してアップロード
#
publish: clean
	sed -e "s/VERSIONCODE/${VERSIONCODE}/" AndroidManifest.template | sed -e "s/VERSION/${VERSION}/" > AndroidManifest.xml
	ant release
	/bin/cp bin/Gyaim-release-unsigned.apk bin/Gyaim.apk
	jarsigner -J-Dfile.encoding=UTF8 -keystore ~/.android/masui.keystore -verbose bin/Gyaim.apk pitecan
	-/bin/rm bin/Gyaim-aligned.apk
	zipalign -v 4 bin/Gyaim.apk bin/Gyaim-aligned.apk
	scp bin/Gyaim.apk pitecan.com:/www/www.pitecan.com/GyaimAndroid
	scp bin/Gyaim.apk pitecan.com:/www/www.pitecan.com/GyaimAndroid/Gyaim-${VERSION}.apk

local.properties:
	android update project -p ./

# Gyazz.com/SlimeDictから辞書を作成!
#
dict:
	cd ../SlimeDict; make; cd ../Slime
	cp ../SlimeDict/dict.txt assets/dict.txt

# Gyazz.com/kdict から辞書を作成!
dict-old:
	ruby -I~/GyazzDict ~/GyazzDict/gyazz2dic 'kdict::リスト' 'kdict::名詞' 'kdict::固有名詞' 'kdict::増井リスト' > /tmp/tmp
	/bin/rm -r -f assets
	mkdir assets
	ruby -I~/GyazzDict ~/GyazzDict/connection2txt /tmp/tmp > assets/dict.txt
