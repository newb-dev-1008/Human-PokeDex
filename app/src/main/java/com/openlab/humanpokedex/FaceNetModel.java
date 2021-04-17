package com.openlab.humanpokedex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.Image;
import android.text.format.Time;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.text.Normalizer;
import java.util.ArrayList;

public class FaceNetModel {

    private Interpreter interpreter;
    private int imgSize = 300, embeddingDim = 128;

    private ImageProcessor imageTensorProcessor = new ImageProcessor.Builder().add(new ResizeOp(imgSize, imgSize, ResizeOp.ResizeMethod.BILINEAR))
            .add(new NormalizeOp(127.5f, 127.5f)).build();

    FaceNetModel(Context context) {
        Interpreter.Options interpreterOptions = new Interpreter.Options().setNumThreads(4);
        interpreter = new Interpreter(FileUtil.loadMappedFile(context, ""), interpreterOptions);
    }

    private ArrayList<Float> getFaceEmbedding(Bitmap image , Rect crop, Boolean preRotate, Boolean isRearCameraOn) {
        return runFaceNet(convertBitmapToBuffer(cropRectFromBitmap(image, crop, preRotate, isRearCameraOn )))[0];
    }

    private ArrayList<Float> runFaceNet(ByteBuffer buffer) {
        long t1 = System.currentTimeMillis();
        ArrayList<Float> output = new ArrayList<>();
        interpreter.run(buffer, output);
        return output;
    }

    private ByteBuffer convertBitmapToBuffer(Bitmap image) {
        TensorImage imageTensor = imageTensorProcessor.process(TensorImage.fromBitmap(image));
        return imageTensor.getBuffer();
    }
}
