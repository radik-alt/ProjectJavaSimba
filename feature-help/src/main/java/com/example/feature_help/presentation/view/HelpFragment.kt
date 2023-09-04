package com.example.feature_help.presentation.view

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.common.SESSION_CATEGORY
import com.example.common.hide
import com.example.common.isFirstEnter
import com.example.core.entity.Category
import com.example.feature_help.databinding.FragmentHelpragmentBinding
import com.example.base.MessageAdapter.MessageAdapter
import com.example.base.placeholder.PlaceHolderAdapter
import com.example.common.show
import com.example.feature_help.R
import com.example.feature_help.di.HelpComponentProvider
import com.example.feature_help.presentation.adapter.HelperAdapter
import com.example.feature_help.presentation.viewmodel.HelpViewModel
import com.example.feature_help.service.ServiceGetData
import javax.inject.Inject


class HelpFragment : Fragment(), ServiceGetData.CallbackData<Category> {

    private var _binding: FragmentHelpragmentBinding? = null
    private val binding: FragmentHelpragmentBinding
        get() = _binding ?: throw RuntimeException()

    private val component by lazy {
        (requireActivity().application as HelpComponentProvider).provideHelpComponent()
    }

    @Inject
    lateinit var viewModel: HelpViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onResume() {
        super.onResume()
        if (binding.rvHelper.adapter == null) {
            observable()
            val session = isFirstEnter(requireContext(), SESSION_CATEGORY)
            viewModel.getParseListCategory(session)
            binding.rvHelper.adapter = PlaceHolderAdapter()
            binding.selectCategoryTitle.hide()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHelpragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.toolbarHelper) {
            text.text = getString(R.string.help)
            create.visibility = View.GONE
        }
    }

    private fun observable() = with(viewModel) {
        listCategory.observe(this@HelpFragment) { listCategory ->
            binding.selectCategoryTitle.show()
            binding.rvHelper.adapter = HelperAdapter(listCategory)
            binding.rvHelper.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        messageError.observe(this@HelpFragment) { message ->
            binding.selectCategoryTitle.hide()
            binding.rvHelper.adapter = MessageAdapter(message)
        }
    }

    private fun startService() {
        val serviceIntent = Intent(requireContext(), ServiceGetData::class.java)
        requireContext().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as? ServiceGetData.LocalBinder
            binder?.getService().let { service ->
                service?.callbackCategory = this@HelpFragment
                service?.getDataCategory()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {}
    }

    override fun onDataReceived(list: List<Category>) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}