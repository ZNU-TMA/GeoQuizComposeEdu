package ua.edu.znu.geoquizcomposeedu.nav

import android.os.Build
import android.util.Log
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json
import ua.edu.znu.geoquizcomposeedu.data.Question

private const val TAG = "QuestionNavType"

/**
 * Custom navigation type for the Question class.
 */
object QuestionNavType {
    val questionType = object : NavType<Question>(
        isNullableAllowed = false
    ) {
        /**
         * Inserts a Parcelable value into the mapping of this Bundle
         * (without JSON serialization),
         */
        override fun put(
            bundle: SavedState,
            key: String,
            value: Question
        ) {
            Log.d(TAG, "put: bundle = $bundle, key = $key, value = $value")
            bundle.putParcelable(key, value)
        }

        /**
         * Returns the value associated with the given key (without JSON serialization)
         * or null if:
         * - No mapping of the desired type exists for the given key.
         * - A null value is explicitly associated with the key.
         * -The object is not of type clazz.
         */
        override fun get(
            bundle: SavedState,
            key: String
        ): Question? {
            Log.d(TAG, "get: bundle = $bundle, key = $key")
            // for backwards compatibility
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, Question::class.java)
            } else {
                @Suppress("DEPRECATION") // for backwards compatibility
                bundle.getParcelable(key)
            }
        }

        /**
         * Decodes and deserializes the given JSON string to the value of Question type.
         */
        override fun parseValue(value: String): Question {
            Log.d(TAG, "parseValue: value = $value")
            return Json.decodeFromString(value)
        }

        /**
         * Serializes the Question value to a JSON string.
         */
        override fun serializeAsValue(value: Question): String {
            Log.d(TAG, "serializeAsValue: value = $value")
            return Json.encodeToString(value)
        }
    }
}