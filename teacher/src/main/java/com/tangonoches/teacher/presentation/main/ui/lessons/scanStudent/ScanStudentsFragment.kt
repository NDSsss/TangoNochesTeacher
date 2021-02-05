package com.tangonoches.teacher.presentation.main.ui.lessons.scanStudent

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding2.view.clicks
import com.tangonoches.teacher.R
import com.tangonoches.teacher.presentation.base.BaseTeacherFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.frag_scan_students.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.util.concurrent.TimeUnit

const val CAMERA_PERMISSIONS_REQUEST = 1234

class ScanStudentsFragment : BaseTeacherFragment<ScanStudentsFragmentVm>(),
    ZBarScannerView.ResultHandler {
    override val layoutId: Int = R.layout.frag_scan_students

    override fun getVmClass(): Class<ScanStudentsFragmentVm> =
        ScanStudentsFragmentVm::class.java


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
                runCamera()
            }
        }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            frag_scan_students_confirm_btn.clicks().subscribe {
                vm.confirmAction.accept(Unit)
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSIONS_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                runCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Необходимы разрешение, что бы запустить сканирование",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun runCamera() {
        vm.permissionsGranted = true

    }

    public override fun onResume() {
        super.onResume()
        if (vm.permissionsGranted) {
            frag_scan_students_zbar.setResultHandler(this) // Register ourselves as a handler for scan results.
            frag_scan_students_zbar.startCamera()          // Start camera on resume
        }
    }

    public override fun onPause() {
        super.onPause()
        if (vm.permissionsGranted) {
            frag_scan_students_zbar.stopCamera()
        }// Stop camera on pause
    }


    override fun handleResult(rawResult: Result?) {
        // If you would like to resume scanning, call this method below:
//        activity_login_zbar.resumeCameraPreview(this)
        try {
            val barcodeContent = rawResult?.contents?.toLong() ?: -1L
            if (barcodeContent > 0) {
                vm.codeScannedAction.accept(barcodeContent)
                resumeScanning()
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
        vmBinds.add(
            Completable.complete().delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    frag_scan_students_zbar.resumeCameraPreview(this)
                }
        )
    }
}