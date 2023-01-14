package az.farzinzeynal.hesabat.input

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import az.farzinzeynal.hesabat.R

class ValueInputDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_value_input, container, false);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val submitButton = view.findViewById<Button>(R.id.btnSubmit)

        submitButton.setOnClickListener {
            dismiss()
        }
    }
}