package com.pablo.tvschedule.presentation.home.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pablo.tvschedule.presentation.home.HomeInteraction
import java.time.LocalDate
import java.time.ZoneOffset


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    showDatePickerDialog: Boolean,
    currentDate: LocalDate,
    homeInteraction: (HomeInteraction) -> Unit
) {
    if (!showDatePickerDialog) return

    val state = rememberDatePickerState(
        initialSelectedDateMillis = currentDate
            .atStartOfDay()
            .toInstant(ZoneOffset.MIN)
            .toEpochMilli()
    )

    DatePickerDialog(
        onDismissRequest = {
            homeInteraction(HomeInteraction.HideDatePicker())
        },
        confirmButton = {
            TextButton(onClick = {
                state.selectedDateMillis?.let { millis ->
                    homeInteraction(HomeInteraction.ChangeScheduleDate(millis))
                }
                homeInteraction(HomeInteraction.HideDatePicker())
            }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                homeInteraction(HomeInteraction.HideDatePicker())
            }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = state)
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeDatePickerDialogPreview() {
    CustomDatePickerDialog(
        showDatePickerDialog = true,
        currentDate = LocalDate.now()
    ) { }
}
