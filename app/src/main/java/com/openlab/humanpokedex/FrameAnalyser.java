    package com.openlab.humanpokedex;

import android.content.Context;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.JsonParser;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import org.json.JSONArray;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class FrameAnalyser implements ImageAnalysis.Analyzer {

    private FaceDetectorOptions realTimeOpts = new FaceDetectorOptions.Builder().setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST).build();
    private FaceDetector detector;
    private boolean isProcessing                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            ;
    private ArrayList<Pair<String, Float>> faceList = new ArrayList<>();
    private FaceNetModel faceNetModel;
    private String metricToBeUsed;
    private Context getContext;
    private FaceNetModel model;

    public FrameAnalyser(Context context, BoundingBoxOverlay boundingBoxOverlay) {
        faceList = new ArrayList<>();
        faceNetModel = new FaceNetModel(context);
        isProcessing = AtomicBoolean(false);
        metricToBeUsed = "l2";
        detector = FaceDetection.getClient(realTimeOpts);
        getContext = context;
        model = FaceNetModel(context);
    }

    @Override
    public void analyze(@NonNull ImageProxy image, int rotationDegrees) {
        Bitmap bitmap = toBitmap(image);

        if (isProcessing) {
            return;
        } else {
            isProcessing = true;

            InputImage inputImage = InputImage.fromByteArray(BitmaptoNv21(bitmap), 640, 480, rotationDegrees, InputImage.IMAGE_FORMAT_NV21);
            detector.process(inputImage).addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                @Override
                public void onSuccess(@NonNull List<Face> faces) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runModel(faces, bitmap);
                        }
                    }).start();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void runModel(List<Face> faces, Bitmap cameraFrameBitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(getContext.getFilesDir(), "faceList.txt");
                JsonParser
                ArrayList<Prediction> predictions = new ArrayList<>();
                for (Face face : faces) {
                    try {
                        String subject = model.getFaceEmbedding(cameraFrameBitmap, face.getBoundingBox(), true, RecognizeFaceActivity.isRearCameraOn());
                        Map<String, ArrayList<Float>> nameScoreHashMap = new ArrayList<>();
                    }
                }
            }
        })
    }
}
