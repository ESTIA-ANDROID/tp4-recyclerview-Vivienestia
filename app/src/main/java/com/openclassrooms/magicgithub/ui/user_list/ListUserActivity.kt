package com.openclassrooms.magicgithub.ui.user_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ActivityListUserBinding
import com.openclassrooms.magicgithub.di.Injection.getRepository
import com.openclassrooms.magicgithub.model.User

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {

    // -- ViewBinding --
    private lateinit var binding: ActivityListUserBinding

    // -- Adaptateur --
    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2) Configuration
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // -- CONFIGURATION --
    private fun configureRecyclerView() {
        adapter = UserListAdapter(this)
        binding.activityListUserRv.adapter = adapter

        // Configuration ItemTouchHelper (drag & swipe)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,  // Directions pour le drag
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // Directions pour le swipe
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Réordonner la liste si on déplace la cellule
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                getRepository().swipeUser(fromPosition, toPosition)
                loadData()
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Récupérer l’utilisateur swipé
                val position = viewHolder.adapterPosition
                val user = adapter.getItem(position)
                getRepository().active(user)
                //loadData()
                adapter.notifyItemChanged(position)
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.activityListUserRv)
    }

    private fun configureFab() {
        binding.activityListUserFab.setOnClickListener {
            getRepository().addRandomUser()
            loadData()
        }
    }

    private fun loadData() {
        adapter.updateList(getRepository().getUsers())
    }

    // -- ACTIONS --
    override fun onClickDelete(user: User) {
        Log.d(ListUserActivity::class.java.name, "User tries to delete an item.")
        getRepository().deleteUser(user)
        loadData()
    }
}
