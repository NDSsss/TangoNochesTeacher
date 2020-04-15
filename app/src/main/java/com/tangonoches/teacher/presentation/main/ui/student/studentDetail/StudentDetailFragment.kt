package com.tangonoches.teacher.presentation.main.ui.student.studentDetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.tangonoches.teacher.R
import com.tangonoches.teacher.domain.extensions.setTextIfNotEqual
import com.tangonoches.teacher.domain.repositories.constants.DEFAULT_ID
import com.tangonoches.teacher.presentation.base.BaseVmFragment
import com.tangonoches.teacher.presentation.main.ui.student.allStudents.StudentsAllFragment
import kotlinx.android.synthetic.main.frag_student_detail.*
import okhttp3.internal.toLongOrDefault

const val STUDENT_VIEW_TYPE = "STUDENT_VIEW_TYPE"
const val STUDENT_ID = "STUDENT_ID"

class StudentDetailFragment : BaseVmFragment<StudentDetailVm>() {
    override val layoutId: Int
        get() = R.layout.frag_student_detail

    override fun getVmClass(): Class<StudentDetailVm> = StudentDetailVm::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let { args ->
            val id = args.getLong(STUDENT_ID, DEFAULT_ID)
            if (id != DEFAULT_ID) {
                vm.studentId.accept(id)
            }
            vm.viewTypeState.accept(
                StudentsAllFragment.StudentDetailViewType.valueOf(
                    args.getString(
                        STUDENT_VIEW_TYPE, StudentsAllFragment.StudentDetailViewType.ADD.name
                    )
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_student_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_student_detail_delete -> {
                vm.deleteAction.accept(Unit)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun createVmBinds() {
        super.createVmBinds()
        vmBinds.addAll(
            frag_student_detail_name_et.textChanges().skipInitialValue().subscribe {
                vm.nameState.accept(it.toString())
            },
            frag_student_detail_phone_et.textChanges().skipInitialValue().subscribe {
                vm.phoneState.accept(it.toString())
            },
            frag_student_detail_barcode_id_et.textChanges().skipInitialValue()
                .filter {
                    it.toString().toLongOrDefault(DEFAULT_ID) != DEFAULT_ID
                }.subscribe {
                vm.barcodeIdState.accept(it.toString().toLong())
            },
            frag_student_detail_points_et.textChanges().skipInitialValue().filter {
                it.toString().toIntOrNull() != null
            }.subscribe {
                vm.pointsState.accept(it.toString().toInt())
            },
            frag_student_detail_extra_info_et.textChanges().skipInitialValue().subscribe {
                vm.extraInfoState.accept(it.toString())
            },
            frag_student_detail_vk_et.textChanges().skipInitialValue().subscribe {
                vm.vkState.accept(it.toString())
            },
            frag_student_detail_facebook_et.textChanges().skipInitialValue().subscribe {
                vm.facebookState.accept(it.toString())
            },
            frag_student_detail_instagram_et.textChanges().skipInitialValue().subscribe {
                vm.instagramState.accept(it.toString())
            },
            frag_student_detail_save_btn.clicks().subscribe {
              vm.saveStudentAction.accept(Unit)
            },
            vm.nameState.subscribe {
                frag_student_detail_name_et.setTextIfNotEqual(it)
            },
            vm.phoneState.subscribe {
                frag_student_detail_phone_et.setTextIfNotEqual(it)
            },
            vm.barcodeIdState.subscribe {
                frag_student_detail_barcode_id_et.setTextIfNotEqual(it.toString())
            },
            vm.pointsState.subscribe {
                frag_student_detail_points_et.setTextIfNotEqual(it.toString())
            },
            vm.extraInfoState.subscribe {
                frag_student_detail_extra_info_et.setTextIfNotEqual(it)
            },
            vm.vkState.subscribe {
                frag_student_detail_vk_et.setTextIfNotEqual(it)
            },
            vm.facebookState.subscribe {
                frag_student_detail_facebook_et.setTextIfNotEqual(it)
            },
            vm.instagramState.subscribe {
                frag_student_detail_instagram_et.setTextIfNotEqual(it)
            }
        )
    }
}