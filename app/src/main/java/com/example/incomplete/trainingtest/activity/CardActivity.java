package com.example.incomplete.trainingtest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.incomplete.trainingtest.R;
import com.example.incomplete.trainingtest.adapter.CardAdapter;
import com.example.incomplete.trainingtest.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class CardActivity extends BaseActivity implements CardAdapter.OnCardExitListener {

    private SwipeFlingAdapterView flingContainer;
    private CardAdapter cardAdapter;
    private ArrayList<String> cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        cards = new ArrayList<>();
        cards.add("第一个");
        cards.add("第二个");
        cards.add("第三个");
        cards.add("第四个");
        cards.add("第五个");
        cards.add("第六个");
        initCards();


    }

    public void initCards() {

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.fling_view);
        flingContainer.setVisibility(View.VISIBLE);
        cardAdapter = new CardAdapter(this, (ArrayList<? extends Object>) cards);
        cardAdapter.setOnCardExitListener(this);
        flingContainer.setAdapter(cardAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                cardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                try {
                    View selectedView = flingContainer.getSelectedView();
                    selectedView.findViewById(R.id.fl_ignore).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);// 左滑忽略
                    selectedView.findViewById(R.id.fl_next).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);// 右滑下一条
                } catch (Exception e) {
                    Log.e("e", "Exception:" + e.getMessage());
                }
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                if (cardAdapter.getData().contains(dataObject)) {
                    //打点的逻辑 v2.51

                    cardAdapter.getData().remove(dataObject);
                    cardAdapter.getData().add(dataObject);
                }
            }

            @Override
            public void onLeftCardExit(Object dataObject) {


                cardAdapter.getData().remove(dataObject);
                cardAdapter.notifyDataSetChanged();

//                if (cardAdapter.getData().contains(dataObject)) {


//                                if (dataObject instanceof ConnectionCard) {
//                                    LPAnalytics.addLogRequest(getActivity(), LPInfo.TLOG_C, LPTLogConstants.TYPE_C.ON_LEFT_CARD_EXIT);
//                                    ConnectionCard item = (ConnectionCard) dataObject;
//                                    cardAdapter.getData().remove(dataObject);
//                                    requestDeleteCard(item);
//                                }
//                }
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                if (cardAdapter.getData() == null || cardAdapter.getData().isEmpty()) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            flingContainer.setVisibility(View.GONE);
//                                    llTopCards.setBackgroundResource(R.drawable.fliping_bg_empty_card);
//                                    adapter.setOnCardExitListener(null);
                        }
                    }, 100);
                }
            }
        });
//        flingContainer.setOnItemClickListener(this);


    }


    @Override
    public void onLeftExit(Object item) {

    }

    @Override
    public void onRightExit(Object item) {

    }
}

