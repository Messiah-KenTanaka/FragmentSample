package com.beit_and_pear.android.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListFragment extends Fragment {
    // 大画面かどうかの判定フラグ
    private boolean _isLayoutXLarge = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity parentActivity = getActivity();
        View menuThanksFrame = parentActivity.findViewById(R.id.menuThanksFrame);

        if (menuThanksFrame == null) {
            _isLayoutXLarge = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Activity parentActivity = getActivity();
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        ListView lvMenu = view.findViewById(R.id.lvMenu);
        List<Map<String, String>> menuList = new ArrayList<>();

        // ~menuListデータ生成処理~
        // 「から揚げ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", "800円");
        menuList.add(menu);

        // 「ハンバーグ定食」のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録。
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        // 以下データ登録の繰り返し。
        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", "1000円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", "750円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", "900円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", "900円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "回鍋肉定食");
        menu.put("price", "750円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "麻婆豆腐定食");
        menu.put("price", "800円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "青椒肉絲定食");
        menu.put("price", "900円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", "850円");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼肉定食");
        menu.put("price", "950円");
        menuList.add(menu);

        String[] from = {"name", "price"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(parentActivity, menuList, android.R.layout.simple_list_item_2, from, to);
        lvMenu.setAdapter(adapter);

        // リスナの登録
        lvMenu.setOnItemClickListener(new ListItemClickListener());
        return view;
    }


    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            String menuName = item.get("name");
            String menuPrice = item.get("price");

            Activity parentActivity = getActivity();

            Bundle bundle = new Bundle();
            bundle.putString("menuName", menuName);
            bundle.putString("menuPrice", menuPrice);

            // 大画面の場合
            if (_isLayoutXLarge) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MenuThanksFragment menuThanksFragment = new MenuThanksFragment();
                menuThanksFragment.setArguments(bundle);
                transaction.replace(R.id.menuThanksFrame, menuThanksFragment);
                transaction.commit();
            }else{
                Intent intent = new Intent(parentActivity, MenuThanksActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            Intent intent = new Intent(parentActivity, MenuThanksActivity.class);
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);

            startActivity(intent);
        }
    }

}