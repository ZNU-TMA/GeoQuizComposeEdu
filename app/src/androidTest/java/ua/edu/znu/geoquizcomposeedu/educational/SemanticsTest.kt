package ua.edu.znu.geoquizcomposeedu.educational

import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsMatcher.Companion.expectValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ua.edu.znu.geoquizcomposeedu.educational.semantics.SemanticsScreen

class SemanticsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // ARRANGE: Set up any necessary test environment or state before each test
        composeTestRule.setContent {
            SemanticsScreen()
        }
    }

    @Test
    fun testEmbeddedSemantics() {
        composeTestRule.onNodeWithText("Semantics Screen").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Info Icon").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SecondSpacer").assertExists()
    }

    @Test
    fun testHeadingSemantics(): Unit = with(composeTestRule) {
        val isHeading = SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading)
        // The title node is marked as a heading
        onNode(isHeading and hasText("Semantics in Jetpack Compose"))
            .assertIsDisplayed()

        // Only one heading exists; plain Text is not a heading
        onAllNodes(isHeading).assertCountEquals(1)
        onNodeWithText("Semantics Screen").assert(!isHeading)
    }

    @Test
    fun testSwitchRoleSemantics(): Unit = with(composeTestRule) {
        // Check that the Switch is displayed and has the correct role
        onNodeWithText("Semantics Screen").assertIsDisplayed()
        val switchNode = expectValue(
            SemanticsProperties.Role,
            Role.Switch
        )
        // Initial state: On
        onNode(switchNode).assertIsOn()
        onNode(switchNode).performClick()
        // After click: Off
        onNode(switchNode).assertIsOff()
    }

    @Test
    fun testSwitchStateSemantics() {
        // Check that the Switch is displayed and has the correct state
        composeTestRule.onNodeWithText("Semantics Screen").assertIsDisplayed()
        val switchNode = expectValue(
            SemanticsProperties.Role,
            Role.Switch
        )
        val isOff = expectValue(
            SemanticsProperties.ToggleableState,
            ToggleableState.Off
        )

        // Initial state: On
        composeTestRule.onNode(switchNode).assertIsOn()
        composeTestRule.onNode(switchNode).performClick()
        // After click: Off
        composeTestRule.onNode(switchNode).assert(isOff)
    }

    @Test
    fun testSnackbarHasPoliteLiveRegion() {
        // ModalBottomSheet lives in its own window, so there are two roots
        composeTestRule.onAllNodes(isRoot(), useUnmergedTree = true).printToLog("Test")
        composeTestRule.onNode(
            expectValue(
                SemanticsProperties.LiveRegion, LiveRegionMode.Polite
            ) and hasText("SnackBar Message")
        ).assertExists()
    }

    @Test
    fun testShareSheetHasPaneTitle() {
        // ModalBottomSheet lives in its own window, so there are two roots
        composeTestRule.onAllNodes(isRoot(), useUnmergedTree = true)
            .printToLog("Test")
        composeTestRule.onNode(
            expectValue(
                SemanticsProperties.PaneTitle, "New bottom sheet"
            ) and hasAnyDescendant(hasText("Share this message"))
        ).assertExists()
    }

    @Test
    fun testErrorHasErrorSemantics() {
        composeTestRule.onNode(
            expectValue(
                SemanticsProperties.Error, "Please add both email and password"
            ) and hasText("Fields cannot be empty")
        ).assertExists()
    }

    @Test
    fun testProgressInfoBarHasProgressBarRangeInfo() {
        composeTestRule.onNode(
            expectValue(
                SemanticsProperties.ProgressBarRangeInfo,
                ProgressBarRangeInfo(
                    current = 0.5F,
                    range = 0F..1F
                )
            )
        ).assertExists()
    }

    @Test
    fun testMilkyWayListHasCollectionInfo() {
        composeTestRule.onNode(
            expectValue(
                SemanticsProperties.CollectionInfo,
                CollectionInfo(
                    rowCount = 10,
                    columnCount = 1
                )
            )
        ).assertExists()
    }

    @Test
    fun testMilkyWayListItemsHaveCollectionItemInfo() {
        // CollectionItemInfo has no equals(), so compare its fields
        val hasItemInfo = SemanticsMatcher("has CollectionItemInfo with columnIndex = 0") {
            it.config.getOrNull(SemanticsProperties.CollectionItemInfo)?.columnIndex == 0
        }
        composeTestRule.onAllNodes(hasItemInfo).assertCountEquals(10)

        repeat(10) { index ->
            composeTestRule.onNode(
                SemanticsMatcher("rowIndex = $index") {
                    it.config.getOrNull(SemanticsProperties.CollectionItemInfo)?.rowIndex == index
                }
            ).assertExists()
        }
    }
}