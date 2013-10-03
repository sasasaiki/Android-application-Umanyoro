package jp.saiki.newsnake;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;

public class BookImage {
	final int UMAMAXNUM=16;
	int page=0;
	int umaSpecies[]=new int[UMAMAXNUM];
	int umaHints[]=new int[UMAMAXNUM];
	//Bitmap umaImage[]=new Bitmap[UMAMAXNUM];
	Bitmap Umaimage;
	//Bitmap hatena[]=new Bitmap[UMAMAXNUM];
	int imageWidth;

	TextView text;

	Context cont;

	final int
	SYOBON=0,
	KAWAIIUMA=1,
	DEBUUMA=2,
	UMAKO=3,
	TUKEMA=4,
	SYAKIN=5,
	YASEUMA=6,
	HAKUBA=7,
	OUJI=8,
	DARK=9,
	BRAIDARU=10,
	TIGER=11,
	ITIGOUMA=12,
	HAGE=13,
	SARARIMAN=14,
	STAFF=15;




	String umaText[]=new String[UMAMAXNUM];
	String hintText[]=new String[UMAMAXNUM];
	String name[]=new String[UMAMAXNUM];
	String coment[]=new String[UMAMAXNUM];

	BookImage(Context cont){
		this.cont=cont;
		initImage();
		initText();
		initHinttext();
		initName();
		initComent();
	}

	void initImage(){
//図鑑画像
		umaSpecies[KAWAIIUMA]=R.drawable.kawaiiuma;
		umaSpecies[ITIGOUMA]=R.drawable.itigouma;
		umaSpecies[TIGER]=R.drawable.taigerumasuku;
		umaSpecies[TUKEMA]=R.drawable.tukema;
		umaSpecies[HAGE]=R.drawable.hage;
		umaSpecies[HAKUBA]=R.drawable.hakuba;
		umaSpecies[OUJI]=R.drawable.hakubanoouji;
		umaSpecies[SARARIMAN]=R.drawable.sarariumann;
		umaSpecies[SYAKIN]=R.drawable.syakinn;
		umaSpecies[SYOBON]=R.drawable.syobon;
		umaSpecies[YASEUMA]=R.drawable.yaseuma;
		umaSpecies[DEBUUMA]=R.drawable.debuuma;
		umaSpecies[UMAKO]=R.drawable.umako;
		umaSpecies[BRAIDARU]=R.drawable.umakoburaidal;
		umaSpecies[DARK]=R.drawable.darkhouse;
		umaSpecies[STAFF]=R.drawable.staff;

//ヒント画像
		umaHints[0]=R.drawable.hatena;

	}

	Bitmap getImage(int num){
		Resources res = cont.getResources();
		Umaimage=BitmapFactory.decodeResource(res, umaSpecies[num]);
		return Umaimage;
	}

	Bitmap getHatena(int num){
		Resources res = cont.getResources();
		Umaimage= BitmapFactory.decodeResource(res, umaHints[0]);
		return Umaimage;
	}

	String getName(int num){
		return name[num];
	}
	String getComent(int num){
		return coment[num];
	}

	void initText(){

		umaText[KAWAIIUMA]="めっちゃ馬。\n脚力、持久力ともに申し分なくひたすら走り続けるが、痛みに弱いためなにかにぶつかるとくじけるし足は短い。\nあわよくばグッズ化されたいと思っているがやはり足は短い。\n「…そこにはふれないでよ…」";
		umaText[ITIGOUMA]="めっちゃいちご。\nイチゴって何と組み合わせても大体かわいいよねっという作者の意図に反してなかなかに気持ち悪いできになった馬。\nしかしその見た目とは裏腹に糖度は高く酸味は程よい。";
		umaText[TIGER]="めっちゃ虎。\n子供に大人気のプロレスラー。\nとても強いが試合のない日は普通にサラリーマンをしているとかしていないとか。\n後頭部の寝癖のようなものは鋭く、像も一突きだとかなんとか。";
		umaText[TUKEMA]="めっちゃつけま。\n久しぶりに会った友人がすごく派手になっていてうわーとか思っていたのに気がついたらそっち側に引っ張りきられていたうまこ。\nあげぽよの意味も実はよく分かっていない。";
		umaText[HAGE]="めっちゃはげ。\n自らのはげを自覚し日々育毛と増毛にいそしんでいる。\n本気出しすぎてここ最近ひじきとわかめしか口にしていないので視界がかすんできているがおかげで鏡越しの毛が多く見えるので満足。";
		umaText[HAKUBA]="めっちゃしろい。\n生まれたときからずっと純白。\n他人から見ればきれいだが本人にとってはコンプレックスで一年中日焼けサロンに通っている。\n毛は日焼けしないということに彼はまだ気づいていない。";
		umaText[OUJI]="めっちゃ王子。\n純白の体と裏腹にどす黒い心を持つ。\n童話などで王子様が白馬に乗っているのが気に食わず、いつかこの権力で世界中の童話を『王子に乗った白馬の王子様』に改変するというややこしい事を企んでいる。";
		umaText[SARARIMAN]="めっちゃ社蓄。\n最近人気の某銀行マンのドラマに影響され何かしらを倍返ししたいと思い、とりあえず返事を倍の「はいはい」にしてみたらすごく叱られた。\nそういうとことか後頭部の寝癖とかが割とおちゃめ。";
		umaText[SYAKIN]="めっちゃりりしい馬。\nこの記録まで来るなんてやるじゃん！いいね！って感じの顔をしている。\nただ本気出せば２００とかいくからがんばって！っていう気持ちもなくはない。\n";
		umaText[SYOBON]="めっちゃしょぼんな馬。\n２０頭まではがんばってほしいんだよ！そこはまだ序の口なんだよ！もっと熱くなれよ！！という言葉をのどまで出してぐっとこらえるとこんな顔になる。\n「大丈夫…のんびりいこうぜ…」";
		umaText[YASEUMA]="めっちゃがりがり。\nガリ馬ーとかいてガリバーと読む。\n細さは美しさだと信じて疑わないため基本的にサプリで生活している。\nそろそろ駅前とかでモデルとかにスカウトされる予定。\n常に瀕死。";
		umaText[DEBUUMA]="めっちゃでぶ。\n意識の高いデブ。\n飲み会での最初の一言で「とりあえずカレー」と発し場を凍りつかせた経験を持つ。\nしかしその後巧みな話術で全員にカレーを”飲ませる”ことに成功したとか。";
		umaText[UMAKO]="めっちゃうまこ。\nこころ優しき乙女うまこ、少しおつむは弱い。\n今は専ら滝○クリステルのファン。\n凱旋した滝○クリステルに「お・も・て・な・し」をやってくださいと要求した記者には恥を知れといいたい。";
		umaText[BRAIDARU]="めっちゃ寿。\n合コンででたまたま知り合ったなんかよくわかんないけど金持ちなイケメンと結婚したうまこ。\n出会って三日の電撃婚だが彼女には何の迷いもない。\n「だってほら玉の輿だからうふふふ」";
		umaText[DARK]="めっちゃ暗黒界の主。\n純粋だった彼は世界の闇に飲まれ暗黒界に堕ちる…純白だったその体は心と呼応するかのように黒く染まっていった…という設定のはくばのコスプレ。\nダースベーダーとイメージがごっちゃになっている。";
		umaText[STAFF]="めっちゃ製作者。\n未熟者なのでバグとかとても心配。不具合があったら教えてやるとよろこぶ。口には出さないけどもしこのゲームが気に入ったらレビューなど書いてくれたらうれしいなとかいやしいことを考えている。口には出さないけど。";
	}

