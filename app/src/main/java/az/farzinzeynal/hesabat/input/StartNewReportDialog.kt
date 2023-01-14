package az.farzinzeynal.hesabat.input

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import az.farzinzeynal.hesabat.R

class StartNewReportDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_start_new_report, container, false);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStartReport = view.findViewById<Button>(R.id.btnStartReport)

        btnStartReport.setOnClickListener {
            dismiss()
        }

    }
}