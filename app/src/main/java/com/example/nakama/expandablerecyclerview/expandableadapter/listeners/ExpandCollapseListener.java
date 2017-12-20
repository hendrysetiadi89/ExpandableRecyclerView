package com.example.nakama.expandablerecyclerview.expandableadapter.listeners;

public interface ExpandCollapseListener<T> {

    void onGroupExpanded(int positionStart, int itemCount);

    void onGroupCollapsed(int positionStart, int itemCount);

    int getChildCount(T group);

}
