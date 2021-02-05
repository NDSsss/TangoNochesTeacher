package com.tangonoches.teacher.presentation.view.dialogScanStudent

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.main.ui.lessons.scanStudent.CAMERA_PERMISSIONS_REQUEST
import kotlinx.android.synthetic.main.dialog_student_scan.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScanStudentDialog(
    private val idResultListener: (Long) -> Unit
) : DialogFragment(),
    ZBarScannerView.ResultHandler {

    companion object {
        val TAG = "ScanStudentDialog_TAG"
    }

    private var permissonsGranted = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_student_scan, null, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //request
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSIONS_REQUEST
                )
            } else {
                //granted
                permissonsGranted = true
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSIONS_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissonsGranted = true
            } else {
                Toast.makeText(
                    requireContext(),
                    "Необходимы разрешение, что бы запустить сканирование",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    public override fun onResume() {
        super.onResume()
        if (permissonsGranted) {
            dialog_student_scan_zbar.setResultHandler(this) // Register ourselves as a handler for scan results.
            dialog_student_scan_zbar.startCamera()          // Start camera on resume
        }
    }

    public override fun onPause() {
        super.onPause()
        if (permissonsGranted) {
            dialog_student_scan_zbar.stopCamera()
        }// Stop camera on pause
    }


    override fun handleResult(rawResult: Result?) {
        // If you would like to resume scanning, call this method below:
//        activity_login_zbar.resumeCameraPreview(this)
        try {
            val barcodeContent = rawResult?.contents?.toLong() ?: -1L
            if (barcodeContent > 0) {
                idResultListener(barcodeContent)
                dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неверный формат, попробуйте еще",
                    Toast.LENGTH_LONG
                ).show()
                resumeScanning()
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(
                requireContext(),
                "Неверный формат, попробуйте еще",
                Toast.LENGTH_LONG
            ).show()
            resumeScanning()
        }
    }


    private fun resumeScanning() {
        dialog_student_scan_zbar.resumeCameraPreview(this)
    }
}