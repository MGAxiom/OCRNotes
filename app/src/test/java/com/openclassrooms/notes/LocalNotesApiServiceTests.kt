package com.openclassrooms.notes

import com.openclassrooms.notes.service.LocalNotesApiService
import com.openclassrooms.notes.model.Note
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocalNotesApiServiceTest {

    private lateinit var localNotesApiService: LocalNotesApiService

    @Before
    fun setup() {
        localNotesApiService = LocalNotesApiService()
    }

    @Test
    fun `getAllNotes returns correct number of notes`() {
        val notes = localNotesApiService.getAllNotes()
        assertEquals(10, notes.size)
    }

    @Test
    fun `getAllNotes returns correct first note`() {
        val notes = localNotesApiService.getAllNotes()
        val firstNote = notes.first()
        assertEquals("La vie est belle", firstNote.title)
        assertEquals("La vie est belle, pleine de choses à voir et à faire. Profitez de chaque moment et ne laissez jamais personne vous dire que vous ne pouvez pas faire ce que vous voulez.", firstNote.body)
    }

    @Test
    fun `getAllNotes returns correct last note`() {
        val notes = localNotesApiService.getAllNotes()
        val lastNote = notes.last()
        assertEquals("Risez et amusez-vous.", lastNote.title)
        assertEquals("La vie est trop courte pour être sérieuse tout le temps. Riez et amusez-vous. Passez du temps à faire les choses que vous aimez.", lastNote.body)
    }

    @Test
    fun `getAllNotes returns list with expected titles`() {
        val notes = localNotesApiService.getAllNotes()
        val expectedTitles = listOf(
            "La vie est belle",
            "Ne laissez personne vous dire que vous ne pouvez pas faire quelque chose.",
            "Suivez vos rêves",
            "Soyez gentil avec les autres",
            "Aidez les autres",
            "Soyez reconnaissant pour ce que vous avez.",
            "Vivez le moment présent",
            "Prenez soin de vous",
            "Passez du temps avec vos proches",
            "Risez et amusez-vous."
        )
        assertEquals(expectedTitles, notes.map { it.title })
    }

    @Test(expected = NotImplementedError::class)
    fun `addNote throws NotImplementedError`() {
        localNotesApiService.addNote(Note("Test Title", "Test Body"))
    }
}