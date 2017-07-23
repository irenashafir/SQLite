package shafir.irena.sqlite.dialogs;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import shafir.irena.sqlite.R;
import shafir.irena.sqlite.models.Todo;
import shafir.irena.sqlite.sqlite.DAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTodoFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    EditText etMission;
    Spinner spImportance;  // entries = @array/importance (we do the array in value/ string)
    Button btnDone;

    public AddTodoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_todo, container, false);
        etMission = v.findViewById(R.id.etMission);
        spImportance = v.findViewById(R.id.spImportance);
        btnDone = v.findViewById(R.id.btnDone);

        btnDone.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {
        String mission = etMission.getText().toString();
        String importance = spImportance.getSelectedItem().toString();

        DAO.getInstance(getContext()).addTodo(mission, importance);
        dismiss();

        // for spinner we use on item selected & not clicked
        //spImportance.setOnItemSelectedListener(this);
        Todo todo = new Todo(mission, importance);

        Intent intent= new Intent("addedTodo");
        intent.putExtra("todo", todo);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

    }




}
