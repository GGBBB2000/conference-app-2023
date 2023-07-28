package io.github.droidkaigi.confsched2023.testing.robot

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performClick
import com.github.takahirom.roborazzi.captureRoboImage
import io.github.droidkaigi.confsched2023.main.MainScreenTab
import io.github.droidkaigi.confsched2023.testing.RobotTestRule
import kotlinx.coroutines.test.TestDispatcher
import javax.inject.Inject

class KaigiAppRobot @Inject constructor(
    private val testDispatcher: TestDispatcher,
) {

    @Inject lateinit var robotTestRule: RobotTestRule

    @Inject lateinit var timetableScreenRobot: TimetableScreenRobot
    private lateinit var composeTestRule: AndroidComposeTestRule<*, *>
    operator fun invoke(
        block: KaigiAppRobot.() -> Unit,
    ) {
        this.composeTestRule = robotTestRule.composeTestRule
        waitUntilIdle()
        block()
    }

    fun capture() {
        composeTestRule
            .onNode(isRoot())
            .captureRoboImage()
    }

    fun goToContributor() {
        composeTestRule
            .onNode(hasTestTag(MainScreenTab.Contributor.testTag))
            .performClick()
        waitUntilIdle()
    }

    fun waitUntilIdle() {
        composeTestRule.waitForIdle()
        testDispatcher.scheduler.advanceUntilIdle()
    }
}
