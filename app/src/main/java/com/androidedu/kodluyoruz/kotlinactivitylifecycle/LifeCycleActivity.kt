package com.androidedu.kodluyoruz.kotlinactivitylifecycle

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

class LifeCycleActivity : AppCompatActivity() {

    fun printLog(methodName: String) {

        val format: String = "mm:ss__SSSS"

        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val now = Date()

        val time: String = sdf.format(now.time)

        Log.e(methodName, time)
    }

    /**
     * Activity ayağa kalktığında ilk çağrılan methodtur.
     *
     * İçerisinde finish() methodu çağrılırsa, direkt olarak onDestroy() methoduna geçilir.
     * Activity yaşam döngüsündeki diğer methodlar çağrılmaz.
     *
     * @param savedInstanceState eğer Activity bir şekilde yeniden yaratılma ihtiyacı duymuşsa
     * onSaveInstanceState(Bundle) methodunun içerisinden sağlanan değerleri içerir. Aksi halde null döner.
     */
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        printLog("onCreate1 setContentView öncesi")
//        setContentView(R.layout.activity_life_cycle)
//        printLog("onCreate1 setContentView sonrası")
//    }

    /**
     * Uygulama UI'ının görünümü herhangi bir şekilde değişirse çağrılır.
     *Örnek : notifyDataSetChanged, setContentView, addContentView bu değişime neden olur.
     */
    override fun onContentChanged() {
        super.onContentChanged()
        printLog("onContentChanged")
    }

    /**
     * onCreate(Bundle)'dan sonra çağrılır.
     * Ya da Activity bir şekilde onStop() durmuna geçmişse onRestart()'dan sonra çağrılır.
     */
    override fun onStart() {
        super.onStart()
        printLog("onStart")
    }

    /**
     * Activity bir şekilde yeniden yaratılmaya ihtiyaç duyduysa, onCreate(Bundle)'da olduğu gibi
     * @param savedInstanceState onSaveInstanceState(Bundle) methodunun içerisinden sağlanan değerleri içerir. Aksi halde null döner.
     *
     * Çoğu uygulama Activity yeniden yaratılma durumları için onCreate(Bundle)'ı kullanır.
     * Ancak bazı durumlarda UI hazırlandıktan sonra bazı işleri yapmanız gerekir. Bu gibi durumlarda kullanılır.
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        printLog("onRestoreInstanceState")
    }

    /**
     * Activity'nizin UI'ı çizdirildikten sonra onCreate(Bundle) veya onRestoreInstanceState(Bundle) çağrılır.
     * Çoğu uygulama kullanmaya gerek duymaz.
     * Bu method, daha çok sistem class'larının, uygulama kodu çalıştırıldıktan sonra ilk değer ataması
     * yapması için kullanılır.
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        printLog("onPostCreate")
    }

    /**
     * Activity'nizin kullanıcı ile etkileşime geçebileceği en iyi yerdir.
     * onRestoreInstanceState(Bundle), onRestart() ya da onPause() methodlarından sonra çağrılır.
     * Animasyonları ilk kez veya arka plandan gelinmesi durumunda tekrar çalıştırılması amacıyla,
     * Kamera gibi özel erişim fonksiyonlarının ilk kez veya arka plandan gelinmesi durumunda tekrar çalıştırılması
     * amacıyla kullanılır.
     */
    override fun onResume() {
        super.onResume()
        printLog("onResume")
    }

    /**
     * Animasyonlar veya kamera gibi özel erişim fonksiyonlar ilk kez veya tekrar çalıştırıldıktan sonra
     * çağrılır.
     * Çoğu uygulama kullanmaya gerek duymaz.
     * Bu method, daha çok sistem class'larının, uygulama kodu çalıştırıldıktan sonra ilk değer ataması
     * yapması için kullanılır.
     */
    override fun onPostResume() {
        super.onPostResume()
        printLog("onPostResume")
    }

