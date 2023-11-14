package com.example.sg_news_app.Util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.example.sg_news_app.R

private var pDialog: ProgressDialog? = null

object CustomProgressDialog {

    fun showProgressDialog(context: Context?) {
        try {
            if (pDialog != null) pDialog!!.dismiss()
            pDialog = ProgressDialog(context, R.style.MyAlertDialogStyle)
            try {
                pDialog!!.show()
            } catch (e: WindowManager.BadTokenException) {
                e.printStackTrace()
            }
            pDialog!!.setCancelable(false)
            pDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            pDialog!!.setContentView(R.layout.progress_layout)
            if (!pDialog!!.isShowing) {
                pDialog!!.show()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    /**
     * cancel progress dialog if shown
     */
    fun hideProgressDialog() {
        try {
            if (pDialog != null && pDialog!!.isShowing) pDialog!!.dismiss()
        } catch (exception: Exception) {

        }
    }


}