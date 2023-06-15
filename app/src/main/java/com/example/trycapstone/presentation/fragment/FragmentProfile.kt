package com.example.trycapstone.presentation.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.trycapstone.MainActivity
import com.example.trycapstone.User
import com.example.trycapstone.presentation.auth.SignInActivity
import com.example.trycapstone.databinding.FragmentProfileBinding
import com.example.trycapstone.presentation.vm.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class FragmentProfile : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var storageRef = Firebase.storage
    private val cameraPermission = android.Manifest.permission.CAMERA
    private val storagePermission = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private val mediaPermission = android.Manifest.permission.ACCESS_MEDIA_LOCATION
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_GALLERY = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        firebaseAuth = FirebaseAuth.getInstance()
        storageRef = FirebaseStorage.getInstance()

        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(requireActivity(), SignInActivity::class.java))
        }

        binding.imageView3.setOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        binding.imageUsers.setOnClickListener {
            dispatchTakePicture()
        }

        viewModel.imageBitmap.observe(requireActivity()){
            if(it!=null)
                Glide.with(binding.imageUsers)
                    .load(it)
                    .into(binding.imageUsers)
        }

        viewModel.userData.observe(requireActivity()){
            if (it!=null)
                setupView(it)
        }

        binding.imageUsers.setOnClickListener {
            saveData()
        }

        binding.imageUsers.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), cameraPermission) == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePicture()
            } else {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(cameraPermission), CAMERA_PERMISSION_REQUEST_CODE)
            }

        }

    }

    private fun saveData() {
        val user = User(
            email = binding.editText.text.toString(),
            username = binding.profileName.text.toString(),
            image = viewModel.userData.value?.image,
        )
        if(viewModel.imageBitmap.value!=null)
            viewModel.updateProfile(user, viewModel.imageBitmap.value!!)
        else
            viewModel.updateProfile(user)
    }

    private fun setupView(user: User) {
        with(binding){
            Glide.with(binding.imageUsers).load(user.image).into(binding.imageUsers)
            profileName.setText(user.username)
            editText.setText(user.email)
        }
    }

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            viewModel.storeImage(imageBitmap,100)
        }
    }
    val documentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        val contentResolver = requireActivity().contentResolver
        val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        viewModel.storeImage(imageBitmap, 100)
    }


    fun dispatchTakePicture() {
        val pickImage = "Pick Image"
        val takePhoto = "Take Photo"

        val options = arrayOf<CharSequence>(pickImage, takePhoto)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Select Image Source")
        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                pickImage -> {
                    if (ContextCompat.checkSelfPermission(requireActivity(), storagePermission) != PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(requireActivity(),mediaPermission)) != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(storagePermission,mediaPermission),
                            4
                        )
                    } else {
                        // Permission granted, launch file picker intent
                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*" // only allow image file types
                        documentLauncher.launch(intent.type)
                    }
                }
                takePhoto -> {
                    if (ContextCompat.checkSelfPermission(
                            requireActivity(),
                            cameraPermission
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // Permission not granted, request it
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(cameraPermission),
                            6
                        )
                    } else {
                        // Permission granted, launch camera intent
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        resultLauncher.launch(intent)
                    }
                }
            }
        }
        builder.show()
    }
}