    /**
     * Activity ile ilişkilendirilmiş ekran, ekran yöneticisine eklendiğinde çağrılır.
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        printLog("onAttachedToWindow")
    }

    /**
     * Activity arka plana gönderildiğinde ancak öldürülmediğinde çağrılır. onResume()'un tersi işlemdir.
     * Ayakta bir A Activity'si bulunuyorken B Activity'si çağırırsanız, A Activity'si onPause() methodunu çağırır.
     * A Activity'sinin onPause()'u çağrılmadan B Activity'si yaratılmaz.
     *
     * Animasyon, kamera gibi CPU harcayacak fonksiyonların kapatılması burada yapılır.
     *
     * onPause() durumuna geçmiş olan Activity'ler Sistem'in daha çok belleğe ihtiyaç duyması durumunda
     * sonlandırılabilirler. Bu gibi bir durumda sorun yaşamamak için tüm Activity durumunu onPause()'a
     * geçtiğinizde kaydetmek isteyebilirsiniz. Yine de bu işlem daha çok onSaveInstanceState(Bundle) methodunda yapılır.
     */
    override fun onPause() {
        super.onPause()
        printLog("onPause")
    }

    /**
     * Activity artık kullanıcı tarafından görüntülenemediğinde çağrılır.
     * Peşine ya onRestart() ya da onDestroy() çağrılacaktır.
     */
    override fun onStop() {
        super.onStop()
        printLog("onStop")
    }

    /**
     * Arka planda olan Activity tekrar kullanıcıya gösterileceği zaman çağrılır.
     * Peşine onStart ve onResume() çağrılacaktır.
     */
    override fun onRestart() {
        super.onRestart()
        printLog("onRestart")
    }

    /**
     * Activity tamamen yok olmadan önce gerekli olan tüm temizlik işlemleri burada yapılır.
     * Activity iki türlü yok olabilir: Ya finish() methodu bir yerlerde çağrılmıştır ya da Sistem
     * daha fazla bellek ihtiyacı için otomatik olarak pasif duran Activity'i öldürmek istemiştir.
     * Bu iki durumu birbirinden ayırmak için isFinish() methodunu kullanabilirsiniz. finish() ile
     * kapatılması durumunda true dönecektir.
     *
     * Activity durumunu kaydetmek için bu methodu kullanmayınız. Bunu yapmak için onPause() ya da onSaveInstanceState(Bundle)
     * methodları kullanılmalıdır. Burada daha çok Activity yok olduğunda çalışmasına gerek kalmayacak nesneleri
     * kapatma ve bellekten temizleme işlemleri yapılır. Böylece diğer uygulamalara yer ve performans
     * sağlanmış olur.
     */
    override fun onDestroy() {
        super.onDestroy()
        printLog("onDestroy")
    }