	void initHinttext(){
		hintText[SYOBON]="１～１９(ハードならその半分)で登場\nもう少しがんばってほしい";
		hintText[KAWAIIUMA]="２０～３９(ハードならその半分)で登場\n馬";
		hintText[DEBUUMA]="２０～３９(ハードならその半分)でまれに登場\nぼくはぽっちゃり系じゃない！！でぶだ！！";
		hintText[UMAKO]="４０～５９(ハードならその半分)で登場\n乙女";
		hintText[TUKEMA]="４０～５９(ハードならその半分)でまれに登場\nふーふふっふーふふっふふふつけ～♪";
		hintText[SYAKIN]="６０～７９(ハードならその半分)で登場\nりりしい";
		hintText[YASEUMA]="６０～７９(ハードならその半分)でまれに登場\nモデルさんのようだわ";
		hintText[HAKUBA]="８０～８９(ハードならその半分)で登場\nメラニンゼロ！";
		hintText[OUJI]="８０～８９(ハードならその半分)でまれに登場\n純白なるはら黒王子";
		hintText[DARK]="９０～９９(ハードならその半分)でまれに登場\n暗黒界の主";
		hintText[BRAIDARU]="９０～９９(ハードならその半分)でまれに登場\n幸せになるんだよ";
		hintText[TIGER]="１００～(ハードならその半分)で登場\nまさかの肉食動物か！？";
		hintText[ITIGOUMA]="名前を数字に変換した記録で登場\n赤くてあまずっぱい食べ物○○○\nこれ100%のマンガとかあるよね";
		hintText[HAGE]="毛の数と同じ記録で登場\nはげてはいるけどなみへいさんの三倍はあるとのこと";
		hintText[SARARIMAN]="髪型に準じた記録で登場\n働く男の髪型といえば○○わけ";
		hintText[STAFF]="一回結果画面を表示で登場\nクレジット";
	}

	void initName(){
		name[KAWAIIUMA]="馬";
		name[ITIGOUMA]="いちご馬";
		name[TIGER]="タイガー馬スク";
		name[TUKEMA]="つけ馬";
		name[HAGE]="はげ";
		name[HAKUBA]="はくば";
		name[OUJI]="白馬の王子様";
		name[SARARIMAN]="サラリー馬ン";
		name[SYAKIN]="馬(りりしい)";
		name[SYOBON]="馬(しょんぼり)";
		name[YASEUMA]="ガリ馬ー";
		name[DEBUUMA]="デーブ";
		name[UMAKO]="うまこ";
		name[BRAIDARU]="ブライダルうまこ";
		name[DARK]="だーくほーす";
		name[STAFF]="クレジット";


	}
	void initComent(){
		coment[KAWAIIUMA]="おつかれさま！！";
		coment[ITIGOUMA]="けあながひらいてるわけではない";
		coment[TIGER]="とらだぁ！お前はとらになるんだぁ!!";
		coment[TUKEMA]="あげぽよ～";
		coment[HAGE]="そろそろ髪切った方がいいかな？";
		coment[HAKUBA]="こむぎいろうらやま";
		coment[OUJI]="最初に白馬に乗った王子まじゆるさねえ";
		coment[SARARIMAN]="倍返…!!…いえなんでもないですひとりごとです";
		coment[SYAKIN]="いいじゃん！！";
		coment[SYOBON]="…おつかれさま…";
		coment[YASEUMA]="細さは…美しさ…";
		coment[DEBUUMA]="おつかれ！カレー、飲む?";
		coment[UMAKO]="お・も・て・な・し、おもてなし";
		coment[BRAIDARU]="た・ま・の・こ・し、たまのこし";
		coment[DARK]="ｺｫｰ…ｼｭｰ…";

	}

}
