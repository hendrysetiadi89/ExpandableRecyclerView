package com.example.nakama.expandablerecyclerview.expandableadapter.listeners;

public interface GroupExpandCollapseListener<T> {

  void onGroupExpanded(T group);

  void onGroupCollapsed(T group);
}
