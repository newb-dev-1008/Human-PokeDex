package com.openlab.humanpokedex;

import android.content.Context;

import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.ops.ResizeOp;

import java.text.Normalizer;

public class FaceNetModel {

    private Interpreter interpreter;
    private int imgSize = 300, embeddingDim = 128;

    private ImageProcessor imageTensorProcessor = new ImageProcessor.Builder().add(new ResizeOp(imgSize, imgSize, ResizeOp.ResizeMethod.BILINEAR))
            .add(new NormalizeOp(127.5f, 127.5f)).build();

    FaceNetModel(Context context) {
        Interpreter.Options interpreterOptions = new Interpreter.Options().setNumThreads(4);
        interpreter = new Interpreter(FileUtil.loadMappedFile(context, ""), interpreterOptions);
    }


}
