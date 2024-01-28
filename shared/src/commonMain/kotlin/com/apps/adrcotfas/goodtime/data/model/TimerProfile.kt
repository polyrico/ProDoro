package com.apps.adrcotfas.goodtime.data.model

import com.apps.adrcotfas.goodtime.domain.TimerType
import kotlin.time.Duration.Companion.minutes

data class TimerProfile(
    val isCountdown: Boolean = true,
    /** Work duration in minutes; invalid for isCountdown false */
    val workDuration: Int = DEFAULT_WORK_DURATION,
    /** Break duration in minutes */
    val breakDuration: Int = DEFAULT_BREAK_DURATION,
    /** Long break duration in minutes */
    val longBreakDuration: Int = DEFAULT_LONG_BREAK_DURATION,
    /** Number of sessions before long break or 0 to have this feature disabled */
    val sessionsBeforeLongBreak: Int = DEFAULT_SESSIONS_BEFORE_LONG_BREAK,
    /** the ratio between work and break duration; invalid for isCountdown true */
    val workBreakRatio: Int = DEFAULT_WORK_BREAK_RATIO
) {
    companion object {
        const val DEFAULT_WORK_DURATION = 25
        const val DEFAULT_BREAK_DURATION = 5
        const val DEFAULT_LONG_BREAK_DURATION = 15
        const val DEFAULT_SESSIONS_BEFORE_LONG_BREAK = 4
        const val DEFAULT_WORK_BREAK_RATIO = 3
    }
}

/**
 * Returns the end time of the timer in milliseconds since Unix Epoch.
 * If the timer is not a countdown timer, returns 0.
 * @param timerType the type of the timer
 * @param now the current time in milliseconds since Unix Epoch
 */
fun TimerProfile.endTime(timerType: TimerType, now: Long): Long {
    return if (isCountdown) {
        now + when (timerType) {
            TimerType.WORK -> workDuration
            TimerType.BREAK -> breakDuration
            TimerType.LONG_BREAK -> longBreakDuration
        }.minutes.inWholeMilliseconds
    } else 0
}