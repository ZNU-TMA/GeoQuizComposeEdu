package ua.edu.znu.geoquizcomposeedu.educational.navigation.nav

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json
import ua.edu.znu.geoquizcomposeedu.educational.navigation.data.Subject

/**
 * Custom navigation type for the Subject class.
 * NavType will be used by compose internally to put subjectType object into a bundle,
 * and later retrieve it.
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
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        /**
         * Deserialize the string to the custom type.
         * The string is obtained from the navigation framework.
         * Also uses when the custom type object is returned as a string result from
         * the navigated screen.
         */
        override fun parseValue(value: String): Subject {
            return Json.decodeFromString(Uri.decode(value))
        }

        /*!!! MANUALLY ADDED !!!
        * Serialize the custom type to the string.
        * The string is then passed to the navigation framework.
        * Uses when the custom type object is passed to UI as a string.
        * Also used when the custom type needs to be serialized to a string for display,
        * route arguments, or returned results.
         */
        override fun serializeAsValue(value: Subject): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}