    /**
     * Bir Activity öldürülmeden hemen önce çağrılır. Böylece Activity'nin durumunu kaydedebilirsiniz.
     * Kaydettiğiniz durumu daha sonra onCreate(Bundle) ya da onRestoreInstanceState(Bundle) methodları içerisinde
     * tekrar kullanabilirsiniz. Böylece veri kaybı yaşanmaz.
     *
     * Mesela ekranda A Activity'si açık olsun. Bunun üzerine B Activity'si açalım ve A Activity'i pasif
     * duruma alıp, arka plana gönderelim. Bu durumda Sistem daha fazla yer açmak için A Activity'nizi öldürebilir.
     * Bu durumda B Activity'sinden A Acitivity'sine tekrar geri dönmek isterseniz, A Actvitiy'sinin tüm
     * geçerli durumunu korumuş ve tekrar bu durumu çağırmış ve kullanmış olmanız gerekir ki veri kaybı yaşamayın.
     * Bunu yapacağınız yer işte burası.
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        printLog("onSaveInstanceState")
    }

    /**
     * Seçenekler menusu ilk kez görüntülendiğinde sadece bir kez çağrılır. Yeni menu tanımlamaları yapabilirsiniz.
     * Menüyü her görüntülediğinizde güncellemek için bkz. OnPrepareOptionsMenu(Menu).
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        printLog("onCreateOptionsMenu")
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Seçenekler menusu her görüntülendiğinde çağrılır. Menu itemları efektif bir şekilde enable veya disable
     * etmek gibi işlerde kullanabilirsiniz.
     */
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        printLog("onPrepareOptionsMenu")
        return super.onPrepareOptionsMenu(menu)
    }

    /**
     * Activity'niz ayakta iken kullanıcı herhangi bir şekilde ekranla etkileşime geçerse bu method çağrılır.
     * onUserLeaveHint() her çağrıldığında da bu method yine ona eşlik ederek çağrılacaktır.
     */
    override fun onUserInteraction() {
        super.onUserInteraction()
        printLog("onUserInteraction")
    }

    /**
     * Kullanıcı Home(Ana) butona basarak ayaktaki Activity'i arka plana almak isterse çağrılır.
     * Telefona gelen aramalarda da ayakta bir Activity varsa arkaplana alınır. Ancak bu durumda onUserLeaveHint()
     * çağrılmaz. Yalnızca bilinçli bir şekilde kullanıcı Activity'nizi arka plana almak istediğinde çağrılır.
     * Activity arka plana alınırken, eğer bu bir kullanıcı seçimiyse onPause() methodu çağrılmadan hemen önce çağrılır.
     */
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        printLog("onUserLeaveHint")
    }

    /**
     * Activity'i bir "requestCode" ile beraber başlatmak istemeniz durumuunda çağrılır. Bu başlatma sırasında
     * requestCode'un yanında ekstra veriler de taşıyabilirsiniz.
     *
     * Telefon rehberinden seçilen bir numarayı Activity'nize taşımak için bu yolu kullanabilirsiniz.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        printLog("onActivityResult")
    }

    /**
     * Eğer bir fragment Activity'nize eklenmek isterse, bu durumda çağrılır.
     */
    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        printLog("onAttachFragment")
    }

    /**
     * Manifest dosyası üzerinde bir Activity tanımlaması yapılırken, configChanges özelliğinde sistem tarafından
     * değiştirilen ayarlardan haberdar olmak istediklerinizi yazarsanız (dil değişikliği, yazı tipi ve boyutu,
     * ekran oryantasyonunun değişmesi) bunlar gerçekleştiğinde Activity'inize bildirir.
     *
     * Önemli uyarı, Manifest'de bildirilmeyen sistem değişiklikleri yapıldığı durumda, Activity'niz öldürülür
     * ve yeniden başlatılır. Bu durumda Activity durumunu onSaveInstanceState(Bundle) methodunda kaydetmeli
     * ve daha sonra onCreate(Bundle) veya onRestoreInstanceState(Bundle) methodlarından birinde yeniden Activity
     * durumuna ulaşabilmelisiniz. Aksi halde uygulamanızda çökme durumlarıyla karşılaşabilirsiniz.
     * Activity'nin Sistem tarafından öldürülüp yeniden başlatılmasının sebebi, yeni sistem ayarlarını
     * mevcut Activity'nize uygulayabilmek içindir.
     */
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        printLog("onConfigurationChanged")
    }

    /**
     * Cihaz üzerindeki geri tuşuna kullanıcının basması durumunda çağrılır.
     * Genel davranışı mevcut activity'i öldürmek şeklindedir. Ancak override ettikten sonra istediğiniz
     * herhangi başka bir işlem de yaptırabilirsiniz. Mesela "Çıkmak İstediğinize Emin Misiniz?" gibi bir dialog
     * çıkartmak isteyebilirsiniz.
     */
    override fun onBackPressed() {
        super.onBackPressed()
        printLog("onBackPressed")
    }

    /**
     * Activity'niz LayoutInflater ile doldurulurken çağrılır. onCreate ile benzer bir çalışma mantığı vardır.
     * XML dosyasındaki UI elementlerini Activity class'ınıza bağlamak için kullanabilirsiniz.
     */
    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View {
        printLog("onCreateView1")
        return super.onCreateView(name, context, attrs)
    }
}
