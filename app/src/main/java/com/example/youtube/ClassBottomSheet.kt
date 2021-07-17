package com.example.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube.databinding.BottomDialogVideoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ClassBottomSheet() : BottomSheetDialogFragment() {
    private var _binding : BottomDialogVideoBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetOptionAdapter: DataBottomSheetOptionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = BottomDialogVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dataList = ArrayList<DataBottomSheetOption>()
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "나중에 볼 동영상에 저장"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "재생목록에 저장"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "동영상 오프라인 저장"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "공유"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "관심없음"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "채널 추천 안함"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_fire, "신고"))

        bottomSheetOptionAdapter = DataBottomSheetOptionAdapter((activity as MainActivity), dataList)
        binding.listView.adapter = bottomSheetOptionAdapter

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}