package com.example.newproject.model.fcm
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.newproject.App
import com.example.newproject.R
import com.example.newproject.view.ui.main.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushMessageService : FirebaseMessagingService() {

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        FCMHandler.pushToken = newToken
//        BaseViewModel.token.value?.takeIf { it.accessToken.isNullOrEmpty().not() }?.let {
//            updatePushToken(it.accessToken, newToken)
//        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
//        if(remoteMessage.data.isNotEmpty()){
//            //근데 넘겨줄때 이미 data값을 받는데 데이터로 구분하는게 맞나?
//            //넘어 오기는 함 근데 종료됨
//            Toast.makeText(this,"토픽 메시지 테스트",Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this,"토픽 메시지 테스트 실패 -> remoteMessage data값이 공백",Toast.LENGTH_SHORT).show()
//        }
        //이 부분은 크게 필요없음 -> 공식문서에 topic관련 코드 (이 부분이 들어가지 않아도 일반 알림처럼 정상 작동함 오히려 들어가면 포그라운드에서 튕김, 백그라운드 상태에서만 스플레쉬 화면에서 메인화면으로 바로 타고 넘어와짐)
        remoteMessage.notification?.let {
            val title = it.title ?: ""
            val body = it.body ?: ""
            remoteMessage.data.let {
                val type =
                    it.takeIf { it.containsKey(FCMDataUtil.KEY_TYPE) }
                        ?.let { it[FCMDataUtil.KEY_TYPE] }
                        ?: ""
                showNotification(title, body, type)
            }
        }
    }

    private fun showNotification(
        title: String,
        body: String,
        type: String
    ) {
        var builder: NotificationCompat.Builder
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        builder = if (Build.VERSION.SDK_INT >= 26) {
            val mChannel = NotificationChannel(
                //이 부분 변경점 위에가 알림 아이디, 밑은 타이틀 값으로
                resources.getString(R.string.default_notification_id),
                resources.getString(R.string.default_notification_title),
//                resources.getString(R.string.app_name),
//                resources.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(mChannel)
            NotificationCompat.Builder(this, mChannel.id)
        } else {
            NotificationCompat.Builder(this)
        }
        builder.setContentTitle(title).setContentText(body).setAutoCancel(true).setWhen(
            System.currentTimeMillis()
        )
//            .setSmallIcon(R.drawable.ic_stat_name)
            .setDefaults(
            Notification.DEFAULT_SOUND
        ).setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
//        val stackBuilder = TaskStackBuilder.create(this)
//        stackBuilder.addNextIntent(Intent(this, MainActivity::class.java).apply {
//            if (!isExternalPush.isEmpty()) {
//                putExtra(ScreenDataKey.INOUT, isExternalPush)
//            }
//            if (!type.isEmpty()) {
//                putExtra(ScreenDataKey.TYPE, type)
//            }
//            if (!detail.isEmpty()) {
//                putExtra(ScreenDataKey.DETAIL, detail)
//            }
//        })


//        val notificationLayout = RemoteViews(packageName, R.layout.item_notification_small)
//        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle()).setCustomContentView(notificationLayout)


        //stackBuilder는 말그대로 스택 쌓아 주는애 -> if 댓글 화면으로 이동하는 알림이 왔을때 수정을 하고 뒤로가기를 누르면 꺼진다 그러니까 스택빌더로 메인화면이나 추가적인 화면을 만들어줘야 앱이 종료되지 않고 앱이 정상작동된다
        //광고알림 같은건 클릭시 알림만 띄워주면 바탕화면에서 알림화면 뒤로 블러처리 되서 나옴 (설명을 들었을땐 그렇다 그래서 메인화면을 한번 띄위주고 뒤로가기시 앱 메인화면으로 이동되게한다고 보면 될듯)
        var intent: Intent? = null
        var pendingIntent: PendingIntent? = null
        intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (!type.isEmpty()) {
                putExtra(FCMDataUtil.KEY_TYPE, type)
            }
//            if (!id.isEmpty()) {
//                putExtra(FCMDataUtil.KEY_ID, id)
//            }
        }

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntentWithParentStack(intent)
        pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)
        /*
        이부분에서 이해 안됐던 부분은 intent 랑 pendingIntent선언 하고 나서 왜 intent를 먼저 사용하는지 였는데
        위쪽을 보면 선언은 둘다 먼저 되어있다 그리고 바로 위에 코드를 보면 이해를 할 수 있다.
        그뒤에 스택 빌더를 만들고 스택빌더에서 인텐트를 사용한다 , 그리고 이 부분에서 펜딩 인텐트도 사용을 함께 하면서 둘이 같이 사용이 된다고 보면된다
        그리고 인텐트에서 플래그를 사용하는 부분에서 해당 플래그를 사용하게 된다 -> 예를 들면 이전 작업이 남아있는 액티비티 스택중에 해당 화면으로 넘어가게 되었을 경우, 이전 스택을 전부 지워줘야 된다
        -> 이부분은 어떻게 구동이 되는지 로직적으로 먼저 생각하고 그다음에 어떤 플래그들을 사용할지 골라야될 것 같다
        근데 이부분이 항상 동일한 코드를 사용한다면 고정적으로 사용해도 될 것 같다
         */

        if (pendingIntent != null) {
            builder.setContentIntent(pendingIntent)
            builder.setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
                .setVibrate(longArrayOf(1000, 1000))
            builder.setCategory("msg").setPriority(NotificationCompat.PRIORITY_HIGH).setVisibility(
                NotificationCompat.VISIBILITY_PUBLIC
            )
            val pushId: Int = getPushId(App.instance)
            savePushId(App.instance, pushId + 1)
            notificationManager.notify(pushId, builder.build())
        }

    }


    //
    fun savePushId(context: Context, pushId: Int) {
        val preferences = context.getSharedPreferences("push", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(
            "type", if (pushId > 100) {
                0
            } else pushId
        )
        editor.commit()
    }


    fun getPushId(context: Context): Int {
        val preferences = context.getSharedPreferences("push", MODE_PRIVATE)
        return preferences.getInt("type", 0)
    }

    fun updatePushToken(token: String, pushToken: String) {
//        val userRepository: UserRepository by inject()
//        userRepository.updatePushToken(token, pushToken, 0)
    }
    //userRepository
//    fun updatePushToken(token: String, pushToken: String, repeatCount: Int) {
//        apiService.updatePushToken("Bearer " +  token, pushToken)
//            .enqueue(object : Callback<RepoBase> {
//                override fun onResponse(
//                    call: Call<RepoBase>,
//                    response: Response<RepoBase>
//                ) {
//                    if ((response.body() != null || response.code() != 200 || response.body()?.status?.code != "0000") && repeatCount < 3) {
//                        updatePushToken(token, pushToken, repeatCount + 1)
//                    }
//                }
//
//                override fun onFailure(call: Call<RepoBase>, t: Throwable) {
//                    if (repeatCount < 3) {
//                        updatePushToken(token, pushToken, repeatCount + 1)
//                    }
//                }
//            })
//    }
}