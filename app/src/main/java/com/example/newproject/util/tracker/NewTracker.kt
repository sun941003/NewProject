package com.example.newproject.util.tracker

import com.example.newproject.util.tracker.lib.Amplitude
import com.example.newproject.util.tracker.lib.AppsFlyer
import com.example.newproject.util.tracker.lib.ChannelTalk
import com.example.newproject.util.tracker.lib.FirebaseLog

enum class TrackerType(val strategy: TrackerStrategy?) {
    AMPLITUDE(AmplitudeStrategy(Amplitude())),
    FIREBASE_LOG(FirebaseLogStrategy(FirebaseLog())),
    CHANNEL_TALK(ChannelTalkStrategy(ChannelTalk())),
    APPS_FLYER(AppsFlyerStrategy(AppsFlyer())),
    ALL(null); // ALL은 특별 취급, 실제 전략은 없음

    companion object {
        fun applyToAll(action: (TrackerStrategy) -> Unit) {
            values().filter { it != ALL }.forEach { action(it.strategy!!) }
        }
    }
}

class NewTracker {
    fun init(trackerType: TrackerType) {
        if (trackerType == TrackerType.ALL) {
            TrackerType.applyToAll { it.init() }
        } else {
            trackerType.strategy?.init()
        }
    }

    fun boot(trackerType: TrackerType) {
        if (trackerType == TrackerType.ALL) {
            TrackerType.applyToAll { it.boot() }
        } else {
            trackerType.strategy?.boot()
        }
    }

    fun sendEvent(trackerType: TrackerType) {
        if (trackerType == TrackerType.ALL) {
            TrackerType.applyToAll {
                if (it.isBoot()) {
                    it.sendEvent()
                }
            }
        } else {
            trackerType.strategy?.let {
                if (it.isBoot()) {
                    it.sendEvent()
                }
            }
        }
    }
}


interface TrackerStrategy {
    fun init()
    fun boot()
    fun sendEvent()
    fun isBoot(): Boolean
}

class AmplitudeStrategy(
    private val amplitude: Amplitude
) : TrackerStrategy {
    override fun init() = amplitude.init()
    override fun boot() = amplitude.boot()
    override fun sendEvent() = amplitude.sendEvent()
    override fun isBoot(): Boolean = amplitude.isBoot()
}

class FirebaseLogStrategy(
    private val firebaseLog: FirebaseLog
) : TrackerStrategy {
    override fun init() = firebaseLog.init()
    override fun boot() = firebaseLog.boot()
    override fun sendEvent() = firebaseLog.sendEvent()
    override fun isBoot(): Boolean = firebaseLog.isBoot()
}

class ChannelTalkStrategy(
    private val channelTalk: ChannelTalk
) : TrackerStrategy {
    override fun init() = channelTalk.init()
    override fun boot() = channelTalk.boot()
    override fun sendEvent() = channelTalk.sendEvent()
    override fun isBoot(): Boolean = channelTalk.isBoot()
}

class AppsFlyerStrategy(
    private val appsFlyer: AppsFlyer
) : TrackerStrategy {
    override fun init() = appsFlyer.init()
    override fun boot() = appsFlyer.boot()
    override fun sendEvent() = appsFlyer.sendEvent()
    override fun isBoot(): Boolean = appsFlyer.isBoot()
}

