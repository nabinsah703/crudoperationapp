package com.example.crudoperationofroom.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.crudoperationofroom.R
import com.example.crudoperationofroom.model.User
import com.example.crudoperationofroom.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class updateFragment : Fragment() {

    private val args by navArgs<updateFragmentArgs>()
      private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.updatefirstname.setText(args.currentUser.firstName)
        view.updatelastname.setText(args.currentUser.lastName)
        view.updateuserage.setText(args.currentUser.age.toString())
        view.updatebutton.setOnClickListener{
            updateItem()

        }

        // Add menu
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(){
        val firstName = updatefirstname.text.toString()
        val lastName = updatelastname.text.toString()
        val age  = Integer.parseInt(updateuserage.text.toString())

        if (inputCheck(firstName,lastName,updateuserage.text)){


            // Create User object
            val updateUser = User(args.currentUser.id,firstName,lastName,age)
            // Update Current User
            mUserViewModel.updateUser(updateUser)

            Toast.makeText(requireContext(),"update successfully",Toast.LENGTH_LONG).show()
            // navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(),"error update",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String,lastName:String,age:Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete)
            deleteUser()

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_,->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_,->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }

}