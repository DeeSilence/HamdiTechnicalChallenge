package com.shakatreh.hamditechnicalchallenge.globals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    abstract fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View
    abstract fun setTitle(): String?
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutResource(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()?.let { activity?.title = it }
    }

    fun showProgress() {
        activity?.let {
            LoaderDialog.getInstance(it).showProgress()
        }
    }

    fun hideProgress() {
        activity?.let {
            LoaderDialog.getInstance(it).hideProgress()
        }
    }

    fun showSnackBar(msg: String?) {
        view?.let {
            if (msg.isNullOrEmpty())
                return
            val snak by lazy { Snackbar.make(it, msg, Snackbar.LENGTH_LONG) }
            snak.view.translationY = (-170).toFloat()
            snak.show()

        }
    }

    fun onBackPressed(todo: () -> Unit) =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            todo()
        }
}