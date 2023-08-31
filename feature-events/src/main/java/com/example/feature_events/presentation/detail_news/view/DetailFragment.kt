package com.example.feature_events.presentation.detail_news.view

import android.Manifest
import android.app.Application
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.WorkManager
import com.example.common.dialog
import com.example.common.toIntOrDefault
import com.example.core.entity.CategoryDetail
import com.example.core.entity.EventEntity
import com.example.core.repository.db.SimbaDataBase
import com.example.feature_events.R
import com.example.feature_events.databinding.DialogHelpManyBinding
import com.example.feature_events.databinding.FragmentDetailBinding
import com.example.feature_events.entity.DonateItems
import com.example.feature_events.presentation.detail_news.adapter.category_detail_adapter.CategoryDetailAdapter
import com.example.feature_events.presentation.detail_news.adapter.donate_adapter.DonateAdapter
import com.example.feature_events.presentation.detail_news.adapter.friends_detail_adapter.FriendsDetailAdapter
import com.example.feature_events.presentation.detail_news.adapter.image_adapter.ImageDetailAdapter
import com.example.feature_events.service.DonatWorkManager
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailBinding == null")

    private val args: DetailFragmentArgs by navArgs()
    private var dialog: Dialog? = null

    @Inject
    lateinit var db: SimbaDataBase

    @Inject
    lateinit var application: Application

    private var sum = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.run {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        val event = args.event
        binding.run {
            title.text = event.title
            description.text = event.description
            date.text = event.startDate.toString()
            countFriends.text = "3"
            street.text = event.street
            phone.text = event.phone
            setDefaultAdapter()
        }
    }

    private fun setDefaultAdapter() {
        binding.recyclerImageDetail.adapter = ImageDetailAdapter(args.event.listImage)

        binding.rvHelpDetail.adapter = CategoryDetailAdapter(
            listOf(
                CategoryDetail(getString(R.string.help_shirt), R.drawable.shirt),
                CategoryDetail(getString(R.string.state_hands), R.drawable.hands),
                CategoryDetail(getString(R.string.prof_help), R.drawable.tools),
                CategoryDetail(getString(R.string.help_many), R.drawable.coins)
            )
        ) {
            dialogHelpToMany()
        }
        binding.rvHelpDetail.layoutManager =
            GridLayoutManager(requireContext(), 4)

        val listFriends = listOf(1, 2, 3, 4, 5, 6, 7)
        binding.recyclerFriendsDetail.adapter = FriendsDetailAdapter(listFriends.take(3))
        binding.countFriends.text = "+${listFriends.size}"
    }

    private fun dialogHelpToMany() {
        dialog = dialog(
            requireActivity(),
            DialogHelpManyBinding.inflate(layoutInflater).apply {

                tvTitle.text = getString(R.string.help_for_donate)
                tvDescription.text = getString(R.string.choose_size_donate)
                tvDescription2.text = getString(R.string.choose_size_donate_2)

                btnCancel.setOnClickListener {
                    dialog?.dismiss()
                }

                rvItemsDonation.adapter =
                    DonateAdapter(requireContext(), createDonateItem()) { selectSum ->
                        edSumDonate.text?.clear()
                        sum = selectSum
                    }

                edSumDonate.addTextChangedListener {
                    (rvItemsDonation.adapter as DonateAdapter).apply {
                        updateAllItems()
                    }
                    sum = it.toIntOrDefault(0)
                }

                btnSend.setOnClickListener {
                    if (sum in 1..99999998) {
                        validPermissionWorker(args.event, sum)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.valid_donate), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            false
        )

        dialog?.setOnDismissListener {
            dialog = null
        }

        dialog?.show()
    }

    private val postNotificationsPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                startWorker(args.event, sum)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Не удалось получить разрешение на уведомление!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun validPermissionWorker(event: EventEntity, sum: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) ==
                PackageManager.PERMISSION_DENIED
            ) {
                postNotificationsPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            } else {
                startWorker(event, sum)
            }
        }
    }

    private fun startWorker(event: EventEntity, sum: Int) {
        val worker = WorkManager.getInstance(requireActivity())
        worker.enqueue(
            DonatWorkManager.makeRequest(event.id, event.title, sum)
        )
    }

    private fun createDonateItem(): List<DonateItems> {
        return listOf(
            DonateItems(1, 500, false),
            DonateItems(2, 1000, false),
            DonateItems(3, 2000, false),
            DonateItems(4, 3000, false)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}