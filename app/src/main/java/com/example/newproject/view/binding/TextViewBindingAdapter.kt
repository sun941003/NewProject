package com.example.newproject.view.binding

import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.example.newproject.R
import com.example.newproject.util.CustomTypefaceSpan
import com.example.newproject.util.ResourceUtil
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TextViewBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["isUnderLine"])
    fun setUnderLine(textView: TextView, isUnderLine: Boolean) {
        if (isUnderLine) {
            textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["inputText", "replaceText", "spanTextColor", "spanTextBold", "spanTextSize", "isMediumFont"],
        requireAll = false
    )
    fun setSpanText(
        textView: TextView,
        inputText: String,
        replaceText: String?,
        color: Int?,
        bold: Boolean?,
        textSize: Int?,
        isMediumFont: Boolean?
    ) {
        replaceText?.let {
            val startIndex = inputText.indexOf("-")
            val resultText = inputText.replace("-", replaceText)
            val sb = SpannableStringBuilder(resultText)
            color?.let {
                sb.setSpan(
                    ForegroundColorSpan(color),
                    startIndex,
                    startIndex + replaceText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            bold?.let {
                sb.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startIndex,
                    startIndex + replaceText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            textSize?.let {
                sb.setSpan(
                    AbsoluteSizeSpan(ResourceUtil.dip(it)),
                    startIndex,
                    startIndex + replaceText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            isMediumFont?.let {
                val typeface = ResourcesCompat.getFont(
                    textView.context,
                    R.font.noto_medium
                )
                sb.setSpan(
                    CustomTypefaceSpan("", typeface!!), startIndex,
                    startIndex + replaceText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            textView.text = sb
        } ?: textView.setText(inputText)

    }

    @JvmStatic
    @BindingAdapter(
        value = ["inputText", "accentText", "spanTextColor", "spanTextBold", "spanTextSize", "isMediumFont"],
        requireAll = false
    )
    fun setAccentText(
        textView: TextView,
        inputText: String,
        accentText: String?,
        color: Int?,
        bold: Boolean?,
        textSize: Int?,
        isMediumFont: Boolean?
    ) {
        accentText?.let {
            val startIndex = inputText.indexOf(accentText)
            val sb = SpannableStringBuilder(inputText)
            color?.let {
                sb.setSpan(
                    ForegroundColorSpan(color),
                    startIndex,
                    startIndex + accentText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            bold?.let {
                sb.setSpan(
                    StyleSpan(Typeface.BOLD),
                    startIndex,
                    startIndex + accentText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            textSize?.let {
                sb.setSpan(
                    AbsoluteSizeSpan(ResourceUtil.dip(it)),
                    startIndex,
                    startIndex + accentText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            isMediumFont?.let {
                val typeface = ResourcesCompat.getFont(
                    textView.context,
                    R.font.noto_medium
                )
                sb.setSpan(
                    CustomTypefaceSpan("", typeface!!), startIndex,
                    startIndex + accentText.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            textView.text = sb
        } ?: textView.setText(inputText)

    }

    @JvmStatic
    @BindingAdapter(value = ["commaText", "commaTextSubfix", "commaTextPrefix"], requireAll = false)
    fun setCommaText(
        view: TextView,
        price: String?,
        commaTextSubfix: String?,
        commaTextPrefix: String?
    ) {
        price?.let {
            return try {
                var commnavalues = ""
                var decimal = ""
                var values: Array<String>? = null
                if (price.contains(".")) {
                    values = price.split("\\.").toTypedArray()
                    commnavalues = values[0]
                    decimal = values[1]
                    var noneZeroIdnex = -1
                    for (i in 0 until decimal.length) {
                        if (decimal[i] != '0') {
                            noneZeroIdnex = i
                        }
                    }
                    decimal = if (noneZeroIdnex == -1) {
                        ""
                    } else {
                        decimal.substring(0, noneZeroIdnex)
                    }
                } else {
                    commnavalues = price
                }
                val value = commnavalues.toLong()
                val format = DecimalFormat("###,###")
                var result_int = format.format(value)
                if (values != null && values.size > 0 && decimal.length > 0) {
                    result_int = "$result_int.$decimal"
                }
                view.setText((commaTextPrefix ?: "") + result_int + (commaTextSubfix ?: ""))
            } catch (e: Exception) {
                view.setText((commaTextPrefix ?: "") + price + (commaTextSubfix ?: ""))
            }
        }
    }
    //어노테이션 (retrofit사용하는듯이 정형화된 함수라고 생각하기 -> 구글링 하면 많이 나온다 , 프로젝트 진행하는 중에는 사용하는 방법은 서비스 사용하는거랑 비슷하게 돌아가나까 사용하면서 안되는 부분, 모르는 부분만 띠로 배우기 )
    @JvmStatic
    @BindingAdapter(
        value = ["time", "inputDateFormat", "outputDateFormat", "timePrefix", "timeSubfix"], // 통신에 먼저 선언 되어있는 값이랑 형식이 비슷함
        requireAll = false
    )
    //위에서 먼저 선언된 데이터 형식에 맞춰서 type값이랑 이름도 선언 해놓고 사용 -> 사용하는 부분은 통신, 서비스랑 비슷한 구조 같다. 세부 코드만 보고 이해하기 모르는 부분은 따로 질문 해도 됨
    //코드 자체를 이해할 만 해야 오픈소스를 받아와도 커스텀 가능하다. 그니까 많이 보면서 흐름이랑 코드 자체 사용되는 부분 이해하기
    
    fun setTimeText(
        textView: TextView,
        time: String?,
        inputDateFormat: String?,
        outputDateFormat: String?,
        timePrefix: String?,
        timeSubfix: String?
    ) {
        time?.let {
            var inputTime =
                if (time.contains(".")) time.split("\\.".toRegex()).toTypedArray()[0] else time
            val inputPattern = inputDateFormat ?: let {
                if (inputTime.contains("T")) {
                    "yyyy-MM-dd'T'HH:mm:ss"
                } else {
                    "yyyy-MM-dd HH:mm:ss"
                }
            }
            val outPattern = outputDateFormat ?: "yyyy-MM-dd HH:mm:ss"
            val inputFormat = SimpleDateFormat(inputPattern)
            val outputFormat = SimpleDateFormat(outPattern)
            try {
                val date = inputFormat.parse(inputTime)
                textView.text = (timePrefix ?: "") + outputFormat.format(date) + (timeSubfix ?: "")
            } catch (e: ParseException) {
                textView.text = (timePrefix ?: "") + inputTime + (timeSubfix ?: "")
            }
        }
    }
}