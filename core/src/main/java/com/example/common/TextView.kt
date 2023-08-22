package com.example.common

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt

fun TextView.setLabeledText(
    label: CharSequence,
    labelColor: Int,
    text: CharSequence?,
    textColor: Int,
    hideIfTextEmpty: Boolean = true
) {
    if (!text.isNullOrEmpty()) {
        val ss = SpannableString("$label $text")
        ss.setSpan(
            ForegroundColorSpan(labelColor),
            0,
            label.length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        if (text.isNotEmpty()) {
            ss.setSpan(
                ForegroundColorSpan(textColor),
                label.length,
                ss.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        this.text = ss
        this.visibility = View.VISIBLE
    } else {
        if (hideIfTextEmpty)
            this.visibility = View.GONE
        else
            this.visibility = if (hideIfTextEmpty) View.GONE else View.VISIBLE
    }
}

fun TextView.setLabeledText(
    label: CharSequence, labelColor: Int, labelStyle: Int,
    text: CharSequence?, textColor: Int, textStyle: Int,
    hideIfTextEmpty: Boolean = true
) {
    if (!text.isNullOrEmpty()) {
        val ss = SpannableString("$label $text")
        if (label.isNotEmpty()) {
            ss.setSpan(
                ForegroundColorSpan(labelColor),
                0,
                label.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
            ss.setSpan(StyleSpan(labelStyle), 0, label.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }
        if (text.isNotEmpty()) {
            ss.setSpan(
                ForegroundColorSpan(textColor),
                label.length,
                ss.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
            ss.setSpan(
                StyleSpan(textStyle),
                label.length,
                ss.length,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
            )
        }
        this.text = ss
        this.visibility = View.VISIBLE
    } else {
        if (hideIfTextEmpty)
            this.visibility = View.GONE
        else
            this.visibility = if (hideIfTextEmpty) View.GONE else View.VISIBLE
    }
}

val TextView.isEmpty: Boolean
    get() = text.isEmpty()

fun TextView.clear() {
    this.text = ""
}

fun TextView.markAsRequired(marker: CharSequence? = null, @ColorInt markerColor: Int? = null) {
    this.text = SpannableString("${this.text} ${marker ?: "*"}").apply {
        setSpan(
            ForegroundColorSpan(markerColor ?: Color.parseColor("#FF3B3B")),
            length - 1,
            length,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
    }
}