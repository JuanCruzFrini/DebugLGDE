package com.example.debuglgde

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.debuglgde.databinding.BackdropContentActivityBinding
import com.example.debuglgde.databinding.CardViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {

    private var binding: BackdropContentActivityBinding? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = BackdropContentActivityBinding.inflate(LayoutInflater.from(activity))
        binding?.let {

            val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
            bottomSheetDialog.setContentView(it.root)
            bottomSheetBehavior = BottomSheetBehavior.from(it.root.parent as View)//.apply { state = BottomSheetBehavior.STATE_COLLAPSED }
            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            return bottomSheetDialog
        }
    return super.onCreateDialog(savedInstanceState)
    }
}
