package com.annaclara.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.annaclara.kanban.R
import com.annaclara.kanban.data.model.Status
import com.annaclara.kanban.databinding.FragmentTodoBinding
import com.annaclara.kanban.data.model.Task
import com.annaclara.kanban.ui.adapter.TaskAdapter


class TodoFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view, savedInstanceState)
        initListeners()

        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners() {
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTaskFragment)
        }
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()){ task, option -> optionSelected(task,option)}
        with(binding.recyclerviewTask){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }

    }

    private fun optionSelected(task: Task, option: Int) {
        when (option) {
            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task(id = "101", description = "Adicionar funcionalidade de reserva online por horário", status = Status.TODO),

            Task(id = "102", description = "Criar protótipo visual da interface do cardápio digital", status = Status.TODO),

            Task(id = "103", description = "Integrar banco de dados com sistema de categorias e itens do menu", status = Status.TODO),

            Task(id = "104", description = "Melhorar segurança e performance do serviço de autenticação", status = Status.TODO),

            Task(id = "105", description = "Desenvolver guia interativo para uso do app pelos garçons", status = Status.TODO)

        )

        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}