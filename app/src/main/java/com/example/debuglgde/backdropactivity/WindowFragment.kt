package com.example.debuglgde.backdropactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.debuglgde.Card
import com.example.debuglgde.CardAdapter
import com.example.debuglgde.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_window.*

class WindowFragment : BottomSheetDialogFragment(), OnBottomSheetCallbacks {

    private var currentState: Int = BottomSheetBehavior.STATE_EXPANDED

    private var adapter: CardAdapter? = null

    var cardsCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as BackdropActivity).setOnBottomSheetCallbacks(this)

        return inflater.inflate(R.layout.fragment_window, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textResult.setOnClickListener {
            (activity as BackdropActivity).openBottomSheet()
        }

        filterImage.setOnClickListener {
            if (currentState == BottomSheetBehavior.STATE_EXPANDED) {
                (activity as BackdropActivity).closeBottomSheet()
            } else  {
                (activity as BackdropActivity).openBottomSheet()
            }
        }

        adapter = CardAdapter(getCards())
        rvCards.apply {
            this.adapter = this@WindowFragment.adapter
            textResult.text = "$cardsCount results"
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onStateChanged(bottomSheet: View, newState: Int) {
        currentState = newState
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                textResult.text = "$cardsCount results"
                filterImage.setImageResource(R.drawable.ic_round_filter_list_24)
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                textResult.text = "See the results"
                filterImage.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24)
            }
        }
    }

    private fun getCards() : MutableList<Card>{
        val cardsList = arrayListOf(
            Card("Raqueta", "Wilson", "Ultra liviana"),
            Card("Mochila", "Head", "20Lts"),
            Card("Bolso", "Adidad", "Botinero rojo con 6 bolsillos"),
            Card("Pelotas", "Toalson", "Set x3"),
            Card("Gorra", "Nike", "Azul edicion Roger Federer"),
            Card("Short", "Topper", "50% OFF")
        )
        cardsCount = cardsList.size
        return cardsList
    }
}