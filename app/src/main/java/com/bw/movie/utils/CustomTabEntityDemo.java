package com.bw.movie.utils;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 20:31
 */
public class CustomTabEntityDemo implements CustomTabEntity {
    private String tab;
    private int SelectedIcon;
    private int TabUnselectedIcon;

    public CustomTabEntityDemo(String tab, int selectedIcon, int tabUnselectedIcon) {
        this.tab = tab;
        SelectedIcon = selectedIcon;
        TabUnselectedIcon = tabUnselectedIcon;
    }

    @Override
    public String getTabTitle() {
        return tab;
    }

    @Override
    public int getTabSelectedIcon() {
        return SelectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return TabUnselectedIcon;
    }
}
