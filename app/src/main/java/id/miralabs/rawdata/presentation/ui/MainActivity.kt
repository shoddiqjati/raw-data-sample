package id.miralabs.rawdata.presentation.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.miralabs.rawdata.R
import id.miralabs.rawdata.databinding.ActivityMainBinding
import id.miralabs.rawdata.data.dto.Type
import id.miralabs.rawdata.presentation.vm.MainViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        collectMainUiState()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadTypeList()
    }

    private fun collectMainUiState() = lifecycleScope.launchWhenStarted {
        viewModel.typeListUiState.collect {
            when (it) {
                is MainViewModel.MainUiState.Loading -> binding.setShowContentState(true)
                is MainViewModel.MainUiState.Loaded -> binding.onDataLoaded(it.typeList)
            }
        }
    }

    private fun ActivityMainBinding.onDataLoaded(typeList: List<Type>) {
        setOptionsData(typeList)
        setShowContentState()
    }

    @SuppressLint("SetTextI18n")
    private fun ActivityMainBinding.setOptionsData(typeList: List<Type>) {
        val labelList = typeList.map { it.label }
        val labelsAdapter = ArrayAdapter(this@MainActivity, R.layout.item_option, labelList)
        (tfOptions.editText as? AutoCompleteTextView)?.run {
            setAdapter(labelsAdapter)
            setOnItemClickListener { _, _, position, _ ->
                tvSelected.text = "Option Selected ${labelList[position]}"
            }
        }
    }

    private fun ActivityMainBinding.setShowContentState(isLoading: Boolean = false) {
        progressBar.isIndeterminate = isLoading
        progressBar.isVisible = isLoading
        tfOptions.isVisible = isLoading.not()
        tvSelected.isVisible = isLoading.not()
    }
}