package com.example.jetnote.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.model.NoteModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = maxLines,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@SuppressLint("WeekBasedYear")
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onNoteClicked: (NoteModel) -> Unit
) {
    Surface(modifier = modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(10.dp)),
        color = Color.LightGray) {
        Column(modifier = modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
            Row(modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(modifier = modifier.align(Alignment.CenterVertically), text = note.title, style = MaterialTheme.typography.titleLarge)
                Icon(modifier = modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onNoteClicked(note) }, imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.DarkGray)
            }
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)
            val df = SimpleDateFormat.getDateTimeInstance()

            Text(text = df.format(note.entryDate), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun Preview() {
    NoteRow(note = NoteModel(title ="Hi", description ="Text"), onNoteClicked = { })
}