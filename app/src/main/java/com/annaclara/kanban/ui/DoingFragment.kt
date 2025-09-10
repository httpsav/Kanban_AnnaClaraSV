package com.annaclara.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.annaclara.kanban.data.model.Status
import com.annaclara.kanban.databinding.FragmentDoingBinding
import com.annaclara.kanban.data.model.Task
import com.annaclara.kanban.ui.adapter.TaskAdapter

class DoingFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view, savedInstanceState)

        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()){ task, option -> optionSelected(task,option)}

        with(binding.recyclerviewTask){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                Toast.makeText(requireContext(), "Movendo para Feito: ${task.description}", Toast.LENGTH_SHORT).show()
            }
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Movendo para A Fazer: ${task.description}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
            Task(
                id = "301",
                description = "Atualizar e validar a interface de login para diferentes tipos de usuários",
                status = Status.DOING
            ),
            Task(
                id = "302",
                description = "Redesenhar a página inicial com foco nos pratos em destaque",
                status = Status.DOING
            ),
            Task(
                id = "303",
                description = "Implementar controle de permissões baseado em perfil de usuário",
                status = Status.DOING
            ),
            Task(
                id = "304",
                description = "Executar testes de logout e garantir encerramento de sessão seguro",
                status = Status.DOING
            ),
            Task(
                id = "305",
                description = "Melhorar a performance no carregamento de pratos e categorias no menu digital",
                status = Status.DOING
            )
        )

        taskAdapter.submitList(taskList)
    }
}