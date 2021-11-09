package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;

import java.util.List;

public class FaceActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face);
        mContext = this;

        final RelativeLayout RelativeLayout_main = findViewById(R.id.RelativeLayout_main);

        // High-accuracy landmark detection and face classification
        FaceDetectorOptions options =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.img1);

        InputImage image = InputImage.fromBitmap(bitmap, 0);

        FaceDetector detector = FaceDetection.getClient(options);
        // Or use the default options:
        // FaceDetector detector = FaceDetection.getClient();

        Task<List<Face>> result =
                detector.process(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<Face>>() {
                                    @Override
                                    public void onSuccess(List<Face> faces) {
                                        // Task completed successfully
                                        // ...
                                        Log.d("FACES", faces.toString());

                                        Point p = new Point();
                                        Display display = getWindowManager().getDefaultDisplay();
                                        display.getSize(p);

                                        // p.x point 의 p 변수의 x, y = 스크린상의 좌표
                                        float lex = 0f, ley = 0f;
                                        float lcx = 0f, lcy = 0f;
                                        float rcx = 0f, rcy = 0f;

                                        for (Face face : faces) {

                                            FaceLandmark leftEye = face.getLandmark(FaceLandmark.LEFT_EYE);
                                            if (leftEye != null) {
                                                lex = leftEye.getPosition().x;
                                                ley = leftEye.getPosition().y;
                                            }
                                            FaceLandmark leftCheek = face.getLandmark(FaceLandmark.LEFT_CHEEK);
                                            if (leftCheek != null) {
                                                lcx = leftCheek.getPosition().x;
                                                lcy = leftCheek.getPosition().y;
                                            }
                                            FaceLandmark rightCheek = face.getLandmark(FaceLandmark.RIGHT_CHEEK);
                                            if (rightCheek != null) {
                                                rcx = rightCheek.getPosition().x;
                                                rcy = rightCheek.getPosition().y;
                                            }

                                            ImageView imageLeftEye = new ImageView(mContext);
                                            imageLeftEye.setImageResource(R.drawable.img4);
                                            imageLeftEye.setX(p.x * lex / bitmap.getWidth() - 100);
                                            imageLeftEye.setY(p.y * ley / bitmap.getHeight() - 100);
                                            imageLeftEye.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                            RelativeLayout_main.addView(imageLeftEye);

                                            ImageView imageLeftCheek = new ImageView(mContext);
                                            imageLeftCheek.setImageResource(R.drawable.left_cheek);
                                            imageLeftCheek.setX(p.x * lcx / bitmap.getWidth() - 100);
                                            imageLeftCheek.setY(p.y * lcy / bitmap.getHeight() - 100);
                                            imageLeftCheek.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                            RelativeLayout_main.addView(imageLeftCheek);

                                            ImageView imageRightCheek = new ImageView(mContext);
                                            imageRightCheek.setImageResource(R.drawable.right_cheek);
                                            imageRightCheek.setX(p.x * rcx / bitmap.getWidth() - 100);
                                            imageRightCheek.setY(p.y * rcy / bitmap.getHeight() - 100);
                                            imageRightCheek.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                                            RelativeLayout_main.addView(imageRightCheek);
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });

    }
}