package com.lesson3940;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Task2ActivityWITHLISTVIEW extends AppCompatActivity {
//TODO change xml
    Button btnCommentZera;
    ImageView imgZera;
    Button btnCommentTass;
    ImageView imgTass;

    Button btnPost;

    TableRow postCommentSection;

    EditText edtComment;

    TextView txtComment;
    ImageView imgComment;

    LinearLayout commentlayout;
    TableLayout rootTable;

    ImageView currentimg;

    Animation appear;
    Animation fadeOut;

    LinearLayout commContain;

    ArrayList<String> allComments = new ArrayList<>();
    ArrayList<ImageView> pictures = new ArrayList<>();
    CommentsAdapter commentsAdapter;
    ListView lv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            allComments.addAll(savedInstanceState.getStringArrayList("commentsFromState"));
        }

        setContentView(R.layout.activity_task2);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowHomeEnabled(true);
            ab.setIcon(android.R.drawable.btn_star_big_on);
        }


        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);

        appear = AnimationUtils.loadAnimation(Task2ActivityWITHLISTVIEW.this, R.anim.appear);
        fadeOut = AnimationUtils.loadAnimation(Task2ActivityWITHLISTVIEW.this, R.anim.fade_out);
// removed for list view
//        commContain = (LinearLayout) findViewById(R.id.commentContainer);

        btnCommentTass = (Button) findViewById(R.id.btn_commentTass);
        imgTass = (ImageView) findViewById(R.id.imageTass);
        showCommentField(btnCommentTass, imgTass);

        btnCommentZera = (Button) findViewById(R.id.btn_commentZera);
        imgZera = (ImageView) findViewById(R.id.imageZera);
        showCommentField(btnCommentZera, imgZera);

        rootTable = (TableLayout) findViewById(R.id.table_parent);
        commentlayout = (LinearLayout) findViewById(R.id.cl);

        imgComment = (ImageView) findViewById(R.id.img_post_image);
        txtComment = (TextView) findViewById(R.id.txt_post_text);

//     LISTVIEW
        commentsAdapter = new CommentsAdapter(this,allComments,pictures);
//        lv = (ListView) findViewById(R.id.listView);
//       END Listview
        btnPost = (Button) findViewById(R.id.btn_post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Task2ActivityWITHLISTVIEW.this, R.anim.shake));
                String comment = edtComment.getText().toString();

                if (comment == null || comment.length()<1){
                    edtComment.setError("Must be at least one char");
                    return;
                }
//                Test ListView
//                int pic = currentimg;
                allComments.add(comment);
                pictures.add(currentimg);
                lv.setAdapter(commentsAdapter);
                commentsAdapter.notifyDataSetChanged();

                //Testing state savings
//                allComments.add(comment);
//                showAllComments();
                // end of test state save
/*
                LinearLayout commentContainer = (LinearLayout) getLayoutInflater().inflate(R.layout.comment_layout, null);

                ImageView comImg = (ImageView) commentContainer.getChildAt(0);
                comImg.setImageDrawable(currentimg.getDrawable());
                TextView comText = (TextView) commentContainer.getChildAt(1);
                comText.setText(comment);

                commentContainer.startAnimation(appear);
                commContain.addView(commentContainer);

                deleteComment(commentContainer.getChildAt(2));

                edtComment.getText().clear();
                postCommentSection.startAnimation(fadeOut);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        postCommentSection.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });*/
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                lv.removeView(view);
//                commentsAdapter.notifyDataSetChanged();
                Toast.makeText(Task2ActivityWITHLISTVIEW.this, "EBASISIISIISI", Toast.LENGTH_SHORT).show();
            }
        });

        postCommentSection = (TableRow) findViewById(R.id.commentRow);
        postCommentSection.setVisibility(View.GONE);

        edtComment = (EditText) findViewById(R.id.edtWriteComment);
    }

    private void showCommentField(Button btn, final ImageView img) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Task2ActivityWITHLISTVIEW.this, R.anim.shake));
                postCommentSection.startAnimation(appear);
                postCommentSection.setVisibility(View.VISIBLE);
                edtComment.requestFocus();
                currentimg = img;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onResume();
    }

    @Override
    protected void onPause() {
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        super.onPause();
    }

    /**
     *Calling predefined parent where all comments are stored to delete
     *the parent of the button which was clicked
     * @param v delete button
     */
    private void deleteComment(View v) {
//        old implementation of delete without listview
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Calling the parent of the parent of dynamically added delete btn
//                ((ViewGroup) (v.getParent()).getParent()).removeView((View) v.getParent());
//
////                commContain.removeView((View) v.getParent());
//            }
//        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("commentsFromState",allComments);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        allComments.addAll(savedInstanceState.getStringArrayList("commentsFromState"));
    }
//    TESTS
    private void showAllComments(){
        StringBuffer allcoms = new StringBuffer();
        for (String com: allComments) {
            allcoms.append(com + "\n");
        }
        Toast.makeText(Task2ActivityWITHLISTVIEW.this, allcoms, Toast.LENGTH_SHORT).show();
    }
}
