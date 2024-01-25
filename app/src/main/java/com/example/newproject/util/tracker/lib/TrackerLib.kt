package com.example.newproject.util.tracker.lib

class Amplitude {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
    }

    fun boot() {
        isBoot = true
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent() {
        // 이벤트 전송
    }
}

class FirebaseLog {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
    }

    fun boot() {
        isBoot = true
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent() {
        // 이벤트 전송
    }
}

class ChannelTalk {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
    }

    fun boot() {
        isBoot = true
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent() {
        // 이벤트 전송
    }
}

class AppsFlyer {
    private var isBoot: Boolean? = null

    fun init() {
        // 초기화
        isBoot = false
    }

    fun boot() {
        isBoot = true
    }

    fun isBoot(): Boolean {
        if (isBoot != null) {
            return isBoot!!
        }

        return false
    }

    fun sendEvent() {
        // 이벤트 전송
    }
}

