package com.it.testprojects.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.it.testprojects.BuildConfig
import com.it.testprojects.R
import com.it.testprojects.databinding.FragmentRegisterBinding
import com.it.testprojects.notesapp.AuthViewModel
import com.it.testprojects.notesapp.models.UserRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)

        binding.btnSignUp.setOnClickListener {
          //  findNavController().navigate(R.id.action_registerFragment_to_mainFragment2)
            authViewModel.registerUser(UserRequest("KS","mailer3@.com","11111"))

        }

       BuildConfig.CONSUMER_KEY

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}