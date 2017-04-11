package com.dji.sdk.sample.internal.view;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.dji.sdk.sample.internal.controller.DJISampleApplication;
import com.dji.sdk.sample.internal.utils.DialogUtils;
import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;

import java.util.Arrays;
import java.util.List;

import dji.common.error.DJIError;
import dji.common.util.CommonCallbacks;

/**
 * Created by qiyut on 2017/4/10.
 */

public class BlocklyView extends AbstractBlocklyActivity{
    private static final String TAG = "SimpleActivity";

    private static final List<String> BLOCK_DEFINITIONS = Arrays.asList(
            "default/flightcontroller_blocks.json"
    );
    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
            // Custom block generators go here. Default blocks are already included.
            // TODO(#99): Include Javascript defaults when other languages are supported.
    );

    CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new LoggingCodeGeneratorCallback(this, TAG);

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return BLOCK_DEFINITIONS;
    }

    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        return "default/toolbox.xml";
    }

    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return JAVASCRIPT_GENERATORS;
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        // Uses the same callback for every generation call.
        DJISampleApplication.getAircraftInstance()
                .getFlightController()
                .startTakeoff(new CommonCallbacks.CompletionCallback() {
                    @Override
                    public void onResult(DJIError djiError) {
                        //DialogUtils.showDialogBasedOnError(getco, djiError);
                        Log.d("Error","Takeoff error");
                    }
                });
        return mCodeGeneratorCallback;
    }
}
