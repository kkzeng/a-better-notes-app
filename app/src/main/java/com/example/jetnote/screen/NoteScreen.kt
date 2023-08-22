package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.components.NoteRow
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.NoteModel

private fun String.isValid(): Boolean {
    return this.all { it.isLetterOrDigit() || it.isWhitespace() }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    notesList: List<NoteModel>,
    onNoteAdded: (NoteModel) -> Unit,
    onNoteRemoved: (NoteModel) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = { Icon(imageVector = Icons.Rounded.Notifications, contentDescription ="View notifications" ) },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding(),
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp),
                    text = title,
                    label = "Title",
                    onTextChange = { input ->
                        if (input.isValid()) {
                            title = input
                        }
                    }
                )

                NoteInputText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 8.dp),
                    text = description,
                    label = "Add a note",
                    onTextChange = { input ->
                        if (input.isValid()) {
                            description = input
                        }
                    }
                )

                val context = LocalContext.current

                NoteButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 10.dp),
                    text = "Save",
                    enabled = title.isNotEmpty() && description.isNotEmpty(),
                    onClick = {
                        onNoteAdded(NoteModel(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                )

                Divider(modifier = Modifier.fillMaxWidth())

                LazyColumn(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
                    items(notesList.size) { index ->
                        val note = notesList[index]
                        NoteRow(
                            note = note,
                            onNoteClicked = onNoteRemoved
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NotesScreen(NotesDataSource.loadNotes(), {}, {})
}