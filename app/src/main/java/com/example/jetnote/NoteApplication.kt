package com.example.jetnote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Creates dependency container at top level
@HiltAndroidApp
class NoteApplication: Application() {}