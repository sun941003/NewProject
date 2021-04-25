package com.example.newproject.util

import java.util.regex.Pattern

object ValidationCheckUtil {
    const val ID_REGEX = "[a-z][a-z0-9]{5,19}\$"
    const val PASSWORD_REGEX =
        "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[-!@#\$^&*=+|()/?<>~{}]{0,})[A-Za-z\\d-!@#\$^&*=+|()/?<>~{}]{8,20}\$"
    const val NICKNAME_REGEX = "^[ㄱ-ㅎ가-힣A-Za-z0-9]{1,20}\$"
    const val NAME_REGEX ="^[가-힣A-Za_z]{1,20}\$" // Maybe
    const val EMAIL_REGEX = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$/i\n" +
            "\n"

    fun checkInputValidation(text: String, regex: String): Boolean {
        val pattern = Pattern.compile(regex)
        return pattern.matcher(text).matches()
    }
}