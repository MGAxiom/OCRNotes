package com.openclassrooms.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.openclassrooms.notes.databinding.ActivityMainBinding
import com.openclassrooms.notes.modules.appModule
import com.openclassrooms.notes.viewmodel.OCRViewModel
import com.openclassrooms.notes.widget.NoteItemDecoration
import com.openclassrooms.notes.widget.NotesAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main activity for the app.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: OCRViewModel by viewModel()

    /**
     * The binding for the main layout.
     */
    private lateinit var binding: ActivityMainBinding

    private val notesAdapter = NotesAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initFABButton()
        binding.recycler.adapter = notesAdapter
        lifecycleScope.launch {
            viewModel.notes.collect() { notes ->
                notesAdapter.updateNotes(notes)
            }
        }
    }

    /**
     * Initializes the FAB button.
     */
    private fun initFABButton() {
        binding.btnAdd.setOnClickListener {
            MaterialAlertDialogBuilder(this).apply {
                setTitle(R.string.coming_soon)
                setMessage(R.string.not_available_yet)
                setPositiveButton(android.R.string.ok, null)
            }.show()
        }
    }

    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {
        with(binding.recycler) {
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.default_margin),
                    resources.getInteger(R.integer.span_count)
                )
            )

            adapter = notesAdapter
        }

    }

}
