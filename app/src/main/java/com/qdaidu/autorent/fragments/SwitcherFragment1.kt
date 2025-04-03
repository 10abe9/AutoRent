package com.qdaidu.autorent.fragments

import CardAdapter
import CardItem
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.SwitcherFragment1Binding

class SwitcherFragment1 : Fragment(R.layout.switcher_fragment_1) {

    private var _binding: SwitcherFragment1Binding? = null
    private val binding get() = _binding!!

    // Исходный список карточек
    private lateinit var allItems: List<CardItem>
    private lateinit var adapter: CardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SwitcherFragment1Binding.bind(view)

        // Навигация (к примеру, по клику на imageView7 и imageView8)
        binding.imageView7.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment1_to_switcherFragment2)
        }
        binding.imageView8.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment1_to_switcherFragment3)
        }

        // Настраиваем RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        allItems = listOf(
            CardItem(
                title = "S700 Sedan",
                description = "Luxeed",
                subtitle = "2500 в день",
                imagePath = R.drawable.car_card1,
                oil = "Бензин"

            ),
            CardItem(
                title = "S700 Sedan",
                description = "Brabus",
                subtitle = "3500 в день",
                imagePath = R.drawable.car_card2,
                oil = "Бензин"
            ),
            CardItem(
                title = "S700 Sedan",
                description = "Luxeed",
                subtitle = "4500 в день",
                imagePath = R.drawable.car_card3,
                oil = "Бензин"
            ),
            CardItem(
                title = "S700 Sedan",
                description = "Brabus",
                subtitle = "2500 в день",
                imagePath = R.drawable.car_card2,
                oil = "Дизель"
            ),
            CardItem(
                title = "S700 Sedan",
                description = "Luxeed",
                subtitle = "5500 в день",
                imagePath = R.drawable.car_card3,
                oil = "A/T"
            )
        )
        adapter = CardAdapter(allItems)
        binding.recyclerView.adapter = adapter

        // Добавляем TextWatcher для поля ввода поиска
        binding.editTextText4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s?.toString()?.trim()?.lowercase() ?: ""
                if (query.isEmpty()) {
                    // Если поиск пустой, показываем все карточки
                    adapter.updateList(allItems)
                } else {
                    // Фильтруем карточки по title, description и subtitle (без учета регистра)
                    val filtered = allItems.filter { item ->
                        item.title.lowercase().contains(query) ||
                                item.description.lowercase().contains(query) ||
                                item.subtitle.lowercase().contains(query)
                    }
                    adapter.updateList(filtered)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
