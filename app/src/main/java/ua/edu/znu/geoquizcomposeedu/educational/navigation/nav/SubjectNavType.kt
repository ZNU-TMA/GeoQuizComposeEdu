package ua.edu.znu.geoquizcomposeedu.educational.navigation.nav

import android.net.Uri
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
         * Serialize the custom type in the string and save it to the bundle.
         * The bundle is passed to the navigation framework.
         * The use URL encoding form Bundle is not necessary.
         */
        override fun put(
            bundle: SavedState,
            key: String,
            value: Subject
        ) {
            Log.d(TAG, "put: bundle = $bundle, key = $key, value = $value")
            bundle.putString(key, Json.encodeToString(value))
        }

        /**
         * Retrieve serialized in string the custom type from the bundle
         * and deserialize it to the custom type.
         * The bundle is passed from the navigation framework.
         */
        override fun get(
            bundle: SavedState,
            key: String
        ): Subject? {
            Log.d(TAG, "get: bundle = $bundle, key = $key")
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        /**
         * Deserialize the string to the custom type.
         * Use when a custom type is encoded into a route string
         * (building/parsing ".../screen/{arg}").
         */
        override fun parseValue(value: String): Subject {
            Log.d(TAG, "parseValue: value = $value")
            return Json.decodeFromString(Uri.decode(value))
        }

        /*!!! MANUALLY ADDED !!!
        * Serialize the custom type to the string.
        * The string is then passed to the navigation framework.
         */
        override fun serializeAsValue(value: Subject): String {
            Log.d(TAG, "serializeAsValue: value = $value")
            return Uri.encode(Json.encodeToString(value))
        }
    }
}