package dev.lucasnlm.antimine.core.preferences

import android.app.Application
import androidx.preference.PreferenceManager
import dev.lucasnlm.antimine.common.level.data.LevelSetup
import javax.inject.Inject

class PreferencesInteractor @Inject constructor(
    private val application: Application
) {
    private val preferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    fun getCustomMode() = LevelSetup(
        preferences.getInt(PREFERENCE_CUSTOM_GAME_WIDTH, 9),
        preferences.getInt(PREFERENCE_CUSTOM_GAME_HEIGHT, 9),
        preferences.getInt(PREFERENCE_CUSTOM_GAME_MINES, 9)
    )

    fun updateCustomMode(customLevelSetup: LevelSetup) {
        preferences.edit().apply {
            putInt(PREFERENCE_CUSTOM_GAME_WIDTH, customLevelSetup.width)
            putInt(PREFERENCE_CUSTOM_GAME_HEIGHT, customLevelSetup.height)
            putInt(PREFERENCE_CUSTOM_GAME_MINES, customLevelSetup.mines)
        }.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean) = preferences.getBoolean(key, defaultValue)

    fun putBoolean(key: String, value: Boolean) = preferences.edit().putBoolean(key, value).apply()

    fun getInt(key: String, defaultValue: Int) = preferences.getInt(key, defaultValue)

    fun putInt(key: String, value: Int) = preferences.edit().putInt(key, value).apply()

    companion object {
        private const val PREFERENCE_CUSTOM_GAME_WIDTH = "preference_custom_game_width"
        private const val PREFERENCE_CUSTOM_GAME_HEIGHT = "preference_custom_game_height"
        private const val PREFERENCE_CUSTOM_GAME_MINES = "preference_custom_game_mines"
    }
}