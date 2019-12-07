package mail.gvsu.edu.ocrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
        text = (TextView)findViewById(R.id.textView2);
    }


    public void getTextFromImage(View view) {
        //store image in Bitmap variable
        Bitmap bitmap = BitmapFactory.decodeResource(getApplication().getResources(), R.drawable.textarea01);

        //Initialize Text Recognizer
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        //if there's no text in image
        if(!textRecognizer.isOperational()){
            Toast.makeText(getApplicationContext(), "Could not get the Text", Toast.LENGTH_SHORT).show();
        }else{
            //store bitmap into a frame
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();

            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            //Read text from image
            for(int i = 0; i <items.size(); ++i)
            {
                TextBlock myItem = items.valueAt(i);
                stringBuilder.append(myItem.getValue());
                stringBuilder.append("\n");
            }
            //Set text of TextView to the string read from image
            text.setText(stringBuilder.toString());
        }
    }
}
