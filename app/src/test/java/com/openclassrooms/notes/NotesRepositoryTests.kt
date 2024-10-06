package com.openclassrooms.notes

import com.openclassrooms.notes.model.Note
import com.openclassrooms.notes.repository.NotesRepository
import com.openclassrooms.notes.service.NotesApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NotesRepositoryTests {

    @Mock
    private lateinit var mockNotesApiService: NotesApiService

    private lateinit var notesRepository: NotesRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        notesRepository = NotesRepository()
        notesRepository.notesApiService = mockNotesApiService
    }

    @Test
    fun `initial state of notes is empty`() = runTest {
        val initialNotes = notesRepository.notes.first()
        assertEquals(emptyList<Note>(), initialNotes)
    }

    @Test
    fun `loadNotes updates notes state flow`() = runTest {
        val testNotes = listOf(
            Note("Test Title 1", "Test Body 1"),
            Note("Test Title 2", "Test Body 2")
        )
        `when`(mockNotesApiService.getAllNotes()).thenReturn(testNotes)

        notesRepository.loadNotes()

        val loadedNotes = notesRepository.notes.first()
        assertEquals(testNotes, loadedNotes)
    }
}