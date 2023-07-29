package com.example.projectjavasimba.presentation.helpFragment.view

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
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectjavasimba.R
import com.example.projectjavasimba.common.utils.SESSION_CATEGORY
import com.example.projectjavasimba.common.utils.hide
import com.example.projectjavasimba.common.utils.isFirstEnter
import com.example.projectjavasimba.common.utils.show
import com.example.projectjavasimba.domain.entity.Category
import com.example.projectjavasimba.databinding.FragmentHelpragmentBinding
import com.example.projectjavasimba.presentation.adapter.MessageAdapter.MessageAdapter
import com.example.projectjavasimba.presentation.adapter.placeholder.PlaceHolderAdapter
import com.example.projectjavasimba.presentation.helpFragment.adapter.HelperAdapter
import com.example.projectjavasimba.presentation.helpFragment.viewmodel.HelpViewModel
import com.example.projectjavasimba.service.ServiceGetData


class HelpFragment : Fragment(), ServiceGetData.CallbackData<Category> {

    private var _binding: FragmentHelpragmentBinding? = null
    private val binding: FragmentHelpragmentBinding
        get() = _binding ?: throw RuntimeException()

    private val viewModel: HelpViewModel by activityViewModels()
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