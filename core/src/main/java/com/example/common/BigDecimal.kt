package com.example.common

import java.math.BigDecimal

fun BigDecimal?.toMoneyString() = if (this != null)
    String.format("%.2f", this)
else
    ""

fun BigDecimal?.toRublesString() = "${this.toMoneyString()} $RUBLE"