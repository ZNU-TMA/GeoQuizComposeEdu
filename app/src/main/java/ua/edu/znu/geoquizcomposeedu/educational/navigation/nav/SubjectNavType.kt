package ua.edu.znu.geoquizcomposeedu.educational.navigation.nav

import android.os.Build
import android.util.Log
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject

private const val TAG = "SubjectNavType"

/**
 * Custom navigation type for the Subject class.
 * NavType will be used by compose internally to put subjectType object into a bundle,
 * and later retrieve it.
 * SubjectNavType serializes/deserializes the whole Subject (including its Category)
 * with kotlinx.serialization (and Category is annotated @Serializable).
 * You need to implement the serialization and deserialization of the custom type
 * by Ctrl+Insert -> Implement Methods.
 * */
object SubjectNavType {
    val subjectType = object : NavType<Subject>(
        isNullableAllowed = false
    ) {
        /**
         * Inserts a Parcelable value into the mapping of this Bundle (without JSON serialization),
         * replacing any existing value for the given key.
         * Either key or value may be null.
         */
        override fun put(
            bundle: SavedState,
            key: String,
            value: Subject
        ) {
            Log.d(TAG, "put: bundle = $bundle, key = $key, value = $value")
            bundle.putParcelable(key, value)
        }

        /**
         * Returns the value associated with the given key (without JSON serialization) or null if:
         * - No mapping of the desired type exists for the given key.
         * - A null value is explicitly associated with the key.
         * -The object is not of type clazz.
         */
        override fun get(
            bundle: SavedState,
            key: String
        ): Subject? {
            Log.d(TAG, "get: bundle = $bundle, key = $key")
            // for backwards compatibility
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, Subject::class.java)
            } else {
                @Suppress("DEPRECATION") // for backwards compatibility
                bundle.getParcelable(key)
            }
        }

        /**
         * Decodes and deserializes the given JSON string to the value of Subject type.
         */
        override fun parseValue(value: String): Subject {
            Log.d(TAG, "parseValue: value = $value")
            return Json.decodeFromString(value)
        }

        /*!!! MANUALLY ADDED !!!
        * Serializes the value of Subject type into an equivalent JSON.
         */
        override fun serializeAsValue(value: Subject): String {
            Log.d(TAG, "serializeAsValue: value = $value")
            return Json.encodeToString(value)
        }
    }
}