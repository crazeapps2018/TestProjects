package com.it.testprojects.firebase

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.it.testprojects.databinding.ActivityFirebaseBinding
import java.io.IOException
import java.util.*


class FirebaseActivity : AppCompatActivity() {

    private var _binding: ActivityFirebaseBinding? = null
    private val binding get() = _binding!!

    // Uri indicates, where the image will be picked from
    private var filePath: Uri? = null


    // instance for firebase storage and StorageReference
    private var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(
            Color.parseColor("#0F9D58")
        )
        actionBar!!.setBackgroundDrawable(colorDrawable)

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance()
        storageReference = storage?.reference


        binding.btnChoose.setOnClickListener {
            selectImage()
        }

        binding.btnUpload.setOnClickListener {
            uploadImage()
        }

    }

    private fun uploadImage() {

        // Code for showing progressDialog while uploading
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        // Defining the child of storageReference
        val ref = storageReference!!.child("images/" + UUID.randomUUID().toString())

        // adding listeners on upload
        // or failure of image
        ref.putFile(filePath!!).addOnSuccessListener {
            // Image uploaded successfully
            // Dismiss dialog
            progressDialog.dismiss();
            Toast.makeText(this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
        }.addOnFailureListener {
            progressDialog.dismiss();
            Toast.makeText(this, "Failed " + it.message, Toast.LENGTH_SHORT).show();
        }.addOnProgressListener {
            val progress: Double = (100.0 * it.bytesTransferred / it.totalByteCount)
            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
        }

    }

    private fun selectImage() {

        // Defining Implicit Intent to mobile gallery
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                // Get the Uri of data
                filePath = data!!.data
                try {

                    // Setting image on image view using Bitmap
                    val bitmap = MediaStore.Images.Media
                        .getBitmap(
                            contentResolver,
                            filePath
                        )
                    binding.imgView.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    // Log the exception
                    e.printStackTrace()
                }
            }
        }


}