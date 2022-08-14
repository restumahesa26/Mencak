package com.example.mencak.ui.home.ui.scan

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mencak.R
import com.example.mencak.databinding.FragmentScanBinding
import com.example.mencak.ml.FoodModelML
import com.example.mencak.model.FoodModel
import com.example.mencak.ui.detail.DetailActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null

    private val binding get() = _binding!!

    private val imageSize = 299

    private val foodList = arrayOf("Pecel Lele", "Gorengan", "Nasi Goreng")

    private var listFood = ArrayList<FoodModel>()
    private var data = FoodModel("", "", 0f, "", "", "", "")

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private val FILENAME_FORMAT = "dd-MMM-yyyy"

    private val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun classifyImage(bitmap: Bitmap) {
        val resize = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false)
        val foodModel = FoodModelML.newInstance(requireContext())

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 299, 299, 3), DataType.FLOAT32)
//        var tBuffer = TensorImage.fromBitmap(resize)
//        var byteBuffer = tBuffer.buffer
//
//        inputFeature0.loadBuffer(byteBuffer)
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(imageSize * imageSize)
        resize.getPixels(intValues, 0, resize.width, 0, 0, resize.width, resize.height)

        var pixel = 0
        for (i in 0 until imageSize) {
            for (j in 0 until imageSize) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 1))
                byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 1))
                byteBuffer.putFloat((value and 0xFF) * (1f / 1))
            }
        }

        inputFeature0.loadBuffer(byteBuffer)

        val outputs = foodModel.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val confidence = outputFeature0.floatArray
        var maxPos = 0
        var maxConfidence = 0f
        for (i in 0 until confidence.size) {
            if (confidence[i] > maxConfidence) {
                maxConfidence = confidence[i]
                maxPos = i
            }
        }

        val dataName = resources.getStringArray(R.array.data_food_name)
        val dataDescription = resources.getStringArray(R.array.data_food_description)
        val dataPhotos = resources.getStringArray(R.array.data_food_foto)
        val dataCity = resources.getStringArray(R.array.data_food_city)
        val dataPrice = resources.getStringArray(R.array.data_food_price)
        val dataTag = resources.getStringArray(R.array.data_food_tag)
        val dataRating = resources.getStringArray(R.array.data_food_rating)
        for (i in dataName.indices) {
            val food = FoodModel(dataName[i], dataPhotos[i], dataRating[i].toFloat(), dataCity[i], dataDescription[i], dataPrice[i], dataTag[i])
            listFood.add(food)
        }

        //val classFood = arrayListOf("Ayam Betutu", "Gado-gado Betawi", "Kue Cucur", "Kue Serabi","Lumpia", "Pecel Lele", "Rawon", "Roti Ganjel Rel", "Sambal Matah", "Sate Lilit", "Sate Madura", "Seblak", "Semur Jengkol", "Tahu Petis", "Wingko Babat", "Model Belum Terlalu Ahli")
        binding.tvPredict.setText(listFood[maxPos].name)
        binding.detailButton.visibility = View.VISIBLE

        data.name = listFood[maxPos].name
        data.image = listFood[maxPos].image
        data.rating = listFood[maxPos].rating
        data.city = listFood[maxPos].city
        data.description = listFood[maxPos].description
        data.price = listFood[maxPos].price
        data.tag = listFood[maxPos].tag

        foodModel.close()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 3) {
//                var image: Bitmap = data?.extras?.get("data") as Bitmap
//                val dimension = Math.min(image.width, image.height)
//                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
//                binding.previewImageView.setImageBitmap(image)
//
//
//                classifyImage(image)
//            }else {
//                val dat: Uri = data?.data as Uri
//                var image : Bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, dat)
//                binding.previewImageView.setImageBitmap(image)
//
//                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
//                classifyImage(image)
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.captureButton.setOnClickListener {
            startTakePhoto()
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(intent, 3)
        }

        binding.selectGalleryButton.setOnClickListener {
            startGallery()
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(intent, 1)
        }

        binding.detailButton.setOnClickListener {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            //intent.putExtra("DATA", FoodModel("Indonesia", "https://static6.depositphotos.com/1011646/544/i/600/depositphotos_5440451-stock-photo-flag-of-indonesia.jpg", 4f, "Bengkulu", "XXXXX", "Rp. 40.000", "#bengkulu"))
            intent.putExtra("DATA", data)
            startActivity(intent)
        }

        Glide.with(requireContext())
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6zes53m4a_2VLTcmTn_bHk8NO5SkuWfcQbg&usqp=CAU")
            .circleCrop()
            .into(binding.ivProfil)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireActivity(),
                    "Tidak mendapatkan permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private var getFile: File? = null

    private lateinit var currentPhotoPath: String

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)

            getFile = myFile

            var result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                true
            )

            binding.previewImageView.setImageBitmap(result)
            classifyImage(result)
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, isBackCamera: Boolean = false): Bitmap {
        val matrix = Matrix()
        return if (isBackCamera) {
            matrix.postRotate(90f)
            Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
        } else {
            matrix.postRotate(-90f)
            matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
            Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matrix,
                true
            )
        }
    }

    private fun createTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }


    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)

        createTempFile(requireContext()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.example.mencak",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireActivity())

            var result2 = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                true
            )

            getFile = myFile
            binding.previewImageView.setImageURI(selectedImg)
            classifyImage(result2)
        }
    }

    private fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createTempFile(requireContext().toString())

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
    }

    private fun outputGenerator(bitmap: Bitmap) {
        val foodModel = FoodModelML.newInstance(requireContext())

        val image = TensorImage.fromBitmap(bitmap)
        var byteBuffer = image.buffer
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = foodModel.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        var max = getMax(outputFeature0.floatArray)
        binding.tvPredict.setText(foodList[max])

        foodModel.close()
    }

    fun getMax(arr:FloatArray) : Int{
        var index = 0
        var min = 0.0f

        for(i in 0..1000){
            if(arr[i]>min){
                index = i
                min = arr[i]
            }
        }
        return index

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}