package com.annaclara.kanban.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.annaclara.kanban.data.model.Status
import com.annaclara.kanban.databinding.FragmentDoneBinding
import com.annaclara.kanban.data.model.Task
import com.annaclara.kanban.ui.adapter.TaskAdapter


class DoneFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view, savedInstanceState)

        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext(),){ task, option -> optionSelected(task,option)}
        binding.recyclerviewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewTask.setHasFixedSize(true)
        binding.recyclerviewTask.adapter = taskAdapter
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
                id = "201",
                description = "Revisar e concluir a documentação técnica do sistema de pedidos",
                status = Status.DONE
            ),
            Task(
                id = "202",
                description = "Desenvolver sistema de alertas para confirmações de reservas e entregas",
                status = Status.DONE
            ),
            Task(
                id = "203",
                description = "Lançar a primeira versão do aplicativo do restaurante na Play Store",
                status = Status.DONE
            ),
            Task(
                id = "204",
                description = "Design final e oficial da identidade visual do app",
                status = Status.DONE
            ),
            Task(
                id = "205",
                description = "Estabelecer conexão com APIs de pagamento (Pix, cartões, etc.)",
                status = Status.DONE
            ),
        )
        taskAdapter.submitList(taskList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}