package com.example.ui

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.example.ui.model.AppLanguage
import com.example.ui.screens.MainScreen
import com.example.ui.viewmodel.FuelUiState
import com.example.ui.viewmodel.FuelViewModel
import com.example.util.AppStrings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class DeleteAllDialogUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `confirmation input field is completely empty when permanent deletion dialog opens`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val viewModel = FuelViewModel(context as android.app.Application)

        viewModel.openClearAllStep1()
        viewModel.proceedToClearAllStep2()

        composeTestRule.setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            MainScreen(
                viewModel = viewModel,
                uiState = uiState
            )
        }

        // Verify permanent deletion dialog is displayed
        composeTestRule.onNodeWithTag("clear_all_step2_dialog")
            .assertIsDisplayed()

        // Requirement 1 & 8: Verify confirmation input field is displayed and completely empty
        composeTestRule.onNodeWithTag("clear_all_confirmation_input")
            .assertIsDisplayed()
            .assertTextEquals("")

        // Requirement 5: Verify permanent delete button is initially disabled
        composeTestRule.onNodeWithTag("clear_all_step2_confirm")
            .assertIsNotEnabled()

        // Perform text input "DELETE"
        composeTestRule.onNodeWithTag("clear_all_confirmation_input")
            .performTextInput("DELETE")

        // Requirement 5 & 6: Verify button becomes enabled when exact "DELETE" is typed
        composeTestRule.onNodeWithTag("clear_all_step2_confirm")
            .assertIsEnabled()
    }

    @Test
    fun `confirmation text instructions are present outside input field for all languages`() {
        for (lang in AppLanguage.values()) {
            val msg = AppStrings.clearAllStep2Msg(lang)
            val expectedInstruction = when (lang) {
                AppLanguage.ENG -> "Type DELETE to confirm."
                AppLanguage.CZ -> "Pro potvrzení napište DELETE."
                AppLanguage.ESP -> "Escribe DELETE para confirmar."
                AppLanguage.DE -> "Geben Sie zur Bestätigung DELETE ein."
            }
            assertTrue("Instruction for $lang should be contained in clearAllStep2Msg", msg.contains(expectedInstruction))
        }
    }
}
